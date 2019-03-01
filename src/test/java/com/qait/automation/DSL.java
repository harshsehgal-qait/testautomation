package com.qait.automation;

import org.jasypt.util.text.BasicTextEncryptor;
import org.testng.ITestResult;

public class DSL extends TestSessionInitiator {

	public DSL(String testname) {
		super(testname);
	}

	public DSL(String testname, String browserName) {
		super(testname, browserName);
	}

	public void launchApplication(String url) {
		topBanner.launchSpecificUrl(url);
	}

	static final String SECRET_KEY = "ABCDEFabcdef123456";
	static String productName = "";

	public static String encrypt(String data) {
		BasicTextEncryptor encryptor = new BasicTextEncryptor();
		encryptor.setPassword(SECRET_KEY);
		return encryptor.encrypt(data);
	}

	public static String decrypt(String data) {
		try {
			BasicTextEncryptor encryptor = new BasicTextEncryptor();
			encryptor.setPassword(SECRET_KEY);
			return encryptor.decrypt(data);
		} catch (Exception e) {
			return data;
		}
	}

	public void loginAsRegisteredUser(String userName, String password) {
		topBanner.clickOnSignINRegister().loginWithRegisteredUser(userName, password);
	}

	public void loginWithRegisteredUser(String userName, String password) {
		accountPage.loginWithRegisteredUser(userName, password);
	}

	public void searchProduct(String product) {
		search.verifyIfProductIsAvailable(product);
	}

	public void takeScreenShotOnException(ITestResult result) {
		takescreenshot.takeScreenShotOnException(result);
	}

	public void selectFirstProduct() {
		shopGridPage.selectFirstProduct();
	}

	public void addTheProductToBag() {
		productdetails.Add_To_Bag();
	}

	public void enterShipingDetails(String guestMailId, String fname, String lname, String address, String zipcode,
			String city, String phno) {
		shipngpage.enterShipingDetails(guestMailId, fname, lname, address, zipcode, city, phno);
	}

	public void makePaymentFromMasterCard(String cardno, String expiryDate, String cardType, String cvv) {
		paymentpage.userSelectsCardType(cardType);
		paymentpage.user_enter_card_details(cardno, expiryDate, cardType, cvv);
	//	paymentpage.user_submit_payment();
	}
	
	public void markSameAsBillingAndClickSubmit() {
		paymentpage.user_submit_payment();
	}

	public void mobile_loginAsRegisteredUser(String userName, String password) {
		accountPage.mobile_loginWithRegisteredUser(userName, password);
	}

	public void verifyUserIsOnShippingPage() {
		shipngpage.verifyUserIsOnShippingPage();
	}

	public void submitYourOrder() {
		paymentpage.submitYourOrder();
	}

	public void orderConfirmation() {
		ordereviewpage.orderConfirmation();
	}

	public void verifyAccountLablesOnHover() {
		homeheader.verifyAccountLablesOnHover();
	}

	public void clickAndVerifyMobileAccountLables() {
		homeheader.verifyMobileAccountLablesOnHover();
	}

	public void clickOnMyAccount(String account) {
		homeheader.clickOnMyAccount(account);
	}

	public void verifyAccountPageHolderName() {
		accountPage.verifyAccountPageHolderName();
	}

	public void verifyAccountPageNames() {
		accountPage.verifyAccountPageNames();
	}
	
	public void clickOnPersonalData(String personalAccount) {
		accountPage.clickOnPersonalData(personalAccount);
	}

	public void verifyPersonalDataPage() {
		accountPage.verifyPersonalDataPage();
	}

	public void verifyEditInfoTab(String password) {
		accountPage.verifyEditInfoTab(password);
	}

	public void clickAndVerifyOnAddressBook(String addressBook) {
		accountPage.clickAndVerifyOnAddressBook(addressBook);
	}

	public void clickAndVerifyOnOrderHistory(String orderHistory) {
		accountPage.clickAndVerifyOnOrderHistory(orderHistory);
	}

	public void clickAndVerifyOnGiftRegistry(String giftRegistry) {
		accountPage.clickAndVerifyOnGiftRegistry(giftRegistry);
	}

	public void mobile_logoutFromApplication() {
		topBanner.openHamBurgerNavigation();
		accountPage.mobile_logoutFromApplication();
	}

	public void logoutFromApplicationMobile() {
		accountPage.logoutFromApplicationOnMobile();
	}

	public void navigateToHandbagFromLandingPage(String handbag) {
		homeheader.navigateToHandbagFromLandingPage(handbag);
	}

	public void navigateToHandbagFromLandingPageOnMobile(String handbag) {
		homeheader.navigateToHandbagFromLandingPageOnMobile(handbag);
	}

	public void verifyUserIsOnHandBagPage() {
		shopGridPage.verifyUserIsOnHandBagPage();
	}

	public void navigateToNewViewAllShop() {
		homeheader.navigateToNewViewAllShop();
	}

	public void navigateToClothingViewAllShop() {
		homeheader.navigateToClothingViewAllShop();
	}

	public void verifyQuickShopFunctioanlity() {
		shopGridPage.verifyQuickShopFunctioanlity();
	}

	public void verifyQuickBuyPage() {
		shopGridPage.verifyQuickBuyPage();
	}

	public void clickOnQuickByAddToBag() {
		shopGridPage.clickOnQuickByAddToBag();
	}

	public void verifyMoreThenThreeSwatches() {
		shopGridPage.verifyMoreThenThreeSwatches();
	}

	public void verifyQuickBuyIcon() {
		shopGridPage.verifyQuickViewIconOnProductTiles();
	}

	public void verifyBreadCrumbIcon() {
		shopGridPage.verifyBreadCrumOnShopGrid();
	}

	public void verifyProductDetailPage() {
		productdetails.verifyProductDetailPage();
	}

	public void navigateToSatchelsUnderHandbag(String satchels) {
		landingPage.navigateToSatchelsUnderHandbag(satchels);
	}

	public void navigateToSatchelsUnderHandbagOnMobile(String satchels) {
		landingPage.navigateToSatchelsUnderHandbagOnMobile(satchels);
	}

	public void verifyUserIsOnSatchelsPage() {
		shopGridPage.verifyUserIsOnSatchelsPage();
	}

	public void navigateToLeftNavigation() {
		shopGridPage.navigateToLeftNavigation();
	}

	public void navigateToLeftNavigationOnMobile() {
		shopGridPage.navigateToLeftNavigationOnMobile();
	}

	public void verifyQuantityDropDown() {
		minicartpage.verifyQuantityDropDown();
	}

	public void verifyBagIconFunctionalityInStickyHeader() {
		homeheader.verifyBagIconFunctionalityInStickyHeader();
		minicartpage.verifyStickyBagIconFunctionality();
	}

	public void verifyDetailsOfProductsPresentInMiniCartPage() {
		minicartpage.verifyDetailsOfProductsPresentInMiniCartPage();
	}

	public void verifyCartPage() {
		minicartpage.verifyCartPage();
	}

	public void verifyEditLinkFunctionality() {
		minicartpage.verifyEditLinkFunctionality();
	}

	public void verifySaveForLaterFuctionality() {
		minicartpage.verifySaveForLaterFuctionality();
	}

	public void verifyRemoveFunctionality() {
		minicartpage.verifyRemoveFunctionality();
	}

	public void checkoutFromMiniCartWindow() {
		minicartpage.checkoutFromMiniCart();
	}

	public void verifyShoppingCartPage() {
		shoppingCart.verifyShoppingCartPage();
	}

	public void verifyProductDetailsOnShoppingCartPage() {
		shoppingCart.verifyProductDetailOnCartPage();
	}

	public void checkoutFromCartPage() {
		shoppingCart.clickOnCheckoutButton();
	}

	public void serachProductWithAKeyword(String string) {
		search.serachProductWithAKeyword(string);
	}

	public void verifyThePredictiveResults() {
		search.verifyOnEntering3LettersSuggestionsAppears();
		search.verifyThePredictiveResults();
		search.verifyThumbnailImagesProductNameAndPrice();
	}

	public void clickOnFirstElementFromPredectiveResult() {
		search.clickOnFirstElementFromPredectiveResult();
	}

	public void verifySearchResultPage(String name) {
		shopGridPage.verifySearchResultPage(name);
	}

	public void verifyLandingPage() {
		landingPage.verifyLandingPage();
	}

	public void verifyTopNavigationBar() {
		topBanner.verifyTopNavigationBar();
	}

	public void navigateToMobileSignInPage() {
		topBanner.navigateToMobileSignInPage();
	}

	public void navigateToMobileRegisterAccountPage() {
		topBanner.navigateToMobileRegisterAccountPage();
	}

	public void verifySingInRegisterLinkWhenUserLoggedIn() {
		topBanner.verifySingInRegisterLinkWhenUserLoggedIn();
	}

	public void navigateToWishListFromTopNavigationOnMobile() {
		topBanner.navigateToWishListFromTopNavigationOnMobile();
	}

	public void verifyMobileCategoryNavigation() {
		topBanner.verifyMobileCategoryNavigations();
	}

	public void verifyMobileTopNavigationSubCategory() {
		topBanner.verifySubCategoryForMobile();
		shopGridPage.verifyShopHeader();
	}

	public void openHamburgerMenu() {
		topBanner.openHamBurgerNavigation();
	}

	public void verifyUserIsOnSingInPage() {
		accountPage.verifyUserIsOnSingInPage();
	}

	public void veriyUserIsOnRegisterAccountPageOnMobile() {
		accountPage.veriyUserIsOnRegisterAccountPageOnMobile();
	}

	public void verifyTopBannerWhenUserNotLoggedIn() {
		topBanner.verifyTopBannerWhenUserNotLoggedIn();
	}

	public void verifyTopBannerWhenUserLoggedIn() {
		topBanner.verifyTopBannerWhenUserLoggedIn();
	}

	public void closeHamburgerMenu() {
		topBanner.closeHamburgerMenuOptions();
	}

	public void verifySearchDisplayed() {
		search.verifySearchDisplayed();
	}

	public void verifyCountryToggleIcon() {
		landingPage.verifyCountryToggle();
	}

	public void clickOnCountryToggle() {
		countrySelection.clickOnCountryToggle();
	}

	public void utilityNavigationBar() {
		topBanner.utilityNavigationBar();
	}

	public void mobile_utilityNavigationBar() {
		topBanner.mobile_utilityNavigationBar();
	}

	public void verifySignInLink() {
		landingPage.verifySignInLink();
	}

	public void verifyCountrySelectorLink() {
		landingPage.verifyCountrySelectorLink();
	}

	public void verifyCountryLinks() {
		countrySelection.verifyCountryLinks();
	}

	public void verifyStoreLocatoreIcon() {
		topBanner.verifyStoreLocatoreIcon();
	}

	public void verifyHelpLink() {
		topBanner.verifyHelpLink();
	}

	public void verifyKatespadeSpadeLogo() {
		topBanner.verifyKatespadeSpadeLogo();
	}

	public void verifySearchIconIsDisplayed() {
		topBanner.verifySearchIconIsDisplayed();
	}

	public void verifyFooterSection() {
		footer.verifyFooterSection();
	}

	public void scrollDownToBottom() {
		landingPage.scrollDownToBottom();
	}

	public void scrollUpToPage() {
		landingPage.scrollUpToPage();
	}

	public void navigate_To_Registeration_Page_From_Landing_Page() {
		topBanner.clickOnSignINRegister();
	}

	public void registerANewUser() {
		accountPage.clickOnRegisterButton();
		accountPage.enterRegisterInformation();
	}

	public void logOutFromTheApplication() {
		topBanner.logoutFromTheApplication();
	}

	public void verifyUserAccountMenuTabOptions() {
		accountPage.verifyAccountMenuTabOptions();
	}

	public void userGoesToEditAccountPage() {
		accountPage.goToEditAccountPage();
	}

	public void verifySavedAddressOnAccountPage() {
		accountPage.verifiedSavedAddresss();
	}

	public void editNameinAccountSetting(String existin_password) {
		String decriptedPin = decrypt(existin_password);
		accountPage.editNameAccountInformation(decriptedPin);
	}

	public void addAddressInAccountSettings(String addressName, String firstName, String lastName, String postalCode,
			String phoneNumber, String address1, String city) {
		accountPage.goToAddressBook();
		accountPage.addAddressInAddressBook(addressName, firstName, lastName, postalCode, phoneNumber, address1, city);
	}

	public void mobile_addAddressInAccountSettings(String addressName, String firstName, String lastName,
			String postalCode, String phoneNumber, String address1, String city) {
		accountPage.mobile_goToAddressBook();
		accountPage.addAddressInAddressBook(addressName, firstName, lastName, postalCode, phoneNumber, address1, city);
	}

	public void verifySignInFromFooter() {
		footer.verifySignInFromFooter();
	}

	public void verifyPrimaryImageAndProductThumbnail() {
		productdetails.verifyPrimaryImageAndProductThumbnail();
		productdetails.verifyTheMaxLimitOfThumbnails();
	}

	public void verifyPrimaryImageAndProductThumbnailInMobile() {
		productdetails.verifyPrimarImageInMobile();
	}

	public void verifyDetailsOfProductInPDPPage() {
		productdetails.verifyDetailsOfProductInPDPPage();
		productdetails.verifyColorAppearsOnHoveringOverTheColorSwatches();
	}

	public void verifySocialSitesIcons() {
		productdetails.verifySocialSitesIcons();
	}

	public void verifyBreadCrumbs() {
		productdetails.verifyBreadCrumbs();
	}

	public void verifyStoreLocatorIconInHomepage() {
		landingPage.verifyStoreLocatorIcon();
	}

	public void navigateToStoreLocatorPage() {
		landingPage.navigateToStoreLocatorPage();
	}
	
	public void verifyDetailsOfStoreLocatorPage(String Text_placeholder, String TextInPlaceholderFr) {
		storeLocator.verifyDetailsOfStoreLocatorPage(Text_placeholder, TextInPlaceholderFr);
	}

	public void verifyUserIsAbleToSearchStore(String postalCode) {
		storeLocator.searchStore(postalCode);
		storeLocator.verifyDetailsOfStoresSearchedPage();
	}

	public void navigateBackToKateSpadeHomePage() {
		homeheader.navigateToKateSpadeHomePage();
	}

	public void verifyBagIconForMobile() {
		homeheader.verifyBagIconForMobile();
	}

	public void verifyAndHoverAtHeaderLinks() {
		homeheader.verifyAndHoverAtHeaderLinks();
	}

	public void verifySubcategoriesOfHeaderLinks() {
		homeheader.verifySubcategoriesOfHeaderLinks();
	}

	public void verifyFooterLinks() {
		footer.verifyAboutUsFooterLinks();
	}

	public void verifyAboutUsFooterLinks() {
		footer.verifyAboutUsFooterLinks();
	}

	public void verifyCustomerCareFooterLinks() {
		footer.verifyCustomerCareFooterLinks();
	}

	public void verifyCareersFooterLinks() {
		footer.verifyCareersFooterLinks();
	}

	public void verifyMyAccountFooterLinks() {
		footer.verifyMyAccountFooterLinks();
	}

	public void goToYourOrderFromFooter() {
		footer.goToYourOrderFromFooter();
	}

	public void verifyandClickOnOrderHistory() {
		accountPage.verifyandClickOnOrderHistory();
	}

	public void navigateToOrderHistoryPage() {
		accountPage.navigateToOrderHistoryPage();
	}

	public void verfifyUserlandsOnOrderHistoryPage() {
		accountPage.verfifyUserlandsOnOrderHistoryPage();
	}

	public void verifyOrderNumberInOrderHistory(String orderNum) {
		accountPage.verifyOrderNumberInOrderHistory(orderNum);
	}

	public void verifyOrderDetailsInOrderHistory(String OrderDate, String OrderStatus, String OrderNum, String TrackNum,
			String OrderAdd, String Items, String Total) {
		accountPage.verifyOrderDetailsInOrderHistory(OrderDate, OrderStatus, OrderNum, TrackNum, OrderAdd, Items,
				Total);
	}

	public void verifyMegaMenuIsDisplayed() {
		homeheader.verifyMegaMenuIsDisplayed();
	}

	public void verifyTopLevelCategoriesColorAndMegaMenu() {
		homeheader.verifyTopLevelCategoriesColorAndMegaMenu();
	}

	public void verifyOnClickingCategoryUserLandsOnCorrectPage() {
		homeheader.verifyOnClickingCategoryUserLandsOnCorrectPage();
	}

	public void verifyMegaMenuElements() {
		homeheader.verifyMegaMenuElements();
	}

	public void verifyWishlistCanBeAccessdedFromHeader() {
		landingPage.verifyWishlistCanBeAccessdedFromHeader();
	}

	public void verifyWishlistCanBeAccessdedFromFooter() {
		footer.verifyWishlistCanBeAccessdedFromFooter();
	}

	public void verifyUserLandsOnWishlistPage() {
		wishlistpage.verifyUserLandsOnWishlistPage();
	}

	public void verfifyUserIsNotOnWishlistSignInPage() {
		wishlistpage.verfifyUserIsNotOnWishlistSignInPage();
	}

	public void selectAndClickOnSwatch() {
		productdetails.selectAndClickOnSwatch();
	}

	public String verifyWishlistCanBeAccessdedFromPDP() {
		return productdetails.verifyWishlistCanBeAccessdedFromPDP();
	}

	public void verifyWishlistCanBeAccessdedMyAccount() {
		accountPage.verifyWishlistCanBeAccessdedMyAccount();
	}

	public void verifyUserLandsOnSighInPage() {
		wishlistpage.verifyUserLandsOnSighInPage();
	}

	public void verifyProductAddedInWishList(String skuValue) {
		wishlistpage.verifyProductAddedInWishList(skuValue);
	}

	public void verifyProductNameOnWishlistPage() {
		wishlistpage.verifyProductNameOnWishlistPage();
	}

	public void verifyUserlandsOnHomePageOnClickingKateSpadeLogo(String homepageURL) {
		landingPage.verifyUserlandsOnHomePageOnClickingKateSpadeLogo(homepageURL);
	}

	public void verifyImageQuantityAndAddtoBagOnWishlistPage() {
		wishlistpage.verifyImageQuantityAndAddtoBagOnWishlistPage();
	}

	public void verifyProductDetailsOnWishlistPage() {
		wishlistpage.verifyProductDetailsOnWishlistPage();
	}

	public void verifyRemoveBtnOnWishlistPage() {
		wishlistpage.verifyRemoveBtnOnWishlistPage();
	}

	public void verifySideNavOnWishlistPage() {
		wishlistpage.verifySideNavOnWishlistPage();
	}

	public void createAnAccountOnWishlistPageIfUserIsnotRegisterd() {
		wishlistpage.clickOnRegisterButton();
		wishlistpage.enterRegisterInformation();
	}

	public void verifyTheAddressDetailsOfStoreOnStoreDetailPage() {
		storeLocator.verifyTheAddressDetailsOfStoreOnStoreDetailPage();
	}

	public void verifyFullWidthExperienceOfHeader() {
		homeheader.verifyFullWidthExperienceOfHeader();
	}

	public void verifyTheCompanyLogoIsDisplayedAndLinkToHeader(String homepageURL) {
		homeheader.verifyKateSpadeLogoIsPresent();
		homeheader.verifyKateSpadeLogoIsLinkedToHomapage(homepageURL);
	}

	public void verifyCountryAndItsCurrencySymbol(String countryNameAndCurrency) {
		homeheader.verifyCountryAndItsCurrencySymbol(countryNameAndCurrency);
	}

	public void verifySignInAndRegisterLink(String userName, String password) {
		homeheader.verifySignInAndRegisterLink();
		topBanner.clickOnSignINRegister().loginWithRegisteredUser(userName, password);
		homeheader.verifyLogOutLink();
	}

	public void verifyWishlistLinkAndIcon() {
		homeheader.verifyWishlistLinkAndIcon();
	}

	public void verifyMiniCartWindow() {
		minicartpage.verifyMiniCartWindow();
	}

	public void verifyMiniCartInMobile() {
		minicartpage.verifyMiniCartInMobile();
	}

	public void verifySearchtextAndIcon() {
		homeheader.verifySearchTextAndIconFunctionality();
	}

	public void verifyMaxWidthOfHeader() {
		homeheader.verifyMaxWidthOfHeader();
	}

	public void verifyWidthOfStickyHeader() throws InterruptedException {
		scrollDownToBottom();
		homeheader.verifyWidthOfStickyHeader();
	}

	public void verifyStickyKSLogoItsLinkingToHomepage(String homepageURL) {
		scrollDownToBottom();
		homeheader.verifyStickyKSLogoAndItsLinkingToHomepage(homepageURL);
	}

	public void verifySignInAndRegisterLinkInStickyHeader(String userName, String password) {
		scrollDownToBottom();
		homeheader.verifySignInAndRegisterIcon();
		scrollUpToPage();
		topBanner.clickOnSignINRegister().loginWithRegisteredUser(userName, password);
		scrollDownToBottom();
		homeheader.verifyAccountLinksWhenUserIsLoggedIn();
	}

	public void verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist() {
		productdetails.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
	}

	public void verifyUserIsAbleToAddProductToWishlistWhenLoggedIn(String userName, String password) {
		topBanner.clickOnSignINRegister();
		accountPage.loginWithRegisteredUser(userName, password);
		wishlistpage.verifyUserIsNavigatedToWislistPage();
	}

	public void verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn() {
		productdetails.verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn();
	}

	public void verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn(String product) {
		search.verifyIfProductIsAvailable(product);
		shopGridPage.selectFirstProduct();
		productdetails.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
		wishlistpage.verifyUserIsNavigatedToWislistPage();
	}

	public void verifyTheGreenColorOfTheButtonAfterSelectingAttribute() {
		productdetails.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
		productdetails.verifyGreenColorOfAddToBagButton();
	}

	public void verifyUserIsAbleToAddProductToMiniCart() {
		productdetails.clickOnAddProductToBag();
		minicartpage.verifyMiniCartWindow();
	}

	public void verifyTheHeaderOfTheRecommendationSection() {
		productdetails.verifyTheHeaderOfTheRecommendationHeaderSection();
	}

	public void verifyTheMaxLimitOfRecommendedItemsAndProductTiles() {
		productdetails.verifyTheMaxLimitOfRecommendedItems();
		productdetails.verifyProductIsDisplayedAsTiles();
	}

	public void verifyQuickViewShopButtonShouldNotVisibleOnPDPPage() {
		productdetails.verifyQuickViewShopButtonShouldNotVisibleOnPDPPage();
	}

	public void verifyColorChangeOfTheRecommendedProductName() {
		productdetails.verifyColorChangeOfRecommendedProductName();
	}

	public void selectProductFromRecommendedListAndVerifyInPDPPage() {
		productdetails.selectProductFromRecommendedListAndVerifyInPDPPage();
	}

	public void verifyTheAlignmentOfTheRecommendedProductImage() {
		productdetails.verifyTheAlignmentOfTheRecommendedProductImage();
	}

	public void verifyTheDetailsOfMostRecentlyViewedSection(String recentlyViewedHeader) {
		productdetails.verifyMostRecentlyViewedSection(recentlyViewedHeader);
		productdetails.verifyTheMaxLimitOfPreviouslyViewedItems();
		productdetails.verifyQuickViewShopButtonShouldNotVisibleOnPDPPage();
		productdetails.verifyColorChangeOfRecentlyViewedProductName();
	}

	public void verifyWriteAReviewIconOnPdp() {
		productdetails.verifyProductReviewOnPdpPage();
	}

	public void navigateToReviewPage() {
		productdetails.navigateToReviewPage();
	}

	public void verifyTheAlignmentOfTheRecentlyViewdProductImage() {
		productdetails.verifyTheAlignmentOfTheRecentlyViewdProductImage();
	}

	public void selectProductFromMostRecentlyViewdListAndVerifyInPDPPage() {
		productdetails.selectProductFromMostRecentlyViewdListAndVerifyInPDPPage();
	}

	public void verifyPromotionalMessageForAProduct(String promotionalMessage) {
		productdetails.verifyThatPromotionalMessageIsDisplayedForTheProduct(promotionalMessage);
	}

	public void verifyThePricingStructureOfProduct() {
		productdetails.verifyThePricingStructureOfProduct();

	}

	public void verifyThatSizeSwatchesIsPresentForTheProduct() {
		productdetails.verifyThatSizeSwatchesIsPresentForTheProduct();

	}

	public void verifyPromotionalAndMarketingMaterialIsDisplayedInPDP() {
		productdetails.verifyPromotionalAndMarketingMaterialIsDisplayedInPDP();
	}

	public void verifyCategoryNameAndParentAssociation() {
		search.verifyCategoryNameAndParentAssociation();
	}

	public void verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePage() {
		shopGridPage.verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePage();
	}

	public void verifyInventoryMessagingIsPresentOnPDPPage() {
		productdetails.verifyUserIsAbleToChooseAttributesBeforeAddingToWishlist();
		// productdetails.verifyInventoryMessagingIsPresentOnPDPPage();
	}

	public void verifyPromotionalMessagingBarAppearsOnHeader() {
		homeheader.verifyPromotionalMessagingBarAppearsOnHeader();

	}

	public void verifyStoreLocatorIcon() {
		homeheader.verifyStoreLocatorIcon();
	}

	public void verifyUserIsAbleToAddAllProductsToWishlistDirectlyWhenAlreadyLoggedIn(String product) {
		search.verifyIfProductIsAvailable(product);
		productdetails.verifyDetailsOfPDPPageWhenProductSetItemIsSearched();
		productdetails.verifyUserIsAbleToAddProductToWishlistDirectlyWhenAlreadyLoggedIn();
		wishlistpage.verifyAllProductsAddedToWishlist();

	}

	public void verifyDetailsOfPDPPageWhenProductSetItemIsSearched() {
		productdetails.verifyDetailsOfPDPPageWhenProductSetItemIsSearched();

	}

	public void verifyOnClickingProductNameFromProductSetUserWillBeNavigatedToItsPDPPage() {
		productdetails.verifyOnClickingProductNameFromProductSetUserWillBeNavigatedToItsPDPPage();

	}

	public void verifyThePricingStructureOfProductSet() {
		productdetails.verifyThePricingStructureOfProductSet();

	}

	public void verifyTheMainProductSetImageAndAlternateProductImageIsDisplayed() {
		productdetails.verifyTheMainProductSetImageAndAlternateProductImageIsDisplayed();

	}

	public void verifyTheQuantityDropdownSelected(String drpDownSelected) {
		productdetails.verifyTheQuantityDropdownSelected(drpDownSelected);
	}

	public void removeTheProductAddedFromWishlist() {
		wishlistpage.removeTheProductAddedFromWishlist();

	}

	public void verifyComapnyLinkNameInFooter() {
		footer.verifyComapnyLinkNameInFooter();

	}

	public void verifyComapnyLinksAndThePageThatTheyNavigateTo() {
		footer.verifyComapnyLinksAndThePageThatTheyNavigateTo();

	}

	public void verifySocialMediaIcons() {
		footer.verifySocialMediaIcons();

	}

	public void verifyHeadersDisplaySigninAndRegistration() {
		landingPage.verifyHeadersDisplaySigninAndRegistration();

	}

	public void verifyEmailSignUpFunctionalityInFooter() {
		footer.verifyEmailSignUpElementsInFooter();
		footer.verifyTheValidationsInJoinMailingList();
	}

	public void verifyFooterCompanyLinks() {
		footer.verifyComapnyLinkNameInFooter();

	}

	public void verifyStoreDetailsAndStoreDirections(String pageName) {
		storeLocator.verifyDetailsOnSearchPageOnMapAndDetailsWhenStoreDetailsIsClicked(pageName);
		storeLocator.verifyDetailsWhenGetDirectionsIsClickedOnStoreDetailsPage();
		storeLocator.verifyAddressDetailsDisplayedOnMapOnStoreDetailPage();
	}

	public void verifyUserIsAbleToAddProductToBag() {
		productdetails.clickOnAddProductToBag();
	}

	public void verifyGlobalBannerSlotIsPresentOnBagPage() {
		shoppingCart.verifyGlobalSlotBannerIsPresentOnBagPage();
	}

	public void verifyThePricingStructureOfOrderTotalInShoppingPage() {
		shoppingCart.verifyThePricingStructureOfOrderTotalInShoppingPage();
	}

	public void verifyThatCustomerServiceTelephoneNumberIsDisplayed() {
		shoppingCart.verifyThatCustomerServiceTelephoneNumberIsDisplayed();
	}

	public void verifyAfterClickingCheckoutBtnInShopngPageUserNavigatedToShipngPage() {
		shoppingCart.clickOnCheckoutButton();
		shipngpage.verifyUserIsOnShippingPage();
	}

	public void verifyTheInformationOnOrderSummaryBox() {
		shoppingCart.verifyTheInformationOnOrderSummaryBox();
	}

	public void verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(String homepageURL) {
		homeheader.verifySimplifiedHeaderKSLogoAndItsLinkingToHomepage(homepageURL);
	}

	public void clickOnSignInLink() {
		landingPage.clickOnSignInLink();
	}

	public void validateThatOnClickSignInLinkTakesUserToMyAccountLandingLoginPage() {
		accountPage.validateThatOnClickSignInLinkTakesUserToMyAccountLandingLoginPage();
	}

	public void validateThatOnClickRegisterLinkTakesUserToLoginCreateAccountLandingPage() {
		accountPage.validateThatOnClickRegisterLinkTakesUserToLoginCreateAccountLandingPage();
	}

	public void clickOnRegisterButton() {
		accountPage.clickOnRegisterButton();
	}

	public void clickOnRegisterUserSignInNowButtonAndVerifyFieldsForLogin() {
		shipngpage.clickOnRegisterUserSignInNowButtonAndVerifyFieldsForLogin();
	}

	public void verifyForgotPasswordModalOpensOnClickingForgotPasswordLink() {
		shipngpage.verifyForgotPasswordModalOpensOnClickingForgotPasswordLink();
	}

	public void loginToAccountDuringCheckoutFlow(String emailAddress, String password) {
		shipngpage.loginToAccountDuringCheckoutFlow(emailAddress, password);
	}

	public void verifyShippingPageAfterSucessfulLogin() {
		shipngpage.verifyShippingPageAfterSucessfulLogin();

	}

	public void verifyThatRegisteredUserIsAbleToClickAndSelectFirstSavedAddress() {
		shipngpage.verifyRegisteredUserIsAbleToClickAndSelectFirstSavedAddress();

	}

	public void verifyLoginFieldsAreNotVisibleInShippingPage() {
		shipngpage.verifyLoginFieldsAreNotVisibleInShipingPage();

	}

	public void verifyThatShippingFieldsArePrePopulated(String firstName, String lastName, String phoneNo) {
		shipngpage.verifyThatShippingFieldsArePrePopulated(firstName, lastName, phoneNo);

	}

	public void verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage() {
		homeheader.verifyWishlistIconIsNotDisplayedInSimplifiedHeaderAtShippingPage();

	}

	public void verifyThatStepFooterIsDisplayed() {
		shipngpage.verifyThatStepFooterIsDisplayed();
	}

	public void verifyEmailANdPasswordAreRequiredToLogin() {
		accountPage.verifyEmailANdPasswordAreRequiredToLogin();
	}

	public void verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen() {
		accountPage.verifyAfterEnteringValidCredentialsLogInDisplayesHiNameOfUserAndMyAccountPageIsOpen();
	}

	public void verifyOnEnteringWrongCredentialsErrorMessageAppears(String username, String password, String text) {
		accountPage.verifyOnEnteringWrongCredentialsErrorMessageAppears(username, password, text);

	}

	public void verifyForgotPasswordLink(String username) {
		accountPage.verifyForgotPasswordLink(username);
	}

	public void verifyOrderHistoryPage() {
		orderHistoryPage.verifyOrderHistoryPage();
	}

	public void verifyStep2BillingIsActive() {
		paymentpage.verifyStep2BillingIsActive();
	}

	public void verifyUsersCanNavigateBackToPreviouslyCompletedCheckoutSteps() {
		shipngpage.verifyUserCanNavigateBackFromBillingPageToShipppingPage();
	}

	public void verifyContinueToPaymentButtonIsDisabledInShippingPage() {
		shipngpage.verifyContinueToPaymentButtonIsDisabledInShippingPage();
	}

	public void mobile_verifyContinueToPaymentButtonIsDisabledInShippingPage() {
		shipngpage.mobile_verifyContinueToPaymentButtonIsDisabledInShippingPage();
	}

	public void clickContinuePaymentButton() {
		shipngpage.clickContinuePaymentButton();
	}

	public void verifyRadioButtonsAndTheirCountForShippingMethodsAreDisplayed(int shippingMethodCount) {
		shipngpage.verifyRadioButtonsAndTheirCountForShippingMethodsAreDisplayed(shippingMethodCount);
	}

	public void verifyOrderSummaryIsUpdatedAccordingToTheShippingMethod() {
		shipngpage.verifyOrderSummaryIsUpdatedAccordingToTheShippingMethod();

	}

	public void clickUseThisAddressForBillingCheckboxInShippingPage() {
		shipngpage.clickUseThisAddressForBillingCheckboxInShippingPage();
	}

	public void verifyFieldsFromShippingPageArePopulatedIntoRelevantFieldsOfBillingPage(String fname, String lname,
			String postalCode, String phoneNo) {
		paymentpage.verifyFieldsFromShippingPageArePopulatedIntoRelevantFieldsOfBillingPage(fname, lname, postalCode,
				phoneNo);
	}

	public void clickSameAsShippingAddressCheckboxOnBillingPage() {
		paymentpage.clickSameAsShippingAddressCheckboxOnBillingPage();
	}

	public void verifyContinueToPlaceOrderButtonIsDisabledInBillingPage() {
		paymentpage.verifyContinueToPlaceOrderButtonIsDisabledInBillingPage();
	}

	public void uncheckUseThisAddressForBillingAndClickContinueToBillingBtn() {
		shipngpage.clickUseThisAddressForBillingCheckboxInShippingPage();
		shipngpage.clickContinuePaymentButton();
	}

	public void verifyFieldsOfBillingPageAreEmpty() {
		paymentpage.verifyFieldsOfBillingPageAreEmpty();
	}
 
	public void enterBillingAddressDetails(String fname, String lname, String address, String zipcode, String phno , String city) {
		paymentpage.enterBillingAddressDetails(fname, lname, address, zipcode, phno , city);
	}

	public void verifyUseThisAddressForBillingIsByDefaultNotSelected() {
		shipngpage.verifyUseThisAddressForBillingIsByDefaultNotSelected();
	}

	public void verifyErrorMessageDisplayedForRequiredFields() {
		paymentpage.clickSameAsShippingAddressCheckboxOnBillingPage();
		paymentpage.verifyErrorMessageDisplayedForRequiredFields();

	}

	public void selectFirstSavedCardInTheAccount(String cardName) {
		paymentpage.selectFirstSavedCardInTheAccount(cardName);

	}

	public void verifyFieldsOfBillingPageAreNotEmpty() {
		paymentpage.verifyFieldsOfBillingPageAreNotEmpty();
	}

	public void clickOnaddressButton() {
		accountPage.clickOnAddNewAddress();

	}

	public void verifyUserLandsOnAddBookPageOnClickingCancleBtn() {
		accountPage.verifyUserLandsOnAddBookPageOnClickingCancleBtn();

	}

	public void verifyUserLandsOnAddBookPageOnClickingXBtn() {
		accountPage.verifyUserLandsOnAddBookPageOnClickingXBtn();

	}

	public void verifyUserIsAbleToEditAddress(String firstName) {
		accountPage.verifyUserIsAbleToEditAddress(firstName);

	}

	public void verifyUserIsAbleToUpdateAddress() {
		accountPage.verifyUserIsAbleToUpdateAddress();

	}

	public void verifyUserIsAbleToDeleteAddress() {
		accountPage.verifyUserIsAbleToDeleteAddress();

	}

	public void verifyUserIsAbleTocloseAddWindowOnClickingXBtn() {
		accountPage.verifyUserIsAbleTocloseAddWindowOnClickingXBtn();

	}

	public void addAddressInMyAccount(String addressName, String firstName, String lastName, String postalCode,
			String phoneNumber, String address1, String city) {
		accountPage.goToAddressBook();
		accountPage.verifyUserIsOnAddressBookPage();
		accountPage.addAddressInAddressBook(addressName, firstName, lastName, postalCode, phoneNumber, address1, city);
	}
	
	public void navigateToAddressBookPage() {
		accountPage.goToAddressBook();
		accountPage.verifyUserIsOnAddressBookPage();
	}
	
	public void verifyErrorMessageIsNotDisplayed() {
		paymentpage.verifyErrorMessageIsNotDisplayed();
	}

	public void clickAddToAddressBookCheckbox() {
		paymentpage.clickAddToAddressBookCheckbox();
	}

	public void verifyStep3OrderReviewIsActive() {
		ordereviewpage.verifyStep3OrderReviewIsActive();
	}

	public void verifyCustomerIsUnableToEditItemsWithinCartInOrderReviewPage() {
		ordereviewpage.verifyCustomerIsUnableToEditItemsWithinCartInOrderReviewPage();
	}

	public void clickShipngAddressEditBtnInOrderReviewPage() {
		ordereviewpage.clickShipngAddressEditBtnInOrderReviewPage();
	}

	public void clickBillingAddressEditBtnInOrderReviewPage() {
		ordereviewpage.clickBillingAddressEditBtnInOrderReviewPage();
	}

	public void clickShipngMethodEditBtnInOrderReviewPage() {
		ordereviewpage.clickShipngMethodEditBtnInOrderReviewPage();
	}

	public void enterPaymentInformation(String cardno, String expiryDate, String cardType, String cvv) {
		paymentpage.user_enter_card_details(cardno, expiryDate, cardType, cvv);
	}

	public void clickContinueToPlaceOrderButton() {
		paymentpage.clickContinueToPlaceOrderButton();
	}

	public void verifyElementsInOrderSummaryAtOrderReviewPage() {
		ordereviewpage.verifyElementsInOrderSummaryAtOrderReviewPage();
	}

	public void verifyThePricingStructureOfProductInOrderReviewPage() {
		ordereviewpage.verifyThePricingStructureOfProductInOrderReviewPage();
	}

	public void verifyTheStickyNatureOfOrderSummaryBoxInOrderReviewPage() {
		ordereviewpage.verifyTheStickyNatureOfOrderSummaryBoxInOrderReviewPage();
	}

	public void verifyTermsOfUseAndPrivacyPolicyLinksInOrderReviewPage() {
		ordereviewpage.verifyTermsOfUseAndPrivacyPolicyLinksInOrderReviewPage();
	}

	public void verifyCustomerServiceContactNoIsDisplayedOnOrderReviewPage() {
		ordereviewpage.verifyCustomerServiceContactNoIsDisplayedOnOrderReviewPage();
	}

	public void verifyCreditCardIsTheByDefaultPaymentMethodOnTheBillingPage() {
		paymentpage.verifyCreditCardIsTheByDefaultPaymentMethodOnTheBillingPage();
	}

	public void verifyPaymentMethodFieldsOnBillingPage() {
		paymentpage.verifyPaymentMethodFieldsOnBillingPage();
	}

	public void verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank() {
		paymentpage.verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank();
	}

	public void mobile_verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank() {
		paymentpage.mobile_verifyErrorMsgDisplayedWhenPaymentMethodFieldsLeftBlank();
	}

	public void verifyCardTypeAvailableForPayment(String[] availableCards) {
		paymentpage.verifyCardTypeAvailableForPayment(availableCards);
	}

	public void verifyPreviouslySavedCardsAreDisplayedOnBillingPage() {
		paymentpage.verifyPreviouslySavedCardsAreDisplayedOnBillingPage();
	}

	public void verifyFieldsOfPaymentMethodAreAutoPopulated() {
		paymentpage.verifyFieldsOfPaymentMethodAreAutoPopulated();
	}

	public void clickSubmitYourOrderButtonInOrderReviewPage() {
		ordereviewpage.clickSubmitYourOrderButtonInOrderReviewPage();
	}

	public void verifyUserIsNavigatedToOrderConfirmationPage() {
		orderConfirmationPage.verifyUserIsNavigatedToOrderConfirmationPage();
	}

	public void navigateToMyAccountPage() {
		topBanner.navigateToMyAccountPage();
	}

	public void clickOnCreditCardLinkOnMyAccountPage() {
		accountPage.clickOnCreditCardLinkOnMyAccountPage();
	}

	public void enterNameOnCardOnBillingPage(String cardOwnerName) {
		paymentpage.enterNameOnCardOnBillingPage(cardOwnerName);
	}

	public void verifyNewlyAddedCardIsDisplayedOnAccountPage(String savedCardName) {
		accountPage.verifyNewlyAddedCardIsDisplayedOnAccountPage(savedCardName);
	}

	public void deleteSavedCardByItsNameOnAccountPage(String savedCardName) {
		accountPage.deleteSavedCardByItsNameOnAccountPage(savedCardName);
	}

	public void verifyElementsInOrderReviewPage() {
		ordereviewpage.verifyElementsInOrderReviewPage();
	}

	public void verifyHandbagSubcategyColorChangesToGreenOnHovering() {
		homeheader.verifyHandbagSubcategyColorChangesToGreenOnHovering();
	}

	public void GoToAddressBook() {
		accountPage.goToAddressBookFromAccountPage();
	}
	
	public void clickOnPlusIconFromMiniCartLinkOnShippingPage() {
		shipngpage.clickOnPlusIconFromMiniCartLinkOnShippingPage();
	}

	public void verifyMiniCartAttributes() {
		shipngpage.verifyMiniCartAttributes_Images();
		shipngpage.verifyMiniCartAttributes_ProductNames();
		shipngpage.verifyMiniCartAttributes_ProductPrice();
		shipngpage.verifyMiniCartAttributes_ProductQuantity();
	}

	public void verifyMyBagIsSticky() {
		shipngpage.verifyMyBagIsSticky();
	}

	public void verifyOnClickingPlusIconOfMyBagDrawerClosesIfItIsOpen() {
		shipngpage.verifyOnClickingPlusIconOfMyBagDrawerClosesIfItIsOpen();
	}

	public void verifyProductsAddedInCartAreDisplayedInTheSameOrderOnShippingPage(String product_1, String product_2,
			String product_3) {
		shipngpage.verifyProductsAddedInCartAreDisplayedInTheSameOrderOnShippingPage(product_1, product_2, product_3);
	}

	public void clickOnKatespadeSpadeLogo() {
		productdetails.clickOnKatespadeSpadeLogo();
	}

	public void goToCustomerCarePageFromFooter() {
		footer.goToCustomerCarePageFromFooter();
	}

	public void verfifyUserlandsOnContactUsPage(String pageName) {
		customercare.verfifyUserlandsOnContactUsPage(pageName);
	}

	public void verifyCustomerCareLeftHandNavigation(String customerCarePage) {
		customercare.verifyCustomerCareLeftHandNavigationLibraryFoldersAreDisplayed();
		customercare.verifyCustomerCareContentInLeftHandNavigationAndColor(customerCarePage);
	}

	public void verifyCustomerCareHasHeaderH2() {
		customercare.verifyCustomerCareHasHeaderH2();
	}

	public void verifyPageNameHasHeaderH1() {
		customercare.verifyPageNameHasHeaderH1();
	}

	public void verifyPageDescriptionTextOnContactUsPage(String content) {
		customercare.verifyPageDescriptionTextOnContactUsPage(content);
	}

	public void verifyNeedHelpSubHeader() {
		customercare.verifyNeedHelpSubHeader();
	}

	public void verfiyChatWithUSAndEmailUs() {
		customercare.verfiyChatWithUSAndEmailUs();
	}
	
	public void verifyImNewHereAndClickOnCheckYourOrderLink() {
		accountPage.verifyImNewHereAndClickOnCheckYourOrderLink();
	}

	public void verifyUserIsAbleToSelectAnAddressFromDropdown() {
		shipngpage.verifyUserIsAbleToSelectAnAddressFromDropdown();
	}

	public void verifyAfterSelectingAddressFRomDropdownShippingFieldsAeAutoPopulatedFromAddressSelected() {
		shipngpage.verifyAfterSelectingAddressFRomDropdownShippingFieldsAeAutoPopulatedFromAddressSelected();
	}

	public void selectTheAddressDropdownAndPickanotherAddress() {
		shipngpage.selectTheAddressDropdownAndPickanotherAddress();
	}

	public String enterTheShippingDetailDataAndEnterNewDataManually(String username, String firstname, 
			String lastName, String address, String zip, String phnnumber) {
		return shipngpage.enterTheShippingDetailDataAndEnterNewDataManually(username, firstname, lastName, address, zip,
				phnnumber);
	}

	public void selectTheCheckBoxAddToAddressBookAndUseThisBillingAddress() {
		shipngpage.selectTheCheckBoxAddToAddressBookAndUseThisBillingAddress();
	}

	public void verifyShippingAddressIsReflectedInBillingAddress(String zip) {
		paymentpage.verifyShippingAddressIsReflectedInBillingAddress(zip);
	}

	public void verifyAddressAddedFromShippingPageIsAddedInAddressBook(String zip) {
		paymentpage.clickOnKateSpadeLogo();
		accountPage.GoToMyAccountPage();
		accountPage.selectAddressBook(zip);
	}

	public void mobile_verifyAddressAddedFromShippingPageIsAddedInAddressBook(String zip) {
		paymentpage.clickOnKateSpadeLogo();
		topBanner.mobile_clickOnSignINRegister();
		accountPage.expandMyAccountOptionsInMobile();
		accountPage.selectAddressBook(zip);
	}

	public void enterShippingDetailsAndDoNotContinue(String username, String firstname, String lastName, String address,
			String zip, String city, String phnnumber) {
		shipngpage.enterShippingDetailsAndDoNotContinue(username, firstname, lastName, address, zip, city, phnnumber);
	}

	public void verifyCountryDropdownWillShowCountryThatTheUserIsOn(String country) {
		shipngpage.verifyCountryDropdownWillShowCountryThatTheUserIsOn(country);
	}

	public void selectAshippingMethod() {
		shipngpage.selectAshippingMethod();
	}

	public void verifyUserIsOnBillingPage() {
		paymentpage.verifyUserIsOnBillingPage();
	}

	public void clickOnGiftMessageAndGiftBox() {
		shipngpage.clickOnGiftMessageAndGiftBox();
	}

	public void verifyGiftBoxModal() {
		shipngpage.verifyGiftBoxModal();
	}

	public void verifyAllCheckboxesInGiftBoxModel() {
		shipngpage.verifyAllCheckboxesInGiftBoxModel();
	}

	public void verifyFreeGiftMessageFunctionaity(String text) {
		shipngpage.verifyFreeGiftMessageFunctionaity(text);
	}

	public void verifyFirstNameIsAppearingInOrderReciept() {
		topBanner.getFirstNameOfUser();
		orderConfirmationPage.verifyFirstNameIsAppearingInOrderReciept();
	}

	public void verifyThePricingStructureOfProductInOrderConfirmationPage() {
		orderConfirmationPage.verifyThePricingStructureOfProductInOrderConfirmationPage();
	}

	public void verifyPaymentRelatedInformationInOrderConfirmationPage() {
		orderConfirmationPage.verifyPaymentRelatedInformationInOrderConfirmationPage();
	}

	public void verifyProductRelatedInformationOnConfirmationPage() {
		orderConfirmationPage.verifyProductRelatedInformationOnConfirmationPage();
	}

	public void verifySelectedShippingMethodTypeIsDisplayedInOrderConfirmationPage() {
		orderConfirmationPage.verifySelectedShippingMethodTypeIsDisplayedInOrderConfirmationPage();
	}

	public void verifyOrderNumberIsDisplayed() {
		orderConfirmationPage.verifyOrderNumberIsDisplayed();
	}

	public void verifyShippingAddressrelatedInfoInOrderConfirmationPage() {
		orderConfirmationPage.verifyShippingAddressrelatedInfoInOrderConfirmationPage();
	}

	public void verifyBillingAddressrelatedInfoInOrderConfirmationPage() {
		orderConfirmationPage.verifyBillingAddressrelatedInfoInOrderConfirmationPage();
	}

	public void verifyUserIsAbleToClickPlusBtnToExpandAndMinusBtnToCloseCreateAnAccountDrawer() {
		orderConfirmationPage.verifyUserIsAbleToClickPlusBtnToExpandAndMinusBtnToCloseCreateAnAccountDrawer();
	}

	public void createAnAccountFromOrderReceipt(String email, String pwd) {
		orderConfirmationPage.createAnAccountFromOrderReceipt(email, pwd);
	}

	public void verifyUserIsInAccountPage() {
		accountPage.verifyUserIsInAccountPage();
	}

	public void verifyContactUsContentAssetInOrderConfirmationPage() {
		orderConfirmationPage.verifyContactUsContentAssetInOrderConfirmationPage();
	}

	public void verifyErrorMessageIsDisplayed() {
		accountPage.verifyErrorMessageIsDisplayed();
	}

	public void navigateToCheckYourOrderPage() {
		topBanner.clickOnSignINRegister();
		accountPage.clickCheckYourOrderButton();
		orderHistoryPage.verifyUserIsNavigatedToCheckYourOrderPage();
	}

	public void clickCheckYourOrderButton() {
		accountPage.clickCheckYourOrderButton();
	}

	public void verifyUserIsNavigatedToCheckYourOrderPage() {
		orderHistoryPage.verifyUserIsNavigatedToCheckYourOrderPage();
	}

	public void checkStatusOfYourOrder(String orderNo, String orderEmail, String postalCode) {
		orderHistoryPage.checkStatusOfYourOrder(orderNo, orderEmail, postalCode);
	}

	public void verifyErrorMsgDisplayedForWrongorderInformation() {
		orderHistoryPage.verifyErrorMsgDisplayedForWrongorderInformation();
	}

	public void verifyAllPreviousOrdersPlacedAreDisplayedAndLimitOfOrderInPOrderHistoryPage() {
		orderHistoryPage.verifyAllPreviousOrdersPlacedAreDisplayedAndLimitOfOrderInPOrderHistoryPage();
	}

	public void clickFirstLinkOfOrderDetails() {
		orderHistoryPage.clickFirstLinkOfOrderDetails();
	}

	public void verifyUserIsNavigatedToOrderHistoryPage() {
		orderHistoryPage.verifyUserIsNavigatedToOrderHistoryPage();
	}

	public void verifyUserIsNavigatedToOrderDetailsPage() {
		orderDetailsPage.verifyUserIsNavigatedToOrderDetailsPage();
	}

	public void clickOrderHistoryLinkOnFooter() {
		footer.clickOrderHistoryLinkOnFooter();
	}

	public void loginIntoAccountFromGuestOrderCheckPage(String email, String password) {
		orderHistoryPage.loginIntoAccountFromGuestOrderCheckPage(email, password);
	}

	public void clickOnCheckOrderLink() {
		topBanner.clickOnCheckOrderLink();
	}

	public void verifyElementsInOrderHistoryPage() {
		orderHistoryPage.verifyElementsInOrderHistoryPage();
	}

	public void verifyThePricingStructureOfProductInOrderHistoryPage() {
		orderHistoryPage.verifyThePricingStructureOfProductInOrderHistoryPage();
	}

	public void verifyContentAssetAccNavTextAndItsLeftNavigation() throws InterruptedException {
		orderDetailsPage.verifyContentAssetAccNavTextAndItsLeftNavigation();

	}

	public void verifyOptionLinkingInContentAssetLeftNavigation() {
		orderDetailsPage.verifyOptionLinkingInContentAssetLeftNavigation();
	}

	public void verifyLeftHandHelpText() {
		orderDetailsPage.verifyLeftHandHelpText();
	}

	public void clickOnAddToWishlistLink() {
		productdetails.clickOnAddToWishlistLink();
	}

	public void verifyUserIsNavigatedToWislistPage() {
		wishlistpage.verifyUserIsNavigatedToWislistPage();
	}

	public void verifyInventoryMsgIsDisplayedInWishlistPage() {
		wishlistpage.verifyInventoryMsgIsDisplayedInWishlistPage();
	}

	public void veriyQtyDropdownIsDisplayedAndItsDefaultValue() {
		wishlistpage.veriyQtyDropdownIsDisplayedAndItsDefaultValue();
	}

	public void changeTheQuantityOfTheProduct(String qty) {
		wishlistpage.changeTheQuantityOfTheProduct(qty);
	}

	public void verifyWishlistBannerAndAccLeftNavBannerContentAsset() {
		wishlistpage.verifyWishlistBannerAndAccLeftNavBannerContentAsset();
	}

	public void verifyOptionLinkingInContentAssetLeftNavigationOnWishlistPage() {
		wishlistpage.verifyOptionLinkingInContentAssetLeftNavigationOnWishlistPage();
	}

	public void clickEditButtonOfFirstProduct() {
		wishlistpage.clickEditButtonOfFirstProduct();
	}

	public void verifyProductNameIsDisplayedInPDP(String prodName) {
		productdetails.verifyProductNameIsDisplayedInPDP(prodName);
	}

	public void clickOnCloseButton() {
		productdetails.clickOnCloseButton();
	}

	public void clickOnBackToOrderHistoryLinkInOrderDetailsPage() {
		orderDetailsPage.clickOnBackToOrderHistoryLinkInOrderDetailsPage();
	}

	public void verifyLinksInOrderDetailsPage() {
		orderDetailsPage.verifyLinksInOrderDetailsPage();
	}

	public void verifyOrderTrackingDetailsOnOrderDetailsPage() {
		orderDetailsPage.verifyOrderTrackingDetailsOnOrderDetailsPage();
	}

	public void verifyPaymentDetailsOnOrderDetailsPage() {
		orderDetailsPage.verifyPaymentDetailsOnOrderDetailsPage();
	}

	public void verifyProductDetailsOnOrderDetailsPage() {
		orderDetailsPage.verifyProductDetailsOnOrderDetailsPage();
	}

	public void verifyBillingAndShippingDetailsOnOrderDetailsPage() {
		orderDetailsPage.verifyBillingAndShippingDetailsOnOrderDetailsPage();
	}

	public void verifyDetailsInOrderDetailsPage() {
		orderDetailsPage.verifyLinksInOrderDetailsPage();
		orderDetailsPage.verifyOrderTrackingDetailsOnOrderDetailsPage();
		orderDetailsPage.verifyPaymentDetailsOnOrderDetailsPage();
		orderDetailsPage.verifyProductDetailsOnOrderDetailsPage();
		orderDetailsPage.verifyBillingAndShippingDetailsOnOrderDetailsPage();
	}

	public void verifyEnteredEmailIsAutopopulatedInEmailFieldWhileCreatingAcc(String guestEmail) {
		orderConfirmationPage.verifyEnteredEmailIsAutopopulatedInEmailFieldWhileCreatingAcc(guestEmail);
	}

	public void verifyRegionHeading(String region) {
		countrySelection.verifyRegionHeading(region);
	}

	public void verifyFindStoreNearYouHeaderIsH1() {
		storeLocator.verifyFindStoreNearYouHeaderIsH1();

	}

	public void verifyNumberOfSearchResultsHeaderIsH2() {
		storeLocator.verifyNumberOfSearchResultsHeaderIsH2();

	}

	public void verifyUserIsAbleToSearchStoreUsingHalfUKZipCodeAndVerifyDetailsOfThePage(String postalCode) {
		storeLocator.searchStore(postalCode);
		storeLocator.verifyUserIsAbleToSearchStoreUsingHalfUKZipCodeAndVerifyDetailsOfThePage();
	}

	public void verifyRadiusDropDownsIncluded() {
		storeLocator.verifyRadiusDropDownsIncluded();
	}

	public void verifyDetailsWhenNoStoresAreFound(String searchType, String errorMsg) {
		storeLocator.searchStore(searchType);
		storeLocator.verifyDetailsWhenNoStoresAreFound(errorMsg);
	}

	public void verifyCustomersCanFurtherRefineStoreSearchResultsList(String refineLabelText, String retail,
			String outlet, String stockist) {
		storeLocator.verifyRefineYourSearchResultsIsDisplayed(refineLabelText);
		storeLocator.verifyStoreTypesAvailable(retail, outlet, stockist);
		storeLocator.verifyCustomersCanFurtherRefineStoreSearchResultsList();
	}

	public void verifyTheDirectionDetailsLinkOfFirstStoreSearched() {
		storeLocator.verifyTheDirectionDetailsLinkOfFirstStoreSearched();
	}

	public void verifyTheStoreDetailsLinkOfFirstStoreSearched() {
		storeLocator.verifyTheStoreDetailsLinkOfFirstStoreSearched();
	}

	public void verifyOtherDetailsOnStoreDetailPage() {
		storeLocator.verifyOtherDetailsOnStoreDetailPage();
	}

	public void verifyThreeStoresResultsWillBeDisplayedPerRow() {
		storeLocator.verifyThreeStoresResultsWillBeDisplayedPerRow();
	}

	public void verifyStoreNameOnSearchPageIsHyperlinkandNavigatesUserToStoreDetailPage(String pageName) {
		storeLocator.verifyStoreNameOnStoreDetailPageIsHyperlink();
		storeLocator.verifyUserIsNavigatedToStoreDetailPageOnClickingStoreName(pageName);
	}

	public void userGoesBackToSearchPage() {
		storeLocator.userGoesBackToSearchPage();
	}

	public void clickCategoryOnHeader(String categoryIndex) {
		homeheader.clickCategoryOnHeader(categoryIndex);
	}

	public void verifyRefinementSectionInShopGridPage() {
		shopGridPage.verifyRefinementSectionInShopGridPage();
	}

	public void verifyProductSortingInShopGridPage(String sortingOption) {
		shopGridPage.verifyProductSortingInShopGridPage(sortingOption);
	}

	public void verifyCategoryHeaderAndProductCountIsDisplayed() {
		shopGridPage.verifyCategoryHeaderAndProductCountIsDisplayed();
	}
	
	public void verifyProductCountChangesAccordingToRefinement() {
		shopGridPage.verifyProductCountChangesAccordingToRefinement();
	}

	public void verifySubCategoriesAreDisplayedInSideBar() {
		shopGridPage.verifySubCategoriesAreDisplayedInSideBar();
	}

	public void clickOnTheSubCategoryLinkOnTheHeader(String categoryIndex, String subCategory, String subCategoryFr) {
		homeheader.clickOnTheSubCategoryLinkOnTheHeader(categoryIndex, subCategory, subCategoryFr);
	}

	public void verifyTheGreenColorOfSelectedSubCategory() {
		shopGridPage.verifyTheGreenColorOfSelectedSubCategory();
	}
	
	public void verifySubcategoriesAreExpandedInSideBar() {
		shopGridPage.verifySubcategoriesAreExpandedInSideBar();
	}

	public void selectACountryFromCountrySelectorPage(String country) {
		countrySelection.selectACountryFromCountrySelectorPage(country);
	}

	public void verifyCountrySwitchDialogIsDisplayed() {
		countrySelection.verifyCountrySwitchDialogIsDisplayed();
	}

	public void verifyUserLandsOnSelectedCountry(String germany) {
		landingPage.verifyUserLandsOnSelectedCountry(germany);
	}

	public void verifyAfterChangingTheCountryAddedProductIsRemoved() {
		landingPage.verifyAfterChangingTheCountryAddedProductIsRemoved();
	}

	public void goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(String uk) {
		landingPage.goBackToThePreviousCountryByClickingOnBackButtonFromTheBrowser(uk);
	}

	public void verifyProductIsAddedIntoTheBag() {
		shoppingCart.verifyProductIsAddedIntoTheBag();
	}

	public void clickOnContinueButtonFromCountrySwitchDialog() {
		countrySelection.clickOnContinueButtonFromCountrySwitchDialog();
	}

	public void clickOnCancelButtonFromCountrySwitchDialog() {
		countrySelection.clickOnCancelButtonFromCountrySwitchDialog();
	}

	public void verifyOnCancellingTheCountrySwitchTheMyBagRetainsTheProductThatIsAddedToTheBag() {
		countrySelection.verifyOnCancellingTheCountrySwitchTheMyBagRetainsTheProductThatIsAddedToTheBag();
	}

	public void verifyCountrySelectorHeaderIsUpdated(String country) {
		landingPage.verifyCountrySelectorHeaderIsUpdated(country);
	}

	public void addProductToWishlist() {
		productdetails.addProductToWishlist();
	}

	public void verifyProductThumbnailOnWishlistPage() {
		wishlistpage.verifyProductThumbnailOnWishlistPage();
	}

	public void verifyProductNameForMultipleItemsOnWishlistPage() {
		wishlistpage.verifyProductNameForMultipleItemsOnWishlistPage();
	}

	public void verifyStyleNumberOnWishlistPage() {
		wishlistpage.verifyStyleNumberOnWishlistPage();
	}

	public void verifySizeAttributeOnWishlistPage() {
		wishlistpage.verifySizeAttributeOnWishlistPage();
	}

	public void verifyColorAttributeOnWishlistPage() {
		wishlistpage.verifyColorAttributeOnWishlistPage();

	}

	public void verifyPriceOnWishlistPage() {
		wishlistpage.verifyPriceOnWishlistPage();

	}

	public void verifyInventoryOnWishlistPage() {
		wishlistpage.verifyInventoryOnWishlistPage();

	}

	public void verifyDateOnWishlistPage() {
		wishlistpage.verifyDateOnWishlistPage();

	}

	public void verifyQuantityIsSetToOneByDefaultOnWishlistPage() {
		wishlistpage.verifyQuantityIsSetToOneByDefaultOnWishlistPage();

	}

	public void verifyUserIsAbleToaddProductTOBagFromWishlistPage() {
		wishlistpage.verifyUserIsAbleToaddProductTOBagFromWishlistPage();
	}

	public void verifyUserIsStillLoggedIn() {
		accountPage.verifyUserIsStillLoggedIn();
	}

	public void verifyCountrySelectorIsNotPresentOnCheckout() {
		shipngpage.verifyCountrySelectorIsNotPresentOnCheckout();
	}

	public void goToCreditCardFromContentAssest() {
		accountPage.goToCreditCardFromContentAssest();
	}

	public void verifyUserCanAddANewAddress() {
		accountPage.verifyUserCanAddANewAddress();
	}

	public void verifyUserClicksOnSideNavAndLandsOnCorrectPage(int position, String myAccount) {
		accountPage.verifyUserClicksOnSideNav(position);
		accountPage.verifyUserLandsOnCorrectPage(myAccount);
	}

	public void mobile_verifyUserClicksOnSideNavAndLandsOnCorrectPage(int position, String myAccount) {
		accountPage.mobile_verifyUserClicksOnSideNav(position);
		accountPage.verifyUserLandsOnCorrectPage(myAccount);
	}

	public void verify_404_page_Customer_Service(String customerServiceMessage) {
		page404.verify_404_page_Customer_Service(customerServiceMessage);
	}

	public void verify_404_page_Try_New_Search(String searchText) {
		page404.verify_404_page_Try_New_Search(searchText);
	}

	public void verify_404_page(String title, String titleFr) {
		page404.verify_404_page(title, titleFr);
	}

	public void verify_404_Page_Search_Functionality(String product) {
		page404.verify_404_Page_Search_Functionality(product);
		productdetails.verifyTheUserIsAbletoMakeASearch();
		shopGridPage.selectFirstProduct();
	}

	public void verify404PageIsDisplayedWhenProductIDNotFoundInUrl(String productId, String title, String titleFr) {
		page404.verify404PageIsDisplayedWhenProductIDNotFoundInUrl(productId);
		page404.verify_404_page(title, titleFr);
	}

	public void verify404PageIsDisplayedWhenProductCategoryNotFoundInUrl(String title, String titleFr) {
		page404.verify404PageIsDisplayedWhenProductCategoryNotFoundInUrl();
		page404.verify_404_page(title, titleFr);
	}

	public void clickOnReturnToShoppingLinkInOrderDetailsPage() {
		orderDetailsPage.clickOnReturnToShoppingLinkInOrderDetailsPage();
	}

	public void verifyOptionLinkingInContentAssetLeftNavigationForGuestUser() {
		orderDetailsPage.verifyOptionLinkingInContentAssetLeftNavigationForGuestUser();
	}

	public void verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage() {
		orderHistoryPage.verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage();
	}

	public void verifyRememberMeChkbxIsUncheckedByDefault() {
		orderHistoryPage.verifyRememberMeChkbxIsUncheckedByDefault();
	}

	public void verifyForgotPasswordLinkInOrderHistoryPage(String username) {
		orderHistoryPage.verifyForgotPasswordLinkInOrderHistoryPage(username);
	}

	public void verifyCheckOrderFieldsCannotBeLeftEmpty() {
		orderHistoryPage.verifyCheckOrderFieldsCannotBeLeftEmpty();
	}

	public void verifyTheMaxProductCountInShopgridPage() {
		shopGridPage.verifyTheMaxProductCountInShopgridPage();
	}

	public void verifyLazyLoadingOnShopGridPage() {
		shopGridPage.verifyLazyLoadingOnShopGridPage();
	}

	public void verifyUserIsNaviagatedToSamePositionInShpGrdPageWhenClickBackOnPDP() {
		shopGridPage.verifyUserIsNaviagatedToSamePositionInShpGrdPageWhenClickBackOnPDP();
	}

	public void verifyProductImagesAreLoadedUsingAdobeScene7() {
		shopGridPage.verifyProductImagesAreLoadedUsingAdobeScene7();
	}

	public void verifyClickingFrstProductImageOnShopGridPageNavToCorrespondngPDP() {
		shopGridPage.verifyClickingFrstProductImageOnShopGridPageNavToCorrespondngPDP();
	}

	public void verifyClickingFrstProductNameOnShopGridPageNavToCorrespondngPDP() {
		shopGridPage.verifyClickingFrstProductNameOnShopGridPageNavToCorrespondngPDP();
	}

	public void verifyClickingQuickBuyIconOnShopGridPageNavToCorrespondngQuickBuyPage() {
		shopGridPage.verifyClickingQuickBuyIconOnShopGridPageNavToCorrespondngQuickBuyPage();
	}

	public void verifyPromotionalMsgAreAppearingInShopGridPage(String promotionalMsg) {
		shopGridPage.verifyPromotionalMsgAreAppearingInShopGridPage(promotionalMsg);
	}

	public void verifyAllTheValidationChecksAreAvailable() {
		accountPage.verifyAddCreditCardsAttributesArePresent();
		accountPage.verifyAllTheValidationChecksAreAvailable();
	}

	public void enterTheValidCreditCardValues(String type, String number, String cvv, String cardname) {
		accountPage.enterTheValidCreditCardValues(type, number, cvv, cardname);
		accountPage.verifyTheResultsOnceUserClicksOnApplyButton();
	}

	public void mobile_loginWithRegisteredUser(String userName, String password) {
		accountPage.mobile_loginWithRegisteredUser(userName, password);
	}

	public void verifyWhenGuestUserAddsAProductToWishlistLoginScreenIsDisplayedInstaedOfMyWishlistPage() {
		productdetails.verifyUserCantAddProductToWishlistWhenNotLoggedIn();
	}

	public void enterInvalidCreditDetail() {
		accountPage.enterInvalidCreditDetail();
	}

	public void verifyOnClickingOnCancelButtonUserIsBackOnWalletPage() {
		accountPage.verifyOnClickingOnCancelButtonUserIsBackOnWalletPage();
	}

	public void verifyHeaderReturnsSearchQuerryWitchTotalNumberOfResults() {
		shopGridPage.verifyHeaderReturnsSearchQuerryWitchTotalNumberOfResults();
	}

	public void verifyFilterOptionIsPresent() {
		shopGridPage.verifyFilterOptionIsPresent();
	}

	public void verifySortingFiltersArePresent() {
		shopGridPage.verifySortingFiltersArePresent();
	}

	public void verifyAlternateImagesAreDisplayedOnMouseOver() {
		shopGridPage.verifyAlternateImagesAreDisplayedOnMouseOver();
	}

	public void verifySwatchListOnTheShopGridPage(String prodIndex) {
		shopGridPage.verifySwatchListOnTheShopGridPage(prodIndex);
	}

	public void verifyTextBuyAllOrNoneDisplayForProductSet() {
		shopGridPage.verifyTextBuyAllOrNoneDisplayForProductSet();
	}

	public void verifyUserIsAbleToApplyPromoCodeInShopingCartPage(String promoCode) {
		shoppingCart.verifyUserIsAbleToApplyPromoCodeInShopingCartPage(promoCode);
	}

	public void verifyUserIsAbleToApplyPromoCodeAndVerifyDetailsInFooterOfShopingCartPage(String promoCode)
			throws InterruptedException {
		shoppingCart.verifyUserIsAbleToApplyPromoCodeAndVerifyDetailsInFooterOfShopingCartPage(promoCode);
	}

	public void applyPromoCodeInShopingCartPage(String promoCode) {
		shoppingCart.applyPromoCodeInShopingCartPage(promoCode);
	}

	public void verifyOrderSummaryForDiscountedProductInShippingPg() {
		shipngpage.verifyOrderSummaryForDiscountedProductInShippingPg();
	}

	public void verifyTheStickyNatureOfOrderSummaryBoxInProductShipngPage() {
		shipngpage.verifyTheStickyNatureOfOrderSummaryBoxInProductShipngPage();
	}

	public void verifyUserIsAbleToApplyAndRemovePromoCodeInBillingPage(String promoCode) {
		paymentpage.verifyUserIsAbleToApplyAndRemovePromoCodeInBillingPage(promoCode);
	}

	public void verifyThePricingStructureOfProductInShopGridPage() {
		shopGridPage.verifyThePricingStructureOfProductInShopGridPage();
	}

	public void applyPromoCodeInBillingPage(String promoCode) {
		paymentpage.applyPromoCodeInBillingPage(promoCode);
	}

	public void removePromoCodeInBillingPage() {
		paymentpage.removePromoCodeInBillingPage();
	}

	public void verifyErrorMsgDisplayedWhenPromoIsNotApplicableInBillingPage() {
		paymentpage.verifyErrorMsgDisplayedWhenPromoIsNotApplicableInBillingPage();
	}

	public void verifyAddAllToBagButtonAndProdSetPriceIsDisplayed() {
		productdetails.verifyAddAllToBagButtonAndProdSetPriceIsDisplayed();
	}

	public void verifyClickingAlternativeImageReplacedTheMainImage(int thmbImgIndx) {
		productdetails.verifyClickingAlternativeImageReplacedTheMainImage(thmbImgIndx);
	}

	public void verifyUserIsableToMakeNewlyAddedAddressAsDefaultAddress(String defautFirstName) {
		accountPage.verifyUserIsableToMakeNewlyAddedAddressAsDefaultAddress(defautFirstName);
	}

	public void verifyBonusProductMsgAndSelectBonusProductLink(String bonusProductMsg) {
		shoppingCart.verifyBonusProductMsgAndSelectBonusProductLink(bonusProductMsg);
	}

	public void verifySelectBonusProductModal() {
		shoppingCart.verifySelectBonusProductModal();
		shoppingCart.verifyProductInfoAndAddToBagWhenBtnToSelectIsClicked();
	}

	public void verifyCartPageOnAddingBonusProductToCart(String productStyle) {
		shoppingCart.verifyCartPageOnAddingBonusProductToCart(productStyle);
	}

	public void verifyBonusProductQuantityAndTotalCost(String bonusProductPrice, String bonusProductQnt,
			String bonusProductSubTotal) {
		shoppingCart.verifyBonusProductQuantityAndTotalCost(bonusProductPrice, bonusProductQnt, bonusProductSubTotal);
	}

	public void verifyBonusProductDetailsOnBagPage() {
		shoppingCart.verifyBonusProductDetailsOnBagPage();
	}

	public void verifyBonusProductNameLinkNavigatesUserToPDPPage() {
		shoppingCart.verifyBonusProductNameLinkNavigatesUserToPDPPage();
	}

	public void verifyBonusProductDetailsOnMiniCartPage(String bonusPrice) {
		minicartpage.verifyBonusProductDetailsOnMiniCartPage();
		minicartpage.verifyDetailsOfBonusProductsPresentInMiniCartPage(bonusPrice);
	}

	public void verifySizeChartIsPresentToGuideTheUserInPDP() {
		productdetails.verifySizeChartIsPresentToGuideTheUserInPDP();
	}

	public void verifyUserIsInPDPPage() {
		productdetails.verifyUserIsInPDPPage();
	}

	public void waitForMiniCartWindowToDisappear() {
		minicartpage.waitForMiniCartWindowToDisappear();
	}

	public void clickOnAddAllToBagButtonForProductSetInPdpPage() throws InterruptedException {
		productdetails.clickOnAddAllToBagButtonForProductSetInPdpPage();
	}

	public void verifyAllProductsAreAddedInTheBag() throws InterruptedException {
		productdetails.verifyAllProductsAreAddedInTheBag();
	}

	public void verifyIndividualItemsInProductsSetPDPIsAddedToMiniCart() {
		productdetails.verifyIndividualItemsInProductsSetPDPIsAddedToMiniCart();
	}

	public void verifyMostRecentlyViewedSection(String recentlyViewedHeader) {
		productdetails.verifyMostRecentlyViewedSection(recentlyViewedHeader);
	}

	public void verifyUserHasTheAbilityToZoomTheProduct() {
		productdetails.verifyUserHasTheAbilityToZoomTheProduct();
	}

	public void verifyMainImageAndZoomImageOccupyTheSameSpace() {
		productdetails.verifyMainImageAndZoomImageOccupyTheSameSpace();
	}

	public void verifySocialShareLinks() {
		productdetails.verifySocialShareLinks();
	}

	public void verifyBlackBottomBorderOfTheSelectedThumbnailImage() {
		productdetails.verifyBlackBottomBorderOfTheSelectedThumbnailImage();
	}

	public void verifyTheMaxProductCountInSearchPage() {
		search.verifyTheMaxProductCountInSearchPage();
	}

	public void verifyUserIsNaviagatedToSamePositionInSearchPageWhenClickBackOnPDP(String searchedItem) {
		search.verifyUserIsNaviagatedToSamePositionInSearchPageWhenClickBackOnPDP(searchedItem);
	}

	public void verifyLazyLoadingOnSearchPage() {
		search.verifyLazyLoadingOnSearchPage();
	}

	public void device_verifyUserlandsOnContactUsPage(String pageName) {
		customercare.device_verifyUserlandsOnContactUsPage(pageName);
	}

	public void verifyContactUsPage() {
		customercare.verifyContactUsPage();
	}

	public void verifyContactUsDropdown() {
		customercare.verifyContactUsDropdown();
	}

	public void verifyAddedItemsAreRemovedFromWishlistPage() {
		wishlistpage.verifyAddedItemsAreRemovedFromWishlistPage();
	}

	public void verifyHeaderForTheNoResultsPage(String zeroResults) {
		search.verifyHeaderForTheNoResultsPage(zeroResults);
	}

	public void verifyNoResultBannerInNoResultsSearchedPage() {
		search.verifyNoResultBannerInNoResultsSearchedPage();
	}

	public void verifyNoResultHelpIsDisplayed() {
		search.verifyNoResultHelpIsDisplayed();
	}

	public void verifySearchFrom0ResultsSearchPage(String prodName) {
		search.verifySearchFrom0ResultsSearchPage(prodName);
	}

	public void verifyErrorMsgForInvalidCouponCode() {
		shoppingCart.verifyErrorMsgForInvalidCouponCode();
	}

	public void searchAProductInMobile(String productName) {
		search.searchAProductInMobile(productName);
	}

	public void verifySearchBxFunctionality(String searchBxPlacehldr) {
		search.verifySearchBxFunctionality(searchBxPlacehldr);
	}

	public void verifyErrorMsgIfAlreadyRegisteredEmailIsUsedForAccontCreation(String alredayRegisteredEmail) {
		accountPage.verifyErrorMsgIfAlreadyRegisteredEmailIsUsedForAccontCreation(alredayRegisteredEmail);
	}

	public void verifyAddToMailingListRadioBtnIsNotChecked() {
		accountPage.verifyAddToMailingListRadioBtnIsNotChecked();
	}

	public void verifyUserIsAbleToEditAProduct(String prodIndex, String option) {
		shoppingCart.verifyUserIsAbleToEditAProduct(prodIndex);
		shoppingCart.verifyUserIsAbleToUpdateProductQuantityOnClickingEditBtn(option);
		shoppingCart.verifyTheOptionSelected();
	}

	public void verifyUserIsAbleToRemoveProductFromShoppingCart(String productIndex) {
		shoppingCart.verifyUserIsAbleToRemoveProductFromShoppingCart(productIndex);
	}

	public void applyValidPromoCodeInBillingPage(String promoCode) {
		paymentpage.applyValidPromoCodeInBillingPage(promoCode);
	}

	public void verifyPromoCodeWithDetailsAreDislayedOnOrderReviewPage(String promoApplied) {
		ordereviewpage.verifyPromoCodeWithDetailsAreDislayedOnOrderReviewPage(promoApplied);
	}

	public void verifyInformationOnOrderSummaryBoxAfterApplyingValidPromoCode() throws InterruptedException {
		shoppingCart.verifyInformationOnOrderSummaryBoxAfterApplyingValidPromoCode();
	}

	public void verifyTheStickyNatureOfOrderSummaryBoxOnCartPage() {
		shoppingCart.verifyTheStickyNatureOfOrderSummaryBoxOnCartPage();
	}

	public void applyPromoCodeInShopingCartPageToVerifyInformation(String promoCode) {
		shoppingCart.applyPromoCodeInShopingCartPageToVerifyInformation(promoCode);
	}

	public void navigateToAnyShopFromHeader() {
		homeheader.navigateToAnyShopFromHeader();
	}

	public void verifyRecommendationsOnCartPage(String youMayAlsoLikeText, String youMayAlsoLikeFr) {
		shoppingCart.verifyRecommendationsOnCartPage(youMayAlsoLikeText, youMayAlsoLikeFr);
	}

	public void verifyWishlistIsEmpty(String emptyWishlistText) {
		wishlistpage.verifyWishlistIsEmpty(emptyWishlistText);
	}

	public void verifyYouHaveNoSavedItemsOnCartPageWhenUserWishlistIsEmpty(String youHaveNoSavedItems) {
		minicartpage.goToCartPage();
		shoppingCart.youHaveNoSavedItemsOnCartPage(youHaveNoSavedItems);
	}

	public void verifyUserIsAbleToViewEmptyWishlist() {
		landingPage.verifyWishlistCanBeAccessdedFromHeader();
	}

	public void verifyProductIsAddedToWishlistRecommendationOnCartPage() {
		productName = wishlistpage.getProductNameAddedToWishlist();
		wishlistpage.verifyUserIsAbleToaddProductTOBagFromWishlistPage();
		minicartpage.goToCartPage();
		shoppingCart.verifyProductIsAddedToWishlistRecommendationOnCartPage(productName);
	}

	public void verifyTheMaxLimitOfWishlistRecommendedItemsOnCartPage() {
		minicartpage.goToCartPage();
		shoppingCart.verifyTheMaxLimitOfWishlistRecommendedItemsOnCartPage();
	}

	public void verifyFirstProductAddedToWishlistIsNotPresentInWishlistRecommendedItemsOnCartPage() {
		shoppingCart.verifyFirstProductAddedToWishlistIsNotPresentInWishlistRecommendedItemsOnCartPage(productName);
		shoppingCart.removeTheProductFromBag();
	}

	public void verifyCustomerServiceOnCartPage() {
		shoppingCart.verifyCustomerServiceOnCartPage();
	}

	public void clickOnCheckOutWithPayPalBtn() {
		shoppingCart.clickOnCheckOutWithPayPalBtn();
	}

	public void logInAndPayWithPayPal(String email, String password) {
		paypal.logInAndPayWithPayPal(email, password);
	}

	public void selectKlarnaRadioBtnForPaymentInBillingPage() {
		paymentpage.selectKlarnaRadioBtnForPaymentInBillingPage();
	}

	public void enterKlarnaPaymentDetails(String dob, String houseNo, String houseExtn) {
		paymentpage.enterKlarnaPaymentDetails(dob, houseNo, houseExtn);
	}

	public void verifyDiscountIsNotAppliedInOrderSummary() {
		shoppingCart.verifyDiscountIsNotAppliedInOrderSummary();
	}

	public void verifySubtext(String subText, int location) {
		accountPage.verifySubtext(subText, location);
	}

	public void verifyUserClicksOnSideNavAndLandsOnCorrectPage(String pageName, String pageHeader) {
		accountPage.clickOnMyInformation();
		accountPage.verifyUserClicksOnSideNavAndLandsOnCorrectPage(pageName, pageHeader);
	}

	public void verifyFirstAndLastNameFieldIsPresent() {
		accountPage.verifyFirstAndLastNameFieldIsPresent();
	}

	public void mobile_verifyFirstAndLastNameFieldIsPresent() {
		accountPage.mobile_verifyFirstAndLastNameFieldIsPresent();
	}

	public void verifyEmailAddressFieldIsPresent(String validEmail, String inValidEmail, String errorMsg,
			String orgEmail) {
		accountPage.verifyEmailAddressFieldIsPresent();
		accountPage.verifyWhenValidAndInvalidEmailIdIsEntered(validEmail, inValidEmail);
		accountPage.verifyErrorMessageOnEnteringInvalidEmailID(errorMsg);
		accountPage.enterOriginalEmailId(orgEmail);
	}

	public void mobile_verifyEmailAddressFieldIsPresent(String validEmail, String inValidEmail, String errorMsg,
			String orgEmail) {
		accountPage.mobile_verifyEmailAddressFieldIsPresent();
		accountPage.mobile_verifyWhenValidAndInvalidEmailIdIsEntered(validEmail, inValidEmail);
		accountPage.verifyErrorMessageOnEnteringInvalidEmailID(errorMsg);
		accountPage.enterOriginalEmailId(orgEmail);
	}

	public void verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(String inValidEmail) {
		accountPage.verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(inValidEmail);
	}

	public void mobile_verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(String inValidEmail) {
		accountPage.mobile_verifyConfirmEmailFieldAndErrorIfEmailFieldMismatchFromUsersEmail(inValidEmail);
	}

	public void verifyPasswordField(String password1, String errorMsg) {
		accountPage.verifyPasswordField(password1, errorMsg);
	}

	public void mobile_verifyPasswordField(String password1, String errorMsg) {
		accountPage.mobile_verifyPasswordField(password1, errorMsg);
	}

	public void verifyRadioButtonFieldAndOptInOptOutText(String optInMail, String optOutFromMail) {
		accountPage.verifyRadioButtonFieldAndOptInText(optInMail);
		accountPage.verifyRadioButtonFieldAndOptOutText(optOutFromMail);
	}

	public void openHamBurgerNavigation() {
		topBanner.openHamBurgerNavigation();
	}

	public void updateTheQuantityOfFirstProductInShoppingCartPage(String qty) {
		shoppingCart.updateTheQuantityOfFirstProductInShoppingCartPage(qty);
	}

	public void verifyPromotionalMessageAndItemPriceAfterPromotionIsApplied() {
		shoppingCart.verifyPromotionalMessageAndItemPriceAfterPromotionIsApplied();
	}

	public void verifySubmitButtonFunctinality(String lastName, String usersEmail, String userspassword) {
		accountPage.clickOnMyInformation();
		accountPage.editLastNameAndAddUserInfoInPersonalInfo(lastName, usersEmail, userspassword);
		accountPage.clickOnSubmitbtn();
	}

	public void verifylastNameisUpdatedSuccessfully(String lastName) {
		accountPage.verifylastNameisUpdatedSuccessfully(lastName);
	}

	public void verifyChangePasswordFeildsCannotLeftBeEmpty() {
		accountPage.clickOnMyInformation();
		accountPage.verifyChangePasswordFeildsCannotLeftBeEmpty();
	}

	public void mobile_verifyChangePasswordFeildsCannotLeftBeEmpty() {
		accountPage.clickOnMyInformation();
		accountPage.mobile_verifyChangePasswordFeildsCannotLeftBeEmpty();
	}

	public void verifyNewPasswordFeildLength(String password1, String errorMsg) {
		accountPage.verifyNewPasswordFeildLength(password1, errorMsg);
	}

	public void verifyConfirmPasswordFeildShouldBeSameAsNewPassword(String newPassword, String confirmPassword) {
		accountPage.verifyConfirmPasswordFeildShouldBeSameAsNewPassword(newPassword, confirmPassword);
	}

	public void verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(String newPassword,
			String confirmPassword, String errMsg) {
		accountPage.verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(newPassword, confirmPassword,
				errMsg);
	}

	public void mobile_verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(String newPassword,
			String confirmPassword, String errMsg) {
		accountPage.mobile_verifyErrorMessageWhenConfirmPasswordFeildAndNewPasswordDoesNotMatch(newPassword,
				confirmPassword, errMsg);
	}

	public void verifyErrorMessageWhenWrongCurrentPasswordIsEntered(String wrongCurrentPass, String newPass,
			String confirmPass, String errMsg) {
		accountPage.enterPasswordFeilds(wrongCurrentPass, newPass, confirmPass);
		accountPage.clickOnApplyBtn();
		accountPage.verifyErrorMessageWhenWrongCurrentPasswordIsEntered(errMsg);
	}
	
	public void verifyUserIsAbleToChangePasswordSuccessfully(String currentPass, String newPass, String confirmPass,
			String pageName) {
		accountPage.clickOnMyInformation();
		accountPage.enterPasswordFeilds(currentPass, newPass, confirmPass);
		accountPage.clickOnApplyBtn();
		accountPage.verifyUserUpdatesThePasswordSuccessfully(pageName);
	}

	public void verifyFunctionalityOfFindAddressButtonInBillingPage(String postalCode) {
		paymentpage.verifyFunctionalityOfFindAddressButtonInBillingPage(postalCode);
	}

	public void removeTheProductFromBag() {
		shoppingCart.removeTheProductFromBag();
	}

	public void verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePageForMobile() {
		shopGridPage.verifyOnClickingOnCategoryUserLandsOnCategoryBrowsePageForMobile();
	}

	public void verifyShippingDiscountIsApplied() {
		shoppingCart.verifyShippingDiscountIsApplied();
	}

	public void verifyShippingDiscountIsAppliedInShippingPage() {
		shipngpage.verifyShippingDiscountIsAppliedInShippingPage();
	}

	public void clickOnBagIcon() {
		homeheader.clickOnBagIcon();
	}

	public void verifyProductImagesAreLoadedUsingAdobeScene7InPDP() {
		productdetails.verifyProductImagesAreLoadedUsingAdobeScene7InPDP();
	}

	public void verifySalesPriceForRecommendedProduct() {
		productdetails.verifySalesPriceForRecommendedProduct();
	}

	public void verifySearchResultBannerInSearchPage() {
		search.verifySearchResultBannerInSearchPage();
	}

	public void verifyAlternateImagesAreDisplayedOnMouseOverInPDP() {
		productdetails.verifyAlternateImagesAreDisplayedOnMouseOverInPDP();
	}

	public void selectProductImageRecommendedListAndVerifyInPDPPage() {
		productdetails.selectProductImageRecommendedListAndVerifyInPDPPage();
	}

	public void verifyAddressBookFeilds() {
		accountPage.goToAddressBook();
		accountPage.verifyUserIsOnAddressBookPage();
		accountPage.verifyAddressBookFeilds();
	}

	public void mobile_verifyAddressBookFeilds() {
		accountPage.goToAddressBook();
		accountPage.verifyUserIsOnAddressBookPage();
		accountPage.mobile_verifyAddressBookFeilds();
	}

	public void verifyTheHeaderOfTheRecommendationHeaderSectionInShopingCartPage(String recommendationHeader,
			String recommendationHeaderFr) {
		shoppingCart.verifyTheHeaderOfTheRecommendationHeaderSectionInShopingCartPage(recommendationHeader,
				recommendationHeaderFr);
	}

	public void verifyTheMaxLimitOfRecommendedItemsInCartPage() {
		shoppingCart.verifyTheMaxLimitOfRecommendedItemsInCartPage();
	}

	public void verifyErrorsOnTheMandatoryFieldsInShipingPageAfterEnteringInvalidDetails() {
		shipngpage.enterInvalidShippingDetails();
		shipngpage.verifyErrorsOnTheMandatoryFieldsInShipingPage();
	}

	public void verifyErrorMsgWhenInvalidPostalCodeIsEntered(String errMsg, String enterManuallyMsg) {
		accountPage.verifyErrorMsgWhenInvalidPostalCodeIsEntered(errMsg, enterManuallyMsg);
	}

	public void verifyWeHaveFoundMsgWhenValidPostalCodeIsEntered(String zipCode) {
		accountPage.verifyWeHaveFoundMsgWhenValidPostalCodeIsEntered(zipCode);
	}

	public void verifyUserIsAbleToEnterAddressManually() {
		accountPage.clickOnEnterManuallyLink();
		accountPage.verifyEnterManuallyFields();
	}

	public void closeCookieNotification() {
		topBanner.closeCookieNotification();
	}

	public void navigateToCheckYourOrderPageInMobile() {
		topBanner.mobile_clickOnSignINRegister();
		accountPage.clickCheckYourOrderButton();
		orderHistoryPage.verifyUserIsNavigatedToCheckYourOrderPage();
	}

	public void verifyProductCountChangesAccordingToSizeRefinement() {
		shopGridPage.verifyProductCountChangesAccordingToSizeRefinement();
	}

	public void expandMyAccountOptionsInMobile() {
		accountPage.expandMyAccountOptionsInMobile();
	}

	public void checkoutFromMiniCartInIPad() {
		minicartpage.checkoutFromMiniCartInIPad();
	}

	public void verifyContentAssetIn404Page() {
		page404.verifyContentAssetIn404Page();
	}

	public void verifyCustomerCareFunctionalityIn404Page() {
		page404.verifyCustomerCareFunctionalityIn404Page();
	}

	public void verifySearchFunctionalityIn404Page() {
		page404.verifySearchFunctionalityIn404Page();
	}

	public void verifyHomeBtnFunctionalityIn404Page(String homepageURL) {
		page404.verifyHomeBtnFunctionalityIn404Page(homepageURL);
	}

	public void verifyCVNToolTipInPaymentPage() {
		paymentpage.verifyCVNToolTipInPaymentPage();
	}

	public void selectProductCategoryForMobileUser(String catIndex) {
		homeheader.selectProductCategoryForMobileUser(catIndex);
	}

	public void selectSizeSwatch(int swatchIndex) {
		productdetails.selectSizeSwatch(swatchIndex);
	}

	public void verifyGreenColorOfAddToBagButton() {
		productdetails.verifyGreenColorOfAddToBagButton();
	}

	public void selectColorSwatch(int colorSwatchIndex) {
		productdetails.selectColorSwatch(colorSwatchIndex);
	}

	public void addWishlistProductToBag(int prodIndex) {
		wishlistpage.addWishlistProductToBag(prodIndex);
	}

	public void closeMiniCartWindowIniPhone() {
		minicartpage.closeMiniCartWindowIniPhone();
	}

	public void VerifyMegaMenuElementsForFr() {
		homeheader.VerifyMegaMenuElementsForFr();
	}

	public void clickOnTheSubCategoryLinkWithIndexOnTheHeader(String categoryIndex, String subCategoryIndex) {
		homeheader.clickOnTheSubCategoryLinkWithIndexOnTheHeader(categoryIndex, subCategoryIndex);
	}

	public void backButton() {
		accountPage.backButton();
	}

	public void mobileBackButton() {
		accountPage.mobileBackButton();
	}

	public void verifySocialMediaIconsFR() {
		footer.verifySocialMediaIconsFR();
	}

	public void mobile_verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage() {
		orderHistoryPage.mobile_verifyEmailAndPwdAreMandatoryFieldsInGuestOrderCheckPage();
	}

	public void mobile_verifyCheckOrderFieldsCannotBeLeftEmpty() {
		orderHistoryPage.mobile_verifyCheckOrderFieldsCannotBeLeftEmpty();
	}

	public void fr_verifyDetailsOnSearchPageOnMapAndDetailsWhenStoreDetailsIsClicked(String pageName) {
		storeLocator.fr_verifyDetailsOnSearchPageOnMapAndDetailsWhenStoreDetailsIsClicked(pageName);
	}

	public void verifyPrivacyPolicyCheckBoxIsUncheckedByDefault() {
		accountPage.verifyPrivacyPolicyCheckBoxIsUncheckedByDefault();
	}

	public void verifyPrivacyPolicyCheckBoxIsRequiredToCheckedForAccountRegistration() {
		accountPage.verifyPrivacyPolicyCheckBoxIsRequiredToCheckedForAccountRegistration();
	}

	public void expandContactUsOptions() {
		customercare.expandContactUsOptions();
	}
	
	public void verifyUserHasNavigatedToCountrySelectorPage(String countrySelectorPageHeader) {
		landingPage.verifyUserHasNavigatedToCountrySelectorPage(countrySelectorPageHeader);
	}
	
	public void clickOnCountrySelectorIcon() {
		landingPage.clickOnCountrySelectorIcon();
	}
	
	public void verifyCountryIsAddedInCountrySelectionPage(String country) {
		countrySelection.verifyCountryIsAddedInCountrySelectionPage(country);
	}
	
	public void clickOnCountryLink(String country) {
		countrySelection.clickOnCountryLink(country);
	}
	
	public void verifyUserHasNavigatedToHomePageOfSelectedCountry(String homePageURL) {
		countrySelection.verifyUserHasNavigatedToHomePageOfSelectedCountry(homePageURL);
	}
	
	public void verifyOrderOfCountriesInListOnCountrySelectorPage(String listOfCountries) {
		countrySelection.verifyOrderOfCountriesInListOnCountrySelectorPage(listOfCountries);
	}
	
	public void verifyCountrySelectorLogoInHeader(String shippingLabel , String countryName , String currency) {
		landingPage.verifyCountrySelectorLogoInHeader(shippingLabel , countryName ,currency);
	}

	public void verifyDefaultLocaleOfKateSpadeRoESite(String defaultUrl, String defaultRoESite,
			String countryShippingTo, String defaultCountry, String countryCurrency) {
		landingPage.verifyDefaultLocaleOfKateSpadeRoESite(defaultUrl, defaultRoESite, countryShippingTo,
				defaultCountry, countryCurrency);
	}

	public void verifyGermanLocaleToKateSpadeRoESiteHomePage(String homePageUrl, String germanLocale) {
		landingPage.verifyGermanLocaleToKateSpadeRoESiteHomePage(homePageUrl, germanLocale);
	}
	
	public void clickOnCountryLinkOnCountrySelectionPage(String homePageURL) {
		countrySelection.clickOnCountryLinkOnCountrySelectionPage(homePageURL);
	}
	
	public void clickOnCountry(String country) {
		countrySelection.clickOnCountry(country);
	}
	
	public void verifyMessageOfItemRemoveFromShoppingBag(String message) {
		countrySelection.verifyMessageOfItemRemoveFromShoppingBag(message);
	}
	
	public void verifyLocaleInKateSpadeRoESiteHomePage(String countryLocale) {
		countrySelection.verifyLocaleInKateSpadeRoESiteHomePage(countryLocale);	
	}
	
	public void clickOnNavigationOverlay() {
		countrySelection.clickOnNavigationOverlay();
	}

	public void clickToAddAddress() {
		accountPage.clickToAddAddressButton();	
	}

	public void verifyLocaleInCountryDropdown(String country) {
		accountPage.verifyLocaleInCountryDropdown(country);	
	}

	public void closeAddressModal() {
		accountPage.closeAddressModal();	
	}

	public void verifyLocaleIsSelectedByDefault(String country) {
		shipngpage.verifyLocaleIsSelectedByDefault(country);	
	}

	public void verifyLocaleIsSelectedByDefaultOnBillingPage(String string) {
		paymentpage.verifyLocaleIsSelectedByDefaultOnBillingPage(string);
	}
	
	public void verifyTopBannerLinksOnKateSpadeRoEHomePage(String storeLocatorTitle, String needHelpTitle,
			String signInAndRegisterLink, String wishListTitle, String bagTitle) {
		landingPage.verifyTopBannerLinksOnKateSpadeRoEHomePage(storeLocatorTitle, needHelpTitle, 
				signInAndRegisterLink, wishListTitle, bagTitle);
	}
	
	public void verifyLoginFormOnKateSpadeRoEAccountPage(String loginAccountHeader, 
			String emailAddressPlaceHolderText, String passwordPlaceHolderText, String rememberMeCheckBoxText,
			String forgotPasswordLinkText, String signInButtonText, String createAnAccountButtonText) {
		accountPage.verifyLoginFormOnKateSpadeRoEAccountPage(loginAccountHeader, emailAddressPlaceHolderText, 
				passwordPlaceHolderText, rememberMeCheckBoxText, forgotPasswordLinkText, signInButtonText,
				createAnAccountButtonText);
	}

	public void verifyCountryLinkForFranceHasBeenModifiedInCountrySelectorPage(
			String countryLinkFranceFrench, String countryLinkFranceEnglish) {
		countrySelection.verifyCountryLinkForFranceHasBeenModifiedInCountrySelectorPage(
				countryLinkFranceFrench, countryLinkFranceEnglish);
	}

	public void verifyHeaderTextInCountrySelectorPage(String shippingToLabel, String countryFlag, 
			String country, String countryCurrency) {
		countrySelection.verifyHeaderTextInCountrySelectorPage(shippingToLabel, countryFlag, 
				country, countryCurrency);
	}

	public void verifyShippingMethod(String shippingMethod) {
		shipngpage.verifyShippingMethod(shippingMethod);	
	}
	
	public void selectCountryFromDropDown(String selectCountry) {
		shipngpage.selectCountryFromDropDown(selectCountry);
	}

	public void fillingOfDetails(String guestMailId, String fname, String lname, String address, String zipcode,
			String city, String phno) {
		shipngpage.fillingOfDetails(guestMailId, fname, lname, address, zipcode,
				 city, phno);
	}

	public void verifyFieldsAreDisplayed() {
		accountPage.verifyFieldsOnAddAddressModal();
	}
	
	public void verifyAddressDetailsCollapseWhenSearchClicked() {
		accountPage.clickOnSearchLink();
		accountPage.verifyAddressFeildsNotDisplayed();
	}

	public void verifyNewAddressFindFieldByName(String placeholder) {
		accountPage.verifyNewAddressFindFieldByName(placeholder);
	}

	public void verifyPositionOfNewAddressFindField() {
		accountPage.verifyPositionOfNewAddressFindField();		
	}
	
	public void enterCity(String city) {
		shipngpage.enterCity(city);
	}

	public void verifyFieldsAreDisplayedForShippingAddress() {
		shipngpage.verifyFieldsAreDisplayedForShippingAddress();	
	}

	public void verifyNewAddressFindFieldByNameOnShippingPage(String placeholder) {
		shipngpage.verifyNewAddressFindFieldByNameOnShippingPage(placeholder);	
	}

	public void verifyPositionOfNewAddressFindFieldOnShippingPage() {
		shipngpage.verifyPositionOfNewAddressFindFieldOnShippingPage();
	}
		
	public void verifyPositionOfEnterManuallyLink() {
		accountPage.verifyPositionOfEnterManuallyLink();
	}

	public void verifyChangeOfEnterManuallyToSearchOnClick() {
		accountPage.verifyChangeOfEnterManuallyToSearchOnClick();
	}

	public void verifySuggestionsAppearWhenAtleast3CharInput() {
		accountPage.verifySuggestionsAppearWhenAtleast3CharInput();
	}

	public void verifyAddressSearchIsNotCaseSensitive(String city) {
		accountPage.verifyAddressSearchIsNotCaseSensitive(city);
	}

	public void verifyPostCodesSearchByDifferentFormats(String zip) {
		accountPage.verifyPostCodesSearchByDifferentFormats(zip);	
	}

	public void verifyAddressFieldsPopulateAndInUpperCase(String city) {
		accountPage.verifyAddressFieldsPopulateAndInUpperCase(city);	
	}
	
	public String pickPriceOfSearchedProduct() {
		return productdetails.pickPriceOfSearchedProduct();
	}

	public void verifyProductPriceUsedByFranceFrenchLocaleWillBeSameForOtherRoECountries(
			String productPriceFranceLocale) {
		productdetails.verifyProductPriceUsedByFranceFrenchLocaleWillBeSameForOtherRoECountries(
				productPriceFranceLocale);
	}

	public void clickCrossBtn() {
		accountPage.clickCrossBtn();
	}

	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(String addressName, String firstName,
			String lastName, String postalCode, String phoneNumber) {
		accountPage.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(addressName, firstName, lastName, postalCode,
				phoneNumber);
	}

	public void verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(String addressName, String firstName,
			String lastName, String postalCode, String phoneNumber, String address1, String city) {
		accountPage.verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(addressName, firstName, lastName,
				postalCode, phoneNumber, address1, city);
	}

	public void verifyShoppingCartIsEmpty(String emptyCartHeader) {
		shoppingCart.verifyShoppingCartIsEmpty(emptyCartHeader);
	}

	public void verifyCountrySwitchDialogBoxDoesNotAppearWhenCartIsEmpty() {
		countrySelection.verifyCountrySwitchDialogBoxDoesNotAppearWhenCartIsEmpty();
	}

	public void verifyCartWillNotBeDeletedOnSwitchingWithinCountriesInRoESite(int cartQuantity, 
			String changeShippingDestinationMsg, String shippingLabel, String countryCurrency) {
		countrySelection.verifyCartWillNotBeDeletedOnSwitchingWithinCountriesInRoESite(cartQuantity, 
				changeShippingDestinationMsg, shippingLabel, countryCurrency);
	}

	public int noOfItemsInCart() {
		return topBanner.noOfItemsInCart();
	}

	public void navigateToHomePage() {
		topBanner.navigateToHomePage();
	}

	public void verifyFieldsAppearOnShipngPage() {
		shipngpage.verifyFieldsAppearOnShipngPage();
	}
	
	public void verifyPositionOfNewAddressFindFieldOnShipngPage() {
		shipngpage.verifyPositionOfNewAddressFindFieldShipngPage();
	}
	
	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsedOnShipngPage(String firstName, String lastName, String phoneNumber) {
		shipngpage.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(firstName, lastName,phoneNumber);
	}
	
	public void verifyNewFindAddressFieldIsNotRequiredFieldWhenExpandedOnShipngPage(String username, String firstName,
			String lastName, String postalCode, String phoneNumber, String address1, String city) {
		shipngpage.verifyNewFindAddressFieldIsNotRequiredFieldWhenExpanded(username, firstName, lastName,
				postalCode, phoneNumber, address1, city);
	}
	
	public void clickOnContinuePayment() {
		shipngpage.clickOnContinuePayment();
	}

	public void verifyAddressFieldsPopulateAndInUpperCaseOnShipngPage(String city) {
		shipngpage.verifyAddressFieldsPopulateAndInUpperCaseOnShipngPage(city);
	}

	public void verifyFieldsAppearOnBillingPage() {
		paymentpage.verifyFieldsAppearOnBillingPage();
	}

	public void verifyPositionOfNewAddressFindFieldOnBillingPage() {
		paymentpage.verifyPositionOfNewAddressFindFieldOnBillingPage();
	}

	public void verifyAddressFieldsPopulateAndInUpperCaseOnBillingPage(String city) {
		paymentpage.verifyAddressFieldsPopulateAndInUpperCaseOnBillingPage(city);
	}

	public void verifyNewFindAddressFieldIsRequiredFieldWhenCollapsedOnBillingPage(String firstName, String lastName, String phoneNumber) {
		paymentpage.verifyNewFindAddressFieldIsRequiredFieldWhenCollapsed(firstName, lastName,phoneNumber);
	}
	
	public void clickOnEnterManuallyLink() {
		paymentpage.clickOnEnterManuallyLink();
	}

	public void verifyItemsHaveBeenRemovedFromShoppingBagOnSwitchingCountryOtherThanRoESites(int cartQuantity,
			String shippingLabel, String countryOtherThanRoESite, String countryCurrency) {
		landingPage.verifyItemsHaveBeenRemovedFromShoppingBagOnSwitchingCountryOtherThanRoESites
		(cartQuantity, shippingLabel, countryOtherThanRoESite, countryCurrency);
	}

	public void verifyUGCContentSlotAboveFooter() {
		landingPage.verifyUGCContentSlotAboveFooter();
	}

	public void verifyUGCContentSlotAboveRatingsAndReviewSection() {
		productdetails.verifyUGCContentSlotAboveRatingsAndReviewSection();
	}

	public void verifyUGCContentSlotByTitle(String title) {
		landingPage.verifyUGCContentSlotByTitle(title);
	}

	public void verifyUGCContentSlotByDescription(String description) {
		landingPage.verifyUGCContentSlotByDescription(description);
	}

	public void confirmNavigationOnClickingViewGalleryButton() {
		productdetails.confirmNavigationOnClickingViewGalleryButton();
	}

	public void confirmNavigationOnClickingSubmitYourPhotoButton() {
		productdetails.confirmNavigationOnClickingSubmitYourPhotoButton();
	}

	public void verifyErrorMessageForAddressField(String errormsg) {
		accountPage.verifyErrorMessageForAddressField(errormsg);
	}

	public void verifyCheckboxAtLogin(String userName, String password) {
		topBanner.verifyCheckboxAtLogin(userName , password);
	}

	public void verifyCheckBoxAtWishListLogin(String userName, String password) {
		wishlistpage.verifyCheckBoxAtWishListLogin(userName , password);
	}

	public void navigateToWishListPage() {
		topBanner.navigateToWishListPage();
	}
	
	public void verifyUserCanAddANewCard() {
		accountPage.verifyUserCanAddANewCard();
	}

	public void verifyUserCanRemoveSavedCard(int i) {
		accountPage.verifyUserCanRemoveSavedCard(i);
	}

//	public void verifySavedCardRemoved() {
//		accountPage.verifySavedCardRemoved();
//	}
//	
}
