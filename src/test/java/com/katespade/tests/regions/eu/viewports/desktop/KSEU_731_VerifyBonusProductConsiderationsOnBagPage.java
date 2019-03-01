/*Test Case Working for FR site 
 * Shopping Cart - Bonus Product: Considerations*/

package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_731_VerifyBonusProductConsiderationsOnBagPage extends KateSpadeTest {
	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> bonusProductSubTotal;
	Map<String, String> bonusProductPrice;
	String Promotionalmsg = "Bonus bonanza!!";
	String BonusProductQnt = "1";

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(4);
		bonusProductSubTotal = testData.get("Page Names").get(9);
		bonusProductPrice = testData.get("Product Price").get(0);
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
	public void TestStep04_Verify_That_Promotional_Message_Is_Displayed_For_The_Product() {
		dsl.verifyPromotionalMessageForAProduct(Promotionalmsg);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_Verify_User_Is_Able_To_Add_Product_To_Bag_and_Goes_To_Bag_Page() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_Verify_Bonus_Product_Msg_And_Select_Bonus_Product_Link() {
		dsl.verifyBonusProductMsgAndSelectBonusProductLink(Promotionalmsg);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_Verify_Select_Bonus_Product_Modal() {
		dsl.verifySelectBonusProductModal();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_Verify_Cart_Page_On_Adding_Bonus_Product_To_Cart() {
		dsl.verifyCartPageOnAddingBonusProductToCart(products.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_Verify_Bonus_Product_Quantity_And_Total_Cost() {
		dsl.verifyBonusProductQuantityAndTotalCost(bonusProductPrice.get("Price"), BonusProductQnt,
				bonusProductSubTotal.get("Text Verification"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_Verify_Bonus_Product_Details_On_Bag_Page() {
		dsl.verifyBonusProductDetailsOnBagPage();

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_Verify_Bonus_Product_Details_On_Mini_Cart() {
		dsl.verifyBonusProductDetailsOnMiniCartPage(bonusProductSubTotal.get("Text Verification"));

	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_Verify_Bonus_Product_Name_Link_Navigates_User_To_PDP_Page() {
		dsl.verifyBonusProductNameLinkNavigatesUserToPDPPage();

	}

}
