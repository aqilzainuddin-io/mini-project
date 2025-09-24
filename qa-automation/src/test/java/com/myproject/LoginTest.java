package com.myproject;


import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {
    
    private WebDriver driver;

    @BeforeMethod
    public void setUp(){
        // WebDriverManager will download & setup the right ChromeDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        };


    @Test
    public void testOpenLoginPage(){
        driver.get("https://the-internet.herokuapp.com/login"); // sample login site
        String pageTitle =  driver.getTitle();
        Assert.assertTrue(pageTitle.contains("The Internet"), "Page title should contain 'The Internet'");
    }

    @AfterMethod
    public void tearDown(){
        if(driver != null){
            driver.quit();
        }
    }
}
