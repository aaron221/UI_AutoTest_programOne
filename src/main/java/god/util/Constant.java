package god.util;

import com.sun.org.apache.xml.internal.security.Init;
import org.omg.CORBA.PUBLIC_MEMBER;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.net.FileNameMap;

public class Constant {
    public static final String URL = "http://uccuat.im-cc.com";//定义测试网址的常量
    public static final String TESTCASE_DATA = "TestData.xlsx";//定义读写的Excel文件路径
    public static final String SHEET_NAME = "logincase";
    public static final String ELE_CONF_FILE = "objectMap.properties";//元素定位配置文件的路径
    public static final String LOG4J_COF_PATH = "target\\classes\\log4j.xml";
    public static final String SCR_SHOT_PATH = "src\\test\\screenshot\\";
    public static final String USERNAME = "1086@11196";
    public static final String PASSWD = "Aa_111111";
    public static final String STATUS = "小休";
    public static final String REM_PWD = "是";

    public static final String PHONE_NUM = "";//注册用到的手机号
    public static final String PHONE_CODE = "";//此处需要开发那边开放一个万能验证码
    public static final String PHOTO_CODE = "";//此处需要开发那边开放一个万能验证码
    public static final String EMAIL_URL = "https://exmail.qq.com/cgi-bin/loginpage";
    public static final String EMAIL_USER_NAME = "name@icloudsoft.com.cn";
    public static final String EMAIL_PASSWD = "passwd";
    public static final String CONTACT_PERSON = "aaron";

    //系统中html页面中存在的iframe框架id
    public static final String SENCOND_IFRAME = "mainFrame";//第二列目录的框架id,第一层框架
    public static final String THIRD_IFRAME = "settingIframe";//第三列内容展示的框架id，包含在第一层框架中
    public static final String CONTACT_PLANE_IFRAME = "contactFrame";//联系计划列表页面展示所在的框架id，被嵌套在了第三列目录的框架中
    public static final String VIP_CUSTOMER_IFRAME = "infoFrame";//专享客户列表内容框架，被嵌套在第三列中

}
