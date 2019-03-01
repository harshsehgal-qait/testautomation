package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class ShopGridPage extends GetPage {

	public ShopGridPage(WebDriver driver) {
		super(driver, "Katespade/ShopGridPage");
	}

	public void verifyQuickBuyPage() {
		clickOnQuickBuyIcon();
		wait.waitForElementToBeVisible(element("quickViewWindow"));
		// isElementDisplayed("icon_zoom"); // Removed as per KSEU-981
		isElementDisplayed("lbl_productName");
		msg.log(element("lbl_productName").getText() + " is displayed");
		isElementDisplayed("quickViewProductPrice");
		msg.log(element("quickViewProductPrice").getText() + " is displayed");
		
		isElementDisplayed("icon_ratings");
		isElementDisplayed("btn_addToCart");
		isElementDisplayed("link_seeDetails");
		isElementDisplayed("img_quickBuyAlt", "1");
	}

	public void verifyMoreThenThreeSwatches() {
		wait.waitForLoad();
		long length = executeJavascriptReturnsLong("return $('.swatch-list .product-swatches-all').size()");
		for (int i = 0; i < length; i++) {
			elements("icon_swatches").get(i).isDisplayed();
		}
		scrollDown(element("icon_MoreSwatches"));
		isElementDisplayed("icon_MoreSwatches");
		executeJavascript("$('.product-swatches-all>a')[0].click()");
		String swatch = executeJavascriptReturnsString(
				"return $('li.grid-tile .product-swatches>ul')[1].getAttribute('class')");
		assertEquals(swatch.contains("show-all"), true, "Swatches box not visible");

	}

	public void clickOnQuickByAddToBag() {
		wait.waitForPageToLoadCompletely();
		element("btn_addToCart").click();
		msg.log("User clicks on Add to bag button");
	}

	public void verifyQuickViewIconOnProductTiles() {
		wait.waitForLoad();
		isElementDisplayed("list_prodctImg");
		String currentURL = getCurrentURL();
		hover(elements("list_prodctImg").get(0));
		msg.log("Hovered");
		wait.waitForElementToBeVisible(element("icon_quickView"));
		isElementDisplayed("icon_quickView");
		assertTrue(elements("lbl_productPrice").get(0).isDisplayed(), "product Price not visible");
		msg.log("Product Price is visible");
		if (currentURL.contains("ksEuUk") || currentURL.contains("Sites-ksEuRoe-Site")) {
			assertEquals(elements("lbl_productPrice").get(0).getText().contains("£"), true, "pound sign not visible");
			msg.log(elements("lbl_productPrice").get(0).getText());
		} else if (currentURL.contains("ksEuFr")) {
			assertEquals(elements("lbl_productPrice").get(0).getText().contains("€"), true, "Euro sign not visible");
			msg.log(elements("lbl_productPrice").get(0).getText());
		}

	}

	public void verifyBreadCrumOnShopGrid() {
		wait.waitForLoad();
		isElementDisplayed("breadCrum");
	}

	public void clickOnQuickBuyIcon() {
		wait.waitForPageToLoadCompletely();
		hover(elements("tile_productTile").get(1));
		hoverClick(element("icon_quickView"));
		msg.log("Clicked on Quick buy icon");
	}

	public void selectFirstProduct() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(2);
			if (getCurrentURL().contains("Possible Phishing Website"))
				;
			executeJavascript("PhishingAlertController.ignoreWarningSelected();");
			msg.log("User ignores the phishing alert");
		}
		wait.waitForPageToLoadCompletely();
		hardWait(1);
		executeJavascript("$('.product-image>a')[0].click();");
		msg.log("User selected first product");
	}

	public void verifyUserIsOnHandBagPage() {
		wait.waitForLoad();
		scrollDown(element("header_ResultsFor"));
		isElementDisplayed("header_ResultsFor");
		msg.log(element("header_ResultsFor").getText() + " is displayed");
		hardWait(2);
		assertEquals(element("header_ResultsFor").getText().contains("handbags"), true, "User is not on handbag page");
		msg.log("User is on handbag page");
	}

	public void verifyQuickShopFunctioanlity() {
		isElementDisplayed("plp_first_product");
		hover(element("plp_first_product"));
		msg.log("User hovers on first product of the page");
		hardWait(2);
		executeJavascript("$('#quickviewbutton')[0].click()");
		msg.log("User clicks on Quick View");
	}

	public void verifyUserIsOnSatchelsPage() {
		wait.waitForLoad();
		scrollDown(element("header_ResultsFor"));
		isElementDisplayed("header_ResultsFor");
		msg.log(element("header_ResultsFor").getText() + " is displayed");
		hardWait(2);
		assertEquals(element("header_ResultsFor").getText().contains("satchels"), true, "User is not on satchels page");
		msg.log("User is on satchels page");
	}

	public void navigateToLeftNavigation() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			executeJavascript("$('.toggle.expanded')[0].click()");
			hardWait(3);
		}
		List<WebElement> left_navigation = elements("plp_leftNavigation");
		for (WebElement allValues : left_navigation) {
			msg.log(allValues.getText() + " is displayed");
		}

	}

	public void verifyShopHeader() {
		isElementDisplayed("lbl_shopHeader");
		msg.log(element("lbl_shopHeader").getText());
		msg.log("verify shop header on shopgrid page");
	}

	public void navigateToLeftNavigationOnMobile() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			executeJavascript("$('.toggle.expanded')[0].click()");
			hardWait(3);
		}
		/*
		 * List<WebElement> left_navigation = elements("plp_leftNavigation"); for
		 * (WebElement allValues : left_navigation) { msg.log(allValues.getText() +
		 * " is displayed"); }
		 */
	}

	public void verifySearchResultPage(String name) {
		wait.waitForLoad();

		msg.log("Actual Current URL (LowerCase): " + getCurrentURL().toLowerCase());
		msg.log("Expected Search Results (LowerCase): " + name.toLowerCase() + "\n");

		Assert.assertTrue(getCurrentURL().toLowerCase().contains(name.toLowerCase()),
				"[ASSERTION FAILED]: Searched result URL does not contains searched keyword");
		msg.pass("Searched result url contains product !!!");

		// Avoid "Appium error: An unknown server-side error occurred while processing
		// the command" error
		wait.hardWait(5);

		scrollDown(element("header_ResultsFor"));

		msg.log("Actual Results Header (LowerCase): " + element("header_ResultsFor").getText().toLowerCase());
		msg.log("Expected Search Results (LowerCase): " + name.toLowerCase() + "\n");

		Assert.assertTrue(element("header_ResultsFor").getText().toLowerCase().contains(name.toLowerCase()),
				"[ASSERTION FAILED]: Searched product does not lies under product category");
		msg.pass("Searched product lies under product category");
	}

	public void verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePage() {
		executeJavascript("$('.search-menu .search-toggle a').click()");
		msg.log("Clicked on Search bar");
		
		isElementNotDisplayed("searchfor");
		isElementDisplayed("textbox_Search");

		element("textbox_Search").sendKeys("Bags");
		msg.log("User enters the value to see search predictions");

		// To avoid StaleElementReference Exception
		hardWait(2);

		executeJavascript("document.getElementsByClassName('t046-close')[0].click()");
		msg.log("Clicked on 'Close' icon of Feedback dialog box");
		
		elements("category_search").get(0).click();
		msg.log("Clicked on first category");

		wait.waitForLoad();
		executeJavascript("$('.search-menu .search-toggle a').click()");
		msg.log("Clicked on Search bar");

		isElementDisplayed("text_search");
		isElementNotDisplayed("searchfor");
		
		executeJavascript("$('.search-menu .search-toggle a').click()");
		msg.log("Clicked on Search toggle");
		
		sendText(element("textbox_Search"), "Bags");
		msg.log("User enters the value to see search predictions");

		// To avoid StaleElementReference Exception
		hardWait(2);
		
		msg.log("Actual Current URL (REPLACEMENT): " + 
				getCurrentURL().toLowerCase().replaceAll("[^a-zA-Z]+", " ").trim());
		msg.log("Expected Refinement Category header (REPLACEMENT): "
				+ elements("h2_refinementCategory").get(0).getText().
						toLowerCase().replaceAll("[^a-zA-Z]+", " ").trim());

		Assert.assertTrue(
				getCurrentURL().toLowerCase().replaceAll("[^a-zA-Z]+", " ").contains(
						elements("h2_refinementCategory").get(0).getText().
								toLowerCase().replaceAll("[^a-zA-Z]+", " ").trim()),
				"[ASSERTION FAILED]: User is NOT on CATEGORY searched page !!!");
		msg.pass("User is on Category searched page !!!");
	}

	public void verifyRefinementSectionInShopGridPage() {
		wait.waitForLoad();
		isElementDisplayed("drpdn_Filter");
		isElementDisplayed("drpdn_gridSortHeader");
		isElementDisplayed("sortngOptions");
		List<WebElement> sortngOptions = elements("sortngOptions");
		for (WebElement el : sortngOptions) {
			el.isDisplayed();
			msg.log("Sorting option: '" + el.getText() + "' is displayed");
		}
	}

	public void verifyProductSortingInShopGridPage(String sortingOption) {
		wait.waitForLoad();

		ArrayList<String> originalSorting = new ArrayList<>();
		ArrayList<String> increasingOrderList = new ArrayList<>();
		ArrayList<String> decreasingOrderList = new ArrayList<>();

		// To avoid StaleElementReference Exception
		hardWait(5);

		msg.log("No. of products: " + elements("lbl_productPrice").size());
		List<WebElement> productPrice = elements("lbl_productPrice");

		for (WebElement el : productPrice) {
			wait.waitForLoad();
			originalSorting.add(el.getText());
		}

		scrollDown(element("drpdn_gridSortHeader"));
		isElementDisplayed("drpdn_gridSortHeader");

		Select sel = new Select(element("drpdn_gridSortHeader"));
		sel.selectByVisibleText(sortingOption);

		// To avoid StaleElementReference Exception
		hardWait(5);

		wait.waitForLoad();
		msg.log("No. of products after '" + sortingOption + "': " + elements("lbl_productPrice").size());

		ArrayList<String> obtainedList = new ArrayList<>();
		List<WebElement> priceAfterSort = elements("lbl_productPrice");

		for (WebElement el : priceAfterSort) {
			wait.waitForLoad();
			obtainedList.add(el.getText());
		}

		for (String s : obtainedList) {
			wait.waitForLoad();
			increasingOrderList.add(s);
			decreasingOrderList.add(s);
		}

		Collections.sort(increasingOrderList);
		Collections.sort(decreasingOrderList);
		Collections.reverse(decreasingOrderList);

		if (sortingOption.equalsIgnoreCase("Low to High") || sortingOption.equalsIgnoreCase("bas à élevé")) {
			assertEquals(increasingOrderList.size(), obtainedList.size(),
					"[ASSERTION FAILED]: Product is not arranged by price as Low to High");
			msg.pass("Product is arranged by price as 'Low to High' option");
		} else if (sortingOption.equalsIgnoreCase("High to Low") || sortingOption.equalsIgnoreCase("élevé à bas")) {
			assertEquals(decreasingOrderList.size(), obtainedList.size(),
					"[ASSERTION FAILED]: Product is not arranged by price as High to Low");
			msg.pass("Product is arranged by price as 'High to Low' option");
		} else if (sortingOption.equalsIgnoreCase("Just Added")
				|| (sortingOption.equalsIgnoreCase("Vient D'ajouter"))) {
			assertEquals(originalSorting.size(), obtainedList.size(),
					"[ASSERTION FAILED]: Product is not arranged by attribute Newly added");
			msg.pass("Product is arranged by attribute 'Newly added' option");
		}
	}

	public void verifyCategoryHeaderAndProductCountIsDisplayed() {
		wait.waitForLoad();

		isElementDisplayed("header_ResultsFor");
		msg.log("Product Count is displayed : " + element("header_ResultsFor").getText().replaceAll("\\D+", ""));
	}

	public void verifyProductCountChangesAccordingToRefinement() {
		wait.waitForLoad();
		isElementDisplayed("header_ResultsFor");
		String prodCount1 = element("header_ResultsFor").getText().replaceAll("\\D+", "");
		isElementDisplayed("drpdn_Filter");
		element("drpdn_Filter").click();
		msg.log("Dropdown filter is clicked");
		isElementDisplayed("frstRefinmntColor");
		clickUsingJS(element("frstRefinmntColor"));
		msg.log("First refinement color is clicked");
		String prodCount2 = element("header_ResultsFor").getText().replaceAll("\\D+", "");
		assertEquals(prodCount1 != prodCount2, true,
				"[Assert Fail] : Product Count does not changes after filter selection");
		msg.log("Product count changes after filter selection");
	}

	public void verifySubCategoriesAreDisplayedInSideBar() {
		wait.waitForLoad();
		isElementDisplayed("subCatWithNoSubLink");
		List<WebElement> subCatWithNoSubLink = elements("subCatWithNoSubLink");
		for (WebElement el : subCatWithNoSubLink) {
			el.isDisplayed();
			msg.log("Subcategory with no link: " + el.getText());
		}
		isElementDisplayed("subCatWithSubLink");
		List<WebElement> subCatWithSubLink = elements("subCatWithSubLink");
		for (WebElement el : subCatWithSubLink) {
			el.isDisplayed();
			msg.log("Subcategory with sub link: " + el.getText());
		}

		try {
			isElementDisplayed("frstPlusIcon");
			elements("frstPlusIcon").get(0).isDisplayed();
			clickUsingJS(elements("frstPlusIcon").get(0));
			msg.log("Plus icon is displayed");
			isElementDisplayed("list_subSubCatgry");
			List<WebElement> subSubCatgry = elements("list_subSubCatgry");
			for (WebElement el : subSubCatgry) {
				el.isDisplayed();
				msg.log("Sub sub category : " + el.getText());
			}
			isElementDisplayed("firstMinusIcon");
			element("firstMinusIcon");
			msg.log("Minus icon is clicked");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyTheGreenColorOfSelectedSubCategory() {
		wait.waitForLoad();
		isElementDisplayed("activeSubCatLink");
		assertEquals(element("activeSubCatLink").getCssValue("color"), "rgba(72, 168, 66, 1)",
				"Green color not displaying");
		msg.log("Green color is didplayed");
	}

	public void verifySubcategoriesAreExpandedInSideBar() {
		isElementDisplayed("subCatWithNoSubLink");
		isElementDisplayed("expandedSubCatgry");
		List<WebElement> expandedSubCatgry = elements("expandedSubCatgry");
		for (WebElement el : expandedSubCatgry) {
			el.isDisplayed();
			msg.log("Expanded Subcategory : " + el.getText());
		}
	}

	public void verifyTheMaxProductCountInShopgridPage() {
		wait.waitForLoad();
		isElementDisplayed("list_prodName");
		List<WebElement> prodName = elements("list_prodName");
		int count = 0;
		for (WebElement el : prodName) {
			el.isDisplayed();
			count++;
		}
		msg.log("No of products in shop grid page : " + count);
		assertEquals(count <= 30, true,
				"[Assert Fail]: Number of the products in the shop grid page is not as expected");
		msg.log("Number of the products in the shop grid page is as expected");

		if (count > 30) {
			scrollDownToBottom();
			isElementDisplayed("btn_loadMore");
		}
	}

	public void verifyLazyLoadingOnShopGridPage() {
		wait.waitForLoad();
		scrollDownToBottom();
		try {
			isElementDisplayed("btn_loadMore");
			element("btn_loadMore").click();
			msg.log("Load more button is clicked");
			checkLazyLoading();
			scrollDownToBottom();
			isElementNotDisplayed("btn_loadMore");
			msg.log("Verified: Load more button is clickable only once ");
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyUserIsNaviagatedToSamePositionInShpGrdPageWhenClickBackOnPDP() {
		wait.waitForLoad();
		isElementDisplayed("list_prodName");
		List<WebElement> prodNameBeforeNav = elements("list_prodName");
		String frstProdNameBefNav = prodNameBeforeNav.get(1).getText();
		clickUsingJS(prodNameBeforeNav.get(1));
		msg.log("Clicked on the first product");
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(3);
		}
		String pdpName = executeJavascriptReturnsString("return $('h1.product-name').text()");
		// assertEquals(getCurrentURL().contains(frstProdNameBefNav.toLowerCase().replaceAll("
		// ", "-")), true, "[Assert Fail]: User is not navigated to the PDP");
		assertEquals(frstProdNameBefNav.equalsIgnoreCase(pdpName), true,
				"[Assert Fail]: User is not navigated to the PDP");
		msg.log("User is navigated to the PDP");
		backButton();
		msg.log("Back button is clicked");
		wait.waitForLoad();
		List<WebElement> prodNameAfterNav = elements("list_prodName");
		String frstProdNameAfterNav = prodNameAfterNav.get(1).getText();
		assertEquals(frstProdNameBefNav, frstProdNameAfterNav, "Position Of the product does not remains same ");
		msg.log("Postion of the product remains same");
	}

	public void verifyProductImagesAreLoadedUsingAdobeScene7() {
		wait.waitForLoad();
		isElementDisplayed("list_prodctImg");
		List<WebElement> prodImg = elements("list_prodctImg");
		for (WebElement el : prodImg) {
			el.isDisplayed();
			msg.log("Actual Result: " + el.getAttribute("src"));
			msg.log("Expected Result: scene7");
			Assert.assertTrue(el.getAttribute("src").contains("scene7"),
					"[ASSERTION FAILED]: Images are not loaded from Adobe Scene 7 !!!");
		}
		msg.pass("Images are loaded from Adobe Scene7 !!!");
	}

	public void verifyClickingFrstProductImageOnShopGridPageNavToCorrespondngPDP() {
		wait.waitForLoad();
		isElementDisplayed("list_prodctImg");
		// List<WebElement> prodImg = elements("list_prodctImg");
		String frstProdName = elements("list_prodName").get(0).getText();
		// prodImg.get(0).click();
		executeJavascript("$('.product-image .front.face img').eq(0).click()");
		msg.log("User clicked on the first product image on the shop grid page");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String pdpName = executeJavascriptReturnsString("return $('.product-col-1 h1').text()");
		assertEquals(frstProdName.equalsIgnoreCase(pdpName), true,
				"[Assert Fail] : User is not navigated to the same PDP");
		msg.log("User is navigated to the same PDP");
		backButton();
	}

	public void verifyClickingFrstProductNameOnShopGridPageNavToCorrespondngPDP() {
		wait.waitForLoad();
		isElementDisplayed("list_prodName");
		List<WebElement> prodName = elements("list_prodName");
		String frstProdName = elements("list_prodName").get(0).getText();
		scrollDown(prodName.get(0));
		clickUsingJS(prodName.get(0));
		msg.log("User clicked on the first product name on the shop grid page");
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String pdpName = executeJavascriptReturnsString("return $('.product-col-1 h1').text()");
		assertEquals(frstProdName.equalsIgnoreCase(pdpName), true,
				"[Assert Fail] : User is not navigated to the same PDP");
		msg.log("User is navigated to the same PDP");
		backButton();
	}

	public void verifyClickingQuickBuyIconOnShopGridPageNavToCorrespondngQuickBuyPage() {
		wait.waitForLoad();
		isElementDisplayed("list_prodctImg");
		List<WebElement> prodImg = elements("list_prodctImg");
		String frstProdName = elements("list_prodName").get(0).getText();
		hover(prodImg.get(0));
		isElementDisplayed("icon_quickView");
		click(element("icon_quickView"));
		msg.log("User clicked on the QuickShop Icon");
		String quickBuyPageProdName = element("lbl_productName").getText();
		assertEquals(frstProdName.equalsIgnoreCase(quickBuyPageProdName), true,
				"[Assert Fail] : User is not navigated to the same product quick buy page");
		msg.log(" User is navigated to the same product quick buy page");
		isElementDisplayed("icon_close");
		element("icon_close").click();
		msg.log("Close button is clicked");
	}

	public void verifyPromotionalMsgAreAppearingInShopGridPage(String promotionalMsg) {
		wait.waitForLoad();
		try {
			isElementDisplayed("text_promotionalMsg", promotionalMsg);
			msg.log("Promotional message is appearing in shop grid page. : " + promotionalMsg);
		} catch (Exception e) {
			msg.log("Promotional message does not appear in shop grid page. : ");
			msg.log("Exception : " + e);
		}
	}

	public void verifyAlternateImagesAreDisplayedOnMouseOver() {
		wait.waitForLoad();
		isElementDisplayed("list_frontFaceImg");
		hover(elements("list_frontFaceImg").get(0));
		msg.log("Hovered on the first image of the product");
		isElementDisplayed("list_backFaceImg");
		msg.log("Verified: Alternate images are displayed on moue over");
	}

	public void verifySwatchListOnTheShopGridPage(String prodIndex) {
		wait.waitForLoad();
		try {
			scrollDown(element("list_swatch", prodIndex));
			isElementDisplayed("list_swatch", prodIndex);
			int count = elements("list_swatch", prodIndex).size();
			msg.log("Count of the list : " + count);
			if (count <= 3) {
				isElementNotDisplayed("link_allPrdctSwatch", prodIndex);
			} else {
				isElementDisplayed("link_allPrdctSwatch", prodIndex);
				element("link_allPrdctSwatch", prodIndex).click();
				msg.log("User clicked on the + button of the swatches");
				assertEquals(element("list_allPrdctSwatch", prodIndex).getAttribute("style").contains("none"), true,
						"[Assert Fail]: Modal window displaying all color swatches are not displayed");
				msg.log("Modal window displaying all color swatches are is displayed");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public void verifyTextBuyAllOrNoneDisplayForProductSet() {
		wait.waitForLoad();
		isElementDisplayed("list_productStPrice");
		msg.log("Verified: Buy All Or None text appears for Product Set");
	}

	public void verifyHeaderReturnsSearchQuerryWitchTotalNumberOfResults() {
		isElementDisplayed("searched_header");
		assertEquals(element("searched_header").getTagName(), "h1", 
				"[ASSERTION FAILED]: Searched result page is not in H1 !!!");
		msg.pass("Searched result page is in H1");
	}

	public void verifyFilterOptionIsPresent() {
		isElementNotDisplayed("drpdn_hide_filter");
		isElementDisplayed("drpdn_Filter");
		element("drpdn_Filter").click();
		msg.log("User clicks on filter dropdown");
		isElementDisplayed("drpdn_hide_filter");
		int size = elements("filter_options").size();
		for (int i = 0; i < size; i++) {
			elements("filter_options").get(i).isDisplayed();
			elements("filter_options").get(i).getText();
		}
		element("drpdn_hide_filter").click();
		msg.log("User closes the filter option");
	}

	public void verifySortingFiltersArePresent() {
		isElementDisplayed("drpdn_gridSortHeader");
		List<WebElement> sort = elements("sortngOptions");
		for (WebElement sortOptions : sort) {
			msg.log(sortOptions.getText());
		}
	}

	public void verifyThePricingStructureOfProductInShopGridPage() {
		wait.waitForLoad();
		isElementDisplayed("lbl_productPrice");
		msg.log("Product price is " + elements("lbl_productPrice").get(0).getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(elements("lbl_productPrice").get(0).getText().contains("£"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}

		else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(elements("lbl_productPrice").get(0).getText().contains("€"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePageForMobile() {
		executeJavascript("$('.search-menu .search-toggle a').click()");
		msg.log("User clicks on search bar");
		
		isElementDisplayed("textbox_Search");

		element("textbox_Search").sendKeys("Shoes");
		msg.log("User enters the value to see search predictions");

		elements("category_search").get(1).isDisplayed();
		String categorySearch = elements("category_search").get(1).getText().toLowerCase();
		msg.log(categorySearch);
		elements("category_search").get(1).click();
		msg.log("User clicks on first category");
		wait.waitForLoad();

		msg.log(getCurrentURL().toLowerCase());
		msg.log(categorySearch);
		assertEquals(getCurrentURL().toLowerCase().contains(categorySearch), true,
				"[ASSERTION FAILED]: User is NOT on category searched page");
		msg.log("User is on category searched page");
	}

	public void verifyProductCountChangesAccordingToSizeRefinement() {
		wait.waitForLoad();
		isElementDisplayed("header_ResultsFor");
		String prodCount1 = element("header_ResultsFor").getText().replaceAll("\\D+", "");

		// To Avoid StaleElementReference Exception
		hardWait(2);

		scrollUpToPage();

		isElementDisplayed("drpdn_Filter");
		element("drpdn_Filter").click();
		msg.log("Dropdown filter is clicked");

		isElementDisplayed("frstRefinmntSize");
		element("frstRefinmntSize").click();
		msg.log("First refinement size is clicked");

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		String prodCount2 = element("header_ResultsFor").getText().replaceAll("\\D+", "");
		assertEquals(prodCount1 != prodCount2, true,
				"[Assert Fail] : Product Count does not changes after filter selection");
		msg.log("Product count changes after filter selection");
	}

}
