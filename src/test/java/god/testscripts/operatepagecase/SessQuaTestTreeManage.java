package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
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
 * Created by aaron on 2018/11/1
 */
public class SessQuaTestTreeManage {
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
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "会话质检"));
            MenuNameEleLoc.getMenuEle(driver, "会话质检").click();//点击公共回复语设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(500);
            DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[1]/div[2]"))).click();//点击质检打分树列表链接
            Thread.sleep(500);
            DriverWait.eleIsToBeclicked(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div/div/ul[1]/li/span[2]/span[2]/button[1]/i"));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入会话质检打分树页面失败");
            Log.error("进入会话质检打分树页面失败");
            e.printStackTrace();
            Assert.fail("进入会话质检打分树页面失败");
        }
    }
    @Test
    public void SessQuaTestAddDel(){
        String nodename = null;
        String score = null;
        String isvalid = null;
        String nodetype = null;
        try {
            for (int i = 1; i <= 3; i++) {
                if(i==1){
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div/div/ul[1]/li/span[2]/span[2]/button[1]/i")).click();//点击新建按钮
                    Thread.sleep(1000);
                    DriverWait.findElement(driver,By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[3]/div/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[3]/div/div/input")).sendKeys("22");
                    DriverWait.findElement(driver,By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[5]/div/span")).click();//置为无效
                }
                else {
                    DriverWait.eleIsToBeclicked(driver, By.xpath("//span[contains(@title,'节点名称')]")).click();//点击新创建的这条数据
                    Thread.sleep(2000);
                    nodename = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[2]/div/div/input")).getAttribute("value");
                    isvalid = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[5]/div/span/span/span")).getText();
                    if(i==2){
                        score = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[3]/div/div/input")).getAttribute("value");
                        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[1]/div/div/div[1]/span[2]")).click();
                        Thread.sleep(1000);
                        DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[1]/div/div/div[2]/ul[2]/li[1]")).click();//修改节点为关键指标
                        Thread.sleep(1000);
                        DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[5]/div/span")).click();//置为有效
                    }else {
                        nodetype = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[1]/div/div/div[1]/span[2]")).getText();
                    }
                }
                    DriverWait.findElement(driver,By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[7]/div/button[1]/span"));
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[2]/div/div/input")).clear();
                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[2]/div/div/input")).sendKeys("节点名称"+i);

                    driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[2]/div/form/div[7]/div/button[1]/span")).click();//点击保存按钮
                    Thread.sleep(2000);

                if(i==1){
                    continue;
                }
                else if(i==2&&isvalid.equals("无效")&&score.equals("22")&&nodename.equals("节点名称1")){
                    Log.startTestCase("新增质检打分树节点用例");
                    Log.info("新建质检打分树节点成功");
                    Log.endTestCase("新增质打分树节点用例");
                }
                else if(i==3&&isvalid.equals("有效")&&nodetype.equals("关键指标")&&nodename.equals("节点名称2")){
                    Log.startTestCase("修改质打分树节点用例");
                    Log.info("修改质检打分树节点成功");
                    Log.endTestCase("修改质打分树节点用例");
                    }
               else {
                    new GetScreenshot(driver,"新增或修改质检打分树节点失败");
                    Log.error("新增或修改质检打分树节点失败");
                  Assert.fail();
                }
            }

            //对新建修改的节点进行删除操作
            List<WebElement> listtree = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div/div/ul[1]/li")).findElements(By.tagName("ul"));
            listtree.get(listtree.size()-1).findElement(By.xpath("li/span[2]/span[2]/button[2]")).click();//点击删除按钮
            Thread.sleep(2000);

            List<WebElement> listtree1 = DriverWait.findElement(driver, By.xpath("/html/body/div[1]/div[1]/div[2]/div[2]/div[1]/div/div/ul[1]/li")).findElements(By.tagName("ul"));
            String lastnodename = listtree1.get(listtree1.size()-1).findElement(By.xpath("li/span[2]/span[1]/span")).getAttribute("title");


            if(!lastnodename.equals("节点名称3")){
                Log.startTestCase("删除质检打分树节点用例");
                Log.info("删除节点成功");
                Log.endTestCase("删除质检打分树节点用例");
            }else {
                Log.error("删除节点失败");
                Assert.fail();
            }

        } catch (Exception e) {
            new GetScreenshot(driver,"新增修改删除质检打分树节点过程中发生了错误");
            Log.error("新增修改删除质检打分树节点过程中发生了错误");
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
