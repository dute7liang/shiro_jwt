package com.duteliang.config;

import com.alibaba.druid.support.http.WebStatFilter;
import com.duteliang.properties.DruidDataSourceProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;

/**
 * druid 过滤器
 */
@SpringBootConfiguration
public class DruidWebStatFilterConfig {
	@Autowired
    private DruidDataSourceProperties dataSourceProperties;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.addInitParameter("exclusions", dataSourceProperties.getExclusions());
        return filterRegistrationBean;
    }
}
