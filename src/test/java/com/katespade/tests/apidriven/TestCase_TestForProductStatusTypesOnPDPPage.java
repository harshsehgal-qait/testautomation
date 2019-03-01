package com.katespade.tests.apidriven;

import org.testng.annotations.BeforeClass;

/**
 *
 * @author Ramandeep <RamandeepsSingh@QAInfoTech.com>
 */
public class TestCase_TestForProductStatusTypesOnPDPPage extends CoachTestCase{

    String url;
    
    @BeforeClass
    public void launchApplication(){
        url = dsl.testData.get("d1").data.get("BASE URL").get(0).get("URL");
        dsl.launchApplication(url);
    }
    
   
}
