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
 * Created by aaron on 2018/10/22
 */
public class WeixinSerNumManage {
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
    public void weixinSerNumManage(){
        Boolean flag = true;
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "微信"));
            MenuNameEleLoc.getMenuEle(driver, "微信").click();//点击微信链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class='access-method']/button[2]")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入微信列表页面失败");
            Log.error("进入微信列表页面失败");
            Assert.fail("进入微信列表页面失败");
            e.printStackTrace();
        }

        try {
            for (int i = 1; i <= 2; i++) {
                String addbutid = null;
                if(i==1){
                    Log.startTestCase("新增微信服务号用例");
                    driver.findElement(By.xpath("//div[@class='access-method']/button[2]")).click();//点击手动录入按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,driver.findElement(By.id("editBtn_1"))).isDisplayed();
                    addbutid = "editBtn_1";
                    driver.findElement(By.id("authenticationType1")).click();//是否认证-是
                }else {
                    Log.startTestCase("修改微信服务号用例");
                    DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]" +
                            "/table/tbody/tr[2]/td[5]/button[2]"))).click();//点击编辑按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,driver.findElement(By.id("editBtn"))).isDisplayed();
                    addbutid = "editBtn";
                    driver.findElement(By.id("imName")).clear();
                    driver.findElement(By.id("imNumber")).clear();
                    driver.findElement(By.id("appID")).clear();
                    driver.findElement(By.id("appKey")).clear();
                    driver.findElement(By.id("authenticationType2")).click();//是否认证-否
                }
            driver.findElement(By.id("imName")).sendKeys("公司测试机微信"+i);
            driver.findElement(By.id("imNumber")).sendKeys("gh_31a2a1e1641f");
            driver.findElement(By.id("appID")).sendKeys("wxd8e0da0e36564d5f");
            driver.findElement(By.id("appKey")).sendKeys("8fda39e7daa24579d5765f1430442ab3");
            driver.findElement(By.id(addbutid)).click();//点击保存按钮
            driver.findElement(By.xpath("//button[@id='"+addbutid+"']/following-sibling::button")).click();//点击取消按钮退出新增页面
            Thread.sleep(1000);

            //判断是否新增修改成功
            String weixinName = DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]" +
                    "/table/tbody/tr[2]/td[1]"))).getAttribute("title");//获取新创建的微信服务号名称
            if(weixinName.equals("公司测试机微信1")){
                Log.info("新增微信服务号成功");
                Log.endTestCase("新增微信服务号用例");
            }else if(weixinName.equals("公司测试机微信2")){
                Log.info("修改微信服务号成功");
                Log.endTestCase("修改微信服务号用例");
            }else {
                Log.error("新增或修改微信服务号失败");
                flag = false;
            }
        }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"新增或修改微信服务号过程中出现了错误");
            Log.error("新增或修改微信服务号过程中出现了错误");
            e.printStackTrace();
            Assert.fail();
        }

        Log.startTestCase("删除微信服务号用例");
        try {
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]" +
                    "/table/tbody/tr[2]/td[5]/button[3]"))).click();//点击删除按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.id("delBtn"))).click();//点击确认删除按钮
            Thread.sleep(1000);
            //判断是否删除成功
            String weixinName1 = DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]" +
                    "/table/tbody/tr[2]/td[1]"))).getAttribute("title");//获取第一行的微信服务号的微信名称
            if(!weixinName1.equals("公司测试机微信2")){
                Log.info("删除微信服务号成功");
            }else {
                Log.error("删除微信服务号失败");
                flag = false;
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"删除微信服务号过程中发生了错误");
            Log.error("删除微信服务号过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        Log.endTestCase("删除微信服务号用例");


        if(flag){
            Log.info("执行对微信服务号新增修改删除各用例成功");
        }else {
            Log.error("执行对微信服务号新增修改删除各用例失败");
            Assert.fail();
        }
    }


    @AfterMethod
    public void afterMethod(){
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }
}
