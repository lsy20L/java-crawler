package com.crawler.qqrobot.intercepter;

import com.crawler.qqrobot.annotation.PreAuthority;
import com.crawler.qqrobot.mapper.QqUserMapper;
import com.crawler.qqrobot.mbg.model.TQqUser;
import love.forte.simbot.ID;

import love.forte.simbot.event.GroupMessageEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * <h3>crawler</h3>
 * <p>preAuthority注解的实现类</p>
 * @author : hit-lsy
 * @date : 2023/4/13 21:46
 **/
public class PreAuthorityIntercepter implements MethodInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(PreAuthorityIntercepter.class);
    @Resource
    QqUserMapper qqUserMapper;
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        PreAuthority annotation = method.getAnnotation(PreAuthority.class);
        if(annotation!=null){
            ID id = null;
            for (Object object : objects) {
                if(object instanceof GroupMessageEvent){
                    id = (ID) object;
                    break;
                }
            }
            if(id!=null){
                TQqUser tQqUser = qqUserMapper.selectByAccount(id.toString());
                if(tQqUser!=null){
                    return methodProxy.invoke(o,objects);
                }
            }else{
                LOGGER.error("Exception caught while processing " + method.getName() + ": " + "Require a parameter typed GroupMessageEvent");
                return "你没有权限使用ChatGpt服务";
            }
        }
        return methodProxy.invoke(o,objects);
    }
}
