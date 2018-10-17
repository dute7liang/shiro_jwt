package com.duteliang;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @Auther: zl
 * @Date: 2018-10-17 15:45
 */
@SpringBootApplication
@Slf4j
@EnableTransactionManagement
public class Application {

	public static void main(String[] args) {
		ConfigurableApplicationContext run = SpringApplication.run(Application.class, args);
		log.info("项目已启动！");
	}
}
