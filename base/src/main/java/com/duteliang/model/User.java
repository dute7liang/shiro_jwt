package com.duteliang.model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * @Auther: zl
 * @Date: 2018-10-18 11:53
 */
@Data
@ToString
public class User implements Serializable {

	@Id
	private String id;

	private String userName;

	private String password;

	/**
	 * 角色
	 */
	private String role;

	/**
	 * 资源
	 */
	private String permission;

	public User(){}

	public User(String userName) {
		this.userName = userName;
	}
}
