package god.testscripts.uwatestcase;

import god.util.*;
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
public class AddDelSearch {
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
    public void addDelSearchResult(){
        String keywords = null;
        Log.startTestCase("addSearchResult case");
        try {
            keywords = "Vienna University";
            DriverWait.findElement(driver,driver.findElement(By.xpath("//ul[@class='select2-selection__rendered']/li/input"))).sendKeys(keywords);
            ContextTab.pressEnter();
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(driver, driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/form/div[2]/div[3]/button")));
            DriverWait.findElement(driver, driver.findElement(By.xpath("/html/body/div/div[1]/div/div[2]/div[2]/form/div[2]/div[3]/button"))).click();
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("//table[@id=\"__table__decisions-table\"]/tbody/tr[1]/td[6]/button")));
            DriverWait.findElement(driver, driver.findElement(By.xpath("//table[@id=\"__table__decisions-table\"]/tbody/tr[1]/td[6]/button"))).click();
            Thread.sleep(1000);
            WebElement element = DriverWait.findElement(driver,driver.findElement(By.xpath("//table[@id=\"__table__datatable-1\"]/tbody/tr[3]/td[1]/a")));
            String addresult = element.getText();
            if(addresult.contains(keywords)){
                Log.info("addSearchResult success");
            }else {
                new GetScreenshot(driver,"addSearchResult fail");
                Log.error("addSearchResult fail");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"addSearchResult fail");
            Log.error("addSearchResult fail");
            Assert.fail();
        }
        Log.endTestCase("addSearchResult case");

        Log.startTestCase("delSearchResult case");
        try {
            DriverWait.findElement(driver,driver.findElement(By.xpath("//table[@id=\"__table__datatable-1\"]/tbody/tr[3]/td[4]/button"))).click();
            Thread.sleep(1000);
            WebElement element = DriverWait.findElement(driver,driver.findElement(By.xpath("//table[@id=\"__table__datatable-1\"]/tbody/tr[2]/td[1]/a")));
            String addresult = element.getText();
            if(!addresult.contains(keywords)){
                Log.info("delSearchResult success");
            }else {
                new GetScreenshot(driver,"delSearchResult fail");
                Log.error("delSearchResult fail");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"delSearchResult fail");
            Log.error("delSearchResult fail");
            Assert.fail();
        }
        Log.endTestCase("delSearchResult case");

    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
