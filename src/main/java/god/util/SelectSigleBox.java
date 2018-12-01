package god.util;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;


/**
 * Created by thomas on 2018/9/25.
 */
public class SelectSigleBox {
    public static void chooseOption(WebDriver driver,WebElement selectEle,int index){
        Select chooselist = new Select(selectEle);
        chooselist.selectByIndex(index); //选择某个选项
    }

    public static void chooseOption(WebDriver driver,WebElement selectEle,String optionName){
        Select chooselist = new Select(selectEle);
        chooselist.selectByVisibleText(optionName); //选择某个选项
    }

    public static void chooseOption(WebDriver driver,String value,WebElement selectEle){
        Select chooselist = new Select(selectEle);
        chooselist.selectByValue(value); //选择某个选项
    }

    public static void chooseOption(WebDriver driver,By selectby,int index){
        Select chooselist = new Select(driver.findElement(selectby));
        chooselist.selectByIndex(index); //选择某个选项
    }

    public static void chooseOption(WebDriver driver,By selectby,String optionName){
        Select chooselist = new Select(driver.findElement(selectby));
        chooselist.selectByVisibleText(optionName); //选择某个选项
    }

    public static void chooseOption(WebDriver driver,String value,By selectby){
        Select chooselist = new Select(driver.findElement(selectby));
        chooselist.selectByValue(value); //选择某个选项
    }
}
