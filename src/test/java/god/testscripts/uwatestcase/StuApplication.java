package god.testscripts.uwatestcase;

import god.util.DriverWait;
import god.util.GetScreenshot;
import god.util.Log;
import god.util.SetBrowserProperty;
import god.util.waitUse.ContextTab;
import jdk.nashorn.internal.ir.Flags;
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
public class StuApplication {
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
    public void stuApplication(){
        Log.startTestCase("stuApplication case");
        try {
            Boolean flag = true;
            String name = "aaron";
            String email = "32423234";
            String degree = "4";
            String major = "computer";
            String twomajor = "music";
            String univesityname = "baidu";
            String universityhome = "https://www.baidu.com";

            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"navbarCollapsable\"]/ul[1]/li[1]"))).click();
            DriverWait.findElement(driver,driver.findElement(By.name("name"))).sendKeys(name);

            DriverWait.findElement(driver,driver.findElement(By.name("email"))).sendKeys(email);

            DriverWait.findElement(driver,driver.findElement(By.name("degree"))).sendKeys(degree);

            DriverWait.findElement(driver,driver.findElement(By.name("major"))).sendKeys(major);

            DriverWait.findElement(driver,driver.findElement(By.name("major2nd"))).sendKeys(twomajor);

            DriverWait.findElement(driver,driver.findElement(By.name("universityName"))).sendKeys(univesityname);

            DriverWait.findElement(driver,driver.findElement(By.name("universityHomepage"))).sendKeys(universityhome);

            if(driver.getPageSource().contains("Please enter a valid UWA email")){
                Log.error("email fail");
                flag = false;
            }else {
                Log.info("email pass");
            }
            if(driver.getPageSource().contains("This field is required")){
                Log.error("field fail");
                flag = false;

            }else {
                Log.info("field pass");
            }

            if(flag){
                Log.info("stuApplication pass");
            }else {
                Log.error("stuApplication fail");
                Assert.fail();
            }
        } catch (Exception e) {
            Log.error("there has a erro message in excute code");
        }
        Log.endTestCase("stuApplication case");
    }


    @AfterMethod
    public void afterMethod(){
        driver.quit();
    }
}
