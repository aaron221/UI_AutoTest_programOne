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
public class ReplyModelAddDel {

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
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "留言管理"));
            MenuNameEleLoc.getMenuEle(driver, "留言管理").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/main/ul/li[3]"))).click();//点击回复模板链接
            Thread.sleep(500);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"commentTpl\"]/div[1]/button")));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入留言类型页面失败");
            Log.error("进入留言类型页面失败");
            e.printStackTrace();
            Assert.fail("进入留言类型页面失败");
        }
    }
    @Test
    public void replyModelAddDel(){
        String changetype = null;
        String sencenddep = null;
        String updatedep = null;
        String updatetitle = null;
        String updatecontent = null;
        Boolean isselected = null;
        try {
            for (int i = 1; i <= 2; i++) {
                if(i==1){
                    Log.startTestCase("新建回复模板用例");
                    driver.findElement(By.xpath("//div[@id=\"commentTpl\"]/div[1]/button")).click();//点击新建按钮
                    DriverWait.findElement(driver,By.id("saveTemplateBtn"));

                    sencenddep = EditEle.getNumSelectedEle(driver, By.id("saveOrganId"),1);
                    SelectSigleBox.chooseOption(driver, By.id("saveOrganId"),1);
                    driver.findElement(By.id("saveTitle")).sendKeys("这是标题");
                    driver.findElement(By.id("saveState")).click();//点击状态复选框，选择启用
                    driver.findElement(By.id("saveContent")).sendKeys("这是模板内容");
                    driver.findElement(By.id("saveTemplateBtn")).click();//点击确认按钮
                    Thread.sleep(1000);

                    //获取修改后的各个字段的值
                    DriverWait.findElement(driver, By.xpath("//table[@id=\"tableTpl\"]/tbody/tr[1]/td[6]/botton[2]")).click();//点击编辑按钮
                    DriverWait.findElement(driver,By.id("saveTemplateBtn"));

                    updatedep = EditEle.getInitSelectedEle(driver, By.id("saveOrganId"));
                    updatetitle = driver.findElement(By.id("saveTitle")).getAttribute("value");
                    updatecontent = driver.findElement(By.id("saveContent")).getAttribute("value");
                    isselected = driver.findElement(By.id("saveState")).isSelected();

                    driver.findElement(By.id("saveTemplateBtn")).click();//点击确认按钮
                    Thread.sleep(1000);
                }else {
                    Log.startTestCase("删除回复模板用例");
                    DriverWait.findElement(driver, By.xpath("//table[@id=\"tableTpl\"]/tbody/tr[1]/td[6]/botton[3]")).click();//点击删除按钮
                    DriverWait.findElement(driver,By.id("delTemplateBtn")).click();//点击确认删除按钮
                    Thread.sleep(1000);

                }
                //判断是否新建删除成功
                changetype = DriverWait.findElement(driver,driver.findElement(By.xpath("//table[@id=\"tableTpl\"]/tbody/tr[1]/td[2]"))).getText();
                if(i==1&&updatedep.equals(sencenddep)&&updatetitle.equals("这是标题")&&updatecontent.equals("这是模板内容")&&isselected){
                    Log.info("新增回复模板成功");
                    Log.endTestCase("新增回复模板用例");
                }else if(i==2&&!changetype.equals(updatedep)){
                    Log.info("删除回复模板成功");
                    Log.endTestCase("删除回复模板用例");
                }else {
                    Log.error("新增或删除回复模板失败");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新增删除留言类型过程中发生了错误");
            Log.error("新增删除留言类型过程中发生了错误");
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
