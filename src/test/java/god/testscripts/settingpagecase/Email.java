package god.testscripts.settingpagecase;

import god.testscripts.OneDriverSuite;
import org.openqa.selenium.WebDriver;
import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/22
 */
public class Email {
//    public WebDriver driver ;
//    @BeforeSuite
//    public void beforeSuite() throws Exception {
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        try {
//            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver, "登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @AfterSuite
//    public void afterSuite(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }

    @BeforeClass
    public void beforeClass()throws Exception{
//        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
//        Thread.sleep(1000);
    }

    @AfterClass
    public void afterClass(){
        DriverWait.findElement(OneDriverSuite.driver,MenuNameEleLoc.getMenuEle(OneDriverSuite.driver,"首页")).click();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeMethod
    public void beforeMethod(){
        try {
            DriverWait.findElement(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "邮箱"));
            MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "邮箱").click();//点击微博链接
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@class='addbtn']/button")));
        } catch (Exception e) {
            new GetScreenshot(OneDriverSuite.driver, "进入邮箱列表页面失败");
            Log.error("进入邮箱列表页面失败");
            Assert.fail("进入邮箱列表页面失败");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(){
        OneDriverSuite.driver = OneDriverSuite.driver.switchTo().defaultContent();
    }

    @Test
    public void emaiManage(){
        Boolean flag = true;
        try {
            for (int i = 1; i <= 2 ; i++) {
                if (i == 1) {
                    Log.startTestCase("新增邮箱设置用例");
                    OneDriverSuite.driver.findElement(By.xpath("//div[@class='addbtn']/button")).click();//点击添加邮箱地址按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[3]/button"))).isDisplayed();
                } else {
                    Log.startTestCase("修改邮箱设置用例");
                    DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[3]/button[1]"))).click();//点击编辑按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[3]/button"))).isDisplayed();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[1]/td[2]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[2]/td[2]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[3]/td[2]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[1]/td[2]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[1]/td[4]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[2]/input")).clear();
                    OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[4]/input")).clear();
                }
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[1]/td[2]/input")).sendKeys("邮箱名称" + i);
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[2]/td[2]/input")).sendKeys("123231@qq.com");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[1]/tbody/tr[3]/td[2]/input")).sendKeys("Aa_111111");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[1]/td[2]/input")).sendKeys("outlook.office365.com");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[1]/td[3]/span/input")).click();
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[1]/td[4]/input")).sendKeys("995");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[2]/input")).sendKeys("smtp.office365.com");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[3]/span/input")).click();
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[2]/table[2]/tbody/tr[2]/td[4]/input")).sendKeys("465");
                OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"addemail\"]/div[2]/div/div[3]/button")).click();//点击保存按钮
                Thread.sleep(1000);

                //判断是否添加修改成功
                WebElement element1 = DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[1]")));
                    if (i==1&&element1.getText().equals("邮箱名称1")) {
                        Log.info("新增邮箱成功");
                        Log.endTestCase("新增邮箱用例");
                    } else if(i==2&&element1.getText().equals("邮箱名称2")) {
                        Log.info("修改邮箱成功");
                        Log.endTestCase("修改邮箱用例");
                    } else {
                        Log.error("新增或修改邮箱失败");
                        Assert.fail();
                        flag = false;
                    }
                }
        } catch (InterruptedException e) {
            new GetScreenshot(OneDriverSuite.driver,"新增修改邮箱过程中出错了");
            Log.error("新增修改邮箱过程中出错了");
            flag = false;
            e.printStackTrace();
        }

        try {
            Log.startTestCase("删除邮箱设置用例");
            DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[3]/button[2]"))).click();//点击删除按钮
            Thread.sleep(1000);
            DriverWait.findElement(OneDriverSuite.driver,OneDriverSuite.driver.findElement(By.xpath("//div[@id=\"modalDelete\"]/div[2]/div/div[3]/button[1]"))).click();//点击确认删除按钮
            Thread.sleep(1000);

            //确认是否删除成功
            WebElement element2 = DriverWait.findElement(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//div[@class='panel-table']/table/tbody/tr[2]/td[1]")));
            if (!element2.getText().equals("邮箱名称2")) {
                Log.info("删除邮箱成功");
                Log.endTestCase("删除邮箱设置用例");
            } else {
                new GetScreenshot(OneDriverSuite.driver, "删除邮箱失败");
                Log.error("删除邮箱失败");
                flag = false;
                Log.endTestCase("删除邮箱设置用例");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = false;
            new GetScreenshot(OneDriverSuite.driver,"删除邮箱过程中出错了");
            Log.error("删除邮箱过程中出错了");
        }

        if(flag){
            Log.info("执行邮箱设置的新增修改删除用例成功");
        }else {
            Log.error("执行邮箱设置的新增修改删除用例失败");
            Assert.fail();
        }

    }
}
