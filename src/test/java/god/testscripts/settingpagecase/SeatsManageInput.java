//package god.testscripts.settingpagecase;
//import god.appmodules.LoginAction;
//import god.appmodules.LogoutAction;
//import god.util.*;
//import god.util.waitUse.ContextTab;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by thomas on 2018/10/10
// */
//public class SeatsManageInput {
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
//            By saveby = By.xpath("//footer[@class='text-center']/button[1]");
//            Boolean flag = true;
//            //对输入框的各个元素进行修改以及还原操作
//
//        String [] inputname = {"name","crmName","email","tel"};
//        String [] hintvalue = {"姓名","昵称","邮箱","联系电话"};
//        String [] updatavalue = {"aaron","aaron","emailname@163.com","13633456766"};
//        for (int i = 0; i < inputname.length; i++) {
//            Log.startTestCase("修改"+hintvalue[i]+"字段用例");
//            if(EditEle.editInputEle(driver,By.id("name"),updatavalue[i],saveby)){
//                Log.info("修改"+hintvalue[i]+"字段成功");
//            }else {
//                Log.error("修改"+hintvalue[i]+"字段失败");
//                flag = false;
//            }
//            Log.endTestCase("修改"+hintvalue[i]+"字段用例");
//        }
//
//        Log.startTestCase("修改坐席管理界面接入量字段用例");
//        if(EditEle.editInputEle(driver, By.name("defaultConnCount"), "66",saveby)){
//            Log.info("修改坐席管理界面接入量成功");
//        } else {
//            Log.error("修改坐席管理界面接入量失败");
//            flag = false;
//        }
//        Log.endTestCase("修改坐席管理界面接入量字段用例");
//
//
//        if(driver.findElement(By.id("servPhoneCheckbox")).isSelected()){
//            String [] inputname1 = {"defaultPhoneMaxcount","sapcingInterval","bindMobileWorkTime"};
//            String [] hintvalue1 = {"最大接入量","话后切换时间","通过输入模式修改转接时间"};
//            String [] updatavalue1 = {"66","33","00:00-23:00"};
//            for (int i = 0; i < inputname.length; i++) {
//                Log.startTestCase("修改"+hintvalue1[i]+"字段用例");
//                if(EditEle.editInputEle(driver,By.id("name"),updatavalue1[i],saveby)){
//                    Log.info("修改"+hintvalue1[i]+"字段成功");
//                }else {
//                    Log.error("修改"+hintvalue1[i]+"字段失败");
//                    flag = false;
//                }
//                Log.endTestCase("修改"+hintvalue1[i]+"字段用例");
//            }
//
//            //通过按钮点击修改转接时间段
//            Log.startTestCase("修改坐席管理界面通过点击按钮模式修改转接时间字段用例");
//            String timevalue = null;
//            try {
//                WebElement element = driver.findElement(By.id("bindMobileWorkTime"));
//                timevalue = element.getAttribute("value");//把修改前的姓名给保存到变量中，后面好还原
//                element.clear();//清除原先的姓名元素的value值
//
//                driver.findElement(By.id("date1")).click();
//                driver.findElement(By.xpath("/html/body/div[10]/div[2]/div/div[1]/div[1]")).click();//选中00:00开始时间
//                driver.findElement(By.xpath("/html/body/div[11]/div[2]/div/div[1]/div[48]")).click();//选中23:30结束时间
//                driver.findElement(By.xpath("//input[@id='date2']/following-sibling::i")).click();//点击加号按钮
//
//                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮
//                Thread.sleep(1000);
//                String timeupdate = driver.findElement(By.id("bindMobileWorkTime")).getAttribute("value");
//                //判断是否修改成功
//                if(!timevalue.equals(timeupdate)){
//                    Log.info("通过按钮修改转接时间成功");
//                }else {
//                    new GetScreenshot(driver,"通过按钮修改转接时间失败");
//                    Log.error("通过按钮修改转接时间失败");
//                    flag = false;
//                }
//            }catch (Exception e){
//                new GetScreenshot(driver,"点击保存按钮前出错了");
//                e.printStackTrace();
//                Log.error("点击保存按钮前出错了");
//                flag = false;
//            }
//
//
//            //把原先的转接时间还原回去
//            try {
//                driver.findElement(By.id("bindMobileWorkTime")).clear();//清除修改的转接时间
//                driver.findElement(By.id("bindMobileWorkTime")).sendKeys(timevalue);
//                driver.findElement(By.xpath("//footer[@class='text-center']/button[1]")).click();//点击保存按钮
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                new GetScreenshot(driver,"还原时候点击保存按钮前出错误了");
//                e.printStackTrace();
//                Log.error("还原时候点击保存按钮前出错误了");
//                flag = false;
//            }
//            //判断是否还原成功
//            try {
//                if(driver.findElement(By.id("bindMobileWorkTime")).getAttribute("value").equals(timevalue)){
//                    Log.info("转接时间还原成功");
//                }else {
//                    new GetScreenshot(driver,"转接时间还原失败");
//                    Log.error("转接时间还原失败");
//                    flag = false;
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//                Log.error("判断是否还原的时候出错了");
//                flag = false;
//            }
//
//        }else {
//            new GetScreenshot(driver,"该坐席设置的是不允许电话接入");
//            Log.info("该坐席设置的是不允许电话接入");
//        }
//        Log.endTestCase("修改坐席管理界面通过点击按钮模式修改转接时间字段用例");
//
//        if(flag){
//            Log.info("修改坐席界面的最大接入量,话后切换时间,转接时间字段成功");
//        }else {
//            Log.error("修改坐席界面的最大接入量,话后切换时间,转接时间字段失败");
//        }
//    }
//
//    @AfterMethod
//    public void afterMethod(){
//        try {
//            LogoutAction.excut(driver);
//        } catch (Exception e) {
//            Log.error("执行LogoutAction.excut失败");
//            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
//        }
//        driver.quit();
//    }
//}
