package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.testscripts.OneDriverSuite;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/16
 */
public class ComAccount {
//    public static WebDriver driver;
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
    public void beforeClass() throws Exception {
//        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
//        Thread.sleep(1000);
    }

    @AfterClass
    public void afterClass() throws Exception {
        DriverWait.findElement(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "首页")).click();
        Thread.sleep(1000);
    }

    @BeforeMethod
    public void beforeMethod() {
        try {
            DriverWait.findElement(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "设置")).click();//点击设置链接
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DriverWait.findElement(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "企业账号")).click();//点击企业账号链接
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.THIRD_IFRAME);
        } catch (Exception e) {
            new GetScreenshot(OneDriverSuite.driver, "进入企业账号列表页面失败");
            Log.error("进入企业账号列表页面失败");
            Assert.fail("进入企业账号列表页面失败");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod() {
        OneDriverSuite.driver = OneDriverSuite.driver.switchTo().defaultContent();
    }

//    @Test(priority = 0)
//    public void companyAccountManage() throws Exception {
//            Boolean flag = true;
//            if(EditEle.eidtAccountImg(driver, By.id("uploadPreview"), By.id("uploadLogo"),"企业账号")){
//                Log.info("修改企业logo成功");
//            }else {
//                new GetScreenshot(driver,"修改企业logo失败");
//                Log.error("修改企业logo失败");
//                flag = false;
//            }
//
//            if(EditEle.eidtAccountImg(driver, By.id("licensePreview"), By.id("uploadLicense"),"企业账号")){
//                Log.info("修改公司营业执照成功");
//            }else {
//                new GetScreenshot(driver,"修改公司营业执照失败");
//                Log.error("修改公司营业执照失败");
//                flag = false;
//            }
//
//            if(EditEle.eidtAccountImg(driver, By.id("defaultLogUrlPreview"), By.id("uploadDefaultLogUrl"),"企业账号")){
//                Log.info("修改客服头像成功");
//            }else {
//                new GetScreenshot(driver, "修改客服头像失败");
//                Log.error("修改客服头像失败");
//                flag = false;
//            }
//        if(flag){
//            Log.info("修改企业账号界面图片字段成功");
//        }else {
//            Log.error("修改企业账号界面图片字段失败");
//            Assert.fail();
//        }
//    }

    //    @Test(priority = 1)
//    public void comAccountEditText(){
//        Boolean flag = true;
//        String [] inputname = {"name","email","fixedPhone","address","postalcode","contacts","contactsTel"};
//        String [] hintvalue2 = {"企业公司名称","企业邮箱","企业固定电话","企业地址","企业账号邮政编码","企业联系人姓名","企业联系人手机号码"};
//        String [] updatavalue = {"aaron","165517692@qq.com","0755-6666666","滨江大道6666号","666666","秦始皇","13566666666"};
//        for (int i = 0; i < inputname.length; i++) {
//            Log.startTestCase("修改"+hintvalue2[i]+"字段用例");
//            if(EditEle.editInputEle(driver, By.id(inputname[i]),updatavalue[i], By.id("updateBtn"))){
//                Log.info("修改"+hintvalue2[i]+"字段成功");
//            }else {
//                Log.error("修改"+hintvalue2[i]+"字段失败");
//                flag = false;
//            }
//            Log.endTestCase("修改"+hintvalue2[i]+"字段用例");
//        }
//        if(flag){
//            Log.info("修改企业账号界面的\"企业公司名称\",\"企业邮箱\",\"企业固定电话\",\"企业地址\",\"企业账号邮政编码\",\"企业联系人姓名\",\"企业联系人手机号码\"字段成功");
//        }else {
//            Log.error("修改企业账号界面的\"企业公司名称\",\"企业邮箱\",\"企业固定电话\",\"企业地址\",\"企业账号邮政编码\",\"企业联系人姓名\",\"企业联系人手机号码\"字段失败");
//            Assert.fail();
//        }
//    }
//
    @Test(priority = 2)
    public void comAccoutEditSel() {
        Boolean flag = true;
        String[] inputname = {"industry", "province", "city"};
        String[] hintvalue2 = {"企业行业", "企业所在省", "企业所在市"};
        for (int i = 0; i < inputname.length; i++) {
            Log.startTestCase("修改" + hintvalue2[i] + "字段用例");
            if (EditEle.editSelEle(OneDriverSuite.driver, By.id(inputname[i]), By.id("updateBtn"))) {
                Log.info("修改" + hintvalue2[i] + "字段成功");
            } else {
                Log.error("修改" + hintvalue2[i] + "字段失败");
                flag = false;
            }
            Log.endTestCase("修改" + hintvalue2[i] + "字段用例");
        }
        if (flag) {
            Log.info("修改企业账号界面单选框字段信息成功");
        } else {
            Log.error("修改企业账号界面单选框字段信息成功");
            Assert.fail("修改企业账号界面单选框字段信息成功");
        }
    }
}
