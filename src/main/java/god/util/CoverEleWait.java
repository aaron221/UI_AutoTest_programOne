package god.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by aaron on 2018/10/29
 * 本类方法是为了获取那些隐藏的被遮住的元素。
 */
public class CoverEleWait {
    public static WebElement getHideEle(WebDriver driver, By by) {
        int i = 0;
        WebElement element = null;
        while (i < 10) {
            try {
                element = driver.findElement(by);
                if (element.isDisplayed()) {
                    element = driver.findElement(by);
                    break;
                }
                Thread.currentThread().sleep(300);
            } catch (Exception e) {
                try {
                    Thread.currentThread().sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            i++;
        }
        return element;
    }

    public static WebElement getHideEle(WebDriver driver, WebElement element) {
        int i = 0;
        while (i < 10) {
            try {
                if (element.isDisplayed()) {
                    break;
                }
                Thread.currentThread().sleep(300);
            } catch (Exception e) {
                try {
                    Thread.currentThread().sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
            i++;
        }
        return element;
    }

    public static WebDriver getDriver(WebDriver driver, By by) {
        int i = 0;
        while (i < 10) {
            try {
                if (driver.findElement(by).isDisplayed()) {
                    break;
                }
                Thread.currentThread().sleep(300);
            } catch (Exception e) {
                try {
                    Thread.currentThread().sleep(300);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            i++;
        }
        return driver;
    }

    public static WebDriver getDriver(WebDriver driver, WebElement element) {
        int i = 0;
        while (i < 10) {
            try {
                if (element.isDisplayed()) {
                    break;
                }
                Thread.currentThread().sleep(300);
            } catch (Exception e) {
                try {
                    Thread.currentThread().sleep(500);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }

            i++;
        }
        return driver;
    }
}
