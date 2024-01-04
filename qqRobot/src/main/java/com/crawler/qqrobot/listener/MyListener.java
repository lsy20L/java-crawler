package com.crawler.qqrobot.listener;

import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simbot.ID;
import love.forte.simbot.event.EventListenerProcessingContext;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * <h3>crawler</h3>
 * <p>监听器测试</p>
 * @author : hit-lsy
 * @date : 2023/4/6 18:38
 **/
@Component
public class MyListener {
    @Listener
    @Filter("你好")
    public void onFriendMessage(FriendMessageEvent event){
        event.replyBlocking("hello world ");
        event.getFriend().sendAsync("simbot");
    }
    @Listener
    @Filter(".你好")
    public void onFriendMessage(EventListenerProcessingContext context, GroupMessageEvent event){
        event.replyBlocking("hello "+event.getSource().getOwner().getNickname());
    }
    @Listener
    @Filter(".你在哪")
    public void whereMessage(FriendMessageEvent event) {
        try(DatagramSocket socket = new DatagramSocket()){
            socket.connect(InetAddress.getByName("8.8.8.8"),10002);
            event.replyBlocking(socket.getLocalAddress().toString());
        } catch (SocketException | UnknownHostException e) {
            event.replyBlocking("我也不知道");
        }
    }
}
