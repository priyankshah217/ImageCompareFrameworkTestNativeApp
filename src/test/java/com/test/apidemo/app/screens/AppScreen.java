package com.test.apidemo.app.screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utils.AbstractScreen;
import com.test.utils.AppUtils;

public class AppScreen extends AbstractScreen {

	@AndroidFindBy(accessibility = "Activity")
	private WebElement activityMenuItem;

	public AppScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		loadPage();
	}

	public ActivityScreen getActivityPage() {
		// TODO Auto-generated method stub
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		activityMenuItem.click();
		WebDriverWait wait = new WebDriverWait(driver,
				AppUtils.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
				.AccessibilityId("Hello World")));
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		return new ActivityScreen(driver);
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
