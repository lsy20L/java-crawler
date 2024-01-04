package com.crawler.qqrobot.service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.crawler.qqrobot.annotation.PreAuthority;
import com.crawler.qqrobot.domain.ChatRoleEnum;
import com.crawler.qqrobot.domain.Message;
import com.crawler.qqrobot.dto.ChatGptDto;
import com.crawler.qqrobot.mapper.QqUserMapper;
import com.crawler.qqrobot.mbg.mapper.TGptHistoryMapper;
import com.crawler.qqrobot.mbg.mapper.TQqUserMapper;
import com.crawler.qqrobot.mbg.model.TGptHistory;
import com.crawler.qqrobot.mbg.model.TGptHistoryExample;
import com.crawler.qqrobot.mbg.model.TQqUser;
import com.crawler.qqrobot.service.GptService;
import love.forte.simbot.ID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <h3>crawler</h3>
 * <p>Gpt功能实现类</p>
 * @author : hit-lsy
 * @date : 2023/4/9 19:39
 **/
@Service("gptService")
public class GptServiceImpl implements GptService {
    @Autowired
    RestTemplate restTemplate;
    @Value("${api.token}")
    String token;
    @Value("${user.agent}")
    String userAgent;
    @Resource
    QqUserMapper qqUserMapper;
    @Resource
    TQqUserMapper tQqUserMapper;
    @Resource
    TGptHistoryMapper historyMapper;
    static Map<ID, List<Message>> messages = new HashMap<>();
    @Override
    @PreAuthority
    public String chat(String content, ID id) throws IOException {
//        TQqUser tQqUser = qqUserMapper.selectByAccount(id.toString());
//        if(tQqUser==null){
//            return "你没有权限使用ChatGpt服务";
//        }
        String url = "https://v2.alapi.cn/api/chatgpt/stream";
        List<Message> message = new ArrayList<>();
        if(messages.containsKey(id)){
            message = messages.get(id);
            if(message.size()%2!=0){
                return "请耐心等待ChatGpt解答完你的上一个回答";
            }
        }else{
            messages.put(id,message);
        }
        message.add(new Message(ChatRoleEnum.USER.getRole(),content));
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String body ="";
        try{
            body=restTemplate.postForObject(url,new HttpEntity<>(new ChatGptDto(token, message).toJsonString(),headers),String.class);
        }catch (Exception e){
            System.out.println(e.getMessage());
            message.clear();
            message.add(new Message(ChatRoleEnum.USER.getRole(),content));
            body=restTemplate.postForObject(url,new HttpEntity<>(new ChatGptDto(token, message).toJsonString(),headers),String.class);
        }
        assert body != null;
        String[] split = body.split("\n");
        if(split.length<=1){
            message.clear();
            return chat(content,id);
        }
        JSONObject jsonObject = JSON.parseObject(split[split.length-2]);
        String answer = (String) jsonObject.get("text");
        message.add(new Message(ChatRoleEnum.ASSISTANT.getRole(),answer));
        TGptHistory tGptHistory = new TGptHistory();
        tGptHistory.setQuestion(content);
        tGptHistory.setAnswer(answer);
//        insertHistory(tQqUser,tGptHistory);
        return answer;
    }
    @Async
    public void insertHistory(TQqUser tQqUser,TGptHistory tGptHistory){
        tGptHistory.setUserId(tQqUser.getId());
        tGptHistory.setCreateDate(new Date());
        tQqUser.setLastActionTime(new Date());
        tQqUserMapper.updateByPrimaryKey(tQqUser);
        historyMapper.insert(tGptHistory);
    }
    @Override
    public String getHistory(ID id) {
        String res = "你的聊天记录为空";
        if(messages.containsKey(id)){
            List<Message> messages = GptServiceImpl.messages.get(id);
            res = messages.stream().map(Message::getMessage).collect(Collectors.joining("\n"));
        }
        return res;
    }

    @Override
    public String addUser(TQqUser user) {
        if(qqUserMapper.selectByAccount(user.getAccount())!=null){
            return "对不起，我已经名花有主了";
        }else{
            if(tQqUserMapper.insert(user)>0) {
                return "好き";
            }
        }
        return "我已经被玩坏了";
    }

}
