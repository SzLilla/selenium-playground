package com.oszl.selenium_demo.herokuapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LoginTest {

    private WebDriver driver;
    private WebElement usernameField;
    private WebElement passwordField;
    private WebElement loginButton;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("https://the-internet.herokuapp.com/login");

        usernameField = driver.findElement(By.id("username"));
        passwordField = driver.findElement(By.id("password"));
        loginButton = driver.findElement(By.xpath("//form[@id='login']/button/i"));

    }

    @Test
    public void testTitle() {
        String title = driver.getTitle();
        assertEquals("The Internet", title);
    }

    @Test
    public void testLoginPass() throws InterruptedException {
        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("SuperSecretPassword!");
        loginButton.click();

        WebElement flashMessage = driver.findElement(By.id("flash-messages"));
        String text = flashMessage.getText();
        assertTrue(text.contains("You logged into a secure area!"));
    }

    @Test
    public void testLoginFailByUsername() throws InterruptedException {
        usernameField.sendKeys("abc");
        passwordField.sendKeys("xyz");
        loginButton.click();

        WebElement flashMessage = driver.findElement(By.id("flash-messages"));
        String text = flashMessage.getText();
        assertTrue(text.contains("Your username is invalid!"));
    }

    @Test
    public void testLoginFailByPassword() throws InterruptedException {
        usernameField.sendKeys("tomsmith");
        passwordField.sendKeys("xyz");
        loginButton.click();

        WebElement flashMessage = driver.findElement(By.id("flash-messages"));
        String text = flashMessage.getText();
        assertTrue(text.contains("Your password is invalid!"));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

}