//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
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
// * Created by aaron on 2018/10/18
// */
//public class PersInfoCardEdit {
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
//        try {
//            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @Test
//    public void persInfoCardEdit(){
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//        } catch (Exception e) {
//            new GetScreenshot(driver, "进入个人信息页面失败");
//            Log.error("进入个人信息页面失败");
//            Assert.fail("进入个人信息页面失败");
//            e.printStackTrace();
//        }
//
//        Boolean flag = true;
//        Log.startTestCase("修改个人信息客服性别用例");
//        if(EditEle.editSelEle(driver, By.id("sex"),By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改客服性别成功");
//        }else {
//            new GetScreenshot(driver,"修改客服性别失败");
//            Log.error("修改客服性别失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息客服性别用例");
//
//        Log.startTestCase("修改个人信息客服昵称用例");
//        if(EditEle.editInputEle(driver, By.id("crmName"), "aaron", By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改客服昵称成功");
//        }else {
//            new GetScreenshot(driver,"修改客服昵称失败");
//            Log.error("修改客服昵称失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息客服昵称用例");
//
//        Log.startTestCase("修改个人信息客服年龄用例");
//        if(EditEle.editInputEle(driver, By.name("age"), "18", By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改客服年龄成功");
//        }else {
//            new GetScreenshot(driver,"修改客服年龄失败");
//            Log.error("修改客服年龄失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息客服年龄用例");
//
//        Log.startTestCase("修改个人信息客服联系电话用例");
//        if(EditEle.editInputEle(driver, By.id("tel"), "13666666666", By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改客服昵称成功");
//        }else {
//            new GetScreenshot(driver,"修改客联系电话称失败");
//            Log.error("修改客联系电话称失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息客服联系电话用例");
//
//        if(flag){
//            Log.info("修改个人信息客服名片模块字段成功");
//        }else {
//            Log.error("修改个人信息客服名片模块字段失败");
//            Assert.fail();
//        }
//
//
//    }
//
//    @AfterMethod
//    public void afterMethod(){
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction.excut失败");
////            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
////        }
////        driver.quit();
//    }
//}
