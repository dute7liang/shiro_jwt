package com.duteliang.controller;

import com.duteliang.bean.ResponseBean;
import com.duteliang.model.User;
import com.duteliang.service.IUserService;
import com.duteliang.shiro.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author: zl
 * @Date: 2018-10-18 16:18
 */
@RestController
@RequestMapping(value = "testController")
public class TestController {

	@PostMapping("/article")
	public ResponseBean article() {
		Subject subject = SecurityUtils.getSubject();
		if (subject.isAuthenticated()) {
			return new ResponseBean("You are already logged in");
		} else {
			return new ResponseBean("You are guest");
		}
	}

	@PostMapping("/require_auth")
	@RequiresAuthentication
	public ResponseBean requireAuth() {
		return new ResponseBean("You are authenticated");
	}

	@PostMapping("/require_role")
	@RequiresRoles("admin")
	public ResponseBean requireRole() {
		return new ResponseBean("You are visiting require_role");
	}

	@PostMapping("/require_permission")
	@RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
	public ResponseBean requirePermission() {
		return new ResponseBean("You are visiting permission require edit,view");
	}

}
