import conf.DefaultConfigure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

/**
 * <h3>crawler</h3>
 * <p>爬取网址的内容</p>
 * @author : hit-lsy
 * @date : 2023/4/5 22:11
 **/
public class Crawler {
    @Autowired
    WebDriver driver;
    public void dataCrawler(){
        driver.get("https://weibo.com/login.php");
        try{
            Thread.sleep(1000);
            List<WebElement> elements = driver.findElements(By.xpath("//a[@render = \"ext\"]"));
            for(WebElement element:elements){
                System.out.println(element.getText());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        Crawler crawler = new Crawler();
        crawler.dataCrawler();
    }
}
