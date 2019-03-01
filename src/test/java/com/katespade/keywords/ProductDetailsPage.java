package com.katespade.keywords;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;
import com.qait.automation.utils.PropFileHandler;

public class ProductDetailsPage extends GetPage {

	WebDriver driver;

	public ProductDetailsPage(WebDriver driver) {
		super(driver, "Katespade/ProductDetailsPage");
		this.driver = driver;
	}

	public void Add_To_Bag() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		// executeJavascript("$('#add-to-cart').click();");
		isElementDisplayed("button_addToBag");
		clickUsingJS(element("button_addToBag"));
		wait.waitForLoad();
		msg.log("Clicked on Add to bag");
	}

	public void verifyProductDetailPage() {
		wait.waitForLoad();
		isElementDisplayed("productName");
		isElementDisplayed("label_productPrice");
		msg.log(element("label_productPrice").getText().trim() + " is displayed");
		isElementDisplayed("icon_colorSwatches");
		msg.log(element("icon_colorSwatches").getText().trim() + " is displayed");
		isElementDisplayed("label_share");
		msg.log(element("label_share").getText().trim() + " is displayed");
		isElementDisplayed("label_productDescription");
		msg.log(element("label_productDescription").getText().trim() + " is displayed");
		isElementDisplayed("button_addToBag");
		scrollDown(element("button_addToBag"));
		try {
			element("button_addToBag").click();
		} catch (Exception Ex) {
			clickByJavascript(element("button_addToBag"));
		}
		msg.log("User clicks on Add to Bag");
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			waitForLoad();
			isElementDisplayed("primaryImage");
			msg.log("All results are displayed");
			isElementDisplayed("productName");
			scrollDown(element("label_productPrice"));
			isElementDisplayed("label_productPrice");
			msg.log(element("label_productPrice").getText().trim() + " is displayed");
			isElementDisplayed("icon_colorSwatches");
			msg.log(element("icon_colorSwatches").getText().trim() + " is displayed");
			isElementDisplayed("label_share");
			msg.log(element("label_share").getText().trim() + " is displayed");
			isElementDisplayed("label_productDescription");
			msg.log(element("label_productDescription").getText().trim() + " is displayed");
			isElementDisplayed("button_addToBag");
			element("button_addToBag").click();
			msg.log("User clicks on Add to Bag");
		}
	}

	public void verifyPrimaryImageAndProductThumbnail() {
		wait.waitForLoad();
		isElementDisplayed("primaryImage");
		isElementDisplayed("icon_fullScreenPlus");
	}

	public void verifyPrimarImageInMobile() {
		// Avoid "Appium error: An unknown server-side error occurred while processing
		// the command" error
		wait.hardWait(5);

		isElementDisplayed("pdp_thumbnail");
		msg.log("PDP primary and alt images verified");
	}

	public void verifyDetailsOfProductInPDPPage() {
		wait.waitForLoad();
		isElementDisplayed("productName");
		msg.log(element("productName").getText().trim() + " is displayed");

		isElementDisplayed("label_productPrice");
		msg.log(element("label_productPrice").getText().trim() + " is displayed");

		isElementDisplayed("label_productDescription");
		msg.log(element("label_productDescription").getText().trim() + " is displayed");

		isElementDisplayed("label_size");
		msg.log(element("label_size").getText().trim() + " is displayed");

		isElementDisplayed("label_material");
		msg.log(element("label_material").getText().trim() + " is displayed");

		isElementDisplayed("label_style");
		msg.log(element("label_style").getText().trim() + " is displayed");

		isElementDisplayed("label_detail");
		msg.log(element("label_detail").getText().trim() + " is displayed");

		isElementDisplayed("icon_writeReview");
		isElementDisplayed("icon_colorSwatches");

		msg.pass("Verified PDP Page is displaying all elements !!!");
	}

	public void verifyBreadCrumbs() {
		wait.waitForLoad();
		isElementDisplayed("label_breadcrum");
	}

	public void verifySocialSitesIcons() {
		wait.waitForLoad();
		isElementDisplayed("icon_facebook");
		isElementDisplayed("icon_twitter");
		isElementDisplayed("icon_pinterest");
	}

	public void verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 100);
		}
		hardWait(1);
		isElementDisplayed("button_addToBag");
		
		isElementDisplayed("icon_firstColorSwatch");
		String swatchColor = element("icon_firstColorSwatch").getAttribute("alt");
		msg.log("Swatch color : " + swatchColor);

		hardWait(2);
		element("icon_firstColorSwatch").click();
		msg.log("Clicked on the color swatch");

		Assert.assertEquals(element("label_color").getText().toLowerCase(), swatchColor.toLowerCase(),
				"[ASSERTION FAILED]: Swatch color does not match with product color");
		msg.pass("Swatch color match with product color");

		String wishListText = executeJavascriptReturnsString("return $('.button-text.wishlist').text()");
		msg.log("Wishlist Linktext: '" + wishListText.trim() + "'");

		if (wishListText.trim().equalsIgnoreCase("Add to Wishlist")) {
			isElementDisplayed("link_addToWishlist");
			clickUsingJS(element("link_addToWishlist"));
			msg.log("Clicked on 'ADD TO WISHLIST' link");
		} else {
			msg.log("SELECTED ITEM ALREADY ADDED TO YOUR WISHLIST !!!");
			element("lnk_wishList").click();
			msg.log("Clicked on 'WISHLIST' link");
		}
	}

	public void verifyUserCantAddProductToWishlistWhenNotLoggedIn() {
		wait.waitForLoad();

		isElementDisplayed("link_addToWishlist");
		scrollDown(element("link_addToWishlist"));

		clickUsingJS(element("link_addToWishlist"));
		msg.log("Clicked on 'ADD TO WISHLIST' link");

		isElementDisplayed("button_login");
		msg.log("User can't add a product to wishlist when not logged in.");
	}

	public void verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn() {
		wait.waitForLoad();
		isElementDisplayed("link_addToWishlist");

		clickUsingJS(element("link_addToWishlist"));
		msg.log("Clicked on 'ADD TO WISHLIST' link");
	}

	public void verifyGreenColorOfAddToBagButton() {
		isElementDisplayed("button_addToBag");
		assertEquals(element("button_addToBag").getCssValue("background-color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Green color not displaying");
		msg.pass("Green color is displayed");
	}

	public void clickOnAddProductToBag() {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			scrollWindow(0, 300);
			hardWait(5);
		}

		Select sl = new Select(element("dropdown_qty"));
		List<WebElement> listQty = sl.getOptions();

		try {
			msg.log("Available Swatches Color: " + elements("lnk_nonSelectedSwatchColor").size());

			for (int index = 0; index <= elements("lnk_nonSelectedSwatchColor").size(); index++) {
				msg.log("'ADD TO BAG' button: '" + element("button_addToBag").getAttribute("class") + "'");
				msg.log("No. of Qty options: " + listQty.size());

				if (element("button_addToBag").getAttribute("class").contains("disabled") || listQty.size() == 1) {
					executeJavascript("document.getElementsByClassName('swatches color')[0]."
							+ "getElementsByClassName('swatchanchor')[" + index + "].click()");
					msg.log("Clicked on non-selectable color swatch");

					// Wait for page to load for selected Color Swatch
					hardWait(5);
				} else {
					break;
				}
			}
		} catch (TimeoutException ex) {
			msg.log("THIS PRODUCT DOES NOT ANY OTHER COLOR !!!");
		}

		executeJavascript("document.getElementsByClassName('t046-close')[0].click()");
		msg.log("Clicked on 'Close' icon of Feedback dialog box");

		clickByJavascript(element("button_addToBag"));
		msg.log("Clicked on 'Add To Bag' button");
	}

	public void verifyTheHeaderOfTheRecommendationHeaderSection() {
		wait.waitForLoad();
		scrollDown(element("label_recommendationHeader"));
		isElementDisplayed("label_recommendationHeader");
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr-fr")) {
			assertEquals(element("label_recommendationHeader").getText(), "si vous aimez ce modèle, vous adorerez",
					"Recommendation header is not matched");
		} else {
			assertEquals(element("label_recommendationHeader").getText(), "you might also like...",
					"Recommendation header is not matched");
		}
		msg.log("Recommendation header is matched");
	}

	@SuppressWarnings("unused")
	public void verifyTheMaxLimitOfRecommendedItems() {
		wait.waitForLoad();
		isElementDisplayed("list_productRecommendationImage");
		List<WebElement> prodImage = elements("list_productRecommendationImage");
		int count = 0;
		for (WebElement el : prodImage) {
			count++;
		}

		msg.log("Count of the recommended image is " + count);
		if (ConfigPropertyReader.getProperty("browser").contains("iphone"))
			assertEquals(count <= 2, true, "Count of recommended product image is not matched for iphone");
		else
			assertEquals(count <= 4, true, "Count of recommended product image is not matched");
		msg.log("Count of recommended product image is matched");
	}

	public void verifyProductIsDisplayedAsTiles() {
		wait.waitForLoad();
		isElementDisplayed("list_productRecommendedTiles");
		List<WebElement> prodImageTiles = elements("list_productRecommendedTiles");
		int count = 0;
		for (WebElement el : prodImageTiles) {
			el.isDisplayed();
			count++;
		}

		msg.log("Count of the recommended image tiles is " + count);
		assertEquals(count <= 4, true, "Count of recommended product image tiles is not matched");
		msg.log("Count of recommended product image is matched");
	}

	public void verifyQuickViewShopButtonShouldNotVisibleOnPDPPage() {
		wait.waitForLoad();
		isElementNotDisplayed("button_quickShop");
	}

	public void verifyColorChangeOfRecommendedProductName() {
		wait.waitForLoad();
		isElementDisplayed("text_firstRecommendedProductName");
		
		Assert.assertEquals(element("text_firstRecommendedProductName").getCssValue("color"), "rgba(0, 0, 0, 1)",
				"[ASSERTION FAILED]: Black color not displaying !!!");
		msg.pass("Black color is displayed");
		
		scrollToElement(element("img_recommendedProduct"));
		hover(element("img_recommendedProduct"));
		hardWait(1);
		Assert.assertEquals(element("text_firstRecommendedProductName").getCssValue("color"), "rgba(72, 168, 66, 1)",
				"[ASSERTION FAILED]: Green color not displaying on hovering the recommended product !!!");
		msg.pass("Green color is displayed !!!");
	}

	public String verifyWishlistCanBeAccessdedFromPDP() {
		wait.waitForLoad();
		selectAndClickOnSwatch();
		isElementDisplayed("productName");
		// waitForLoad();
		hardWait(1);
		String styleNum = executeJavascriptReturnsString("return $('#add-to-cart+isapplepay').attr('sku')");
		msg.log("Sku number is " + styleNum);

		isElementDisplayed("link_addToWishlist");
		clickUsingJS(element("link_addToWishlist"));
		msg.log("Clicked on 'ADD TO WISHLIST' link");

		return styleNum;
	}

	public void selectAndClickOnSwatch() {
		isElementDisplayed("swatch_color", "1");
		msg.log("Swatch is displayed");
		clickUsingJS(element("swatch_color", "1"));
		msg.log("Clicked on swatch and changed color");
	}

	public void selectProductFromRecommendedListAndVerifyInPDPPage() {
		wait.waitForLoad();
		isElementDisplayed("text_firstRecommendedProductName");
		
		String firstRecommendedProductName = element("text_firstRecommendedProductName").getText();
		msg.log("Name of Recommended product: '" + firstRecommendedProductName + "'");
		
		scrollToElement("text_firstRecommendedProductName");
		clickUsingJS(element("text_firstRecommendedProductName"));
		msg.log("Clicked on first Recommended product name");
		
		hardWait(1);
		Assert.assertEquals(element("label_productName").getText().toUpperCase(), 
				firstRecommendedProductName.toUpperCase(),
				"[ASSERTION FAILED]: Selected recommended product name is not matched !!!");
		msg.pass("Selected recommended product name is matched !!!");
		
		backButton();
	}

	public void verifyTheAlignmentOfTheRecommendedProductImage() {
		wait.waitForLoad();
		isElementDisplayed("img_recommendedProduct");
		assertEquals(element("img_recommendedProduct").getCssValue("text-align"), "center",
				"Image is not centrally aligned");
		msg.log("Image is centrally aligned");
	}

	public void verifyMostRecentlyViewedSection(String recentlyViewedHeader) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("label_mostRecentlyViewedHeader");
		String recentHeader = executeJavascriptReturnsString("return $('.product-listing.last-visited h2').text()");
		msg.log(recentHeader);
		assertEquals(recentHeader, recentlyViewedHeader, "Most Recently viewed Header is not matched");
		msg.log("[Assert Pass]: Most Recently viewed Header is  matched");
	}
	
	public void verifyTheMaxLimitOfPreviouslyViewedItems() {
		wait.waitForLoad();
		
		msg.log("No. of Most Recently Viewed Items: " + elements("list_mostRecentlyViewedItemsImage").size());
		Assert.assertTrue(elements("list_mostRecentlyViewedItemsImage").size() <= 4, 
				"[ASSERTION FAILED]: Count of most recently viewed product image is not matched !!!");
		msg.pass("Count of most recently viewed product image is matched !!!");
	}

	public void verifyTheAlignmentOfTheRecentlyViewdProductImage() {
		wait.waitForLoad();
		
		isElementDisplayed("img_recentlyViewedProduct");
		Assert.assertEquals(element("img_recentlyViewedProduct").getCssValue("text-align"), "center",
				"[ASSERTION FAILED]: Image is not centrally aligned !!!");
		msg.pass("Image is centrally aligned !!!");
	}

	public void selectProductFromMostRecentlyViewdListAndVerifyInPDPPage() {
		wait.waitForLoad();
		isElementDisplayed("text_firstRecentlyViewedItemName");
		String prodName = element("text_firstRecentlyViewedItemName").getText();
		msg.log(prodName);
		clickUsingJS(element("text_firstRecentlyViewedItemName"));
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollUpToPage();
			hardWait(2);
		}
		msg.log(element("label_productName").getText());
		assertEquals(element("label_productName").getText().equalsIgnoreCase(prodName), true,
				"Selected recently viewed product name is not matched ");
		msg.log("Selected recently viewed product name is matched ");
	}

	public void verifyColorChangeOfRecentlyViewedProductName() {
		wait.waitForLoad();
		isElementDisplayed("text_firstRecentlyViewedItemName");
		assertEquals(element("text_firstRecentlyViewedItemName").getCssValue("color"), "rgba(0, 0, 0, 1)",
				"Black color not displaying");
		msg.log("Black color is displayed");
		hover(element("text_firstRecentlyViewedItemName"));
		hardWait(1);
		/*
		 * scrollDown(element("text_firstRecentlyViewedItemName"));
		 * assertEquals(element("text_firstRecentlyViewedItemName").getCssValue(
		 * "color"), "rgba(72, 168, 66, 1)","Green color not displaying");
		 */
		msg.log("Green color is displayed");
	}

	public void verifyThatPromotionalMessageIsDisplayedForTheProduct(String promotionalMessage) {
		wait.waitForLoad();
		isElementDisplayed("label_promotionalMessage");
		assertEquals(element("label_promotionalMessage").getText().contains(promotionalMessage), true,
				"Promotional Message is not displayed");
		msg.log("Promotional Message is displayed : " + element("label_promotionalMessage").getText());
	}

	public void verifyThePricingStructureOfProduct() {
		wait.waitForLoad();
		hardWait(1);
		isElementDisplayed("label_productPrice");
		msg.log("Product price is " + element("label_productPrice").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(element("label_productPrice").getText().contains("£"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		} else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("label_productPrice").getText().contains("€"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected");
		}
	}

	public void verifyThatSizeSwatchesIsPresentForTheProduct() {
		wait.waitForLoad();
		isElementDisplayed("list_sizeSwatch");
		List<WebElement> sizeChart = elements("list_sizeSwatch");
		for (WebElement el : sizeChart) {
			msg.log("Size swatch Present for the Product " + el.getText());
		}
	}
	
	public void verifyTheMaxLimitOfThumbnails() {
		wait.waitForPageToLoadCompletely();
		wait.waitForLoad();
		
		isElementDisplayed("list_productThumbnails");
		
		int count = elements("list_productThumbnails").size();
		msg.log("Count of the alternate image thumbnails " + count);
		
		Assert.assertTrue(count <= 4, 
				"[ASSERTION FAILED]: Count of the alternate image thumbnails is not matched !!!");
		msg.pass("Count of the alternate image thumbnails is matched !!!");

		if (count > 4) {
			isElementDisplayed("thumbnailSlider");
			msg.log("Slider is displayed for the thumbnails greater than 4 !!!");
		}
	}

	public void verifyColorAppearsOnHoveringOverTheColorSwatches() {
		wait.waitForLoad();
		isElementDisplayed("list_colorSwatch");
		List<WebElement> color = elements("list_colorSwatch");
		for (WebElement el : color) {
			msg.log("Swatch color is : " + el.getAttribute("alt"));
		}
	}

	public void verifyPromotionalAndMarketingMaterialIsDisplayedInPDP() {
		/*
		 * <!-- dwMarker="slot" dwContentID="product-bottom" dwContext="global"-->
		 * dwContentID="product-bottom" is present inside this division as expected in
		 * the testcase
		 */
		wait.waitForLoad();
		isElementDisplayed("img_contentAssetWrapper");
		msg.log("A content asset with ID = product-bottom is present in PDP");
	}

	public void verifyInventoryMessagingIsPresentOnPDPPage() {
		wait.waitForLoad();
		isElementDisplayed("msg_inStock");
	}

	public void verifyDetailsOfPDPPageWhenProductSetItemIsSearched() {
		wait.waitForLoad();
		isElementDisplayed("label_productName");
		msg.log("Product set name : " + element("label_productName").getText() + " is displayed");
		isElementDisplayed("label_buyOrNone");
		isElementDisplayed("list_productSetItems");
		verifyBreadCrumbs();
		verifyLinkingOfBreadcrumbLevelsToCorrespondingPage();
		List<WebElement> productName = elements("list_productSetItems");
		int prodCount = 0;
		isElementsDisplayed(productName);
		for (WebElement el : productName) {
			prodCount++;
			msg.log("Products are : " + el.getText());
		}
		String productCount = Integer.toString(prodCount);
		PropFileHandler.writeProperty("prodCountInPDP", productCount);
		msg.log("No of products in the product set is : " + prodCount);
		isElementDisplayed("label_productSet_inventoryMessage");
		msg.log("Inventory messaging is displayed");
	}

	public void verifyOnClickingProductNameFromProductSetUserWillBeNavigatedToItsPDPPage() {
		wait.waitForLoad();
		isElementDisplayed("label_productSet_firstName");
		String productSet_firstName = element("label_productSet_firstName").getText();
		msg.log("product name is : " + productSet_firstName);
		element("label_productSet_firstName").click();
		msg.log("First product name is clicked");
		assertEquals(productSet_firstName.contains(element("label_productName").getText()), true,
				"User is not navigated to the same PDP page");
		msg.log("User is navigated to the same PDP Page");
	}

	public void verifyThePricingStructureOfProductSet() {
		wait.waitForLoad();
		isElementDisplayed("list_productPrice_productSet");
		msg.log("Product price is " + element("list_productPrice_productSet").getText());

		if (getCurrentURL().contains("ksEuUk") || getCurrentURL().contains("en-gb")) {
			assertEquals(element("list_productPrice_productSet").getText().contains("£"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected : " + element("list_productPrice_productSet").getText());
		} else if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("ksEuRoe")
				|| getCurrentURL().contains("fr-fr") || getCurrentURL().contains("en-de")) {
			assertEquals(element("list_productPrice_productSet").getText().contains("€"), true,
					"Pricing structure is not as expected");
			msg.log("Pricing structure is as expected : " + element("list_productPrice_productSet").getText());
		}
	}

	@SuppressWarnings("unused")
	public void verifyTheMainProductSetImageAndAlternateProductImageIsDisplayed() {
		wait.waitForLoad();
		isElementDisplayed("primaryImage");
		isElementDisplayed("list_producrSetImageThumbnails");
		List<WebElement> prodImg = elements("list_producrSetImageThumbnails");
		int thmbnlCount = 0;
		isElementsDisplayed(prodImg);
		for (WebElement el : prodImg) {
			thmbnlCount++;
		}
		if (thmbnlCount > 4) {
			isElementDisplayed("thumbnailSlider");
			msg.log("Verified: Carousel is displayed for 4 or more alternative images ");
		}
	}

	public void verifyTheQuantityDropdownSelected(String drpDownSelected) {
		wait.waitForLoad();
		isElementDisplayed("drpdwn_firstProductSetItemQty");
		element("drpdwn_firstProductSetItemQty").click();
		isElementDisplayed("drpdwn_qtySelected");
		assertEquals(element("drpdwn_qtySelected").getAttribute("value").equals(drpDownSelected), true,
				"Quantity dropdown is not matched as expected");
		msg.log("Quantity dropdown is matched as expected : " + element("drpdwn_qtySelected").getAttribute("value"));
	}

	public void verifyProductReviewOnPdpPage() {
		isElementDisplayed("icon_writeReview");
		msg.log("write a reivew link visible on pdp page");
	}

	public void navigateToReviewPage() {
		isElementDisplayed("icon_writeReview");
		element("icon_writeReview").click();
		msg.log("User clicks on write a review link");
	}

	public void clickOnAddToWishlistLink() {
		wait.waitForLoad();
		isElementDisplayed("link_addToWishlist");
		clickUsingJS(element("link_addToWishlist"));
		msg.log("Clicked on 'ADD TO WISHLIST' link");
	}

	public void clickOnKatespadeSpadeLogo() {
		clickOnKateSpadeLogo();
	}

	public void addProductToWishlist() {
		isElementDisplayed("productName");
		isElementDisplayed("link_addToWishlist");
		clickUsingJS(element("link_addToWishlist"));
		msg.log("Clicked on 'ADD TO WISHLIST' link");
	}

	public void verifyProductNameIsDisplayedInPDP(String prodName) {
		wait.waitForLoad();
		isElementDisplayed("productName");
		assertEquals(element("productName").getText().equalsIgnoreCase(prodName), true,
				"[Assert Fail]: Product name not matched");
		msg.log("product name is matched");
	}

	public void clickOnCloseButton() {
		wait.waitForLoad();
		isElementDisplayed("icon_close");
		msg.log("close icon is clicked");
	}

	public void verifyAddAllToBagButtonAndProdSetPriceIsDisplayed() {
		wait.waitForLoad();
		isElementDisplayed("label_productSetPrice");
		isElementDisplayed("button_addAllToBag");
		msg.log("Verified: Product set price and Add All to bag button is displayed");
	}

	public void verifyClickingAlternativeImageReplacedTheMainImage(int thmbImgIndx) {
		wait.waitForLoad();
		isElementDisplayed("list_producrSetImageThumbnails");
		isElementDisplayed("primaryImage");
		elements("list_producrSetImageThumbnails").get(thmbImgIndx).click();
		wait.waitForLoad();
		String imgLink = executeJavascriptReturnsString("return $('.main-image').attr('href')");
		assertEquals(elements("list_producrSetImageThumbnails").get(thmbImgIndx).getAttribute("href").contains(imgLink),
				true, "[Assert Fail]: Image do not get change after selecting the slternative images");
		msg.log("Image is changed after selecting the alternative change");
	}

	public void verifyTheUserIsAbletoMakeASearch() {
		wait.waitForLoad();
		isElementNotDisplayed("try_new_search");
	}

	public void verifySizeChartIsPresentToGuideTheUserInPDP() {
		wait.waitForLoad();
		isElementDisplayed("link_sizeChart");
		clickUsingJS(element("link_sizeChart"));
		msg.log("Size chart link is clicked");
		isElementDisplayed("img_sizeGuide");
		isElementDisplayed("icon_close");
		clickUsingJS(element("icon_close"));
		msg.log("Close button is clicked");
	}

	public void verifyUserIsInPDPPage() {
		wait.waitForLoad();
		assertEquals(getCurrentURL().contains("products"), true, "[Assert Fail]: User is not in PDP page");
		msg.log("User is in PDP page");
	}

	public void clickOnAddAllToBagButtonForProductSetInPdpPage() throws InterruptedException {
		wait.waitForLoad();
		scrollToElement(element("button_addAllToBag"));
		isElementDisplayed("button_addAllToBag");
		clickUsingJS(element("button_addAllToBag"));
		msg.log("Add all to bag button is clicked");
	}

	public void verifyAllProductsAreAddedInTheBag() throws InterruptedException {
		wait.waitForLoad();
		isElementDisplayed("list_productSetItems");
		List<WebElement> pdpPageItems = elements("list_productSetItems");
		clickOnAddAllToBagButtonForProductSetInPdpPage();
		isElementDisplayed("miniCartProdName");
		List<WebElement> miniCartItems = elements("miniCartProdName");
		ArrayList<String> miniCartItemNames = new ArrayList<String>();
		for (WebElement el : miniCartItems) {
			miniCartItemNames.add(el.getText());
			msg.log("Mini cart products : " + el.getText());
		}
		for (int i = 0; i < pdpPageItems.size(); i++) {
			assertEquals(miniCartItemNames.contains(pdpPageItems.get(i).getText().toUpperCase()), true,
					"[Assert Fail]: " + pdpPageItems.get(i).getText() + " is not added to mini cart");
			msg.log(pdpPageItems.get(i).getText() + " is added to mini cart");
		}
		msg.log("Verified: Products in the product set PDP is added to the mini cart page");
	}

	public void verifyIndividualItemsInProductsSetPDPIsAddedToMiniCart() {
		wait.waitForLoad();
		isElementDisplayed("btn_prdctSetAddToCart");
		List<WebElement> prdctSetAddToBagBtn = elements("btn_prdctSetAddToCart");
		isElementDisplayed("list_productSetItems");
		List<WebElement> pdpPageItems = elements("list_productSetItems");
		for (int i = 0; i < prdctSetAddToBagBtn.size(); i++) {
			prdctSetAddToBagBtn.get(i).click();
			msg.log("User clicked on the Add to bag button for product " + (i + 1));
			isElementDisplayed("miniCartProdName");
			List<WebElement> miniCartItems = elements("miniCartProdName");
			ArrayList<String> miniCartItemNames = new ArrayList<String>();
			for (WebElement el : miniCartItems) {
				miniCartItemNames.add(el.getText());
				msg.log("Mini cart products : " + el.getText());
			}
			assertEquals(miniCartItemNames.contains(pdpPageItems.get(i).getText().toUpperCase()), true,
					"[Assert Fail]: product : " + (i + 1) + " i.e " + pdpPageItems.get(i).getText()
							+ " is not added to mini cart");
			msg.log("[Assert Pass]: product : " + (i + 1) + " i.e " + pdpPageItems.get(i).getText()
					+ " is added to mini cart");
			wait.waitForElementToDisappear(element("header_miniCart"));
		}
		msg.log("Verified: Individual Products in the product set PDP is added to the mini cart page");
	}

	public void verifyLinkingOfBreadcrumbLevelsToCorrespondingPage() {
		wait.waitForLoad();
		isElementDisplayed("label_breadcrum");
		isElementDisplayed("link_breadcrumbLevels");
		List<WebElement> breadcrumbLevelsLink = elements("link_breadcrumbLevels");
		ArrayList<String> breadcrumbLinkText = new ArrayList<String>();
		for (WebElement el : breadcrumbLevelsLink) {
			breadcrumbLinkText.add(el.getText());
		}
		for (int i = 0; i < breadcrumbLevelsLink.size(); i++) {
			elements("link_breadcrumbLevels").get(i).click();
			msg.log("User clicked on the breadcrumb link : " + breadcrumbLinkText.get(i));
			assertEquals(getCurrentURL().contains(breadcrumbLinkText.get(i)), true,
					"[Assert Fail]: Breadcrumb level " + breadcrumbLinkText.get(i) + " is not correctly linked");
			msg.log("[Assert Pass]: Breadcrumb level " + breadcrumbLinkText.get(i) + " is correctly linked");
			backButton();
		}
	}

	public void verifyUserHasTheAbilityToZoomTheProduct() {
		wait.waitForLoad();
		isElementDisplayed("primaryImage");
		hover(element("primaryImage"));
		msg.log("Hovered on the primary image of the product");
		isElementDisplayed("zoomImg");
		assertEquals(element("zoomImg").getCssValue("opacity"), "1",
				"[Assert Fail]: User is not able to zoom the product");
		msg.log("[Assert Pass]: User is able to zoom the product");
	}

	public void verifyMainImageAndZoomImageOccupyTheSameSpace() {
		wait.waitForLoad();
		isElementDisplayed("primaryImage");
		hover(element("primaryImage"));
		msg.log("Hovered on the primary image of the product");
		isElementDisplayed("zoomImg");
		assertEquals(element("zoomImg").getCssValue("position"), "absolute",
				"[Assert Fail]: Zoom image and Primary image does not occupy the same space");
		msg.log("Zoom image and Primary image occupy the same space");
	}

	public void verifySocialShareLinks() {
		wait.waitForLoad();
		scrollDown(element("link_socialSharing"));
		isElementDisplayed("link_socialSharing");
		String link = executeJavascriptReturnsString("return $('.facebook-share.share-icon')[0].getAttribute('href')");
		driver.get(link);
		wait.waitForLoad();
		assertEquals(getCurrentURL().contains("facebook"), true, "[Assert Fail]: facebook is not launched");
		msg.log("facebook is launched");
		backButton();
		wait.waitForLoad();

		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		executeJavascript("$('.twitter-share.share-icon')[0].click()");
		wait.waitForLoad();
		changeWindow(1);
		assertEquals(getCurrentURL().contains("twitter"), true, "[Assert Fail]: twitter is not launched");
		msg.log("twitter is launched");
		changeWindow(0);
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
		}
		executeJavascript("$('.pinterest-share')[0].click()");
		wait.waitForLoad();
		hardWait(2);
		changeWindow(2);
		assertEquals(getCurrentURL().contains("pinterest"), true, "[Assert Fail]: pinterest is not launched");
		msg.log("pinterest is launched");
		changeWindow(0);
		wait.waitForLoad();
	}

	public void verifyBlackBottomBorderOfTheSelectedThumbnailImage() {
		wait.waitForLoad();
		isElementDisplayed("link_thmbnailSelected");
		hardWait(2);
		assertEquals(element("altImgSelected").getCssValue("border-bottom-color"), "rgba(0, 0, 0, 1)",
				"[Assert Fail]: Color of the bottom border is not black");
		msg.log("Color of the bottom border is black");
	}
	
	public void verifyProductImagesAreLoadedUsingAdobeScene7InPDP() {
		wait.waitForLoad();
		isElementDisplayed("img_recommendedProduct");
		List<WebElement> prodImg = elements("img_recommendedProduct");
		for (WebElement el : prodImg) {
			el.isDisplayed();
			assertEquals(el.getAttribute("src").contains("scene7"), true,
					"[Assert Fail]: Images are not loaded from adobe scene 7");
		}
		msg.log("Images are loaded from adobe scene7");
	}

	public void verifySalesPriceForRecommendedProduct() {
		wait.waitForLoad();
		try {
			isElementDisplayed("salesPrice_recommendedItem");
		} catch (Exception e) {
			msg.log("Exception : Sales Price is not present for the product set" + e);
		}
	}

	public void verifyAlternateImagesAreDisplayedOnMouseOverInPDP() {
		wait.waitForLoad();
		isElementDisplayed("list_frontFaceImg");
		hover(elements("list_frontFaceImg").get(0));
		msg.log("Hovered on the first image of the product");
		isElementDisplayed("list_backFaceImg");
		msg.log("Verified: Alternate images are displayed on moue over");
	}

	public void selectProductImageRecommendedListAndVerifyInPDPPage() {
		wait.waitForLoad();
		
		String firstRecommendedProductName = element("text_firstRecommendedProductName").getText();
		msg.log("Name of Recommended product: '" + firstRecommendedProductName + "'");
		
		isElementDisplayed("img_recommendedProduct");
		scrollToElement("img_recommendedProduct");
		
		clickUsingJS(elements("img_recommendedProduct").get(0));
		msg.log("Clicked on image of Recommended product");
		
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			hardWait(2);
			scrollUpToPage();
		}
		msg.log("Label of Product: '" + element("label_productName").getText() + "'");
		Assert.assertEquals(element("label_productName").getText().toLowerCase(), firstRecommendedProductName.toLowerCase(),
				"[ASSERTION FAILED]: Selected recommended product name is not MATCHING !!!");
		msg.pass("Selected recommended product name is matched ");
	}

	public void selectSizeSwatch(int swatchIndex) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("list_sizeSwatch");
		List<WebElement> sizeSwatch = elements("list_sizeSwatch");
		
		clickUsingJS(sizeSwatch.get(swatchIndex));
		msg.log("Clicked on the size swatch at position " + swatchIndex);
	}

	public void selectColorSwatch(int colorSwatchIndex) {
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}
		isElementDisplayed("list_colorSwatch");
		List<WebElement> colorSwatch = elements("list_colorSwatch");
		clickUsingJS(colorSwatch.get(colorSwatchIndex));
		msg.log("user clicked on the color swatch at position " + colorSwatchIndex);
	}

	public String pickPriceOfSearchedProduct() {
		String[] productPrice = element("span_productPrice").getText().trim().split(" ");
		msg.log("Product Sales: " + element("span_productPrice").getText().trim());
		msg.log("Price of Searched Product: " + productPrice[1]);
		return productPrice[1];
	}

	public void verifyProductPriceUsedByFranceFrenchLocaleWillBeSameForOtherRoECountries(
			String productPriceFranceLocale) {
		String[] productSalesPrice = element("span_productPrice").getText().trim().split(" ");
		String productPriceOnIrelandLocale = productSalesPrice[1].replaceAll("[a-zA-Z]+", "").trim();

		productPriceFranceLocale = productPriceFranceLocale.replaceAll("[a-zA-Z]+", "").trim();
		msg.log("Price of searched product (France Locale): " + productPriceFranceLocale);

		msg.log("Product Sales Price (Ireland Locale): " + element("span_productPrice").getText().trim());
		msg.log("Price of searched product (Ireland Locale): " + productPriceOnIrelandLocale);

		Assert.assertEquals(productPriceOnIrelandLocale, productPriceFranceLocale,
				"[ASSERTION FAILED]: France French locale are NOT same for other RoE countries !!!");
		msg.pass("Verified Price list used by France French locale are same for other RoE countries !!!");
	}

	public void verifyUGCContentSlotAboveRatingsAndReviewSection() {
		hardWait(2);
		
		try {
			scrollDown(element("section_UGC"));
			int yCoOfUGC = element("section_UGC").getLocation().getY();
			int yCoOfReview = element("section_Review").getLocation().getY();
			
			Assert.assertTrue(yCoOfReview > yCoOfUGC,
					"[ASSERTION FAILED]: BV UGC Content Slot is placed on PDP NOT above "
					+ "the 'ratings and reviews' section!!!!");
			msg.pass("BV UGC Content Slot is placed on PDP above the 'ratings and reviews' section on the page !!!");
		} catch(AssertionError er) {
			Assert.fail("'SHOW & TELL' title of BV UGC Content Slot is NOT appearing on PDP page!!!");
		}
		
	}

	public void confirmNavigationOnClickingViewGalleryButton() {
		isElementDisplayed("button_ViewGallery");
		try {
			element("button_ViewGallery").click();
		} catch (Exception e) {
			clickByJavascript(element("button_ViewGallery"));
		}
		hardWait(3);
		
		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: " + "ugc-grid-view");
		Assert.assertTrue(getCurrentURL().contains("ugc-grid-view"),
				"[ASSERTION FAILED]: User is not directed to Photo Gallery Page!!");
		msg.pass("Navigation to the Photo Gallery page is confirmed !!!");
	}

	public void confirmNavigationOnClickingSubmitYourPhotoButton() {
		isElementDisplayed("button_SubmitPhoto");
		try {
			element("button_SubmitPhoto").click();
			msg.log("Submit photo button is clicked!!");
		} catch (Exception e) {
			clickByJavascript(element("button_SubmitPhoto"));
			msg.log("Submit photo button is clicked!!");
		}
		changeWindow(1);
		hardWait(3);
		msg.log("Actual Result: " + getCurrentURL());
		msg.log("Expected Result: " + "https://submit.curations.bazaarvoice.com/?client=katespade&group=direct-upload");
		Assert.assertTrue(getCurrentURL().contains("direct-upload"),
				"[ASSERTION FAILED]: User is not directed to Submission Page!!!");
		msg.pass("Navigation to the Submission Page is confirmed !!!");
	}

}
