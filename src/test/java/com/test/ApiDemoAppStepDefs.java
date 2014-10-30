package com.test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;

import org.openqa.selenium.remote.CapabilityType;
import org.testng.Assert;

import com.test.apidemo.app.screens.AppActivityScreen;
import com.test.apidemo.app.screens.AppMenuScreen;
import com.test.apidemo.app.screens.HomeScreen;
import com.test.apidemo.app.screens.SecureDialogDescriptionScreen;
import com.test.apidemo.app.screens.SecureSurfaceScreen;
import com.test.utils.AppUtils;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApiDemoAppStepDefs {
	private AppUtils testAppUtils;
	private AndroidDriver driver;
	private HomeScreen homeScreen;
	private AppMenuScreen appMenuScreen;
	private AppActivityScreen appActivityScreen;
	private SecureSurfaceScreen secureSurfaceScreen;
	private SecureDialogDescriptionScreen secureDialogDescriptionScreen;

	@Given("^I Open API demo Application in my device$")
	public void i_Open_API_demo_Application_in_my_device() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		AppUtils.loadConfigProp("config_apidemo_test_app.properties");
		testAppUtils = new AppUtils();
		testAppUtils.setCapability(CapabilityType.BROWSER_NAME, "");
		testAppUtils.setCapability("platformVersion", "4.4.2");
		testAppUtils.setCapability("appium-version", "1.2.2");
		testAppUtils.setCapability("platformName", "Android");
		testAppUtils.setCapability("deviceName", "Android");
		testAppUtils.setCapability("automationName", "Appium");
		testAppUtils
				.setCapability(
						"app",
						new File(ClassLoader.getSystemResource(
								AppUtils.APPLICATION_NAME).getFile())
								.getAbsolutePath());
		testAppUtils.setCapability("newCommandTimeout", "3600");
		testAppUtils.setCapability("deviceReadyTimeout", "3600");
		testAppUtils.setCapability("appActivity", AppUtils.APP_ACTIVITY);
		testAppUtils.setCapability("appPackage", AppUtils.BASE_PKG);
		driver = testAppUtils.getDriver();
		homeScreen = new HomeScreen(driver);
	}

	@When("^I click on 'App'$")
	public void i_click_on_App() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		appMenuScreen = homeScreen.getAppMenuPage();
		appMenuScreen.grabScreenShot("ApplicationScreen.png");
	}

	@Then("^'Activity' text visible on 'Application' screen$")
	public void activity_text_visible_on_Application_screen() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Assert.assertEquals(appMenuScreen.compareScreenUsingMD5(
		// "ApplicationScreen.png", "ApplicationScreen.png"), true);
		Assert.assertEquals(appMenuScreen.validateScreen(
				"ApplicationScreen.png", "ApplicationScreen.png"), true);
	}

	@When("^I click on 'Activity'$")
	public void i_click_on_Activity() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		appActivityScreen = appMenuScreen.getActivityPage();
		appActivityScreen.grabScreenShot("AppActivityScreen.png");
	}

	@Then("^'Secure Surface' text visible on 'Activity' screen$")
	public void secure_Surface_text_visible_on_Activity_screen()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Assert.assertEquals(appActivityScreen.compareScreenUsingMD5(
		// "AppActivityScreen.png", "AppActivityScreen.png"), true);
		Assert.assertEquals(appActivityScreen.validateScreen(
				"AppActivityScreen.png", "AppActivityScreen.png"), true);
	}

	@When("^I click on 'Secure Surface'$")
	public void i_click_on_Secure_Surface() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		secureSurfaceScreen = appActivityScreen.browseAppActivityScreen()
				.getSecureSurfaceScreen();
		secureSurfaceScreen.grabScreenShot("SecureSurfaceScreen.png");
	}

	@Then("^'Secure Dialog' text visble on 'Secure Surface' screen$")
	public void secure_Dialog_text_visble_on_Secure_Surface_screen()
			throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Assert.assertEquals(secureSurfaceScreen.compareScreenUsingMD5(
		// "SecureSurfaceScreen.png", "SecureSurfaceScreen.png"), true);
		Assert.assertEquals(secureSurfaceScreen.validateScreen(
				"SecureSurfaceScreen.png", "SecureSurfaceScreen.png"), true);
	}

	@When("^I click on 'Secure Dialog'$")
	public void i_click_on_Secure_Dialog() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		secureDialogDescriptionScreen = secureSurfaceScreen
				.getSecureDialogDescriptionScreen();
		secureDialogDescriptionScreen
				.grabScreenShot("SecureDialogDescriptionScreen.png");
	}

	@Then("^'Show Secure Dialog' Button is displayed$")
	public void show_Secure_Dialog_Button_is_displayed() throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		// Assert.assertEquals(secureDialogDescriptionScreen
		// .compareScreenUsingMD5("SecureDialogDescriptionScreen.png",
		// "SecureDialogDescriptionScreen.png"), true);
		Assert.assertEquals(
				secureDialogDescriptionScreen.hasShowSecureDialogButton(),
				true, "'Show secure dialog' button is not present.");
		Assert.assertEquals(secureDialogDescriptionScreen.validateScreen(
				"SecureDialogDescriptionScreen.png",
				"SecureDialogDescriptionScreen.png"), true);
		driver.quit();
	}

	// @When("^I click on 'OS'$")
	// public void i_click_on_OS() throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	//
	// }
	//
	// @Then("^'Rotation Vector' text visible on 'OS' screen$")
	// public void rotation_Vector_text_visible_on_OS_screen() throws Throwable
	// {
	// // Write code here that turns the phrase above into concrete actions
	//
	// }
	//
	// @When("^I click on 'Rotation Vector'$")
	// public void i_click_on_Rotation_Vector() throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	//
	// }
	//
	// @Then("^image visible should be visible 'Rotation Vector' screen$")
	// public void image_visible_should_be_visible_Rotation_Vector_screen()
	// throws Throwable {
	// // Write code here that turns the phrase above into concrete actions
	//
	// }
}
