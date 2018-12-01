//package god.testscripts.registercase;
//
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
//import static god.util.waitUse.ContextTab.pressCtrlV;
//import static god.util.waitUse.ContextTab.pressEnter;
//import static god.util.waitUse.ContextTab.pressTabkey;
//
///**
// * Created by thomas on 2018/10/8.
// * 注册成功后查看邮箱是否收到相应邮件
// */
//public class ReceiveEmaiInfo {
////    public WebDriver driver;
//    public WebDriver driver1;
//
//    @BeforeClass
//    public void BeforeClass() throws Exception{
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
////        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
////        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        driver1 = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver1.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//    }
//
//    @Test
//    public void receiveEmaiInfo() throws Exception {
//        Log.startTestCase("注册成功后是否收到邮件");
////        try {
////            RegisterUserAction.execute(driver,Constant.CONTACT_PERSON,Constant.EMAIL_USER_NAME);
////        }catch (Exception e){
////            new GetScreenshot(driver1,"注册过程中出现错误-是否收到邮件用例");
////            Log.error("注册过程中出现错误");
////            Assert.fail("注册过程中出现错误");
////        }
//
//        //登录进入邮箱
//        driver1.get(Constant.EMAIL_URL);
//        try {
////            long starttime = System.currentTimeMillis();
////            DriverWait.findElement(driver1,driver1.findElement(By.id("btlogin")));
////            driver1.findElement(By.id("inputuin")).sendKeys(Constant.EMAIL_USER_NAME);
//
//            (new WebDriverWait(driver1,5)).until(ExpectedConditions.visibilityOfElementLocated(By.id("inputuin")));
//            driver1.findElement(By.id("inputuin")).sendKeys(Constant.EMAIL_USER_NAME);
//
////            DriverWait.findElement(driver1,driver1.findElement(By.id("inputuin"))).sendKeys(Constant.EMAIL_USER_NAME);
////            long endtime = System.currentTimeMillis();
////            System.out.println("执行显示等待代码所消耗的时间为"+(endtime-starttime)+"ms");
//
//            //注意下面代码执行的时候，请不要操作移动鼠标，从而把焦点移动到其他地方去,由于执行基本上都是在无人值守时候执行自动执行代码，所以登录操作简化如下
//            pressTabkey();
//            pressCtrlV(Constant.EMAIL_PASSWD);
//            pressTabkey();
//            pressEnter();
//        }catch (Exception e ){
//            new GetScreenshot(driver1,"登录邮箱失败");
//            Log.error("登录邮箱失败");
//        }
//        try {
//            DriverWait.findElement(driver1,driver1.findElement(By.id("readmailbtn_link"))).click();//显示等待这个元素然后找到后点击收信链接
//            Thread.sleep(1000);
//            driver1 = driver1.switchTo().frame("mainFrame");
//            DriverWait.judgeExist(driver1,"您已完成账号注册");
//        }catch (Exception e){
//            new GetScreenshot(driver1,"未收到邮件");
//            Log.error("没有收到邮件");
//            Assert.fail("注册成功后，未收到邮件");
//        }
//        Log.endTestCase("注册成功后是否收到邮件");
//    }
//
//
//    @AfterMethod
//    public void afterMethod(){
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction的excut方法时失败");
////            new GetScreenshot(driver,"执行LogoutAction的excut方法时失败");
////        }
////        driver.quit();
////        driver1.quit();
//    }
//}
