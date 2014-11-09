package com.test.apidemo.app.screens;

import io.appium.java_client.android.AndroidDriver;

import org.openqa.selenium.By;

import com.test.utils.AbstractScreen;

public class HelloWorldScreen extends AbstractScreen {	

	public HelloWorldScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
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
