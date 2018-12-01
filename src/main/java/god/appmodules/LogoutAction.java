package god.appmodules;

import god.pageobjects.HomePage;
import god.util.Constant;
import god.util.DriverWait;
import god.util.ObjectMap;
import org.openqa.selenium.WebDriver;


public class LogoutAction {
    private static ObjectMap objectMap = new ObjectMap(Constant.ELE_CONF_FILE);
    public static void excut(WebDriver driver) throws Exception{
        HomePage homePage = new HomePage(driver);
        DriverWait.findElement(driver,homePage.LogoutPhoto()).click();

        Thread.sleep(1000);
        DriverWait.findElement(driver,homePage.logout()).click();

        //Thread.sleep(1000);
        //方法一 直接使用显示等待确认退出按钮元素
        //(new WebDriverWait(driver,5)).until(new ExpectedCondition<WebElement>() {
        //    @Override
        //    public WebElement apply(WebDriver driver){
        //            return driver.findElement(objectMap.getLocator("126mai.HomePage.logoutconfirm"));
        //    }
        //}).click();

        //方法二
        Thread.sleep(1000);
        DriverWait.findElement(driver,homePage.LogoutConfirm()).click();//调用DriverWait类的findElement方法，显式查找等待确认退出按钮


        //方法三
        //Thread.sleep(1000);
        //homePage.LogoutConfirm().click();//调用homePage类中的logoutConfirm方法，获得该元素，然后点击该元素按钮-确认退出

    }

}
