package god.appmodules;

import god.pageobjects.HomePage;
import god.pageobjects.LoginPage;
import god.util.*;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class LoginAction {
    public static void execute(WebDriver driver,String userName,String passWord,String status,String rembPwd) throws Exception{
        Log.info("访问测试网址"+Constant.URL);
        driver.get(Constant.URL);
        final LoginPage loginPage = new LoginPage(driver);//把登录首页的元素集合类new出来
//
//        Log.info("在登录页面的用户名输入框输入" + userName);
        loginPage.userName().sendKeys(userName);
//        Log.info("在登录页面的密码输入框输入"+passWord);
        loginPage.password().sendKeys(passWord);
//        Log.info("在登录页面的状态框选择"+Constant.STATUS+"状态");
        SelectSigleBox.chooseOption(driver, loginPage.status(), status);

//        Log.info("选择是否记住账号密码: "+rembPwd);
        if(rembPwd.equals("是")){
            loginPage.rembPwd().click();
        }

//        Log.info("点击登录页面的登录按钮");
        loginPage.loginButton().click();
        Thread.sleep(1000);

        if(driver.getPageSource().contains("账号不存在！")||driver.getPageSource().contains("登录失败,用户名或密码错误！")){
            new GetScreenshot(driver,"账号不存在");
            Log.info("账号不存在或者用户名密码有误");
        }
        else if(driver.getPageSource().contains("登录失败,您已连续输入错误5次,账户已锁定,请联系管理员解锁，或30分钟后再试!")){
            Log.error("登录失败,您已连续输入错误5次,账户已锁定,请联系管理员解锁，或30分钟后再试!");
            Assert.fail("登录失败,您已连续输入错误5次,账户已锁定,请联系管理员解锁，或30分钟后再试!");
            new GetScreenshot(driver,"账户已锁定");
        }
        else if(driver.getPageSource().contains("当前用户已登录！")){
            Log.info("当前用户已登录");
//            new GetScreenshot(driver,"当前用户已登录");
            loginPage.loginButton().click();
            Thread.sleep(1000);
            DriverWait.findElement(driver, new HomePage(driver).LogoutPhoto());
        }
        else {
            DriverWait.findElement(driver, new HomePage(driver).LogoutPhoto());
        }

    }
}
