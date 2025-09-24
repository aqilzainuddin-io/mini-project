package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginTest {
    
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeMethod
    public void setUp(){
       driver = new ChromeDriver();
       driver.manage().window().maximize();
       driver.get("https://the-internet.herokuapp.com/login");
       loginPage = new LoginPage(driver);
    }


    @Test
    public void validLoginTest(){
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"),"Login was not successful!");
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
