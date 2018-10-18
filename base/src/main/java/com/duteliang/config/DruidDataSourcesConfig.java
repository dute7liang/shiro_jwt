package com.duteliang.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.duteliang.properties.DruidDataSourceProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.SQLException;


/**
 * druid servlet
 */
@Slf4j
@Component
@SpringBootConfiguration
public class DruidDataSourcesConfig {
	@Autowired
	private DruidDataSourceProperties dataSourceProperties;
	
	@Primary // 默认数据源
	@Bean
	public DataSource dataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataSource.setUrl(dataSourceProperties.getUrl());
		dataSource.setUsername(dataSourceProperties.getUsername());
		dataSource.setPassword(dataSourceProperties.getPassword());
		dataSource.setDriverClassName(dataSourceProperties.getDriverClassName());
		// 配置最大连接
		dataSource.setMaxActive(dataSourceProperties.getMaxActive());
		// 配置初始连接
		dataSource.setInitialSize(dataSourceProperties.getInitialSize());
		// 配置最小连接
		dataSource.setMinIdle(dataSourceProperties.getMinIdle());
		// 连接等待超时时间
		dataSource.setMaxWait(dataSourceProperties.getMaxWait());
		// 间隔多久进行检测,关闭空闲连接
		dataSource.setTimeBetweenEvictionRunsMillis(dataSourceProperties.getTimeBetweenEvictionRunsMillis());
		// 一个连接最小生存时间
		dataSource.setMinEvictableIdleTimeMillis(dataSourceProperties.getMinEvictableIdleTimeMillis());
		// 用来检测是否有效的sql
		dataSource.setValidationQuery(dataSourceProperties.getValidationQuery());
		dataSource.setTestWhileIdle(dataSourceProperties.isTestWhileIdle());
		dataSource.setTestOnBorrow(dataSourceProperties.isTestOnBorrow());
		dataSource.setTestOnReturn(dataSourceProperties.isTestOnReturn());
		// 打开PSCache,并指定每个连接的PSCache大小
		dataSource.setPoolPreparedStatements(dataSourceProperties.isPoolPreparedStatements());
		dataSource.setMaxOpenPreparedStatements(dataSourceProperties.getMaxPoolPreparedStatementPerConnectionSize());
		// 配置sql监控的filter
		dataSource.setFilters(dataSourceProperties.getFilters());
		try {
			dataSource.init();
		} catch (SQLException e) {
			throw new RuntimeException("druid datasource init fail");
		}
		return dataSource;
	}
	

}
