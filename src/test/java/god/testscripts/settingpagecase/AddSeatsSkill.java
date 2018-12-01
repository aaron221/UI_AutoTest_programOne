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
// * Created by thomas on 2018/10/10.
// */
//public class AddSeatsSkill {
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
//    public void addSeatsSkill(){
//        Log.startTestCase("技能新建");
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"坐席分组")).click();//点击坐席分组链接
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//ul[@role='tablist']/li[2]/a"))).click();//点击技能链接
//            driver = driver.switchTo().frame("iframeSkill");
//            Thread.sleep(1000);
//            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div/a"))).click();//点击新建按钮
//            driver = driver.switchTo().parentFrame();//返回到上一级框架
//            DriverWait.findElement(driver,driver.findElement(By.id("skillDescribe"))).isDisplayed();
//            SelectSigleBox.chooseOption(driver,driver.findElement(By.id("skillCenterOrg")), 2);//选择第二个组织
//            driver.findElement(By.id("skillName")).sendKeys("自动输入的技能名称");//输入技能名称
//            driver.findElement(By.id("skillDescribe")).sendKeys("自动输入的技能描述");//输入技能描述
//            driver.findElement(By.xpath("//div[@id='modalUpdateSkill']/div[2]/div/div[3]/a")).click();//点击保存按钮
//            driver = driver.switchTo().frame("iframeSkill");
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//td[@title='自动输入的技能描述']"))).isDisplayed();//判断是否新建成功
//            driver.findElement(By.xpath("//td[@title='自动输入的技能描述']/following-sibling::td[2]/botton[2]")).click();//点击刚刚新建的角色后面的删除按钮
//            driver =driver.switchTo().parentFrame();
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalDelSkill\"]/div[2]/div/div[3]/a"))).click();//点击确定按钮
//        } catch (Exception e) {
//            new GetScreenshot(driver,"新建技能失败");
//            Log.error("新建技能失败");
//            Assert.fail("新建技能失败");
//        }
//        Log.endTestCase("技能新建");
//
//    }
//
////    @AfterMethod
////    public void afterMethod(){
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction.excut失败");
////            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
////        }
////        driver.quit();
////    }
//}
