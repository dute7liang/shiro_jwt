package com.duteliang.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Auther: zl
 * @Date: 2018-10-18 11:39
 */
public class JWTToken implements AuthenticationToken {

	// 密钥
	private String token;

	public JWTToken(String token) {
		this.token = token;
	}

	@Override
	public Object getPrincipal() {
		return token;
	}

	@Override
	public Object getCredentials() {
		return token;
	}
}
