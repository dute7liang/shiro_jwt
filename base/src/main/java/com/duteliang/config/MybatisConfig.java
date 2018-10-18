package com.duteliang.config;

import com.github.pagehelper.PageHelper;
import org.springframework.context.annotation.Bean;

import java.util.Properties;

/**
 * @Description:
 * @Auther: zl
 * @Date: 2018-9-12 11:08
 */
//@SpringBootConfiguration
public class MybatisConfig {

	@Bean
	public PageHelper pageHelper(){
		//分页插件
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		// 分页合理化
		properties.setProperty("reasonable", "true");
		//
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);

		//添加插件
//		new SqlSessionFactoryBean().setPlugins(new Interceptor[]{pageHelper});
		return pageHelper;
	}
}
