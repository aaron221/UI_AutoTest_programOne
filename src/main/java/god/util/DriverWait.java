package god.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverWait {
    public static WebElement findElement(WebDriver driver, final WebElement element) {
        WebElement webElement = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if (element.isDisplayed()) {
                    return element;
                } else {
                    return null;
                }
            }
        });
        return webElement;
    }

    public static Boolean judgeExist(WebDriver driver, final String containsKeys) {
        Boolean temp = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                String simpleContent = driver.getPageSource();
                simpleContent = simpleContent.replaceAll("[\\/|\n|\t|<html.*>|<script.*>|<div.*>|<a.*>|<body.*>|<p.*>|<input.*>|<!--.*-->]", "");
                if (simpleContent.contains(containsKeys)) {
                    return true;
                } else {
                    return false;
                }
            }
        });
        return temp;
    }

    public static WebElement findElement(WebDriver driver, final By by) {
        WebElement element = (new WebDriverWait(driver, 5)).until(new ExpectedCondition<WebElement>() {
            @Override
            public WebElement apply(WebDriver driver) {
                if(driver.findElement(by).isDisplayed())
                return driver.findElement(by);
                else
                    return null;
            }
        });
        return element;
    }

    public static WebElement eleIsToBeclicked(WebDriver driver,final By by) {
        WebElement element = null;
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(by));
            element =  driver.findElement(by);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element;
    }

    public static WebElement eleIsToBeclicked(WebDriver driver,final WebElement element) {
        WebElement element1 = null;
        try {
            new WebDriverWait(driver,5).until(ExpectedConditions.elementToBeClickable(element));
            element1 = element;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return element1;
    }



}
