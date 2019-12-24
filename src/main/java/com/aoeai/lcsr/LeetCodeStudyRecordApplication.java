package com.aoeai.lcsr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.aoeai.lcsr.dao.mapper")
@SpringBootApplication(scanBasePackages = {"com.aoeai"})
public class LeetCodeStudyRecordApplication {

    public static void main(String[] args) {
        SpringApplication.run(LeetCodeStudyRecordApplication.class, args);
    }

}
