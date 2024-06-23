package com.illustration.dao;

import com.illustration.entity.Illustraion;
import com.illustration.entity.vo.IllustraionVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface IllustraionDao {
    @Insert("INSERT INTO illustraion (user_id, title, path, remark) VALUES (#{userId}, #{title}, #{path}, #{remark})")
    void addIllustraion(Illustraion illustraion);

    List<IllustraionVO> getIllustraions(@Param("page") int page, @Param("limit") int limit);

    @Select("SELECT COUNT(*) FROM illustraion")
    Long getIllustraionsCount();
}
