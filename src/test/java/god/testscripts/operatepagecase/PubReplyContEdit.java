package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
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
 * Created by aaron on 2018/10/30
 */
public class PubReplyContEdit {
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
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "公共回复语"));
            MenuNameEleLoc.getMenuEle(driver, "公共回复语").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//main[@class='page-body']/ul/li[2]/a"))).click();//点击记录列表
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"jllist\"]/div/a[2]")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入公共回复语记录页面失败");
            Log.error("进入公共回复语记录页面失败");
            e.printStackTrace();
            Assert.fail("进入公共回复语记录页面失败");
        }
    }
    @Test
    public void TestCaseName(){
        String firstdep = null;
        String firstlable = null;
        String changecontent = null;
        String changelable = null;
        String changedep = null;
        String initlable = DriverWait.findElement(driver, By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[4]")).getText();
        String initcontent =  DriverWait.findElement(driver, By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[2]")).getText();
        String initdep = null;
        try {
            for (int i = 1; i <= 2; i++) {
                DriverWait.findElement(driver, By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[6]/button[1]")).click();//点击第一条数据编辑按钮
                DriverWait.findElement(driver, By.xpath("//div[@id=\"modalEdit\"]/div[2]/div/div[2]/button[1]"));
                if(i==1){
                    //进行修改操作
                    Log.startTestCase("修改公共回复语标签内容用例");
                    firstdep = EditEle.getNumSelectedEle(driver,By.id("organ_update"),1);
                    initdep = EditEle.getInitSelectedEle(driver, By.id("organ_update"));
                    SelectSigleBox.chooseOption(driver, By.id("organ_update"),1);//修改为第二个选项的组织机构
                    firstlable = EditEle.getNumSelectedEle(driver,By.id("tabId_update"),1);
                    SelectSigleBox.chooseOption(driver, By.id("tabId_update"),1);//修改为第二个选项的页签
                    driver.findElement(By.id("content_update")).clear();
                    driver.findElement(By.id("content_update")).sendKeys("这是修改后的标签内容消息");
                }else {
                    //进行还原操作
                    SelectSigleBox.chooseOption(driver, By.id("organ_update"),initdep);//修改为第二个选项的组织机构
                    SelectSigleBox.chooseOption(driver, By.id("tabId_update"),initlable);//修改为第二个选项的页签
                    driver.findElement(By.id("content_update")).clear();
                    driver.findElement(By.id("content_update")).sendKeys(initcontent);
                }
                //判断是否修改还原成功
                driver.findElement(By.xpath("//div[@id=\"modalEdit\"]/div[2]/div/div[2]/button[1]")).click();//点击保存按钮
                Thread.sleep(500);

                DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[5]")));
                changecontent = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[2]")).getText();
                changelable = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[4]")).getText();
                changedep = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[5]")).getText();

                if(i==1&&changecontent.equals("这是修改后的标签内容消息")&&firstdep.contains(changedep)&&firstlable.equals(changelable)){
                    Log.info("修改公共回复语标签内容成功");
                    Log.endTestCase("修改公共回复语标签内容用例");
                }else if(i==2&&changecontent.equals(initcontent)&&initdep.contains(changedep)&&initlable.equals(changelable)){
                    Log.info("还原公共回复语标签内容成功");
                    Log.endTestCase("还原公共回复语标签内容用例");
                }else {
                    Log.error("修改或还原公共回复语标签内容失败");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改或还原公共回复语过程中发生了错误");
            Log.error("修改或还原公共回复语过程中发生了错误");
            e.printStackTrace();
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
