package god.util;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;

import java.awt.*;

public class Log {
    private static Logger logger = Logger.getLogger(Log.class.getName());
    //定义测试用例开始执行的打印方法，在日志中打印测试用例开始执行的信息
    public static void startTestCase(String testCaseName){
        logger.info("---------\""+testCaseName+"\"用例开始执行-------");
    }
    public static void endTestCase(String testCaseName){
        logger.info("----------\""+testCaseName+ "\"用例执行结束-------");
    }
    //定义打印info级别的日志的方法
    public static void info(String message){
        logger.info(message);
    }
    //定义打印error级别的日志的方法
    public static void error(String message){
        logger.log(Level.ERROR,message);
    }

    //定义打印warning级别的日志的方法
    public static void warning(String message){
        logger.log(Level.WARN,message);
    }

    //定义打印debug级别日志的方法
    public static void debug(String message){
        logger.log(Level.DEBUG,message);
        logger.info(message);
    }

}
