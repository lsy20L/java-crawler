package com.crawler.qqrobot.dto;


import com.crawler.qqrobot.domain.Message;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <h3>crawler</h3>
 * <p>gpt消息实体类</p>
 * @author : hit-lsy
 * @date : 2023/4/11 16:07
 **/

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatGptDto extends AbstractDto{
    private String token;
    private List<Message> message;
    @Override
    public String toJsonString(){
        return "{\n"+
                "\"token\": \""+token+"\",\n"+
                "\"message\": [\n"+
                message.stream().map(Message::toJsonString).collect(Collectors.joining(",\n"))+
                "]\n"+
                "}";

    }
}
