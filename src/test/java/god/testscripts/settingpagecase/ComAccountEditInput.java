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
// * Created by thomas on 2018/10/17
// */
//public class ComAccountEditInput {
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
//    public void comAccountEditText(){
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
//
//        if(flag){
//            Log.info("修改企业账号界面的\"企业公司名称\",\"企业邮箱\",\"企业固定电话\",\"企业地址\",\"企业账号邮政编码\",\"企业联系人姓名\",\"企业联系人手机号码\"字段成功");
//        }else {
//            Log.error("修改企业账号界面的\"企业公司名称\",\"企业邮箱\",\"企业固定电话\",\"企业地址\",\"企业账号邮政编码\",\"企业联系人姓名\",\"企业联系人手机号码\"字段失败");
//            Assert.fail();
//        }
//
//    }
//
//    @AfterMethod
//    public void afterMethod() {
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction.excut失败");
////            new GetScreenshot(driver, "用例名称退出时候执行LogoutAction.excut失败");
////        }
////        driver.quit();
//    }
//}
