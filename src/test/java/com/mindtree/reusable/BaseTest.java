package com.mindtree.reusable;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import com.aventstack.extentreports.ExtentTest;
import com.mindtree.manager.WebDriverManager;

import utility.Logs;

public class BaseTest extends WebDriverManager {
	 WebDriverManager driverManager;
	WebDriverHelper Helper;
	static Logs loggerUtil;
	static Logger log;
	public static ExtentTest test;

	public void startUp() {
		loggerUtil = new Logs();
		log = loggerUtil.createLog("Hooks.class");
		log.debug("Opening driver");
		driverManager = new WebDriverManager();
		driver= driverManager.getDriver();
		log.info("Driver opened");
		driver.get(properties.getProperty("url"));
		log.debug("Opening URL");
	}

	public  void tearDown() {
		log.info("Driver closed");
		driver.quit();
	}
	public static String getscreenshotpath(String TestCaseName) throws IOException 
	{
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		String date = sdf.format(d);
		String timeStamp = date.replace(" ", "_").replaceAll(":", "_").replaceAll("/", "_");
		//System.setProperty("timeStamp", timeStamp);
		String target = (System.getProperty("user.dir") + "/Screenshot/"+TestCaseName+ timeStamp + ".png");
		File file=new File(target);
		FileUtils.copyFile(source, file);
		return target;
	}

}
