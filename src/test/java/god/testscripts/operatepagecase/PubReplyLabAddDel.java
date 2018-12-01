package god.testscripts.operatepagecase;

import god.appmodules.LoginAction;
import god.util.*;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/29
 */
public class PubReplyLabAddDel {
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
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "公共回复语"));
            MenuNameEleLoc.getMenuEle(driver, "公共回复语").click();//点击人工服务设置链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"pagelist\"]/div/button")));//新建按钮
        } catch (Exception e) {
            new GetScreenshot(driver, "进入公共回复语页面失败");
            Log.error("进入公共回复语页面失败");
            Assert.fail("进入公共回复语页面失败");
            e.printStackTrace();
        }

    }

    @Test
    public void publicReplyLableAddDel(){
        List<WebElement> listele = new ArrayList<>();
        String updatelable = null;
        String updatedep = null;
        String thirddep = null;
        try {
            //新建操作
            for (int i = 1; i <= 2; i++) {
                if(i==1){
                    Log.startTestCase("新增公共回复语页签用例");
                    driver.findElement(By.xpath("//div[@id=\"pagelist\"]/div/button")).click();//点击新建按钮
                    DriverWait.findElement(driver, By.xpath("//form[@id='publicRevertTabAddForm']/following-sibling::div/button[1]"));thirddep = driver.findElement(By.xpath("//select[@id='orgId_add']/option[2]")).getText();
                    SelectSigleBox.chooseOption(driver, By.id("orgId_add"),1);//选择第二个option选项
                    driver.findElement(By.id("tabName_add")).sendKeys("这是页签名称");
                    driver.findElement(By.xpath("//form[@id='publicRevertTabAddForm']/following-sibling::div/button[1]")).click();//点击保存按钮
                    DragScrollBar.dragToBottom(driver);
                }else {
                    //删除操作
                    Log.startTestCase("删除公共回复语页签用例");
                    DragScrollBar.dragToBottom(driver);
                    DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody/tr[" + listele.size() + "]/td[3]/button[2]"))).click();//点击删除按钮
                    DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalDel\"]/div[2]/div/div[3]/button[1]"))).click();//确认删除按钮
                }
                //判断是否新增删除成功
                listele = DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"pagelist\"]/table/tbody"))).findElements(By.tagName("tr"));
            updatelable = listele.get(listele.size()-1).findElement(By.xpath("td[1]")).getText();
            updatedep = listele.get(listele.size()-1).findElement(By.xpath("td[2]")).getText();

            if(i==1&&updatelable.equals("这是页签名称")&&updatedep.equals(thirddep)){
                Log.info("新增公共回复语页签");
                Log.endTestCase("新增公共回复语页签用例");
            }else if(i==2&&!updatelable.equals("这是页签名称")&&!updatedep.equals(thirddep)){
                Log.info("删除公共回复语页签成功");
                Log.endTestCase("删除公共回复语页签用例");
            }else {
                Log.error("新增或删除公共回复页签失败");
                Assert.fail();
            }
          }
        }catch (Exception e){
            new GetScreenshot(driver,"新增删除公共回复语标签过程中发生了错误");
            Log.error("新增删除公共回复语标签过程中发生了错误");
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
