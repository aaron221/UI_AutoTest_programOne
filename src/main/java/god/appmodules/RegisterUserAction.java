package god.appmodules;

import god.pageobjects.LoginPage;
import god.pageobjects.RegisterPage;
import god.util.Constant;
import god.util.DriverWait;
import god.util.GetScreenshot;
import god.util.Log;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import static god.util.waitUse.ContextTab.pressCtrlV;
import static god.util.waitUse.ContextTab.pressEnter;
import static god.util.waitUse.ContextTab.pressTabkey;

/**
 * Created by thomas on 2018/10/8.
 */
public class RegisterUserAction {
    public static void execute(WebDriver driver,String contactPerson,String emailaddress){
        try {
            LoginPage loginPage = new LoginPage(driver);
            loginPage.regNewUser().click();//点击注册新用户链接,进入注册页面
            Thread.sleep(1000);
            RegisterPage registerPage = new RegisterPage(driver);
            DriverWait.findElement(driver, registerPage.directLogin());//等待注册页面加载完毕
            registerPage.phoneNum().sendKeys(Constant.PHONE_NUM);
            registerPage.getCode().click();//点击获取短信验证码
            registerPage.phoneCode().sendKeys(Constant.PHONE_CODE);
            registerPage.photoCode().sendKeys(Constant.PHOTO_CODE);
            registerPage.agreeRegister().click();//点击同意服务条款并注册按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,registerPage.directLogin());
            registerPage.companyName().sendKeys("自动化输入的企业名称");
            //接下来的几个输入框可以通过tab键进行切换定位，然后复制粘贴相关内容进去
            pressTabkey();
            pressCtrlV(contactPerson);
            pressTabkey();
            pressCtrlV("自动输入的企业地址");
            pressTabkey();
            pressCtrlV(emailaddress);
            pressTabkey();
            pressCtrlV("自动输入的推荐人信息");
            pressEnter();//点击完成注册按钮
            Thread.sleep(1000);
        }catch (Exception e){
            Log.error("注册失败");
            new GetScreenshot(driver,"注册失败");
            Assert.fail("执行注册新用户用例失败");
        }
    }
}
