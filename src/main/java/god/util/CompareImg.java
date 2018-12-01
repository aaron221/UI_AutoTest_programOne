package god.util;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by thomas on 2018/10/16.
 */
public class CompareImg {
    //此方法是比较二张图片是否一样
    public static Boolean isSameOne(String filepathone,String filepathtwo) throws Exception{
        Boolean flag = false;
        File fileone = new File(filepathone);
        File filetwo = new File(filepathtwo);
        if(fileone.length()!=fileone.length()){
            flag =false;
        }else {
            FileInputStream fileinputone = new FileInputStream(fileone);
            FileInputStream fileinputtwo = new FileInputStream(filetwo);
            int len1 = 0;
            int len2 = 0;
            byte[] bytes = new byte[6];
            while((len1=fileinputone.read(bytes))>0&(len2=fileinputtwo.read(bytes))>0){
                if(fileinputone.read(bytes,0,len1)!=fileinputtwo.read(bytes,0,len2)){
                    flag =false;
                    break;
                }else {
                    flag =true;
                }
            }
        }
        return flag;
    }
}
