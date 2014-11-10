package com.test;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.im4java.core.IM4JavaException;
import org.junit.Assert;

import com.test.apidemo.app.screens.ActivityScreen;
import com.test.apidemo.app.screens.AppScreen;
import com.test.apidemo.app.screens.HelloWorldScreen;
import com.test.apidemo.app.screens.HomeScreen;
import com.test.utils.AbstractScreen;
import com.test.utils.AppUtils;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class ApiDemoAppStepDefs {
	private AndroidDriver driver;
	private HomeScreen homeScreen;
	private AppScreen appScreen;
	private ActivityScreen activityScreen;
	private HelloWorldScreen helloworldScreen;
	private Scenario scenario;
	private AbstractScreen screen;
	private String diffImage;

	@Before
	public void Setup(Scenario scenario) throws IOException {
		this.scenario = scenario;
		AppUtils.loadConfigProp("config_apidemo_test_app.properties");
		AppUtils.setCapabilities();
		driver = AppUtils.getDriver();
	}

	@Given("^I open \"(.*?)\" application in my device$")
	public void i_open_application_in_my_device(String arg1) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		homeScreen = new HomeScreen(driver);
	}

	@Then("^I take a screenshot of \"(.*?)\" screen$")
	public void i_take_a_screenshot_of_screen(String screenName)
			throws IOException, InterruptedException, IM4JavaException {
		// Write code here that turns the phrase above into concrete actions
		switch (screenName) {
		case "Home Screen":
			screen = homeScreen;
			break;
		case "App":
			screen = appScreen;
			break;
		case "Activity":
			screen = activityScreen;
			break;
		case "Hello World":
			screen = helloworldScreen;
			break;
		}
		String screenshotName = screen.getScreenName() + ".png";
		String actualImage = AppUtils.ACTUAL_IMAGE_DIR + "/" + screenshotName;
		String maskImage = AppUtils.MASKED_IMAGE_DIR + "/" + screenshotName;
		String referenceImage = AppUtils.REFERENCE_IMAGE_DIR + "/"
				+ screenshotName;
		File maskImageFile = new File(maskImage);
		AppUtils.takeScreenShot(actualImage);
		if (maskImageFile.exists()) {
			String maskedActualImage = AppUtils.DIFF_IMAGE_DIR + "/"
					+ "MaskedActual" + screenshotName;
			String maskedReferenceImage = AppUtils.DIFF_IMAGE_DIR + "/"
					+ "MaskedReference" + screenshotName;
			AppUtils.maskImage(actualImage, maskImage, maskedActualImage);
			AppUtils.maskImage(referenceImage, maskImage, maskedReferenceImage);
			actualImage = maskedActualImage;
			referenceImage = maskedReferenceImage;

		}
		diffImage = AppUtils.DIFF_IMAGE_DIR + "/" + "Diff" + screenshotName;
		Assert.assertSame(true,
				AppUtils.compareImages(actualImage, referenceImage, diffImage));
	}

	@When("^I click on \"(.*?)\"$")
	public void i_click_on(String elementName) throws Throwable {
		// Write code here that turns the phrase above into concrete actions
		switch (elementName) {
		case "App":
			appScreen = homeScreen.getAppScreen();
			break;
		case "Activity":
			activityScreen = appScreen.getActivityPage();
			break;
		case "Hello World":
			helloworldScreen = activityScreen.getHelloWorldScreen();
			break;
		}
	}

	@After
	public void tearDown() throws IOException {
		if (this.scenario.isFailed()) {
			byte[] fileContent = Files.readAllBytes(new File(diffImage)
					.toPath());
			this.scenario.embed(fileContent, "image/png");
			this.scenario.write("Image Comparision Failed");
		}
		driver.quit();
	}
}
