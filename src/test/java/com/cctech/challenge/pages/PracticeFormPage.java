package com.cctech.challenge.pages;

import java.util.List;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.cctech.challenge.tests.BaseClass;

public class PracticeFormPage extends BaseClass {
	private final WebDriver driver;

	public PracticeFormPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	// Existing Elements
	@FindBy(id = "firstName")
	private WebElement firstNameInput;
	@FindBy(id = "lastName")
	private WebElement lastNameInput;
	@FindBy(id = "userEmail")
	private WebElement emailInput;
	@FindBy(id = "userNumber")
	private WebElement mobileInput;
	// date of birt elements
	@FindBy(id = "dateOfBirthInput")
	private WebElement dobInput;

//	@FindBy(xpath="//select[@class=\"react-datepicker__month-select\"]") 
//	private WebElement monthdropdown;

	@FindBy(xpath = "//select[@class=\"react-datepicker__month-select\"]")
	private WebElement monthdropdown;

	@FindBy(xpath = "//select[@class=\"react-datepicker__year-select\"]")
	private WebElement yeardropdown;

	@FindBy(id = "subjectsInput")
	private WebElement subjectsInput;
	@FindBy(id = "currentAddress")
	private WebElement addressInput;
	@FindBy(id = "uploadPicture")
	private WebElement uploadPicInput;
	@FindBy(id = "submit")
	private WebElement submitButton;
	@FindBy(id = "example-modal-sizes-title-lg")
	private WebElement thanksModal;

	@FindBy(id = "userForm")
	private WebElement formElement;

	// Gender Radio Buttons

	@FindBy(xpath = "//label[@for=\"gender-radio-1\"]")
	private WebElement maleRadio;

	@FindBy(xpath = "//label[@for=\"gender-radio-2\"]")
	private WebElement femaleRadio;
	@FindBy(xpath = "//label[@for=\"gender-radio-3\"]")
	private WebElement otherRadio;

	// Hobbies Checkboxes
	@FindBy(xpath = "//label[@for=\"hobbies-checkbox-1\"]")
	private WebElement sportsCheckbox;
	@FindBy(xpath = "//label[@for=\"hobbies-checkbox-2\"]")
	private WebElement readingCheckbox;
	@FindBy(xpath = "//label[@for=\"hobbies-checkbox-3\"]")
	private WebElement musicCheckbox;

	// State & City
	@FindBy(id = "state")
	private WebElement stateDropdown;
	@FindBy(id = "react-select-3-input")
	private WebElement stateInput;

	@FindBy(id = "city")
	private WebElement cityDropdown;
	@FindBy(id = "react-select-4-input")
	private WebElement cityInput;

	// Return WebElement
	public WebElement returnFirstNameInput() {
		return firstNameInput;
	}

	public WebElement returnLastNameInput() {
		return lastNameInput;
	}

	public WebElement returnEmailInput() {
		return emailInput;
	}

	public WebElement returnMobileInput() {
		return mobileInput;
	}

	public WebElement returnDobInput() {
		return dobInput;
	}

	public WebElement returnSubjectsInput() {
		return subjectsInput;
	}

	public WebElement returnAddressInput() {
		return addressInput;
	}

	public WebElement returnUploadPicInput() {
		return uploadPicInput;
	}

	public WebElement returnSubmitButton() {
		return submitButton;
	}

	public WebElement returnThanksModal() {
		return thanksModal;
	}

	// ---------------- Actions (methods to interact with elements) ----------------
	public void enterFirstName(String firstname) {
		scrollToElement(firstNameInput);
		elementToBeClicable(firstNameInput);
		firstNameInput.clear();
		firstNameInput.sendKeys(firstname);
	}

	public void enterLastName(String lastname) {
		scrollToElement(lastNameInput);
		elementToBeClicable(lastNameInput);
		lastNameInput.clear();
		lastNameInput.sendKeys(lastname);
	}

	public void enterEmail(String userEmail) {
		scrollToElement(emailInput);
		elementToBeClicable(emailInput);
		emailInput.clear();
		emailInput.sendKeys(userEmail);
	}

	public void selectGender(String gender) {
		String genderinput = gender;
		switch (genderinput.toLowerCase()) {
		case "male":
			scrollToElement(maleRadio);
			elementToBeClicable(maleRadio);
			actions.moveToElement(maleRadio);
			maleRadio.click();
			break;

		case "female":
			scrollToElement(femaleRadio);
			elementToBeClicable(femaleRadio);
			femaleRadio.click();
			break;

		case "other":
			scrollToElement(otherRadio);
			elementToBeClicable(otherRadio);
			otherRadio.click();
			break;

		default:
			System.out.println("Gender is invalid");

		}

	}

	public void enterMobile(String mobile) {
		scrollToElement(mobileInput);
		elementToBeClicable(mobileInput);
		mobileInput.clear();
		mobileInput.sendKeys(mobile);
	}

	public void enterDob(String dob) {
		scrollToElement(dobInput);
		elementToBeClicable(dobInput);
		dobInput.click();
		dobInput.clear();
		dobInput.sendKeys(dob);
	}

	public void selectDateOfBirth(String day, String month, String year) {
		scrollToElement(dobInput);
		elementToBeClicable(dobInput);
		// Click the DOB field to open date picker // Open calendar
		waitForElementToBeVisible(dobInput);
		dobInput.click();

		// Select year
		scrollToElement(yeardropdown);
		waitForElementToBeVisible(yeardropdown);
		// select(yeardropdown).selectByVisibleText(year);
		Select selectyear = new Select(yeardropdown);
		selectyear.selectByVisibleText(year);

		// Select month

		// Normalize month (capitalize first letter)
		String formattedMonth = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase();
		scrollToElement(monthdropdown);
		waitForElementToBeVisible(monthdropdown);
		// select(monthdropdown).selectByVisibleText(formattedMonth);
		Select selectmonth = new Select(monthdropdown);
		selectmonth.selectByVisibleText(formattedMonth);

		// Select day (note: avoid duplicate classes for outside days)
		// Select valid day of current month
		// Select valid day (exclude outside-month days)
		List<WebElement> days = driver.findElements(By.xpath(
				"//div[contains(@class,'react-datepicker__day') and not(contains(@class,'outside-month')) and text()='"
						+ day + "']"));

		for (WebElement d : days) {
			if (d.isDisplayed()) {
				d.click();
				break;
			}
		}
	}

	public void enterSubject(String subject) {
		scrollToElement(subjectsInput);
		elementToBeClicable(subjectsInput);
		subjectsInput.sendKeys(subject);
		subjectsInput.sendKeys(Keys.ENTER);
	}

	// Hobbies Checkbox Selection
	public void selectSports() {
		scrollToElement(sportsCheckbox);
		// scrollIntoView(sportsCheckbox);
		sportsCheckbox.click();
	}

	public void selectReading() {
		scrollToElement(readingCheckbox);
		// scrollIntoView(readingCheckbox);
		readingCheckbox.click();
	}

	public void selectMusic() {
		scrollToElement(musicCheckbox);
		// scrollIntoView(musicCheckbox);
		musicCheckbox.click();
	}

	public void uploadPicture(String filePath) {
		scrollToElement(uploadPicInput);
		elementToBeClicable(uploadPicInput);
		uploadPicInput.sendKeys(filePath); // file path from local system
	}

	public void enterAddress(String address) {
		scrollToElement(addressInput);
		elementToBeClicable(addressInput);
		addressInput.clear();
		addressInput.sendKeys(address);
	}

	// select state
	public void selectState(String stateName) {
		scrollToElement(stateDropdown);
		waitForElementToBeVisible(stateDropdown);
		stateDropdown.click(); // click on state dropdown
		stateInput.sendKeys(stateName); // type the state name
		stateInput.sendKeys(Keys.ENTER); // press enter to select

	}

	// select city
	public void selectCity(String cityName) {
		scrollToElement(cityDropdown);
		waitForElementToBeVisible(cityDropdown);
		cityDropdown.click(); // click on city dropdown
		cityInput.sendKeys(cityName); // type the city name
		cityInput.sendKeys(Keys.ENTER); // press enter to select

	}

	public void clickSubmit() {
		scrollToElement(submitButton);
		submitButton.click();
	}

	public String getThanksMessage() {
		scrollToElement(thanksModal);
		return thanksModal.getText();
	}

	public String formClass() {
		scrollToElement(formElement);
		return formElement.getAttribute("class");
	}

	public boolean isThanksModalVisible() {
		try {
			scrollToElement(thanksModal);
			thanksModal.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		}
		return false;
	}

}
