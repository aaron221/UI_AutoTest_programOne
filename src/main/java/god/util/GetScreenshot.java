package god.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import java.io.File;

/**
 * Created by thomas on 2018/9/27.
 */
public class GetScreenshot {
    public GetScreenshot(String photoName,WebDriver driver){
        File srcFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(Constant.SCR_SHOT_PATH+photoName+".png"));
        } catch (Exception e) {
        }
    }
}
