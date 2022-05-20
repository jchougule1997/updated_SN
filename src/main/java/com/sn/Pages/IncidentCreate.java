package com.sn.Pages;

import org.openqa.selenium.By;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.formula.functions.T;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.sn.Commons.TestBase;
import com.sn.utilities.ExcelUtilities;
import com.sn.Commons.TestBase;

public class IncidentCreate extends TestBase
{
	TestBase t = new TestBase();
	//driver.findElement(By.className("sn-widget-list-title")).click();


	@FindBy(xpath=("//button[@id='sysverb_new']"))
	WebElement ClickNew;

	@FindBy(id = "incident.number")
	WebElement Incidentnumber;

	@FindBy(id=("sys_display.incident.caller_id"))
	WebElement SelectAbel;

	@FindBy(xpath ="//*[@id=\"list_nav_incident\"]/div/div[1]/h1/a")
	WebElement IncidentCreate;

	@FindBy(id=("incident.short_description"))
	WebElement SendMsg;

	//@FindBy(id=("sysverb_insert"))
	//WebElement ClickSub;

	@FindBy(id=("sysverb_insert_bottom"))
	WebElement ClickSub;


	@FindBy(xpath ="//a[@class='list_action list_top_title']")
	WebElement IncidentCreate1;

	@FindBy(xpath = "//*[@id=\"output_messages\"]/div/div/span[2]")
	WebElement Incidenterrormsg;
	@FindBy(linkText=("Inquiry / Help"))
	WebElement LinkText;
	@FindBy(xpath = "/html/body/div[1]/div[1]/span/div/div[1]/div/span/div/div/input")
	WebElement SearchButton;


	public IncidentCreate()
	{
		PageFactory.initElements(t.driver, this);
	}

	/*public String verifyIncidentPage()
	{
		return IncidentCreate.getText();	
	}*/

	public String VerifyTitle()
	{
		return t.driver.getTitle();

	}



	public String verifyIncidentPage()
	{
		//WebDriverWait wait = new WebDriverWait(driver,30);
		//until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='list_action list_top_title']")));
		return IncidentCreate1.getText();	
	}

	public ResolveInfo NewIncident(String caller,String shortdiscription) throws InterruptedException 
	{
		//driver.manage().timeouts().implicitlyWait(30,TimeUnit.SECONDS );
		//used frame concept
		System.out.println("................This is New incident Create Page class.........");
		Thread.sleep(2000);
		//switch to 0th index frame
		driver.switchTo().frame(0);
		
		//Click on New Button Create New Incident Page
		boolean createNewIncident_Button =ClickNew.isDisplayed();
		System.out.println("Is CreateNewIncident_Button displayed  :"+ createNewIncident_Button);
		ClickNew.click();
		System.out.println("Clicked on new Button");
		System.out.println("-----------------------------------------------------------------");
		
		//Get The value of Incident
		String IncidentNo = Incidentnumber.getAttribute("value");
		System.out.println("New Incident Number" +IncidentNo);
		//SelectAbel.clear();
		Thread.sleep(1000);
		//SelectAbel.clear();
		//Send input in caller field
		
		boolean  Caller_Text_Field = SelectAbel.isDisplayed();
		System.out.println("Is Caller_Text_Field displayed  :"+ Caller_Text_Field );
		SelectAbel.clear();
		SelectAbel.sendKeys(caller);
		System.out.println("Passed input in caller field from EXEL");
		//SelectAbel.clear();
		//SendMsg.clear();
		Thread.sleep(1000);
		SendMsg.sendKeys(shortdiscription);
		//driver.switchTo().frame(0);
		ClickSub.click();


		SearchButton.sendKeys(IncidentNo);
		Actions act = new Actions(driver);
		act.sendKeys(Keys.ENTER).build().perform();
		System.out.println("sended incident number in  search  field to search created incident number  ");
		String t = IncidentNo;
		// getPageSource() to get page source
		if ( driver.getPageSource().contains( t))
		{
			System.out.println( t + "this incident number created succesfully");
			LinkText.click();
		} else {
			System.out.println("incident not created ");
		}

		return new ResolveInfo();
		
		
		
	}
	public void InvalidIncidentcreate(String caller, String shortdiscription) throws InterruptedException
	{
		driver.switchTo().frame(0);
		ClickNew.click();
		String IncidentNo = Incidentnumber.getAttribute("value");
		System.out.println(IncidentNo);
		SelectAbel.clear();
		SelectAbel.sendKeys(caller);
		Thread.sleep(1000);
		SendMsg.sendKeys(shortdiscription);
		//Thread.sleep(3000);
		ClickSub.click();
		String incidenterror = Incidenterrormsg.getText();
		System.out.println("Error msg is:" +incidenterror );
		Assert.assertEquals(incidenterror, "The following mandatory fields are not filled in: Caller", "Test failed");

	}
}
