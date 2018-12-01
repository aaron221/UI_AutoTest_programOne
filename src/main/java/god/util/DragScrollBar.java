package god.util;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Created by thomas on 2018/10/11.
 * 封装拖动滚动条
 * */
public class DragScrollBar {
    public static void dragToBottom(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,document.body.scrollHeight)");//下拉到最底端
    }

    public static void dragToTop(WebDriver driver){
        ((JavascriptExecutor)driver).executeScript("window.scrollTo(0,0)");//上拉到最顶端
    }

    public static void dragToEleBot(WebDriver driver,WebElement element){
        //移动到元素element对象的“底端”与当前窗口的“底部”对齐
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(false);", element);
    }

    public static void dragIntoEleTop(WebDriver driver,WebElement element){
        //移动到元素element对象的“顶端”与当前窗口的“顶部”对齐
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public static void dragByPixel(WebDriver driver,int pixelNum){
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, "+pixelNum+")");//相对于当前坐标
    }

    public static void dragToPixel(WebDriver driver,int pixelNum){
        ((JavascriptExecutor)driver).executeScript("window.scrollBy(0, "+pixelNum+")");//相对于绝对坐标
    }

}
