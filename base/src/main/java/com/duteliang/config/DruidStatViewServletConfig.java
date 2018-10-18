package com.duteliang.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.duteliang.properties.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidStatViewServletConfig {
	@Autowired
	private DruidDataSourceProperties dataSourceProperties;

	@Bean
	public ServletRegistrationBean druidServlet() {
		ServletRegistrationBean reg = new ServletRegistrationBean();
		reg.setServlet(new StatViewServlet());
		reg.addUrlMappings(dataSourceProperties.getUrlMapping());
		// IP白名单
		reg.addInitParameter("allow", dataSourceProperties.getAllow());
		reg.addInitParameter("deny", dataSourceProperties.getDeny());
		reg.addInitParameter("loginUsername", dataSourceProperties.getLoginUsername());
		reg.addInitParameter("loginPassword", dataSourceProperties.getLoginPassword());
		return reg;
	}
}
