package god.util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by thomas on 2018/10/15.
 */
public class GetEleList {

    public static List<WebElement> getEleList(WebElement parentEle, String sonTagName){
        List<WebElement> listEle = parentEle.findElements(By.tagName(sonTagName));
        return listEle;
    }

}
