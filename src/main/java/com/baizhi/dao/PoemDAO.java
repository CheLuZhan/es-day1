package com.baizhi.dao;

import com.baizhi.entity.Poem;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PoemDAO {

    @Select("select * from t_poem")
    public List<Poem> selectAll();
}
