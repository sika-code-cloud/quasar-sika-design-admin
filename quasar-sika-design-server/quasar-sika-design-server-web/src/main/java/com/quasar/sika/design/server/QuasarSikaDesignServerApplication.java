package com.quasar.sika.design.server;

import com.sika.code.basic.constant.BaseComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


/**
 * 启动类
 * <p>
 * exclude = {DataSourceAutoConfiguration.class}非常重要，如果没有关闭，在启动程序时会发生循环依赖的错误
 *
 * @author daiqi
 * @create 2019-05-10 21:16
 */
@SpringBootApplication(scanBasePackages = {BaseComponentScan.COM_EASY_CLOUD, "com.quasar.sika.design.server"}, exclude = {DataSourceAutoConfiguration.class})
@MapperScan({"com.quasar.sika.design.server.**.mapper"})
public class QuasarSikaDesignServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuasarSikaDesignServerApplication.class, args);
    }
}
