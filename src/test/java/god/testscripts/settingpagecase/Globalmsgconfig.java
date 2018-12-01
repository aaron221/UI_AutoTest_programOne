package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.testscripts.OneDriverSuite;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/26
 */
public class Globalmsgconfig {
//    public WebDriver OneDriverSuite.driver;
//
//    @BeforeSuite
//    public void beforeSuite() throws Exception {
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//        OneDriverSuite.driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        OneDriverSuite.driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//        try {
//            LoginAction.execute(OneDriverSuite.driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(OneDriverSuite.driver, "登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @AfterSuite
//    public void afterSuite(){
//        try {
//            LogoutAction.excut(OneDriverSuite.driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(OneDriverSuite.driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        OneDriverSuite.driver.quit();
//    }

    @BeforeClass
    public void beforeClass(){
    }

    @AfterClass
    public void afterClass(){

    }

    @BeforeMethod
    public void beforeMethod(){
        try {
            DriverWait.findElement(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(OneDriverSuite.driver, MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "全局消息配置"));
            MenuNameEleLoc.getMenuEle(OneDriverSuite.driver, "全局消息配置").click();//点击人工服务设置链接
            OneDriverSuite.driver = OneDriverSuite.driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DragScrollBar.dragIntoEleTop(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.xpath("//table[@class=\"panel-table\"]/tbody/tr[13]/td[2]/button")));
            OneDriverSuite.driver.findElement(By.xpath("//table[@class=\"panel-table\"]/tbody/tr[13]/td[2]/button")).isDisplayed();
        } catch (Exception e) {
            new GetScreenshot(OneDriverSuite.driver, "进入全局消息配置页面失败");
            Log.error("进入全局消息配置页面失败");
            Assert.fail("进入全局消息配置页面失败");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(){
        OneDriverSuite.driver = OneDriverSuite.driver.switchTo().defaultContent();
    }

    @Test
    public void globalMsgConfig(){
          Boolean flag = true;
        try {
            //修改选择配置渠道字段用例-由于此处选择后，整个driver就会发生改变的，所以单独弄出来
            Log.startTestCase("修改配置渠道字段用例");
            SelectSigleBox.chooseOption(OneDriverSuite.driver, OneDriverSuite.driver.findElement(By.id("imType")), 1);
            Thread.sleep(1000);
            OneDriverSuite.driver.findElement(By.xpath("//table[@class=\"panel-table\"]/tbody/tr[13]/td[2]/button")).click();
            Thread.sleep(1000);
            if(OneDriverSuite.driver.findElement(By.id("imType")).findElement(By.xpath("option[2]")).isSelected()){
                Log.info("修改配置渠道字段成功");
            }else {
                Log.error("修改配置渠道字段成功");
                flag = false;
            }
            //还原回去
            SelectSigleBox.chooseOption(OneDriverSuite.driver,OneDriverSuite.driver.findElement(By.id("imType")),0);
            OneDriverSuite.driver.findElement(By.xpath("//table[@class=\"panel-table\"]/tbody/tr[13]/td[2]/button")).click();
            Thread.sleep(1000);
            DragScrollBar.dragToTop(OneDriverSuite.driver);
            Thread.sleep(1000);
            Log.endTestCase("修改配置渠道字段用例");

            By saveby = By.xpath("//table[@class=\"panel-table\"]/tbody/tr[13]/td[2]/button");
            List<WebElement> listele = OneDriverSuite.driver.findElements(By.tagName("select"));//获取当前页面所有的select标签元素
            String[] casename = {"选择配置渠道","连续超时多久关闭会话","客服超时时间","用户首次超时时间","客服超时提醒时间"
                    ,"关闭会话转向机器人","会话超时转向机器人","是否允许文件传输","用户接入后是否推送欢迎语"};
            String[] casename1 = {"配置公司名称","用户接入时推送的提示语","客服主动拉取用户时推送的提示语"
                    ,"当客服超时时推送给用户的提示语","当客服超提醒时间推送给客服的提示语","用户接入超时后提醒客服的提示语","用户超时推送的提醒提示语","用户继续超时后推送的提示语"};
            By[] listby = new By[casename1.length];
            listby[0] = By.id("attr-company");//第一个输入框配置公司名称字段By属性值

            //下面的for是为了把接下来的所有输入框的By属性给加到listby数组中去
            for (int i = 1; i < casename1.length; i++) {
                listby[i] = By.xpath("//td[@title='"+casename1[i]+"']/parent::tr/td[2]/textarea");
             }

            //下面的for是判断select单选框各个字段是否修改成功
            for (int i = 1; i < listele.size(); i++) {
                Log.startTestCase("修改"+casename[i]+"字段用例");
                if(EditEle.editSelEle(OneDriverSuite.driver, listele.get(i),saveby)){
                    Log.info("修改"+casename[i]+"字段成功");
                }else {
                    Log.error("修改"+casename[i]+"字段失败");
                    flag = false;
                }
                Log.endTestCase("修改"+casename[i-1]+"字段用例");
            }

            //下面for是为了判断input输入框是否修改成功
            for (int i = 0; i < casename1.length; i++) {
                Log.startTestCase("修改"+casename1[i]+"字段用例");
                if(EditEle.editInputEle(OneDriverSuite.driver, listby[i],casename1[i],saveby)){
                    Log.info("修改"+casename1[i]+"字段成功");
                }else {
                    Log.error("修改"+casename1[i]+"字段失败");
                    flag = false;
                }
                Log.endTestCase("修改"+casename1[i]+"字段用例");
            }
        } catch (Exception e) {
            new GetScreenshot(OneDriverSuite.driver,"修改全局消息配置页面各个字段过程中发生了错误");
            Log.error("修改全局消息配置页面各个字段过程中发生了错误");
            Assert.fail();
            e.printStackTrace();
        }

        if(flag){
            Log.info("修改全局消息配置页面各个字段成功");
        }else {
            Log.error("修改全局消息配置页面各个字段失败");
            Assert.fail();
        }

    }
}
