package god.testscripts.logincase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/8.
 */
public class ForceLogin {
    public WebDriver driver;
    public WebDriver driver1;
       @Test
    public void forceLogin() throws Exception{
        Log.startTestCase("强制登录测试用例");
        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD,Constant.STATUS, Constant.REM_PWD);
            LoginAction.execute(driver1, Constant.USERNAME, Constant.PASSWD,Constant.STATUS, Constant.REM_PWD);
        }catch (Exception error){
            Log.error("执行强制登录用例失败");
            new GetScreenshot(driver,"执行强制登录用例失败");
            //执行LoginAction类的execte方法失败时，catch语可以捕获AssertionError类型的异常，并设置
            //调用Asseert类的fail方法将此测试用例设定为执行失败，后续代码将不被执行
            Assert.fail("执行强制登录用例失败");
        }
        Log.info("执行强制登录测试用例成功");
        Log.endTestCase("强制登录测试用例");
    }

    @BeforeMethod
    public void beforeMethod()throws Exception{
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver1 = SetBrowserProperty.openChromeBrowser();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver1.manage().timeouts().implicitlyWait(3,TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod()throws Exception{
        if(driver.getPageSource().contains("账户不存在")||driver.getPageSource().contains("登录失败")){
            Thread.sleep(1000);
            driver.quit();
        }
        else if (driver1.getPageSource().contains("账户不存在")||driver1.getPageSource().contains("登录失败")){
            Thread.sleep(1000);
            driver1.quit();
        }
        else {
            try {
                LogoutAction.excut(driver1);
            }catch (Exception error){
                Log.error("执行LogoutAction类的excut方法失败");
            }
            driver.quit();
            driver1.quit();
        }
    }

    @BeforeSuite
    public void BeforeSuite() throws Exception{
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }
}
