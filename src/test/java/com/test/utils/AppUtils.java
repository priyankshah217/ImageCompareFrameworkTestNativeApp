package com.test.utils;

import io.appium.java_client.android.AndroidDriver;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.im4java.core.CompareCmd;
import org.im4java.core.ConvertCmd;
import org.im4java.core.IM4JavaException;
import org.im4java.core.IMOperation;
import org.im4java.process.StandardStream;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

public class AppUtils {

	private static Properties prop = new Properties();
	public static int EXPLICIT_WAIT_TIME;
	public static int IMPLICIT_WAIT_TIME;
	public static int DEFAULT_WAIT_TIME;
	public static String APPLICATION_NAME;
	public static String BASE_PKG;
	public static String APP_ACTIVITY;
	public static String APP_PASSWORD;
	private static String APPIUM_PORT;
	public static String DEVICE_NAME;
	public static String ACTUAL_IMAGE_DIR;
	public static String REFERENCE_IMAGE_DIR;
	public static String ACTUAL_IMAGE_PARENT_DIR;
	public static String REFERENCE_IMAGE_PARENT_DIR;
	private static int SCREENSHOT_WIDTH;
	private static int SCREENSHOT_HEIGHT;
	private static int SCREENSHOT_X;
	private static int SCREENSHOT_Y;
	private DesiredCapabilities capabilities;
	private URL serverUrl;
	private static AndroidDriver driver;

	public AppUtils() {
		// TODO Auto-generated constructor stub
		capabilities = new DesiredCapabilities();
	}

	public static void loadConfigProp(String propertyFileName)
			throws IOException {
		prop.load(ClassLoader.getSystemResource(propertyFileName).openStream());

		EXPLICIT_WAIT_TIME = Integer
				.parseInt(prop.getProperty("explicit.wait"));
		IMPLICIT_WAIT_TIME = Integer
				.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT_TIME = Integer.parseInt(prop.getProperty("default.wait"));
		APPLICATION_NAME = prop.getProperty("application.path");
		BASE_PKG = prop.getProperty("base.pkg");
		APP_ACTIVITY = prop.getProperty("application.activity");
		APPIUM_PORT = prop.getProperty("appium.server.port");
		DEVICE_NAME = prop.getProperty("device.name");
		ACTUAL_IMAGE_PARENT_DIR = prop.getProperty("actual.image.dir");
		REFERENCE_IMAGE_PARENT_DIR = prop.getProperty("reference.image.dir");
		ACTUAL_IMAGE_DIR = System.getProperty("user.dir") + "/"
				+ ACTUAL_IMAGE_PARENT_DIR + "/" + DEVICE_NAME;
		REFERENCE_IMAGE_DIR = System.getProperty("user.dir") + "/test-classes/"
				+ REFERENCE_IMAGE_PARENT_DIR + "/" + DEVICE_NAME;
		SCREENSHOT_WIDTH = Integer.parseInt(prop.getProperty(DEVICE_NAME
				+ ".width"));
		SCREENSHOT_HEIGHT = Integer.parseInt(prop.getProperty(DEVICE_NAME
				+ ".height"));
		SCREENSHOT_X = Integer.parseInt(prop.getProperty(DEVICE_NAME + ".x"));
		SCREENSHOT_Y = Integer.parseInt(prop.getProperty(DEVICE_NAME + ".y"));
		createDirectory(ACTUAL_IMAGE_DIR);
	}

	public void setCapability(String capabilityName, String capabilityValue) {
		capabilities.setCapability(capabilityName, capabilityValue);
	}

	public AndroidDriver getDriver() throws MalformedURLException {
		serverUrl = new URL("http://localhost:" + APPIUM_PORT + "/wd/hub");
		driver = new AndroidDriver(serverUrl, capabilities);
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		return driver;
	}

	public static void createDirectory(String directoryName) {
		File files = new File(directoryName);
		if (!files.exists()) {
			if (files.mkdirs()) {
				System.out.println("Multiple directories are created!");
			} else {
				System.out.println("Failed to create multiple directories!");
			}
		}
	}

	public static void takeScreenShot(String fileName) {
		// TODO Auto-generated method stub
		File file = new File(AppUtils.ACTUAL_IMAGE_DIR + "/" + fileName);
		File tmpFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tmpFile, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void cropScreenShot(String inputImage, String outputImage)
			throws IOException, InterruptedException, IM4JavaException {
		IMOperation op = new IMOperation();
		op.addImage(ACTUAL_IMAGE_DIR + "/" + inputImage); // input
		// image
		op.crop(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT, SCREENSHOT_X, SCREENSHOT_Y);
		op.addImage(ACTUAL_IMAGE_DIR + "/" + outputImage); // output
		// image
		ConvertCmd convert = new ConvertCmd();
		convert.run(op);
		File file = new File(AppUtils.ACTUAL_IMAGE_DIR + "/" + inputImage);
		file.delete();
	}

	public static boolean compareMD5Hash(String actualImage,
			String expectedImage) {
		return (DigestUtils.md5Hex(ACTUAL_IMAGE_DIR + "/" + actualImage)
				.equals(DigestUtils.md5Hex(REFERENCE_IMAGE_DIR + "/"
						+ expectedImage)));
	}

	public static boolean compareScreenShot(String actualImage,
			String expectedImage) throws FileNotFoundException {
		CompareCmd compare = new CompareCmd();
		compare.setErrorConsumer(StandardStream.STDERR); // for // metric-output
		IMOperation cmpOp = new IMOperation();
		cmpOp.addImage(ACTUAL_IMAGE_DIR + "/" + actualImage);
		cmpOp.addImage(REFERENCE_IMAGE_DIR + "/" + expectedImage);
		cmpOp.metric("RMSE"); // root mean squared (normalized root mean
								// squared)
		cmpOp.addImage();
		try {
			compare.run(cmpOp);
			return true;
		} catch (IOException | InterruptedException | IM4JavaException e) {
			return false;
		}
	}
}
