package god.util.waitUse;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class XpathTest {

    public static WebDriver openPage(String url){
        WebDriver driver = new ChromeDriver();
        driver.get(url);
        return driver;
    }
    public static void absolutePath(WebDriver driver){
        WebElement inputKey = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/span/input[@class='s_ipt']"));
        inputKey.sendKeys("aaron");
        WebElement baiduSearch = driver.findElement(By.xpath("/html/body/div/div/div/div/div/form/span/input[@value='百度一下']"));
        baiduSearch.clear();
    }

    public static void relativePath(WebDriver driver){
        WebElement inputKey = driver.findElement(By.xpath("//input[@class='s_ipt']"));
        inputKey.sendKeys("aaron");
        WebElement baiduSearch = driver.findElement(By.xpath("//input[@value='百度一下']"));
        baiduSearch.click();
    }

    public static WebElement xpanthLocation(WebDriver driver,String xpathRoute){
        WebElement inputKey = driver.findElement(By.xpath(xpathRoute));
        return inputKey;
    }

    public static void inputKey(WebDriver driver,String xpathRoute,String inputKey){
        xpanthLocation(driver,xpathRoute).sendKeys(inputKey);
    }

    public static void clickSearch(WebDriver driver,String xpathRoute){
        xpanthLocation(driver,xpathRoute).click();
    }

    public static void searchCase(String url,String inpuntRoute,String searchRoute,String inputKey){
        WebDriver driver=openPage(url);
        inputKey(driver,inpuntRoute,inputKey);
        clickSearch(driver,searchRoute);
    }


    public static void main(String[] args) {
        ////xpath绝对路径
        //searchCase("https://www.baidu.com","/html/body/div/div/div/div/div/form/span/input[@class='s_ipt']"
        //,"/html/body/div/div/div/div/div/form/span/input[@value='百度一下']","aaron");
        //
        ////xpath相对路径
        //searchCase("https://www.baidu.com","//input[@class='s_ipt']"
        //        ,"//input[@value='百度一下']","aaron");

        //xpath使用索引号来定位
        //searchCase("https://www.baidu.com","//form[@id='form']/span[1]/input[1]"
        //        ,"//form[@id='form']/span[2]/input[1]","aaron");

        //使用页面元素的属性值定位元素
        //WebDriver driver=openPage("https://www.baidu.com");
        //WebElement imageclink = driver.findElement(By.xpath("//*[@id='lg']/map/area"));
        //imageclink.click();

        //使用模糊的属性值定位元素
        //searchCase("https://www.baidu.com","//input[starts-with(@id,'k')]"
        //        ,"//input[contains(@class,'btn')]","aaron");

        //使用xpath的轴(Axis)进行元素定位,
        // parent（上一级）,child（下一级）,ancestor(所有上级),descendant(所有下级),
        // following(当前节点之后所有的节点),preceding(当前节点前面的所有节点)
        //preceding-sibling(当前节点前面所有同级节点),following-sibling(当前节点后面所有平级节点)
        //searchCase("https://www.baidu.com","//a[@id='quickdelete']/preceding-sibling::input[1]"
        //        ,"//span[@class='bg s_btn_wr']/child::input[1]","aaron");

        //使用页面元素的文本来定位元素
        //searchCase("https://www.baidu.com","//input[@id='kw']"
        //        ,"//input[text()='百度一下']","aaron");


    }
}
