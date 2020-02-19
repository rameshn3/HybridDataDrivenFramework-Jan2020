package com.qa.linkedin.testcase;

import org.testng.annotations.Test;

import com.qa.linkedin.base.TestBase;
import com.qa.linkedin.page.LinkedinHomePage;
import com.qa.linkedin.page.LinkedinLandingPage;
import com.qa.linkedin.page.SearchResultsPage;
import com.qa.linkedin.utils.TestUtil;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;

import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.AfterClass;

public class SearchDataDrivenTest extends TestBase{
 LinkedinHomePage lhmpg=null;
 LinkedinLandingPage llpg=null;
 SearchResultsPage srpg=null;
  @BeforeClass
  public void initlizeObjects() {
	  log.debug("initilize the objects in BeforeClass annotation");
	  lhmpg=new LinkedinHomePage();
	  llpg=new LinkedinLandingPage();
	  srpg=new SearchResultsPage();
  }

  @Test(dataProvider="searchData",dependsOnMethods= {"loginTest"})
  public void searchPeopleTest(String s) throws InterruptedException {
	 lhmpg.verifyProfileCard();
	 lhmpg.doPeopleSearch(s);
	 long cnt=srpg.getResultsCount();
	 log.debug("search results count for: "+s+" is -->"+cnt);
	 driver.navigate().back();
	 
  }
  
  
  @DataProvider
  public Object[][] searchData() throws InvalidFormatException, IOException{
	  
	  Object[][] data=TestUtil.getTestData(excel_path, "Sheet1");
	  return data;
  }
  
  
  @Test
  public void loginTest() {
	  log.debug("Started Executing loginTest()....");
	  llpg.verifyLandingPageTitle();
	  llpg.verifyLinkedinLogo();
	  llpg.clickOnSigninLink();
	  llpg.login(prop.getProperty("uname"), prop.getProperty("pwd"));
	  
	  
  }
  
  
  
  @AfterClass
  public void doLogOutTearDown() throws InterruptedException {
	 lhmpg.doLogout();
	 Thread.sleep(3000);
  }

}
