package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/31
 */
public class SessManaSuppColl {
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
            DriverWait.eleIsToBeclicked(driver,By.xpath("/html/body/div[1]/div/div[1]/div[4]/div[1]/button/span")).click();//点击是否采集
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[4]/div[2]/ul/li[2]")).click();//选择未采集
            Thread.sleep(1000);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[1]/button/span")).click();//点击来访时间
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div/div[1]/div[2]/div[2]/ul/li[5]")).click();//选择最近七天来访时间
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
    public void sessManaSuppColl(){
        Log.startTestCase("会话补采用例");
        try {
            String  beforefirstnum = driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[1]/span")).getText();
            driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[12]/div/button/span")).click();//点击补采按钮
            Thread.sleep(1000);
            driver = driver.switchTo().frame(0);
            DriverWait.findElement(driver,driver.findElement(By.id("save_btn")));//保存按钮
            driver.findElement(By.id("summary-inner")).sendKeys("这是自动化测试补采功能输入的会话小结，请忽略");
            Log.startTestCase("会话补采页面原因树搜索功能用例");
            String sencendvalue = driver.findElement(By.xpath("//ul[@id='treeReason']/li[2]/a")).getAttribute("title");//获得第二个大类来话原因值
            driver.findElement(By.id("reasonKey")).sendKeys(sencendvalue);//输入刚刚获取到的原因值
            driver.findElement(By.id("search_btn")).click();//点击搜索按钮
            Thread.sleep(500);
            String searchresult = driver.findElement(By.xpath("//ul[@id='treeReason']/li/a")).getAttribute("title");//获取搜索得到的值

            //判断搜索功能是否正确
            if(searchresult.equals(sencendvalue)){
                driver.findElement(By.xpath("//ul[@id='treeReason']/li/span[2]")).click();//点击复选框进行勾选
                driver.findElement(By.id("save_btn")).click();//点击保存按钮
                Log.info("会话补采页面原因树搜索功能正确");
                Log.endTestCase("会话补采页面原因树搜索功能用例");
                Thread.sleep(500);
            }else {
                Log.error("会话补采页面的搜索原因树功能有误");
                Assert.fail();
            }

            driver = driver.switchTo().parentFrame();
            Thread.sleep(3000);
            //判断是否采集成功
            String afterfirstnum =driver.findElement(By.xpath("/html/body/div[1]/div/div[3]/table/tr[2]/td[1]/span")).getText();
            if(!beforefirstnum.equals(afterfirstnum)){
                Log.info("补采按钮功能正确");
            }else {
                Log.error("补采按钮功能有误");
                Assert.fail();
            }

        } catch (Exception e) {
            new GetScreenshot(driver,"对会话进行补采过程中发生了错误");
            Log.error("对会话进行补采过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        Log.endTestCase("会话补采用例");
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
