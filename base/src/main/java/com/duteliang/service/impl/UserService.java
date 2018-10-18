package com.duteliang.service.impl;

import com.duteliang.mapper.UserMapper;
import com.duteliang.model.User;
import com.duteliang.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

/**
 * @Auther: zl
 * @Date: 2018-10-18 11:55
 */
@Transactional
@Service
public class UserService implements IUserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public User getUser(User user) {
		return userMapper.selectOne(user);
	}

	@Override
	public User getUserByUserName(String userName) {
		Example example = new Example(User.class);
		example.createCriteria().andEqualTo("userName", userName);
		return userMapper.selectOneByExample(example);
	}
}
