package com.quasar.sika.design.server.common.system.pojo.response;

import cn.hutool.core.date.BetweenFormater;
import cn.hutool.core.date.DateUtil;
import cn.hutool.system.JavaRuntimeInfo;
import cn.hutool.system.JvmInfo;
import cn.hutool.system.OsInfo;
import cn.hutool.system.SystemUtil;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.google.common.collect.Lists;
import com.sika.code.common.number.util.NumberUtil;
import com.sun.management.OperatingSystemMXBean;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.lang.management.MemoryMXBean;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author daiqi
 * @create 2021-01-06 23:09
 */
@Data
@Accessors(chain = true)
public class SystemResponseBO {
    private static final Date START_DATE = DateUtil.date();
    private static final long KB = 1024;
    private static final long MB = KB * 1024;
    private static final long GB = MB * 1024;
    private static final int SCALE = 2;
    private Cpu cpu;
    private Memory memory;
    private Server server;
    private Jvm jvm;
    private List<Disk> disks;


    public SystemResponseBO build() {
        this.cpu = new Cpu().build();
        this.memory = new Memory().build();
        this.server = new Server().build();
        this.jvm = new Jvm().build();
        this.disks = Disk.build();
        return this;
    }

    private static String computeRate(long v1, long v2) {
        BigDecimal rate = NumberUtil.div(NumberUtil.toBigDecimal(v1), NumberUtil.toBigDecimal(v2));
        return formatForRate(rate);
    }

    private static String formatForRate(BigDecimal rate) {
        String format = "#.##%";
        return NumberUtil.decimalFormat(format, rate);
    }

    private static BigDecimal divFor100(Number number) {
        return NumberUtil.div(NumberUtil.toBigDecimal(number), 100);
    }

    private static String formatForSize(long size, long unit) {
        String format = "0.##";
        BigDecimal bigDecimal = NumberUtil.toBigDecimal(size);
        BigDecimal unitDecimal = NumberUtil.toBigDecimal(unit);
        return NumberUtil.decimalFormat(format, NumberUtil.div(bigDecimal, unitDecimal));
    }


    private static String convertFileSize(long size) {
        if (size >= GB) {
            return formatForSize(size, GB) + "G";
        } else if (size >= MB) {
            return formatForSize(size, MB) + "MB";
        } else if (size >= KB) {
            return formatForSize(size, KB) + "KB";
        } else {
            return size + "B";
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Cpu {

        /**
         * cpu核心数
         */
        private Integer cpuNum;

        /**
         * CPU总的使用率
         */
        private String total;

        /**
         * CPU系统使用率
         */
        private String sys;

        /**
         * CPU用户使用率
         */
        private String used;

        /**
         * CPU当前等待率
         */
        private String wait;

        /**
         * CPU当前空闲率
         */
        private String free;

        /**
         * CPU型号信息
         */
        private String cpuModel;

        private Cpu build() {
            CpuInfo cpuInfo = OshiUtil.getCpuInfo();
            this.total = formatForRate(divFor100(cpuInfo.getToTal()));
            this.sys = formatForRate(divFor100(cpuInfo.getSys()));
            this.used = formatForRate(divFor100(cpuInfo.getUsed()));
            this.wait = formatForRate(divFor100(cpuInfo.getWait()));
            this.free = formatForRate(divFor100(cpuInfo.getFree()));
            this.cpuNum = cpuInfo.getCpuNum();
            this.cpuModel = cpuInfo.getCpuModel();
            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Memory {
        private String total;
        private String used;
        private String free;
        private String usedRate;
        private String jvmTotal;
        private String jvmUsed;
        private String jvmFree;
        private String jvmUsedRate;

        private Memory build() {
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) SystemUtil.getOperatingSystemMXBean();
            long totalTemp = osmxb.getTotalPhysicalMemorySize();
            long freeTemp = osmxb.getFreePhysicalMemorySize();
            long usedTemp = totalTemp - freeTemp;

            this.total = convertFileSize(totalTemp);
            this.free = convertFileSize(freeTemp);
            this.used = convertFileSize(usedTemp);
            this.usedRate = computeRate(usedTemp, totalTemp);


            MemoryMXBean mxb = SystemUtil.getMemoryMXBean();
            long jvmTotalTemp = mxb.getHeapMemoryUsage().getMax();
            long jvmUsedTemp = mxb.getHeapMemoryUsage().getUsed();
            long jvmFreeTemp = jvmTotalTemp - jvmUsedTemp;

            this.jvmTotal = convertFileSize(jvmTotalTemp);
            this.jvmFree = convertFileSize(jvmFreeTemp);
            this.jvmUsed = convertFileSize(jvmUsedTemp);
            this.jvmUsedRate = computeRate(jvmUsedTemp, jvmTotalTemp);

            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Server {
        private String name;
        private String operateSystem;
        private String arch;
        private String version;
        private String hostIp;
        private String hostName;

        private Server build() {
            OsInfo osInfo = SystemUtil.getOsInfo();
            this.name = osInfo.getName();
            this.arch = osInfo.getArch();
            this.version = osInfo.getVersion();
            this.operateSystem = SystemUtil.get("os.name", false);

            this.hostIp = SystemUtil.getHostInfo().getAddress();
            this.hostName = SystemUtil.getHostInfo().getName();
            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Jvm {
        private String name;
        private String version;
        private String vendor;

        private String startDate;
        private String runTime;
        private String homeDir;
        private String projectPath;

        private Jvm build() {
            JvmInfo jvmInfo = SystemUtil.getJvmInfo();
            this.name = jvmInfo.getName();
            this.version = SystemUtil.getJavaInfo().getVersion();
            this.vendor = jvmInfo.getVendor();

            Date startDateTemp = DateUtil.date(SystemUtil.getRuntimeMXBean().getStartTime());
            this.runTime = DateUtil.formatBetween(startDateTemp, DateUtil.date(),  BetweenFormater.Level.SECOND);
            this.startDate = DateUtil.formatDateTime(startDateTemp);
            JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
            this.homeDir = javaRuntimeInfo.getHomeDir();
            this.projectPath = SystemUtil.get(SystemUtil.USER_DIR);
            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Disk {
        private String total;
        private String free;
        private String used;
        private String usedRate;

        private static List<Disk> build() {
            List<Disk> disks = Lists.newArrayList();
            Disk disk = new Disk();
            // 磁盘使用情况
            File[] files = File.listRoots();
            for (File file : files) {
                long total = file.getTotalSpace();
                long free = file.getFreeSpace();
                long used = total -  free;
                disk.setTotal(convertFileSize(total));
                disk.setFree(convertFileSize(free));
                disk.setUsed(convertFileSize(used));
                disk.setUsedRate(computeRate(used, total));
                disks.add(disk);
            }
            return disks;
        }
    }

}
