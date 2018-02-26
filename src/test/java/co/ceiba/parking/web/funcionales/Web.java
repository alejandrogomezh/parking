package co.ceiba.parking.web.funcionales;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import co.ceiba.parking.messages.Messages;

public class Web {

	@Test
	public void test() {
		
		WebDriver driver = null;
		try {
			driver = new RemoteWebDriver(new URL("http://localhost:8090"), DesiredCapabilities.chrome());
			driver.get("http://www.google.com");

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		WebDriver browser = new ChromeDriver();
    browser.get("http://localhost:8090");
    WebElement inputVehicle = browser.findElement(By.id("inputVehicle"));
    WebElement inputPlate = browser.findElement(By.id("inputPlate"));
    //WebElement inputDisplacement = browser.findElement(By.id("inputDisplacement"));
    WebElement inputSubmit = browser.findElement(By.id("inputSubmit"));
    
    inputVehicle.sendKeys("carro");
    inputPlate.sendKeys("QWE219");
    inputSubmit.click();
    
    WebDriverWait wait = new WebDriverWait(browser, 2);
    wait.until(ExpectedConditions.alertIsPresent());
    Alert alert = browser.switchTo().alert();
    
    
    assertEquals(Messages.INGRESO_SATISFACTORIO, alert.getText());;

    browser.close(); 
	}

}
