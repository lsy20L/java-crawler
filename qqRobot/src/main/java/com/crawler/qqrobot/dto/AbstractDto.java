package com.crawler.qqrobot.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * <h3>crawler</h3>
 * <p>抽象实体类</p>
 * @author : hit-lsy
 * @date : 2023/4/11 16:15
 **/
public abstract class AbstractDto implements Serializable {

    public abstract String toJsonString();
}
