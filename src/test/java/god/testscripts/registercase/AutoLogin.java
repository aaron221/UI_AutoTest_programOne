//package god.testscripts.registercase;
//
//import god.appmodules.LogoutAction;
//import god.appmodules.RegisterUserAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
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
//public class AutoLogin {
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
//    public void testAutoLogin() throws Exception {
//        driver.get(Constant.URL);
//        Log.startTestCase("三秒后自动登录");
//        try {
//            RegisterUserAction.execute(driver,Constant.CONTACT_PERSON,Constant.EMAIL_USER_NAME);
//            Thread.sleep(3000);
//            DriverWait.judgeExist(driver,Constant.CONTACT_PERSON);
//            Log.info("三秒后自动登录");
//        }catch (Exception e){
//            Log.error("自动登录失败");
//            new GetScreenshot(driver,"自动登录失败");
//            Assert.fail("执行自动登录测试用例失败");
//        }
//        Log.endTestCase("三秒后自动登录");
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
//
//        driver.quit();
//    }
//}
