package utils;

import java.io.File;

/**
 * <h3>crawler</h3>
 * <p>文件相关工具类</p>
 * @author : hit-lsy
 * @date : 2023/4/5 21:55
 **/
public class FileUtil {
    public static File getProjectRoot(){
        return new File(System.getProperty("user.dir")).getParentFile();
    }

    public static File getProjectResource(String dir){
        return new File(getProjectRoot(),dir);
    }
}
