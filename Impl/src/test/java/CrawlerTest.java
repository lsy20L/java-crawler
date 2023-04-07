import conf.DefaultConfigure;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

/**
 * <h3>crawler</h3>
 * <p>爬取网址的内容</p>
 * @author : hit-lsy
 * @date : 2023/4/5 22:11
 **/
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = DefaultConfigure.class)
public class CrawlerTest {
    @Autowired
    WebDriver driver;
    @Test
    public void dataCrawler(){
        driver.get("https://weibo.com/login.php");
        String[] label= {"点赞数：","评论数：","转发数："};
        int count = 0;
        try{
            Thread.sleep(3000);
            List<WebElement> elements;
            while((elements = driver.findElements(By.xpath("//ul[@node-type=\"feed_list\"]//following-sibling::div[@class = \"list_des\"]"))).size()<100){
                ((JavascriptExecutor) driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");
                Thread.sleep(1000);
            }
            for(WebElement element:elements){
                //爬取新闻的链接
                System.out.println(element.findElement(By.xpath("./descendant::a[1]")).getAttribute("href"));
                //爬取新闻的标题
                System.out.println(element.findElement(By.xpath("./descendant::h3[1]")).getText());
                //爬取新闻的数据
                System.out.print("出版社："+element.findElement(By.xpath("./descendant::h3[1]/following-sibling::div[1]/a/span[@class=\"subinfo S_txt2\"]")).getText()+" ");
                System.out.print("时间："+element.findElement(By.xpath("./descendant::h3[1]/following-sibling::div[1]/span[@class=\"subinfo S_txt2\"]")).getText()+" ");
                List<WebElement> dataElement = element.findElements(By.xpath("./descendant::h3[1]/following-sibling::div/span[@class=\"subinfo_rgt S_txt2\"]"));
                for(int i = 0;i<dataElement.size();++i){
                    System.out.print(label[i] + dataElement.get(i).getText()+" ");
                }
                System.out.println();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
