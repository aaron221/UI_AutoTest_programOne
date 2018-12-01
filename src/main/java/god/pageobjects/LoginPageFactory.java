package god.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPageFactory {
    //可以通过pagefactory类给测试类提供待操作的元素
    @FindBy(id="logonNumber")
    public static WebElement elementUserName;

    @FindBy(id="password")
    public static WebElement elementPassword;

    public LoginPageFactory(WebDriver driver){
        PageFactory.initElements(driver,this);
    }
}
