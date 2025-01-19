package dev;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginWithAccount {
    WebDriver driver;

    @BeforeTest
    private void init() {
        System.setProperty("webdriver.opera.driver", "C:\\Program Files\\Drivers\\chrome-win32\\chromedriver.exe");
        this.driver = new ChromeDriver();
        this.driver.navigate().to("https://dev.to/enter");
        this.driver.manage().window().maximize();
    }

    @Test(description = "Test login dengan berbagai akun")
    private void loginWithAccounts() throws InterruptedException {
        Object[][] accounts = new Object[][]{
                {By.cssSelector(".brand-apple"), "Login dengan akun Apple"},
                {By.xpath("//form[2]"), "Login dengan akun Facebook"},
                {By.xpath("//form[3]"), "Login dengan akun Forem"},
                {By.xpath("//form[4]"), "Login dengan akun GitHub"},
                {By.xpath("//form[5]"), "Login dengan akun Google"},
                {By.xpath("//form[6]"), "Login dengan akun Twitter"},
                {By.xpath("(//a[normalize-space()='Create account'])[2]"), "Melihat halaman Register"},
                {By.xpath("//a[normalize-space()='Forgot password?']"), "Melihat halaman Forgot Password"}};

        for (Object[] account : accounts) {
            By locator = (By) account[0];
            String description = (String) account[1];

            try {
                WebElement element = driver.findElement(locator);
                System.out.println(description);
                element.click();
                Thread.sleep(2000);
                driver.navigate().back();
            } catch (Exception e) {
                System.out.println("Gagal menjalankan: " + description);
                e.printStackTrace();
            }
        }

        driver.quit();
    }

}
