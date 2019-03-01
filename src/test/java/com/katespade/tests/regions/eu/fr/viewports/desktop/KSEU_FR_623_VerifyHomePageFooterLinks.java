package com.katespade.tests.regions.eu.fr.viewports.desktop;

import com.katespade.tests.KateSpadeTest;
import java.util.Map;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class KSEU_FR_623_VerifyHomePageFooterLinks extends KateSpadeTest {
	Map<String, String> url;

	@BeforeClass(groups = { "desktop", "mobile" })
	public void start_test_session() {
		url = testData.get("BASE URL").get(0);
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep01_Launch_Application_URL() {
		dsl.launchApplication(url.get("URL"));
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep02_VerifyAboutUsFooterLinks() {
		dsl.verifyAboutUsFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep03_VerifyCustomerCareFooterLinks() {
		dsl.verifyCustomerCareFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep04_verifyCareersFooterLinks() {
		dsl.verifyCareersFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep05_VerifyMyAccountFooterLinks() {
		dsl.verifyMyAccountFooterLinks();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep06_verifyEmailSignUpFunctionality() {
		dsl.verifyEmailSignUpFunctionalityInFooter();
	}
		
	@Test(groups = { "desktop", "mobile" })
	public void TestStep07_VerifyComapnyLinksAndThePageThatTheyNavigateTo() {
		dsl.verifyComapnyLinkNameInFooter();
		dsl.verifyComapnyLinksAndThePageThatTheyNavigateTo();
	}

	@Test(groups = { "desktop", "mobile" })
	public void TestStep08_VerifySocialMediaLinksAreActive() {
		dsl.verifySocialMediaIconsFR();
	}
	
	@Test(groups = { "desktop", "mobile" })
	public void TestStep09_goToYourOrderFromFooter() {
		dsl.goToYourOrderFromFooter();
	}


}