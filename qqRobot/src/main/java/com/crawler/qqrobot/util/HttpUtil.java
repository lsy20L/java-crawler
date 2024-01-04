package com.crawler.qqrobot.util;

import love.forte.simbot.event.EventListenerProcessingContext;
import love.forte.simbot.event.FriendMessageEvent;
import love.forte.simbot.event.GroupMessageEvent;
import love.forte.simbot.message.Messages;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;

/**
 * <h3>crawler</h3>
 * <p>http请求相关的工具类</p>
 * @author : hit-lsy
 * @date : 2023/4/9 17:39
 **/
public class HttpUtil {
    public static HashMap<String, String> convertCookie(String cookie) {
        String[] split = cookie.split(";");
        HashMap<String, String> stringStringHashMap = new HashMap<>();
        for (String s1 : split) {
            String[] split1 = s1.split("=");
            stringStringHashMap.put(split1[0],split1[1]);
        }
        return stringStringHashMap;
    }
    public static String getText(EventListenerProcessingContext context, FriendMessageEvent event) {
        String textContent = context.getTextContent();
        if (textContent != null) {
            return textContent;
        }

        return event.getMessageContent().getPlainText();
    }
    public static String getText(EventListenerProcessingContext context, GroupMessageEvent event) {
        String textContent = context.getTextContent();
        if (textContent != null) {
            return textContent;
        }

        return event.getMessageContent().getPlainText();
    }
    public static Messages convertStringToMessages (String message){
        return null;
    }
    public static String getApiBody(String url,String params) throws IOException {
        return "";
//        OkHttpClient client = new OkHttpClient();
//        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
//        RequestBody requestBody = RequestBody.create(mediaType, params);
//        Request request = new Request.Builder()
//                .url(url)
//                .post(requestBody)
//                .addHeader("Content-Type", "application/x-www-form-urlencoded")
//                .build();
//        Response response = client.newCall(request).execute();
//        return response.body().string();
    }
    public static  String doPost(String url, String map, String charset) {
        CloseableHttpClient httpClient = null;
        HttpPost httpPost = null;
        String result = null;
        try {
            httpClient = HttpClients.createDefault();
            httpPost = new HttpPost(url);
            httpPost.setHeader("Content-Type", "application/json;charset=UTF-8");
            StringEntity requestEntity = new StringEntity(map,charset);
            requestEntity.setContentType("application/json");
            httpPost.setEntity(requestEntity);
            CloseableHttpResponse httpResponse = httpClient.execute(httpPost);
            if (httpResponse != null) {
                HttpEntity resEntity = httpResponse.getEntity();
                if (resEntity != null) {
                    result = EntityUtils.toString(resEntity, charset);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return "gpt被你玩坏了，等待管理员进行修复";
        }
        return result;
    }
}
