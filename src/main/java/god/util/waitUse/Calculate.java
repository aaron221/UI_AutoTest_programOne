package god.util.waitUse;

/**
 * Created by thomas on 2018/9/25.
 */
public class Calculate {
    public static void main(String[] args) throws Exception{
        long startTime = System.currentTimeMillis(); //获取开始时间
        Thread.sleep(1000);
        long endTime = System.currentTimeMillis(); //获取结束时间
        System.out.println("程序运行时间：" + (endTime - startTime) + "ms"); //输出程序运行时间
    }
}
