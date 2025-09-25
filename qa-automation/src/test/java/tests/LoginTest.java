package tests;

import pages.LoginPage;
import utils.DriverFactory;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

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
        loginPage.login("tomsmith","SuperSecretPassword!");

        String message = loginPage.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"), 
            "Login was not successful! Actual message: " + message);
    }

    @Test
    public void invalidLoginTest(){
        loginPage.login("wronguser","wrongpass");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!") 
                    || message.contains("Your password is invalid!"), 
            "Error message not displayed correctly! Actual message: " + message);
    }

    @Test
    public void emptyUsernameAndPasswordTest(){
        loginPage.login("","");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!"),
                "Error message for empty fields not displayed!");
    }

    @Test
    public void onlyUsernameProvidedTest(){
        loginPage.login("tomsmith","");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your password is invalid!") 
                       || message.contains("Your username is invalid!"),
                "Error message for missing password not displayed!");
    }

    @Test
    public void onlyPasswordProvidedTest(){
        loginPage.login("","SuperSecretPassword!");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!"),
                "Error message for missing username not displayed!");
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
