package com.duteliang.mapper;

import com.duteliang.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Auther: zl
 * @Date: 2018-9-28 17:33
 */
@Mapper
@Repository
public interface UserMapper extends SupperMapper<User> {

}
