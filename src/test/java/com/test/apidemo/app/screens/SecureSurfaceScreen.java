package com.test.apidemo.app.screens;

import io.appium.java_client.MobileBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.utils.AppUtils;

public class SecureSurfaceScreen extends AbstractScreen {

	@AndroidFindBy(id = "android:id/text1")
	private List<WebElement> secureSurfaceItemList;

	public SecureSurfaceScreen(AndroidDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
		PageFactory.initElements(new AppiumFieldDecorator(driver, 15,
				TimeUnit.SECONDS), this);
	}

	public SecureDialogDescriptionScreen getSecureDialogDescriptionScreen() {
		// TODO Auto-generated method stub
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.DEFAULT_WAIT_TIME, TimeUnit.SECONDS);
		for (WebElement el : secureSurfaceItemList) {
			if (el.getText().equals("Secure Dialog")) {
				el.click();
				break;
			}
		}
		WebDriverWait wait = new WebDriverWait(driver,
				AppUtils.EXPLICIT_WAIT_TIME);
		wait.until(ExpectedConditions.visibilityOfElementLocated(MobileBy
				.AccessibilityId("Show secure dialog")));
		driver.manage().timeouts()
				.implicitlyWait(AppUtils.IMPLICIT_WAIT_TIME, TimeUnit.SECONDS);
		return new SecureDialogDescriptionScreen(driver);
	}

}
