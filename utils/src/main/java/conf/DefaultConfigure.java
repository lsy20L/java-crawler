package conf;

import component.WebDriverFactory;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <h3>crawler</h3>
 * <p>默认配置类</p>
 * @author : hit-lsy
 * @date : 2023/4/5 21:59
 **/
@Configuration
public class DefaultConfigure {
    @Bean
    public FactoryBean<WebDriver> webDriverFactory(){
        return new WebDriverFactory();
    }
}
