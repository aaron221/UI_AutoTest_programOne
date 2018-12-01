package god.testscripts.logincase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

public class RightLogin {
    public WebDriver driver;
    @Test
    public void rightLogin() throws Exception{
        Log.startTestCase("正常登录测试用例");
        try {
            LoginAction.execute(driver, Constant.USERNAME,Constant.PASSWD,Constant.STATUS,Constant.REM_PWD);
        }catch (Exception error){
            Log.error("执行正常登录用例失败");
            new GetScreenshot(driver,"执行正常登录用例失败");
            //执行LoginAction类的execte方法失败时，catch语可以捕获AssertionError类型的异常，并设置
            //调用Asseert类的fail方法将此测试用例设定为执行失败，后续代码将不被执行
            Assert.fail("执行LoginAction类excut方法失败");
        }
        Log.info("执行正常登录测试用例成功");
        Log.endTestCase("正常登录测试用例");
    }
    @BeforeMethod
    public void beforeMethod()throws Exception{
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
    }

    @AfterMethod
    public void afterMethod()throws Exception {
        if(driver.getPageSource().contains("账户不存在")||driver.getPageSource().contains("登录失败")){
            Thread.sleep(1000);
            driver.quit();
            Assert.fail();
        }
        else {
            try {
                LogoutAction.excut(driver);
            }catch (Exception error){
                Log.error("执行LogoutAction类的excut方法失败");
            }
            driver.quit();
        }
    }
    @BeforeSuite
    public void BeforeSuite() throws Exception{
        //使用Constant类中常量，加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }
    }
