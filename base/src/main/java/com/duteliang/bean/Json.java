package com.duteliang.bean;

import lombok.Data;
import lombok.ToString;

/**
 * @Description:
 * @Auther: zl
 * @Date: 2018-8-14 17:43
 */
@Data
@ToString
public class Json {

	private boolean success;

	private String msg;

	private Object obj;

	public Json(){}

	public Json(String msg){
		this.msg = msg;
	}

	public Json(boolean success, String msg){
		this.success = success;
		this.msg = msg;
	}
}
