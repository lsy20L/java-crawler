package com.crawler.qqrobot.service.Impl;

import com.crawler.qqrobot.service.MusicService;
import com.crawler.qqrobot.util.HttpUtil;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Value;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;

/**
 * <h3>crawler</h3>
 * <p>音乐服务实现类</p>
 * @author : hit-lsy
 * @date : 2023/4/9 17:12
 **/
@Service("musicService")
public class MusicServiceImpl implements MusicService {
    @Value("${api.token}")
    String token;
    @Value("${music.cookie}")
    String musicCookie;
    @Value("${user.agent}")
    String userAgent;
    @Override
    public String findMusic(String keyword) throws IOException {
        String url = "https://v2.alapi.cn/api/music/search";
        String params = "keyword="+keyword+"&token="+token;
        String body = HttpUtil.getApiBody(url,params);
        JSONObject jsonObject = JSON.parseObject(body);
        JSONObject data = (JSONObject)jsonObject.get("data");
        JSONArray songs = (JSONArray) data.get("songs");
        JSONObject song = (JSONObject)songs.get(0);
        String id = song.get("id").toString();
        String songName = song.get("name").toString();
        StringBuilder artistsName = new StringBuilder();
        JSONArray artists = (JSONArray) song.get("artists");
        for (Object artist : artists) {
            JSONObject jsonObject1 = (JSONObject) artist;
            artistsName.append(jsonObject1.get("name").toString()).append(" ");
        }

        String musicUrl ="https://music.163.com/song?id="+id;
        HashMap<String, String> cookie = HttpUtil.convertCookie(musicCookie);
        Document document1 = Jsoup.connect(musicUrl).ignoreContentType(true).userAgent(userAgent).referrer("https://music.163.com/").cookies(cookie).get();
        Elements element = document1.select("img.j-img");
        String src = element.attr("src");
        String resultInfo ="[CAT:music," +
                "kind=neteaseCloud," +
                "musicUrl=http://music.163.com/song/media/outer/url?id="+id+","+
                "title="+songName+"," +
                "jumpUrl=https://music.163.com/#/song?id="+id+"," +
                "pictureUrl="+src+"," +
                "summary="+artistsName+"]";
        return resultInfo;
    }
}
