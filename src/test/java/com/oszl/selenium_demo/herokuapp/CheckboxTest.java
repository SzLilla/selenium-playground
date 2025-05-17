package com.oszl.selenium_demo.herokuapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CheckboxTest {

	private WebDriver driver;
	private WebElement checkbox1;
	private WebElement checkbox2;

	@BeforeEach
	public void setup() {
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(1));
		driver.get("https://the-internet.herokuapp.com/checkboxes");

		List<WebElement> checkboxes = driver.findElements(By.cssSelector("#checkboxes input[type='checkbox']"));
		checkbox1 = checkboxes.get(0);
		checkbox2 = checkboxes.get(1);
	}

	@Test
	public void testTitle() {
		String title = driver.getTitle();
		assertEquals("The Internet", title);
	}

	@Test
	public void testDefaultStatus() {
		assertFalse(checkbox1.isSelected());
		assertTrue(checkbox2.isSelected());
	}

	@Test
	public void testStatusChange() {
		checkbox1.click();
		checkbox2.click();
		assertTrue(checkbox1.isSelected());
		assertFalse(checkbox2.isSelected());
	}

	@AfterEach
	public void teardown() {
		driver.quit();
	}

}