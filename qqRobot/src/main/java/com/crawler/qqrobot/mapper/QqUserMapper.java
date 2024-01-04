package com.crawler.qqrobot.mapper;


import com.crawler.qqrobot.mbg.model.TQqUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * <h3>crawler</h3>
 * <p></p>
 * @author : hit-lsy
 * @date : 2023/4/13 22:14
 **/
@Mapper
public interface QqUserMapper {
    TQqUser selectByAccount(String account);
}
