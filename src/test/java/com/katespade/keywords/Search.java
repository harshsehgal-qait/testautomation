package com.katespade.keywords;

import static org.testng.Assert.assertEquals;
import java.util.List;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.qait.automation.getpageobjects.GetPage;
import com.qait.automation.utils.ConfigPropertyReader;

public class Search extends GetPage {

	WebDriver driver;

	public Search(WebDriver driver) {
		super(driver, "Katespade/Search");
		this.driver = driver;
	}

	public void enterProductIntoSearchBox(String productCategoryOrName) {
		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			hardWait(4);
		} else {
			wait.waitForPageToLoadCompletely();
		}
		scrollUpToPage();
		isElementDisplayed("icon_search");

		element("icon_search").click();
		msg.log("Clicked on 'Search' icon");

		element("textbox_Search").sendKeys(productCategoryOrName);
		msg.log("User entered '" + productCategoryOrName + "' in Search textbox");
	}

	public void clickOnSearchBox() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("btn_go");

		clickUsingJS(element("btn_go"));
		msg.log("Clicked on 'GO' button");
	}

	public void verifyIfProductIsAvailable(String product) {
		int i;
		String[] NumberOfProducts = product.split(",");
		Boolean Flag = null;
		for (i = 0; i < NumberOfProducts.length; i++) {
			msg.log("Entered product '" + NumberOfProducts[i] + "'");

			enterProductIntoSearchBox(NumberOfProducts[i]);
			clickOnSearchBox();

			if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
				waitForLoad();
			} else {
				Flag = checkIfProductIsAvailable(NumberOfProducts[i]);
				if (Flag == true) {
					break;
				} else {
					continue;
				}
			}
		}
	}

	private Boolean checkIfProductIsAvailable(String p1) {
		wait.waitForPageToLoadCompletely();

		if (getPageTitle().contains("No Search Result") || getPageTitle().contains("Keine Suchergebnisse")
				|| getPageTitle().contains("Aucun résultat pour la recherche")
				|| getPageTitle().contains("Nessun risultato di ricerca")
				|| getPageTitle().contains("No hay resultados de la búsqueda")) {
			msg.log("0 search results");
			return false;
		} else {
			msg.pass("Search Results found for product");
			return true;
		}
	}

	public void serachProductWithAKeyword(String string) {
		enterProductIntoSearchBox(string);
	}

	public void verifyThePredictiveResults() {
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("en-fr")) {
			isElementDisplayed("search_phrase_fr");
			msg.log(element("search_phrase_fr").getText().trim().toUpperCase() + " is displayed");
			int size = elements("search_results").size();
			for (int i = 0; i < size; i++) {
				elements("search_results").get(i).isDisplayed();
				msg.pass("Search Results: " + elements("search_results").get(i).getText());
			}
			msg.pass("All results are displayed");
		} else {
			// Due to Auto-suggestion Search results
			wait.hardWait(5);

			isElementDisplayed("search_phrase");
			msg.log(element("search_phrase").getText().trim().toUpperCase() + " is displayed");

			int size = elements("search_results").size();
			for (int i = 0; i < size; i++) {
				elements("search_results").get(i).isDisplayed();
				msg.pass("Search Results: " + elements("search_results").get(i).getText());
			}
			msg.pass("All results are displayed");
		}
	}

	public void clickOnFirstElementFromPredectiveResult() {
		isElementDisplayed("searchfor");
		executeJavascript("$('.product-details .product-name')[0].click()");
		msg.log("Clicked on first element");
	}

	public void verifySearchDisplayed() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_search");
		clickUsingJS(element("icon_search"));
		msg.log("Clicked on Search option");

		isElementDisplayed("textbox_Search");
		isElementDisplayed("btn_go");

		clickUsingJS(element("icon_search"));
		msg.log("Clicked on Search icon");
	}

	public void verifyOnEntering3LettersSuggestionsAppears() {
		wait.waitForLoad();
		executeJavascript("$('.search-menu .search-toggle a').click()");
		msg.log("User clicks on search bar");

		wait.waitForLoad();
		element("textbox_Search").sendKeys("S");
		msg.log("User enters the first letter");

		isElementNotDisplayed("searchfor");
		element("textbox_Search").sendKeys("H");
		msg.log("User enters the second letter");

		isElementNotDisplayed("searchfor");
		element("textbox_Search").sendKeys("O");
		msg.log("User enters the third letter");

		// Due to Auto-suggestion Search box
		wait.hardWait(5);
		isElementDisplayed("searchfor");

		element("textbox_Search").sendKeys("ES");
	}

	public void verifyThumbnailImagesProductNameAndPrice() {
		List<WebElement> allSuggestions = elements("thumbnail_image");
		for (WebElement suggestion : allSuggestions) {
			msg.log("Suggestion: " + suggestion.getAttribute("src"));
		}

		List<WebElement> allName = elements("thumbnail_prodname");
		for (WebElement name : allName) {
			msg.log("Thumbnail of Product: " + name.getText());
		}

		List<WebElement> allPrice = elements("thumbnail_prices");
		for (WebElement price : allPrice) {
			msg.log("Product Price: " + price.getText());
		}
	}

	public void verifyCategoryNameAndParentAssociation() {
		wait.waitForPageToLoadCompletely();
		int size = elements("category_search").size();
		for (int i = 0; i < size; i++) {
			elements("category_search").get(i).isDisplayed();
			msg.log(elements("category_search").get(i).getText());
		}
	}

	public void verifyTheMaxProductCountInSearchPage() {
		wait.waitForLoad();
		isElementDisplayed("list_prodName");
		List<WebElement> prodName = elements("list_prodName");
		int count = 0;
		for (WebElement el : prodName) {
			el.isDisplayed();
			count++;
		}
		msg.log("No of products in search page : " + count);
		assertEquals(count <= 30, true,
				"[Assert Fail]: Number of the products in the search grid page is not as expected");
		msg.log("Number of the products in the search  page is as expected");

		if (count > 30) {
			scrollDownToBottom();
			isElementDisplayed("btn_loadMore");
		}
	}

	public void verifyUserIsNaviagatedToSamePositionInSearchPageWhenClickBackOnPDP(String searchedItem) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("list_prodName");
		List<WebElement> prodNameBeforeNav = elements("list_prodName");
		String frstProdNameBefNav = prodNameBeforeNav.get(1).getText();
		scrollDown(prodNameBeforeNav.get(1));
		clickUsingJS(prodNameBeforeNav.get(1));
		msg.log("Clicked on the first product");
		wait.waitForLoad();
		assertEquals(getCurrentURL().contains(searchedItem), true, "[Assert Fail]: User is not navigated to the PDP");
		msg.log("User is navigated to the PDP");
		// backButton();
		executeJavascript("window.history.back();");
		msg.log("Back button is clicked");
		wait.waitForLoad();
		if (ConfigPropertyReader.getProperty("browser").contains("ios")) {
			scrollWindow(0, 300);
		}

		List<WebElement> prodNameAfterNav = elements("list_prodName");
		String frstProdNameAfterNav = prodNameAfterNav.get(1).getText();
		assertEquals(frstProdNameBefNav, frstProdNameAfterNav, "Position Of the product does not remains same ");
		msg.log("Postion of the product remains same");

	}

	public void verifyLazyLoadingOnSearchPage() {
		wait.waitForPageToLoadCompletely();
		scrollUpToPage();
		isElementDisplayed("categoryTitle");
		int prodCount = Integer.parseInt(element("categoryTitle").getText().replaceAll("\\D+", ""));
		msg.log("No of products in page: " + prodCount);
		if (prodCount > 30) {
			scrollDownToBottom();
			try {
				isElementDisplayed("btn_loadMore");
				clickUsingJS(element("btn_loadMore"));
				msg.log("Load more button is clicked");
				checkLazyLoading();
				scrollDownToBottom();
				isElementNotDisplayed("btn_loadMore");
				msg.log("Verified: Load more button is clickable only once ");
			} catch (Exception e) {
				System.out.println(e);
			}
		}

		else {
			msg.log("Load More button is not visible if product count is less than 30");
		}
	}

	public void verifyHeaderForTheNoResultsPage(String zeroResults) {
		wait.waitForLoad();
		isElementDisplayed("noSearchRsltHeader");
		assertEquals(elements("noSearchRsltHeader").get(0).getText().contains(zeroResults), true, "[Assert Fail]: "
				+ zeroResults + " is not displayed when invalid search term is entered in the search box");
		msg.log(zeroResults + " is displayed when invalid search term is entered in the search box");
	}

	public void verifyNoResultBannerInNoResultsSearchedPage() {
		wait.waitForPageToLoadCompletely();
		if (!ConfigPropertyReader.getProperty("browser").contains("ios")) {
			isElementDisplayed("noResultBanner");
		}

	}

	public void verifyNoResultsTextIsDisplayed() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("text_noResult");
		msg.log("No results text is displayed " + element("text_noResultHelp").getText());
	}

	public void verifyNoResultHelpIsDisplayed() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("text_noResultHelp");
		msg.log("No results help text is displayed " + element("text_noResultHelp").getText());
	}

	public void verifySearchFrom0ResultsSearchPage(String prodName) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("lbl_tryNewSearch");
		msg.log("Label : " + element("lbl_tryNewSearch").getText());
		isElementDisplayed("txtbx_noHitSearch");
		isElementDisplayed("btn_goNoHotSearch");
		sendText(element("txtbx_noHitSearch"), prodName);
		msg.log(prodName + " is entered in the search box");
		clickUsingJS(element("btn_goNoHotSearch"));
		msg.log("Search button is clicked");
	}

	public void searchAProductInMobile(String productName) {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("icon_search");
		element("icon_search").click();
		msg.log("Search Icon is clicked");
		element("textbox_Search").sendKeys(productName);
		msg.log("User entered product in search Box : " + productName);
		executeJavascript("$('.header-search .submit-search').is(':visible')");
		executeJavascript("$('.header-search .submit-search').click()");
		msg.log("Go button is clicked");

	}

	public void verifySearchBxFunctionality(String searchBxPlacehldr) {
		wait.waitForPageToLoadCompletely();

		isElementDisplayed("icon_search");
		element("icon_search").click();
		msg.log("Search Icon is clicked");

		isElementDisplayed("textbox_Search");
		if (getCurrentURL().contains("ksEuFr") || getCurrentURL().contains("fr")) {
			hardWait(1);
			String txtbxPlaceholder = executeJavascriptReturnsString(
					"return $('.search-placeholder-wrapper .katesearchval')[0].getAttribute('placeholder')");
			msg.log("str1..............." + txtbxPlaceholder);
			msg.log("str2..............." + searchBxPlacehldr);
			assertEquals(txtbxPlaceholder.contains(searchBxPlacehldr), true,
					"[Assert Fail]: Search box placeholder does not match");
			msg.log("Verified : Search box placeholder match : " + searchBxPlacehldr);
		} else {
			assertEquals(element("textbox_Search").getAttribute("placeholder"), searchBxPlacehldr,
					"[Assert Fail]: Search box placeholder does not match");
			msg.log("Verified : Search box placeholder match : " + searchBxPlacehldr);
		}

		sendText(element("textbox_Search"), "bags");

		if (ConfigPropertyReader.getProperty("browser").startsWith("ios")) {
			isElementDisplayed("btn_go");
			clickUsingJS(element("btn_go"));
			msg.log("User clicked on go button");

			wait.waitForLoad();
			wait.waitForElementToDisappear(element("btn_go"));

			assertEquals(getCurrentURL().contains("Search") && getCurrentURL().contains("bags"), true,
					"[ASSERTION FAILED]: User is not able to search by clicking Enter button in search box");
			msg.pass("User is able to search by clicking 'Enter' button in search box");
		} else {
			element("textbox_Search").sendKeys(Keys.ENTER);

			msg.log("Actual Result: " + getCurrentURL());
			msg.log("Expected Search Result (contains): 'Search' & 'bags'");

			if (getCurrentURL().contains("staging")) {
				Assert.assertTrue(getCurrentURL().contains("bags"),
						"[ASSERTION FAILED]: Current URL DOES NOT have 'bags' search term !!!");
			} else {
				Assert.assertTrue(getCurrentURL().contains("Search"),
						"[ASSERTION FAILED]: User is NOT on SEARCH page !!!");
				Assert.assertTrue(getCurrentURL().contains("bags"),
						"[ASSERTION FAILED]: Current URL DOES NOT have 'bags' search term !!!");
			}
			msg.pass("User is able to search by clicking Enter button in search box");
		}
	}

	public void verifySearchResultBannerInSearchPage() {
		wait.waitForPageToLoadCompletely();
		isElementDisplayed("searchResultBanner");
		msg.log("Content Slot search result banner is displayed : " + element("searchResultBanner").getText());
	}

}
