
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.CapabilityType;

import io.github.bonigarcia.wdm.WebDriverManager;


public class AllLevels {
	
	private static WebDriver driver = null;
	private By startButton = By.id("start_button");
	private By levelTitle=By.cssSelector("h1");
	private By input = By.id("input");
	private By nextButton = By.id("next");
	private By customDummyLabel=By.className("custom_dummy_label");
	private By link = By.linkText("Enlace!");
	private By hiddenButtonPath = By.xpath("//*[@type='button' and @style='display:none;']");
	private By psswdPath = By.xpath("//*[@id='pass']");

	@BeforeAll
	public static void setUpBeforeClass() throws Exception {
		
  		WebDriverManager.chromedriver().setup();
  		ArrayList<String> optionsList = new ArrayList<String>();
		ChromeOptions chromeOptions = new ChromeOptions();
		optionsList.add("--start-maximized");
		optionsList.add("--incognito");
		optionsList.add("disable-notifications");
		chromeOptions.addArguments(optionsList);
		chromeOptions.setCapability(CapabilityType.ACCEPT_SSL_CERTS, true);
  		chromeOptions.setCapability(CapabilityType.ACCEPT_INSECURE_CERTS, true);
				
		driver = new ChromeDriver(chromeOptions);
		
	}

	@AfterAll
	public static void tearDownAfterClass() throws Exception {
		// Close browser
		driver.quit();
	}

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void test() throws InterruptedException {
		
		// Open URL
		driver.get("http://pruebaselenium.serviciosdetesting.com/");
		WebElement levelTitleElement=driver.findElement(levelTitle);
		
		// Level 1
		assertEquals(levelTitleElement.getText(),"Práctica Selenium");
		WebElement startButtonElement=driver.findElement(startButton);
		startButtonElement.click();
		levelTitleElement=driver.findElement(levelTitle);
		assertEquals(levelTitleElement.getText(),"Level 2");
		
		Thread.sleep(2000); // Useful to debug by waiting some miliseconds
		
		// Level 2
		WebElement textBox1=driver.findElement(input);
		textBox1.sendKeys("selenium");
		WebElement nextButtonElement=driver.findElement(nextButton);
		nextButtonElement.click();
		
		Thread.sleep(2000);
		
		// Level 3
		WebElement dummyLabel=driver.findElement(customDummyLabel);
		String text1 = dummyLabel.getText();
		WebElement textBox2=driver.findElement(input);
		textBox2.sendKeys(text1);
		WebElement nextButtonElement2=driver.findElement(nextButton);
		nextButtonElement2.click();
		
		Thread.sleep(2000);
		
		// Level 4
		By boton[]=new By[4]; //En esta array creamos las 4 variables By.linkText
		WebElement nivel4[] = new WebElement[4]; //En esta array creamos las 4 driver.findElement
		
		for(int j=0,i=1;j<boton.length;j++,i++) {
			boton[j]=By.linkText("Botón "+ i);	
			nivel4[j]=driver.findElement(boton[j]);
			nivel4[j].click();
			Thread.sleep(1000);
		}
				
		Thread.sleep(2000);
		
		// Level 5
		WebElement link5=driver.findElement(link);
		link5.click();
		
		Thread.sleep(2000);
		
		// Level 6
		WebElement hiddenButton=driver.findElement(hiddenButtonPath);
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", hiddenButton);
		
		Thread.sleep(2000);
		
		// Level 7
		Alert aceptar = driver.switchTo().alert();
		aceptar.accept();
		
		Thread.sleep(2000);
		
		// Level 8 
		Alert level8 = driver.switchTo().alert();
		level8.sendKeys("9");
		level8.accept();
		
		Thread.sleep(2000);
		
		// Level 9
		// Almacenar el identificador de ventana actual
		String winHandleBefore = driver.getWindowHandle();

		// Cambiar a nueva ventana abierta
		for(String winHandle : driver.getWindowHandles()){
		    driver.switchTo().window(winHandle);
		}
				
		WebElement password=driver.findElement(psswdPath);
		String text = password.getText();
		
		// Cierra la nueva ventana, si esa ventana ya no es necesaria
		driver.close();

		// Volver al navegador original (primera ventana)
		driver.switchTo().window(winHandleBefore);
		
		WebElement textBox3=driver.findElement(input);
		textBox3.sendKeys(text);
		WebElement nextButtonElement3=driver.findElement(nextButton);
		nextButtonElement3.click();
		
		Thread.sleep(2000);
		
		// Level 10
		Actions action = new Actions(driver);
		
		WebElement element1 = driver.findElement(By.id("source"));
		WebElement element2 = driver.findElement(By.id("target"));
		
		action.dragAndDrop(element1, element2).build().perform();
		
		Thread.sleep(4000);	
	}
}
