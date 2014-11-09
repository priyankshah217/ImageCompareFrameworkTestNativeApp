package com.test.apidemo.app.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import com.test.utils.AbstractScreen;

public class ActivityScreen extends AbstractScreen {

	@AndroidFindBy(accessibility = "Hello World")
	private WebElement helloworldMenuItem;

	public ActivityScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
	}

	public HelloWorldScreen getHelloWorldScreen() {
		// TODO Auto-generated method stub		
		helloworldMenuItem.click();		
		return new HelloWorldScreen(driver);
	}

	@Override
	public String getScreenName() {
		// TODO Auto-generated method stub
		return getClass().getSimpleName();
	}

	@Override
	public AbstractScreen clickOnLink(String linkName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AbstractScreen clickOnElement(By locator) {
		// TODO Auto-generated method stub
		return null;
	}
}
