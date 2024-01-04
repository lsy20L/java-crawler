package com.crawler.qqrobot.converter;

import com.crawler.qqrobot.dto.AbstractDto;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * <h3>crawler</h3>
 * <p>参数解析器</p>
 * @author : hit-lsy
 * @date : 2023/4/11 18:56
 **/
public class AbstractDtoConverter implements HttpMessageConverter<AbstractDto> {
    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(AbstractDto.class);
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return MediaType.parseMediaTypes("application/x-AbstractDto");
    }

    @Override
    public AbstractDto read(Class<? extends AbstractDto> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return null;
    }

    @Override
    public void write(AbstractDto abstractDto, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        String data = abstractDto.toJsonString();
        OutputStream body = outputMessage.getBody();
        body.write(data.getBytes());
    }
}
