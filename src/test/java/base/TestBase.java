package base;

import Pages.*;
import Utilities.Helper;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import org.apache.commons.io.FileUtils;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;

public class TestBase extends AbstractTestNGCucumberTests {
    public static WebDriver driver;

    @BeforeSuite
    @Parameters({"browser"})
    public void startDriver(@Optional("chrome") String browserName) {
        //public void startDriver( String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            // System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "\\drivers\\chromedriver.exe");
            WebDriverManager.chromedriver().setup();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(caps);

        } else if (browserName.equalsIgnoreCase("firefox")) {
            // System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\drivers\\geckodriver.exe");
            WebDriverManager.firefoxdriver().setup();
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setAcceptInsecureCerts(true);
            driver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("opera")) {
            System.setProperty("webdriver.opera.driver", System.getProperty("user.dir") + "\\drivers\\operadriver.exe");
            driver = new OperaDriver();
            WebDriver webDriver = new FirefoxDriver();

        } else if (browserName.equalsIgnoreCase("chrome-headless")) {
            WebDriverManager.chromedriver().setup();
            ChromeOptions option = new ChromeOptions();
            option.addArguments("--headless");
            DesiredCapabilities caps = new DesiredCapabilities();
            caps.setAcceptInsecureCerts(true);
            driver = new ChromeDriver(caps);
            driver = new ChromeDriver(option);
        }
        driver.manage().deleteAllCookies();
        driver.get("https://vbook.isoft.world:4434/ar");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(120, TimeUnit.SECONDS);

    }

    @AfterSuite
    public void closeDriver() {
        driver.quit();
    }

    @AfterMethod
    public void screenShot(ITestResult result) throws IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("Failed");
            System.out.println("Taking Screenshot....");
            Helper.captureScreenShot(driver, result.getName());
        }
        String testcase = result.getMethod().getMethodName();
        File destfile= new File("Screenshots"+File.separator+testcase+".png");
        takeScreenShot(destfile);
    }
    public void takeScreenShot(File destfile)
    {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(file, destfile);
            InputStream is = new FileInputStream(destfile);
            Allure.addAttachment("screenshot",is);
        } catch (IOException e) {
            throw  new RuntimeException(e);
        }
    }
}