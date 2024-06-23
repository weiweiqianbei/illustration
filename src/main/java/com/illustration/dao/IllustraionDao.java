package com.illustration.dao;

import com.illustration.entity.Illustraion;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface IllustraionDao {
    @Insert("INSERT INTO illustraion (user_id, title, path, remark) VALUES (#{userId}, #{title}, #{path}, #{remark})")
    void addIllustraion(Illustraion illustraion);
}
