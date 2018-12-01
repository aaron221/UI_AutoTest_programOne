package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/26
 */
public class PersReplyLabAddDel {
    public WebDriver driver;

    @BeforeClass
    public void BeforeClass() throws Exception {
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }

    @BeforeMethod
    public void beforeMethod() {
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver, "登录失败");
            Log.error("登录失败");
        }

        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "运营")).click();//点击运营链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "个人回复语"));
            MenuNameEleLoc.getMenuEle(driver, "个人回复语").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(driver, driver.findElement(By.xpath("//h4[@class=\"panel-title\"]/button")));
            driver.findElement(By.xpath("//h4[@class=\"panel-title\"]/button")).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入个人回复语页面失败");
            Log.error("进入个人回复语页面失败");
            Assert.fail("进入个人回复语页面失败");
            e.printStackTrace();
        }

    }

    @Test
    public void persAplayAddDel() {
        List<WebElement> listreply = null;
        try {
            Boolean flag = false;
            Log.startTestCase("新增个人回复语标签用例");
            driver.findElement(By.xpath("//h4[@class=\"panel-title\"]/button")).click();//点击新建按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,By.xpath("//form[@id='privateRevertTabAddForm']/following-sibling::div/button[2]")).isDisplayed();
            driver.findElement(By.id("tabName_add")).sendKeys("页签名称1");
            driver.findElement(By.xpath("//form[@id='privateRevertTabAddForm']/following-sibling::div/button[1]")).click();//点击保存按钮
            Thread.sleep(1000);

            //判断是否新建成功
            listreply = driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody")).findElements(By.tagName("tr"));
            WebElement element = driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody/tr["+(listreply.size()+1)+"]/td[1]"));
            if(element.getText().equals("页签名称1")){
                Log.info("新建个人回复语标签成功");
            }else {
                Log.error("新建个人回复语标签失败");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"新增个人回复语标签过程中发生了错误");
            Log.error("新增个人回复语标签过程中发生了错误");
            e.printStackTrace();
        }
        Log.endTestCase("新增个人回复语标签用例");

        Log.startTestCase("删除个人回复语标签用例");

        try {
            DragScrollBar.dragToBottom(driver);
            WebElement element = driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody/tr[" + (listreply.size() + 1) + "]/td[2]/button[2]"));
            element.click();//点击删除按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver, By.xpath("//div[@id=\"modalDel\"]/div[2]/div/div[3]/button[1]")).click();//点击确认删除按钮
            Thread.sleep(1000);

            List<WebElement> listreply1 = driver.findElement(By.xpath("//div[@id=\"personalPane\"]/section[1]/div/table/tbody")).findElements(By.tagName("tr"));
            //判断是否删除成功
            if(listreply.size()!=listreply1.size()){
                Log.info("删除个人回复标签成功");
            }else {
                Log.error("删除个人回复标签失败");
                Assert.fail();
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"删除个人回复标签过程中发生了错误");
            Log.error("删除个人回复标签过程中发生了错误");
            e.printStackTrace();
        }
        Log.endTestCase("删除个人回复语标签用例");

    }


    @AfterMethod
    public void afterMethod() {
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }
}
