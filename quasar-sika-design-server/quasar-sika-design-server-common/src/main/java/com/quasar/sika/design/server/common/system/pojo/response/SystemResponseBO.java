package com.quasar.sika.design.server.common.system.pojo.response;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.system.*;
import cn.hutool.system.oshi.CpuInfo;
import cn.hutool.system.oshi.OshiUtil;
import com.sika.code.common.number.util.NumberUtil;
import com.sun.management.OperatingSystemMXBean;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author daiqi
 * @create 2021-01-06 23:09
 */
@Data
@Accessors(chain = true)
public class SystemResponseBO {
    private static final Date START_DATE = DateUtil.date();
    private Cpu cpu;
    private Memory memory;
    private Server server;
    private Jvm jvm;
    private Disk disk;


    public SystemResponseBO build() {
        this.cpu = new Cpu().build();
        this.memory = new Memory().build();
        this.server = new Server().build();
        this.jvm = new Jvm().build();
        this.disk = new Disk().build();
        return this;
    }

    private static BigDecimal computeRate(long v1, long v2) {
        return NumberUtil.div(new BigDecimal(v1 * 100), new BigDecimal(v2), 2);
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
        private double toTal;

        /**
         * CPU系统使用率
         */
        private double sys;

        /**
         * CPU用户使用率
         */
        private double used;

        /**
         * CPU当前等待率
         */
        private double wait;

        /**
         * CPU当前空闲率
         */
        private double free;

        /**
         * CPU型号信息
         */
        private String cpuModel;

        private Cpu build() {
            CpuInfo cpuInfo = OshiUtil.getCpuInfo();
            BeanUtil.copyProperties(cpuInfo, this);
            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Memory {
        private long total;
        private long used;
        private long free;
        private BigDecimal usedRate;
        private long jvmTotal;
        private long jvmUsed;
        private long jvmFree;
        private BigDecimal jvmUsedRate;

        private Memory build() {
            OperatingSystemMXBean osmxb = (OperatingSystemMXBean) SystemUtil.getOperatingSystemMXBean();
            this.total = osmxb.getTotalPhysicalMemorySize();
            this.free = osmxb.getFreePhysicalMemorySize();
            this.used = total - free;
            this.usedRate = computeRate(used, total);

            RuntimeInfo runtimeInfo = SystemUtil.getRuntimeInfo();
            this.jvmTotal = runtimeInfo.getTotalMemory();
            this.jvmFree = runtimeInfo.getFreeMemory();
            this.jvmUsed = runtimeInfo.getMaxMemory();
            this.jvmUsedRate = computeRate(jvmUsed, jvmTotal);
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

        private Date startDate;
        private String runTime;
        private String homeDir;
        private String projectPath;

        private Jvm build() {
            JvmInfo jvmInfo = SystemUtil.getJvmInfo();
            this.name = jvmInfo.getName();
            this.version = jvmInfo.getVersion();
            this.vendor = jvmInfo.getVendor();

            this.startDate = DateUtil.date(SystemUtil.getRuntimeMXBean().getStartTime());
            this.runTime = DateUtil.formatBetween(startDate, DateUtil.date());
            JavaRuntimeInfo javaRuntimeInfo = SystemUtil.getJavaRuntimeInfo();
            this.homeDir = javaRuntimeInfo.getHomeDir();
            this.projectPath = javaRuntimeInfo.getClassPath();
            return this;
        }
    }

    @Data
    @Accessors(chain = true)
    private static class Disk {
        private long total;
        private long free;
        private long used;
        private BigDecimal usedRate;

        private Disk build() {
            // 磁盘使用情况
            File[] files = File.listRoots();
            for (File file : files) {
                this.total = file.getTotalSpace();
                this.free = file.getTotalSpace();
                this.used = file.getUsableSpace();
                this.usedRate = computeRate(used, total);

            }
            return this;
        }
    }

}
