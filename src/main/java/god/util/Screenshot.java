package god.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;

import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;


public class Screenshot {

    public static void captureScreen(String imagecodepath, List<Integer> list) throws Exception {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Rectangle screenRectangle = new Rectangle(screenSize);
        Robot robot = new Robot();
        BufferedImage image = robot.createScreenCapture(screenRectangle);
        // 截图保存的路径
        File screenFile = new File(imagecodepath);
        // 指定屏幕区域，参数为截图左上角坐标(100,100)+右下角坐标(500,500)
        BufferedImage subimage = image.getSubimage(list.get(0), list.get(1), list.get(2), list.get(3));
        ImageIO.write(subimage, "png", screenFile);

    }

    public static void screenShotForElement(WebDriver driver,
                                            WebElement element, String path) throws InterruptedException {
        File scrFile = ((TakesScreenshot) driver)
                .getScreenshotAs(OutputType.FILE);
        try {
            Point p = element.getLocation();
            int width = element.getSize().getWidth();
            int height = element.getSize().getHeight();
            Rectangle rect = new Rectangle(width, height);
            BufferedImage img = ImageIO.read(scrFile);
            System.out.println(p.getX() + ":" + p.getY() + ":" +
                    rect.width + ":" + rect.height);
            BufferedImage dest = img.getSubimage(p.getX(), p.getY(),
                    rect.width, rect.height);
            ImageIO.write(dest, "png", scrFile);
            Thread.sleep(1000);
            FileUtils.copyFile(scrFile, new File(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

