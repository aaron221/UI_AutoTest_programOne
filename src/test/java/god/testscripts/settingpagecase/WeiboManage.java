package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
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
 * Created by aaron on 2018/10/19
 */
public class WeiboManage {
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
    public void weiboManage() {
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "微博"));
            MenuNameEleLoc.getMenuEle(driver, "微博").click();//点击微博链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, By.xpath("//div[@class='addbtn']/button"));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入微博列表页面失败");
            Log.error("进入微博列表页面失败");
            Assert.fail("进入微博列表页面失败");
            e.printStackTrace();
        }

        Boolean flag = true;
        try {
            for (int i = 1; i <= 2; i++) {
                if (i == 1) {
                    Log.startTestCase("新增企业微博账号用例");
                    driver.findElement(By.xpath("//div[@class='addbtn']/button")).click();//点击添加企业微博账号按钮
                    DriverWait.findElement(driver, By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[7]/td[2]/a")).isDisplayed();
                } else {
                    Log.startTestCase("修改企业微博账号用例");
                    DriverWait.findElement(driver,By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[3]/a[1]")).click();//点击编辑按钮
                    DriverWait.findElement(driver, By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[7]/td[2]/a")).isDisplayed();
                    driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[1]/td[2]/input")).clear();
                    driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[2]/td[2]/input")).clear();
                    driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[4]/td[2]/input")).clear();
                    driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[5]/td[2]/input")).clear();
                }
                String weiboname = null;
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[1]/td[2]/input")).sendKeys("微博名称66" + i);
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[2]/td[2]/input")).sendKeys("123456" + i);
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[4]/td[2]/input")).sendKeys("appkey"+i);
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[5]/td[2]/input")).sendKeys("access_token"+i);
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[6]/td[2]/span[1]/input")).click();
                driver.findElement(By.xpath("//div[@id=\"saveweibo\"]/div[2]/div/div[2]/table/tbody/tr[7]/td[2]/a")).click();//点击保存
                Thread.sleep(1000);
                //判断是否新建成功
                weiboname = DriverWait.findElement(driver, By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[1]")).getText();
                if (weiboname.equals("微博名称661")) {
                    Log.info("新增企业微博账号成功");
                    Log.endTestCase("新增企业微博账号用例");
                }
                else if(weiboname.equals("微博名称662")) {
                    Log.info("修改企业微博账号成功");
                    Log.endTestCase("修改企业微博账号用例");
                } else {
                    Log.error("新增或修改企业微博账号失败");
                    flag = false;
                }
        }
            Log.startTestCase("删除企业微博账号用例");
                driver.findElement(By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[3]/a[2]")).click();//点击删除按钮
                Thread.sleep(1000);
                driver.findElement(By.xpath("//div[@id=\"modalDelete\"]/div[2]/div/div[3]/button[1]")).click();//点击确认删除按钮
                Thread.sleep(1000);
                //确认是否删除成功
                String firstname = DriverWait.findElement(driver, By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[1]")).getText();
                if (!firstname.equals("微博名称662")) {
                    Log.info("删除企业微博账号成功");
                } else {
                    Log.error("删除企业微博账号失败");
                    flag = false;
                }
            Log.endTestCase("删除企业微博账号用例");

    }catch(InterruptedException e){
        Log.error("新增修改删除企业微博过程中发生了错误");
        e.printStackTrace();
        flag = false;
    }


        if(flag){
            Log.info("新增修改删除微博账号成功");
        }else {
            Log.error("新增修改删除微博账号失败");
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
