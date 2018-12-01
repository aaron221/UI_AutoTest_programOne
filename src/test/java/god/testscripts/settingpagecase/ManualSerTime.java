//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.interactions.Actions;
//import org.testng.Assert;
//import org.testng.annotations.AfterMethod;
//import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
//import org.testng.annotations.Test;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.concurrent.TimeUnit;
//
///**
// * Created by aaron on 2018/10/24
// */
//public class ManualSerSet {
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
//        try {
//            DriverWait.findElement(driver, MenuNameEleLoc.getMenuEle(driver, "设置")).click();//点击设置链接
//            Thread.sleep(1000);
//            driver = driver.switchTo().frame(Constant.SENCOND_IFRAME);
//            DragScrollBar.dragIntoEleTop(driver, MenuNameEleLoc.getMenuEle(driver, "人工服务设置"));
//            MenuNameEleLoc.getMenuEle(driver, "人工服务设置").click();//点击人工服务设置链接
//            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
//            Thread.sleep(1000);
//            DriverWait.findElement(driver,driver.findElement(By.xpath("//form[@id=\"actionForm\"]/div/a"))).isDisplayed();
//        } catch (Exception e) {
//            new GetScreenshot(driver, "进入人工服务设置列表页面失败");
//            Log.error("进入人工服务设置列表页面失败");
//            Assert.fail("进入人工服务设置列表页面失败");
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public void manualSerTimeEdit() {
//        Boolean flag = true;
//        List<String> worklist = new ArrayList<>();
//        List<String> weeklist = new ArrayList<>();
//        Log.startTestCase("编辑修改人工服务设置中的服务时间模块各字段用例");
//        for (int i = 1; i <= 3; i++) {
//            try {
//                DriverWait.findElement(driver, driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[7]/botton[1]"))).click();//点击编辑按钮进入修改页面
//                Thread.sleep(1000);
//                DriverWait.findElement(driver, driver.findElement(By.id("saveBtn"))).isDisplayed();
//            } catch (InterruptedException e) {
//                new GetScreenshot(driver, "进入修改人工服务类型界面失败");
//                Log.error("进入修改人工服务类型界面失败");
//                e.printStackTrace();
//                Assert.fail();
//            }
//       try {
//            //进行保存原始数据以及修改操作
//           worklist.add(driver.findElement(By.id("workTimeval")).getAttribute("value"));
//           weeklist.add(driver.findElement(By.id("weekTimeval")).getAttribute("value"));
//
//        if (i != 3) {
//            Actions action = new Actions(driver);
//            action.moveToElement(driver.findElement(By.id("workTimeval"))).perform();
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("//input[@id='workTimeval']/following-sibling::i")).click();
//            action.moveToElement(driver.findElement(By.id("weekTimeval"))).perform();
//            Thread.sleep(1000);
//            driver.findElement(By.xpath("//input[@id='weekTimeval']/following-sibling::i")).click();
//        }
//        switch (i) {
//            case 1: {
//                driver.findElement(By.id("datetime1")).click();
//                Thread.sleep(1000);
//                DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[20]")));
//                driver.findElement(By.xpath("/html/body/div[3]/div[2]/div/div[1]/div[20]")).click();//选择19:00
//                driver.findElement(By.id("datetime2")).click();
//                Thread.sleep(1000);
//                DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[22]")));
//                driver.findElement(By.xpath("/html/body/div[4]/div[2]/div/div[1]/div[22]")).click();//选择21:00
//                driver.findElement(By.xpath("//input[@id='datetime2']/following-sibling::button")).click();//点击添加按钮
//
//                driver.findElement(By.id("datetime3")).click();
//                Thread.sleep(1000);
//                DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[20]")));
//                driver.findElement(By.xpath("/html/body/div[5]/div[2]/div/div[1]/div[20]")).click();//选择19:00
//                driver.findElement(By.id("datetime4")).click();
//                Thread.sleep(1000);
//                DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[1]/div[22]")));
//                driver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div[1]/div[22]")).click();//选择21:00
//                driver.findElement(By.xpath("//input[@id='datetime4']/following-sibling::button")).click();//点击添加按钮
//                break;
//            }
//            case 2: {
//                //判断是否修改成功
//                if (worklist.get(1).equals("19:00-21:00")) {
//                    Log.info("修改工作日服务时间字段成功");
//                } else {
//                    Log.error("修改工作日服务时间字段失败");
//                    flag = false;
//                }
//
//                if (weeklist.get(1).equals("19:00-21:00")) {
//                    Log.info("修改节假日服务时间字段成功");
//                } else {
//                    Log.error("修改节假日服务时间字段失败");
//                    flag = false;
//                }
//
//                //进行还原操作
//
//                driver.findElement(By.id("datetime1")).sendKeys(worklist.get(0).split("-")[0]);
//                driver.findElement(By.id("datetime2")).sendKeys(worklist.get(0).split("-")[1]);
//                driver.findElement(By.xpath("//input[@id='datetime2']/following-sibling::button")).click();//点击添加按钮
//                driver.findElement(By.id("datetime3")).sendKeys(weeklist.get(0).split("-")[0]);
//                driver.findElement(By.id("datetime4")).sendKeys(weeklist.get(0).split("-")[1]);
//                driver.findElement(By.xpath("//input[@id='datetime4']/following-sibling::button")).click();//点击添加按钮
//                break;
//            }
//            case 3: {
//                //判断是否还原成功
//                if (worklist.get(2).equals(worklist.get(0))) {
//                    Log.info("还原工作服务时间字段成功");
//                } else {
//                    Log.error("还原工作服务时间失败");
//                    flag = false;
//                }
//                if (weeklist.get(2).equals(weeklist.get(0))) {
//                    Log.info("还原节假日服务时间字段成功");
//                } else {
//                    Log.error("还原节假日服务时间失败");
//                    flag = false;
//                }
//                break;
//            }
//        }
//
//            driver.findElement(By.id("saveBtn")).click();//点击保存按钮
//            Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                new GetScreenshot(driver,"修改还原服务时间模块各字段时发生了错误");
//                Log.error("修改还原服务时间模块各字段时发生了错误");
//                e.printStackTrace();
//            }
//        }
//        if(flag){
//            Log.info("编辑修改人工服务设置中的基本设置模块各字段成功");
//        }else {
//            Log.error("编辑修改人工服务设置中的基本设置模块各字段失败");
//            Assert.fail();
//        }
//        Log.endTestCase("编辑修改人工服务设置中的基本设置模块各字段用例");
//    }
//
//    @AfterMethod
//    public void afterMethod(){
////        try {
////            LogoutAction.excut(driver);
////        } catch (Exception e) {
////            Log.error("执行LogoutAction.excut失败");
////            new GetScreenshot(driver,"用例名称退出时候执行LogoutAction.excut失败");
////        }
////        driver.quit();
//    }
//}
