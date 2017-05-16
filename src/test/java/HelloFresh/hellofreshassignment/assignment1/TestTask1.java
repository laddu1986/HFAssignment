/**Task 1 - Java, Selenium Webdriver and Maven

 

Hellofresh is present in several countries as we can see in https://www.hellofresh.co.uk and https://www.hellofresh.com.

 

Based on that, create automated tests for following scenarios covering both countries mentioned above:

Register new user
Login with existing user
Add classic box/plan to cart and validate that box and price are ok in checkout page
 

Tip: It would be really nice if same test could run in different countries just based on parameter when running it from command line!  Don't forget to use Maven on the project!

**/
package HelloFresh.hellofreshassignment.assignment1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TestTask1 {
	
	public WebDriver driver;
	private String gender;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private String birthDate;
	private String birthMonth;
	private String birthYear;
	private String newsletterSubscription;
	
	String PATH = null;
	
	//Property Files
	private String appPropertiesFile = "user.properties";
	
	private String URL=System.getProperty("url");
	
	// LOCATORS
	private String gender_list_locator = "//select[@id='gender-input']";
	private String firstname_locator = "//input[@id='first-name-input']";
	private String lastname_locator = "//input[@id='last-name-input']";
	private String email_locator = "//input[@id='email-input']";
	private String password_locator = "//input[@id='password-input']";
	private String birthdate_locator= "//input[@id='day-input']";
	private String birthmonth_locator= "//input[@id='month-input']";
	private String birthyear_locator = "//input[@id='year-input']";
	private String newlettersubs_locator = "//input[@id='register-input']";
	private String login_link_locator = "//button[@id='login-button']";
	private String register_link_locator = "//div[@class='modal-content']//p/a[text()='Register']";
	private String register_button_locator = "//button[@id='register-button']";
	
	@Before
	public void setUp(){
		
		File file= null;
		FileInputStream fileInput =null;
		
		try {
			
			Path currentRelativePath = Paths.get("");
			PATH = currentRelativePath.toAbsolutePath().toString();
			System.out.println("Getting the New User Details from the Properties file");
			
			String osType = System.getProperty("os.name").toLowerCase();
			if(osType.indexOf("mac") >= 0)
			      file = new File(PATH + "/" + appPropertiesFile);
			else if(osType.indexOf("lin") >= 0)
				  file = new File(PATH + "/" + appPropertiesFile);
			else 
				  file = new File(PATH + "\\" + appPropertiesFile);
			fileInput = new FileInputStream(file);
			Properties properties = new Properties();
			properties.load(fileInput);
			
			gender = properties.getProperty("gender");
			firstName = properties.getProperty("firstName");
			lastName = properties.getProperty("lastName");
			email = properties.getProperty("email");
			password = properties.getProperty("password");
			birthDate = properties.getProperty("birthDate");
			birthMonth = properties.getProperty("birthMonth");
			birthYear = properties.getProperty("birthYear");
			newsletterSubscription = properties.getProperty("newsletterSubscription");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fileInput != null) {
				try {
					fileInput.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("Initializing Firefox Browser");
		driver = new FirefoxDriver();
		
		System.out.println("Maximizing Window");
		driver.manage().window().maximize();
		
		System.out.println("Setting implicit wait of 10 seconds");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
		System.out.println("Browsing HelloFresh Website");
		driver.get(URL);
		
	}
	
	
	@Test
	public void createNewUser() throws InterruptedException{
		
		waitForElementToBeClickable(login_link_locator, 60);
		System.out.println("Clicking on Login Button");
		driver.findElement(By.xpath(login_link_locator)).click();
		
		System.out.println("Waiting for Register Modal To appear");
		waitForElementToPresent(register_link_locator, 5);
		System.out.println("Clicking on Register Link");
		driver.findElement(By.xpath(register_link_locator)).click();
		
		System.out.println("Waiting for Register form to appear");
		waitForElementToPresent(firstname_locator, 5);
		
		System.out.println("Selecting Gender as provided in the Properties file");
		Select genderList = new Select(driver.findElement(By.xpath(gender_list_locator)));
		if(gender.trim().equals("Mr.") || gender.trim().equals("Mr"))
		    genderList.selectByVisibleText("Mr.");
		else
			genderList.selectByVisibleText("Mrs./Miss/Ms.");
		
		System.out.println("Entering Input details of new user provided in the properties file");
		driver.findElement(By.xpath(firstname_locator)).sendKeys(firstName);
		driver.findElement(By.xpath(lastname_locator)).sendKeys(lastName);
		driver.findElement(By.xpath(email_locator)).sendKeys(email);
		driver.findElement(By.xpath(password_locator)).sendKeys(password);
		driver.findElement(By.xpath(birthdate_locator)).sendKeys(birthDate);
		driver.findElement(By.xpath(birthmonth_locator)).sendKeys(birthMonth);
		driver.findElement(By.xpath(birthyear_locator)).sendKeys(birthYear);
		
		System.out.println("Checking Newssubscription checkbox if Yes");
		if(newsletterSubscription.trim().equals("Yes"))
			driver.findElement(By.xpath(newlettersubs_locator)).click();
		
		System.out.println("Clicking on Register Button");
        driver.findElement(By.xpath(register_button_locator)).click();
		
        System.out.println("Waiting for Register to be successful and User name to appearing top right corner");
		waitForElementToPresent("//nav[@id='nav-navigation']//li//span[@ng-bind='::customer.firstName']", 30);
		
		System.out.println("Verifying that register is successful and correct User name is appearing on the screen");
		Assert.assertTrue("Login Failed", driver.findElement(By.xpath("//nav[@id='nav-navigation']//li//span[@ng-bind='::customer.firstName']")).getText().equals(firstName));
		
		System.out.println("Navigating to the Homepage of Hello Fresh");
        driver.findElement(By.xpath("//nav//img[@alt='HelloFresh']")).click();
		
        System.out.println("Waiting for View Plans button to appear and clicking on it");
		waitForElementToPresent("//button[@id='banner-view-plans-button']", 30);
		driver.findElement(By.xpath("//button[@id='banner-view-plans-button']")).click();
		
		System.out.println("Waiting for Plans to appear and clicking on Classic Plan");
		waitForElementToPresent("//a[@id='classic-menu-product-detail-button' and text()='View Plan']", 30);
		driver.findElement(By.xpath("//a[@id='classic-menu-product-detail-button' and text()='View Plan']")).click();
		
		System.out.println("Waiting for Add to Cart Button");
		waitForElementToPresent("//button[@id='add-to-cart-button']", 10);
		String boxType = driver.findElement(By.xpath("//h1")).getText();
		System.out.println("The Box Type choosen is : " + boxType);
		String planPrice = driver.findElement(By.xpath("//h4//span[@ng-bind='grandTotal']")).getText();
		System.out.println("The Total Plan price is : " + planPrice);
		
		System.out.println("Clicking on Add to cart Button");
		driver.findElement(By.xpath("//button[@id='add-to-cart-button']")).click();
		
		System.out.println("Waiting for Checkout page to appear");
		waitForElementToPresent("//button[text()='Next: Payment Information']", 20);
		
		System.out.println("Fetching Plan price on Checkout Page");
		String planPriceInCart = driver.findElement(By.xpath("//div[@ui-view='summary']//p//strong")).getText();
		
		System.out.println("Verifying Plan price on Checkout Page as it should be same as appearing on Plans Page");
		Assert.assertTrue("Price is not matching", planPriceInCart.equals(planPrice));
		
		System.out.println("Verifying Plan Type on Checkout Page as it should be same as appearing on Plans Page");
		String boxTypeInCart = driver.findElement(By.xpath("//div[@ui-view='summary']//strong[@applanga='classic-menu-title']")).getText();
		Assert.assertTrue("Box type is not matching", boxType.contains("Classic") && boxTypeInCart.contains("Classic"));
		
	}
	
	@After
	public void cleanUp(){
		driver.quit();
	}

	public void waitForElementToPresent(String locator, long time){
		System.out.println("Wait for element to be present");
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
		
	}
	
	public void waitForElementToBeClickable(String locator, long time){
    	System.out.println("Wait for element to be clickable");
		WebDriverWait wait = new WebDriverWait(driver, time);
		wait.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));
		
	}

}
