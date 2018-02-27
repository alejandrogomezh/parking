package co.ceiba.parking.web.funcionales;

//import static org.junit.Assert.*;
//
//import java.net.MalformedURLException;
//import java.net.URL;
//
//import org.junit.Test;
//import org.junit.After;
//import org.junit.Before;
//
//import org.openqa.selenium.*;
//import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.remote.DesiredCapabilities;
//import org.openqa.selenium.remote.RemoteWebDriver;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//
//import static org.hamcrest.CoreMatchers.is;  
//import static org.hamcrest.MatcherAssert.assertThat;  
//import static org.hamcrest.core.IsEqual.equalTo;  
//
//import co.ceiba.parking.messages.Messages;

public class Web {
//  WebDriver browser;
//  String  BaseURL,NodeURL;
//  
//	@Before
//  public void beforeTest() throws MalformedURLException {
//     BaseURL="www.google.com";
//     NodeURL="http://localhost:8090";
//     System.setProperty("webbrowser.firefox.browser", "D:\\ProgramData\\browser_browsers\\geckobrowser_64.exe"); 
//     DesiredCapabilities capa =DesiredCapabilities.firefox();
//     capa.setBrowserName("firefox");
//     capa.setCapability("binary", "D:\\SOFTWARE\\Internet\\Browser\\Mozilla\\FirefoxPortable64\\FirefoxPortable.exe");
//     capa.setPlatform(Platform.ANY);
//     browser=new RemoteWebDriver(new URL(NodeURL),capa);
//  }
//	
//	@Test
//	public void test() {
//			
//	   System.setProperty("webbrowser.chrome.browser", "D:\\ProgramData\\browser_browsers\\chromebrowser.exe");  
//     WebDriver browser = new ChromeDriver();  
//     browser.get("http://the-internet.herokuapp.com/login");  
//     assertThat(browser.getTitle(), is(equalTo("The Internet")));  
//     browser.quit();  
//
//
//		WebDriver browser;   
//		
//		//Firefox's geckobrowser requires you to specify its location.
//		//System.setProperty("webbrowser.gecko.browser","/Users/jimholmes/Utils/geckobrowser");   
//		
//		browser = new FirefoxDriver();    browser.get("http://saucelabs.com");   
//		
//		WebElement header = browser.findElement(By.id("site-header"));   
//		
//		assertTrue((header.isDisplayed()));   
//		
//		browser.close();		 
//		
//    NodeURL="http://localhost:8090";
//		//System.setProperty("webbrowser.chrome.browser", "D:\\ProgramData\\browser_browsers\\chromebrowser.exe"); 
//		//WebDriver browser = new ChromeDriver();
//    System.setProperty("webbrowser.firefox.browser", "D:\\ProgramData\\browser_browsers\\geckobrowser_64.exe"); 
//    DesiredCapabilities capa =DesiredCapabilities.firefox();
//    capa.setBrowserName("firefox");
//    capa.setCapability("binary", "D:\\SOFTWARE\\Internet\\Browser\\Mozilla\\FirefoxPortable64\\FirefoxPortable.exe");
//    capa.setPlatform(Platform.ANY);
//    try {
//			browser=new RemoteWebDriver(new URL(NodeURL),capa);
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		
//		
//		
//    browser.get(NodeURL);
//    WebElement inputVehicle = browser.findElement(By.id("inputVehicle"));
//    WebElement inputPlate = browser.findElement(By.id("inputPlate"));
//    //WebElement inputDisplacement = browser.findElement(By.id("inputDisplacement"));
//    WebElement inputSubmit = browser.findElement(By.id("inputSubmit"));
//    
//    inputVehicle.sendKeys("carro");
//    inputPlate.sendKeys("QWE219");
//    inputSubmit.click();
//    
//    WebDriverWait wait = new WebDriverWait(browser, 2);
//    wait.until(ExpectedConditions.alertIsPresent());
//    Alert alert = browser.switchTo().alert();
//    
//    
//    assertEquals(Messages.INGRESO_SATISFACTORIO, alert.getText());;
//
//    browser.close();
//		
//	}
//  @After
//  public void afterTest() {
//      browser.quit();
//  }
}
