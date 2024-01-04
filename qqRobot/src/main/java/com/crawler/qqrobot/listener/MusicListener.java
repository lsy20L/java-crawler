package com.crawler.qqrobot.listener;

import com.crawler.qqrobot.service.MusicService;
import com.crawler.qqrobot.util.HttpUtil;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simboot.filter.MatchType;
import love.forte.simbot.event.EventListenerProcessingContext;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * <h3>crawler</h3>
 * <p>音乐功能监听类</p>
 * @author : hit-lsy
 * @date : 2023/4/9 17:55
 **/
@Component
public class MusicListener {
    @Resource
    MusicService musicService;
    @Listener
    @Filter(value = ".点歌",matchType = MatchType.TEXT_STARTS_WITH)
    public void onFriendMessage(EventListenerProcessingContext context, GroupMessageEvent event) throws IOException {
        String keyword = HttpUtil.getText(context, event).substring(3);
        String music = musicService.findMusic(keyword);
        event.replyBlocking(music);
    }
}
