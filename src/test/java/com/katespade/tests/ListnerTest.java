package com.katespade.tests;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class ListnerTest implements ITestListener {
	
	FileWriter fw;
	Set<String> failedTest = new HashSet<>();
	Set<String> skippedTest = new HashSet<>();

	public void onFinish(ITestContext arg0) {
		try {
			for (String test : failedTest)
				fw.write("<class name=\"" + test + "\" />");
			for (String test : skippedTest)
				fw.write("<class name=\"" + test + "\" />");
			fw.write("</classes>    </test></suite>");
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onStart(ITestContext arg0) {
		try {
			fw = new FileWriter("generatedTestNG.xml");
			fw.write(
					"<suite name=\"Suite1\" verbose=\"1\"  preserve-order=\"true\" parallel=\"classes\" thread-count=\"5\"><test name=\"Regression1\">    <classes>");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {

	}

	public void onTestFailure(ITestResult arg0) {
		failedTest.add(arg0.getTestClass().getName());
	}

	public void onTestSkipped(ITestResult arg0) {
		skippedTest.add(arg0.getTestClass().getName());
	}

	public void onTestStart(ITestResult arg0) {

	}

	public void onTestSuccess(ITestResult arg0) {

	}

}