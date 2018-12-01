package god.testscripts.settingpagecase;

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

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/25
 */
public class SesReasonEdit {
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
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "来话原因设置"));
            MenuNameEleLoc.getMenuEle(driver, "来话原因设置").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class=\"btn-groups\"]/a[2]"))).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入来话原因设置列表页面失败");
            Log.error("进入来话原因设置列表页面失败");
            Assert.fail("进入来话原因设置列表页面失败");
            e.printStackTrace();
        }
    }

    @Test
    public void sesReasonEdit(){
        Boolean flag = true;
        try {
            driver.findElement(By.id("callReasonTree_2_a")).click();//点击第二个节点进行修改操作
            Thread.sleep(1000);
            List<Boolean> listright = new ArrayList<>();
            String[] casename = {"来话原因名称","上级来话原因","是否必采项","状态"};
            By savebut = By.xpath("//form[@id=\"callReasonUpdateForm\"]/div[7]/div/a[2]");
            DriverWait.findElement(driver, driver.findElement(savebut)).isDisplayed();
            listright.add(EditEle.editInputEle(driver, By.id("nodeName_update"), "来话原因名称", savebut));
            listright.add(EditEle.editSelEle(driver, By.id("parentId"), "0", savebut));//修改为value值为0的选项
            listright.add(EditEle.editSigCboxEle(driver, By.id("bcReasonCheckbox"), savebut));
            listright.add(EditEle.editSigCboxEle(driver, By.id("stateCheckbox"), savebut));

            for (int i = 0; i < listright.size(); i++) {
                Log.startTestCase("修改"+casename[i]+"字段用例");
                if(listright.get(i)){
                    Log.info("修改"+casename[i]+"字段成功");
                }else {
                    Log.error("修改" + casename[i] + "字段失败");
                    flag = false;
                }
                Log.endTestCase("修改" + casename[i] + "字段用例");
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改来话原因设置页面各个字段发生了错误");
            Log.error("修改来话原因设置页面各个字段过程中发生了错误");
            e.printStackTrace();
        }

        if(flag){
            Log.info("修改来话原因设置页面各个字段成功");
        }else {
            Log.error("修改来话原因设置页面各个字段失败");
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
