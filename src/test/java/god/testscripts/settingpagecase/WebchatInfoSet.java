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
 * Created by aaron on 2018/10/19
 */
public class WebchatInfoSet {
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
    public void webchatInfoSet(){
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver,MenuNameEleLoc.getMenuEle(driver,"webchat"));
            MenuNameEleLoc.getMenuEle(driver,"webchat").click();//点击webchat链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, By.id("addBtn"));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入webcaht列表页面失败");
            Log.error("进入webcaht列表页面失败");
            Assert.fail("进入webcaht列表页面失败");
            e.printStackTrace();
        }

        Boolean flag = true;

        try {
            driver.findElement(By.xpath("//div[@class='tab-container']/ul/li[2]/a")).click();//点击消息配置链接
            DriverWait.findElement(driver, By.xpath("//div[@id=\"navPane\"]/div/table/tbody/tr[2]/td[4]/button")).click();//点击编辑按钮
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"进入webchat消息配置页面失败");
            Log.error("进入webchat消息配置页面失败");
            e.printStackTrace();
            Assert.fail();
        }

        String [] selname = {"origsessionid","getproperty","send-cmd03-create-session"};
        String [] hintvalue = {"是否使用原始sessionid","是否通过回调地址主动拉取用户属性","第一条消息触发导航"};
        for (int i = 0; i < selname.length; i++) {
            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
            if(EditEle.editSigCboxEle(driver, By.name(selname[i]), By.id("editBtn"))){
                Log.info("修改"+hintvalue[i]+"成功");
            }else {
                Log.error("修改"+hintvalue[i]+"失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
        }

        if(flag){
            Log.info("修改webchat消息配置页面字段成功");
        }else {
            Log.error("修改webchat消息配置页面字段失败");
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
