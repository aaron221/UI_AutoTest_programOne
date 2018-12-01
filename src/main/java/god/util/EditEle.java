package god.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2018/10/17.
 * 此类中的方法是对用例编写过程中对修改的各种元素进行判断是否修改成功
 */
public class EditEle {
    //此方法是判断input标签元素是否被修改以及还原成功
    public static Boolean editInputEle(WebDriver driver, By inputBy,String updateData,By buttonBy){
        Boolean flag =true;
        WebElement element = null;
        String namevalue = null;//把修改前的内容给保存到变量中，后面好还原
        try {
            DragScrollBar.dragToEleBot(driver,driver.findElement(buttonBy));
            Thread.sleep(1000);
            element = driver.findElement(inputBy);
            namevalue = element.getAttribute("value");
            element.clear();//清楚原先的姓名元素的value值
            element.sendKeys(updateData);
            driver.findElement(buttonBy).click();//点击保存按钮
            CoverEleWait.getHideEle(driver,inputBy);
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"点击保存按钮之前的操作有误");
            Log.error("点击保存按钮之前的操作有误");
            e.printStackTrace();
            Assert.fail();
        }

            if (element.getAttribute("value").equals(updateData)) {
                //修改为原先的值
                try {
                    Thread.sleep(1000);
                    DragScrollBar.dragIntoEleTop(driver, driver.findElement(buttonBy));
                    driver.findElement(inputBy).clear();
                    DragScrollBar.dragIntoEleTop(driver,element);
                    element.sendKeys(namevalue);
                    driver.findElement(buttonBy).click();//点击保存按钮
                    CoverEleWait.getHideEle(driver, inputBy);
                } catch (InterruptedException e) {
                    new GetScreenshot(driver,"修改为原先值的时候出错");
                    Log.error("修改为原先值的时候出错");
                    e.printStackTrace();
                    Assert.fail();
                }

                if(!element.getAttribute("value").equals(namevalue)){
                    new GetScreenshot(driver, "还原"+namevalue+"失败");
                    flag =false;
                }
            }else {
                new GetScreenshot(driver, "修改"+updateData+"失败");
                flag = false;
            }
            return flag;
        }

    public static Boolean editInputEle(WebDriver driver, By editby,By inputBy,String updateData,By buttonBy){
        Boolean flag =true;
        WebElement element = null;
        String namevalue = null;//把修改前的内容给保存到变量中，后面好还原
        try {
            DragScrollBar.dragToEleBot(driver,driver.findElement(buttonBy));
            Thread.sleep(1000);
            element = driver.findElement(inputBy);
            namevalue = element.getAttribute("value");
            driver.findElement(editby).click();
            element.clear();//清除原先的姓名元素的value值
            element.sendKeys(updateData);
            driver.findElement(buttonBy).click();//点击保存按钮
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"点击保存按钮之前的操作有误");
            Log.error("点击保存按钮之前的操作有误");
            e.printStackTrace();
            Assert.fail();
        }

        if (element.getAttribute("value").equals(updateData)) {
            //修改为原先的值
            try {
                DragScrollBar.dragToEleBot(driver,driver.findElement(buttonBy));
                Thread.sleep(1000);
                driver.findElement(editby).click();
                element.clear();
                element.sendKeys(namevalue);
                driver.findElement(buttonBy).click();//点击保存按钮
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"修改为原先值的时候出错");
                Log.error("修改为原先值的时候出错");
                e.printStackTrace();
                Assert.fail();
            }
            if(!element.getAttribute("value").equals(namevalue)){
                new GetScreenshot(driver, "还原"+namevalue+"失败");
                flag =false;
            }
        }else {
            new GetScreenshot(driver, "修改"+updateData+"失败");
            flag = false;
        }
        return flag;
    }

    //此方法是判断selec标签元素是否被修改以及还原成功
    public static Boolean editSelEle(WebDriver driver,WebElement selelement,By butBy){
        Boolean flag =true;
        //获取原先被选择的选项元素以及其索引号
        WebElement initvalue = null;
        int iniindex= 0;
        try {
            List<WebElement> listele = DriverWait.findElement(driver,selelement).findElements(By.tagName("option"));
            initvalue = null;
            iniindex = 0;
            for (int i = 0; i < listele.size(); i++) {
                if(listele.get(i).isSelected()){
                    initvalue = listele.get(i);
                    iniindex = i;
                    break;
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"获取单选框的原始值索引失败");
            Log.error("获取单选框的原始值索引失败");
            e.printStackTrace();
            Assert.fail();
        }


        //把原先的选项修改为第一个选项
        try {
            DragScrollBar.dragToEleBot(driver, selelement);
            SelectSigleBox.chooseOption(driver, selelement, 1);//选择下拉框第一个选项
            DragScrollBar.dragToEleBot(driver, driver.findElement(butBy));
            Thread.sleep(1000);
            driver = CoverEleWait.getDriver(driver,selelement);
            DriverWait.findElement(driver,driver.findElement(butBy)).click();//点击保存按钮
//            Thread.sleep(3000);//等待消息通知消失
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改单选框为第一个选项失败");
            Log.error("修改单选框为第一个选项失败");
            e.printStackTrace();
            Assert.fail();
        }

        //获取被修改过的选项元素索引号
        List<WebElement> listele2 = DriverWait.findElement(driver,selelement).findElements(By.tagName("option"));
        int updateindex=0;
        for (int i = 0; i < listele2.size(); i++) {
            if(listele2.get(i).isSelected()){
                updateindex = i;
            }
        }
        //判断是否修改成功
        if(updateindex==1){
            //还原回去
            try {
                DragScrollBar.dragToEleBot(driver, selelement);
                SelectSigleBox.chooseOption(driver, selelement, iniindex);
                DragScrollBar.dragToEleBot(driver,driver.findElement(butBy));
                Thread.sleep(1000);
                driver = CoverEleWait.getDriver(driver,selelement);
                DriverWait.findElement(driver,driver.findElement(butBy)).click();
//                Thread.sleep(3000);//等待消息通知消失
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"还原失败");
                Log.error("还原失败");
                e.printStackTrace();
            }
            //判断是否还原成功
            if(!initvalue.isSelected()){
                new GetScreenshot(driver,"还原"+iniindex+"选项失败");
                Log.error("还原"+iniindex+"选项失败");
                flag =false;
            }else {
//                Log.info("还原成功");
            }

        }else {
            new GetScreenshot(driver,"修改为第一个选项失败");
            Log.error("修改为第一个选项失败");
            flag =false;
        }
        return flag;
    }

    //此方法是判断selec标签元素是否被修改以及还原成功
    public static Boolean editSelEle(WebDriver driver,By selBy,By butBy){
        Boolean flag =true;
        //获取原先被选择的选项元素以及其索引号
        WebElement initvalue = null;
        int iniindex= 0;
        try {
            List<WebElement> listele = DriverWait.findElement(driver,driver.findElement(selBy)).findElements(By.tagName("option"));
            initvalue = null;
            iniindex = 0;
            for (int i = 0; i < listele.size(); i++) {
                if(listele.get(i).isSelected()){
                    initvalue = listele.get(i);
                    iniindex = i;
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"获取单选框的原始值索引失败");
            Log.error("获取单选框的原始值索引失败");
            e.printStackTrace();
            Assert.fail();
        }

        //把原先的选项修改为第一个选项
        WebElement element = null;
        try {
            element = driver.findElement(selBy);
            SelectSigleBox.chooseOption(driver, element, 1);//选择下拉框第一个选项
            DragScrollBar.dragToEleBot(driver,driver.findElement(butBy));
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(butBy)).click();//点击保存按钮
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改单选框为第一个选项失败");
            Log.error("修改单选框为第一个选项失败");
            e.printStackTrace();
            Assert.fail();
        }

        //获取被修改过的选项元素索引号
        List<WebElement> listele2 = DriverWait.findElement(driver,driver.findElement(selBy)).findElements(By.tagName("option"));
        int updateindex=0;
        for (int i = 0; i < listele2.size(); i++) {
            if(listele2.get(i).isSelected()){
                updateindex = i;
            }
        }
        //判断是否修改成功
        if(updateindex==1){
            //还原回去
            try {
                SelectSigleBox.chooseOption(driver, element, iniindex);
                DragScrollBar.dragToEleBot(driver,driver.findElement(butBy));
                Thread.sleep(1000);
                DriverWait.findElement(driver,driver.findElement(butBy)).click();
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"还原失败");
                Log.error("还原失败");
                e.printStackTrace();
            }
            //判断是否还原成功
            if(!initvalue.isSelected()){
                new GetScreenshot(driver,"还原"+iniindex+"选项失败");
                Log.error("还原"+iniindex+"选项失败");
                flag =false;
            }else {
//                Log.info("还原成功");
            }

        }else {
            new GetScreenshot(driver,"修改为第一个选项失败");
            Log.error("修改为第一个选项失败");
            flag =false;
        }
        return flag;
    }

    public static Boolean editSelEle(WebDriver driver,By selBy,String value,By butBy){
        Boolean flag =true;
        //获取原先被选择的选项元素value值
        String initvalue = null;
        try {
            List<WebElement> listele = DriverWait.findElement(driver,driver.findElement(selBy)).findElements(By.tagName("option"));
            initvalue = null;
            for (int i = 0; i < listele.size(); i++) {
                if(listele.get(i).isSelected()){
                    initvalue = listele.get(i).getAttribute("value");
                    break;
                }
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"获取单选框的原始值索引失败");
            Log.error("获取单选框的原始值索引失败");
            e.printStackTrace();
            Assert.fail();
        }

        //把原先的选项修改为指定value值的选项
        WebElement element = null;
        try {
            element = driver.findElement(selBy);
            SelectSigleBox.chooseOption(driver, value, element);//选择下拉框value值为value的选项
            DragScrollBar.dragToEleBot(driver,driver.findElement(butBy));
            Thread.sleep(1000);
            DriverWait.findElement(driver,driver.findElement(butBy)).click();//点击保存按钮
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            new GetScreenshot(driver,"修改单选框为第一个选项失败");
            Log.error("修改单选框为第一个选项失败");
            e.printStackTrace();
            Assert.fail();
        }

        //获取被修改过的被选择的选项的value值
        List<WebElement> listele2 = DriverWait.findElement(driver,driver.findElement(selBy)).findElements(By.tagName("option"));
        String updatevalue=null;
        for (int i = 0; i < listele2.size(); i++) {
            if(listele2.get(i).isSelected()){
                updatevalue= listele2.get(i).getAttribute("value");
                break;
            }
        }
        //判断是否修改成功
        if(updatevalue.equals(value)){
            //还原回去
            try {
                SelectSigleBox.chooseOption(driver, initvalue, element);
                DragScrollBar.dragToEleBot(driver,driver.findElement(butBy));
                Thread.sleep(1000);
                DriverWait.findElement(driver,driver.findElement(butBy)).click();
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                new GetScreenshot(driver,"还原失败");
                Log.error("还原失败");
                e.printStackTrace();
            }
            //判断是否还原成功
            //获取被修改过的被选择的选项的value值
            List<WebElement> listele3 = DriverWait.findElement(driver,driver.findElement(selBy)).findElements(By.tagName("option"));
            String initvalue2=null;
            for (int i = 0; i < listele3.size(); i++) {
                if(listele3.get(i).isSelected()){
                    initvalue2= listele3.get(i).getAttribute("value");
                    break;
                }
            }
            if(initvalue2.equals(initvalue)){
                 Log.info("还原成功");
            }else {
                new GetScreenshot(driver,"还原"+value+"选项失败");
                Log.error("还原"+value+"选项失败");
                flag =false;
            }

        }else {
            new GetScreenshot(driver,"修改指定value值失败");
            Log.error("修改指定value值失败");
            flag =false;
        }
        return flag;
    }

    public static Boolean editImgEle(WebDriver driver,By byimag,By byclick,By bysavebutt){
            Boolean flag = true;
            //下载好原始logo图片
            String initcomlogo = "F:\\SvnData\\trunk\\initcomlogo.jpg";
            try {
                DragScrollBar.dragToEleBot(driver,driver.findElement(bysavebutt));
                Thread.sleep(1000);
                FileIo.loadFile(driver.findElement(byimag), initcomlogo);
//                Log.info("下载原始图片成功");
            } catch (Exception e) {
                new GetScreenshot(driver,"下载原始图片失败");
                e.printStackTrace();
                flag = false;
            }

            String uploadfileone = "F:\\SvnData\\trunk\\uploadfileone.jpg";
            try {
                driver.findElement(byclick).sendKeys(uploadfileone);
                Thread.sleep(2000);//等待图片上传完毕
                driver.findElement(By.id("updateBtn")).click();//点击保存按钮
            } catch (InterruptedException e) {
                e.printStackTrace();
                new GetScreenshot(driver,"上传修改图片失败");
                Log.error("上传修改图片失败");
                flag =false;
                Assert.fail("上传修改图片失败");
            }

            //重新下载好被上传的logo图片
            String reloadfiletwo = "F:\\SvnData\\trunk\\reloadfiletwo.jpg";
            try {
                DragScrollBar.dragToEleBot(driver,driver.findElement(bysavebutt));
                Thread.sleep(1000);
                FileIo.loadFile(driver.findElement(byimag),reloadfiletwo);
//                Log.info("下载被上传的图片成功");
            } catch (Exception e) {
                new GetScreenshot(driver,"下载被上传的logo图片失败");
                e.printStackTrace();
                flag =false;
            }

            //判断是否上传成功
            try {
                if(CompareImg.isSameOne(uploadfileone,reloadfiletwo)){
//                    Log.info("判断上传修改图片成功");
                }else {
                    new GetScreenshot(driver,"判断上传图片失败");
                    Log.error("判断上传修改图片失败");
                    flag =false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //还原原先的图片
            try {
                driver.findElement(byclick).sendKeys(initcomlogo);
                Thread.sleep(2000);//等待图片上传完毕
                driver.findElement(By.id("updateBtn")).click();//点击保存按钮
            } catch (InterruptedException e) {
                e.printStackTrace();
                new GetScreenshot(driver,"上传原始图片失败");
                Log.error("上传原始图片失败");
                flag =false;
            }

            //判断是否还原成功
            String reloadthree= "F:\\SvnData\\trunk\\reloadthree.jpg";
            try {
                DragScrollBar.dragToEleBot(driver,driver.findElement(bysavebutt));
                Thread.sleep(1000);
                FileIo.loadFile(driver.findElement(byimag), reloadthree);
//                Log.info("下载被上传的原始图片成功");
            } catch (Exception e) {
                new GetScreenshot(driver,"下载被上传的原始图片失败");
                e.printStackTrace();
                flag =false;
            }
            //判断是否上传成功
            try {
                if(CompareImg.isSameOne(reloadthree,initcomlogo)){
//                    Log.info("还原图片成功");
                }else {
                    new GetScreenshot(driver,"还原图片失败");
                    Log.error("还原图片失败");
                    flag =false;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //删除多余的图片
            try {
                FileIo.delFile(reloadthree);
                FileIo.delFile(initcomlogo);
                FileIo.delFile(reloadfiletwo);
            } catch (Exception e) {
                Log.error("删除多余的logo图片失败");
                e.printStackTrace();
            }
            return flag;
        }

    //此方法是为了判断修改单选按钮以及还原是否成功
    public static Boolean editSigCboxEle(WebDriver driver,By checkboxBy,By saveBy){
        Boolean flag =true;
        //保留元素原始是否被选中
        try {
            Boolean isseleted=driver.findElement(checkboxBy).isSelected();
            try {
                driver.findElement(checkboxBy).click();
            }catch (Exception e){
                driver.findElement(checkboxBy).findElement(By.xpath("following-sibling::span")).click();
            }
            driver.findElement(saveBy).click();//点击保存按钮
            Thread.sleep(4000);//等待通知提示消失
            if(driver.findElement(checkboxBy).isSelected()==isseleted){
                new GetScreenshot(driver,"点击修改单个复选框失败");
                Log.error("点击修改单个复选框失败");
                flag = false;
            }else {
                Log.info("点击修改单个复选框成功");
            }
            //再次点击还原
            try {
                driver.findElement(checkboxBy).click();
            }catch (Exception e){
                driver.findElement(checkboxBy).findElement(By.xpath("following-sibling::span")).click();
            }

            driver.findElement(saveBy).click();//点击保存按钮
            Thread.sleep(4000);//等待通知提示消失

            if(driver.findElement(checkboxBy).isSelected()==isseleted){
                Log.info("点击还原成功");
            }else {
                new GetScreenshot(driver,"点击还原失败");
                Log.error("点击还原失败");
                flag = false;
            }
        } catch (Exception e) {
            new GetScreenshot(driver,"修改单选框元素时定位元素失败");
            Log.error("修改单选框元素时定位元素失败");
            e.printStackTrace();
            flag = false;
        }
        return flag;
    }

    //此方法是判断多个选项复选框元素是否被修改以及还原成功-有二个tagname
    public static Boolean editMultCboxEle(WebDriver driver,By parentby,By saveby,String childTagname,String grandchildTagname) {
        Boolean flag = true;
        //获取某个复选框下的所有元素
        if (childTagname != null) {
            try {
                List<WebElement> spanele = driver.findElement(parentby).findElements(By.tagName(childTagname));
                List<WebElement> inputele = new ArrayList<WebElement>();
                for (WebElement element : spanele) {
                    inputele.add(element.findElement(By.tagName(grandchildTagname)));
                }

                //判断原始各个input元素是否被选中
                Boolean[] isseleted = new Boolean[inputele.size()];
                for (int i = 0; i < inputele.size(); i++) {
                    if (inputele.get(i).isSelected()) {
                        isseleted[i] = true;
                    } else {
                        isseleted[i] = false;
                    }
                }
                //对各个input元素进行点击一次修改操作
                for (int i = 0; i < inputele.size(); i++) {
                    inputele.get(i).click();
                }

                driver.findElement(saveby).click();//点击保存按钮

                //判断被点击修改过后各个input元素是否被选中
                Boolean[] isseleted2 = new Boolean[inputele.size()];
                for (int i = 0; i < inputele.size(); i++) {
                    if (inputele.get(i).isSelected()) {
                        isseleted2[i] = true;
                    } else {
                        isseleted2[i] = false;
                    }
                }

                //判断二个数组是否相等
                if (isseleted == isseleted2) {
                    new GetScreenshot(driver, "点击复选框失败");
                    Log.error("点击复选框失败");
                    flag = false;
                } else {
//                    Log.info("点击修改复选框成功");
                }

                //对各个input元素进行再次点击一次还原操作
                for (int i = 0; i < inputele.size(); i++) {
                    inputele.get(i).click();
                }

                driver.findElement(saveby).click();//点击保存按钮

                //判断被再次点击还原过后是否被选中
                Boolean[] isseleted3 = new Boolean[inputele.size()];
                for (int i = 0; i < inputele.size(); i++) {
                    isseleted3[i] = inputele.get(i).isSelected();
                }

                //判断二个数组是否相等
                if (isseleted2 == isseleted3) {
                    new GetScreenshot(driver, "还原复选框失败");
                    Log.error("还原复选框失败");
                    flag = false;
                } else {
//                    Log.info("还原复选框成功");
                }

            } catch (Exception e) {
                new GetScreenshot(driver, "修改复选框元素失败");
                Log.error("修改复选框元素失败");
                flag = false;
            }
            return flag;
        }else {
            Log.warning("该用户下的这个复选框没有可选择的内容");
            return true;
        }
    }

    public static String getInitSelectedEle(WebDriver driver,By by){
        String initdep = null;
        List<WebElement> listdep = driver.findElement(by).findElements(By.tagName("option"));
        for (int i = 0; i < listdep.size(); i++) {
            if(listdep.get(i).isSelected()){
                initdep = listdep.get(i).getText();
            }
        }
        return initdep;
    }

    public static String getInitSelectedEle(WebDriver driver,WebElement element){
        String initdep = null;
        List<WebElement> listdep = element.findElements(By.tagName("option"));
        for (int i = 0; i < listdep.size(); i++) {
            if(listdep.get(i).isSelected()){
                initdep = listdep.get(i).getText();
            }
        }
        return initdep;
    }

    public static String getNumSelectedEle(WebDriver driver,By by,int index){
        List<WebElement> listdep = driver.findElement(by).findElements(By.tagName("option"));
        return listdep.get(index).getText();
    }

    public static Boolean eidtAccountImg(WebDriver driver,By byimag,By byclick,String submodule){
        Boolean flag = true;
        //下载好原始logo图片
        String initcomlogo = "F:\\SvnData\\trunk\\initcomlogo.jpg";
        try {
            DriverWait.findElement(driver,driver.findElement(byimag));
            FileIo.loadFile(driver.findElement(byimag), initcomlogo);
//            Log.info("下载原始图片成功");
        } catch (Exception e) {
            new GetScreenshot(driver,"下载原始图片失败");
            e.printStackTrace();
            flag = false;
        }

        String uploadfileone = "F:\\SvnData\\trunk\\uploadfileone.jpg";
        try {
            driver.findElement(byclick).sendKeys(uploadfileone);
            Thread.sleep(2000);//等待图片上传完毕
            DragScrollBar.dragToBottom(driver);
            Thread.sleep(1000);
            if(submodule.equals("企业账号")){
                DriverWait.findElement(driver,driver.findElement(By.id("updateBtn"))).click();//点击保存按钮
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
            new GetScreenshot(driver,"上传修改图片失败");
            Log.error("上传修改图片失败");
            flag =false;
            Assert.fail("上传修改图片失败");
        }

        //重新下载好被上传的logo图片
        String reloadfiletwo = "F:\\SvnData\\trunk\\reloadfiletwo.jpg";
        try {
            driver = driver.switchTo().parentFrame();
            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,submodule)).click();
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            DriverWait.findElement(driver,driver.findElement(byimag));
            FileIo.loadFile(driver.findElement(byimag),reloadfiletwo);
//            Log.info("下载被上传的图片成功");
        } catch (Exception e) {
            new GetScreenshot(driver,"下载被上传的logo图片失败");
            e.printStackTrace();
            flag =false;
        }

        //判断是否上传成功
        try {
            if(CompareImg.isSameOne(uploadfileone,reloadfiletwo)){
//                Log.info("判断上传修改图片成功");
            }else {
                new GetScreenshot(driver,"判断上传图片失败");
                Log.error("判断上传修改图片失败");
                flag =false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //还原原先的图片
        try {
            driver.findElement(byclick).sendKeys(initcomlogo);
            Thread.sleep(2000);//等待图片上传完毕
            DragScrollBar.dragToBottom(driver);
            Thread.sleep(1000);
            if(submodule.equals("企业账号")){
                DriverWait.findElement(driver,driver.findElement(By.id("updateBtn"))).click();//点击保存按钮
            }        } catch (InterruptedException e) {
            e.printStackTrace();
            new GetScreenshot(driver,"上传原始图片失败");
            Log.error("上传原始图片失败");
            flag =false;
        }

        //判断是否还原成功
        String reloadthree= "F:\\SvnData\\trunk\\reloadthree.jpg";
        try {
            driver = driver.switchTo().parentFrame();
            DriverWait.findElement(driver,MenuNameEleLoc.getMenuEle(driver,submodule)).click();
            driver = driver.switchTo().frame(Constant.THIRD_IFRAME);
            Thread.sleep(2000);
            DriverWait.findElement(driver,driver.findElement(byimag));
            FileIo.loadFile(driver.findElement(byimag), reloadthree);
//            Log.info("下载被上传的原始图片成功");
        } catch (Exception e) {
            new GetScreenshot(driver,"下载被上传的原始图片失败");
            e.printStackTrace();
            flag =false;
        }
        //判断是否上传成功
        try {
            if(CompareImg.isSameOne(reloadthree,initcomlogo)){
//                Log.info("还原图片成功");
            }else {
                new GetScreenshot(driver,"还原图片失败");
                Log.error("还原图片失败");
                flag =false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //删除多余的图片
        try {
            FileIo.delFile(reloadthree);
            FileIo.delFile(initcomlogo);
            FileIo.delFile(reloadfiletwo);
        } catch (Exception e) {
            Log.error("删除多余的logo图片失败");
            e.printStackTrace();
        }
        return flag;
    }
}
