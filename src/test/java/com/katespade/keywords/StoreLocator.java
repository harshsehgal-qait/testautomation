package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class StoreLocator extends GetPage {

	WebDriver driver;

	public StoreLocator(WebDriver driver) {
		super(driver, "Katespade/Stores");
		this.driver = driver;
	}

	public void verifyDetailsOfStoreLocatorPage(String Text_placeholder, String TextInPlaceholderFr) {
		wait.waitForLoad();
		isElementDisplayed("storeLocatorHeader");
		isElementDisplayed("textbox_address");
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			assertEquals(element("textbox_address").getAttribute("placeholder"), TextInPlaceholderFr,
					"[ASSERTION FAILED]: Placeholder in the Address textbox is not as expected");
		} else {
			assertEquals(element("textbox_address").getAttribute("placeholder"), Text_placeholder,
					"[ASSERTION FAILED]: Placeholder in the Address textbox is not as expected");
		}
		msg.pass("Placeholder in the address textbox is as expected: "
				+ element("textbox_address").getAttribute("placeholder"));
		isElementDisplayed("drpdwn_distance");
		isElementDisplayed("button_findStores");
		isElementDisplayed("label_breadcrumbs");
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			msg.log("Maps not avalable for mobile users");
		} else {
			isElementDisplayed("map_storeLocator");
		}
		msg.pass("Verified Store locator page details");
	}

	public void searchStore(String searchType) {
		// Avoid "Appium error: An unknown server-side error occurred while processing
		// the command" error
		wait.hardWait(5);

		isElementDisplayed("textbox_address");
		sendText(element("textbox_address"), searchType);
		msg.log("Entered " + searchType);

		isElementDisplayed("list_distance");
		List<WebElement> distance = elements("list_distance");
		for (WebElement el : distance) {
			msg.log("Distance Available: '" + el.getText() + "'");
		}

		isElementDisplayed("drpdwn_distance");
		executeJavascript("$('select#distance')[0].selectedIndex = '3'");
		logMessage("Selected '300 Miles' option");

		isElementDisplayed("button_findStores");
		clickUsingJS(element("button_findStores"));
		msg.log("Clicked on 'FIND STORES' button");
	}

	public void verifyDetailsOfStoresSearchedPage() {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoad();
		
		hardWait(5);
		
		isElementDisplayed("icon_retail");
		isElementDisplayed("icon_outlet");
		isElementDisplayed("icon_stockist");

		List<WebElement> storeNames = elements("label_storeName");
		for (WebElement el : storeNames) {
			msg.log(el.getText() + " is displayed !!!");
		}

		isElementDisplayed("label_frstDistance");
		String distance = element("label_frstDistance").getText();
		msg.log("Distance for the first store is: " + distance);

		// KSEU-1044: Store Locator> Search result> Store List : 'mi' is displayed instead of 'Miles'
		Assert.assertTrue(element("label_frstDistance").getText().contains("mi away"),
		 				"[ASSERTION FAILED]: User is NOT navigated Store detail Page !!!");
		msg.pass("User is navigated Store detail Page !!!");
	}

	public void verifyTheAddressDetailsOfStoreOnStoreDetailPage() {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}

		isElementDisplayed("label_addressInfo");
		PropFileHandler.writeProperty("addressInfo", element("label_addressInfo").getText());
		isElementDisplayed("link_storeDetails");
		isElementDisplayed("link_getDirections");
		isElementDisplayed("label_distance");
		isElementDisplayed("pincode_city");
		isElementDisplayed("phone");

		executeJavascript(
				"document.getElementsByClassName('storedetailslink ')[0].getElementsByTagName('a')[0].click()");
		msg.log("User clicked on Store Details link");

		isElementDisplayed("button_getDirections");
		isElementDisplayed("label_addressInfo");

		assertEquals(element("label_addressInfo").getText(), PropFileHandler.readProperty("addressInfo"),
				"Store Address does not matched");
		msg.log("Store Address is matched");
	}

	public void verifyDetailsOnSearchPageOnMapAndDetailsWhenStoreDetailsIsClicked(String pageName) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		
		scrollDown(element("icon_pin_StoreNo"));
		isElementDisplayed("icon_pin_StoreNo");
		
		executeJavascript("$('.storenumber a')[0].click()");
		msg.log("Clicked on the icon of the first Store number");

		scrollUpToPage();
		
		isElementDisplayed("text_storeNoInMap");
		isElementDisplayed("lnk_getDirections");
		isElementDisplayed("lnk_storeDetailsMap");
		
		executeJavascript("$('.mapContent .storedetailslink a')[0].click()");
		msg.log("Clicked on 'STORE DETAILS' link under Search results");
		
		msg.log("Actual Current URL: " + getCurrentURL());
		msg.log("Expected Page: '" + pageName + "'");
		Assert.assertTrue(getCurrentURL().contains(pageName), 
				"[ASSERTION FAILED]: User is NOT navigated Store detail Page !!!");
		
		isElementDisplayed("button_getDirections");
		msg.pass("User is navigated to the Store Details page !!!");
	}

	public void fr_verifyDetailsOnSearchPageOnMapAndDetailsWhenStoreDetailsIsClicked(String pageName) {
		wait.waitForPageToLoadCompletely();
		scrollDown(element("icon_pin_StoreNo"));

		isElementDisplayed("icon_pin_StoreNo");
		executeJavascript("$('.storenumber a')[0].click()");
		msg.log("Clicked on the icon of the first store number");

		scrollUpToPage();
		isElementDisplayed("text_storeNoInMapFr");
		isElementDisplayed("lnk_getDirections");
		isElementDisplayed("lnk_storeDetailsMap");
		executeJavascript("$('.mapContent .storedetailslink a')[0].click()");

		msg.log("ACTUAL URL: " + getCurrentURL());
		msg.log("EXPECTED Page: " + pageName);
		Assert.assertTrue(getCurrentURL().contains(pageName),
				"[ASSERTION FAILED]: User is not navigated Store detail Page");
		msg.pass("User is navigated to Store detail Page");

		isElementDisplayed("button_getDirections");
		msg.pass("User is navigated to the store detail page");
	}

	public void verifyDetailsWhenGetDirectionsIsClickedOnStoreDetailsPage() {
		hardWait(1);
		isElementDisplayed("lnk_getDirections");
		try {
			element("lnk_getDirections").click();
		} catch (Exception Ex) {
			clickByJavascript(element("lnk_getDirections"));
		}
		/*
		 * if(!isElementNotDisplayed("lnk_getDirections")) {
		 * clickByJavascript(element("lnk_getDirections")); }
		 */
		changeWindow(1);
		assertTrue(getCurrentURL().contains("www.google.com/maps/"), "User is not navigated to the Google Maps page");
		msg.log("User is navigated to the Google Maps page");
		changeWindow(0);
		scrollUpToPage();
		refreshThePage();

	}

	public void verifyAddressDetailsDisplayedOnMapOnStoreDetailPage() {
		isElementDisplayed("text_map_address1");
		msg.log("Address is : " + element("text_map_address1").getText());

		isElementDisplayed("city_pin_on_map");
		msg.log("City and Pin is : " + element("city_pin_on_map").getText());

		isElementDisplayed("store_phone");
		msg.log("Store Phone number is : " + element("store_phone").getText());
	}

	public void verifyFindStoreNearYouHeaderIsH1() {
		wait.waitForLoad();
		isElementDisplayed("find_store_near_you");
		String style = element("find_store_near_you").getTagName();
		msg.log(element("find_store_near_you").getText());
		assertEquals(style.contains("h1"), true, element("find_store_near_you").getText() + " is not with H1 label");
		msg.log("Verified find store header with label H1");

	}

	public void verifyNumberOfSearchResultsHeaderIsH2() {
		isElementDisplayed("label_searchedStoresHeader");
		String style = element("label_searchedStoresHeader").getTagName();
		msg.log("Style: " + style);
		assertEquals(style.contains("h2"), true, "h2  tag name not found");
		msg.log(element("label_searchedStoresHeader").getText() + " is with H2 label");

	}

	public void verifyUserIsAbleToSearchStoreUsingHalfUKZipCodeAndVerifyDetailsOfThePage() {
		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			wait.waitForPageToLoadCompletely();
			isElementDisplayed("icon_retail");
			isElementDisplayed("icon_outlet");
			isElementDisplayed("icon_stockist");

			isElementDisplayed("label_searchedStoresHeader");
			String textCotent = element("label_searchedStoresHeader").getText();
			msg.log(textCotent);

			List<WebElement> storeNames = elements("label_storeName");
			for (WebElement el : storeNames) {
				msg.log(el.getText() + " is displayed");
			}

			isElementDisplayed("label_frstDistance");
			msg.log("Distance for the first store is : " + element("label_frstDistance").getText());

			msg.log("Stores Searched page details verified");
		}

	}

	public void verifyRadiusDropDownsIncluded() {
		List<WebElement> distance = elements("list_distance");
		for (WebElement el : distance) {
			msg.log("Distance Available : " + el.getText());
			String radiusDistance = el.getText();
			msg.log(radiusDistance);
			assertEquals(radiusDistance.contains("25"), true, "ASSERT FAILED: " + radiusDistance + " is not present ");
			msg.log("Verified:" + radiusDistance + " is displayed");
		}
	}

	public void verifyDetailsWhenNoStoresAreFound(String noStoresHeader) {
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			wait.waitForLoad();
			hardWait(5);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		isElementDisplayed("lbl_noStore");
		msg.log("No Stores are displayed");

		Assert.assertEquals((String) executeJavascriptReturnsString("return $('#stores h2')[0].textContent"),
				noStoresHeader, "[ASSERTION FAILED]: Header for NO STORES is NOT MATCHING !!!");
		msg.pass("Header for NO STORES is MATCHING !!!");

		isElementNotDisplayed("icon_retail");
		isElementNotDisplayed("icon_outlet");
		isElementNotDisplayed("icon_stockist");
	}

	public void verifyRefineYourSearchResultsIsDisplayed(String refineLabelText) {
		isElementDisplayed("refine_label");

		msg.log("ACTUAL Label Text: " + element("refine_label").getText().toLowerCase());
		msg.log("EXPECTED Label Text: " + refineLabelText.toLowerCase());

		Assert.assertEquals(element("refine_label").getText().toLowerCase(), refineLabelText.toLowerCase(),
				"[ASSERTION FAILED]: " + refineLabelText + " is not present on Stores");
		msg.pass(refineLabelText + " is present on Stores");
	}

	public void verifyStoreTypesAvailable(String retail, String outlet, String stockist) {
		// Retails
		isElementDisplayed("icon_retail");
		msg.log(element("icon_retail").getText());
		assertEquals(element("icon_retail").getText().contains(retail), true, retail + " is not present on Stores");
		msg.log(retail + " is present on Stores");

		// Outlet
		isElementDisplayed("icon_outlet");
		msg.log(element("icon_outlet").getText());
		assertEquals(element("icon_outlet").getText().contains(outlet), true, outlet + " is not present on Stores");
		msg.log(outlet + " is present on Stores");

		// Stockist
		isElementDisplayed("icon_stockist");
		msg.log(element("icon_stockist").getText());
		assertEquals(element("icon_stockist").getText().contains(stockist), true,
				stockist + " is not present on Stores");
		msg.log(outlet + " is present on Stores");
	}

	public void verifyCustomersCanFurtherRefineStoreSearchResultsList() {
		// Retail
		executeJavascript("$('#retail').click()");
		hardWait(1);
		msg.log("retail chekbox is clicked");
		boolean checkbox = executeJavascriptReturnsBoolean("return $('input#retail')[0].checked");
		assertEquals(checkbox, true, "checkbox marked");
		hardWait(2);
		int sizeOfretail = elements("retailStoreNo").size();
		for (int i = 0; i < sizeOfretail; i++) {
			elements("retailStoreNo").get(i).isDisplayed();
		}
		msg.log(sizeOfretail + " retail shops are displayed");
		executeJavascript("$('#retail').click()");
		// hardWait(1);
		// assertEquals(checkbox, false,"retail checkbox unchecked");

		// Outlet
		executeJavascript("$('#outlet').click()");
		hardWait(1);
		// boolean outlet_checkbox=executeJavascriptReturnsBoolean("return
		// $('input#outlet')[0].checked");
		assertEquals(checkbox, true, "checkbox marked");
		int sizeOfoutlet = elements("outlet").size();
		for (int i = 0; i < sizeOfoutlet; i++) {
			elements("outlet").get(i).isDisplayed();
		}
		msg.log(sizeOfoutlet + " outlet shops are displayed");
		executeJavascript("$('#outlet').click()");
		// hardWait(1);
		// assertEquals(checkbox, false,"outlet checkbox unchecked");

		// Stockist
		executeJavascript("$('#stockist').click()");
		hardWait(1);
		boolean stockist_checkbox = executeJavascriptReturnsBoolean("return $('input#stockist')[0].checked");
		assertEquals(stockist_checkbox, true, "checkbox marked");
		msg.log("stockist checkbox is clicked");
		executeJavascript("$('#stockist').click()");
		// assertEquals(stockist_checkbox, false,"outlet checkbox unchecked");
	}

	public void verifyTheDirectionDetailsLinkOfFirstStoreSearched() {
		wait.waitForLoad();
		executeJavascript("$('.directions a')[0].click()");
		msg.log("User clicked on Store Directions link");
		changeWindow(1);
		assertTrue(getCurrentURL().contains("www.google.com/maps/"), "User is not navigated to the Google Maps page");
		msg.log("User is navigated to the Google Maps page");
		changeWindow(0);
	}

	public void verifyTheStoreDetailsLinkOfFirstStoreSearched() {
		isElementDisplayed("label_addressInfo");
		PropFileHandler.writeProperty("addressInfo", element("label_addressInfo").getText());
		executeJavascript("$('.stores-container .storedetailslink a')[0].click()");
		msg.log("User clicked on Store Details link");

		isElementDisplayed("label_addressInfo");
		assertEquals(element("label_addressInfo").getText(), PropFileHandler.readProperty("addressInfo"),
				"Store Address does not matched");
		msg.log("Store Address is matched");

		isElementDisplayed("store_detail_page_header");
		msg.log("Store name dislayed in header is : " + element("store_detail_page_header").getText());

		isElementDisplayed("store_detail_zip");
		msg.log("City/State zip is displayed");

		isElementDisplayed("store_detail_phone");
		msg.log("City Phone Number is displayed");

		isElementDisplayed("button_getDirections");
		msg.log("User clicked on Get Directions Button");

		changeWindow(1);
		assertTrue(getCurrentURL().contains("www.google.com/maps/"), "User is not navigated to the Google Maps page");
		msg.log("User is navigated to the Google Maps page");
		changeWindow(0);
	}

	public void verifyOtherDetailsOnStoreDetailPage() {
		isElementDisplayed("store_img");

		isElementDisplayed("label_breadcrumbs");
		msg.log("Breadcrum displayed is : " + element("label_breadcrumbs").getText());

		isElementDisplayed("store_hrs");
		msg.log("Store Hours details is displayed");

		if (!(ConfigPropertyReader.getProperty("browser").contains("ios"))) {
			isElementDisplayed("map_storeLocator");
			msg.log("Map is displayed");
		}
	}

	public void verifyThreeStoresResultsWillBeDisplayedPerRow() {
		// int sizeOfComponents = elements("store_info").size();

	}

	public void verifyStoreNameOnStoreDetailPageIsHyperlink() {
		isElementDisplayed("label_storeName");
		// Store name on search Page is a hyper link
		String hyperlink = element("label_storeName").getTagName();
		msg.log("TagName is : " + element("label_storeName").getText());
		assertEquals(hyperlink.contains("a"), true, element("label_storeName").getText() + " is not a hyperlink");
		msg.log("Verified Store Name on store detail pageis a hyperlink");
	}

	public void verifyUserIsNavigatedToStoreDetailPageOnClickingStoreName(String pageName) {
		// Navigate to store detail page
		clickUsingJS(element("label_storeName"));
		wait.waitForLoad();
		msg.log(element("store_detail_page_header").getText());
		assertEquals(getCurrentURL().contains(pageName), true, "User is not navigated Store detail Page");
		msg.log("User is navigated to Store detail Page");
	}

	public void userGoesBackToSearchPage() {
		backButton();
		wait.waitForLoad();
		isElementDisplayed("button_findStores");
		element("button_findStores").click();
		msg.log("User clicked on Find a store button");
	}

}