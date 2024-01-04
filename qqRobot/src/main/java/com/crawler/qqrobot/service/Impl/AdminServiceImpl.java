package com.crawler.qqrobot.service.Impl;

import com.crawler.qqrobot.service.AdminService;
import love.forte.simbot.ID;
import love.forte.simbot.definition.Contact;
import love.forte.simbot.event.FriendAddRequestEvent;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * <h3>crawler</h3>
 * <p></p>
 * @author : hit-lsy
 * @date : 2023/5/9 20:17
 **/
@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private Map<String,FriendAddRequestEvent> friendAddMap = new HashMap<>();
    private final ID adminID = ID.$(710775336);
    @Override
    public void addFriendRequest(FriendAddRequestEvent event) {
        if(!friendAddMap.containsKey(event.getFriend().getId().toString())){
            friendAddMap.put(event.getFriend().getId().toString(),event);
        }
        Contact admin = event.getBot().getContact(adminID);
        admin.sendBlocking("收到来自"+event.getFriend().getUsername()+"发来的好友请求，ID为"+event.getFriend().getId());

    }

    @Override
    public String handleRequest(String id, boolean approved) {
        friendAddMap.forEach((k,v)-> System.out.println(k+"|"+v));
        if(friendAddMap.containsKey(id)){
            if(approved){
                friendAddMap.get(id).acceptBlocking();
                friendAddMap.remove(id);
                return "已成功添加用户"+id+"为好友";
            }else{
                friendAddMap.get(id).rejectBlocking();
                friendAddMap.remove(id);
                return "已成功拒绝用户"+id+"的好友请求";
            }
        }
        return "未查询到用户"+id+"发起的好友请求";
    }
}
