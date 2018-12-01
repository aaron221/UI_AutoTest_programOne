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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/23
 */
public class WeixinInfoSetManage {
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
    public void weixinInfoSetManage(){
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
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div/div[1]/ul/li[2]/a"))).click();//点击消息配置链接
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id='msgPane']/div/table/tbody/tr[2]/td[3]/button"))).isDisplayed();
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"进入消息配置列表页面失败");
            Log.error("进行消息配置列表页面失败");
            e.printStackTrace();
        }

        try {
            driver.findElement(By.xpath("//div[@id='msgPane']/div/table/tbody/tr[2]/td[3]/button")).click();//点击第一行数据编辑按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.id("editBtn"))).isDisplayed();
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"进行消息配置编辑修改页面失败");
            Log.error("进入消息配置编辑修改页面失败");
            e.printStackTrace();
        }

        String [] selname = {"voiceRecognition","voiceRecognitionSendMedia","sendSubscribe"
                , "sendUnSubscribe","send2DSubscribe","send-cmd03-create-session"
                , "sendFirstLocation","keep-cmd","bloadcustomer-ex"};
        String [] hintvalue = {"是否启用语音识别","接受语音文件","主导航发送关注提示语"
                ,"主导航触发取消关注","二维码关注/扫描欢迎语","第一条消息触发导航"
                ,"第一次地理位置触发导航","是否递增导航序号","是否加载用户扩展属性"};
        for (int i = 0; i < selname.length; i++) {
            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
            if(EditEle.editSigCboxEle(driver, By.name(selname[i]), By.id("editBtn"))){
                Log.info("修改"+hintvalue[i]+"字段成功");
            }else {
                Log.error("修改"+hintvalue[i]+"字段失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
        }

        String [] inputname = {"voiceRecognitionTip","maxmsgcounttip","maxusernumbertip"};
        String [] hintvalue2 = {"语音识别抬头","超出每日消息数提示语","超出每日最大好友数提示语"};
        for (int i = 0; i < inputname.length; i++) {
            Log.startTestCase("修改"+hintvalue2[i]+"字段用例");
            if(EditEle.editInputEle(driver, By.id(inputname[i]), hintvalue2[i], By.id("editBtn"))){
                Log.info("修改"+hintvalue2[i]+"字段成功");
            }else {
                Log.error("修改"+hintvalue2[i]+"字段失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue2[i]+"字段用例");
        }

        if(flag){
            Log.info("修改微信消息配置页面各个字段成功");
        }else {
            Log.error("修改微信消息配置页面各个字段失败");
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
