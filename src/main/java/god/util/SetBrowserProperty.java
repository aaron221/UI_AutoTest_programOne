package god.util;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


/**
 * Created by thomas on 2018/9/25.
 */
public class SetBrowserProperty {
    static {
        System.setProperty("webdriver.gecko.driver","C:\\Program Files (x86)\\Mozilla Firefox\\geckodriver.exe");

    }
   public static WebDriver openFoxBrowser(){
       return new FirefoxDriver();
   }
}
