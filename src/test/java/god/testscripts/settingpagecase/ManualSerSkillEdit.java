//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
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
//public class ManualSerSkillEdit {
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
//    public void manualSerBaseEdit() {
//        Boolean flag = true;
//        List<Boolean> listskill = new ArrayList<>();
//        Log.startTestCase("修改人工服务设置技能字段用例");
//        try {
//            for (int i = 1; i <= 3; i++) {
//                try {
//                    DriverWait.findElement(driver, driver.findElement(By.xpath("/html/body/div[1]/div/table/tbody/tr[2]/td[7]/botton[1]"))).click();//点击编辑按钮进入修改页面
//                    Thread.sleep(1000);
//                    DriverWait.findElement(driver, driver.findElement(By.id("saveBtn"))).isDisplayed();
//                } catch (InterruptedException e) {
//                    new GetScreenshot(driver, "进入修改人工服务类型界面失败");
//                    Log.error("进入修改人工服务类型界面失败");
//                    e.printStackTrace();
//                    Assert.fail();
//                }
//
//                listskill.add(driver.findElement(By.xpath("//table[@id=\"skillDataTable\"]/tbody/tr[2]/td[1]/div/input")).isSelected());
//                switch (i) {
//                    case 1: {
//                        //点击第一个技能进行修改操作
//                        driver.findElement(By.xpath("//table[@id=\"skillDataTable\"]/tbody/tr[2]/td[1]/div/input")).click();
//                        break;
//                    }
//                    case 2: {
//                        //判断是否修改成功
//                        if(listskill.get(1)!=listskill.get(0)){
//                            Log.info("修改技能成功");
//                        }else {
//                            Log.error("修改技能失败");
//                            flag = false;
//                        }
//                        //点击第一个技能进行还原操作
//                        driver.findElement(By.xpath("//table[@id=\"skillDataTable\"]/tbody/tr[2]/td[1]/div/input")).click();
//                        break;
//                    }
//                case 3: {
//                    //判断是否还原成功
//                    if(listskill.get(2)==listskill.get(0)){
//                        Log.info("还原技能成功");
//                    }else {
//                        Log.error("还原技能失败");
//                        flag = false;
//                    }
//                    break;
//                }
//            }
//
//            driver.findElement(By.id("saveBtn")).click();//点击保存按钮
//            Thread.sleep(1000);
//                }
//        } catch (InterruptedException e) {
//            new GetScreenshot(driver,"修改人工服务设置技能字段发生了错误");
//            Log.error("修改人工服务设置技能字段过程中发生了错误");
//            e.printStackTrace();
//        }
//
//        if(flag){
//            Log.info("修改人工服务设置技能字段成功");
//        }else {
//            Log.error("修改人工服务设置技能字段失败");
//        }
//        Log.endTestCase("修改人工服务设置技能字段用例");
//        }
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
//
//}
