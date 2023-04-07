package com.exercise.qqrobot.listener;

import love.forte.simboot.annotation.Listener;
import love.forte.simbot.event.FriendMessageEvent;
import org.springframework.stereotype.Component;

/**
 * <h3>crawler</h3>
 * <p>监听器测试</p>
 * @author : hit-lsy
 * @date : 2023/4/6 18:38
 **/
@Component
public class MyListener {
    @Listener
    public void onFriendMessage(FriendMessageEvent event){
        event.replyBlocking("hello world ");
        event.getFriend().sendAsync("simbot");
    }
}
