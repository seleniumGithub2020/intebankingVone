

package com.inetbanking.testCases;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import com.inetbanking.utilities.ReadConfig;


public class BaseClass5 {
    
	ReadConfig readconfig = new ReadConfig();
	public String baseURL= readconfig.getApplicationURL();
	public String username= readconfig.getUsername();
    public String password= readconfig.getPassWord();
   
    public static WebDriver driver;
    public static Logger logger;
    
    @Parameters("browser")
    @BeforeClass
    public void setup(String br)
    {
      //System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
	  //driver = new ChromeDriver();
      
      logger=Logger.getLogger("ebanking");
      PropertyConfigurator.configure("Log4j.properties");
      
      if(br.equals("chrome"))
      {   
         System.setProperty("webdriver.chrome.driver",readconfig.getChromePath());
         driver=new ChromeDriver();
      }
      else if(br.equals("firefox"))
      {   
          System.setProperty("webdriver.gecko.driver",readconfig.getFirefoxPath());
          driver=new FirefoxDriver();
      } 
      else if(br.equals("ie"))
      {   
          System.setProperty("webdriver.chrome.driver",readconfig.getIEPath());
          driver=new InternetExplorerDriver();
      }
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.get(baseURL);
    }
    
    @AfterClass
    public void tearDown()
    {
    	driver.quit();
    }
}
    
    