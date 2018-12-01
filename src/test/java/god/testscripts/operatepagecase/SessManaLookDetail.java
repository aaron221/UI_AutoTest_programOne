package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/11/1
 */
public class SessManaLookDetail {
    public WebDriver driver;

    @BeforeClass
    public void BeforeClass() throws Exception{
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver,"登录失败");
            Log.error("登录失败");
        }
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "运营")).click();//点击运营链接
            Thread.sleep(500);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "会话管理"));
            MenuNameEleLoc.getMenuEle(driver, "会话管理").click();//点击会话管理链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[4]/div[1]/button/span")).click();//点击是否采集
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[4]/div[2]/ul/li[1]")).click();//选择已采集
            Thread.sleep(1000);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/button/span")).click();//点击来访时间
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/ul/li[3]")).click();//选择最近七天来访时间
            Thread.sleep(1000);
            DriverWait.eleIsToBeclicked(driver,By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[12]/div/button/span"));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入会话管理页面失败");
            Log.error("进入会话管理页面失败");
            e.printStackTrace();
            Assert.fail("进入会话管理页面失败");
        }
    }
    @Test
    public void sessManaLookDetail(){
        Log.startTestCase("查看会话详情用例");
        try {
           String listsesscont = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[11]/span")).getText();
           driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[11]/span")).click();//点击第一条已采集数据进入详情查看页面
           Thread.sleep(1000);
           String detailsesscont = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[2]/div/main/div[1]/div")).getText();

           //判断是否正确进入详情页面
           if(listsesscont.equals(detailsesscont)){
               Log.info("成功进入会话小结详情查看页面");
           }else{
               new GetScreenshot(driver,"进入会话详情页面失败");
               Log.error("进入会话详情查看页面失败");
               Assert.fail();
           }
        } catch (Exception e) {
            new GetScreenshot(driver,"查看会话详情过程中发生了错误");
            Log.error("查看会话详情过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        Log.endTestCase("查看会话详情用例");
    }

    @AfterMethod
    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
    }
}
