package com.illustration.dao;

import com.illustration.entity.Illustraion;
import com.illustration.entity.vo.IllustraionVO;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface IllustraionDao {
    @Insert("INSERT INTO illustraion (user_id, title, path, remark) VALUES (#{userId}, #{title}, #{path}, #{remark})")
    void addIllustraion(Illustraion illustraion);

    List<IllustraionVO> getIllustraions(@Param("page") int page, @Param("limit") int limit, @Param("findkey") String findkey, @Param("uid") Long uid);

    Long getIllustraionsCount(@Param("findkey") String findkey, @Param("uid") Long uid);

    @Delete("DELETE FROM illustraion WHERE id = #{id}")
    void deleteIllustraion(@Param("id") Long id);
}
