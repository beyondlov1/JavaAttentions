package com.example.demo.mapper;

import com.example.demo.TOrder;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TOrderMapper {
    int deleteByPrimaryKey(String id);

    int insert(TOrder record);

    int insertSelective(TOrder record);

    TOrder selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TOrder record);

    int updateByPrimaryKey(TOrder record);

    @Select(
            "<script>" +
                    "select * from T_ORDER where AMOUNT=#{amount} " +
                    "</script>"
    )
    List<TOrder> selectByAmount(@Param("amount") int amount);
}