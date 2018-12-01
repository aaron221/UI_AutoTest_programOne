package god.testscripts;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/11/6
 */
public class OneDriverSuite {
    public static WebDriver driver;
    @BeforeSuite
    public void beforeSuite(){
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver, "登录失败");
            Log.error("登录失败");
        }
    }

    @AfterSuite
    public void afterSuite(){
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }

//    @BeforeClass
//    public void beforeClass()throws Exception{
//        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
//        Thread.sleep(1000);
//    }
//
//    @AfterClass
//    public void afterClass()throws Exception{
//        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
//        Thread.sleep(1000);
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"企业账号")).click();//点击企业账号链接
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"进入企业账号列表页面失败");
//            Log.error("进入企业账号列表页面失败");
//            Assert.fail("进入企业账号列表页面失败");
//            e.printStackTrace();
//        }
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        driver = driver.switchTo().defaultContent();
//    }


}
