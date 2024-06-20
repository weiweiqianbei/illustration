package com.illustration.dao;

import com.illustration.entity.Menu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MenuDao {
    List<Menu> getMenusByRoleId(@Param("roleIds") List roleIds);
}
