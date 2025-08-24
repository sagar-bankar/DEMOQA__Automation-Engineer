package com.cctech.challenge.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cctech.challenge.pages.PracticeFormPage;
import com.cctech.challenge.utils.ExtentReportManager;

public class TC003_Submit_form_with_all_fields_empty extends BaseClass {

	@Test
	void Submit_form_with_valid_data() {
		ExtentReportManager.getTest().info("Submit_form_with_valid_data is started..");
		PracticeFormPage formpage = new PracticeFormPage(getDriver());
		ExtentReportManager.getTest().info("Navigated to Demoqa PracticeForm Page");
		
		formpage.clickSubmit();
		ExtentReportManager.getTest().info("Clicked Submit Button");

		// 1) Form should have Bootstrap validation class
		String errorhandleValidataion = formpage.formClass();
		
		String ActualResult = errorhandleValidataion;
		ExtentReportManager.getTest().info("Actual result is form have class attribute value is :" + ActualResult);

		String ExpectedResult = "was-validated";
		ExtentReportManager.getTest().info("Expected result is form have class attribute value is :" + ExpectedResult);

		ExtentReportManager.getTest().info(
				"is expectedResult Equal to ActualResult: " + " " + ExpectedResult.equalsIgnoreCase(ActualResult));
		
		Assert.assertTrue(ActualResult.equalsIgnoreCase(ExpectedResult),"TC003_Submit_form_with_all_fields_empty is failed");
	}

}
