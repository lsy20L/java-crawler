package com.crawler.qqrobot.mbg.mapper;


import com.crawler.qqrobot.mbg.model.TQqUser;
import com.crawler.qqrobot.mbg.model.TQqUserExample;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
@Mapper
public interface TQqUserMapper {
    long countByExample(TQqUserExample example);

    int deleteByExample(TQqUserExample example);

    int deleteByPrimaryKey(Long id);

    int insert(TQqUser row);

    int insertSelective(TQqUser row);

    List<TQqUser> selectByExample(TQqUserExample example);

    TQqUser selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("row") TQqUser row, @Param("example") TQqUserExample example);

    int updateByExample(@Param("row") TQqUser row, @Param("example") TQqUserExample example);

    int updateByPrimaryKeySelective(TQqUser row);

    int updateByPrimaryKey(TQqUser row);
}