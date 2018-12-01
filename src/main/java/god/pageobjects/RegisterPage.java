package god.pageobjects;

import god.util.Constant;
import god.util.ObjectMap;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thomas on 2018/10/8.
 */
public class RegisterPage {
    private WebElement element=null;
    private WebDriver driver;
    private ObjectMap objectMap = new ObjectMap(Constant.ELE_CONF_FILE);

    public RegisterPage(WebDriver driver){
        this.driver = driver;
    }

    public WebElement phoneNum(){
        element = driver.findElement(objectMap.getLocator("registerPage.phoneNum"));
        return element;
    }

    public WebElement photoCode(){
        element = driver.findElement(objectMap.getLocator("registerPage.photoCode"));
        return element;
    }

    public WebElement getCode(){
        element = driver.findElement(objectMap.getLocator("registerPage.getCode"));
        return element;
    }

    public WebElement phoneCode(){
        element = driver.findElement(objectMap.getLocator("registerPage.phoneCode"));
        return element;
    }

    public WebElement agreeRegister(){
        element = driver.findElement(objectMap.getLocator("registerPage.agreeRegister"));
        return element;
    }

    public WebElement directLogin(){
        element = driver.findElement(objectMap.getLocator("registerPage.directLogin"));
        return element;
    }

    public WebElement serviceTerms(){
        element = driver.findElement(objectMap.getLocator("registerPage.serviceTerms"));
        return element;
    }

    public WebElement companyName(){
        element = driver.findElement(objectMap.getLocator("registerPage.companyName"));
        return element;
    }

}
