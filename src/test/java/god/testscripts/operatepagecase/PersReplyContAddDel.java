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
public class PersReplyContAddDel {
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
    public void persReplyContentAddDel() {
        int listsize = 0;
        try {
            for (int i = 1; i <= 2; i++) {
                if (i == 1) {
                    Log.startTestCase("新建个人回复语标签内容用例");
                    driver.findElement(By.xpath("//div[@class=\"panel-btns\"]/a")).click();//点击新建按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"modalNew\"]/div[2]/div/div[2]")));
                    SelectSigleBox.chooseOption(driver, By.id("tabId_add"), 1);//选择第二个选项-第一个选项为请选择
                    driver.findElement(By.id("content_add")).sendKeys("这是个人回复标签内容");
                    driver.findElement(By.xpath("//form[@id=\"privateRevertAddForm\"]/following-sibling::div[1]/button[1]")).click();//点击保存按钮
                    Thread.sleep(3000);

                    DragScrollBar.dragToBottom(driver);
                    List<WebElement> listdata = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody")).findElements(By.tagName("tr"));
                    listsize = listdata.size();
                } else {
                    Log.startTestCase("删除个人回复语标签内容用例");

                    //获取列表数据行数
                    driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr["+listsize+"]/td[4]/button[2]")).click();//点击最后一条的数据进行删除操作
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalDel2\"]/div[2]/div/div[3]/button[1]"))).click();//点击确认删除按钮
                    Thread.sleep(3000);

                    DragScrollBar.dragToBottom(driver);
                    List<WebElement> listdata = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody")).findElements(By.tagName("tr"));
                    listsize = listdata.size();
                }
                //判断是否新增删除成功

                String content = driver.findElement(By.xpath("//form[@id='actionForm']/following-sibling::div/table/tbody/tr["+listsize+"]/td[1]")).getAttribute("title");
                if (i == 1 && content.equals("这是个人回复标签内容")) {
                    Log.info("新增个人回复语标签内容成功");
                    Log.endTestCase("新建个人回复语标签内容用例");
                } else if (i == 2 && !content.equals("这是个人回复标签内容")) {
                    Log.info("删除个人回复语标签内容成功");
                    Log.endTestCase("删除个人回复语标签内容用例");
                } else {
                    new GetScreenshot(driver, "新增或删除个人回复语标签内容失败");
                    Log.error("新增或删除个人回复语标签内容失败");
                    Assert.fail();
                }
            }
        }catch (Exception e){
            new GetScreenshot(driver,"新增修改个人回复语标签内容过程中发生了错误");
            Log.error("新增修改个人回复语标签内容过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
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
