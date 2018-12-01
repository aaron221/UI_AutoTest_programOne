//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by aaron on 2018/10/18
// * 此测试用例所用的客服工号需要在坐席管理处配置允许电话接入才能正确执行
// */
//public class PersInfoBindPhone {
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
//    public void persInfoBindPhone(){
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//            Thread.sleep(1000);
//        } catch (Exception e) {
//            new GetScreenshot(driver, "进入个人信息页面失败");
//            Log.error("进入个人信息页面失败");
//            Assert.fail("进入个人信息页面失败");
//            e.printStackTrace();
//        }
//
//        Boolean flag = true;
//
//
//        Log.startTestCase("修改个人信息是否绑定手机号用例");
//        if(EditEle.editSigCboxEle(driver, By.id("isBindMobileNoChk"),By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改是否绑定手机号成功");
//        }else {
//            Log.error("修改是否绑定手机号失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息是否绑定手机号用例");
//
//        Log.startTestCase("修改个人信息绑定的手机号用例");
//        if(EditEle.editInputEle(driver, By.id("mobileNoStr"), "13588888888", By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("修改绑定的手机号成功");
//        }else {
//            Log.error("修改绑定的手机号失败");
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息绑定的手机号用例");
//
//        Log.startTestCase("修改个人信息手机号绑定转接时间用例");
//        //通过手动输入模式修改转接时间
//        if(EditEle.editInputEle(driver, By.id("bindMobileWorkTimeStr"), "00:00-23:30", By.xpath("/html/body/div[1]/div/footer/button"))){
//            Log.info("手动输入转接时间成功");
//        }else {
//            Log.error("手动输入转接时间失败");
//            flag = false;
//        }
//
//        //通过按钮点击选择修改转接时间
//        try {
//            WebElement element = DriverWait.findElement(driver,driver.findElement(By.id("bindMobileWorkTimeStr")));
//            String timevalue = element.getAttribute("value");//把修改前的姓名给保存到变量中，后面好还原
//            element.clear();//清除原先的姓名元素的value值
//
//            driver.findElement(By.id("date1")).click();
//            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]")));
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[1]")).click();
//            driver.findElement(By.id("date2")).click();
//            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[4]")));
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[4]")).click();
//            driver.findElement(By.xpath("//input[@id='date2']/parent::td/following-sibling::td/i")).click();//点击加号按钮
//            driver.findElement(By.xpath("/html/body/div[1]/div/footer/button")).click();//点击保存按钮
//
//            //判断是否修改成功
//            String timeupdate = DriverWait.findElement(driver, driver.findElement(By.id("bindMobileWorkTimeStr"))).getAttribute("value");
//            if(!timevalue.equals(timeupdate)){
//                Log.info("通过按钮修改转接时间成功");
//            }else {
//                new GetScreenshot(driver,"通过按钮修改转接时间失败");
//                Log.error("通过按钮修改转接时间失败");
//                flag = false;
//            }
//
//            //把原先的转接时间还原回去
//            driver.findElement(By.id("bindMobileWorkTimeStr")).clear();//清除修改的转接时间
//            driver.findElement(By.id("bindMobileWorkTimeStr")).sendKeys(timevalue);
//            driver.findElement(By.xpath("/html/body/div[1]/div/footer/button")).click();//点击保存按钮
//
//            //判断是否还原成功
//            if(driver.findElement(By.id("bindMobileWorkTimeStr")).getAttribute("value").equals(timevalue)){
//                Log.info("转接时间还原成功");
//            }else {
//                new GetScreenshot(driver,"转接时间还原失败");
//                Log.error("转接时间还原失败");
//            }
//        } catch (Exception e) {
//            new GetScreenshot(driver,"通过按钮修改转接时间失败");
//            Log.error("通过按钮修改转接时间失败");
//            e.printStackTrace();
//            flag = false;
//        }
//        Log.endTestCase("修改个人信息手机号绑定转接时间用例");
//
//        if(flag){
//            Log.info("修改手机绑定模块字段信息成功");
//        }else {
//            Log.error("修改手机绑定模块字段信息失败");
//            Assert.fail();
//        }
//
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }
//}
