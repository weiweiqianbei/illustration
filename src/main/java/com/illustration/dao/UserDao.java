package com.illustration.dao;

import com.illustration.entity.User;
import org.apache.ibatis.annotations.*;

@Mapper
public interface UserDao {
    User selectUserByAccount(@Param("account") String account, @Param("type") int type);

    @Insert("INSERT INTO user (user_id, email, password, user_name) VALUES (#{userId}, #{email}, #{password}, #{userName})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    boolean insertUser(User user);

    @Insert("INSERT INTO user_role (user_id, role_id) VALUES (#{id}, 100)")
    boolean insertUserRole(User user);

    @Update("UPDATE user SET user_id = #{userId}, email = #{email}, user_name = #{userName}, head_portrait = #{headPortrait}, update_time = CURRENT_TIMESTAMP WHERE id = #{id}")
    void updateUser(User user);

    @Select("SELECT user_name, head_portrait FROM user WHERE id=#{id}")
    User getUserById(@Param("id") long id);
}
