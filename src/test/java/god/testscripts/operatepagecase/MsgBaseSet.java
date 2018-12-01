package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/31
 */
public class MsgBaseSet {
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
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "留言管理"));
            MenuNameEleLoc.getMenuEle(driver, "留言管理").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/main/ul/li[4]"))).click();//点击基本设置链接
            Thread.sleep(500);
            DriverWait.findElement(driver, driver.findElement(By.id("outTimeBtn")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入留言基本设置页面失败");
            Log.error("进入留言基本设置页面失败");
            e.printStackTrace();
            Assert.fail("进入留言基本设置页面失败");
        }
    }
    @Test
    public void TestCaseName(){
        Boolean flag = true;
        try {
            Log.startTestCase("修改留言超时时间用例");
               if(EditEle.editInputEle(driver, By.id("attrValue"), "22", By.id("outTimeBtn"))){
                   Log.info("修改留言超时时间成功");
               }else {
                   Log.error("修改留言超时时间失败");
                   flag = false;
               }
            Log.endTestCase("修改留言超时时间用例");

            Log.startTestCase("修改留言基本设置留言分配设置字段用例");
            if(EditEle.editSelEle(driver, By.id("autoAllocated"), By.id("outTimeBtn"))){
                Log.info("修改留言分配设置字段成功");
            }else {
                Log.error("修改留言分配设置字段失败");
                flag = false;
            }
            Log.endTestCase("修改留言基本设置留言分配设置字段用例");

           String initdivideset = EditEle.getInitSelectedEle(driver, By.id("autoAllocated"));
           if(initdivideset.equals("手动分配")){
               SelectSigleBox.chooseOption(driver, By.id("autoAllocated"),"自动分配");
           }

           Thread.sleep(500);
           Log.startTestCase("修改留言基本设置最大分配数量字段用例");
           if(EditEle.editInputEle(driver, By.id("allocateMax"), "22", By.id("outTimeBtn"))){
               Log.info("修改最大分配数量成功");
           }else {
               Log.error("修改最大分配数量失败");
               flag = false;
           }
           Log.endTestCase("修改留言基本设置最大分配数量字段用例");

          SelectSigleBox.chooseOption(driver, By.id("autoAllocated"),initdivideset);
          driver.findElement(By.id("outTimeBtn")).click();//点击保存按钮

        }catch (Exception e) {
            new GetScreenshot(driver,"修改还原留言基本设置过程中发生了错误");
            Log.error("修改还原留言基本设置过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        if(flag){
            Log.info("修改留言管理基本设置页面各字段成功");
        }else {
            Log.error("修改留言管理基本设置页面各字段失败");
            Assert.fail();
        }
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
