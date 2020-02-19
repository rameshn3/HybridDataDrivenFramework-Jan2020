package com.qa.linkedin.base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import io.github.bonigarcia.wdm.WebDriverManager;

public class TestBase {
	public static WebDriver driver;
	public static WebDriverWait wait;
	public static Properties prop;
	public static Logger log=Logger.getLogger("devpinoyLogger");
	public static String excel_path =System.getProperty("user.dir")+"\\src\\main\\java\\com\\qa\\linkedin\\data\\peopleSearchJan302020.xlsx";
	
	@BeforeSuite
	public void setup() throws FileNotFoundException {
		//1.create object for properties class
		prop = new Properties();
		//read the properties file using FileInputStream class
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\com\\qa\\linkedin\\config\\Config.properties");
		try {
			prop.load(fis);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
if(prop.getProperty("browser").equalsIgnoreCase("firefox")) {
	WebDriverManager.firefoxdriver().setup();
	driver=new FirefoxDriver();
}else if(prop.getProperty("browser").equalsIgnoreCase("chrome")) {
	WebDriverManager.chromedriver().setup();
	driver=new ChromeDriver();
}else if(prop.getProperty("browser").equalsIgnoreCase("edge")) {
	WebDriverManager.edgedriver().setup();
	driver=new EdgeDriver();
}else if(prop.getProperty("browser").equalsIgnoreCase("ie")) {
	WebDriverManager.iedriver().setup();
	driver=new InternetExplorerDriver();
}
log.debug("browser is lanched");
//maximize the window
driver.manage().window().maximize();
//open the url
driver.get(prop.getProperty("Url"));
log.debug("application url is opened:"+prop.getProperty("Url"));
//add implicitwait
driver.manage().timeouts().implicitlyWait(Integer.parseInt(prop.getProperty("implicitwait")), TimeUnit.SECONDS);
wait=new WebDriverWait(driver,Integer.parseInt(prop.getProperty("explicitwait")));
}

@AfterSuite
public void tearDown() {

		if(driver!=null)
		{
			driver.quit();
		}
		log.debug("browser is closed");
	}
}
