//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/17.
// */
//public class ComAccoutEditSel {
//    public WebDriver driver;
//
//    @BeforeClass
//    public void BeforeClass() throws Exception{
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        try {
//            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @Test
//    public void comAccoutEditSel(){
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"企业账号")).click();//点击企业账号链接
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"进入企业账号列表页面失败");
//            Log.error("进入企业账号列表页面失败");
//            Assert.fail("进入企业账号列表页面失败");
//            e.printStackTrace();
//        }
//
//        Boolean flag = true;
//        String [] inputname = {"industry","province","city"};
//        String [] hintvalue2 = {"企业行业","企业所在省","企业所在市"};
//        for (int i = 0; i < inputname.length; i++) {
//            Log.startTestCase("修改"+hintvalue2[i]+"字段用例");
//            if(EditEle.editSelEle(driver, By.id(inputname[i]), By.id("updateBtn"))){
//                Log.info("修改"+hintvalue2[i]+"字段成功");
//            }else {
//                Log.error("修改"+hintvalue2[i]+"字段失败");
//                flag = false;
//            }
//            Log.endTestCase("修改"+hintvalue2[i]+"字段用例");
//        }
//
//        if(flag){
//            Log.info("修改企业账号界面单选框字段信息成功");
//        }else {
//            Log.error("修改企业账号界面单选框字段信息成功");
//            Assert.fail("修改企业账号界面单选框字段信息成功");
//        }
//
//    }
//
//    @AfterMethod
//    public void afterMethod() {
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver, "用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }
//}
