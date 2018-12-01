package god.testscripts.settingpagecase;

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
 * Created by aaron on 2018/10/19
 */
public class WebchatManage {
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
    }

    @Test
    public void webchatManage(){
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            Thread.sleep(1000);
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver,MenuNameEleLoc.getMenuEle(driver,"webchat"));
            MenuNameEleLoc.getMenuEle(driver,"webchat").click();//点击webchat链接
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(1000);
            DriverWait.findElement(driver, By.id("addBtn"));
        } catch (Exception e) {
            new GetScreenshot(driver, "进入webcaht列表页面失败");
            Log.error("进入webcaht列表页面失败");
            Assert.fail("进入webcaht列表页面失败");
            e.printStackTrace();
        }

        Boolean flag = true;
        Log.startTestCase("系统自动生成webchat接入号码用例");
        try {
            driver.findElement(By.id("addBtn")).click();//点击添加webchat账号按钮
            DriverWait.findElement(driver,By.id("editBtn")).isDisplayed();
            String number = driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[2]/td[2]/input")).getAttribute("value");
            String urlNumber = driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[5]/td[2]/span")).getText().split("ht=")[1];
            if(urlNumber.equals(number)){
                Log.info("新增webchat过程中系统自动生成接入号码成功");
            }else {
                new GetScreenshot(driver,"新增webchat过程中系统自动生成接入号码失败");
                Log.error("新增webchat过程中系统自动生成接入号码失败");
                flag = false;
            }
            driver.findElement(By.xpath("//button[@id='editBtn']/preceding-sibling::button")).click();
            Thread.sleep(1000);
        }catch (Exception e){
            new GetScreenshot(driver,"新增webchat过程中系统自动生成接入号码失败");
            Log.error("新增webchat过程中系统自动生成接入号码失败");
            flag = false;
        }
        Log.endTestCase("系统自动生成webchat接入号码用例");

        try {
            for (int i = 1; i <= 2; i++) {
                if(i==1){
                    Log.startTestCase("新增webchat接入号用例");
                    driver.findElement(By.id("addBtn")).click();//点击添加webchat账号按钮
                }else{
                    Log.startTestCase("修改webchat接入号用例");
                    driver = driver.switchTo().parentFrame();
                    MenuNameEleLoc.getMenuEle(driver,"webchat").click();
                    Thread.sleep(1000);
                    driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
                    driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[4]/button[2]")).click();//点击编辑按钮
                }
                DriverWait.findElement(driver, By.id("editBtn")).isDisplayed();
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[1]/td[2]/input")).clear();
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[2]/td[2]/input")).clear();
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[1]/td[2]/input")).sendKeys("autowebchat"+i);//输入webchat接入名称
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[2]/td[2]/input")).sendKeys("aaaaa"+i);//输入webchat接入号码
                if(i==1){
                    driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[3]/td[2]/div/input")).sendKeys("F:\\SvnData\\trunk\\uploadfileone.jpg");//点击上传logo
                    driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[4]/td[1]/a/i")).click();//点击新增标签页
                    Thread.sleep(1000);
                }else {
                    FileIo.loadFile(driver.findElement(By.xpath("//div[@class='preview']/div/div/div/div/img")), "F:\\SvnData\\trunk\\initcomlogo.jpg");
                    driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[4]/td[2]/table/tbody/tr/td[2]/input")).clear();
                    driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[4]/td[2]/table/tbody/tr/td[4]/input")).clear();
                }
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[4]/td[2]/table/tbody/tr/td[2]/input")).sendKeys("名称"+i);//输入标签名称
                driver.findElement(By.xpath("//table[@class='panel-table']/tbody/tr[4]/td[2]/table/tbody/tr/td[4]/input")).sendKeys("地址"+i);//输入标签地址
                driver.findElement(By.id("editBtn")).click();//点击保存按钮
                Thread.sleep(1000);

                //判断是否修改新增修改成功
                String namevalue = driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[1]")).getAttribute("title");
                String numvalue = driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[2]")).getAttribute("title");
                String urlvalue = driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[3]")).getText().split("ht=")[1];
                if(i==1){
                    if(namevalue.equals("autowebchat1")&numvalue.equals("aaaaa1")&urlvalue.contains("aaaaa1")){
                        Log.info("新增webcaht接入号成功");
                        Log.endTestCase("新增webcha接入号用例");
                    }else {
                        Log.error("新增webcaht接入号成功");
                        flag = false;
                        Log.endTestCase("新增webcha接入号用例");
                    }
                }else {
                    Boolean isrigtimg = CompareImg.isSameOne("F:\\SvnData\\trunk\\uploadfileone.jpg", "F:\\SvnData\\trunk\\initcomlogo.jpg");
                    if(namevalue.equals("autowebchat2")&numvalue.equals("aaaaa2")&urlvalue.contains("aaaaa2")&isrigtimg){
                        Log.info("修改webcaht接入号成功");
                        Log.endTestCase("修改webcha接入号用例");
                    }else {
                        Log.error("修改webcaht接入号失败");
                        flag = false;
                        Log.endTestCase("修改webcha接入号用例");
                    }
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新增webchat接入过程出错了");
            Log.error("新增修改webchat接入过程出错了");
            e.printStackTrace();
            flag = false;
        }

        Log.startTestCase("删除webchat接入号用例");
        try {
            driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[4]/button[3]")).click();//点击删除按钮
            Thread.sleep(1000);
            driver.findElement(By.id("delBtn")).click();//点击确认删除按钮
            String firstname = driver.findElement(By.xpath("//div[@id=\"accessPane\"]/div[2]/table/tbody/tr[2]/td[1]")).getAttribute("title");
            if(!firstname.equals("autowebchat2")){
                Log.info("删除webchat接入号成功");
            }else {
                new GetScreenshot(driver,"删除webchat接入号失败");
                Log.error("删除webchat接入号失败");
                flag = false;
            }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"删除接入号失败");
            Log.error("删除webchat接入号失败");
            e.printStackTrace();
            flag = false;
        }
        Log.endTestCase("删除webchat接入号用例");


        if(flag){
            Log.info("新增修改删除webcaht接入号成功");
        }else {
            Log.error("新增修改删除webchat接入号失败");
            Assert.fail();
        }
    }
    @AfterMethod
    public void afterMethod(){
        try {
            LogoutAction.excut(driver);
        } catch (Exception e) {
            Log.error("执行LogoutAction.excut失败");
            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
        }
        driver.quit();
    }
}
