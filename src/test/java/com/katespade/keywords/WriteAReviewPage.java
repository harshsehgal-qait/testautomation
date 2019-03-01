package com.katespade.keywords;

import org.openqa.selenium.WebDriver;

import com.qait.automation.getpageobjects.GetPage;

public class WriteAReviewPage extends GetPage {

	public WriteAReviewPage(WebDriver driver) {
		super(driver, "Katespade/WriteReviewPage");
	}

	public void verifyWriteAReviewPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_pageHeader");
	}
}