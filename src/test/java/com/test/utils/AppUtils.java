package com.test.utils;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

import java.io.File;
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
import org.im4java.process.ArrayListErrorConsumer;
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
	private static String ACTUAL_IMAGE_PARENT_DIR;
	private static String REFERENCE_IMAGE_PARENT_DIR;
	private static DesiredCapabilities capabilities;
	private static URL serverUrl;
	private static AndroidDriver driver;
	private static String MASKED_IMAGE_PARENT_DIR;
	private static String DIFF_IMAGE_PARENT_DIR;
	private static String PENDING_IMAGE_PARENT_DIR;
	public static String MASKED_IMAGE_DIR;
	public static String DIFF_IMAGE_DIR;
	public static String PENDING_IMAGE_DIR;
	private static String BASE_PATH;
	private static String AUTOMATION_INSTRUMENTATION;
	private static String BROWSER_NAME;
	private static String PLATFORM_NAME;
	private static String NEW_COMMAND_TIMEOUT;
	private static String DEVICE_READY_TIMEOUT;
	private static String PLATFORM_VERSION;
	private static String DEVICE_TYPE;
	public static String APP_URL;

	public static void loadConfigProp(String propertyFileName)
			throws IOException {
		capabilities = new DesiredCapabilities();
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
		AUTOMATION_INSTRUMENTATION = prop
				.getProperty("automation.instumentation");
		DEVICE_TYPE = prop.getProperty("device.type");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		NEW_COMMAND_TIMEOUT = prop.getProperty("new.command.timeout");
		DEVICE_READY_TIMEOUT = prop.getProperty("device.ready.timeout");
		DEVICE_NAME = prop.getProperty("device.name");
		APP_URL = prop.getProperty("app.url");
		ACTUAL_IMAGE_PARENT_DIR = prop.getProperty("actual.image.dir");
		REFERENCE_IMAGE_PARENT_DIR = prop.getProperty("reference.image.dir");
		MASKED_IMAGE_PARENT_DIR = prop.getProperty("mask.image.dir");
		DIFF_IMAGE_PARENT_DIR = prop.getProperty("diff.image.dir");
		PENDING_IMAGE_PARENT_DIR = prop.getProperty("pending.image.dir");

		BASE_PATH = new File(ClassLoader.getSystemResource(propertyFileName)
				.getPath()).getParent();

		ACTUAL_IMAGE_DIR = BASE_PATH + "/" + ACTUAL_IMAGE_PARENT_DIR + "/"
				+ DEVICE_NAME;
		REFERENCE_IMAGE_DIR = BASE_PATH + "/" + REFERENCE_IMAGE_PARENT_DIR
				+ "/" + DEVICE_NAME;
		MASKED_IMAGE_DIR = BASE_PATH + "/" + MASKED_IMAGE_PARENT_DIR + "/"
				+ DEVICE_NAME;
		DIFF_IMAGE_DIR = BASE_PATH + "/" + DIFF_IMAGE_PARENT_DIR + "/"
				+ DEVICE_NAME;
		PENDING_IMAGE_DIR = BASE_PATH + "/" + PENDING_IMAGE_PARENT_DIR + "/"
				+ DEVICE_NAME;
		createDirectory(ACTUAL_IMAGE_DIR);
		createDirectory(DIFF_IMAGE_DIR);
	}

	public static void setCapabilities() {
		capabilities.setCapability(MobileCapabilityType.BROWSER_NAME,
				AppUtils.BROWSER_NAME);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				AppUtils.PLATFORM_VERSION);
		capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
				AppUtils.PLATFORM_NAME);
		capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
				AppUtils.DEVICE_TYPE);
		capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				AppUtils.AUTOMATION_INSTRUMENTATION);
		capabilities.setCapability(MobileCapabilityType.APP, new File(
				ClassLoader.getSystemResource(AppUtils.APPLICATION_NAME)
						.getFile()).getAbsolutePath());
		capabilities.setCapability(MobileCapabilityType.APP_ACTIVITY,
				AppUtils.APP_ACTIVITY);
		capabilities.setCapability(MobileCapabilityType.APP_PACKAGE,
				AppUtils.BASE_PKG);
		capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
				AppUtils.NEW_COMMAND_TIMEOUT);
		capabilities.setCapability(MobileCapabilityType.DEVICE_READY_TIMEOUT,
				AppUtils.DEVICE_READY_TIMEOUT);
	}

	public static AndroidDriver getDriver() throws MalformedURLException {		
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
		File file = new File(fileName);
		File tmpFile = ((TakesScreenshot) driver)
				.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(tmpFile, file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// public static void cropScreenShot(String inputImage, String outputImage)
	// throws IOException, InterruptedException, IM4JavaException {
	// IMOperation op = new IMOperation();
	// op.addImage(ACTUAL_IMAGE_DIR + "/" + inputImage); // input
	// // image
	// op.crop(SCREENSHOT_WIDTH, SCREENSHOT_HEIGHT, SCREENSHOT_X, SCREENSHOT_Y);
	// op.addImage(ACTUAL_IMAGE_DIR + "/" + outputImage); // output
	// // image
	// ConvertCmd convert = new ConvertCmd();
	// convert.run(op);
	// File file = new File(AppUtils.ACTUAL_IMAGE_DIR + "/" + inputImage);
	// file.delete();
	// }

	public static boolean compareMD5Hash(String actualImage,
			String expectedImage) {
		return (DigestUtils.md5Hex(ACTUAL_IMAGE_DIR + "/" + actualImage)
				.equals(DigestUtils.md5Hex(REFERENCE_IMAGE_DIR + "/"
						+ expectedImage)));
	}

	public static void maskImage(String actualImage, String maskImage,
			String maskedImage) throws IOException, InterruptedException,
			IM4JavaException {
		IMOperation op = new IMOperation();
		op.addImage(actualImage);
		op.addImage(maskImage);
		op.alpha("on");
		op.compose("DstOut");
		op.composite();
		op.addImage(maskedImage);
		ConvertCmd convert = new ConvertCmd();
		convert.run(op);
	}

	public static boolean compareImages(String actualImage,
			String expectedImage, String diffImage) throws IOException,
			InterruptedException, IM4JavaException {
		CompareCmd compare = new CompareCmd();
		ArrayListErrorConsumer errorConsumer = new ArrayListErrorConsumer();
		compare.setErrorConsumer(errorConsumer); // for metric-output
		IMOperation cmpOp = new IMOperation();
		cmpOp.addImage();
		cmpOp.addImage();
		cmpOp.metric("RMSE"); // root mean squared (normalized root mean
								// squared)
		cmpOp.addImage();		
		compare.run(cmpOp, actualImage, expectedImage, diffImage);
		return errorConsumer.getOutput().contains("0 (0)");
	}
}
