package god.testscripts.homepagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/9.
 * 从首页点击当日IM会话量大图标进入当日呼出电话统计页面
 */
public class ClickHomePageBigIco {
    public WebDriver driver;

//    @BeforeSuite
//    public void beforeSuite(){
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//    }
    @BeforeClass
    public void BeforeClass() throws Exception{
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        try {
            LoginAction.execute(driver, "1098@11196", "Aa_111111", Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver,"登录失败");
            Log.error("登录失败");
        }
    }
    @AfterClass
    public void afterClass(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
    }

    @BeforeMethod
     public void beforeMethod()throws Exception{
        Thread.sleep(1000);
        driver = driver.switchTo().defaultContent();
        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
        Thread.sleep(1000);
    }

    @Test(priority = 0)
    public void goToImDialogPage(){
        Log.startTestCase("点击当日IM会话量大图标用例");
        try {
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='left']/dl/dd[3]"))).click();//点击当日IM会话量大图标
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            if(!DriverWait.judgeExist(driver,"会话总量")) {
                new GetScreenshot(driver,"点击首页当日IM会话量大图标失败");
                Assert.fail("点击首页当日IM会话量大图标失败");
            }
        }catch (Exception e){
          Log.error("点击首页当日IM会话量大图标失败");
          Assert.fail("点击首页当日IM会话量大图标失败");
        }
        Log.info("点击首页当日IM会话量大图标成功");
        Log.endTestCase("点击首页当日IM会话量大图标用例");
    }

    @Test(priority = 1)
    public void goToWaitOrderPage(){
        Log.startTestCase("点击首页待处理工单大图标留言用例");
        try {
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='left']/dl/dd[5]"))).click();//点击首页待处理工单大图标
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            if(!DriverWait.findElement(driver,driver.findElement(By.className("ivu-page-options-elevator"))).isDisplayed()){
                new GetScreenshot(driver,"点击首页待处理工单大图标留言失败");
                Log.error("点击首页待处理工单大图标留言失败");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"点击首页待处理工单大图标留言失败");
            Log.error("点击首页待处理工单大图标留言失败");
            Assert.fail();
        }
        Log.info("点击首页待处理工单大图标留言成功");
        Log.endTestCase("点击首页待处理工单大图标留言用例");
    }

    @Test(priority = 2)
    public void goToWaitMesgPage(){
        Log.startTestCase("点击首页待处理留言大图标留言用例");
        try {
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='left']/dl/dd[4]"))).click();//点击首页待处理留言大图标
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            if(!DriverWait.judgeExist(driver,"留言类型")){
                new GetScreenshot(driver,"点击首页待处理留言大图标留言失败");
                Log.error("点击首页待处理留言大图标留言失败");
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"点击首页待处理留言大图标留言失败");
            Log.error("点击首页待处理留言大图标留言失败");
            Assert.fail();
        }
        Log.info("点击首页待处理留言大图标留言成功");
        Log.endTestCase("点击首页待处理留言大图标留言用例");
    }

    @Test(priority = 3)
    public void goToOutcallPage(){
        Log.startTestCase("点击当日呼出电话大图标用例");
        try {
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='left']/dl/dd[2]"))).click();//点击当日呼出电话
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            if(!DriverWait.findElement(driver,driver.findElement(By.xpath("//button[text()='今天']"))).isEnabled()){
                new GetScreenshot(driver,"点击进入呼出电话统计列表失败");
                Assert.fail("点击进入呼出电话统计列表失败");
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"点击首页当日呼出电话大图标失败");
            Log.error("点击首页当日呼出电话大图标失败");
            Assert.fail();
        }
        Log.info("点击当日呼出电话大图标成功");
        Log.endTestCase("点击当日呼出电话大图标用例");
    }

    @Test(priority = 4)
    public void goToIncallPage() throws Exception {
        Log.startTestCase("点击当日呼入电话大图标用例");
        try {
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class='left']/dl/dd[1]"))).click();//点击当日呼入电话
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            if(!DriverWait.findElement(driver,driver.findElement(By.xpath("//button[text()='今天']"))).isEnabled()){
                new GetScreenshot(driver,"点击进入呼入电话统计列表失败");
                Assert.fail("点击进入呼入电话统计列表失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"点击首页呼入电话大图标失败");
            Log.error("点击首页呼入电话大图标失败");
            Assert.fail();
        }
        Log.info("点击当日呼入电话大图标成功");
        Log.endTestCase("点击当日呼入电话大图标用例");
        Log.info("修改后提交到git上去");
    }
}
