package com.qa.linkedin.page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import com.qa.linkedin.base.TestBase;

public class LinkedinLandingPage extends TestBase{

	public LinkedinLandingPage() {
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(xpath="//*[@class='nav__logo lazy-loaded']")
	WebElement linkedin_logo;
	
	@FindBy(linkText="Sign in")
	WebElement signin_link;
	
	@FindBy(xpath = "//h1[contains(@class,'heading')]")
	WebElement welcomeback_headertext;
	
	@FindBy(xpath = "//p[contains(@class,'subheading')]")
	WebElement subheadertext;
	
	@FindBy(id = "username")
	WebElement email_editbox;
	
	@FindBy(name = "session_password")
	WebElement password_editbox;
	
	@FindBy(xpath="//button[@type='submit' and @aria-label='Sign in']")
	WebElement Signin_button;
	
	
	public void verifyLandingPageTitle() {
		wait.until(ExpectedConditions.titleContains("LinkedIn: Log In or Sign Up"));
		log.debug("verifying the linkedin landing page title-->"+driver.getTitle());
		Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up");
		
	}
	
	public void verifyLinkedinLogo() {
		wait.until(ExpectedConditions.visibilityOf(linkedin_logo));
		log.debug("verified the linkedin logo");
		Assert.assertTrue(linkedin_logo.isDisplayed());
	}
	
	public void clickOnSigninLink() {
		wait.until(ExpectedConditions.visibilityOf(signin_link));
		log.debug("clicking on signin link");
		signin_link.click();
		log.debug("clicked on signin link in landing page");
		wait.until(ExpectedConditions.titleContains("LinkedIn Login, Sign in | LinkedIn"));
		wait.until(ExpectedConditions.visibilityOf(welcomeback_headertext));
		wait.until(ExpectedConditions.visibilityOf(subheadertext));
		Assert.assertTrue(welcomeback_headertext.isDisplayed());
		Assert.assertTrue(subheadertext.isDisplayed());
	}
	
	public LinkedinHomePage login(String uname,String pwd) {
		email_editbox.clear();
		email_editbox.sendKeys(uname);
		log.debug("typed the username value in email_editbox-->"+uname);
		password_editbox.clear();
		password_editbox.sendKeys(pwd);
		log.debug("Typed the password value in password editbox-->"+pwd);
		Signin_button.click();
		return new LinkedinHomePage();
	}
	
}
