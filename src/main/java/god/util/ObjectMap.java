package god.util;

import org.openqa.selenium.By;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ObjectMap {
    Properties properties;

    public ObjectMap(String propFile){
        properties = new Properties();
        try {
            FileInputStream in = new FileInputStream(propFile);
            properties.load(in);
        } catch (IOException e) {
            System.out.println("读取对象文件出错");
            e.printStackTrace();
        }
    }
    public By getLocator(String ElementNameInpropFile){
        //根据变量ElementNameInpropFile，从属性配置文件中读取对应的配置对象
        String locator = properties.getProperty(ElementNameInpropFile);
        //将配置对象中的定位类型存到locatorType变量，将定位表达式的值存入locatorValue变量
        String locatorType = locator.split(">")[0];
        String locatorValue = locator.split(">")[1];

        //假如配置文件的默认编码为ISO-8859-1,可以使用getBytes方法将字符串编码转换为UTF-8编码，以此来解决配置文件读取中文为乱码的问题
        try {
            locatorValue = new String(locatorValue.getBytes("ISO-8859-1"),"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //输出locatorType变量值和locatorValue变量值，验证是否赋值正确
//        System.out.println("获取的定位类型:"+locatorType+"\t获取的定位表达式"+locatorValue);

        //根据locatorType的变量值内容判断返回何种定位方式的By对象
        if(locatorType.toLowerCase().equals("id"))
            return By.id(locatorValue);
        else if(locatorType.toLowerCase().equals("name"))
            return By.name(locatorValue);
        else if(locatorType.toLowerCase().equals("xpath"))
            return By.xpath(locatorValue);
        else if(locatorType.toLowerCase().equals("classname")||locatorType.toLowerCase().equals("class"))
            return By.className(locatorValue);
        else if(locatorType.toLowerCase().equals("tagname")||locatorType.toLowerCase().equals("tag"))
            return By.tagName(locatorValue);
        else if(locatorType.toLowerCase().equals("linktext")||locatorType.toLowerCase().equals("link"))
            return By.linkText(locatorValue);
        else if(locatorType.toLowerCase().equals("partiallinktext"))
            return By.partialLinkText(locatorValue);
        else if(locatorType.toLowerCase().equals("cssselector")||locatorType.toLowerCase().equals("css"))
            return By.cssSelector(locatorValue);
        return null;
    }
}
