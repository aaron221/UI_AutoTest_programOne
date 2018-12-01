//package god.testscripts.registercase;
//
//import god.appmodules.LogoutAction;
//import god.appmodules.RegisterUserAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/8.
// */
//public class RegisterUser {
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
//    }
//
//    @Test
//    public void testRegisterUser() throws Exception {
//        driver.get(Constant.URL);
//        Log.startTestCase("注册新用户测试用例");
//        try {
//            RegisterUserAction.execute(driver,Constant.CONTACT_PERSON,Constant.EMAIL_USER_NAME);
//            DriverWait.judgeExist(driver,"您已注册成功");
//            driver.findElement(By.xpath("//div[@class='caption']/a")).click();//点击立即登录
//            Thread.sleep(1000);
//            DriverWait.judgeExist(driver,Constant.CONTACT_PERSON);
//        }catch (Exception e){
//            Log.error("注册失败");
//            new GetScreenshot(driver,"注册失败");
//            Assert.fail("执行注册新用户用例失败");
//        }
//        Log.endTestCase("注册新用户测试用例");
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"注册用户登录后退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }
//}
