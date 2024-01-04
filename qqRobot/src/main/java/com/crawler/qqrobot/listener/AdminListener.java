package com.crawler.qqrobot.listener;

import com.crawler.qqrobot.service.AdminService;
import com.crawler.qqrobot.util.HttpUtil;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simboot.filter.MatchType;
import love.forte.simbot.ID;
import love.forte.simbot.bot.Bot;
import love.forte.simbot.event.EventListenerProcessingContext;
import love.forte.simbot.event.FriendAddRequestEvent;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import net.mamoe.mirai.data.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <h3>crawler</h3>
 * <p>管理员监听类</p>
 * @author : hit-lsy
 * @date : 2023/4/14 09:19
 **/
@Component
public class AdminListener {
    @Autowired
    AdminService adminService;
    @Listener
    public void addFriendsListener(EventListenerProcessingContext context, FriendAddRequestEvent event) throws IOException {
        adminService.addFriendRequest(event);
    }
    @Listener
    @Filter(value = "同意",matchType = MatchType.TEXT_STARTS_WITH)
    public void approve(EventListenerProcessingContext context, FriendMessageEvent event) throws IOException {
        String id = HttpUtil.getText(context, event).substring(2);
        event.replyBlocking(adminService.handleRequest(id,true));
    }
    @Listener
    @Filter(value = "不同意",matchType = MatchType.TEXT_STARTS_WITH)
    public void reject(EventListenerProcessingContext context, FriendMessageEvent event) throws IOException {
        String id = HttpUtil.getText(context, event).substring(3);
        event.replyBlocking(adminService.handleRequest(id,false));
    }

}
