package com.cctech.challenge.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.cctech.challenge.pages.PracticeFormPage;
import com.cctech.challenge.utils.ExtentReportManager;

public class TC005_Submit_form_with_invalid_mobile_number_less_than_10_digits extends BaseClass {

	@Test
	void Submit_form_with_valid_data() {
		ExtentReportManager.getTest().info("Submit_form_with_valid_data is started..");
		PracticeFormPage formpage = new PracticeFormPage(getDriver());
		ExtentReportManager.getTest().info("Navigated to Demoqa PracticeForm Page");

		// Enter First Name
		formpage.enterFirstName(p.getProperty("firstname"));
		ExtentReportManager.getTest().info("Entered First Name:" + " " + p.getProperty("firstname"));

		// Enter Last Name
		formpage.enterLastName(p.getProperty("lastname"));
		ExtentReportManager.getTest().info("Entered Last Name:" + " " + p.getProperty("lastname"));

		// Enter invalid Email
		formpage.enterEmail(p.getProperty("email"));
		ExtentReportManager.getTest().info("Entered invalid Email:" + " " + p.getProperty("email"));

		// Select Gender
		formpage.selectGender(p.getProperty("gender"));
		ExtentReportManager.getTest().info("Selected Gender: " + " " + p.getProperty("gender"));

		// Enter Mobile Number
		formpage.enterMobile(p.getProperty("Invalid.mobile"));
		ExtentReportManager.getTest().info("Entered Invalid Mobile Number is: " + " " + p.getProperty("Invalid.mobile"));

		// Enter Date of Birth
		// formpage.enterDob("25 Jan 2002");
		formpage.selectDateOfBirth(p.getProperty("dob.day"), p.getProperty("dob.month"), p.getProperty("dob.year"));
		ExtentReportManager.getTest().info("Entered Date of Birth: " + p.getProperty("dob.day") + "  "
				+ p.getProperty("dob.month") + " " + p.getProperty("dob.year"));

		// Enter Subject
		formpage.enterSubject(p.getProperty("subject1"));
		ExtentReportManager.getTest().info("Entered Subject: " + p.getProperty("subject1"));

		// Select Hobbies
		formpage.selectReading();
		ExtentReportManager.getTest().info("Selected Hobby: Reading");

		// Upload Picture
		formpage.uploadPicture("C:\\Workspaces\\30-10-2024 On words\\demoqa-automation\\pictureprofile\\profile.jpg");
		ExtentReportManager.getTest().info("Uploaded Picture");

		// Enter Address
		formpage.enterAddress(p.getProperty("address"));
		ExtentReportManager.getTest().info("Entered Current Address:" + " " + p.getProperty("address"));

		// select state
		formpage.selectState(p.getProperty("state"));
		ExtentReportManager.getTest().info("Selected State: " + " " + p.getProperty("state"));

		// Select City
		formpage.selectCity(p.getProperty("city"));
		ExtentReportManager.getTest().info("Selected City:" + " " + p.getProperty("city"));

		// Submit
		formpage.clickSubmit();
		ExtentReportManager.getTest().info("Clicked Submit Button");

		String expectedResult = formpage.formClass();
		ExtentReportManager.getTest().info("Expected result is form have class attribute value is :" + expectedResult);

		String ActualResult = formpage.formClass();
		ExtentReportManager.getTest().info("Actual result is form have class attribute value is :" + ActualResult);

		ExtentReportManager.getTest().info(
				"is expectedResult Equal to ActualResult: " + " " + expectedResult.equalsIgnoreCase(ActualResult));

		Assert.assertEquals(ActualResult, expectedResult);
	}

}
