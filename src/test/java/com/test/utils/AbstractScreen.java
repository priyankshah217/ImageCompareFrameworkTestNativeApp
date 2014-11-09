package com.test.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.PageFactory;

public abstract class AbstractScreen {

	public AndroidDriver driver;

	public AbstractScreen(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}
	
	public void loadPage(){
		PageFactory.initElements(new AppiumFieldDecorator(driver), this);		
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}
	
	public abstract AbstractScreen clickOnLink(String linkName);
	
	public abstract AbstractScreen clickOnElement(By locator);

	public abstract String getScreenName();

	// public void grabScreenShot(String fileName) throws IOException,
	// InterruptedException, IM4JavaException {
	// AppUtils.takeScreenShot("temp.png");
	// // AppUtils.cropScreenShot("temp.png", fileName);
	// // }
	//
	// public boolean compareScreenUsingMD5(String actualImage,
	// String expectedImage) throws FileNotFoundException {
	// // TODO Auto-generated method stub
	// return AppUtils.compareMD5Hash(actualImage, expectedImage);
	// }
}
