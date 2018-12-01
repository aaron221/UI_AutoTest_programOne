package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/15
 */
public class PhoSerNumManage {
    public WebDriver driver;
    @BeforeSuite
    public void beforeSuite(){
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver, "登录失败");
            Log.error("登录失败");
        }
    }

    @AfterSuite
    public void afterSuite(){
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }

    @BeforeClass
    public void beforeClass()throws Exception{
        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
        Thread.sleep(1000);
    }

    @AfterClass
    public void afterClass()throws Exception{
        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
        Thread.sleep(1000);
    }

    @BeforeMethod
    public void beforeMethod(){
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "电话服务号管理"));//下拉拖动条至元素与拖页面顶端对齐
            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"电话服务号管理")).click();//点击电话服务号管理链接
        } catch (Exception e) {
            new GetScreenshot(driver,"进入电话服务号管理链接失败");
            Log.error("进入电话服务号管理列表失败");
            Assert.fail("进入电话服务号列表失败");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(){
        driver = driver.switchTo().defaultContent();
    }

    @Test(priority = 0)
    public void phoSeriveNumAddDel()throws Exception{
        String addPhoName = "12345678";
        Log.startTestCase("新增电话服务号用例");
        try {
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"fwh\"]/p/a/i"))).click();//点击新增按钮
            DriverWait.findElement(driver,driver.findElement(By.id("relayNum"))).sendKeys(addPhoName);//输入服务号
            DriverWait.findElement(driver,driver.findElement(By.xpath("//input[@id='relayNum']/parent::div/following-sibling::div/button[1]"))).click();//点击保存按钮
        } catch (Exception e) {
            new GetScreenshot(driver,"新增电话服务号失败");
            Log.error("新增电话服务号失败");
            Assert.fail();
            e.printStackTrace();
        }

        int listsize0 = 0;
        int listsize = 0;
        try {
            //判断是否新建成功
            listsize0 = GetEleList.getEleList(DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"fwh\"]/nav/ul"))), "li").size();
            WebElement lastpagebtn = GetEleList.getEleList(driver.findElement(By.xpath("//div[@id=\"fwh\"]/nav/ul")), "li").get(listsize0-1);
            if(DriverWait.findElement(driver,lastpagebtn.findElement(By.tagName("a"))).isEnabled()){
                DriverWait.findElement(driver,lastpagebtn.findElement(By.tagName("a"))).click();//点击尾页按钮
            }
            //获取刚刚创建的服务号名称-尾页列表页面最后一个
            listsize = GetEleList.getEleList(driver.findElement(By.xpath
                    ("//div[@id=\"fwh\"]/div/table/tbody")), "tr").size();
            String phoName = DriverWait.findElement(driver, GetEleList.getEleList(driver.findElement(By.xpath
                    ("//div[@id=\"fwh\"]/div/table/tbody")), "tr").get(listsize-1)).getText();
            if(phoName.contains(addPhoName)){
                Log.info("新建成功");
            }else {
                new GetScreenshot(driver,"新建电话服务号失败");
                Log.error("新建电话服务号失败");
                Assert.fail("新建电话服务号失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新建电话服务号失败");
            Log.error("新建电话服务号失败");
            Assert.fail("新建电话服务号失败");
            e.printStackTrace();
        }
        Log.endTestCase("新增电话服务号用例");

        Log.startTestCase("编辑电话服务号用例");
        String editPhoName = null;

        try {
            //对刚刚创建的电话服务号进行编辑操作
            editPhoName = "87654321";
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"fwh\"]/div/table/tbody/tr["+listsize+"]/td[6]/button[1]"))).click();//点击编辑按钮
            DriverWait.findElement(driver,driver.findElement(By.id("relayNum"))).clear();
            DriverWait.findElement(driver,driver.findElement(By.id("relayNum"))).sendKeys(editPhoName);//输入服务号
            DriverWait.findElement(driver,driver.findElement(By.xpath("//input[@id='relayNum']/parent::div/following-sibling::div/button[1]"))).click();//点击保存按钮

            //判断是否修改成功
            WebElement elementedit = DriverWait.findElement(driver, GetEleList.getEleList(driver.findElement(By.xpath("//div[@id=\"fwh\"]/nav/ul")), "li").get(listsize0 - 1));
            elementedit.click();//再次点击尾页按钮

            if(driver.getPageSource().contains(editPhoName)){
                Log.info("修改电话服务号成功");
            }else {
                new GetScreenshot(driver,"修改电话服务号失败");
                Log.error("修改电话服务号失败");
                Assert.fail("修改电话服务号失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改电话服务号失败");
            Log.error("修改电话服务号失败");
            Assert.fail("修改电话服务号失败");
            e.printStackTrace();
        }

        Log.endTestCase("编辑电话服务号用例");

        Log.startTestCase("删除电话服务号用例");

        try {
            //对刚刚创建的电话服务号进行删除操作
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"fwh\"]/div/table/tbody/tr["+listsize+"]/td[6]/button[2]"))).click();
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"rm\"]/div[2]/div/div[3]/button[1]"))).click();//确认删除

            //判断是否删除成功
            listsize0 = GetEleList.getEleList(DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"fwh\"]/nav/ul"))), "li").size();
            WebElement elementdel = DriverWait.findElement(driver, GetEleList.getEleList(driver.findElement(By.xpath("//div[@id=\"fwh\"]/nav/ul")), "li").get(listsize0 - 1));
            elementdel.click();//再次点击尾页按钮

            if(driver.getPageSource().contains(editPhoName)){
                new GetScreenshot(driver,"删除电话服务号失败");
                Log.error("删除电话服务号失败");
                Assert.fail("删除电话服务号失败");
            }else {
//                Log.info("删除成功");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"删除电话服务号失败");
            Log.error("删除电话服务号失败");
            Assert.fail("删除电话服务号失败");
            e.printStackTrace();
        }
        Log.endTestCase("删除电话服务号用例");
    }

    @Test(priority = 1)
    public void phoExtAddDelEdit(){
        try {
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/div/ul/li[2]"))).click();//点击分机号页签
        } catch (Exception e) {
            new GetScreenshot(driver,"进入分机号管理列表失败");
            Log.error("进入分机号管理列表失败");
            Assert.fail("进入分机号管理列表失败");
            e.printStackTrace();
        }

        Log.startTestCase("新增分机号用例");

        String addExtName = "9999";
        try {
            driver = driver.switchTo().frame("userDetailIframe");
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@class='panel-btns']/a"))).click();//点击新增按钮
            DriverWait.findElement(driver,driver.findElement(By.id("phoneNum"))).sendKeys(addExtName);//输入分机号
            SelectSigleBox.chooseOption(driver,driver.findElement(By.id("hostTelStr")),1);//选择第一个接入主号选项
            SelectSigleBox.chooseOption(driver,driver.findElement(By.id("numStr")),1);//选择第一个服务号码选项
            DriverWait.findElement(driver,driver.findElement(By.id("btn_save"))).click();//点击保存按钮

            //判断是否新建成功
            Thread.sleep(1000);//等待页面刷新动作的完成
            String extname = DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[3]"))).getText();
            if(extname.equals(addExtName)){
                Log.info("新建分机号成功");
            }else {
                new GetScreenshot(driver,"新建分机号失败");
                Log.error("新建分机号失败");
                Assert.fail("新建分机号失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新建分机号失败");
            Log.error("新建分机号失败");
            Assert.fail("新建分机号失败");
            e.printStackTrace();
        }

        Log.endTestCase("新增分机号用例");

        Log.startTestCase("修改分机号用例");
        String editName = "8888";
        try {
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[6]/a[1]"))).click();//点击编辑按钮
            DriverWait.findElement(driver,driver.findElement(By.id("phoneNum"))).clear();
            driver.findElement(By.id("phoneNum")).sendKeys(editName);//输入分机号
            SelectSigleBox.chooseOption(driver,driver.findElement(By.id("hostTelStr")),2);//选择第二个接入主号选项
            SelectSigleBox.chooseOption(driver,driver.findElement(By.id("numStr")),1);//选择第一个服务号码选项
            DriverWait.findElement(driver,driver.findElement(By.id("btn_save"))).click();//点击保存按钮

            //判断是否修改成功
            Thread.sleep(1000);//等待页面刷新动作的完成
            String editExtname = DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[3]"))).getText();
            if(editExtname.equals(editName)){
                Log.info("修改分机号成功");
            }else {
                new GetScreenshot(driver,"修改分机号失败");
                Log.error("修改分机号失败");
                Assert.fail("修改分机号失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改分机号失败");
            Log.error("修改分机号失败");
            Assert.fail("修改分机号失败");
            e.printStackTrace();
        }

        Log.endTestCase("修改分机号用例");

        //删除分机号用例
        Log.startTestCase("删除分机号用例");
        try {
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[6]/a[2]"))).click();//点击删除按钮
            DriverWait.findElement(driver,driver.findElement(By.xpath("//input[@id='delId']/following-sibling::button[1]"))).click();//点击确认删除按钮

            //判断是否删除成功
            Thread.sleep(1000);//等待页面刷新动作的完成
            String delExtname = DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[2]/table/tbody/tr[2]/td[3]"))).getText();
            if(!delExtname.equals(editName)){
                Log.info("删除分机号成功");
            }else {
                new GetScreenshot(driver,"删除分机号失败");
                Log.error("删除分机号失败");
                Assert.fail("删除分机号失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            new GetScreenshot(driver,"删除分机号失败");
            Log.error("删除分机号失败");
            Assert.fail("删除分机号失败");
        }
        Log.endTestCase("删除分机号用例");

    }

}
