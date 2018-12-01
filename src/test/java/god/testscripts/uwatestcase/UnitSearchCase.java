package god.testscripts.uwatestcase;

import god.util.*;
import god.util.waitUse.ContextTab;
import org.apache.log4j.xml.DOMConfigurator;
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
public class UnitSearchCase {
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
    public void unitSearchCase(){
        Log.startTestCase("keywordSearch case");
        try {
            String keywords = "Vienna University";
            DriverWait.findElement(driver,driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']/li/input"))).sendKeys(keywords);
            ContextTab.pressEnter();
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/form/div[2]/div[3]/button")));
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/form/div[2]/div[3]/button"))).click();
            Thread.sleep(1000);
            WebElement element = DriverWait.findElement(driver, driver.findElement(By.xpath("//table[@id=\"__table__decisions-table\"]/tbody/tr[1]/td[2]/a")));
            String seachresult = element.getText();

            if(seachresult.contains(keywords)){
                Log.info("search success");
            }else {
                new GetScreenshot(driver,"search fail");
                Log.error("seach fail");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"search fail");
            Log.error("seach fail");
            Assert.fail();
            e.printStackTrace();
        }
        Log.endTestCase("keywordSearch case");
    }

    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
