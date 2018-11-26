package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class DriverFactory  {

    Properties properties;

    public WebDriver getDriver() throws java.net.MalformedURLException{
        switch (getDriverFromConfig()){
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            case IE:
                return getIEDriver();
            case EDGE:
                return getEdgeDriver();
            case REMOTECHROME:
                return getRemoteChromeDriver();
            case REMOTEFIREFOX:
                return getRemoteFirefoxDriver();
            case REMOTEIE:
                return getRemoteIEDriver();
            case REMOTEEDGE:
                return getRemoteEdgeDriver();
        }

        return getChromeDriver();
    }

    private WebDriver getChromeDriver(){
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        return new ChromeDriver(options);
    }

    private WebDriver getFirefoxDriver(){
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        return new FirefoxDriver();
    }

    private WebDriver getIEDriver(){
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
        return new InternetExplorerDriver();
    }

    private WebDriver getEdgeDriver(){
        System.setProperty("webdriver.edge.driver", "src\\main\\resources\\MicrosoftWebDriver.exe");
        return new EdgeDriver();
    }

    private WebDriver getRemoteChromeDriver()throws java.net.MalformedURLException{
        System.setProperty("webdriver.chrome.driver", "src\\main\\resources\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("disable-extensions");
        DesiredCapabilities cap = DesiredCapabilities.chrome();
        return new RemoteWebDriver(new URL("http://192.168.56.1:4445/wd/hub"), cap);
    }

    private WebDriver getRemoteFirefoxDriver()throws java.net.MalformedURLException{
        System.setProperty("webdriver.gecko.driver", "src\\main\\resources\\geckodriver.exe");
        DesiredCapabilities cap = DesiredCapabilities.firefox();
        return new RemoteWebDriver(new URL("http://192.168.56.1:4445/wd/hub"), cap);
    }

    private WebDriver getRemoteIEDriver()throws java.net.MalformedURLException{
        DesiredCapabilities caps = DesiredCapabilities.internetExplorer();
        caps.setCapability(InternetExplorerDriver.NATIVE_EVENTS, false);
        caps.setCapability(InternetExplorerDriver.ENABLE_PERSISTENT_HOVERING, false);
        caps.setCapability(InternetExplorerDriver.REQUIRE_WINDOW_FOCUS, false);
        caps.setCapability(InternetExplorerDriver.IE_ENSURE_CLEAN_SESSION, true);
        caps.setCapability(InternetExplorerDriver.IGNORE_ZOOM_SETTING, true);
        caps.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);

        System.setProperty("webdriver.ie.driver", "src\\main\\resources\\IEDriverServer.exe");
        return new RemoteWebDriver(new URL("http://192.168.56.1:7779/wd/hub"), caps);
    }

    private WebDriver getRemoteEdgeDriver()throws java.net.MalformedURLException{
        System.setProperty("webdriver.edge.driver", "src\\main\\resources\\MicrosoftWebDriver.exe");
        DesiredCapabilities cap = DesiredCapabilities.edge();
        return new RemoteWebDriver(new URL("http://192.168.56.1:7780/wd/hub"), cap);
    }


    public DriverType getDriverFromConfig(){
        properties = new Properties();
        try{
            InputStream inputStream = new FileInputStream("src\\main\\resources\\config.properties");
            properties.load(inputStream);
            return DriverType.valueOf(properties.getProperty("browser"));

        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
        return DriverType.CHROME;
    }
}