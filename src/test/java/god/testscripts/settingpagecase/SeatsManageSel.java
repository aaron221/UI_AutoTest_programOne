//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.pageobjects.HomePage;
//import god.pageobjects.LoginPage;
//import god.util.*;
//import god.util.waitUse.ContextTab;
//import jdk.internal.dynalink.beans.StaticClass;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.apache.xmlbeans.impl.xb.xsdschema.Public;
//import org.openqa.selenium.By;
//import org.openqa.selenium.JavascriptExecutor;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.List;
//import java.util.Set;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/11.
// */
//public class SeatsManageSel {
//    public WebDriver driver;
//
//    @BeforeClass
//    public void BeforeClass() throws Exception{
//        //使用Constant类中常量，设定测试数据文件和测试数据所在的sheet名称,以及加载logj4.xml配置文件的路径
//        DOMConfigurator.configure(Constant.LOG4J_COF_PATH);
//    }
//
//    @BeforeMethod
//    public void beforeMethod(){
//        driver = SetBrowserProperty.openChromeBrowser();//创建个默认最大化的谷歌浏览器
//        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
//
//        try {
//            LoginAction.execute(driver, Constant.USERNAME, Constant.PASSWD, Constant.STATUS, Constant.REM_PWD);
//        } catch (Exception e) {
//            new GetScreenshot(driver,"登录失败");
//            Log.error("登录失败");
//        }
//    }
//
//    @Test
//    public void seatsManageName() {
//        Boolean flag = true;
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "坐席管理"));//下拉拖动条至元素与拖页面顶端对齐
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "坐席管理")).click();//点击坐席管理链接
//        } catch (Exception e) {
//            new GetScreenshot(driver, "进入坐席列表页面失败");
//            e.printStackTrace();
//            Assert.fail("进入坐席列表页面失败");
//        }
//
//        try {
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//form[@id='actionForm']/div/div[2]/button"))).click();//点击工号查询条件
//            DriverWait.findElement(driver,driver.findElement(By.id("logonNumber_id"))).sendKeys(Constant.USERNAME.split("@")[0]);
//            ContextTab.pressEnter();//回车进行点击查询
//            Thread.sleep(1000);
//            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@id='zuoxiList']/table/tbody/tr[2]/td[2]"))).click();//点击第一个用户进行修改页面
//            DriverWait.findElement(driver, driver.findElement(By.xpath("//footer[@class='text-center']/button[1]"))).isDisplayed();//等待该保存按钮元素显示
//        } catch (Exception e) {
//            new GetScreenshot(driver, "进入坐席修改页面失败");
//            e.printStackTrace();
//            Assert.fail("进入坐席修改页面失败");
//        }
//
//
//        String [] inputname = {"userType","sex","organ"};
//        String [] hintvalue = {"职位","性别","组织机构"};
//        for (int i = 0; i < inputname.length; i++) {
//            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
//            if(EditEle.editSelEle(driver, By.id(inputname[i]),By.xpath("//footer[@class='text-center']/button[1]"))){
//                Log.info("修改"+hintvalue[i]+"字段成功");
//            }else {
//                Log.error("修改"+hintvalue[i]+"字段失败");
//                flag = false;
//            }
//            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
//        }
//
//        //修改重置密码
//        Log.startTestCase("修改重置密码字段用例");
//        String username =driver.findElement(By.xpath("//form[@id=\"userForm\"]/div/table/tbody/tr[3]/td[2]")).getText();//获取工号
//        username = username+Constant.USERNAME.split("@")[1];
//        try {
//            driver.findElement(By.xpath("//a[text()='重置密码']")).click();//点击重置密码按钮
//            driver.findElement(By.id("password")).sendKeys("Aa_666666");
//            driver.findElement(By.id("confirmPassword")).sendKeys("Aa_666666");
//            ContextTab.pressTabkey();
//            ContextTab.pressEnter();//点击enter键进行点击保存按钮
//            Thread.sleep(1000);
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='重置密码成功！']"))).isDisplayed();
//        }catch (Exception e){
//            new GetScreenshot(driver,"重置密码失败");
//            Log.error("点击保存按钮后，未出现修改密码成功提示");
//            flag = false;
//        }
//
//        //登录一下被修改的账号看是否修改成功
//        //获取当前浏览器driver的handels
////        String curhandle = driver.getWindowHandle();
////        Set<String> handles =driver.getWindowHandles();
////        ((JavascriptExecutor) driver).executeScript("window.open("+Constant.URL+");");//在同一个driver上重新打开另外一个登陆页面
////        for(String handleTo : handles){
////            if(handleTo!=curhandle){
////                driver.switchTo().window(handleTo);
////            }
////        }
////        try {
////            LoginAction.execute(driver,Constant.USERNAME,Constant.PASSWD,Constant.STATUS,Constant.REM_PWD);
////            DriverWait.findElement(driver, new HomePage(driver).LogoutPhoto());//查找是否正确登录进来
////            new GetScreenshot(driver,"修改密码失败");
////            Log.error("修改密码失败");
////            flag = false;
////        }catch (Exception e){
////            Log.info("修改密码成功");
////            driver.close();
////        }
//
//        //还原被修改的账号的密码
//        try {
//            driver.findElement(By.xpath("//a[text()='重置密码']")).click();//点击重置密码按钮
//            driver.findElement(By.id("password")).clear();
//            driver.findElement(By.id("password")).sendKeys(Constant.PASSWD);
//            driver.findElement(By.id("confirmPassword")).clear();
//            driver.findElement(By.id("confirmPassword")).sendKeys(Constant.PASSWD);
//            ContextTab.pressTabkey();
//            ContextTab.pressEnter();//点击enter键进行点击保存按钮
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='重置密码成功！']"))).isDisplayed();
//        }catch (Exception e){
//            new GetScreenshot(driver,"还原密码失败");
//            Log.error("还原密码时，点击保存按钮后，未出现修改密码成功提示");
//            flag = false;
//        }
//
//        Log.endTestCase("修改重置密码字段用例");
//
//        //点击密码解锁按钮
//        driver.findElement(By.xpath("//form[@id='userForm']/div/table/tbody/tr[3]/td[5]/button")).click();
//        try {
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//div[text()='解锁成功！']"))).isDisplayed();
//        }catch (Exception e){
//            new GetScreenshot(driver,"点击解锁按钮没有出现解锁成功提示");
//           Log.error("点击解锁按钮没有出现解锁成功提示");
//            Assert.fail("点击解锁按钮没有出现解锁成功提示");
//        }
//
//        if(driver.findElement(By.id("servPhoneCheckbox")).isSelected()){
//            String [] inputname1 = {"extension","preference"};
//            String [] hintvalue1 = {"分机号码","偏好设置"};
//            for (int i = 0; i < inputname.length; i++) {
//                Log.startTestCase("修改"+hintvalue1[i]+"字段用例");
//                if(eleValueUpdate(driver, By.id(inputname[i]))){
//                    Log.info("修改"+hintvalue1[i]+"字段成功");
//                }else {
//                    Log.error("修改"+hintvalue1[i]+"字段失败");
//                    flag = false;
//                }
//                Log.endTestCase("修改"+hintvalue1[i]+"字段用例");
//            }
//        }else {
//            Log.info("该坐席没有设置允许接电话接入");
//        }
//
//        if(flag){
//            Log.info("修改坐席管理页面重置密码，密码解锁，职位,性别,组织机构，分机号，偏好设置字段成功");
//        }else {
//            Log.error("修改坐席管理页面重置密码，密码解锁，职位,性别,组织机构，分机号，偏好设置字段成功");
//            Assert.fail();
//        }
//    }
//
//    //此方法是判断元素是否被修改以及还原成功
//    public static Boolean eleValueUpdate(WebDriver driver,By by){
//        Boolean flag =true;
//
//        //获取原先被选择的选项元素以及其索引号
//        List<WebElement> listele = driver.findElement(by).findElements(By.tagName("option"));
//        WebElement initvalue = null;
//        int iniindex=0;
//        for (int i = 0; i < listele.size(); i++) {
//            if(listele.get(i).isSelected()){
//                initvalue = listele.get(i);
//                iniindex = i;
//            }
//        }
//        //把原先的选项修改为第一个选项
//        WebElement element = driver.findElement(by);
//        SelectSigleBox.chooseOption(driver, element, 1);//选择下拉框第一个选项
//
//        driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮
//
//        try {
//            DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='ui-pnotify-text']"))).isDisplayed();//判断是否出现保存成功提示
//            Thread.sleep(3000);//等待这个提示消失
//        }catch (Exception e){
//            new GetScreenshot(driver,"修改点击保存后未找到保存成功提示");
//            Log.error("修改点击保存后未找到保存成功提示");
//        }
//
//        //获取被修改过的选项元素索引号
//        List<WebElement> listele2 = driver.findElement(by).findElements(By.tagName("option"));
//        int updateindex=0;
//        for (int i = 0; i < listele2.size(); i++) {
//            if(listele2.get(i).isSelected()){
//                updateindex = i;
//            }
//        }
//        //判断是否修改成功
//        if(updateindex==1){
//            //还原回去
//            SelectSigleBox.chooseOption(driver, element, iniindex);
//            driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();
//            try {
//                DriverWait.findElement(driver, driver.findElement(By.xpath("//div[@class='ui-pnotify-text']"))).isDisplayed();//判断是否出现保存成功提示
//                Thread.sleep(3000);//等待这个提示消失
//            }catch (Exception e){
//                new GetScreenshot(driver,"还原点击保存后未找到保存成功提示");
//                Log.error("还原点击保存后未找到保存成功提示");
//            }
//
//            //判断是否还原成功
//            if(!initvalue.isSelected()){
//                new GetScreenshot(driver,"还原"+iniindex+"选项失败");
//                Log.error("还原"+iniindex+"选项失败");
//                flag =false;
//            }else {
//                Log.info("还原成功");
//            }
//
//        }else {
//            new GetScreenshot(driver,"修改为第一个选项失败");
//            Log.error("修改为第一个选项失败");
//            flag =false;
//        }
//        return flag;
//    }
//
//    @AfterMethod
//    public void afterMethod() {
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction.excut失败");
////            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
////        }
////        driver.quit();
//    }
//}
