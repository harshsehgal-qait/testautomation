package com.katespade.tests;

import org.jasypt.util.text.BasicTextEncryptor;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import com.qait.automation.DSL;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

public class KateSpadeTest {

	protected DSL dsl;
	protected String[] layoutTags = { "all" };
	protected String[] browserSizes = { "1366x768" };
	protected Map<String, List<Map<String, String>>> testData;
	static final String SECRET_KEY = "ABCDEFabcdef123456";
	
	@BeforeClass(groups = { "desktop", "mobile" })
	@Parameters({ "browser", "locale" })
	
	public void __setUp(@Optional String browser, @Optional("UK") String locale) {
		dsl = new DSL(this.getClass().getSimpleName(), browser);
		testData = dsl.testData.get(locale).data;
	}
	
	@BeforeMethod(groups = { "desktop", "mobile" })
	public void handleTestMethodName(Method method) {
		dsl.stepStartMessage(method.getName());
	}
	
	@AfterClass(groups = { "desktop", "mobile" })
	public void tearDown() {
		dsl.closeBrowserSession();
		Reporter.log("User closes the application");
	}
	
	@AfterMethod(groups = { "desktop", "mobile" })
	public void takeScreenshotonFailure(ITestResult result) {
		dsl.takeScreenShotOnException(result);
	}

	public static String encrypt(String data) {
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(SECRET_KEY);
		return encryptor.encrypt(data);
	}

	public static String decrypt(String data) {
		try {
			BasicTextEncryptor encryptor = new BasicTextEncryptor();
			encryptor.setPassword(SECRET_KEY);
			return encryptor.decrypt(data);
		} catch (Exception e) {
			return data;
		}
	}

}
