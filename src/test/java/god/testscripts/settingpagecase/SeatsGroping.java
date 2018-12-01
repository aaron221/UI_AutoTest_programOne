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

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Created by aaron on 2018/10/10
 */
public class SeatsGroping {
    public WebDriver driver;
    @BeforeSuite
    public void beforeSuite() throws Exception {
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
    public void beforeClass() throws Exception{
       DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();//点击首页链接
       Thread.sleep(1000);
    }

    @AfterClass
    public void afterClass() throws Exception{
        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();//点击首页链接
        Thread.sleep(1000);
    }

    @BeforeMethod
    public void beforeMethod(){
        DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver,"设置")).click();//点击设置链接
        driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
        DragScrollBar.dragToEleBot(driver,MenuNameEleLoc.getMenuEle(driver,"坐席分组"));
        MenuNameEleLoc.getMenuEle(driver,"坐席分组").click();//点击坐席分组链接
    }

    @AfterMethod
    public void afterMethod(){
        driver = driver.switchTo().defaultContent();
    }

    @Test(priority = 0)
    public void seatsGropingRole(){
        try {
        for (int i = 1; i < 3; i++) {
            switch (i) {
                case 1: {
                    Log.startTestCase("角色新建用例");
                    driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
                    driver = driver.switchTo().frame("iframepage");
                    DriverWait.findElement(driver, driver.findElement(By.xpath("/html/body/div/a"))).click();//点击新建按钮
                    break;}
                case 2: {
                    Log.startTestCase("角色修改用例");
                    DriverWait.findElement(driver, By.xpath("//td[@title='自动输入的角色描述1']/following-sibling::td[3]/botton[1]")).click();//点击刚刚新建的角色后面的编辑按钮
                    break;}
                default:
                    break;
            }
            driver = driver.switchTo().parentFrame();//返回到上一级框架
            DriverWait.findElement(driver, driver.findElement(By.id("roledescription"))).isDisplayed();//角色描述字段展示
            SelectSigleBox.chooseOption(driver, driver.findElement(By.id("roleOrgan")), (i+1));//选择组织
            SelectSigleBox.chooseOption(driver, driver.findElement(By.id("roleType")), (i-1));//选择角色类型
            driver.findElement(By.id("roleName")).clear();
            driver.findElement(By.id("roleName")).sendKeys("自动输入的角色名称"+i+"");//输入角色名称
            driver.findElement(By.xpath("//input[@id='roleStateTemp']/following-sibling::span")).click();//改变状态
            driver.findElement(By.id("roledescription")).clear();
            driver.findElement(By.id("roledescription")).sendKeys("自动输入的角色描述" + i + "");//输入角色描述
            driver.findElement(By.xpath("//div[@id='modalUpdateRole']/div[2]/div/div[3]/a[1]")).click();//点击保存按钮
            driver = driver.switchTo().frame("iframepage");
            Thread.sleep(1000);

            //判断是否新建修改成功
            WebElement element = DriverWait.findElement(driver, By.xpath("//td[@title='自动输入的角色名称"+i+"']"));
            String roledescr = element.findElement(By.xpath("following-sibling::td[1]")).getAttribute("title");
            String roletype = element.findElement(By.xpath("following-sibling::td[2]")).getText();
            String rolestatte = element.findElement(By.xpath("following-sibling::td[3]")).getText();
            if(i==1&&roledescr.equals("自动输入的角色描述" + i + "")&&roletype.equals("管理员角色")&&rolestatte.equals("无效")){
                Log.info("新建角色成功");
                Log.endTestCase("新建角色用例");
            }else if(i==2&&roledescr.equals("自动输入的角色描述" + i + "")&&roletype.equals("自定义角色")&&rolestatte.equals("有效")){
                Log.info("修改角色成功");
                Log.endTestCase("修改角色用例");
            }else {
                new GetScreenshot(driver,"新建或修改角色失败");
                Log.info("新建或修改角色失败");
                Assert.fail();
            }
        }
        } catch (Exception e) {
            new GetScreenshot(driver,"新建或修改角色过程中发生了错误");
            Log.error("新建或修改角色过程中发生了错误");
            Assert.fail("新建或修改角色过程中发生了错误");
        }

        Log.startTestCase("删除角色用例");
        try {
            DriverWait.findElement(driver,By.xpath("//td[@title='自动输入的角色描述2']/following-sibling::td[3]/botton[3]")).click();//点击刚刚新建的角色后面的删除按钮
            driver = driver.switchTo().parentFrame();
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id='modalDelRole']/div[2]/div/div[3]/a"))).click();//点击确定按钮
            Thread.sleep(1000);
            driver = driver.switchTo().frame("iframepage");
            //判断是否删除成功
            String rolename = DriverWait.findElement(driver, By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getAttribute("title");
            if(!rolename.equals("自动输入的角色名称2")){
                Log.info("删除角色成功");
            }else {
                Log.error("删除角色失败");
                Assert.fail();
            }
        }catch (Exception e){
            Log.error("删除角色过程中发生了错误");
            Assert.fail();
        }
        Log.endTestCase("删除角色用例");
    }

    @Test(priority = 1)
    public void seatsGropingSkill() {
        try {
            for (int i = 1; i < 3; i++) {
                switch (i) {
                    case 1: {
                        Log.startTestCase("技能新建用例");
                        driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
                        DriverWait.findElement(driver,driver.findElement(By.xpath("//ul[@role='tablist']/li[2]/a"))).click();//点击技能链接
                        driver = driver.switchTo().frame("iframeSkill");
                        Thread.sleep(1000);
                        DriverWait.findElement(driver,driver.findElement(By.xpath("/html/body/div/a"))).click();//点击新建按钮
                        break;}
                    case 2: {
                        Log.startTestCase("技能修改用例");
                        DriverWait.findElement(driver, By.xpath("//td[@title='自动输入的技能描述1']/following-sibling::td[2]/botton[1]")).click();//点击刚刚新建的角色后面的编辑按钮
                        break;}
                    default:
                        break;
                }
                driver = driver.switchTo().parentFrame();//返回到上一级框架
                DriverWait.findElement(driver,driver.findElement(By.id("skillDescribe"))).isDisplayed();
                SelectSigleBox.chooseOption(driver,driver.findElement(By.id("skillCenterOrg")), i);//选择组织
                driver.findElement(By.id("skillName")).clear();
                driver.findElement(By.id("skillName")).sendKeys("自动输入的技能名称"+i+"");//输入技能名称
                driver.findElement(By.id("skillDescribe")).clear();
                driver.findElement(By.id("skillDescribe")).sendKeys("自动输入的技能描述"+i+"");//输入技能描述
                driver.findElement(By.xpath("//div[@id='modalUpdateSkill']/div[2]/div/div[3]/a")).click();//点击保存按钮
                driver = driver.switchTo().frame("iframeSkill");
                Thread.sleep(1000);

                //判断是否新建修改成功
                WebElement element = DriverWait.findElement(driver, By.xpath("//td[@title='自动输入的技能描述"+i+"']"));
                String roledescr = element.getAttribute("title");
                if(i==1&&roledescr.equals("自动输入的技能描述" + i + "")){
                    Log.info("新建技能成功");
                    Log.endTestCase("新建技能用例");
                }else if(i==2&&roledescr.equals("自动输入的技能描述" + i + "")){
                    Log.info("修改技能成功");
                    Log.endTestCase("修改技能用例");
                }else {
                    new GetScreenshot(driver,"新建或修改技能失败");
                    Log.info("新建或修改技能失败");
                    Assert.fail();
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"新建或修改技能过程中发生了错误");
            Log.error("新建或修改技能过程中发生了错误");
            Assert.fail("新建或修改技能过程中发生了错误");
        }

        Log.startTestCase("删除技能用例");
        try {
            driver.findElement(By.xpath("//td[@title='自动输入的技能描述2']/following-sibling::td[2]/botton[2]")).click();//点击刚刚新建的技能后面的删除按钮
            driver =driver.switchTo().parentFrame();
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalDelSkill\"]/div[2]/div/div[3]/a"))).click();//点击确定按钮
            driver = driver.switchTo().frame("iframeSkill");

            //判断是否删除成功
            String skillname = DriverWait.findElement(driver, By.xpath("/html/body/table/tbody/tr[2]/td[1]")).getText();
            if(!skillname.equals("自动输入的技能名称2")){
                Log.info("删除技能成功");
            }else {
                Log.error("删除技能失败");
                Assert.fail();
            }
        }catch (Exception e){
            Log.error("删除技能过程中发生了错误");
            Assert.fail();
        }
        Log.endTestCase("删除技能用例");
    }

    @Test(priority = 2)
    public void seatsGropingDepNode(){
        try {
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            List<WebElement> listdep = DriverWait.findElement(driver, By.id("treeDemo_1_ul")).findElements(By.tagName("li"));
            for (int i = 1; i < 3; i++) {
                if(i==1){
                    Log.startTestCase("新增组织机构节点用例");
                    DriverWait.findElement(driver, By.id("treeDemo_1_a")).click();//点击第一个节点，好出现新增按钮
                    DriverWait.findElement(driver,By.id("addBtn_treeDemo_1")).click();//点击新增按钮
                }else {
                    //进行修改操作
                    Log.startTestCase("修改组织机构节点用例");
                    DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li[" + (listdep.size()+1) + "]/a/span[2]")).click();//点击一下新增的组织机构
                    Thread.sleep(1000);
                    DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li["+(listdep.size()+1)+"]/a/span[4]")).click();//点击编辑按钮
                }
                DriverWait.findElement(driver,By.id("saveButton")).isDisplayed();
                driver.findElement(By.id("organName")).clear();
                driver.findElement(By.id("organName")).sendKeys("自动化小组" + i + "");
                SelectSigleBox.chooseOption(driver, By.id("nodeType"),(i-1));
                driver.findElement(By.xpath("//form[@id=\"organModel\"]/div[4]/div/div/label/span")).click();//改变下状态
                driver.findElement(By.id("saveButton")).click();//点击保存按钮
                Thread.sleep(1000);
                //判断是否新增成功
                String depname = DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li[" + (listdep.size()+1) + "]/a")).getAttribute("title");
                if(i==1&&depname.equals("自动化小组1")){
                    Log.info("新增组织机构成功");
                    Log.endTestCase("新增组织机构节点用例");
                }else if(i==2&&depname.equals("自动化小组2")) {
                    Log.info("修改组织机构成功");
                    Log.endTestCase("修改组织机构节点用例");
                }else {
                    Log.error("新增组件机构失败");
                    Assert.fail();
                }
            }
        }catch (Exception e){
            new GetScreenshot(driver,"新增修改组织机构过程中发生了错误");
            Log.error("新增修改组织机构过程中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }

        //删除新增的组织机构
        Log.startTestCase("删除组织机构用例");
        try {
            List<WebElement> listdep = DriverWait.findElement(driver, By.id("treeDemo_1_ul")).findElements(By.tagName("li"));
            DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li[" + listdep.size()+ "]/a")).click();//点击一下刚刚创建的组织机构
            DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li[" + listdep.size() + "]/a/span[5]")).click();//点击删除按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver, By.xpath("//div[@id=\"modalDelOrgan\"]/div[2]/div/div[3]/a")).click();//点击确认删除按钮

            //判断是否删除成功
            String depname = DriverWait.findElement(driver, By.xpath("//li[@id='treeDemo_1']/ul/li[" + (listdep.size()-1) + "]/a")).getAttribute("title");
            if(depname.equals("自动化小组2")){
                Log.info("删除组织机构成功");
            }else {
                Log.error("删除组织机构失败");
                Assert.fail();
            }
        }catch (Exception e){
            new GetScreenshot(driver,"删除组织机构中发生了错误");
            Log.error("删除组织机构中发生了错误");
            e.printStackTrace();
            Assert.fail();
        }
        Log.endTestCase("删除组织机构用例");
    }
}
