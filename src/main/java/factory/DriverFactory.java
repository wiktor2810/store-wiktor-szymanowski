package factory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class DriverFactory {

    Properties properties;

    public WebDriver getDriver(){
        switch (getDriverFromConfig()){
            case CHROME:
                return getChromeDriver();
            case FIREFOX:
                return getFirefoxDriver();
            case IE:
                return getIEDriver();
            case EDGE:
                return getEdgeDriver();
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