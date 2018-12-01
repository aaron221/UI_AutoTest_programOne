package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
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
 * Created by aaron on 2018/10/23
 */
public class SmallAppManage {
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
    }

    @Test
    public void smallAppManage(){
        Boolean flag = true;
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "小程序"));
            MenuNameEleLoc.getMenuEle(driver, "小程序").click();//点击小程序链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[2]/div[2]/button"))).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入小程序列表页面失败");
            Log.error("进入小程序列表页面失败");
            Assert.fail("进入小程序列表页面失败");
            e.printStackTrace();
        }

        for (int i = 1; i <= 2; i++) {
            if(i==1){
              Log.startTestCase("新增小程序用例");
              driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[3]/div[2]/div[2]/button")).click();//点击手动录入按钮
                try {
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[5]/div[2]/button[1]"))).isDisplayed();
                } catch (InterruptedException e) {
                    new GetScreenshot(driver,"进入小程序新增页面失败");
                    Log.error("进入小程序新增页面失败");
                    e.printStackTrace();
                }
            }else {
                Log.startTestCase("修改小程序用例");
                DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class='mcontent']/ul/li[1]/span[4]/button[1]"))).click();//点击编辑按钮
                try {
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[5]/div[2]/button[1]"))).isDisplayed();
                } catch (InterruptedException e) {
                    new GetScreenshot(driver,"进入小程序修改页面失败");
                    Log.error("进入小程序修改页面失败");
                    e.printStackTrace();
                }
            }
            try {
                if(i==2){
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[1]/div[2]/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2]/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[3]/div[2]/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[4]/div[2]/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[1]/div[2]/div/input")).sendKeys("账号名称"+i);
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[2]/div[2]/div/input")).sendKeys("wx28ba3b4384715d64");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[3]/div[2]/div/input")).sendKeys("wx28ba3b4384715d64");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[4]/div[2]/div/input")).sendKeys("9adabc783b13d067a5b51df76460d3cb");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/ul/li[5]/div[2]/button[1]")).click();//点击保存按钮
                    Thread.sleep(3000);//此处加载比较慢
                }else {
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[1]/div[2]/div/input")).sendKeys("账号名称"+i);
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[2]/div[2]/div/input")).sendKeys("wx28ba3b4384715d64");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[3]/div[2]/div/input")).sendKeys("wx28ba3b4384715d64");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[4]/div[2]/div/input")).sendKeys("9adabc783b13d067a5b51df76460d3cb");
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]/ul/li[5]/div[2]/button[1]")).click();//点击保存按钮
                    Thread.sleep(3000);//此处加载比较慢
                }
                //判断是否新增修改成功
                switch (driver.findElement(By.xpath("//div[@class='mcontent']/ul/li[1]/span[1]")).getText()){
                    case "账号名称1" : {
                        Log.info("新增小程序成功");
                        Log.endTestCase("新增小程序用例");
                        break;
                    }
                    case "账号名称2" : {
                        Log.info("修改小程序成功");
                        Log.endTestCase("修改小程序用例");
                        break;
                    }default:
                        Log.error("新增修改小程序名失败");
                        flag = false;
                }
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"新增修改小程序过程中定位元素失败");
                Log.error("新增修改小程序过程中定位元素失败");
                e.printStackTrace();
            }
        }

        Log.startTestCase("删除小程序用例");
        try {
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='mcontent']/ul/li[1]/span[4]/button[2]"))).click();//点击删除按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div/div[3]/div/button[1]"))).click();//点击确认删除按钮
            Thread.sleep(3000);

            //判断是否删除成功
            if(DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class='mcontent']/ul/li[1]/span[1]"))).getText().equals("账号名称2")){
                Log.error("删除小程序失败");
                flag = false;
            }else {
                Log.info("删除小程序成功");
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"删除小程序过程中定位元素发生了错误");
            Log.error("删除小程序过程中定位元素发生了错误");
            e.printStackTrace();
        }
        Log.endTestCase("删除小程序用例");

        if(flag){
            Log.info("新增修改删除小程序各用例成功");
        }else {
            Log.error("新增修改删除小程序各用例失败");
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
