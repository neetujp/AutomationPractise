package com.seleniumgrid.AutomationPractise;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CheckOut {

	WebDriver driver;

	@BeforeMethod
	public void startBrowser() {

		System.setProperty("webdriver.chrome.driver", "C:\\Users\\neetu\\Desktop\\New folder\\chromedriver.exe");

		driver = new ChromeDriver();

		driver.get("https://www.saucedemo.com/");

		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void loginTest() {

		driver.findElement(By.xpath("//input[@placeholder='Username']")).sendKeys("standard_user");
		driver.findElement(By.cssSelector("div.login-box form>div:nth-of-type(2) input")).sendKeys("secret_sauce");
		driver.findElement(By.xpath("//input[@type='submit']")).submit();
	}

	@Test(priority = 2)
	public void checkOutTest() {
		loginTest();

		driver.findElement(
				By.cssSelector("div.inventory_list>div:nth-of-type(3)>div:nth-of-type(2)>div:nth-of-type(2) button"))
				.click();
		driver.findElement(By.cssSelector("a.shopping_cart_link span")).click();
		driver.findElement(By.cssSelector("div.cart_footer button:nth-of-type(2)")).click();
		driver.findElement(By.cssSelector("div.checkout_info div:nth-of-type(1) input")).sendKeys("Neetu");
		driver.findElement(By.cssSelector("div.checkout_info div:nth-of-type(2) input")).sendKeys("Jayapalan");
		driver.findElement(By.cssSelector("div.checkout_info div:nth-of-type(3) input")).sendKeys("L6X1X7");
		driver.findElement(By.cssSelector("div.checkout_buttons input")).click();
		driver.findElement(By.cssSelector("div.cart_footer button:nth-of-type(2)")).click();
		String checkOutConfirmation = driver.findElement(By.cssSelector("h2.complete-header")).getText();
		Assert.assertEquals(checkOutConfirmation, "THANK YOU FOR YOUR ORDER", "Text is not matching");

	}

	@AfterMethod
	public void closeBrowser() {
		driver.quit();
	}

}
