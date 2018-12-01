package god.testscripts.settingpagecase;

import god.appmodules.LoginAction;
import god.appmodules.LogoutAction;
import god.util.*;
import god.util.waitUse.ContextTab;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;

/**
 * Created by thomas on 2018/10/11
 */
public class SeatsManage {
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
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
    }

    @BeforeClass
    public void beforeClass()throws Exception{
        try {
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "坐席管理"));//下拉拖动条至元素与拖页面顶端对齐
            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "坐席管理")).click();//点击坐席管理链接
        } catch (Exception e) {
            new GetScreenshot(driver, "进入坐席列表页面失败");
            e.printStackTrace();
            Assert.fail("进入坐席列表页面失败");
        }

        try {
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//form[@id='actionForm']/div/div[2]/button"))).click();//点击工号查询条件
            DriverWait.findElement(driver,driver.findElement(By.id("logonNumber_id"))).sendKeys(Constant.USERNAME.split("@")[0]);
            ContextTab.pressEnter();//回车进行点击查询
            Thread.sleep(1000);
            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id='zuoxiList']/table/tbody/tr[2]/td[2]"))).click();//点击第一个用户进行修改页面
            DriverWait.findElement(driver, driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")));//等待该保存按钮元素显示
        } catch (Exception e) {
            new GetScreenshot(driver, "进入坐席修改页面失败");
            e.printStackTrace();
            Assert.fail("进入坐席修改页面失败");
        }
    }

    @AfterClass
    public void afterClass()throws Exception{
        DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,"首页")).click();
        Thread.sleep(1000);
    }

    @BeforeMethod
    public void beforeMethod(){

    }

    @AfterMethod
    public void afterMethod(){
        driver = driver.switchTo().defaultContent();
    }

    @Test(priority = 0)
    public void seatsManageCheckbox() {
        //对单选复选框的各个元素进行修改以及还原操作
        Boolean flag = true;
        Log.startTestCase("修改坐席管理通用角色字段用例");
        if(EditEle.editMultCboxEle(driver,By.xpath("//form[@id='userForm']/div/table/tbody/tr[5]/td[4]"),
                By.xpath("//footer[@class='text-center']/button[1]"),"span","Input" )){
            Log.info("修改通用角色成功");
        } else {
            Log.error("修改通用角色失败");
            flag = false;
        }
        Log.endTestCase("修改坐席管理通用角色字段用例");

        Log.startTestCase("修改坐席管理界面组织机构角色字段用例");
        if(EditEle.editMultCboxEle(driver,By.id("role_td"),By.xpath("//footer[@class='text-center']/button[1]"),"span","input")) {
            Log.info("修改组织机构角色成功");
        } else {
            Log.error("修改组织机构角色失败");
            flag = false;
        }
        Log.endTestCase("修改坐席管理界面组织机构角色字段用例");

        Log.startTestCase("修改坐席管理界面包含技能字段用例");
        if(EditEle.editMultCboxEle(driver, By.id("skill_td"), By.xpath("//footer[@class='text-center']/button[1]"), "span", "Input")){
            Log.info("修改包含技能成功");
        } else {
            Log.error("修改包含技能失败");
            flag = false;
        }
        Log.endTestCase("修改坐席管理界面包含技能字段用例");

        String [] idname = {"leaveLimitCheckbox","servQQCheckbox","servManagerCheckbox","servMsgCheckbox", "servPrvwxCheckbox"};
        String [] hintvalue = {"允许留言回复","允许QQ接入","创建群组字段","短信发送","微信接入"};
        for (int i = 0; i < idname.length; i++) {
            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
            if(EditEle.editSigCboxEle(driver, By.id(idname[i]), By.xpath("//footer[@class='text-center']/button[1]"))){
                Log.info("修改"+hintvalue[i]+"字段成功");
            }else {
                Log.error("修改"+hintvalue[i]+"字段失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
        }

        if(driver.findElement(By.id("servPhoneCheckbox")).isSelected()){
            String [] idname1 = {"servPhoneCheckbox","isBindMobileNoChk","servManagerCheckbox","servMsgCheckbox", "servPrvwxCheckbox"};
            String [] hintvalue1 = {"电话接入","是否开启转接","创建群组字段","短信发送","微信接入"};
            for (int i = 0; i < idname.length; i++) {
                Log.startTestCase("修改"+hintvalue[i]+"字段用例");
                if(EditEle.editSigCboxEle(driver, By.id(idname1[1]), By.xpath("//footer[@class='text-center']/button[1]"))){
                    Log.info("修改"+hintvalue1[i]+"字段成功");
                }else {
                    Log.error("修改"+hintvalue1[i]+"字段失败");
                    flag = false;
                }
                Log.endTestCase("修改"+hintvalue1[i]+"字段用例");
            }
            //修改电话接入类型
            Log.startTestCase("修改坐席管理界面电话接入类型用例");
            Boolean flag1 = false;
            try {
                if(driver.findElement(By.id("servPhoneTypeCheckboxS")).isSelected()){
                    driver.findElement(By.id("servPhoneTypeCheckboxU")).click();
                    flag1 = true;
                }else {
                    driver.findElement(By.id("servPhoneTypeCheckboxS")).click();
                }

                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮

                //判断是否点击修改电话接入方式成功
                if(driver.findElement(By.id("servPhoneTypeCheckboxS")).isSelected()==flag1){
                    Log.error("点击修改电话接入类型失败");
                    flag = false;
                }else {
                    Log.info("点击修改电话接入类型成功");
                }

                //还原原先选择的接入方式
                if(flag1){
                    driver.findElement(By.id("servPhoneTypeCheckboxS")).click();
                }else {
                    driver.findElement(By.id("servPhoneTypeCheckboxU")).click();
                }

                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮

                //判断是否还原成功
                if(driver.findElement(By.id("servPhoneTypeCheckboxS")).isSelected()!=flag1){
                    Log.error("还原电话接入类型失败");
                    flag = false;
                }else {
                    Log.info("还原电话接入类型成功");
                }
            }catch (Exception e){
                new GetScreenshot(driver,"修改坐席管理界面接入电话字段失败");
                Log.error("修改坐席管理界面接入电话字段失败");
                flag = false;
            }
        }else {
            Log.startTestCase("修改坐席管理界面允许电话接入字段用例");
            if(EditEle.editSigCboxEle(driver, By.id("servPhoneCheckbox"), By.xpath("//footer[@class='text-center']/button[1]"))) {
                Log.info("修改电话接入成功");
            } else {
                Log.error("修改电话接入失败");
            }
            Log.endTestCase("修改坐席管理界面允许电话接入字段用例");
        }

        if(flag){
            Log.info("坐席管理修改页面中的\"通用角色\"，\"组织机构角色\"，\"包含技能\",\"允许留言回复\",\"允许QQ接入\",\"创建群组字段\",\"短信发送\",\"微信接入\",\"电话接入类型\"，\"允许留言回复\",\"允许QQ接入\",\"创建群组字段\",\"短信发送\",\"微信接入\"字段修改成功");
        }else {
            Log.error("坐席管理修改页面中的\"通用角色\"，\"组织机构角色\"，\"包含技能\",\"允许留言回复\",\"允许QQ接入\",\"创建群组字段\",\"短信发送\",\"微信接入\",\"电话接入类型\"，\"允许留言回复\",\"允许QQ接入\",\"创建群组字段\",\"短信发送\",\"微信接入\"字段修改失败");
            Assert.fail();
        }
        }

    @Test(priority = 1)
    public void seatsManageinput() {
        By saveby = By.xpath("//footer[@class='text-center']/button[1]");
        Boolean flag = true;
        //对输入框的各个元素进行修改以及还原操作

        String [] inputname = {"name","crmName","email","tel"};
        String [] hintvalue = {"姓名","昵称","邮箱","联系电话"};
        String [] updatavalue = {"aaron","aaron","emailname@163.com","13633456766"};
        for (int i = 0; i < inputname.length; i++) {
            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
            if(EditEle.editInputEle(driver,By.id("name"),updatavalue[i],saveby)){
                Log.info("修改"+hintvalue[i]+"字段成功");
            }else {
                Log.error("修改"+hintvalue[i]+"字段失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
        }

        Log.startTestCase("修改坐席管理界面接入量字段用例");
        if(EditEle.editInputEle(driver, By.name("defaultConnCount"), "66",saveby)){
            Log.info("修改坐席管理界面接入量成功");
        } else {
            Log.error("修改坐席管理界面接入量失败");
            flag = false;
        }
        Log.endTestCase("修改坐席管理界面接入量字段用例");


        if(driver.findElement(By.id("servPhoneCheckbox")).isSelected()){
            String [] inputname1 = {"defaultPhoneMaxcount","sapcingInterval","bindMobileWorkTime"};
            String [] hintvalue1 = {"最大接入量","话后切换时间","通过输入模式修改转接时间"};
            String [] updatavalue1 = {"66","33","00:00-23:00"};
            for (int i = 0; i < inputname.length; i++) {
                Log.startTestCase("修改"+hintvalue1[i]+"字段用例");
                if(EditEle.editInputEle(driver,By.id("name"),updatavalue1[i],saveby)){
                    Log.info("修改"+hintvalue1[i]+"字段成功");
                }else {
                    Log.error("修改"+hintvalue1[i]+"字段失败");
                    flag = false;
                }
                Log.endTestCase("修改"+hintvalue1[i]+"字段用例");
            }

            //通过按钮点击修改转接时间段
            Log.startTestCase("修改坐席管理界面通过点击按钮模式修改转接时间字段用例");
            String timevalue = null;
            try {
                WebElement element = driver.findElement(By.id("bindMobileWorkTime"));
                timevalue = element.getAttribute("value");//把修改前的姓名给保存到变量中，后面好还原
                element.clear();//清除原先的姓名元素的value值

                driver.findElement(By.id("date1")).click();
                driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[1]/div[1]")).click();//选中00:00开始时间
                driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[1]/div[48]")).click();//选中23:30结束时间
                driver.findElement(By.xpath("//input[@id='date2']/following-sibling::i")).click();//点击加号按钮

                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮
                Thread.sleep(1000);
                String timeupdate = driver.findElement(By.id("bindMobileWorkTime")).getAttribute("value");
                //判断是否修改成功
                if(!timevalue.equals(timeupdate)){
                    Log.info("通过按钮修改转接时间成功");
                }else {
                    new GetScreenshot(driver,"通过按钮修改转接时间失败");
                    Log.error("通过按钮修改转接时间失败");
                    flag = false;
                }
            }catch (Exception e){
                new GetScreenshot(driver,"点击保存按钮前出错了");
                e.printStackTrace();
                Log.error("点击保存按钮前出错了");
                flag = false;
            }


            //把原先的转接时间还原回去
            try {
                driver.findElement(By.id("bindMobileWorkTime")).clear();//清除修改的转接时间
                driver.findElement(By.id("bindMobileWorkTime")).sendKeys(timevalue);
                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"还原时候点击保存按钮前出错误了");
                e.printStackTrace();
                Log.error("还原时候点击保存按钮前出错误了");
                flag = false;
            }
            //判断是否还原成功
            try {
                if(driver.findElement(By.id("bindMobileWorkTime")).getAttribute("value").equals(timevalue)){
                    Log.info("转接时间还原成功");
                }else {
                    new GetScreenshot(driver,"转接时间还原失败");
                    Log.error("转接时间还原失败");
                    flag = false;
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.error("判断是否还原的时候出错了");
                flag = false;
            }

        }else {
            new GetScreenshot(driver,"该坐席设置的是不允许电话接入");
            Log.info("该坐席设置的是不允许电话接入");
        }
        Log.endTestCase("修改坐席管理界面通过点击按钮模式修改转接时间字段用例");

        if(flag){
            Log.info("修改坐席界面的最大接入量,话后切换时间,转接时间字段成功");
        }else {
            Log.error("修改坐席界面的最大接入量,话后切换时间,转接时间字段失败");
        }
    }

    @Test(priority = 2)
    public void seatsManagesel() {
        Boolean flag = true;
        String [] inputname = {"userType","sex","organ"};
        String [] hintvalue = {"职位","性别","组织机构"};
        for (int i = 0; i < inputname.length; i++) {
            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
            if(EditEle.editSelEle(driver, By.id(inputname[i]),By.xpath("//footer[@class='text-center']/button[1]"))){
                Log.info("修改"+hintvalue[i]+"字段成功");
            }else {
                Log.error("修改"+hintvalue[i]+"字段失败");
                flag = false;
            }
            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
        }

        //修改重置密码
        Log.startTestCase("修改重置密码字段用例");
        String username =driver.findElement(By.xpath("//form[@id=\"userForm\"]/div/table/tbody/tr[3]/td[2]")).getText();//获取工号
        username = username+Constant.USERNAME.split("@")[1];
        try {
            driver.findElement(By.xpath("//a[text()='重置密码']")).click();//点击重置密码按钮
            driver.findElement(By.id("password")).sendKeys("Aa_666666");
            driver.findElement(By.id("confirmPassword")).sendKeys("Aa_666666");
            ContextTab.pressTabkey();
            ContextTab.pressEnter();//点击enter键进行点击保存按钮
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='重置密码成功！']"))).isDisplayed();
        }catch (Exception e){
            new GetScreenshot(driver,"重置密码失败");
            Log.error("点击保存按钮后，未出现修改密码成功提示");
            flag = false;
        }

        //登录一下被修改的账号看是否修改成功
        //获取当前浏览器driver的handels
//        String curhandle = driver.getWindowHandle();
//        Set<String> handles =driver.getWindowHandles();
//        ((JavascriptExecutor) driver).executeScript("window.open("+Constant.URL+");");//在同一个driver上重新打开另外一个登陆页面
//        for(String handleTo : handles){
//            if(handleTo!=curhandle){
//                driver.switchTo().window(handleTo);
//            }
//        }
//        try {
//            LoginAction.execute(driver,Constant.USERNAME,Constant.PASSWD,Constant.STATUS,Constant.REM_PWD);
//            DriverWait.findElement(driver, new HomePage(driver).LogoutPhoto());//查找是否正确登录进来
//            new GetScreenshot(driver,"修改密码失败");
//            Log.error("修改密码失败");
//            flag = false;
//        }catch (Exception e){
//            Log.info("修改密码成功");
//            driver.close();
//        }

        //还原被修改的账号的密码
        try {
            driver.findElement(By.xpath("//a[text()='重置密码']")).click();//点击重置密码按钮
            driver.findElement(By.id("password")).clear();
            driver.findElement(By.id("password")).sendKeys(Constant.PASSWD);
            driver.findElement(By.id("confirmPassword")).clear();
            driver.findElement(By.id("confirmPassword")).sendKeys(Constant.PASSWD);
            ContextTab.pressTabkey();
            ContextTab.pressEnter();//点击enter键进行点击保存按钮
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='重置密码成功！']"))).isDisplayed();
        }catch (Exception e){
            new GetScreenshot(driver,"还原密码失败");
            Log.error("还原密码时，点击保存按钮后，未出现修改密码成功提示");
            flag = false;
        }

        Log.endTestCase("修改重置密码字段用例");

        //点击密码解锁按钮
        driver.findElement(By.xpath("//form[@id='userForm']/div/table/tbody/tr[3]/td[5]/button")).click();
        try {
            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='解锁成功！']"))).isDisplayed();
        }catch (Exception e){
            new GetScreenshot(driver,"点击解锁按钮没有出现解锁成功提示");
            Log.error("点击解锁按钮没有出现解锁成功提示");
            Assert.fail("点击解锁按钮没有出现解锁成功提示");
        }

        if(driver.findElement(By.id("servPhoneCheckbox")).isSelected()){
            String [] inputname1 = {"extension","preference"};
            String [] hintvalue1 = {"分机号码","偏好设置"};
            for (int i = 0; i < inputname.length; i++) {
                Log.startTestCase("修改"+hintvalue1[i]+"字段用例");
                if(EditEle.editSelEle(driver, By.id(inputname[i]), By.xpath("//footer[@class='text-center']/button[1]"))){
                    Log.info("修改"+hintvalue1[i]+"字段成功");
                }else {
                    Log.error("修改"+hintvalue1[i]+"字段失败");
                    flag = false;
                }
                Log.endTestCase("修改"+hintvalue1[i]+"字段用例");
            }
        }else {
            Log.info("该坐席没有设置允许接电话接入");
        }

        if(flag){
            Log.info("修改坐席管理页面重置密码，密码解锁，职位,性别,组织机构，分机号，偏好设置字段成功");
        }else {
            Log.error("修改坐席管理页面重置密码，密码解锁，职位,性别,组织机构，分机号，偏好设置字段成功");
            Assert.fail();
        }
    }
    }
