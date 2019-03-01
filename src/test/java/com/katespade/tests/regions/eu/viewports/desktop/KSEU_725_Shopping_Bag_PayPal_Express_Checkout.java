package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_725_Shopping_Bag_PayPal_Express_Checkout extends KateSpadeTest{
	
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> users;
	Map<String, String> payPal;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(1);
		users = testData.get("Users").get(2);
		payPal = testData.get("Pay Pal").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_SearchAProductFromLandingPage() {
		dsl.searchProduct(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_Add_Product_To_Bag_And_Checkout_From_Mini_Cart_Window() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Click_On_CheckOut_With_PayPal_Button() {
		dsl.clickOnCheckOutWithPayPalBtn();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Log_In_And_Pay_With_PayPal_Account() {
		dsl.logInAndPayWithPayPal(payPal.get("Email"), payPal.get("Password"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_user_Is_Navigated_To_Order_Review_Step() {
		dsl.verifyStep3OrderReviewIsActive();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Submit_Order_() {
		dsl.clickSubmitYourOrderButtonInOrderReviewPage();
		dsl.verifyUserIsNavigatedToOrderConfirmationPage();

	}
}


