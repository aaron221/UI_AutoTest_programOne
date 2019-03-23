package god.testscripts.permitregistercase;

import god.util.DragScrollBar;
import god.util.DriverWait;
import god.util.SetBrowserProperty;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class GetCompanyInfo {

    public static List<String> getCompanyInfo(String companyName)throws Exception{
        List<String> list = new ArrayList<String>();
        WebDriver driver = SetBrowserProperty.openFoxBrowser();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.get("https://www.tianyancha.com");
        DriverWait.findElement(driver,By.id("home-main-search")).sendKeys(companyName);
        Thread.sleep(2000);
        DriverWait.findElement(driver,By.xpath("//*[@id=\"web-content\"]/div/div[1]/div[2]/div/div/div[2]/div[2]/div[1]/div")).click();
        Thread.sleep(2000);
        String firstpage = driver.getWindowHandle();
        DragScrollBar.dragIntoEleTop(driver,driver.findElement(By.xpath("//div[@id='search']/parent::div")));
        DriverWait.findElement(driver,By.xpath("//div[@id='search']/parent::div/following-sibling::div[1]/div[1]/div/div[3]/div/a")).click();
        Thread.sleep(2000);

        Set<String> winhandles = driver.getWindowHandles();//获得全部的窗口句柄
        for ( String winhandleAfter:winhandles ) {
            if(firstpage.equals(winhandleAfter)){
                continue;
            }
            driver.switchTo().window(winhandleAfter);
    }


        DragScrollBar.dragToPixel(driver,250);
        String creditCode = DriverWait.findElement(driver,By.xpath("//*[@id=\"_container_baseInfo\"]/table[2]/tbody/tr[2]/td[2]")).getText();
        String temAddr = DriverWait.findElement(driver,By.xpath("//*[@id=\"_container_baseInfo\"]/table[2]/tbody/tr[8]/td[2]")).getText();
        String regAddr = temAddr.split("附近")[0];
        List<String> list1 = chooseCoutry(regAddr);
        String country = list1.get(0);
        String zipCode = list1.get(1);

        list.add(creditCode);
        list.add(regAddr);
        list.add(country);
        list.add(zipCode);
        driver.quit();
        return list;
    }

    public static List<String> chooseCoutry(String regAddr){
        List<String> list = new ArrayList<String>();
        String country = null;
        String zipCode = null;
        if(regAddr.contains("开发区")){
            country = "开发区";
            zipCode = "274200";
        }
        else if (regAddr.contains("高新区")){
            country = "高新区";
            zipCode = "274000";
        }
        else if (regAddr.contains("市辖区")){
            country = "市辖区";
            zipCode = "274000";
        }
        else if (regAddr.contains("牡丹区")){
            country = "牡丹区";
            zipCode = "274000";
        }
        else if (regAddr.contains("曹县")){
            country = "曹县";
            zipCode = "274400";
        }
        else if (regAddr.contains("单县")){
            country = "单县";
            zipCode = "274300";
        }
        else if (regAddr.contains("成武县")){
            country = "成武县";
            zipCode = "274200";
        }
        else if (regAddr.contains("巨野县")){
            country = "巨野县";
            zipCode = "274900";
        }
        else if (regAddr.contains("郓城县")){
            country = "郓城县";
            zipCode = "274700";
        }
        else if (regAddr.contains("鄄城县")){
            country = "鄄城县";
            zipCode = "274600";
        }
        else if (regAddr.contains("定陶区")){
            country = "定陶区";
            zipCode = "274100";
        }
        else if (regAddr.contains("东明县")){
            country = "东明县";
            zipCode = "274500";
        }else {
            return null;
        }

        list.add(country);
        list.add(zipCode);
        return list;
    }

    //public static void main(String[] args) throws Exception {
    //    List<String> list = getCompanyInfo("菏泽润华汽车服务有限公司");
    //    for (int i = 0; i < list.size(); i++) {
    //        System.out.println(list.get(i));
    //    }
    //}
}
