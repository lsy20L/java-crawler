package com.crawler.qqrobot.service;

import com.crawler.qqrobot.mbg.model.TQqUser;
import love.forte.simbot.ID;

import java.io.IOException;

/**
 * <h3>crawler</h3>
 * <p>Gpt服务接口</p>
 * @author : hit-lsy
 * @date : 2023/4/9 19:38
 **/
public interface GptService {
    String chat(String content, ID id) throws IOException;
    String getHistory(ID id);
    String addUser(TQqUser user);
}
