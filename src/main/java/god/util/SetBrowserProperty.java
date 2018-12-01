package god.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

/**
 * Created by thomas on 2018/9/25.
 */
public class SetBrowserProperty {
    private static ChromeOptions chromeOptions;
    private static WebDriver driver;
    static {
        System.setProperty("webdriver.chrome.driver","C:\\Windows\\SysWOW64\\chromedriver.exe");//指定谷歌浏览器驱动程序位置
        //默认浏览器页面为最大化
        chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
    }
   public static WebDriver openChromeBrowser(){
       return new ChromeDriver(chromeOptions);
   }
}
