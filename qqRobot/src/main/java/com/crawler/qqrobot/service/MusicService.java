package com.crawler.qqrobot.service;

import java.io.IOException;

/**
 * <h3>crawler</h3>
 * <p>音乐服务接口</p>
 * @author : hit-lsy
 * @date : 2023/4/9 17:12
 **/
public interface MusicService {
    String findMusic(String songName) throws IOException;
}
