package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.katespade.tests.KateSpadeTest;

public class KSEU_741_UserCanReviewItsItemsOnShippingPage extends KateSpadeTest {

	Map<String, String> url;
	Map<String, String> products;
	Map<String, String> products1;
	Map<String, String> products2;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
		products = testData.get("Products").get(0);
		products1 = testData.get("Products").get(1);
		products2 = testData.get("Products").get(3);
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
	public void TestStep04_AddProductToCart() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.verifyKatespadeSpadeLogo();
		dsl.clickOnKatespadeSpadeLogo();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_SearchAProductFromLandingPage() {
		dsl.searchProduct(products1.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_AddProductToCart() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.verifyKatespadeSpadeLogo();
		dsl.clickOnKatespadeSpadeLogo();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_SearchAProductFromLandingPage() {
		dsl.searchProduct(products2.get("Style Number"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_SelectTheFirstProduct() {
		dsl.selectFirstProduct();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep10_AddProductToCart() {
		dsl.verifyUserIsAbleToAddProductToBag();
		dsl.checkoutFromMiniCartWindow();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep11_VerifyThatAfterClickingCheckoutButtonInShoppingPageUserIsNavigatedToShippingPage() {
		dsl.verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep12_VerifyUserCanReviewItsItemsOnShippingPage() {
		dsl.clickOnPlusIconFromMiniCartLinkOnShippingPage();
		dsl.verifyMiniCartAttributes();
		dsl.verifyMyBagIsSticky();
		dsl.verifyOnClickingPlusIconOfMyBagDrawerClosesIfItIsOpen();
	}

}
