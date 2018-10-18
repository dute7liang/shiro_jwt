package com.duteliang.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import tk.mybatis.spring.mapper.MapperScannerConfigurer;

import java.util.Properties;

/**
 * @Description:
 * @Auther: zl
 * @Date: 2018-9-25 14:29
 */
//@SpringBootConfiguration
public class TkMybatis {

	@Autowired
	private SqlSessionFactory sqlSessionFactory;

	/**
	 * 配置 MapperScannerConfigurer
	 * @return
	 */
	@Bean
	public MapperScannerConfigurer mapperScannerConfigurer(){
		MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
		mapperScannerConfigurer.setBasePackage("com.duteliang.spring.mybatis.mapper");
//		mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactoryBean");
		// 配置通用mappers
		Properties properties = new Properties();
//		properties.setProperty("mappers", "com.duteliang.spring.mybatis.mapper");
		properties.setProperty("notEmpty", "false");
		properties.setProperty("INENTITY", "MySql");
		mapperScannerConfigurer.setProperties(properties);
		return mapperScannerConfigurer;
	}
}
