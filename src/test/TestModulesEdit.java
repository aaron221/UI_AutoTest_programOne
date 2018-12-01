package god.util;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/8.
 */
public class TestModules {

    public WebDriver driver;

    @BeforeSuite
    public void beforeSuite() throws Exception{
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
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "留言管理"));
            MenuNameEleLoc.getMenuEle(driver, "留言管理").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/main/ul/li[2]"))).click();//点击留言类型链接
            Thread.sleep(500);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"commentType\"]/div[1]/button")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入留言类型页面失败");
            Log.error("进入留言类型页面失败");
            e.printStackTrace();
            Assert.fail("进入留言类型页面失败");
        }
    }
    @Test
    public void TestCaseName(){
        String changetype = null;
        try {
            for (int i = 1; i <= 2; i++) {
                if(i==1){
                    Log.startTestCase("修改留言类型用例");


                }else {


                }
                //判断是否修改还原成功
                changetype = DriverWait.findElement(driver,By.xpath("//table[@id=\"tableType\"]/tbody/tr[1]/td[2]")).getText();
                if(){
                    Log.info("修改留言类型成功");
                    Log.endTestCase("修改留言类型用例");
                }else if(){
                    Log.info("还原留言类型成功");
                    Log.endTestCase("还原留言类型用例");
                }else {
                    Log.error("还原或删除留言类型成功");
                    Assert.fail();
                }

            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改还原留言类型过程中发生了错误");
            Log.error("修改还原留言类型过程中发生了错误");
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
