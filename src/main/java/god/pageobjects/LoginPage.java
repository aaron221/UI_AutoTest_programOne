package god.pageobjects;

import god.util.Constant;
import god.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebElement element = null;
    //指定页面元素定位表达式配置文件的绝对路径
    private ObjectMap objectMap = new ObjectMap(Constant.ELE_CONF_FILE);

    private WebDriver driver;


    public LoginPage(WebDriver driver){
        this.driver = driver;
    }
    //返回登录页面中的用户名输入框页面元素对象
    public WebElement userName() throws Exception{
        //使用objectMap类中的getLocator 方法获取配置文件中关于用户名的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("loginPage.username"));
        return element;
    }

    //返回登录页面中的密码输入框页面元素对象
    public WebElement password() throws Exception{
        //使用objectMap类中的getLocator 方法获取配置文件中关于用户名的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("loginPage.password"));
        return element;
    }

    //返回登录页面中的登录按钮页面元素对象
    public WebElement loginButton() throws Exception{
        //使用objectMap类中的getLocator 方法获取配置文件中关于用户名的定位方式和定位表达式
        element = driver.findElement(objectMap.getLocator("loginPage.loginbutton"));
        return element;
    }

    //返回登录页面中的登录状态元素
    public WebElement status(){
         element = driver.findElement(objectMap.getLocator("loginPage.status"));
         return element;
    }

    //返回登录页面中的是否记住密码元素
    public WebElement rembPwd(){
        element = driver.findElement(objectMap.getLocator("loginPage.rembPwd"));
        return element;
    }

    //返回登录页面中的注册新用户元素
    public WebElement regNewUser(){
        element = driver.findElement(objectMap.getLocator("loginPage.regNewUser"));
        return element;
    }

    //获取登录页面的driver
    public WebDriver getDriver() {
        return driver;
    }


}
