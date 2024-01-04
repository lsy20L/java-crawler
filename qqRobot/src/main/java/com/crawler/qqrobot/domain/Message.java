package com.crawler.qqrobot.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.text.StringEscapeUtils;

import java.io.Serializable;

/**
 * <h3>crawler</h3>
 * <p>gpt消息</p>
 * @author : hit-lsy
 * @date : 2023/4/11 16:13
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message implements Serializable {
    private String role;
    private String content;
    public String toJsonString(){
        return "{\n"+
                "\"role\": \""+role+"\",\n"+
                "\"content\": \""+ StringEscapeUtils.escapeJava(content)+"\"\n"+
                "}";
    }
    public String getMessage(){
        return (ChatRoleEnum.USER.getRole().equals(role)?"问：":"答：")+content;
    }
}
