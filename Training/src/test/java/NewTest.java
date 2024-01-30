import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class NewTest {
	
	private ExtentReports extent;
    private ExtentTest test;

    private WebDriver driver;
    
    @BeforeSuite
    public void extentReport() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("test-output/ExtentReport.html");
        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
    }

    @BeforeClass
    public void setUp() throws InterruptedException {
        // Set up the WebDriver (you need to download chromedriver.exe and set its path)
        System.setProperty("webdriver.chrome.driver", ".\\src\\test\\resources\\updateDrivers\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://tr-services.most.gov.bd/en/auth/login");
        
        Thread.sleep(1000);
       
        
    }

    @Test
    public void loginTest() throws InterruptedException {
    	
        test = extent.createTest("Login Test");

        
        // Locate the username and password fields and the login button
        WebElement usernameInput = driver.findElement(By.id("loginForm_username"));
    	WebElement passwordInput = driver.findElement(By.id("loginForm_password"));       

        WebElement loginButton = driver.findElement(By.xpath("//span[text()='Log in']"));
   

        // Enter your username and password
        usernameInput.sendKeys("admin@most.gov.bd");
        passwordInput.sendKeys("Abc123!");

        // Click on the login button
        loginButton.click();

        
        Thread.sleep(1000);
        
        // Assuming there is an element on the page that indicates successful login
        WebElement successMessage = driver.findElement(By.xpath("//*/div/h2"));

        // Assert that the success message is displayed
        Assert.assertTrue(successMessage.isDisplayed(), "Integrated Digital Service Delivery Platform");
    }

    @AfterClass
    public void tearDown() {
      
    	// Write and flush the report
        extent.flush();
    	
        // Close the browser
        if (driver != null) {
            driver.quit();
            
        }
    }
}
