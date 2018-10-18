package com.duteliang.config;

import com.duteliang.filter.JWTFilter;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.mgt.DefaultSessionStorageEvaluator;
import org.apache.shiro.mgt.DefaultSubjectDAO;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro 配置类 <br/>
 *  官方推荐配置:<a>http://shiro.apache.org/spring.html</a>
 * @Auther: zl
 * @Date: 2018-9-28 16:58
 */
@Configuration
@Slf4j
public class ShiroConfig {

	/**
	 * 注入自定义 realm
	 */
	@Bean
	public MyRealm myRealm(){
		return new MyRealm();
	}

	/**
	 * 注入 SecurityManager
	 */
	@Bean
	public SecurityManager securityManager(MyRealm myRealm){
		DefaultSecurityManager securityManager = new DefaultWebSecurityManager();
		securityManager.setRealm(myRealm);
		/*
		 * 关闭shiro自带的session，详情见文档
		 * http://shiro.apache.org/session-management.html#SessionManagement-StatelessApplications%28Sessionless%29
		 */
		DefaultSubjectDAO subjectDAO = new DefaultSubjectDAO();
		DefaultSessionStorageEvaluator defaultSessionStorageEvaluator = new DefaultSessionStorageEvaluator();
		defaultSessionStorageEvaluator.setSessionStorageEnabled(false);
		subjectDAO.setSessionStorageEvaluator(defaultSessionStorageEvaluator);
		securityManager.setSubjectDAO(subjectDAO);
		return securityManager;
	}

	/**
	 * 配置拦截器
	 */
	@Bean
	public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager){
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);

		// 添加自己的过滤器并且取名为jwt
		Map<String, Filter> filterMap = new HashMap<>();
		filterMap.put("jwt", new JWTFilter());
		shiroFilterFactoryBean.setFilters(filterMap);

		/*// 设置登录 url(没有登陆就跳转到这个链接)
		shiroFilterFactoryBean.setLoginUrl("/login_page");
		// 设置登录成功url
		shiroFilterFactoryBean.setSuccessUrl("/");*/
		// 设置无权限跳转url
		shiroFilterFactoryBean.setUnauthorizedUrl("/unauthorized/noAuth");

		/**
		 * DefaultFilter 默认过滤器
		 */
		// 设置拦截器 这里接口的拦截有顺序要求，对url按顺序进行匹配进入对应的过滤器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

		//开放不需要登陆的接口
		filterChainDefinitionMap.put("/unauthorized/**", "anon");
		/*
		// 前后端分离不需要处理静态资源
		filterChainDefinitionMap.put("/static/**", "anon");*/

		//其余接口一律拦截
		filterChainDefinitionMap.put("/**", "jwt");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		log.debug("Shiro拦截器工厂类注入成功");
		return shiroFilterFactoryBean;
	}


	/**
	 * 下面的代码是添加注解支持
	 */
	@Bean
	@DependsOn("lifecycleBeanPostProcessor")
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		// 强制使用cglib，防止重复代理和可能引起代理出错的问题
		// https://zhuanlan.zhihu.com/p/29161098
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
		advisor.setSecurityManager(securityManager);
		return advisor;
	}

}