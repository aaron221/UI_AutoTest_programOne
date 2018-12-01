package god.util.waitUse;

import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GetDriver {
    public static WebDriver driver;

    //获取远程节点机器中的谷歌浏览器对象Driver，通过函数参数remoteNodeUrl连接不同的指定节点
    public static WebDriver getRemoteChromedriver(String remoteNodeUrl) throws MalformedURLException {
        DesiredCapabilities capability = DesiredCapabilities.chrome();
        capability.setBrowserName("chrome");
        capability.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL(remoteNodeUrl),capability);
        return driver;
    }

    //获取远程节点机器中IE浏览器对象Driver，通过函数参数remoteNodeUrl连接不同的指定节点
    public static WebDriver getRemoteIEdriver(String remoteNodeUrl) throws MalformedURLException {
        DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
        capabilities.setBrowserName("internetExplorer");
        capabilities.setBrowserName("firefox");//火狐浏览器9o
        capabilities.setPlatform(Platform.WINDOWS);
        driver = new RemoteWebDriver(new URL(remoteNodeUrl),capabilities);
        return driver;
    }


}
