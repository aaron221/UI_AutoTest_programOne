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
 * Created by thomas on 2018/10/17
 */
public class PersInfo {
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
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
        } catch (Exception e) {
            new GetScreenshot(driver, "进入个人信息页面失败");
            Log.error("进入个人信息页面失败");
            Assert.fail("进入个人信息页面失败");
            e.printStackTrace();
        }
    }

    @AfterMethod
    public void afterMethod(){
        driver = driver.switchTo().defaultContent();
    }

    @Test(priority = 0)
    public void persInfoAccoutEdit() {
        Boolean flag = true;
        Log.startTestCase("修改个人账号头像用例");
        if(EditEle.eidtAccountImg(driver,By.id("uploadPreview"), By.id("uploadLogo"),"个人信息")) {
            Log.info("修改个人账号头像成功");
        } else {
            Log.error("修改个人账号头像失败");
            flag = false;
        }
        Log.endTestCase("修改个人账号头像用例");

        Log.startTestCase("修改个人账号姓名用例");
        if (EditEle.editInputEle(driver, By.xpath("//input[@id='userName']/following-sibling::a"), By.id("userName"),
                "aaron", By.xpath("//footer[@class='btns-bottom-center']/button"))) {
            Log.info("修改个人账号姓名成功");
        } else {
            Log.error("修改个人账号姓名失败");
            flag = false;
        }
        Log.endTestCase("修改个人账号姓名用例");

        Log.startTestCase("修改个人账号邮箱用例");
        if (EditEle.editInputEle(driver, By.xpath("//input[@id='email']/following-sibling::a"), By.id("email"),
                "165517692@qq.com", By.xpath("//footer[@class='btns-bottom-center']/button"))) {
            Log.info("修改个人账号邮箱成功");
        } else {
            Log.error("修改个人账号邮箱失败");
            flag = false;
        }
        Log.endTestCase("修改个人账号邮箱用例");

        Log.startTestCase("修改个人账号密码用例");
        try {
        String newpasswd = "Aa_666666";
        for (int i = 0; i < 2; i++) {
                DriverWait.findElement(driver, By.xpath("//table[@id=\"Table\"]/tbody/tr[4]/td[2]/a")).click();//点击修改密码按钮
                DriverWait.findElement(driver, By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[1]"));
                driver.findElement(By.id("oldPassword")).sendKeys(Constant.PASSWD);
                driver.findElement(By.id("firstPassword")).sendKeys(newpasswd);
                driver.findElement(By.id("password")).sendKeys(newpasswd);
                driver.findElement(By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[1]")).click();//点击保存按钮
                String info = DriverWait.findElement(driver, By.xpath("/html/body/div[6]/div/div[4]")).getText();
                Thread.sleep(3000);//等待通知提示消失
                if (info.equals("修改密码成功,下次登录请使用新密码登录!")) {
                    Log.info("修改个人账号密码成功");
                } else if (info.equals("修改密码失败，初始密码错误")) {
//                    Log.info("确认修改个人账号密码成功");
                    DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[2]"))).click();//点击取消按钮
                    Thread.sleep(1000);
                    //还原密码
                    for (int j = 0; j < 2; j++) {
                        DriverWait.findElement(driver, By.xpath("//table[@id=\"Table\"]/tbody/tr[4]/td[2]/a")).click();//点击修改密码按钮
                        DriverWait.findElement(driver, By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[1]"));
                        driver.findElement(By.id("oldPassword")).sendKeys(newpasswd);
                        driver.findElement(By.id("firstPassword")).sendKeys(Constant.PASSWD);
                        driver.findElement(By.id("password")).sendKeys(Constant.PASSWD);
                        driver.findElement(By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[1]")).click();//点击保存按钮
                        String info1 = DriverWait.findElement(driver, By.xpath("/html/body/div[6]/div/div[4]")).getText();
                        switch (info1){
                            case "修改密码成功,下次登录请使用新密码登录!":{
//                                Log.info("还原个人账号密码成功");
                                driver = driver.switchTo().parentFrame();
                                MenuNameEleLoc.getMenuEle(driver,"个人信息").click();
                                driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
                                break;
                            }
                            case "修改密码失败，初始密码错误":{
//                                Log.info("确认还原个人账号密码成功");
                                DriverWait.findElement(driver,driver.findElement(By.xpath("//div[@id=\"modalPwd\"]/div[2]/div/div[3]/button[2]"))).click();//点击取消按钮
                                break;
                            }
                            default: {
                                new GetScreenshot(driver, "还原个人密码失败");
                                Log.error("还原个人密码失败");
                                flag = false;
                                break;
                            }
                        }
                    }
                }
        }
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改个人账号密码失败");
            Log.error("修改个人账号密码失败");
            e.printStackTrace();
            flag = false;
        }
        Log.endTestCase("修改个人账号密码用例");

        if(flag){
            Log.info("执行个人账号信息编辑修改成功");
        }else {
            Log.error("执行个人账号信息编辑修改失败");
            Assert.fail();
        }
    }

    @Test(priority = 1)
    public void persInfoCardEdit(){
        Boolean flag = true;
        Log.startTestCase("修改个人信息客服性别用例");
        if(EditEle.editSelEle(driver, By.id("sex"),By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改客服性别成功");
        }else {
            new GetScreenshot(driver,"修改客服性别失败");
            Log.error("修改客服性别失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息客服性别用例");

        Log.startTestCase("修改个人信息客服昵称用例");
        if(EditEle.editInputEle(driver, By.id("crmName"), "aaron", By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改客服昵称成功");
        }else {
            new GetScreenshot(driver,"修改客服昵称失败");
            Log.error("修改客服昵称失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息客服昵称用例");

        Log.startTestCase("修改个人信息客服年龄用例");
        if(EditEle.editInputEle(driver, By.name("age"), "18", By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改客服年龄成功");
        }else {
            new GetScreenshot(driver,"修改客服年龄失败");
            Log.error("修改客服年龄失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息客服年龄用例");

        Log.startTestCase("修改个人信息客服联系电话用例");
        if(EditEle.editInputEle(driver, By.id("tel"), "13666666666", By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改客服昵称成功");
        }else {
            new GetScreenshot(driver,"修改客联系电话称失败");
            Log.error("修改客联系电话称失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息客服联系电话用例");

        if(flag){
            Log.info("修改个人信息客服名片模块字段成功");
        }else {
            Log.error("修改个人信息客服名片模块字段失败");
            Assert.fail();
        }
    }

    @Test(priority = 2)
    public void persInfoBindPhone(){
        Boolean flag = true;
        Log.startTestCase("修改个人信息是否绑定手机号用例");
        if(EditEle.editSigCboxEle(driver, By.id("isBindMobileNoChk"),By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改是否绑定手机号成功");
        }else {
            Log.error("修改是否绑定手机号失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息是否绑定手机号用例");

        Log.startTestCase("修改个人信息绑定的手机号用例");
        if(EditEle.editInputEle(driver, By.id("mobileNoStr"), "13588888888", By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("修改绑定的手机号成功");
        }else {
            Log.error("修改绑定的手机号失败");
            flag = false;
        }
        Log.endTestCase("修改个人信息绑定的手机号用例");

        Log.startTestCase("修改个人信息手机号绑定转接时间用例");
        //通过手动输入模式修改转接时间
        if(EditEle.editInputEle(driver, By.id("bindMobileWorkTimeStr"), "00:00-23:30", By.xpath("/html/body/div[1]/div/footer/button"))){
            Log.info("手动输入转接时间成功");
        }else {
            Log.error("手动输入转接时间失败");
            flag = false;
        }

        //通过按钮点击选择修改转接时间
        try {
            WebElement element = DriverWait.findElement(driver,driver.findElement(By.id("bindMobileWorkTimeStr")));
            String timevalue = element.getAttribute("value");//把修改前的姓名给保存到变量中，后面好还原
            element.clear();//清除原先的姓名元素的value值

            driver.findElement(By.id("date1")).click();
            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[32]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[32]")).click();
            driver.findElement(By.id("date2")).click();
            DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[34]")));
            Thread.sleep(1000);
            driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[34]")).click();
            driver.findElement(By.xpath("//input[@id='date2']/parent::td/following-sibling::td/i")).click();//点击加号按钮
            driver.findElement(By.xpath("/html/body/div[1]/div/footer/button")).click();//点击保存按钮

            //判断是否修改成功
            String timeupdate = DriverWait.findElement(driver, driver.findElement(By.id("bindMobileWorkTimeStr"))).getAttribute("value");
            if(!timevalue.equals(timeupdate)){
                Log.info("通过按钮修改转接时间成功");
            }else {
                new GetScreenshot(driver,"通过按钮修改转接时间失败");
                Log.error("通过按钮修改转接时间失败");
                flag = false;
            }

            //把原先的转接时间还原回去
            driver.findElement(By.id("bindMobileWorkTimeStr")).clear();//清除修改的转接时间
            driver.findElement(By.id("bindMobileWorkTimeStr")).sendKeys(timevalue);
            driver.findElement(By.xpath("/html/body/div[1]/div/footer/button")).click();//点击保存按钮

            //判断是否还原成功
            if(driver.findElement(By.id("bindMobileWorkTimeStr")).getAttribute("value").equals(timevalue)){
                Log.info("转接时间还原成功");
            }else {
                new GetScreenshot(driver,"转接时间还原失败");
                Log.error("转接时间还原失败");
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"通过按钮修改转接时间失败");
            Log.error("通过按钮修改转接时间失败");
            e.printStackTrace();
            flag = false;
        }
        Log.endTestCase("修改个人信息手机号绑定转接时间用例");

        if(flag){
            Log.info("修改手机绑定模块字段信息成功");
        }else {
            Log.error("修改手机绑定模块字段信息失败");
            Assert.fail();
        }
    }
}
