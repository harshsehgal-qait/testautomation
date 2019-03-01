package com.katespade.tests.regions.eu.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_802_Verify_404_Error_Page extends KateSpadeTest {

	Map<String, String> error404URL;
	Map<String, String> product;
	Map<String, String> customerServiceMessage;
	Map<String, String> content2;
	
	String title = "we can't seem to find the page you're looking for";
	String titleFr = "nous ne trouvons pas la page que vous recherchez.";

	/*
	 * 404 page is not configured in dev env
	 */
	
	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		error404URL = testData.get("BASE URL").get(1);
		product = testData.get("Products").get(1);
		customerServiceMessage = testData.get("Page Names").get(8);
		content2 = testData.get("Page Names").get(3);
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step01_Launch_Application_URL() {
		dsl.launchApplication(error404URL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step02_Verify_404_Page_Custommer_Service_Message() {
		dsl.verify_404_page(title, titleFr);
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step03_Verify_Content_Asset_In_404_Page() {
		dsl.verifyContentAssetIn404Page();
	}

	@Test(groups = { "desktop", "mobile" })
	public void Test_Step04_Verify_Customer_Care_Functionality_In_404_Page() {
		dsl.verifyCustomerCareFunctionalityIn404Page();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step05_Verify_Search_Functionality_In_404_Page() {
		dsl.verifySearchFunctionalityIn404Page();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void Test_Step06_Verify_Home_Btn_Functionality_In_404_Page() {
		dsl.verifyHomeBtnFunctionalityIn404Page(error404URL.get("URL"));
	}	
	
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep03_Verify_404_Page_Custommer_Service_Message() {
//		dsl.verify_404_page_Customer_Service(customerServiceMessage.get("Text Verification"));
//	}
//
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep04_Verify_Customer_Care_Left_Hand_Navigation_On_404_Error_Page() {
//		dsl.verifyCustomerCareLeftHandNavigation(content2.get("Text Verification"));
//	}
//
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep05_Verify_404_Page_Try_New_Search() {
//		dsl.verify_404_page_Try_New_Search(search);
//	}
//
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep06_Verify_404_Page_Search_Functionality() {
//		dsl.verify_404_Page_Search_Functionality(product.get("Style Number"));
//	}
//	
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep07_Verify_404_Page_Is_Displayed_When_Product_ID_Not_Found() {
//		dsl.verify404PageIsDisplayedWhenProductIDNotFoundInUrl(product.get("Style Number"), title);
//	}
//
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep08_Select_Product_Category_From_Header() {
//		dsl.clickCategoryOnHeader(categoryIndex);
//	}
//
//	@Test(groups = { "desktop", "mobile" })
//	public void TestStep09_Verify_404_Page_Is_Displayed_When_Product_Category_Not_Found() {
//		dsl.verify404PageIsDisplayedWhenProductCategoryNotFoundInUrl(title);
//	}

}
