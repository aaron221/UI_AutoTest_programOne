//package god.testscripts.registercase;
//
//import god.pageobjects.LoginPage;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
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
// * 查看注册页面的服务条款用例
// */
//public class LookServiceTerms {
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
//    public void lookServiceTerms(){
//        Log.startTestCase("查看服务条款测试用例");
//        driver.get(Constant.URL);
//        try {
//            DriverWait.findElement(driver, new LoginPage(driver).regNewUser()).click();//点击注册新用户链接
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//a[text()='《服务条款》']"))).click();//点击服务条款链接
//            String  thisHandleId = driver.getWindowHandle();//获取当前窗口的handleid
//            //切换到新window
//            for(String tempHandleId: driver.getWindowHandles())
//            {
//                //如果不是当前窗口的thisHandleId，那么我们就用它取切换到新窗口
//                if(!tempHandleId.equals(thisHandleId)) {
//                    driver.switchTo().window(tempHandleId);}
//            }
//            ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");//下拉到最底端查看
//            DriverWait.judgeExist(driver,"云软云客服团队");
//        }catch (Exception e){
//            new GetScreenshot(driver,"点击服务条款查看有误");
//            Log.error("点击链接按钮有误");
//        }
//        Log.endTestCase("查看服务条款测试用例");
//    }
//    @AfterMethod
//    public void afterMethod(){
//        driver.quit();
//    }
//}
