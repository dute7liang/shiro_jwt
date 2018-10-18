package com.duteliang.config;

import com.duteliang.bean.UserSubject;
import com.duteliang.model.User;
import com.duteliang.service.IUserService;
import com.duteliang.shiro.JWTToken;
import com.duteliang.shiro.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description:
 * @Auther: zl
 * @Date: 2018-9-28 17:03
 */
@Service
@Slf4j
public class MyRealm extends AuthorizingRealm {

	@Autowired
	private IUserService userService;


	/**
	 * 必须重写此方法，不然Shiro会报错
	 */
	@Override
	public boolean supports(AuthenticationToken token) {
		return token instanceof JWTToken;
	}

	/**
	 * 获取授权信息,获取用户的 资源信息
	 *
	 * @param principalCollection
	 * @return
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
		log.debug("————权限认证————");
		String username = JWTUtil.getUsername(principalCollection.toString());
		User userName = new User(username);
		User user = userService.getUser(userName);

		SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
		simpleAuthorizationInfo.addRole(user.getRole());
		Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
		simpleAuthorizationInfo.addStringPermissions(permission);
		return simpleAuthorizationInfo;
	}

	/**
	 * 获取身份验证信息
	 * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
	 *
	 * @param authenticationToken 用户身份信息 token
	 * @return 返回封装了用户信息的 AuthenticationInfo 实例
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
		log.debug("————身份认证————");

		String token = (String) authenticationToken.getCredentials();
		// 解密获得username，用于和数据库进行对比
		String username = JWTUtil.getUsername(token);
		if (username == null) {
			throw new AuthenticationException("token invalid");
		}

		User userBean = userService.getUser(new User(username));
		if (userBean == null) {
			throw new AuthenticationException("User didn't existed!");
		}

		if (!JWTUtil.verify(token, username, userBean.getPassword())) {
			throw new AuthenticationException("Username or password error");
		}

		return new SimpleAuthenticationInfo(token, token, getName());
	}
}
