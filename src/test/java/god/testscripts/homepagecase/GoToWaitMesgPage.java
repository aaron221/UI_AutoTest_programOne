//package god.testscripts.homepagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/9.
// */
//public class GoToWaitMesgPage {
//    public WebDriver driver;
//
//    @BeforeClass
//    public void BeforeClass() throws Exception{
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        try {
//            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @Test
//    public void goToWaitMesgPage(){
//        Log.startTestCase("点击首页待处理留言大图标留言");
//        try {
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='left']/dl/dd[4]"))).click();//点击首页待处理留言大图标
//            Thread.sleep(1000);
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//            if(!DriverWait.findElement(driver,driver.findElement(By.className("page-last"))).isDisplayed()){
//                new GetScreenshot(driver,"点击首页待处理留言大图标留言失败");
//                Log.error("点击首页待处理留言大图标留言失败");
//            }
//        } catch (InterruptedException e) {
//            new GetScreenshot(driver,"点击首页待处理留言大图标留言失败");
//            Log.error("点击首页待处理留言大图标留言失败");
//        }
//        Log.endTestCase("点击首页待处理留言大图标留言失败");
//    }
//
//
//    @AfterMethod
//    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }
//}
