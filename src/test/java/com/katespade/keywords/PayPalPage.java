package com.katespade.keywords;

import org.openqa.selenium.WebDriver;
import com.qait.automation.getpageobjects.GetPage;

public class PayPalPage extends GetPage {

	WebDriver driver;

	public PayPalPage(WebDriver driver) {
		super(driver, "Katespade/PayPalPage");
		this.driver = driver;
	}

	public void logInAndPayWithPayPal(String email, String password) {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoaderToDisappearOnPayPalPage();
		isElementDisplayed("txtbx_email");
		sendText(element("txtbx_email"), email);
		msg.log("Email is entered : " + email);
		isElementDisplayed("btn_next");
		element("btn_next").click();
		msg.log("Next button is clicked");
		wait.waitForLoaderToDisappearOnPayPalPage();
		isElementDisplayed("txtbx_password");
		sendText(element("txtbx_password"), password);
		msg.log("Password is entered : " + password);
		isElementDisplayed("btn_logIn");
		element("btn_logIn").click();
		msg.log("Log In button is clicked");
		wait.waitForLoaderToDisappearOnPayPalPage();
		isElementDisplayed("btn_continue");
		clickUsingJS(element("btn_continue"));
		msg.log("Continue button is clicked");

		// switchToFrame("injectedUl");
		// assertEquals(executeJavascriptReturnsBoolean("return $('#login_emaildiv
		// #email').is(':visible')"), true,"[Assert Fail]: textbox email is not
		// displayed");
		// msg.log("Textbox email is displayed");
		// assertEquals(executeJavascriptReturnsBoolean("return $('#login_passworddiv
		// #password').is(':visible')"), true,"[Assert Fail]: textbox password is not
		// displayed");
		// msg.log("Textbox password is displayed");
		// executeJavascript("$('#login_emaildiv #email').val('"+email+"')");
		// msg.log("Email entered : "+ email);
		// executeJavascript("$('#login_passworddiv #password').val('"+password+"')");
		// msg.log("Password entered : "+ password);
		// assertEquals(executeJavascriptReturnsBoolean("return
		// $('button#btnLogin').is(':visible')"), true,"[Assert Fail]: log in btn is not
		// displayed");
		// msg.log("login btn is displayed");
		// executeJavascript("$('button#btnLogin').click()");
		// msg.log("Login btn is clicked");
		// wait.waitForLoaderToDisappearOnPayPalPage();
		//// assertEquals(executeJavascriptReturnsBoolean("return
		// $('#confirmButtonTop').is(':visible')"), true,"[Assert Fail]: continue btn is
		// not displayed");
		//// msg.log("Continue btn is displayed");
		// switchToDefaultContent();
		// wait.waitForElementToBeClickable(element("btn_continue"));
		// isElementDisplayed("btn_continue");
		// hardWait(2);
		// if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
		// clickUsingJS(element("btn_continue"));
		// }
		// else {
		// Actions ax = new Actions(driver);
		// ax.moveToElement(element("btn_continue")).click().perform();
		// }
		// msg.log("Continue button is clicked");

	}
}
