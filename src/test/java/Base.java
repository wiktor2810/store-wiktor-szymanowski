import factory.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.logging.LogType;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Base {

    WebDriver driver;
    DriverFactory driverFactory = new DriverFactory();

    @BeforeEach
    public void setUp(){
        try {
            driver = driverFactory.getDriver();
        } catch(java.net.MalformedURLException e){
            e.printStackTrace();
        }
        driver.get("http://store.demoqa.com/");
        driver.manage().window().maximize();
    }


    @AfterEach
    public void cleanUp(){
        System.out.println("Last url " + driver.getCurrentUrl());
        createScreenshot();
        getBrowserLogs();
        driver.quit();
    }

    private void getBrowserLogs(){
        System.out.println("Browser logs: ");
        LogEntries logEntries = driver.manage().logs().get(LogType.BROWSER);
        for (LogEntry entry : logEntries) {
            System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
        }
    }

    private void createScreenshot(){
        File fileScreen = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        try{
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("HH.mm.ss");
            String fileName = sdf.format(cal.getTime()) + ".png";
            String fileDirectory = "C://work//";
            FileUtils.copyFile(fileScreen, new File(fileDirectory + fileName));
        } catch(java.io.IOException e) {
            e.printStackTrace();
        }
    }

}