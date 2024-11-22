package Utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;



public class Driver {

    private static WebDriver driver;

    private Driver() {
    }

    /**
     * Static method to get the single of WebDriver
     */

    public static WebDriver getDriver() {
        //Read the browser type you want to launch from properties file
        String browserType = ConfigurationReader.getPropertyValue("browserType");

        if (driver == null) {//if the driver is null, then create the driver
            //Create the driver based on the browser type
            switch (browserType.toLowerCase()) {

                case "chrome": //if the browser type is chrome
                    driver = new ChromeDriver();
                    break;
                case "firefox": //if the browser type is chrome
                    driver = new FirefoxDriver();
                    break;
                case "edge": //if the browser type is chrome
                    driver = new EdgeDriver();
                    break;
                case "safari": //if the browser type is chrome
                    driver = new SafariDriver();
                    break;
                case "chrome-headless":
                    System.out.println("Running in headless mode in chrome");
                    ChromeOptions chromeOptions = new ChromeOptions();
                    chromeOptions.addArguments("--headless");
                    driver = new ChromeDriver(chromeOptions);// will create new instance of chromedriver in headless mode
                    break;
                case "firefox-headless":
                    System.out.println("Running in headless mode in firefox");
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.addArguments("--headless");
                    driver = new FirefoxDriver(firefoxOptions);
                    break;
                default:
                    driver = new ChromeDriver(); //Default to ChromeDriver
                    break;


            }
        }
        return driver; // Return the existing driver instance
    }

    /**
     * This method will quit the driver and set it to null
     */
    public static void closeDriver() {
        System.out.println("Closing the driver");
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}

