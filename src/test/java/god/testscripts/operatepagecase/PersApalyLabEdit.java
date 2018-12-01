package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.apache.poi.util.NullLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.STString;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/26
 */
public class PersApalyLabEdit {
    public WebDriver driver;

    @BeforeClass
    public void BeforeClass() throws Exception {
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver, "登录失败");
            Log.error("登录失败");
        }

        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "运营")).click();//点击运营链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "个人回复语"));
            MenuNameEleLoc.getMenuEle(driver, "个人回复语").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(driver, driver.findElement(By.xpath("//h4[@class=\"panel-title\"]/button")));
            driver.findElement(By.xpath("//h4[@class=\"panel-title\"]/button")).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入个人回复语页面失败");
            Log.error("进入个人回复语页面失败");
            Assert.fail("进入个人回复语页面失败");
            e.printStackTrace();
        }

    }

    @Test
    public void persAplayEdit() {
        Log.startTestCase("修改个人回复语标签用例");
        String inivalue = null;
        String updatevalue2 = null;
        try {
            for (int i = 1; i <= 2; i++) {
                //选择列表一个数据进行修改操作
                WebElement element = DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody/tr[2]/td[2]/button[1]")));
                element.click();//点击编辑按钮
                Thread.sleep(1000);
                DriverWait.findElement(driver, By.xpath("//div[@id=\"modalYeqian2\"]/div[2]/div/div[2]/button[1]")).isDisplayed();

                if(i==1){
                    //进行修改操作
                    inivalue = driver.findElement(By.id("tabName_update")).getAttribute("value");
                    driver.findElement(By.id("tabName_update")).clear();
                    driver.findElement(By.id("tabName_update")).sendKeys("标签名称2");
                    DriverWait.findElement(driver, By.xpath("//div[@id=\"modalYeqian2\"]/div[2]/div/div[2]/button[1]")).click();//点击保存按钮
                }else {
                    //进行还原操作
                    driver.findElement(By.id("tabName_update")).clear();
                    driver.findElement(By.id("tabName_update")).sendKeys(inivalue);
                    DriverWait.findElement(driver, By.xpath("//div[@id=\"modalYeqian2\"]/div[2]/div/div[2]/button[1]")).click();//点击保存按钮

                }
                Thread.sleep(3000);//等待通知提示消失
                //判断是否修改成功
                updatevalue2 = DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody/tr[2]/td[1]"))).getText();
                if(updatevalue2.equals("标签名称2")){
                    Log.info("修改个人回复语标签成功");
                }else if(updatevalue2.equals(inivalue)){
                    Log.info("还原个人回复语标签成功");
                }else {
                    Log.error("修改或还原个人回复语标签失败");
                    Assert.fail();
                }
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改个人回复语标签过程中发生了错误");
            Log.error("修改个人回复语标签过程中发生了错误");
            e.printStackTrace();
        }

        Log.endTestCase("修改个人回复语标签用例");

    }


    @AfterMethod
    public void afterMethod() {
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }
}
