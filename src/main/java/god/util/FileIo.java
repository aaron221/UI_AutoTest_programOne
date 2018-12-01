package god.util;

import org.openqa.selenium.WebElement;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by thomas on 2018/10/16.
 */
public class FileIo {
    public static void loadFile(WebElement element,String filepath) throws Exception {
        String src = element.getAttribute("src");
        URL url = new URL(src);
        InputStream inputStream = url.openStream();
        int len = 0;
        byte[] bytes = new byte[512];
        FileOutputStream fileOutputStream = new FileOutputStream(new File(filepath));
        while((len=inputStream.read(bytes))>0){
           fileOutputStream.write(bytes,0,len);
        }
        inputStream.close();
        fileOutputStream.close();
    }

    public static void delFile(String filepath){
        File file = new File(filepath);
        file.delete();
    }
}
