//package god.testscripts.homepagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/9.
// */
//public class LookDetailContactPlan {
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
//            LoginAction.execute(driver,Constant.USERNAME,Constant.PASSWD,Constant.STATUS,Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @Test
//    public void lookDetailContactPlan(){
//        Log.startTestCase("查看联系计划详细信息");
//        try {
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);//切换到mainiframe框架中去
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//tr[@class='planDetail']/td[4]/div/a"))).click();//点击第一个联系计划
//            Thread.sleep(1000);
//            //判断是否显示联系计划明细
//            List<WebElement> elenums = driver.findElements(By.xpath("//span[text()='测试服务中心']"));//联系计划的客服组为测试服务中心，这里根据账号实际情况进行填写
//            System.out.println(elenums.size());
//            if(elenums.size()!=2){
//                new GetScreenshot(driver,"查看联系计划明细失败");
//                Assert.fail("查看联系计划明细失败");
//            }
//        }catch (Exception e){
//            new GetScreenshot(driver,"查看联系计划失败");
//            Log.error("查看联系计划失败");
//        }
//        Log.endTestCase("查看联系计划详细信息");
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
