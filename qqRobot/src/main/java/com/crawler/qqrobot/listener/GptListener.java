package com.crawler.qqrobot.listener;

import com.crawler.qqrobot.mbg.model.TQqUser;
import com.crawler.qqrobot.service.GptService;
import com.crawler.qqrobot.util.HttpUtil;
import love.forte.simboot.annotation.Filter;
import love.forte.simboot.annotation.Listener;
import love.forte.simboot.filter.MatchType;
import love.forte.simbot.ID;
import love.forte.simbot.event.EventListenerProcessingContext;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * <h3>crawler</h3>
 * <p>Gtp功能监听器</p>
 * @author : hit-lsy
 * @date : 2023/4/9 19:41
 **/
@Component
public class GptListener {
    @Resource
    GptService gptService;
    @Listener
    @Filter(value = ".gpt",matchType = MatchType.TEXT_STARTS_WITH)
    public void chatListener(EventListenerProcessingContext context, GroupMessageEvent event) throws IOException {
        String keyword = HttpUtil.getText(context, event).substring(4);
        ID id = event.getAuthor().getId();
        String answer = gptService.chat(keyword,id);
        event.replyBlocking(answer);
    }
    @Listener
    @Filter(value = ".查看gpt聊天记录",matchType = MatchType.TEXT_EQUALS)
    public void historyListener(EventListenerProcessingContext context, GroupMessageEvent event) throws IOException {
        ID id = event.getAuthor().getId();
        String answer = gptService.getHistory(id);
        event.replyBlocking(answer);
    }
    @Listener
    @Filter(value = "我爱你",matchType = MatchType.TEXT_EQUALS)
    public void addUserListener(EventListenerProcessingContext context,GroupMessageEvent event){
        ID id = event.getAuthor().getId();
        TQqUser user = new TQqUser();
        user.setAccount(id.toString());
        user.setCreatTime(new Date());
        user.setLastActionTime(new Date());
        String answer = gptService.addUser(user);
        event.replyBlocking(answer);
    }
    @Listener
    @Filter(value = ".gpt",matchType = MatchType.TEXT_STARTS_WITH)
    public void chatListener(EventListenerProcessingContext context, FriendMessageEvent event) throws IOException {
        String keyword = HttpUtil.getText(context, event).substring(4);
        ID id = event.getSource().getId();
        String answer = gptService.chat(keyword,id);
        event.replyBlocking(answer);
    }
    @Listener
    @Filter(value = ".查看gpt聊天记录",matchType = MatchType.TEXT_EQUALS)
    public void historyListener(EventListenerProcessingContext context, FriendMessageEvent event) throws IOException {
        ID id = event.getSource().getId();
        String answer = gptService.getHistory(id);
        event.replyBlocking(answer);
    }
    @Listener
    @Filter(value = "我爱你",matchType = MatchType.TEXT_EQUALS)
    public void addUserListener(EventListenerProcessingContext context,FriendMessageEvent event){
        ID id = event.getSource().getId();
        TQqUser user = new TQqUser();
        user.setAccount(id.toString());
        user.setCreatTime(new Date());
        user.setLastActionTime(new Date());
        String answer = gptService.addUser(user);
        event.replyBlocking(answer);
    }
}
