package com.crawler.qqrobot;


import love.forte.simboot.spring.autoconfigure.EnableSimbot;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


import java.util.TimeZone;

@EnableSimbot
@SpringBootApplication
public class QqRobotApplication {

    public static void main(String[] args) {
        SpringApplication.run(QqRobotApplication.class, args);
        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));
    }

}
