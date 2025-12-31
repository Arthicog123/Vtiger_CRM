package practice_module;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import objectRepo.HomePage;

public class ParameterTest {
	
	@Test
	public void cmdLine() throws Exception {
		String BROWSER = System.getProperty("browser");
		String URL = System.getProperty("url");
		String USERNAME = System.getProperty("username");
		String PASSWORD = System.getProperty("password");
		
		System.out.println(BROWSER);
		System.out.println(URL);
		System.out.println(USERNAME);
		System.out.println(PASSWORD);
		EdgeOptions options = new EdgeOptions();
		options.addArguments("--remote-allow-origins=*");
		options.addArguments("--disable-dev-shm-usage");
		options.addArguments("--no-sandbox");
		options.addArguments("--disable-gpu");
		options.addArguments("--start-maximized");
		WebDriver driver = null;
		// launch the browser
		if (BROWSER.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		}
		else if (BROWSER.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}
		else if (BROWSER.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver(options);
		}
		
		// maximize the window
		driver.manage().window().maximize();
		
		// enter the url
		driver.get(URL);
		
		// wait for page load
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		System.out.println("---browser launched successfully---");
		
		Thread.sleep(3000);
		
		// enter valid username and password and click on login button
		driver.findElement(By.name("user_name")).sendKeys(USERNAME);
		driver.findElement(By.name("user_password")).sendKeys(PASSWORD);
		driver.findElement(By.id("submitButton")).click();
		
		System.out.println("---logged into application---");
		
		Thread.sleep(3000);
		
	
		HomePage hp = new HomePage(driver);
		hp.signOut(driver);
		System.out.println("---logged out from application---");
		
		Thread.sleep(3000);
		
		driver.quit();
		System.out.println("---browser closed successfully---");
	}

}
