package com.oszl.selenium_demo.herokuapp;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class InputTest {

    private WebDriver driver;
    private WebElement inputField;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
        driver.get("https://the-internet.herokuapp.com/inputs");

        inputField = driver.findElement(By.cssSelector("#content input[type='number']"));
    }

    @Test
    public void testTitle() {
        String title = driver.getTitle();
        assertEquals("The Internet", title);
    }

    @Test
    public void testNumerAccepted() throws InterruptedException {
        testNumber(1);
        testNumber(0);
        testNumber(-1);
        testNumber(999999999);
        testNumber(1.1);
        testNumber(.1);
    }

    private void testNumber(double number) {
        String inputString = String.valueOf(number);
        testNumber(inputString);
    }

    private void testNumber(String number) {
        inputField.clear();
        inputField.sendKeys(number);
        assertEquals(number, inputField.getAttribute("value"));
    }

    @Test
    public void testCharacterNotAccepted() {
        testCharacter("a");
        testCharacter("@");
        testCharacter("?");
    }

    private void testCharacter(String text) {
        inputField.clear();
        inputField.sendKeys(text);
        assertEquals("", inputField.getAttribute("value"));
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

}