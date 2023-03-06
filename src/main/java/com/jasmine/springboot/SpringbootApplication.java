package com.jasmine.springboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 应用启动类
 *
 * @author xieshanghan
 * @version SpringbootApplication.java, v 0.1, 2023年01月04日 10:47 xieshanghan
 */
@EnableScheduling
@SpringBootApplication
@MapperScan("com.jasmine.springboot.dal.daointerface")
public class SpringbootApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootApplication.class, args);
	}

}