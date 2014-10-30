package com.test.apidemo.app.screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.im4java.core.IM4JavaException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utils.AppUtils;

public class HomeScreen extends AbstractScreen {

	@AndroidFindBy(accessibility = "App")
	private WebElement appMenuItem;

	public HomeScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15,
				TimeUnit.SECONDS), this);
	}

	public AppMenuScreen getAppMenuPage() throws IOException,
			InterruptedException, IM4JavaException {
		// driver.findElement(By.name("App")).click();

		driver.manage().timeouts()
				.implicitlyWait(AppUtils.DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		appMenuItem.click();
		WebDriverWait wait = new WebDriverWait(driver,
				AppUtils.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
				.AccessibilityId("Activity")));
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);				
		return new AppMenuScreen(driver);
	}
	
}
