package tests;
import pages.LoginPage;
import utils.DriverFactory;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

import org.openqa.selenium.WebDriver;

public class LoginTest {
    
    private WebDriver driver;
    private LoginPage loginPage;

    @BeforeClass
    public void setUp(){       
        driver = DriverFactory.getDriver();
        driver.get("https://the-internet.herokuapp.com/login");
        loginPage = new LoginPage(driver);
    }

    @Test
    public void validLoginTest(){
        loginPage.enterUsername("tomsmith");
        loginPage.enterPassword("SuperSecretPassword!");
        loginPage.clickLogin();

        String message = loginPage.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"), 
            "Login was not successful! Actual message: " + message);
    }

    @Test
    public void invalidLoginTest(){
        loginPage.enterUsername("wronguser");
        loginPage.enterPassword("wrongpass");
        loginPage.clickLogin();

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!") 
                    || message.contains("Your password is invalid!"), 
            "Error message not displayed correctly! Actual message: " + message);
    }


    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
