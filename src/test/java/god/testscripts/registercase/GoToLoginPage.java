//package god.testscripts.registercase;
//
//import god.pageobjects.LoginPage;
//import god.pageobjects.RegisterPage;
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
// * 验证注册页面与登录首页的跳转链接
// */
//public class GoToLoginPage {
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
//    public void goToLoginPage() throws Exception {
//        Log.startTestCase("跳转到登录首页测试用例");
//        driver.get(Constant.URL);
//        DriverWait.findElement(driver,new LoginPage(driver).regNewUser()).click();
//        Thread.sleep(1000);
//        new RegisterPage(driver).directLogin().click();//点击已有账号，直接登录链接
//        Thread.sleep(1000);
//        try {
//            DriverWait.findElement(driver,driver.findElement(By.id("logonNumber")));
//        }catch (Exception e){
//            Log.error("跳转到登录首页失败");
//            new GetScreenshot(driver,"执行跳转到登录首页测试用例失败");
//            Assert.fail("执行跳转到登录首页测试用例失败");
//        }
//        Log.endTestCase("跳转到登录首页测试用例");
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        driver.quit();
//    }
//}
