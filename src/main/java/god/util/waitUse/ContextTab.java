package god.util.waitUse;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.Select;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.Set;

public class ContextTab {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();


        //driver.get("http://u.im-cc.com");
        //driver.findElement(By.id("logonNumber")).sendKeys("1026@10064");
        //pressTabkey();//模拟tab键切换到密码输入框;
        //pressCtrlV("Aa_111111");//调用粘贴方法把密码粘贴到密码框中去
        //pressTabkey();
        ////选择下拉框中的某个选项
        //WebElement status = driver.switchTo().activeElement();
        //Select statusValue = new Select(status);
        //statusValue.selectByIndex(2);
        //Thread.sleep(4000);
        //
        //pressTabkey();
        //driver.switchTo().activeElement().click();//点击记住密码复选框
        //Thread.sleep(4000);
        //
        //pressTabkey();
        //driver.switchTo().activeElement().click();//点击登录按钮

        //操作浏览器的缓存Cookie
        driver.get("https://www.baidu.com");
        Cookie newCookie = new Cookie("cookiename","cookievalue");
        Set<Cookie> cookies  = driver.manage().getCookies();
        Thread.sleep(3000);
        System.out.println(String.format("Domain -> name -> value -> expiry -> path"));
        for(Cookie cookie : cookies){
            System.out.println(String.format("%s -> %s-> %s-> %s-> %s",cookie.getDomain(),
                    cookie.getName(),cookie.getValue(),cookie.getExpiry(),cookie.getPath()));
        }
        //删除cookie
        driver.manage().deleteCookieNamed("delPer");
        driver.manage().deleteCookie(newCookie);
        driver.manage().deleteAllCookies();
        Set<Cookie> cookies1 = driver.manage().getCookies();
        for(Cookie cookie : cookies1){
            System.out.println(cookie.getDomain()+"删除操作后"+cookie.getName());
        }


    }


    public static void pressCtrlV(String string){
        //模拟Ctrl+V进行粘贴操作
        StringSelection stringSelection = new StringSelection(string);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection,null);
        Robot robot = null;

        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

    }

    public static void pressTabkey(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        robot.keyPress(KeyEvent.VK_TAB);
        robot.keyRelease(KeyEvent.VK_TAB);
    }

    public static void pressEnter(){
        Robot robot = null;
        try {
            robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}
