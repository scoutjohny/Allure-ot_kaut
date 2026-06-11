import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class Allure {
    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }

    @Epic("Sauce Demo website")
    @Feature("Login na glavnoj stranici")
    @Test(description = "Testiranje login-a na login stranici")
    @Description("Testiramo pozitivan scenario login-a na login stranici")
    @Step("Login na stranici")
    @Link("htttps://www.google.rs")
    @Issue("RP-2375")
    @TmsLink("DEMO-1")
    @Severity(SeverityLevel.CRITICAL)
    public void loginToSauceDemo() {
        driver.navigate().to("https://www.saucedemo.com/");

        driver.findElement(By.id("user-name")).sendKeys("standard_user");
        driver.findElement(By.cssSelector("#password")).sendKeys("secret_sauce");
        driver.findElement(By.id("login-button")).click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
        Assert.assertEquals(driver.findElement(By.cssSelector(".title")).getText(), "Products");
    }

}
