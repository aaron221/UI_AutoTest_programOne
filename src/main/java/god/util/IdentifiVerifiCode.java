package god.util;
import net.sourceforge.tess4j.Tesseract;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.io.File;
import java.io.IOException;

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
        int times=0;
        while(imagecode.length()!=4&&times<4){
              imagecode = getVerifiCode(codepath);
              times++;
        }

        if(times!=0){
            Log.error("验证码识别有误，请确确认src/test/screenshot的code图片不为空，假如为空，请联系代码提供者进行处理");
            Assert.fail();
        }
            
        return imagecode;
    }
}
