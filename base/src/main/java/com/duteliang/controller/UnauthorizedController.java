package com.duteliang.controller;

import com.duteliang.bean.ResponseBean;
import com.duteliang.model.User;
import com.duteliang.service.IUserService;
import com.duteliang.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zl 不需要权限即可访问的接口
 * @Date: 2018-10-18 17:51
 */
@RestController
@RequestMapping(value = "unauthorized")
public class UnauthorizedController {

	@Autowired
	private IUserService userService;

	@PostMapping(value = "login")
	public ResponseBean login(String userName, String password){
		User user = userService.getUserByUserName(userName);
		if (user.getPassword().equals(password)) {
			return new ResponseBean("Login success", JWTUtil.sign(userName, password));
		} else {
			throw new UnauthorizedException();
		}
	}

	@RequestMapping(path = "noAuth")
	public ResponseBean noAuth(){
		return new ResponseBean(HttpStatus.UNAUTHORIZED.value(),"no auth");
	}

	@PostMapping(value = "getSubject")
	public ResponseBean geiCurrentSubject(){


		return null;
	}

}
