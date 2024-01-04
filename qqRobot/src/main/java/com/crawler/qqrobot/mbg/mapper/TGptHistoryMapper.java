package com.crawler.qqrobot.mbg.mapper;


import com.crawler.qqrobot.mbg.model.TGptHistory;
import com.crawler.qqrobot.mbg.model.TGptHistoryExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TGptHistoryMapper {
    long countByExample(TGptHistoryExample example);

    int deleteByExample(TGptHistoryExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TGptHistory row);

    int insertSelective(TGptHistory row);

    List<TGptHistory> selectByExampleWithBLOBs(TGptHistoryExample example);

    List<TGptHistory> selectByExample(TGptHistoryExample example);

    TGptHistory selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TGptHistory row, @Param("example") TGptHistoryExample example);

    int updateByExampleWithBLOBs(@Param("row") TGptHistory row, @Param("example") TGptHistoryExample example);

    int updateByExample(@Param("row") TGptHistory row, @Param("example") TGptHistoryExample example);

    int updateByPrimaryKeySelective(TGptHistory row);

    int updateByPrimaryKeyWithBLOBs(TGptHistory row);

    int updateByPrimaryKey(TGptHistory row);
}