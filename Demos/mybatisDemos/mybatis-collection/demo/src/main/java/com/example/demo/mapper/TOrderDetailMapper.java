package com.example.demo.mapper;

import com.example.demo.TOrderDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TOrderDetailMapper {
    int deleteByPrimaryKey(String id);

    int insert(TOrderDetail record);

    int insertSelective(TOrderDetail record);

    TOrderDetail selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(TOrderDetail record);

    int updateByPrimaryKey(TOrderDetail record);
}