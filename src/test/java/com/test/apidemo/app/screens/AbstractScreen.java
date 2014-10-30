package com.test.apidemo.app.screens;

import io.appium.java_client.android.AndroidDriver;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.im4java.core.IM4JavaException;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import com.test.utils.AppUtils;

public abstract class AbstractScreen {

	public AndroidDriver driver;

	public AbstractScreen(AndroidDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	protected boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public void grabScreenShot(String fileName) throws IOException, InterruptedException, IM4JavaException {
		AppUtils.takeScreenShot("temp.png");
		AppUtils.cropScreenShot("temp.png", fileName);
	}

	public boolean validateScreen(String actualImage, String expectedImage) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return AppUtils.compareScreenShot(actualImage, expectedImage);
	}
	
	public boolean compareScreenUsingMD5(String actualImage, String expectedImage) throws FileNotFoundException {
		// TODO Auto-generated method stub
		return AppUtils.compareMD5Hash(actualImage, expectedImage);
	}
}
