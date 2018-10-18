package com.duteliang.service;

import com.duteliang.model.User;

/**
 * @Auther: zl
 * @Date: 2018-10-18 11:49
 */
public interface IUserService {

	User getUser(User user);

	User getUserByUserName(String userName);

}
