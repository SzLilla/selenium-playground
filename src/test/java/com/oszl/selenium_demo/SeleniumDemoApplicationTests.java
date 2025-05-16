package com.oszl.selenium_demo;

import static org.testng.Assert.assertEquals;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SeleniumDemoApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	public void myDemo(){
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));

		driver.get("https://www.selenium.dev/selenium/web/web-form.html");

		WebElement textBox = driver.findElement(By.name("my-text"));		
        WebElement submitButton = driver.findElement(By.cssSelector("button"));

		textBox.sendKeys("This is a test sentence.");
		submitButton.click();

		WebElement message = driver.findElement(By.id("message"));
        String messageString = message.getText();
		assertEquals(messageString, "Received!");

		driver.quit();
	}

}
