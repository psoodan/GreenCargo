package JenkinsReport.TestReport;
	
	import java.util.List;
	import java.util.Set;

	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.support.ui.Select;
	import org.testng.annotations.Test;

	import com.relevantcodes.extentreports.ExtentReports;
	import com.relevantcodes.extentreports.ExtentTest;
	import com.relevantcodes.extentreports.LogStatus;

	public class ReportDownload {
		
		WebDriver driver;
		ExtentReports report;
		ExtentTest logger;
		
		@Test
		public void greenCargoReport(){
					
					report = new ExtentReports(System.getProperty("user.dir") +"/test-output/STMExtentReport.html", false);
					logger = report.startTest("Test has been started");
					System.setProperty("webdriver.chrome.driver", "C:/Users/psoodan/workspace/TestReport/drivers/chromedriver.exe");
					driver = new ChromeDriver();
					logger.log(LogStatus.INFO, "Chrome browser open");
					driver.get("https://ddakshi:Mpower@123@20.149.4.228/access/prd2/index.pl");  //http://username:password@url
					logger.log(LogStatus.INFO, "User login");
			        driver.findElement(By.xpath("//*[text()='SERVICE REPORTS']")).click();
			        logger.log(LogStatus.INFO, "Click on service reports");
			        driver.findElement(By.xpath("//*[@id='Menu2']/li[1]/ul/li[1]/a/span[2]")).click();
			        driver.findElement(By.xpath("//*[@id='Menu2']/li[1]/ul/li[1]/ul/li[1]/a/span")).click();
			        Select AccountName = new Select(driver.findElement(By.xpath("html/body/table[1]/tbody/tr[3]/td/table/tbody/tr[1]/td[3]/select")));
			        AccountName.selectByVisibleText("Green Cargo (GCO)");
			        driver.findElement(By.xpath(".//*[@id='button']")).click();
			        Select View = new Select(driver.findElement(By.xpath(".//*[@name='reportview']")));
			        View.selectByVisibleText("Historical Events");
					
					WebElement element=driver.findElement(By.name("columns"));
					Select se=new Select(element);
					se.selectByVisibleText("Class");
					se.selectByVisibleText("Count");
					se.selectByVisibleText("Domain");
					se.selectByVisibleText("ElementName");
					se.selectByVisibleText("Event");
					se.selectByVisibleText("FirstNotifiedAt");
					se.selectByVisibleText("InstanceDisplayName");
					se.selectByVisibleText("Owner");
					se.selectByVisibleText("Ticket");
					se.selectByVisibleText("EventText");
					se.selectByVisibleText("LastClearedAt");
					se.selectByVisibleText("snhID");
					Select timeSpan = new Select(driver.findElement(By.name("timespan")));
			        timeSpan.selectByVisibleText("Last 24 Hours");
					driver.findElement(By.xpath(".//*[@id='effect']/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[4]/td/a/img")).click();

					
					String parentWindow =driver.getWindowHandle();
					Set<String> allWindow= driver.getWindowHandles();
					for(String obj:allWindow){
						if(!(obj.equals(parentWindow))){   //in this case new window open when we click on calender so here it means that if window not equal to parent window switch to other window
							
							driver.switchTo().window(obj);
							break;
						
						}
					}
					driver.findElement(By.xpath("//img[@alt='previous month']")).click();
					driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr[3]/td/a/font[text()='1']")).click();
					driver.switchTo().window(parentWindow);
					driver.findElement(By.xpath("//*[@id='effect']/form/table/tbody/tr[2]/td/table/tbody/tr[1]/td[2]/table/tbody/tr[3]/td/table/tbody/tr/td[4]/table/tbody/tr[5]/td/a/img")).click();
					Set<String> allWindow1 = driver.getWindowHandles();
					for(String obj : allWindow1){
						if(!(obj.equals(parentWindow))){
							driver.switchTo().window(obj);
							break;
						}
					}


					
			driver.findElement(By.xpath("//img[@alt='previous month']")).click();
			List<WebElement>activeRows	=driver.findElements(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr"));
			int totalRow=activeRows.size()-1;
			List<WebElement> activeDates =driver.findElements(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr["+totalRow+"]/td/a/font[@color='#000000']"));
			int activeLastDate = activeDates.size();
			driver.findElement(By.xpath("html/body/table/tbody/tr/td/table/tbody/tr["+totalRow+"]/td["+activeLastDate+"]/a/font[@color='#000000']")).click();
			driver.switchTo().window(parentWindow);
	        Select OutputOptions = new Select(driver.findElement(By.name("outtype")));
			OutputOptions.selectByVisibleText("Excel");
			driver.findElement(By.name("Submit")).click();
			report.endTest(logger);
			report.flush();
			report.close();
}
	}
