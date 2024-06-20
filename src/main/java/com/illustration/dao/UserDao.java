package com.illustration.dao;

import com.illustration.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface UserDao {
    User selectUserByAccount(@Param("account") String account, @Param("type") int type);
}
