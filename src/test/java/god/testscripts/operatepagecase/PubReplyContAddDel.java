package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
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
 * Created by aaron on 2018/10/30
 */
public class PubReplyContAddDel {
    public WebDriver driver;

    @BeforeClass
    public void BeforeClass() throws Exception{
        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
    }

    @BeforeMethod
    public void beforeMethod(){
        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        try {
            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
        } catch (Exception e) {
            new GetScreenshot(driver,"登录失败");
            Log.error("登录失败");
        }

        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "运营")).click();//点击运营链接
            Thread.sleep(500);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "公共回复语"));
            MenuNameEleLoc.getMenuEle(driver, "公共回复语").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//main[@class='page-body']/ul/li[2]/a"))).click();//点击记录列表
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"jllist\"]/div/a[2]")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入公共回复语记录页面失败");
            Log.error("进入公共回复语记录页面失败");
            e.printStackTrace();
            Assert.fail("进入公共回复语记录页面失败");
        }
    }

    @Test
    public void TestCaseName(){
        String firstdep = null;
        String firstlable = null;
        String changecontent = null;
        String changelable = null;
        String changedep = null;
        try {
            for (int i = 1; i <= 2; i++) {
                if(i==1){
                    //进行新建操作
                    Log.startTestCase("新增公共回复语标签内容用例");
                    driver.findElement(By.xpath("//div[@id=\"jllist\"]/div/a[2]")).click();//点击新建按钮
                    Thread.sleep(500);
                    DriverWait.findElement(driver, By.xpath("//form[@id='publicRevertAddForm']/following-sibling::div/button[1]"));
                    firstdep = EditEle.getNumSelectedEle(driver,By.id("organ_add"),1);
                    SelectSigleBox.chooseOption(driver, By.id("organ_add"),1);//选择第二个选项的组织机构
                    firstlable = EditEle.getNumSelectedEle(driver,By.id("tabId_add"),1);
                    SelectSigleBox.chooseOption(driver, By.id("tabId_add"),1);//选择第二个选项的页签
                    driver.findElement(By.id("content_add")).sendKeys("这是标签内容消息");
                    driver.findElement(By.xpath("//form[@id='publicRevertAddForm']/following-sibling::div/button[1]")).click();//点击保存按钮
                }else {
                    Log.startTestCase("删除公共回复语标签内容用例");
                    //进行删除操作
                    DriverWait.findElement(driver, By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[6]/button[2]")).click();//点击刚刚新建的数据的删除按钮
                    Thread.sleep(500);
                    DriverWait.findElement(driver,By.xpath("//div[@id=\"modalDel2\"]/div[2]/div/div[3]/button[1]")).click();//点击确认删除按钮
                }

                //判断是否新建删除成功
                DriverWait.findElement(driver,By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[5]"));
                changecontent = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[2]")).getText();
                changelable = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[4]")).getText();
                changedep = driver.findElement(By.xpath("//div[@id=\"jllist\"]/table/tbody/tr[2]/td[5]")).getText();

                if(i==1&&changecontent.equals("这是标签内容消息")&&firstdep.contains(changedep)&&firstlable.equals(changelable)){
                    Log.info("新增公共回复语标签内容成功");
                    Log.endTestCase("新增公共回复语标签内容用例");
                }else if(i==2&&!changecontent.equals("这是标签内容消息")&&!firstdep.contains(changedep)&&!firstlable.equals(changelable)){
                    Log.info("删除公共回复语标签内容成功");
                    Log.endTestCase("删除公共回复语标签内容用例");
                }else {
                    Log.error("新增或删除公共回复语标签内容成功");
                    Assert.fail();
                }

            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新增删除公共回复语过程中发生了错误");
            Log.error("新增删除公共回复语过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
    }

    @AfterMethod
    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
    }
}
