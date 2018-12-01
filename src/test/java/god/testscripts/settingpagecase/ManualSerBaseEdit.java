//package god.testscripts.settingpagecase;
//
//import god.appmodules.LoginAction;
//import god.util.*;
//import org.apache.log4j.xml.DOMConfigurator;
//import org.openqa.selenium.By;
//import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebElement;
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
//public class ManualSerBaseEdit {
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
//        List<Integer> initindex = new ArrayList<>();
//        List<String> initdesciption = new ArrayList<>();
//        List<Boolean> usestate = new ArrayList<>();
//        List<String> inittypename = new ArrayList<>();
//        Log.startTestCase("编辑修改人工服务设置中的基本设置模块各字段用例");
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
//
//            //进行保存原始数据以及修改操作
//            //获取原始服务时间内外图标
//            try {
//                FileIo.loadFile(driver.findElement(By.id("onlinePicPreview")), "F:\\SvnData\\trunk\\manulServiceInitin" + i + ".jpg");
//                FileIo.loadFile(driver.findElement(By.id("offlinePicPreview")), "F:\\SvnData\\trunk\\manulServiceInitout" + i + ".jpg");
//            } catch (Exception e) {
//                Log.error("下载原始图片时发生了错误");
//                e.printStackTrace();
////                Assert.fail();
//            }
//
//            //获取所属服务中心被选中的选项
//            List<WebElement> options = driver.findElement(By.id("organId")).findElements(By.tagName("option"));
//            //判断原始以及修改上传后所属服务中心是否被选中
//            for (int j = 0; j < options.size(); j++) {
//                if (options.get(j).isSelected()) {
//                    initindex.add(j + 1);
//                    inittypename.add(driver.findElement(By.id("name")).getAttribute("value")); //获取类型名称
//                    usestate.add(driver.findElement(By.id("enableChk")).isSelected());//获取使用状态是否被选中
//                    initdesciption.add(driver.findElement(By.id("remark")).getText());//获取原始描述字段内容
//                }
//            }
//                try {
//                    if (i != 3) {
//                        //进行修改操作
//                        driver.findElement(By.id("name")).clear();
//                        driver.findElement(By.id("onlinePicLogo")).clear();
//                        driver.findElement(By.id("offlinePicLogo")).clear();
//                        driver.findElement(By.id("remark")).clear();
//                    }
//                    switch (i) {
//                        case 1: {
//                            SelectSigleBox.chooseOption(driver, driver.findElement(By.id("organId")), 2);
//                            driver.findElement(By.id("name")).sendKeys("人工服务类型");
//                            driver.findElement(By.xpath("//input[@id='enableChk']/following-sibling::span")).click();
//                            driver.findElement(By.id("onlinePicLogo")).sendKeys("F:\\SvnData\\trunk\\manulService1.jpg");
//                            driver.findElement(By.id("offlinePicLogo")).sendKeys("F:\\SvnData\\trunk\\manulService2.jpg");
//                            driver.findElement(By.id("remark")).sendKeys("人工服务描述内容");
//                            break;
//                        }
//                        case 2: {
//                            //判断是否修改成功
//                            if (initindex.get(1) == 3) {
//                                Log.info("修改所属服务中心字段成功");
//                            } else {
//                                Log.error("修改所属服务中心字段失败");
//                                flag = false;
//                            }
//
//                            if (inittypename.get(1).equals("人工服务类型")) {
//                                Log.info("修改类型名称字段成功");
//                            } else {
//                                Log.error("修改类型名称字段失败");
//                                flag = false;
//                            }
//
//                            if (usestate.get(1) != usestate.get(0)) {
//                                Log.info("修改使用状态字段成功");
//                            } else {
//                                Log.error("修改使用状态字段失败");
//                                flag = false;
//                            }
//
//                            try {
//                                if (CompareImg.isSameOne("F:\\SvnData\\trunk\\manulServiceInitin2.jpg", "F:\\SvnData\\trunk\\manulService1.jpg")) {
//                                    Log.info("修改服务时间内图标字段成功");
//                                } else {
//                                    Log.error("修改服务时间内图标字段失败");
//                                    flag = false;
//                                }
//
//                                if (CompareImg.isSameOne("F:\\SvnData\\trunk\\manulServiceInitout2.jpg", "F:\\SvnData\\trunk\\manulService2.jpg")) {
//                                    Log.info("修改服务时间外图标字段成功");
//                                } else {
//                                    Log.error("修改服务时间外图标字段失败");
//                                    flag = false;
//                                }
//                            } catch (Exception e) {
//                                Log.error("判断服务时间内外图标字段是否修改成功时发生了错误");
//                                e.printStackTrace();
//                                flag = false;
//                            }
//
//                            if (initdesciption.get(1).equals("人工服务描述内容")) {
//                                Log.info("修改描述内容字段成功");
//                            } else {
//                                Log.error("修改藐视内容字段失败");
//                                flag = false;
//                            }
//                            //进行还原操作
//                            SelectSigleBox.chooseOption(driver, driver.findElement(By.id("organId")), (initindex.get(0) - 1));
//                            driver.findElement(By.id("name")).sendKeys(inittypename.get(0));
//                            driver.findElement(By.xpath("//input[@id='enableChk']/following-sibling::span")).click();
//                            driver.findElement(By.id("onlinePicLogo")).sendKeys("F:\\SvnData\\trunk\\manulServiceInitin1.jpg");
//                            driver.findElement(By.id("offlinePicLogo")).sendKeys("F:\\SvnData\\trunk\\manulServiceInitout1.jpg");
//                            driver.findElement(By.id("remark")).sendKeys(initdesciption.get(0));
//                            break;
//                        }
//                        case 3: {
//                            //判断是否还原成功
//                            if (initindex.get(2).equals(initindex.get(0))) {
//                                Log.info("还原所属服务中心字段成功");
//                            } else {
//                                Log.error("还原所属服务中心字段失败");
//                                flag = false;
//                            }
//
//                            if (inittypename.get(2).equals(inittypename.get(0))) {
//                                Log.info("还原类型名称字段成功");
//                            } else {
//                                Log.error("还原类型名称字段失败");
//                                flag = false;
//                            }
//
//                            if (usestate.get(2) == usestate.get(0)) {
//                                Log.info("还原使用状态字段成功");
//                            } else {
//                                Log.error("还原使用状态字段失败");
//                                flag = false;
//                            }
//
//                            try {
//                                if (CompareImg.isSameOne("F:\\SvnData\\trunk\\manulServiceInitin3.jpg", "F:\\SvnData\\trunk\\manulServiceInitin1.jpg")) {
//                                    Log.info("还原服务时间内图标字段成功");
//                                } else {
//                                    Log.error("还原服务时间内图标字段失败");
//                                    flag = false;
//                                }
//
//                                if (CompareImg.isSameOne("F:\\SvnData\\trunk\\manulServiceInitout3.jpg", "F:\\SvnData\\trunk\\manulServiceInitout1.jpg")) {
//                                    Log.info("修改服务时间外图标字段成功");
//                                } else {
//                                    Log.error("修改服务时间外图标字段失败");
//                                    flag = false;
//                                }
//                            } catch (Exception e) {
//                                Log.error("判断服务时间内外图标字段是否还原成功时发生了错误");
//                                e.printStackTrace();
//                                flag = false;
//                            }
//
//                            if (initdesciption.get(2).equals(initdesciption.get(0))) {
//                                Log.info("还原描述内容字段成功");
//                            } else {
//                                Log.error("还原藐视内容字段失败");
//                                flag = false;
//                            }
//                            break;
//                        }
//                    }
//                    driver.findElement(By.id("saveBtn")).click();//点击保存按钮
//                    Thread.sleep(1000);
//
//                } catch (Exception e) {
//                    Log.error("修改还原过程中发生了错误");
//                    e.printStackTrace();
//                    flag = false;
//                }
//        }
//            if (flag) {
//                Log.info("编辑修改人工服务设置中的基本设置模块各字段成功");
//            } else {
//                Log.error("编辑修改人工服务设置中的基本设置模块各字段失败");
//                Assert.fail();
//            }
//        Log.endTestCase("编辑修改人工服务设置中的基本设置模块各字段用例");
//        }
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
