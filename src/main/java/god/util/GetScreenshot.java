package god.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * Created by thomas on 2018/9/27.
 */
public class GetScreenshot {
    public GetScreenshot(WebDriver driver,String photoName){
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(Constant.SCR_SHOT_PATH+photoName+SystemTime.getCurTime()+".png"));
        } catch (Exception e) {
           Log.error("截图失败");
        }
    }
}
