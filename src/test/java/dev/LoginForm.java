package dev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginForm {
    WebDriver driver;

    @BeforeTest
    private void init() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\Drivers\\chrome-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.navigate().to("https://dev.to/enter");
        this.driver.manage().window().maximize();
    }

    @Test(
            dataProvider = "data",
            description = "Check Login"
    )
    public void checkLogin(String scenario, String email, String password) throws InterruptedException {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\Drivers\\chrome-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(this.driver, Duration.ofSeconds(2L));
        this.driver.get("https://dev.to/enter");
        this.driver.manage().window().maximize();
        this.driver.findElement(By.id("user_email")).sendKeys(new CharSequence[]{email});
        this.driver.findElement(By.id("user_password")).sendKeys(new CharSequence[]{password});
        this.driver.findElement(By.name("commit")).click();
        String currentUrl = this.driver.getCurrentUrl();
        System.out.println("Current URL after valid login: " + currentUrl);
        if (scenario.equals("valid")) {
            WebElement profileButton = this.driver.findElement(By.id("member-menu-button"));
            Assert.assertTrue(profileButton.isDisplayed(), "Member menu button is not displayed.");
            profileButton.click();
            WebElement firstNavLink = (WebElement)wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("first-nav-link")));
            Assert.assertTrue(firstNavLink.isDisplayed(), "Login Not succes");
        } else if (scenario.equals("invalid")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        } else if (scenario.equals("validEmail")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        } else if (scenario.equals("validPassword")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        } else if (scenario.equals("mandatory")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        } else if (scenario.equals("mandatoryEmail")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        } else if (scenario.equals("mandatoryPassword")) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("crayons-notice--danger")));
        }

    }

    @DataProvider(
            name = "data"
    )
    public Object[][] getData() {
        return new Object[][]{{"valid", "fyxalxa2@gmail.com", "admin123"}, {"invalid", "alifah@gmail.com", "123admin"}, {"validEmail", "fyxalxa2@gmail.com", "123admin"}, {"validPassword", "alifah@gmail.com", "admin123"}, {"mandatory", "", ""}, {"mandatoryEmail", "", "admin123"}, {"mandatoryPassword", "fyxalxa2@gmail.com", ""}};
    }

    @Test(
            priority = 2,
            description = "Close Login"
    )
    private void closeLogin() {
        this.driver.quit();
    }

    @Test(
            priority = 3,
            description = "Close login ketika sudah isi data"
    )
    private void closeLoginInput() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\Drivers\\chrome-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.get("https://dev.to/enter");
        this.driver.manage().window().maximize();
        WebElement email = this.driver.findElement(By.id("user_email"));
        email.sendKeys(new CharSequence[]{"fyxalxa2@gmail.com"});
        WebElement password = this.driver.findElement(By.id("user_password"));
        password.sendKeys(new CharSequence[]{"admin123"});
        this.driver.quit();
    }

    @AfterMethod
    public void tearDown() {
        if (this.driver != null) {
            this.driver.quit();
        }

    }
}
