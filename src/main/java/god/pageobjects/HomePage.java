package god.pageobjects;

import god.util.Constant;
import god.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class HomePage {
    private WebElement element=null;
    private ObjectMap objectMap = new ObjectMap(Constant.ELE_CONF_FILE);

    private WebDriver driver;

    public HomePage(WebDriver driver){
        this.driver=driver;
    }

    //如果要在HomePage页面操作更多的链接或元素，可以根据需要自定义

    public WebElement logout(){
        element = driver.findElement(objectMap.getLocator("homePage.logout"));
        return element;
    }

    public WebElement LogoutPhoto(){
        element = driver.findElement(objectMap.getLocator("homePage.logoutphoto"));
        return element;
    }

    public WebElement LogoutConfirm(){
        element = driver.findElement(objectMap.getLocator("homePage.logoutconfirm"));
        return element;
    }

    public WebDriver getDriver() {
        return driver;
    }

}
