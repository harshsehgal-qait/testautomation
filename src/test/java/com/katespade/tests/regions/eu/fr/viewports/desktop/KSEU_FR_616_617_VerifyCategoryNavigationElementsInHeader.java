/*
 - KSEU-616 :Category Navigation: General Layout Considerations
 - KSEU_617 :Category Navigation: Included Elements-Desktop
 - 
  * */
package com.katespade.tests.regions.eu.fr.viewports.desktop;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.katespade.tests.KateSpadeTest;

public class KSEU_FR_616_617_VerifyCategoryNavigationElementsInHeader extends KateSpadeTest {

	Map<String, String> homepageURL;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		homepageURL = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(homepageURL.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_TopLevelCategoriesColorAndMegaMenu() {
		dsl.verifyTopLevelCategoriesColorAndMegaMenu();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyOnClickingCategoryUserLandsOnCorrectPage() {
		dsl.verifyOnClickingCategoryUserLandsOnCorrectPage();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_VerifyMegaMenuElements() {
		dsl.VerifyMegaMenuElementsForFr();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyNewSubcategoriesLinks() {
		dsl.verifySubcategoriesOfHeaderLinks();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_verifySubcategoriesColorChangesToGreenOnHovering() {
		dsl.verifyHandbagSubcategyColorChangesToGreenOnHovering();
	}
}
