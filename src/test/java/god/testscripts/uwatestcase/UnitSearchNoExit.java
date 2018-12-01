package god.testscripts.uwatestcase;

import god.util.DriverWait;
import god.util.GetScreenshot;
import god.util.Log;
import god.util.SetBrowserProperty;
import god.util.waitUse.ContextTab;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/22
 */
public class UnitSearchNoExit {
    public WebDriver driver;

    @BeforeClass
    public void BeforeClass() throws Exception{

    }

    @BeforeMethod
    public void beforeMethod(){
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://exchange-approval-production.herokuapp.com");//open url
    }

    @Test
    public void unitSearchNoExit(){
        Log.startTestCase("NoExitKeywordsSearch case");
        try {
            String keywords = "1234556";
            DriverWait.findElement(driver,driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']/li/input"))).sendKeys(keywords);
            Thread.sleep(1000);
            if(driver.getPageSource().contains("No results found")){
                Log.info("NoExitKeywordsSearch success");
            }else {
                new GetScreenshot(driver,"NoExitKeywordsSearch fail");
                Log.error("NoExitKeywordsSearch fail");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"NoExitKeywordsSearch fail");
            Log.error("NoExitKeywordsSearch fail");
            Assert.fail();
        }
        Log.endTestCase("NoExitKeywordsSearch case");

    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
