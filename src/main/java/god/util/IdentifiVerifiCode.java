package god.util;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class IdentifiVerifiCode {

    public static String getVerifiCode(String imagepath)throws Exception{
        File imageFile = new File(imagepath);
        Tesseract instance = new Tesseract();

        //将验证码图片的内容识别为字符串
        String result = instance.doOCR(imageFile);
        return  result;
    }

    public static String getCode(WebDriver driver,Integer[] list,String srcimage,String subimage)throws Exception{
        String imagepath = Constant.SCR_SHOT_PATH+srcimage+".png";
        String codepath = Constant.SCR_SHOT_PATH+subimage+".png";
        new GetScreenshot(srcimage,driver);
        OperateImage operateImage = new OperateImage(list[0], list[1], list[2], list[3]);
        operateImage.srcpath = imagepath;
        operateImage.subpath = codepath;
        try {
            operateImage.cut();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String imagecode = getVerifiCode(codepath);
        while(imagecode.length()!=4){
              imagecode = getVerifiCode(codepath);
        }
            
        return imagecode;
        
        
    }
}
