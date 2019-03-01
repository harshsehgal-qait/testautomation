package com.katespade.tests.apidriven;

import java.util.Map;

import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.qait.automation.DSL;

public class CoachTestCase {

    public DSL dsl;
    @SuppressWarnings("rawtypes")
	public Map testData;

    @BeforeClass
    @Parameters("browser")
    public void __setUpClass(@Optional String browser) throws Exception {
        dsl = new DSL(this.getClass().getSimpleName(), browser);
        testData = dsl.testData;
    }

    @AfterClass
    public void ztearDownClass() throws Exception {
        dsl.closeBrowserSession();
    }
    
    @AfterMethod
    public void takeScreenShotOnFailure(ITestResult testResult) throws Exception{
        dsl.takeScreenShotOnException(testResult);
    }
}
