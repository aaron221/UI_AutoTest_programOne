package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/26
 */
public class PersReplyContEdit {
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
            DriverWait.findElement(driver, By.xpath("//div[@class=\"panel-btns\"]/a")).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(driver, "进入个人回复语页面失败");
            Log.error("进入个人回复语页面失败");
            Assert.fail("进入个人回复语页面失败");
            e.printStackTrace();
        }
    }
    @Test
    public void persReplyContentEdit() {
        Log.startTestCase("修改个人回复语标签内容用例");
        try {
            String firstoption = null;
            String initcontent = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[1]")).getAttribute("title");
            String initlable = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[3]")).getText();
            for (int i = 1; i <=2; i++) {
                driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[4]/button[1]")).click();//点击第一条的数据进行编辑操作
                Thread.sleep(1000);
                DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"modalEdit\"]/div[2]/div/div[2]")));
                if (i == 1) {
                    firstoption = driver.findElement(By.xpath("//select[@id='tabId_update']/option[2]")).getText();//获得第二个选项所属标签名
                    SelectSigleBox.chooseOption(driver, By.id("tabId_update"), 1);//选择第二个选项-第一个选项为请选择
                    driver.findElement(By.id("content_update")).clear();
                    driver.findElement(By.id("content_update")).sendKeys("这是个人回复标签内容");
                } else {
                    SelectSigleBox.chooseOption(driver, By.id("tabId_update"), initlable);//还原原先option值
                    driver.findElement(By.id("content_update")).clear();
                    driver.findElement(By.id("content_update")).sendKeys(initcontent);//还原原先内容
                }
                driver.findElement(By.xpath("//form[@id='privateRevertUpdateForm']/following-sibling::div/button")).click();//点击保存按钮
                Thread.sleep(3000);
                //判断是否修改成功
                DriverWait.findElement(driver, driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[3]")));
                String updatecontent = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[1]")).getAttribute("title");
                String updatelable = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr[2]/td[3]")).getText();

                if (i==1&&updatecontent.equals("这是个人回复标签内容") && updatelable.equals(firstoption)) {
                    Log.info("修改个人回复语标签内容成功");}
                else if (i==2&&updatecontent.equals(initcontent) && updatelable.equals(initlable)) {
                    Log.info("还原个人回复语标签内容成功");
                }else {
                    new GetScreenshot(driver, "修改或还原个人回复语标签内容失败");
                    Log.error("修改或还原个人回复语标签内容失败");
                    Log.error(SystemTime.getCurTime("initcontent")+initcontent);
                    Log.error(SystemTime.getCurTime("initlable")+initlable);
                    Assert.fail();
                }
                }
        }catch (Exception e){
            new GetScreenshot(driver,"修改还原个人回复语标签内容过程中发生了错误");
            Log.error("修改还原个人回复语标签内容过程中发生了错误");
            Assert.fail();
        }
        Log.endTestCase("修改个人回复语标签内容用例");
    }
    @AfterMethod
    public void afterMethod () {
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }
}
