package com.duteliang.bean;

import lombok.Data;
import lombok.ToString;

/**
 * 当前用户的基本信息
 * @Auther: zl
 * @Date: 2018-9-30 17:52
 */
@Data
@ToString
public class UserSubject {

	private String userName;

	private String host;

	private String ip;

}
