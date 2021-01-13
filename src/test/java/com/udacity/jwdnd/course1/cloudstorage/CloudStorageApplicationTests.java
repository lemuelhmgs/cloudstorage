package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private LoginPage loginPage;

	private static  WebDriver  driver;

	@BeforeAll
	static void beforeAll() {

		WebDriverManager.chromedriver().setup();


	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		driver.get("http://localhost:" + this.port + "/login");
		loginPage = new LoginPage(driver);
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	@Test
	public void getLoginPage() {
		//driver.get("http://localhost:" + this.port + "/login");

		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void verifyInvalidUser(){
		//driver.get("http://localhost:" + this.port + "/login");
		loginPage.signIn("test","1234");


		String errorMessage = loginPage.getErrMessage();
		Assertions.assertEquals("Invalid username or password", errorMessage);
	}

}
