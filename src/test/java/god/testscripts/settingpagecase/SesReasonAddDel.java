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
public class SesReasonAddDel {
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
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "来话原因设置"));
            MenuNameEleLoc.getMenuEle(driver, "来话原因设置").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class=\"btn-groups\"]/a[2]"))).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入来话原因设置列表页面失败");
            Log.error("进入来话原因设置列表页面失败");
            Assert.fail("进入来话原因设置列表页面失败");
            e.printStackTrace();
        }
    }

    @Test
    public void sesReasonAddDel() {
        Boolean flag = true;
        try {
            for (int i = 0; i < 2; i++) {
                driver.findElement(By.xpath("//div[@class=\"btn-groups\"]/a[1]")).click();//点击新建按钮
                DriverWait.findElement(driver, driver.findElement(By.id("editBtn"))).isDisplayed();
                if(i==0){
                    Log.startTestCase("新建来话原因父节点用例");
                    driver.findElement(By.id("nodeName_add")).sendKeys("来话原因名称");
                }else {
                    Log.endTestCase("新建来话原因父节点用例");
                    Log.startTestCase("新建来话原因子节点用例");
                    driver.findElement(By.id("nodeName_add")).sendKeys("子节点");
                    SelectSigleBox.chooseOption(driver, driver.findElement(By.id("parentId_add")), "来话原因名称");
                }
                driver.findElement(By.xpath("//input[@id='bcReasonCheckbox_add']/following-sibling::span")).click();
                driver.findElement(By.xpath("//input[@id='stateCheckbox_add']/following-sibling::span")).click();
                driver.findElement(By.id("editBtn")).click();//点击保存按钮
                Thread.sleep(1000);
            }
            Log.endTestCase("新建来话原因子节点用例");

        } catch (InterruptedException e) {
            Log.error("新建来话原因父子节点树发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        try {
            Log.startTestCase("删除来话原因父子节点用例");
            List<WebElement> listtree = driver.findElement(By.id("callReasonTree")).findElements(By.tagName("a"));
            //获取刚刚新建的父子节点元素
            String delvalue = null;
            for (int i = listtree.size()-1; i >= 0; i--) {
                delvalue = listtree.get(i).getAttribute("title");
                if(delvalue.equals("来话原因名称")||delvalue.equals("子节点")){
                        listtree.get(i).click();
                        driver.findElement(By.xpath("//div[@class=\"btn-groups\"]/a[2]")).click();//点击删除按钮
                        Thread.sleep(1000);
                        DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id='modalDel']/div[2]/div/div[3]/button[1]"))).click();//点击确认删除按钮
                        Thread.sleep(1000);
                        listtree = driver.findElement(By.id("callReasonTree")).findElements(By.tagName("a"));
                }
            }
            List<WebElement> listtree1 = driver.findElement(By.id("callReasonTree")).findElements(By.tagName("a"));//获取该模块下所有a标签的元素
            //判断是否删除父子节点成功
            String isdelvalue = null;
            for (int i = 0; i < listtree1.size(); i++) {
                isdelvalue = listtree1.get(i).getAttribute("title");
                if(isdelvalue.equals("来话原因名称")||isdelvalue.equals("子节点")){
                    Log.error("父子节点删除失败");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            Log.error("删除来话原因父子节点过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        Log.endTestCase("删除来话原因父子节点用例");
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
