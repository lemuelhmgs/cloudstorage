package com.udacity.jwdnd.course1.cloudstorage;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.concurrent.TimeUnit;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CloudStorageApplicationTests {

	@LocalServerPort
	private int port;
	private LoginPage loginPage;
	private SignupPage signupPage;
	private HomePage homePage;
	private final String CREATED_USER = "test";
	private final String CREATED_PASS="1234";
	private final String NOTE_TITLE="myNote";
	private final String NOTE_DESCRIPTION="Description test";
	private final String CRED_URL = "www.google.com";
	private final String CRED_USERNAME = "testUserName";
	private final String CRED_PASSWORD = "adminPass";


	private WebDriver  driver;



	@BeforeAll
	static void beforeAll() {

		WebDriverManager.chromedriver().setup();


	}

	@BeforeEach
	public void beforeEach() {
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		driver.get("http://localhost:" + this.port + "/login");

		loginPage = new LoginPage(driver);
		signupPage = new SignupPage(driver);
		homePage = new HomePage(driver);
	}

	@AfterEach
	public void afterEach() {

			driver.quit();

	}

	@Test
	public void verifyUnauthorizedUser() {
		driver.get("http://localhost:" + this.port + "/home");
		Assertions.assertEquals("Login", driver.getTitle());
	}

	@Test
	public void verifyInvalidUser(){

		loginPage.signIn("test","1234");
		String errorMessage = loginPage.getErrMessage();
		Assertions.assertEquals("Invalid username or password", errorMessage);
	}

	@Test
	public void verifyValidUser(){
		loginPage.signIn("admin","1234");
		Assertions.assertEquals("Home", driver.getTitle());

	}

	@Test
	public void verifyValidUserLogout() throws InterruptedException {

		loginPage.signIn("admin","1234");
		homePage.logout();
		Assertions.assertEquals("Login", driver.getTitle());

	}

	@Test
	public void verifySignUp(){

		driver.get("http://localhost:" + this.port + "/signup");
		signupPage.signUp("John", "Doe", CREATED_USER, CREATED_PASS);
		Assertions.assertEquals("Registered Successfully. SignIn to Continue", signupPage.Message());
		}

	@Test
	public void verifyUserCanAddNote() throws InterruptedException {

		homePage.addNote(NOTE_TITLE,NOTE_DESCRIPTION);
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.", homePage.getResultMessage());

	}

	@Test
	public void verifyUserCanEditNote() throws InterruptedException {

		homePage.editNote(NOTE_TITLE,NOTE_TITLE+"1",NOTE_DESCRIPTION);
		Assertions.assertEquals(homePage.getTitleText(),NOTE_TITLE+"1");
	}

	@Test
	public void verifyUserCanDeleteNote()throws InterruptedException{

		homePage.deleteNote(NOTE_TITLE,NOTE_DESCRIPTION);
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.", homePage.getResultMessage());

	}

	@Test
	public void verifyUserCanAddCredential() throws InterruptedException{

		homePage.addCredential(CRED_URL,CRED_USERNAME,CREATED_PASS);
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.", homePage.getResultMessage());

	}

	@Test
	public void verifyUserCanEditCredential() throws InterruptedException {

		homePage.editCredential(CRED_URL,
				"https://" + CRED_URL,CRED_USERNAME,CREATED_PASS);
		Assertions.assertEquals(homePage.getCredURLText(),"https://" + CRED_URL);
	}

	@Test
	public void verifyUserCanDeleteCredential() throws InterruptedException {

		homePage.deleteCredential(CRED_URL,CRED_USERNAME,CREATED_PASS);
		Assertions.assertEquals("Your changes were successfully saved. Click here to continue.", homePage.getResultMessage());

	}

	@Test
	public void verifyUserGetsProperErrorWhenPageNotFound(){

		loginPage.signIn("admin","1234");
		driver.get("http://localhost:" + this.port + "/test");
		Assertions.assertEquals("Error", driver.getTitle());

	}




}
