package com.test.apidemo.app.screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utils.AppUtils;

public class AppMenuScreen extends AbstractScreen {

	@AndroidFindBy(accessibility = "Activity")
	private WebElement appActivity;

	public AppMenuScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15,
				TimeUnit.SECONDS), this);
	}

	public AppActivityScreen getActivityPage() {
		// TODO Auto-generated method stub

		driver.manage().timeouts()
				.implicitlyWait(AppUtils.DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		appActivity.click();
		WebDriverWait wait = new WebDriverWait(driver,
				AppUtils.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
				.AccessibilityId("Animation")));
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);

		return new AppActivityScreen(driver);
	}

}
