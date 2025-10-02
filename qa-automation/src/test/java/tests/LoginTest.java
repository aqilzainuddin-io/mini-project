package tests;

import pages.LoginPage;
import utils.DriverFactory;
import utils.EnvReader;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class LoginTest {
    
    private WebDriver driver;
    private LoginPage loginPage;

    private String validUsername;
    private String validPassword;
    private String invalidUsername;
    private String invalidPassword;
    private String baseUrl;

    @BeforeClass
    public void setUp(){       
        driver = DriverFactory.getDriver();
        baseUrl = EnvReader.get("BASE_URL");
        driver.get(baseUrl+"/login");
        loginPage = new LoginPage(driver);

        // Read from .env
        validUsername = EnvReader.get("VALID_USERNAME");
        validPassword = EnvReader.get("VALID_PASSWORD");
        invalidUsername = EnvReader.get("INVALID_USERNAME");
        invalidPassword = EnvReader.get("INVALID_PASSWORD");

    }

    @Test
    public void validLoginTest(){
        loginPage.login(validUsername, validPassword);

        String message = loginPage.getSuccessMessage();
        Assert.assertTrue(message.contains("You logged into a secure area!"), 
            "Login was not successful! Actual message: " + message);
    }

    @Test
    public void invalidLoginTest(){
        loginPage.login(invalidUsername, invalidPassword);

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!") 
                    || message.contains("Your password is invalid!"), 
            "Error message not displayed correctly! Actual message: " + message);
    }

    @Test
    public void emptyUsernameAndPasswordTest(){
        loginPage.login("", "");

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!"),
                "Error message for empty fields not displayed!");
    }

    @Test
    public void onlyUsernameProvidedTest(){
        loginPage.login(validUsername,""); 

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your password is invalid!") 
                       || message.contains("Your username is invalid!"),
                "Error message for missing password not displayed!");
    }

    @Test
    public void onlyPasswordProvidedTest(){
        loginPage.login("", validPassword); 

        String message = loginPage.getErrorMessage();
        Assert.assertTrue(message.contains("Your username is invalid!"),
                "Error message for missing username not displayed!");
    }

    @AfterClass
    public void tearDown(){
        DriverFactory.quitDriver();
    }
}
