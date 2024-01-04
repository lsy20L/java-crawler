import conf.DefaultConfigure;
import org.apache.commons.text.StringEscapeUtils;
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
    @Test
    public void test(){
        String res = "曾经有一对甜蜜的恋人——小伟和小美。\\n他们在大学相遇，彼此被对方的聪明才智所吸引。在课堂上，小伟经常和小美互动，分析问题，发表见解。小美也发现小伟很有个性，思想深刻。两人的相遇，令他们更加深入地了解彼此。\\n小伟和小美开始共同追求爱的幸福。他们一起慢慢地走过了大学时光，喜怒哀乐都分享在彼此间。他们一起看电影，做菜，逛公园。每当日落时分，小美和小伟在天空中看到的最深沉、最美好的颜色，总是伴随着彼此的眼神。\\n小伟温柔细致，时时关心小美的需求与情感。而小美则敏感且关注细节，她曾经为小伟编织了一条漂亮的围巾，这个只有两条围巾的恋人，从此更加常常牵起手。\\n在日常的相处中，小伟和小美早已默契地进入了彼此的心里。他们懂得彼此的需要与想要，懂得用深情厚爱去呵护对方。\\n终于，小伟决定向小美求婚，在一次浪漫的晚餐中，小伟温馨地将求婚戒指递给了小美。小美激动落泪，甜蜜接受了他的求婚。\\n如今，小伟和小美已经成为幸福的夫妻，他们一个人在外奔波，另一个人会在家里迎接他的归来，还会为对方做好一切准备，温暖地告诉对方，只要有彼此，一切的困难都不是问题。每当相互依偎，小伟和小美的内心都是满满的爱意。\n";
        System.out.println(StringEscapeUtils.escapeJava(res));
    }

}
