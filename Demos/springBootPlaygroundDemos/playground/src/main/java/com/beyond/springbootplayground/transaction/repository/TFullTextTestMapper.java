package com.beyond.springbootplayground.transaction.repository;

import com.beyond.springbootplayground.transaction.TFullTextTest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface TFullTextTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TFullTextTest record);

    int insertSelective(TFullTextTest record);

    TFullTextTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TFullTextTest record);

    int updateByPrimaryKey(TFullTextTest record);

    @Select("select * from t_full_text_test where id=#{id} for update ")
    TFullTextTest selectByPrimaryKeyForUpdate(Integer id);

}