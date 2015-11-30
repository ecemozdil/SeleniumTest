package com.maventest;

	import java.util.regex.Pattern;
	import java.util.concurrent.TimeUnit;
	import org.junit.*;
	import static org.junit.Assert.*;
	import static org.hamcrest.CoreMatchers.*;
	import org.openqa.selenium.*;
	import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.thoughtworks.selenium.webdriven.commands.WaitForPageToLoad;

	public class SeleniumTest {
	  private WebDriver driver;
	  private String baseUrl;
	  private boolean acceptNextAlert = true;
	  private StringBuffer verificationErrors = new StringBuffer();

	  @Before
	  public void setUp() throws Exception {
	    driver = new FirefoxDriver();
	    baseUrl = "https://www.n11.com";
	    driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  }

	  @Test
	  public void testN11Selenium() throws Exception {
	    driver.get("https://www.n11.com/giris-yap");
	    assertEquals("Giriþ Yap - n11.com", driver.getTitle());
	    driver.findElement(By.xpath("//form[@id='loginForm']/div[4]")).click();
	    String[] handleArray = new String[3];
	    int i = 0;
	    for (String handle : driver.getWindowHandles()) {
	    	handleArray[i] = handle;
	    	i += 1;
	     	driver.switchTo().window(handle);
	     }
	    
	    driver.findElement(By.id("email")).clear();
	    driver.findElement(By.id("email")).sendKeys("ecem_ozdil@hotmail.com");
	    driver.findElement(By.id("pass")).clear();
	    driver.findElement(By.id("pass")).sendKeys("ecem1991");
	    driver.findElement(By.id("u_0_2")).click();
	    driver.switchTo().window(handleArray[0]);
	    driver.get("http://www.n11.com");
	    assertEquals("n11.com - Alýþveriþin Uðurlu Adresi", driver.getTitle());
	   
	    //driver.findElement(By.linkText("Kitap")).click();
	    driver.get("http://www.n11.com/kitap");
	    WaitForPageToLoad wait2 = new WaitForPageToLoad();
	    wait2.setTimeToWait(10000);
	    assertEquals("Kitap - n11.com", driver.getTitle());
	    driver.findElement(By.linkText("Yazarlar")).click();
	    assertEquals("Yazarlar - Türk ve Yabancý Yazarlar - n11.com", driver.getTitle());
	    driver.get("http://www.n11.com/yazarlar/A");
	    wait2.setTimeToWait(10000);
	    assertEquals("A ile Baþlayan Yazarlar 1 - n11.com", driver.getTitle());
	    Number authorcount = driver.findElements(By.xpath("//body/div[@id='wrapper']/div[@id='contentWrapper']/div[@class='container']/div[@id='content']/div[@id='authorsList']/div/ul/li")).size();
	    String authorcountS = authorcount + "";
	    Integer authorcountI = Integer.parseInt(authorcountS);
	    if(authorcountI <= 80)
	    {
	    	driver.navigate().to("http://www.n11.com/yazarlar/A?pg=2");
		    assertEquals("A ile Baþlayan Yazarlar 2 - n11.com", driver.getTitle());
		    System.out.println("dededede");
		    assertFalse(isElementPresent(By.linkText("Ahmet ÝNEM (1)")));
		    System.out.println("asfasdf");
		    driver.get("http://www.n11.com/yazarlar/B");
		    assertEquals("B ile Baþlayan Yazarlar 1 - n11.com", driver.getTitle());
		    System.out.println("gela buraya");
		    Number authorcount2 = driver.findElements(By.xpath("//body/div[@id='wrapper']/div[@id='contentWrapper']/div[@class='container']/div[@id='content']/div[@id='authorsList']/div/ul/li")).size();
		    String authorcount2S = authorcount2 + "";
		    Integer authorcount2I = Integer.parseInt(authorcount2S);
		    if (authorcount2I <= 80)
		    {
		    	System.out.println("gidela buraya son!!");
		    	return;
		    }
	    }
	    
	    
	    
	  }

	  @After
	  public void tearDown() throws Exception {
	    driver.quit();
	    String verificationErrorString = verificationErrors.toString();
	    if (!"".equals(verificationErrorString)) {
	      fail(verificationErrorString);
	    }
	  }

	  private boolean isElementPresent(By by) {
	    try {
	      driver.findElement(by);
	      return true;
	    } catch (NoSuchElementException e) {
	      return false;
	    }
	  }

	  private boolean isAlertPresent() {
	    try {
	      driver.switchTo().alert();
	      return true;
	    } catch (NoAlertPresentException e) {
	      return false;
	    }
	  }

	  private String closeAlertAndGetItsText() {
	    try {
	      Alert alert = driver.switchTo().alert();
	      String alertText = alert.getText();
	      if (acceptNextAlert) {
	        alert.accept();
	      } else {
	        alert.dismiss();
	      }
	      return alertText;
	    } finally {
	      acceptNextAlert = true;
	    }
	  }
	}