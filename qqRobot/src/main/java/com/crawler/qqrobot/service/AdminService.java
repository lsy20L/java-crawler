package com.crawler.qqrobot.service;

import love.forte.simbot.ID;
import love.forte.simbot.definition.UserInfo;
import love.forte.simbot.event.FriendAddRequestEvent;

/**
 * <h3>crawler</h3>
 * <p>用户相关操作</p>
 * @author : hit-lsy
 * @date : 2023/5/9 20:14
 **/
public interface AdminService {
    void addFriendRequest(FriendAddRequestEvent event);
    String handleRequest(String id,boolean approved);
}
