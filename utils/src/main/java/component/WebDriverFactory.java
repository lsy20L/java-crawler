package component;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.config.AbstractFactoryBean;
import utils.FileUtil;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.HashMap;
import java.util.Map;

/**
 * <h3>crawler</h3>
 * <p>WebDriver工厂</p>
 * @author : hit-lsy
 * @date : 2023/4/5 21:43
 **/
public class WebDriverFactory extends AbstractFactoryBean<WebDriver> {
    @Override
    public Class<?> getObjectType() {
        return WebDriver.class;
    }
    @Override
    protected WebDriver createInstance() throws Exception {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        File tempPath = FileUtil.getProjectResource("temp");
        System.out.println("tempPath = " + tempPath);
        options.addArguments("--user-data-dir="+tempPath.getAbsolutePath());
        File downloadPath = FileUtil.getProjectResource("download");
        Map<String,Object> prefs = new HashMap<>();
        prefs.put("profile.default_content_settings.popups",0);
        prefs.put("download.default_directory",downloadPath.getAbsolutePath());
        options.setExperimentalOption("prefs",prefs);
        return new ChromeDriver(options);
    }
//    static class CloseableChromeDriver extends ChromeDriver {
//        WeakReference<Thread> shutdownHookReference;
//        static WeakReference<CloseableChromeDriver> instance;
//
//        protected CloseableChromeDriver(ChromeOptions options) {
//            super(options);
//            Thread thread = new Thread("ChromeDriverDestory"){
//                @Override
//                public void run() {
//                    CloseableChromeDriver.this.quit();
//                }
//            };
//            instance = new WeakReference<>(this);
//            shutdownHookReference = new WeakReference<>(thread);
//            Runtime.getRuntime().addShutdownHook(thread);
//        }
//
//        public static CloseableChromeDriver getInstance(ChromeOptions options) {
//            if (instance!=null && instance.get()!=null){
//                return instance.get();
//            }
//            return new CloseableChromeDriver(options);
//        }
//    }
}
