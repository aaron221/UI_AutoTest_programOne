package god.util.waitUse;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import javax.swing.*;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestApi {
    public static void visitRecentURL() throws InterruptedException {
        //String url1 = "http://u.im-cc.com";
        //String url2 = "http://www.baidu.com";
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        //driver.get(url2);
        //System.out.println("打开了百度网页");
        //driver.get(url1);
        //System.out.println("切换到了登陆网页");
        //driver.navigate().back();
        //System.out.println("退回到了百度网页");
        //driver.navigate().forward();
        //System.out.println("从上次网页访问前进到下一个网页");
        //driver.navigate().refresh();
        //System.out.println("刷新了当前页面");
        //String title = driver.getTitle();
        //System.out.println(title);
        //Assert.assertEquals(title,"云软云客服");
        //System.out.println(driver.getPageSource());
        //System.out.println(driver.getCurrentUrl());
        //WebElement inputbox = driver.findElement(By.id("kw"));
        //inputbox.sendKeys("即将被清除的内容");
        //Thread.sleep(3000);
        //inputbox.clear();

        //WebElement searchclick = driver.findElement(By.id("su"));
        //searchclick.click();
        ////对某个元素进行双击操作
        //Actions builder = new Actions(driver);
        //builder.doubleClick(inputbox).perform();
        ////对输入框内的元素全选操作
        //inputbox.sendKeys(Keys.CONTROL,"a");

        //操作单选下拉列表
        driver.get("http://u.im-cc.com");
        WebElement choosestatus = driver.findElement(By.id("status"));
        Select chooselist = new Select(choosestatus);
        //选择某个选项
        //chooselist.selectByIndex(0);
        //chooselist.selectByValue("5");
        //chooselist.selectByVisibleText("小休");
        //Assert.assertEquals(chooselist.getFirstSelectedOption().getText(),"在线");
        //System.out.println(chooselist.getFirstSelectedOption().getText());


        //检查单选列表的选项文字是否符合期望
        //List<String> expect_option = Arrays.asList(new String[]{"在线","示忙","小休","离线"});
        //List<String> actual_option = new ArrayList<String>();
        //for (WebElement option:chooselist.getOptions()) {
        //    actual_option.add(option.getText());
        //    System.out.println(option.getText());
        //}
        //Assert.assertEquals(expect_option.toArray(),actual_option.toArray());


    }

    public static void main(String[] args) throws Exception {
        visitRecentURL();
    }
}
