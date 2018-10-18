package com.duteliang.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "spring.datasource.druid")
@Component
@Data
public class DruidDataSourceProperties {
	// 驱动配置信息信息
	private String url;
	private String username;
	private String password;
	private String driverClassName;

	// 连接池配置信息
	// 初始化大小
	private int initialSize;
	private int minIdle;
	private int maxActive;
	// 配置获取连接等待超时的时间
	private int maxWait;
	// 配置间隔多久才进行一次检测，检测需要关闭的空闲连接，单位是毫秒
	private int timeBetweenEvictionRunsMillis;
	// 配置一个连接在池中最小生存的时间，单位是毫秒
	private int minEvictableIdleTimeMillis;

	private String validationQuery;
	private boolean testWhileIdle;
	private boolean testOnBorrow;
	private boolean testOnReturn;
	private boolean poolPreparedStatements;
	private int maxPoolPreparedStatementPerConnectionSize;
	private String filters;
	private String connectionProperties;

	// 过滤器和监听器参数
	private String urlMapping;

	// IP白名单(没有配置或者为空，则允许所有访问)
	private String deny;

	// IP黑名单 (存在共同时，deny优先于allow)
	private String allow;

	// 用户名
	private String loginUsername;

	// 密码
	private String loginPassword;

	// 禁用HTML页面上的“Reset All”功能
	private boolean resetEnable;

	// 忽略资源 监控
	private String exclusions;
}
