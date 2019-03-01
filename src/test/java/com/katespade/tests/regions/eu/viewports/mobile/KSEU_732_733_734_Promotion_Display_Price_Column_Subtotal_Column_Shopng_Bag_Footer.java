//Covered 723 step 3,KSEU-726,732,751 - Product line and  promotion and Order line prmotion
package com.katespade.tests.regions.eu.viewports.mobile;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_732_733_734_Promotion_Display_Price_Column_Subtotal_Column_Shopng_Bag_Footer extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> promoCode;
	Map<String, String> promoCode1;
	Map<String, String> promoCode2;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(7);
		promoCode = testData.get("Promo Codes").get(0);
		promoCode1 = testData.get("Promo Codes").get(1);
		promoCode2 = testData.get("Promo Codes").get(3);
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
	//Product line promotion:   Coupon name and discount applied and order total.
	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_User_Is_Able_To_Apply_Promo_Code_In_Shoping_Cart_Page() {
		dsl.verifyUserIsAbleToApplyPromoCodeInShopingCartPage(promoCode.get("Promo Code"));
		dsl.verifyDiscountIsNotAppliedInOrderSummary();
	}
	//Order line promotion:  Coupon name and discount applied and discount should display in order summary
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Information_On_The_Order_Summary_Box_After_Applying_Valid_Promo_Code() throws InterruptedException {
		dsl.applyPromoCodeInShopingCartPageToVerifyInformation(promoCode2.get("Promo Code"));
		dsl.verifyInformationOnOrderSummaryBoxAfterApplyingValidPromoCode();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_User_Is_Able_To_Apply_Promo_Code_And_verify_Details_In_Footer_Of_Shoping_Cart_Page()
			throws InterruptedException {
		dsl.verifyUserIsAbleToApplyPromoCodeAndVerifyDetailsInFooterOfShopingCartPage(promoCode.get("Promo Code"));
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Error_Msg_For_valid_Coupon_Code_But_Invalid_For_Product()
			throws InterruptedException {
		dsl.applyPromoCodeInShopingCartPageToVerifyInformation(promoCode1.get("Promo Code"));
		dsl.verifyErrorMsgForInvalidCouponCode();
	}
	
	

}
