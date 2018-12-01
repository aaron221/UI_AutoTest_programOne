package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/30
 */
public class PubReplyLabEdit {
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
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "公共回复语"));
            MenuNameEleLoc.getMenuEle(driver, "公共回复语").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"pagelist\"]/div/button")));//新建按钮
        } catch (Exception e) {
            new GetScreenshot(driver, "进入公共回复语页面失败");
            Log.error("进入公共回复语页面失败");
            Assert.fail("进入公共回复语页面失败");
            e.printStackTrace();
        }

    }

    @Test
    public void pubReplyLabEdit(){
        //获取原始值
        String initlabe = driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[2]/td[1]")).getText();//获取原始标签值
        String initdep = null;
        String depvalue = null;
        try {
            for (int i = 1; i <= 2; i++) {
                DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[2]/td[3]/button[1]"))).click();//点击第一条数据的编辑按钮
                DriverWait.findElement(driver, By.xpath("//div[@id=\"modalYeqian2\"]/div[2]/div/div[2]/button[1]"));
                if (i == 1) {
                    Log.startTestCase("修改公共回复语标签用例");
                    depvalue = driver.findElement(By.xpath("//select[@id='orgId_update']/option[2]")).getText();//获取第二个选项的值
                    //获取原始组织机构的名字
                    initdep = EditEle.getInitSelectedEle(driver, By.id("orgId_update"));
                    SelectSigleBox.chooseOption(driver, By.id("orgId_update"), 1);//选择第二个option选项-组织机构
                    driver.findElement(By.id("tabName_update")).clear();
                    driver.findElement(By.id("tabName_update")).sendKeys("这是页签名称");
                } else {
                    SelectSigleBox.chooseOption(driver, By.id("orgId_update"), initdep);//还原原始的组织机构
                    driver.findElement(By.id("tabName_update")).clear();
                    driver.findElement(By.id("tabName_update")).sendKeys(initlabe);
                }

                driver.findElement(By.xpath("//div[@id=\"modalYeqian2\"]/div[2]/div/div[2]/button[1]")).click();//点击保存按钮
                CoverEleWait.getHideEle(driver,By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[2]/td[1]"));
                String updatelable = driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[2]/td[1]")).getText();
                String updatedep = driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[2]/td[2]")).getText();

                //判断是否修改成功
                if (i == 1 && updatelable.equals("这是页签名称") && (depvalue.equals(updatedep))) {
                    Log.info("修改公共回复语标签成功");
                    Log.endTestCase("修改公共回复语标签用例");
                } else if(i == 2 && updatelable.equals(initlabe) && (depvalue.equals(initdep))) {
                    Log.info("还原公共回复语标签成功");
                }else {
                    Log.error("修改或还原公共回复语标签失败");
                    //输出修改前的原始信息到日志中去，避免还原出错的情况下进行恢复操作。根据实际执行时间进行查询(报错截图图片名上有当前执行时间)
                    Log.error(SystemTime.getCurTime("initdep")+initdep);
                    Log.error(SystemTime.getCurTime("initlabe")+initlabe);
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改还原公共回复语标签过程中发生了错误");
            Log.error("修改还原公共回复语标签过程中发生了错误");
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
