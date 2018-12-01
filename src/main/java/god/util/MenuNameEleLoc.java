package god.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thomas on 2018/10/9.
 */
public class MenuNameEleLoc {
    public static WebElement getMenuEle(WebDriver driver,String menuname){
        WebElement element;
        element = driver.findElement(By.xpath("//a[@menuname='"+menuname+"']"));
        return element;
    }
}
