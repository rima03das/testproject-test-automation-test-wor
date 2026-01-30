package com.project.base;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.security.SecureRandom;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Random;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentTest; //not in dev
import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {

	public static WebDriver driver;
	public static WebDriver ogdriver;
	public static Properties prop;
	public static int screenshotCount = -1;
	public static WebDriverWait wait;
	public static String strCurrPgTitle;
	public static String dirName = "";
	public static ExtentTest logInfo = null; // not in dev
	public static String url = "";
	public static List<String> actTasksTableColumnSequence;
	public static List<String> expTasksTableColumnSequence;
	public static List<String> actDealsTableColumnSequence;
	public static List<String> expDealsTableColumnSequence;

	public static String existApplicantName = "";
	public static String existDealID = "";
	public static String existDealOwner = "";
	public static String actualDealOwner = "";

	public static String contChannelName = "";
	public static String contChannel = "";
	public static String contSubchannelName = "";
	public static String contSubchannel = "";
	public static String contDealSource = "";

//Contact Notes
	public static String existingOriginalSubject, existingOriginalDescription, existingOriginalSubjectRevisiting,
			existingOriginalDescriptionRevisiting, firstNoteText, updatedFirstNoteText, newContactNoteSubject,
			singleUploadContactNoteSubject, multipleUploadContactNoteSubject;

//Deal Notes
	public static String existingDealOriginalSubject, existingDealOriginalDescription,
			existingDealOriginalSubjectRevisiting, existingDealOriginalDescriptionRevisiting, updatedDealSubject,
			updatedDealDescription, dealNoteOriginalSubject, dealNoteOriginalDescription, newDealNoteSubject,
			firstDealNoteText, updatedFirstDealNoteText, dealNoteSubjectOriginal, dealNoteDescriptionOriginal,
			dealNoteSubjectUpdated, dealNoteDescriptionUpdated, singleUploadDealNoteSubject,
			multipleUploadDealNoteSubject, provinceAddRole;

//	Account
	public static String acctExistName, accountType, createdAccountName, createdAccountNameRevisiting,
			selectedAccountType, selectedAccountTypeRevisiting, firstExistingAccountName, searchedExistingAccountName;

//Create Deal
	public static String transactionTypeNonPurchase, lEmail;

	// contact
	public static String contSalutation, contSuffix, contGender, contMaritalStatus, contLangPreference, contReportingTo,
			contCountry, contUnit, contStreetNumber, contStreetName, contStreetType, contStreetDirection, contCity,
			contProvince, contState, contPostalCode, contAccount, contTransactionType, contNoOfDep, contContOwner,
			contUniqueEmailID, contExistEmailID, contExistPhoneNumber, contExistApplicantName, contExistDealID;

	// task management dashboard
	public static String taskExistStatus, taskExistSubject, taskExistDueDate, taskExistAssignee, taskExistDealID,
			taskExistContactName, currTaskDetails;

	// task edit
	public static String taskEditSubject, taskEditDueDate, taskEditDescription, taskEditRelatedTo, taskEditDealID,
			taskEditContactName, taskEditAssignee, taskEditStatus;
	public static String taskUpdateSubject, taskUpdateDescription, taskUpdateRelatedTo, taskUpdateAssignee,
			taskUpdateContactName, taskUpdateDealID, taskUpdateStatus;

	// task add
	public static String taskAddSubject, strFullDueDate, taskAddDueDate, taskAddDescription, taskAddRelatedTo,
			taskAddDealID, taskAddContactName, taskAddAssignee, taskAddStatus, actTaskAddUpdateDetails;

//Deal Dashboard
	public static String existTransactionType, existApplicationType, existClosingDate, existLoanAmount,
			existChannelSource, existChannelCampaign, existReferral, existFinmoId, existDealEmailId, existDealPhone,
			extractedValue = "";

//	Deal Info Deal Details Page
	public static String dealOwner, transactionType, transactionTypeR, transactionTypeP, applicationType,
			leadSourceDeal, campaignNameDeal, referal, intendedUseOfFunds, actualTransactionType,
			actualApplicationType, actualClosingDate, actualLoanAmount, actualLeadSourceDeal,
			actualCampaignNameDeal, actualCampaignTracker, actualReferral, actualCommitmentNumber, actualFinmoId,
			actualLoginEmailApp, actualPhoneApp, actualFirstNameApp, actualLastNameApp, actualLoginEmailAppAgain,
			actualPhoneAppAgain, actualFirstNameAppAgain, actualLastNameAppAgain, updatedLoginEmailApp, updatedPhoneApp,
			updatedFirstNameApp, updatedLastNameApp, dealIDFromNewDeal = "";

//Applicant Details
	public static String maritalStatusApp, langPreferenceApp, dependentsApp, genderApp, firstTimeBuyerApp, firstNameApp,
			lastNameApp = "";

//CoApplicant Details
	public static String maritalStatusCoApp, langPreferenceCoApp, dependentsCoApp, genderCoApp,
			firstTimeBuyerCoApp = "";

//Create CApplicant
	public static String fnameCoApp,exfnameCoApp, lEmailCoApp, actualFirstNameCoApp, actualLastNameCoApp, actualLoginEmailCoApp,
			actualCommuEmailCoApp, actualPhoneCoApp, actualFirstNameCoAppAgain, actualLastNameCoAppAgain,
			actualLoginEmailCoAppAgain, actualCommuEmailCoAppAgain, actualPhoneCoAppAgain, updatedFirstNameCoApp,
			updatedLastNameCoApp, updatedLoginEmailCoApp, updatedCommuEmailCoApp, updatedPhoneCoApp = "";

//Applicant Living address
	public static String streetTypeLivAddApp, streetDirectionLivAddApp, provinceLivAddApp,
			livingStatusTypeLivAddApp = "";
	public static String moveInOutDateValidationMessage, cityLivAddApp, streetNoLivAddApp, postalCodeAddApp,
			streetNameLivAddApp, actualSingleLineAddAppFirstRow;

//Applicant Employment 
	public static String incomeSourceApp, provinceEmpApp, employmentTypeApp, paymentType, industryTypeApp,
			EarnedIncomeType, paymentFrequencyApp, startEndDateValidationMessage, emailEmpApp = "";

//Applicant Additional Income
	public static String incomeTypeApp,fetchincomeTypeTbl, incomePeriodApp, paymentFrequencyAnnual, isCurrentSourceOfIncomeApp,
			actualSingleLineAdditionalIncomeAppFirstRow, updatedActualSingleLineAdditionalIncomeAppFirstRow = "";

//CoApplicant Additional Income
	public static String incomeTypeCoApp, paymentFrequencyCoApp, incomePeriodCoApp, isCurrentSourceOfIncomeCoApp,
			actualSingleLineAdditionalIncomeCoAppFirstRow = "";

//Asset 
	public static String assetType, ownerAsset;

//Liabilities
	public static String ownerLiabilities, liabilityType, liabilityPayoff;

//Credit Pull
	public static String creditScore;

//Subject Property
	public static String occupancySP, streetTypeSP, streetDirectionSP, provinceSP, mLSListingSP, constructionTypeSP,
			propertyTypeSP, tenureSP, unitLivingSpaceSP, unitLotSizeSP, heatSP, waterInfoSP, styleSP, garageSizeSP,
			garageTypeSP, environmentalHazardSP, sewageInfoSP, includeInTDSSP, taxYearSP, taxPaidBySP, feesInclHeatSP,
			appraisalRequiredSP, appraisalStatusSP, appraiserSP, rentalOptionSP, purchasePrice, totalMonthlyExpensesSP,
			heatingMonthSP, anualTaxesSP, totalCondoFeesSP, updatedPurchasePrice, updatedTotalMonthlyExpensesSP,
			updatedHeatingMonthSP, updatedAnualTaxesSP, updatedTotalCondoFeesSP, propertyFromList, unitSP, streetNoSP,
			streetNameSP, StreetTypeSP, citySP, postalCodeSP, expectedsingleLineAddressSubjectProperty, subjectPropertySuccessMsg;

//Mortgage 
	public static String mortgageTypeM, premiumRateOverrideM, includeInMortgageM, lineOfBusinessM, rateTypeM,
			paymetScheduleM, frequencyM, compoundedPeriodM, termTypeM, propertyValue, mortgageSuccessMsg;

//Properties Owned
	public static String occupancyPO, ownershipPO, countryPO, streetTypePO, streetDirectionPO, rentalOptionPO,
			provincePO, isSellingPO, tdsCalculationPO, propertyValuePO, originalValuePO, totalCondoFeesPO,
			annualTaxesPO, heatingMonthPO, totalMonthlyExpensesPO, updatedPropertyValue, updatedOriginalValue,
			updatedTotalCondoFeesPO, updatedAnnualTaxesPO, updatedHeatingMonthPO, updatedTotalMonthlyExpensesPO,
			feesIncludingHeatPO;

//Properties Owned Mortgage
	public static String mortgageBalancePO, mortgagePaymentPO, mortgageInterestRatePO, mortgagePaymentFrequencyPO,
			RateTypePO, PayoffPO, updatedMortgageBalancePO, updatedMortgagePaymentPO, updatedMortgageInterestRatePO,
			labelPropertyOwnedMortgagePO;

	// contact dashboard variables
	public static int noOfRecords;
	public static List<String> lstActPagOptions, lstFirstTwoExistEmailIDs, expRoleFilterNameValue,
			actRoleFilterNameValue, expRoleFilterToolTipValue, actRoleFilterToolTipValue;
	public static List<WebElement> actPagOptions;
	public static String actRoleFilterToolTip, expRoleFilterToolTip, contExistName, contExistContactRole,
			contExistStreetAdd, contExistCity, contExistProvince, contExistCountry, contExistPostalCode, contAddRole;

	// contact add/update personal info variables
	public static String contAddSalutation, contAddSuffix, contAddGender, contAddMaritalStatus, contAddLangPreference,
			contAddReportingTo, contAddCountry, contAddStreetAddress, contAddUnit, contAddStreetNumber,
			contAddStreetName, contAddStreetType, contAddStreetDirection, contAddCity, contAddProvince, contAddState,
			contAddPostalCode, contAddZipCode, contAddAccount, contAddTransactionType, contAddNoOfDep, contAddContOwner,
			contAddDOB, contAddCommission, contAddAccCommission, contAddId, contAddWebsite, contShortRole, contAddFname,
			contAddMname, contAddLname, contAddPhone;

	// contact view personal info variable
	public static String contPersonalInfoViewLoginEmail, contPersonalInfoViewCommEmail, contPersonalInfoViewPhone,
			contPersonalInfoViewFax, contPersonalInfoViewWorkPhone, contPersonalInfoViewGender,
			contPersonalInfoViewMarital, contPersonalInfoViewDep, contPersonalInfoViewLangPref, contPersonalInfoViewDOB,
			contPersonalInfoViewUniqueID, contPersonalInfoViewAccount, contPersonalInfoViewReportingTo,
			contPersonalInfoViewURL, contPersonalInfoViewContOwn, contPersonalInfoViewAddress, contPersonalInfoViewName,
			contPersonalInfoViewFormattedName;
	public static String contPersonalInfoEditFName, contPersonalInfoEditPName, contPersonalInfoEditMName,
			contPersonalInfoEditLName, contPersonalInfoEditCommEmail;
	public static String contPersonalInfoEditPhone, contPersonalInfoEditWorkPhone, contPersonalInfoEditDOB,
			contPersonalInfoEditGender, contPersonalInfoEditMarital;
	public static String contPersonalInfoEditDep, contPersonalInfoEditSIN, contPersonalInfoEditLangPref,
			contPersonalInfoEditContOwner, contPersonalInfoEditContCountry, contPersonalInfoEditContUnit,
			contPersonalInfoEditFax, contPersonalInfoEditCommPercent, contPersonalInfoEditAccComm,
			contPersonalInfoEditUniqueID, contPersonalInfoEditAccount, contPersonalInfoEditReportingTo,
			contPersonalInfoEditURL, contPersonalInfoEditContStreetNum, contPersonalInfoEditContStreetName,
			contPersonalInfoEditContStreetType, contPersonalInfoEditContStreetDirection, contPersonalInfoEditContCity,
			contPersonalInfoEditContProvince, contPersonalInfoEditContPostalCode;

	public static String contPersonalInfoUpdateFName, contPersonalInfoUpdatePName, contPersonalInfoUpdateMName,
			contPersonalInfoUpdateLName, contPersonalInfoUpdateCommEmail;
	public static String contPersonalInfoUpdatePhone, contPersonalInfoUpdateWorkPhone, contPersonalInfoUpdateDOB,
			contPersonalInfoUpdateGender, contPersonalInfoUpdateMarital;
	public static String contPersonalInfoUpdateDep, contPersonalInfoUpdateSIN, contPersonalInfoUpdateLangPref,
			contPersonalInfoUpdateContOwner;

	private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

	@FindBy(xpath = "//div[@class='progress-loader']")
	public static WebElement imgLoader;

	@BeforeSuite
	public void loadConfig() throws Exception {
		FileInputStream fis = null;
		try {
			prop = new Properties();
			fis = new FileInputStream(System.getProperty("user.dir") + "/configuration/config.properties");
			prop.load(fis);
			// Close the FileInputStream
			fis.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeSuite(alwaysRun = true)
	public void launchApp() throws Exception {

		loadConfig();
		String browserName = prop.getProperty("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			options.addArguments("incognito");
			options.addArguments("--remote-allow-origins=*");
//			if (System.getenv("JENKINS_HOME") != null) { // 💡 Detects if running in Jenkins
//	        options.addArguments("--headless=new");   // ✅ Use headless mode
//	        options.addArguments("--no-sandbox");      // ✅ Recommended for Jenkins
//	        options.addArguments("--disable-dev-shm-usage"); // ✅ Recommended for Jenkins
//	        options.addArguments("--window-size=1920,1080"); // Optional but good
//		    }

			DesiredCapabilities cp = new DesiredCapabilities();
			cp.setCapability(ChromeOptions.CAPABILITY, options);
			options.merge(cp);
			ogdriver = new ChromeDriver(options);// removed for chatGPT
//			driver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("ie")) {
			WebDriverManager.iedriver().setup();
			driver = new InternetExplorerDriver();
		} else {
			System.out.println("Enter valid browser name");
		}
//      temporarily removed for chatGPT
		driver = new EventFiringDecorator<>(new CustomWebDriverListener(ogdriver)).decorate(ogdriver);

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(2));
		driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(150));
		/* Fetch data form Config File */
		String env = prop.getProperty("env");

		if (env.equalsIgnoreCase("QA")) {
			url = prop.getProperty("QAUrl");
		} else if (env.equalsIgnoreCase("UAT")) {
			url = prop.getProperty("UATUrl");
		} else if (env.equalsIgnoreCase("PreDev")) {
			url = prop.getProperty("PreDevUrl");
		} else if (env.equalsIgnoreCase("DEV")) {
			url = prop.getProperty("DEVUrl");
		}
		driver.get(url);
	}

	public boolean selectCalendarDate(WebElement objCal, String calDate, String format)  {
		/*
		 *************************************************************************************
		 * FunctionName: selectCalendarDate Input Parameters: elmCalendar, Date Output
		 * Parameters: n/a Description: This function will select date from the calendar
		 * Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		String[] arrPropDOB = calDate.split("/");
		if (arrPropDOB.length != 3) {
			throw new IllegalArgumentException("Invalid date format: " + calDate);
		}
		int intPropDay, intExpMonth, year1;

		// Handle format logic
		switch (format) {
		case "MM/dd/yyyy":
			intExpMonth = Integer.parseInt(arrPropDOB[0].trim());
			intPropDay = Integer.parseInt(arrPropDOB[1].trim());
			year1 = Integer.parseInt(arrPropDOB[2].trim());
			break;

		case "dd/MM/yyyy":
			intPropDay = Integer.parseInt(arrPropDOB[0].trim());
			intExpMonth = Integer.parseInt(arrPropDOB[1].trim());
			year1 = Integer.parseInt(arrPropDOB[2].trim());
			break;

		default:
			throw new IllegalArgumentException("Unsupported format: " + format);
		}

		String expMonth = "";
		String expShortMonth = "";
		switch (intExpMonth) {
		case 1:
			expMonth = "January";
			expShortMonth = "JAN";
			break;
		case 2:
			expMonth = "February";
			expShortMonth = "FEB";
			break;
		case 3:
			expMonth = "March";
			expShortMonth = "MAR";
			break;
		case 4:
			expMonth = "April";
			expShortMonth = "APR";
			break;
		case 5:
			expMonth = "May";
			expShortMonth = "MAY";
			break;
		case 6:
			expMonth = "June";
			expShortMonth = "JUN";
			break;
		case 7:
			expMonth = "July";
			expShortMonth = "JUL";
			break;
		case 8:
			expMonth = "August";
			expShortMonth = "AUG";
			break;
		case 9:
			expMonth = "September";
			expShortMonth = "SEP";
			break;
		case 10:
			expMonth = "October";
			expShortMonth = "OCT";
			break;
		case 11:
			expMonth = "November";
			expShortMonth = "NOV";
			break;
		case 12:
			expMonth = "December";
			expShortMonth = "DEC";
			break;
		default:
		}

		String strPropYear = arrPropDOB[2];
		int expYear = Integer.parseInt(strPropYear);

		// opens up the calendar pop up
		objCal.click();
		// fetches the current month and year
		WebElement objCurrMonthYear = driver.findElement(By.xpath(
				"//div[contains(@class,'MuiPickersFadeTransitionGroup')]/div[contains(@class,'MuiPickersCalendarHeader')]"));
		String currMonthYear = objCurrMonthYear.getText();

		// stores current month and year separately
		String[] arrMonthYear = currMonthYear.split(" ");
		String currMonth = arrMonthYear[0].trim();
		int currMonthNo = returnMonthNumericValue(currMonth);
		String currYear = arrMonthYear[1].trim();
		int actYear = Integer.parseInt(currYear);

		boolean boolYearMatch = false;
		boolean boolMonthMatch = false;

		// if expected year not matches current year
		if (expYear != actYear) {
			// opens up the 24 year options
			objCurrMonthYear.click();
			WebElement tblYearCalendar = driver.findElement(By.xpath("//div[contains(@class,'MuiPickersFadeTransitionGroup')]//div[@role='radiogroup']"));

			// click Next until expected year is displayed and clicked
			do {
				List<WebElement> lstYears = tblYearCalendar.findElements(By.tagName("button"));
				int size = lstYears.size();
				int count = 0;
				for (WebElement year : lstYears) {
					count++;
					String strYear = year.getText();
					int iterYear = Integer.parseInt(strYear);
					if (iterYear == expYear) {
						boolYearMatch = true;
						year.click();
						break;
					}
				}
				if (boolYearMatch == false) {
					if (expYear > actYear) {
						driver.findElement(By.xpath("//button[@aria-label='Next 24 years']")).click();
					} else {
						driver.findElement(By.xpath("//button[@aria-label='Previous 24 years']")).click();
					}
				}
			} while (boolYearMatch == false);

			// click on the expected month
			List<WebElement> lstMonths = driver
					.findElements(By.xpath("//button[contains(@class,'mat-calendar-body-cell')]"));
			for (WebElement month : lstMonths) {
				String strMonth = month.getText().trim();
				if (strMonth.equalsIgnoreCase(expShortMonth)) {
					month.click();
					break;
				} else if (strMonth.equalsIgnoreCase(expMonth)) {
					month.click();
					break;
				}

			}
			// select day
			List<WebElement> lstDays = driver.findElements(
					By.xpath("//button[contains(@class,'mat-calendar-body-cell')][not(contains(@class,'disabled'))]"));
			for (WebElement day : lstDays) {
				int intDay = Integer.parseInt(day.getText().trim());
				String strDay;

				if (intDay == intPropDay) {
					if (String.valueOf(intDay).length() == 1) {
						strDay = "0" + intDay;
					} else {
						strDay = "" + intDay;
					}
					day.click();
					strFullDueDate = expMonth + " " + strDay + ", " + strPropYear;
					break;
				}
			}
		} // if expected month not matches current month
		else if ((expYear == actYear)
				&& (!expShortMonth.equalsIgnoreCase(currMonth) && !expMonth.equalsIgnoreCase(currMonth))) {
			// click Next until expected month is displayed and clicked
			do {

				objCurrMonthYear = driver.findElement(By.xpath(
						"//div[contains(@class,'MuiPickersFadeTransitionGroup')]/div[contains(@class,'MuiPickersCalendarHeader')]"));
				currMonthYear = objCurrMonthYear.getText();

				arrMonthYear = currMonthYear.split(" ");
				currMonth = arrMonthYear[0].trim();
				currMonthNo = returnMonthNumericValue(currMonth);
				// check if current month already matches expected
				if (expShortMonth.equalsIgnoreCase(currMonth)) {
					boolMonthMatch = true;
					break;
				} else if (expMonth.equalsIgnoreCase(currMonth)) {
					boolMonthMatch = true;
					break;
				}

				if (intExpMonth > currMonthNo) {
					driver.findElement(By.xpath("//button[@aria-label='Next month']")).click();
				} else {
					driver.findElement(By.xpath("//button[@aria-label='Previous month']")).click();
				}

			} while (boolMonthMatch == false);

			// select day
			List<WebElement> lstDays = driver.findElements(By.xpath(
					"//div[contains(@class,'MuiDayCalendar-monthContainer')]//button[contains(@class,'MuiButtonBase')][not(contains(@class,'disabled'))]"));
			System.out.println(lstDays);
			for (WebElement day : lstDays) {
				int intDay = Integer.parseInt(day.getText().trim());
				String strDay;

				if (intDay == intPropDay) {
					if (String.valueOf(intDay).length() == 1) {
						strDay = "0" + intDay;
					} else {
						strDay = "" + intDay;
					}
					day.click();
					strFullDueDate = expMonth + " " + strDay + ", " + strPropYear;
					break;
				}
			}
		} else {
			// select day
			List<WebElement> lstDays = driver.findElements(By.xpath(
					"//div[contains(@class,'MuiDayCalendar-monthContainer')]//button[contains(@class,'MuiButtonBase')][not(contains(@class,'disabled'))]"));
			for (WebElement day : lstDays) {
				int intDay = Integer.parseInt(day.getText().trim());
				String strDay;

				if (intDay == intPropDay) {
					if (String.valueOf(intDay).length() == 1) {
						strDay = "0" + intDay;
					} else {
						strDay = "" + intDay;
					}
					day.click();
					strFullDueDate = expMonth + " " + strDay + ", " + strPropYear;
					break;
				}
			}
		}
		return true;
	}

	public int returnMonthNumericValue(String month) {
		switch (month.trim().toLowerCase()) {
		case "jan":
			return 1;
		case "feb":
			return 2;
		case "mar":
			return 3;
		case "apr":
			return 4;
		case "may":
			return 5;
		case "jun":
			return 6;
		case "jul":
			return 7;
		case "july":
			return 7;
		case "aug":
			return 8;
		case "sep":
			return 9;
		case "oct":
			return 10;
		case "nov":
			return 11;
		case "dec":
			return 12;
		default:
			return -1;
		}
	}

	public boolean checkPrevCalendarDatesAreDisabled(WebElement objCal) {
		// opens calendar pop up
		objCal.click();

		int currentDate = Integer.parseInt(driver
				.findElement(By.xpath(
						"//button[@type='button'][@aria-current='date'][not(contains(@class,'disabled'))]//span"))
				.getText());

		// Fetch all past date elements (disabled buttons)
		List<WebElement> pastDates = driver
				.findElements(By.xpath("//button[@type='button'][@tabindex=-1][contains(@class,'disabled')]"));

		boolean allPastDatesDisabled = true;

		// Loop through each past date and validate
		for (WebElement pastDate : pastDates) {
			String dateLabel = pastDate.getAttribute("aria-label"); // e.g., "February 1, 2025"
			int pastDateValue = Integer.parseInt(dateLabel.replaceAll("[^0-9]", "").trim());

			// Ensure past dates are less than the current date
			if (pastDateValue < currentDate) {
				if (pastDate.isEnabled()) {
					allPastDatesDisabled = false;
				}
			}
		}

		// Final assertion check
		if (allPastDatesDisabled) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean validateSorting(String columnXPath, boolean isAscending) {
		List<WebElement> columnElements = driver.findElements(By.xpath(columnXPath));

		List<String> actualList = new ArrayList<>();
		for (WebElement element : columnElements) {
			actualList.add(element.getText().trim());
		}

		List<String> sortedList = new ArrayList<>(actualList);

		// Custom sorting: Blank -> Numbers -> Alphabets
		Comparator<String> customComparator = (a, b) -> {
			if (a.isEmpty())
				return -1; // Blank first
			if (b.isEmpty())
				return 1;
			boolean aIsNumeric = a.matches("\\d+");
			boolean bIsNumeric = b.matches("\\d+");

			if (aIsNumeric && bIsNumeric)
				return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
			if (aIsNumeric)
				return -1; // Numbers before Alphabets
			if (bIsNumeric)
				return 1;
			return a.compareToIgnoreCase(b); // Alphabetical order
		};

		sortedList.sort(customComparator);
		if (!isAscending)
			Collections.reverse(sortedList);

		System.out.println("Actual Order  : " + actualList);
		System.out.println("Expected Order: " + sortedList);

		return actualList.equals(sortedList);
	}

	public boolean performOperationOnElement(WebElement el, String operationType, String option,
			WebElement objAutoSuggest, String optSuggest) throws InterruptedException {
		boolean retVal = false;

		operationType = (operationType.toUpperCase()).trim();
		// Wait for loader to disappear before interacting
		waitForLoaderToDisappear("//div[@class='infinity-loader']");
		// wait for a webelement to be visible
		WebElement element = waitForElement(el, "visibility");

		scrollIntoView(el);

		// highlight the object if the configuration is set to yes
		if (prop.getProperty("highlight").equals("yes")) {
			highlightElement(element);
		}
		// extract the element attribute
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		// check if the webelement is visible then
		if (element != null) {
			// check if the webelement is enabled then
			if (element.isEnabled()) {
				// perform operation based on the type
				switch (operationType) {
				case "SELECT_AUTOSUGGEST":
					if (!option.equals("") && !option.isEmpty()) {
						Actions actions = new Actions(driver);
						actions.scrollToElement(element);
						element.clear();
						element.sendKeys(option);

						try {
							Thread.sleep(Integer.parseInt(prop.getProperty("shortWait")));
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						List<WebElement> autoSuggestOptions = objAutoSuggest.findElements(By.tagName("li"));
						for (int index = 0; index < autoSuggestOptions.size(); index++) {
							autoSuggestOptions = objAutoSuggest.findElements(By.tagName("li"));
							WebElement i = autoSuggestOptions.get(index);

							String[] opt = i.getText().split("-");
							if (opt[0].trim().equalsIgnoreCase(optSuggest.trim())) {
								actions.moveToElement(i).click().build().perform();
								Thread.sleep(2000);
								break;

							} else if (i.getText().equalsIgnoreCase(optSuggest.trim())) {
								for (int j = 0; j <= index; j++) {
									element.sendKeys(Keys.ARROW_DOWN);
									Thread.sleep(100);
								}
								element.sendKeys(Keys.ENTER);
								break;
							}
						}
					}
					retVal = true;
					break;
				case "SELECT_AUTOSUGGEST_PARTIAL":
					if (!option.equals("") || !option.isEmpty()) {
						element.clear();
						element.sendKeys(option);

						List<WebElement> autoSuggestOptions = objAutoSuggest.findElements(By.tagName("li"));
						System.out.println("Auto-suggest size: " + autoSuggestOptions.size());
						int index = -1;
						Actions actions = new Actions(driver);
						try {
							Thread.sleep(Integer.parseInt(prop.getProperty("shortWait")));
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						for (WebElement i : autoSuggestOptions) {
							index++;
							if (i.getText().contains(optSuggest.trim())) {
								for (int j = 0; j <= index; j++) {
									actions.sendKeys(Keys.ARROW_DOWN).build().perform();
								}
								actions.sendKeys(Keys.ENTER).build().perform();

								break;
							}
						}
					}
					retVal = true;
					break;

				default:
				}
			} else {
			}
		} else {
		}
		return retVal;

	}

	public static boolean performOperationOnElement(WebElement el, String operationType, String option) {
		/*
		 *************************************************************************************
		 * FunctionName: performOperationOnElement Input Parameters: WebElement,
		 * OperationType, Option to select, Suggested value to select Output Parameters:
		 * n/a Description: This function will perform specific operation on a
		 * WebElement Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		boolean retVal = false;

		operationType = (operationType.toUpperCase()).trim();
		// Wait for loader to disappear before interacting
		waitForLoaderToDisappear("//div[@class='infinity-loader']");
		// wait for a webelement to be visible
		WebElement element = waitForElement(el, "");
		scrollIntoView(el);

		// highlight the object if the configuration is set to yes
		if (prop.getProperty("highlight").equals("yes")) {
			highlightElement(element);
		}
		// extract the element attribute
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		// check if the webelement is visible then
		if (element != null) {
			// check if the webelement is enabled then
			if (element.isEnabled()) {
				// perform operation based on the type
				switch (operationType) {
				case "SELECT_DROPDOWN_TEXT":
					if (!option.equals("") || !option.isEmpty()) {
						if (fetchText(element, "DROPDOWN", 0, 0).equals(option)) {

							// extentTest.log(Status.INFO,
							// option + " is already selected into dropdown. attribute: " + attrDesc);
							// log.info(//option + " is already selected into dropdown. attribute: " +
							// attrDesc);
						} else {
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
							Select drpObject = new Select(element);
							drpObject.selectByVisibleText(option);
						}
					}
					retVal = true;
					break;
				case "SELECT_DROPDOWN_INDEX":
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
					Select drpObject = new Select(element);
					drpObject.selectByIndex(Integer.parseInt(option));
					String strText = fetchText(element, "DROPDOWN", 0, 0);
					retVal = true;
					break;
				case "ENTER_TEXT":
					if (!option.equals("") && !option.isEmpty() && !option.equals("<blank>")
							&& !option.equals("<TAB>")) {
						if (fetchText(element, "EDITBOX", 0, 0) != null
								&& fetchText(element, "EDITBOX", 0, 0).equals(option)) {
						} else {
//							element.click(); // Focus on the field
							((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
						    element.sendKeys(Keys.chord(Keys.CONTROL, "a")); // Select all existing text
						    element.sendKeys(Keys.BACK_SPACE); // Clear
						    element.sendKeys(option); // Type new value
						}
					} else if (option.equals("<blank>")) {
						element.clear();
					} else if (option.equals("<TAB>")) {
						element.sendKeys(Keys.TAB);
					}
					retVal = true;
					break;
				default:
				}
			} else {
			}
		} else {
		}
		return retVal;
	}

	public static boolean performOperationOnElement(WebElement el, String operationType) {
		/*
		 *************************************************************************************
		 * FunctionName: performOperationOnElement Input Parameters: WebElement,
		 * OperationType, Option to select, Suggested value to select Output Parameters:
		 * n/a Description: This function will perform specific operation on a
		 * WebElement Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		boolean retVal = false;

		operationType = (operationType.toUpperCase()).trim();
		// Wait for loader to disappear before interacting
		waitForLoaderToDisappear("//div[@class='infinity-loader']");
		// wait for a webelement to be visible
		WebElement element = waitForElement(el, "visibility");
		scrollIntoView(el);

		// highlight the object if the configuration is set to yes
		if (prop.getProperty("highlight").equals("yes")) {
			highlightElement(element);
		}
		// extract the element attribute
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		// check if the webelement is visible then
		if (element != null) {
			// check if the webelement is enabled then
			if (element.isEnabled()) {
				// perform operation based on the type
				switch (operationType) {
				case "CLICK":
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
					retVal = true;
					break;
				case "FORCE_CLICK":
					// Scroll element into view
					((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);

					// Click using JavaScript executor
					((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
					retVal = true;
					break;
				case "CHECK":
					if (element.isSelected() != true) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
					} else {
					}
					retVal = true;
					break;
				case "UNCHECK":
					if (element.isSelected() != false) {
						((JavascriptExecutor) driver).executeScript("arguments[0].click();", el);
					} else {
					}
					retVal = true;
					break;
				default:
				}
			} else {
			}
		} else {
		}
		return retVal;
	}

	public static WebElement waitForElement(WebElement el, String condition) {
		/*
		 *************************************************************************************
		 * FunctionName: waitForElement Input Parameters: Object Locator, wait based on
		 * condition type Output Parameters: WebElement Description: This function will
		 * wait until a specific condition is fulfilled/the threshold time is met Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		try {
			int waitTime = Integer.parseInt(prop.getProperty("elementWait"));
			WebDriverWait waitElm = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

			condition = (condition.trim()).toLowerCase();

			switch (condition) {
			case "visibility":
				return waitElm.until(ExpectedConditions.visibilityOf(el));
			case "refresh":// to be used for stale element
				By element = getByFromWebElement(el);
				return waitElm
						.until(ExpectedConditions.refreshed(ExpectedConditions.presenceOfElementLocated(element)));
			default:
				 return waitElm.until(ExpectedConditions.elementToBeClickable(el));
			}
		} catch (Exception e) {
			return null; // Return null if the element is not found within the timeout
		}
			}
	
	public static void waitForLoaderToDisappear(String loaderXpath) {
	    try {
	        // Short wait to check if loader is present in DOM
	        WebDriverWait shortWait = new WebDriverWait(driver, Duration.ofSeconds(2));
	        List<WebElement> loaders = shortWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(loaderXpath)));
	 
	        // If present and displayed, wait until it's gone
	        if (!loaders.isEmpty() && loaders.get(0).isDisplayed()) {
	            WebDriverWait longWait = new WebDriverWait(driver, Duration.ofSeconds(15));
	            longWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(loaderXpath)));
	        }
	    } catch (TimeoutException e) {
	        // Loader not found in short wait - skip wait
	        System.out.println("Loader not present, continuing...");
	    } catch (Exception e) {
	        System.out.println("Unexpected issue while waiting for loader: " + e.getMessage());
	    }
	    
//		 try {
//		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
//		        wait.until(driver -> {
//		            List<WebElement> loaders = driver.findElements(By.xpath(loaderXpath));
//		            // If no loader is found or none are displayed, consider loader disappeared
//		            return loaders.isEmpty() || loaders.stream().noneMatch(WebElement::isDisplayed);
//		        });
//		    } catch (TimeoutException e) {
//		        System.out.println("Loader did not disappear within the expected time.");
//		    } catch (Exception e) {
//		        System.out.println("Unexpected issue while waiting for loader: " + e.getMessage());
//		    }
	}

	public static WebElement waitForElement(WebElement el, String condition, int time) {
		/*
		 *************************************************************************************
		 * FunctionName: waitForElement Input Parameters: Object Locator, wait based on
		 * condition type Output Parameters: WebElement Description: This function will
		 * wait until a specific condition is fulfilled/the threshold time is met Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		try {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(0));
			WebDriverWait waitElmTime = new WebDriverWait(driver, Duration.ofSeconds(time));

			condition = (condition.trim()).toLowerCase();

			switch (condition) {
			case "visibility":
				WebElement elm = waitElmTime.until(ExpectedConditions.visibilityOf(el));
				if (elm.isDisplayed()) {
					return elm;
				}
			default:
				return waitElmTime.until(ExpectedConditions.elementToBeClickable(el));
			}
		} catch (Exception e) {
			return null; // Return null if the element is not found within the timeout
		} finally {
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		}
	}

	public static boolean pageLoader() {
		/*
		 *************************************************************************************
		 * FunctionName: pageLoader Input Parameters: n/a Output Parameters: true/false
		 * Description: This function will wait until the 'Page Loading' element becomes
		 * invisible Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		try {
			Thread.sleep(Integer.parseInt(prop.getProperty("shortWait")));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int waitTime = Integer.parseInt(prop.getProperty("elementWait"));
		wait = new WebDriverWait(driver, Duration.ofSeconds(waitTime));

		return wait.until(ExpectedConditions.invisibilityOf(imgLoader));
	}

	public static By getByFromWebElement(WebElement element) {
		/*
		 *************************************************************************************
		 * FunctionName: getByFromWebElement Input Parameters: WebElement Output
		 * Parameters: By Locator Description: This function will By locator from
		 * WebElement Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		// Extract the By locator from the WebElement
		String elementDescription = element.toString();
		int intIndex = 0;
		String val = "";
		if (elementDescription.contains("By.")) {
			intIndex = elementDescription.indexOf("By.");
			val = elementDescription.substring(intIndex + 3);
			val = val.trim();
			val = val.substring(0, val.length() - 2);
		} else if (elementDescription.contains("> ")) {
			intIndex = elementDescription.indexOf("> ", intIndex + 1);
			val = elementDescription.substring(intIndex + 2);
			val = val.trim();
			val = val.substring(0, val.length() - 1);
		}

		String[] arrVal = val.split(": ");
		String byType = arrVal[0];
		String byValue = arrVal[1];

		switch (byType) {
		case "xpath":
			return By.xpath(byValue);
		case "id":
			return By.id(byValue);
		case "name":
			return By.name(byValue);
		default:
			throw new IllegalArgumentException("Unsupported locator type: " + byType);
		}
	}

	public boolean elementExists(WebElement el) {
		/*
		 *************************************************************************************
		 * FunctionName: elementExists Input Parameters: WebElement Output Parameters:
		 * true/false Description: This function will check if WebElement exists or not
		 * Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");

		String attrDesc = "";
		if (el.toString().contains("Proxy")) {
			attrDesc = (el.toString().substring(el.toString().indexOf("By.") + 3)).replace("]", "");
		} else {
			attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		}
		if (element != null) {
			scrollIntoView(el);
			// highlight the object if the configuration is set to yes
			if (prop.getProperty("highlight").equals("yes")) {
				highlightElement(element);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean elementExists(WebElement el, int time) {
		/*
		 *************************************************************************************
		 * FunctionName: elementExists Input Parameters: WebElement Output Parameters:
		 * true/false Description: This function will check if WebElement exists or not
		 * Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility", time);
		String attrDesc = "";
		if (el.toString().contains("Proxy")) {
			attrDesc = (el.toString().substring(el.toString().indexOf("By.") + 3)).replace("]", "");
		} else {
			attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		}
		if (element != null) {
			scrollIntoView(el);
			// highlight the object if the configuration is set to yes
			if (prop.getProperty("highlight").equals("yes")) {
				highlightElement(element);
			}
			return true;
		} else {
			return false;
		}
	}

	public boolean elementDoesNotExists(WebElement el) {
		/*
		 *************************************************************************************
		 * FunctionName: elementDoesNotExists Input Parameters: WebElement Output
		 * Parameters: true/false Description: This function will check if WebElement
		 * does not exists Date created/modified: 29/04/2024 Modified by: Rahul Abhay
		 * Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility", 2);
		if (element != null) {
			return false;
		} else {
			return true;
		}
	}

	public boolean elementIsEnabled(WebElement el) {
		WebElement element = waitForElement(el, "visibility");

		String attrDesc = "";
		if (el.toString().contains("Proxy")) {
			attrDesc = (el.toString().substring(el.toString().indexOf("By.") + 3)).replace("]", "");
		} else {
			attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		}
		if (element != null) {
			if (el.isEnabled()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean elementIsSelected(WebElement el) {
		WebElement element = waitForElement(el, "visibility");

		String attrDesc = "";
		if (el.toString().contains("Proxy")) {
			attrDesc = (el.toString().substring(el.toString().indexOf("By.") + 3)).replace("]", "");
		} else {
			attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		}
		if (element != null) {
			if (el.isSelected()) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public static String fetchText(WebElement el, String objType, int Row, int Col) {
		/*
		 *************************************************************************************
		 * FunctionName: fetchText Input Parameters: WebElement, Object Type, Row,
		 * Column Output Parameters: text Description: This method will fetch the text
		 * from the WebElement Date created/modified: 10/03/2024 Modified by: Rahul
		 * Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
//		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		String strText = "";
		if (element != null) {
			switch (objType) {
			case "TABLE":
				WebElement row = element.findElements(By.tagName("tr")).get(Row);
				strText = (row.findElements(By.tagName("td")).get(Col)).getText();
				break;
			case "TABLE1":
				WebElement row1= element.findElements(By.tagName("tr")).get(Row);
				strText=row1.findElements(By.xpath("//td[4]")).get(Col).getText();
				break;
			case "HEADER":
				WebElement header = element.findElements(By.tagName("tr")).get(Row);
				strText = (header.findElements(By.tagName("th")).get(Col)).getText();
				break;
			default:
				strText = "";
			}
		} else {
			return strText;
		}
		return strText;
	}

	public static String fetchText(WebElement el, String objType) {
		/*
		 *************************************************************************************
		 * FunctionName: fetchText Input Parameters: WebElement, Object Type, Row,
		 * Column Output Parameters: text Description: This method will fetch the text
		 * from the WebElement Date created/modified: 10/03/2024 Modified by: Rahul
		 * Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
//		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		String strText = "";
		if (element != null) {
			switch (objType) {
			case "DROPDOWN":
				Select drpObject = new Select(element);
				if (!drpObject.getAllSelectedOptions().isEmpty()) {
					strText = drpObject.getFirstSelectedOption().getText();
					break;
				}
			case "EDITBOX":
				strText = element.getAttribute("value");
				break;
			default:
				strText = element.getText();
			}
		} else {
			return strText;
		}
		return strText;
	}

	public String checkAttributeExistValue(WebElement el, String attributeName) {
		/*
		 *************************************************************************************
		 * FunctionName: checkAttributeExist Input Parameters: webelement, attributename
		 * Output Parameters: String array with 2 indexes (true|false, <attributeValue>)
		 * Description: This method will check if the attribute exists in the webelement
		 * and fetches it's value Date created/modified: 26/04/2024 Modified by: Rahul
		 * Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
//		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		String strAttributeDetails = "";
		if (element != null) {
			String attributeValue = element.getAttribute(attributeName);
			if (attributeValue != null) {
				strAttributeDetails = attributeValue;
			} else {
				strAttributeDetails = "null";
			}
		} else {
		}
		return strAttributeDetails;
	}

	public boolean isElementEditable(WebElement element) {
		return isElementEnabled(element) && isElementNotReadonly(element);
	}

	public boolean isElementEnabled(WebElement element) {
		return element.isEnabled();
	}

	public boolean isElementNotReadonly(WebElement element) {
		String readonly = element.getAttribute("readonly");
		return readonly == null || readonly.isEmpty();
	}

	public static String randomCaseConversion(String input) {
		Random random = new Random();
		StringBuilder result = new StringBuilder();
		// Loop through each character of the input string
		for (char c : input.toCharArray()) {
			// Randomly decide whether to convert the character to upper or lower case
			if (random.nextBoolean()) {
				result.append(Character.toUpperCase(c));
			} else {
				result.append(Character.toLowerCase(c));
			}
		}
		return result.toString();
	}

	public int getColumnNumber(WebElement el, String objType, int row, String textToSearch) {
		/*
		 *************************************************************************************
		 * FunctionName: getColumnNumber Input Parameters: WebElement, Object Type, row,
		 * textToSearch Output Parameters: count Description: This method will retrieve
		 * the column number in the row where the textToSearch is found. Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		int header = getCount(el, objType);
		String strHeader = "";
		for (int i = 1; i <= header; i++) {
			strHeader = fetchText(el, objType, 0, i);
			if (strHeader.equalsIgnoreCase(textToSearch)) {
				return i;
			}
		}
		return 0;
	}

	public List<String> getTableColumnSequence(WebElement el) {
		String colValue = "";

		// Fetch all column headers from the table
		List<WebElement> headers = el.findElements(By.xpath(".//thead/tr/th"));

		// Extract column names from header elements
		List<String> actualColumnNames = new ArrayList<>();
		for (WebElement header : headers) {
			colValue = header.getText().trim();
			if (colValue.length() > 0) {
				actualColumnNames.add(colValue);
			}
		}
		return actualColumnNames;

	}

	public int getCount(WebElement el, String type) {
		/*
		 *************************************************************************************
		 * FunctionName: getCount Input Parameters: WebElement, Object Type Output
		 * Parameters: count Description: This method will retrieve the count Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
//		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		int count = 0;
		if (element != null) {
			switch (type) {
			case "ROW":
				List<WebElement> rows = element.findElements(By.tagName("tr"));
				count = rows.size();
				break;
			case "COLUMN":
				List<WebElement> cols = element.findElements(By.tagName("tr")).get(0).findElements(By.tagName("td"));
				count = cols.size();
				break;
			case "HEADER":
				List<WebElement> headers = element.findElements(By.tagName("tr")).get(0).findElements(By.tagName("th"));
				count = headers.size();
				break;
			case "DROPDOWN":
				Select select = new Select(element);
				count = select.getOptions().size();
				break;
			default:

			}
		} else {
			return count;
		}
		return count;
	}

	public int getCount(WebElement el, String type, int rowNum) {
		/*
		 *************************************************************************************
		 * FunctionName: getCount Input Parameters: WebElement, Object Type, rowNumber
		 * Output Parameters: count Description: This method will retrieve the count
		 * Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		int count = 0;
		if (element != null) {
			switch (type) {
			case "COLUMN":
				List<WebElement> cols = element.findElements(By.tagName("tr")).get(rowNum)
						.findElements(By.tagName("td"));
				count = cols.size();
				break;
			case "HEADER":
				List<WebElement> headers = element.findElements(By.tagName("tr")).get(rowNum)
						.findElements(By.tagName("th"));
				count = headers.size();
				break;
			default:

			}
		} else {
			return count;
		}
		return count;
	}

	public static void switchToMainWindow() {
		/*
		 *************************************************************************************
		 * FunctionName: switchToMainWindow Input Parameters: n/a Output Parameters: n/a
		 * Description: This method switch to the main window Date created/modified:
		 * 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			driver.switchTo().window(windowHandle);
			// Assuming the main window title or some condition to identify the main window
			if (driver.getTitle().equals(strCurrPgTitle)) {
				break;
			}
		}
	}

	public static void scrollIntoView(WebElement element) {
		/*
		 *************************************************************************************
		 * FunctionName: scrollIntoView Input Parameters: WebElement Output Parameters:
		 * n/a Description: This method will scroll until WebElement is in view Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		// Use JavaScript to scroll the element into view
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(false);", element);

	}

	public static void closeWindowUsingRobot() {
		/*
		 *************************************************************************************
		 * FunctionName: closeWindowUsingRobot Input Parameters: n/a Output Parameters:
		 * n/a Description: This method will close the active window Date
		 * created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		try {
			// Close Acrobat Reader using Robot class
			Robot robot = new Robot();
			robot.keyPress(KeyEvent.VK_ALT);
			robot.keyPress(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_F4);
			robot.keyRelease(KeyEvent.VK_ALT);
		} catch (AWTException e) {
			e.printStackTrace();
		}
	}

	public static String generateRandomString(int length) {
		/*
		 *************************************************************************************
		 * FunctionName: generateRandomString Input Parameters: lenght Output
		 * Parameters: random aplhabets string Description: This method will generate
		 * random alpha-numeric string Date created/modified: 09/05/2024 Modified by:
		 * Rahul Abhay Kamat
		 *************************************************************************************/
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		StringBuilder result = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			result.append(alphabet.charAt(random.nextInt(alphabet.length())));
		}

		return result.toString();
	}

	public static String generateRandomAlphaNumericString(int length) {
		/*
		 *************************************************************************************
		 * FunctionName: generateRandomAlphaNumericString Input Parameters: lenght
		 * Output Parameters: random aplha-numeric string Description: This method will
		 * generate random alpha-numeric string Date created/modified: 09/05/2024
		 * Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		StringBuilder builder = new StringBuilder();
		Random random = new Random();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(ALPHA_NUMERIC_STRING.length());
			char randomChar = ALPHA_NUMERIC_STRING.charAt(index);
			builder.append(randomChar);
		}

		return builder.toString();
	}

	public static String getRandomNumberString(int num) {
		/*
		 *************************************************************************************
		 * FunctionName: getRandomNumberString Input Parameters: n/a Output Parameters:
		 * 6-digit random number Description: This method will generate 6-digit random
		 * number Date created/modified: 10/03/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		Random rnd = new Random();
		int number = rnd.nextInt(999999);

		// this will convert any number sequence into 6 character.
		return String.format("%0" + num + "d", number);
	}

	public static int getRandomNumberFromRange(int min, int max) {
		/*
		 *************************************************************************************
		 * FunctionName: getRandomNumberFromRange Input Parameters: min and max number
		 * Output Parameters: random number within the range Description: This method
		 * will generate random number from the given range Date created/modified:
		 * 02/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		Random random = new Random();
		return random.nextInt((max - min) + 1) + min; // Generates a random number between the given range
	}

	public static String dateCalculator(String inputDate, int yearDuration) {
		/*
		 *************************************************************************************
		 * FunctionName: dateCalculator Input Parameters: date, noOfYear Output
		 * Parameters: date in dd/mm/yyyy format Description: This method will calculate
		 * the future date from the inputDate based on the yearDuration provided Date
		 * created/modified: 26/04/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		String futureDate = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();

		try {
			Date date = sdf.parse(inputDate);
			calendar.setTime(date);
			calendar.add(Calendar.YEAR, yearDuration); // Adding future year count to the date
			Date futureYearLater = calendar.getTime();
			futureDate = sdf.format(futureYearLater);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return futureDate;
	}

	public static String getCurrentDate() {
		/*
		 *************************************************************************************
		 * FunctionName: getCurrentDate Input Parameters: Output Parameters: date in
		 * dd/mm/yyyy format Description: This method will return current Date in
		 * dd/mm/yyyy format Date created/modified: 26/04/2024 Modified by: Rahul Abhay
		 * Kamat
		 *************************************************************************************/

		// Get the current date
		LocalDate currentDate = LocalDate.now();

		// Define the date format
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

		// Format the current date
		String formattedDate = currentDate.format(formatter);
		return formattedDate;
	}

	public static String calculateNewDate(String startDate, int daysToSubtract, int monthsToSubtract,
			int yearsToSubtract) throws ParseException {
		/*
		 *************************************************************************************
		 * FunctionName: calculateNewDate Input Parameters: date, daystoSubtract,
		 * monthstoSubtract, yearstoSubtract Output Parameters: date in dd/mm/yyyy
		 * format Description: This method will calculate the date based on the days,
		 * months, years to subtract Date created/modified: 26/04/2024 Modified by:
		 * Rahul Abhay Kamat
		 *************************************************************************************/
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		Calendar calendar = Calendar.getInstance();

		Date date = sdf.parse(startDate);
		calendar.setTime(date);

		calendar.add(Calendar.DAY_OF_MONTH, -daysToSubtract);
		calendar.add(Calendar.MONTH, -monthsToSubtract);
		calendar.add(Calendar.YEAR, -yearsToSubtract);

		Date newDate = calendar.getTime();
		return sdf.format(newDate);
	}

	public void removeFocus(WebElement el) {
		/*
		 *************************************************************************************
		 * FunctionName: removeFocus Input Parameters: WebElement Output Parameters: N/A
		 * Description: This method will remove focus from the WebElement Date
		 * created/modified: 29/04/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		By byLocator = getByFromWebElement(el);

		WebElement objEl = driver.findElement(byLocator);
		((JavascriptExecutor) driver).executeScript("arguments[0].blur();", objEl);

	}

	public static String generateAdultDOB(String adultType) {
		/*
		 *************************************************************************************
		 * FunctionName: generateAdultDOB Input Parameters: adultType (A|P) Output
		 * Parameters: dd/mm/yyyy Description: This method will generate a random Adult
		 * dob in dd/mm/yyyy format Date created/modified: 30/04/2024 Modified by: Rahul
		 * Abhay Kamat
		 *************************************************************************************/

		int randomYears = 0;
		LocalDate currentDate = LocalDate.now();
		Random random = new Random();
		// Generate a random date between 18 and 79 years ago
		if (adultType.equals("A")) {
			randomYears = 18 + random.nextInt(79 - 18 + 1);
		} else if (adultType.equals("P")) {
			// Generate a random date between 36 and 79 years ago
			randomYears = 36 + random.nextInt(79 - 36 + 1);
		} else if (adultType.equals("YA")) {
			// Generate a random date between 18 and 30 years ago
			randomYears = 18 + random.nextInt(30 - 18 + 1);
		} else if (adultType.equals("SA")) {
			// Generate a random date between 56 and 100 years ago
			randomYears = 56 + random.nextInt(100 - 56 + 1);
		}
		LocalDate randomDate = currentDate.minusYears(randomYears);

		// Generate the DOB string in dd/mm/yyyy format
		int day = randomDate.getDayOfMonth();
		int month = randomDate.getMonthValue();
		int year = randomDate.getYear();

		return String.format("%02d/%02d/%04d", day, month, year);
	}

	public static String generateChildDOB() {
		/*
		 *************************************************************************************
		 * FunctionName: generateChildDOB Input Parameters: N/A Output Parameters:
		 * dd/mm/yyyy Description: This method will generate a random Child dob in
		 * dd/mm/yyyy format Date created/modified: 30/04/2024 Modified by: Rahul Abhay
		 * Kamat
		 *************************************************************************************/
		// Generate a random date between 1 and 17 years ago
		LocalDate currentDate = LocalDate.now();
		Random random = new Random();
		int randomYears = 1 + random.nextInt(17);
		LocalDate randomDate = currentDate.minusYears(randomYears);

		// Generate the DOB string in dd/mm/yyyy format
		int day = randomDate.getDayOfMonth();
		int month = randomDate.getMonthValue();
		int year = randomDate.getYear();

		return String.format("%02d/%02d/%04d", day, month, year);
	}

	public static void highlightElement(WebElement element) {
		/*
		 *************************************************************************************
		 * FunctionName: highlightElement Input Parameters: WebElement Output
		 * Parameters: dd/mm/yyyy Description: This method will highlight a webelement
		 * in red color Date created/modified: 01/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		// Create a JavascriptExecutor instance
		JavascriptExecutor js = (JavascriptExecutor) driver;

		// Execute JavaScript code to highlight the element
		js.executeScript("arguments[0].style.border='2px solid red'", element);

		// Wait for a brief moment to see the highlight effect (optional)
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Execute JavaScript code to remove the highlight
		js.executeScript("arguments[0].style.border=''", element);
	}

	public static int roundBasedOnDecimal(double value) {
		/*
		 *************************************************************************************
		 * FunctionName: roundBasedOnDecimal Input Parameters: double value Output
		 * Parameters: int value Description: This method will highlight a webelement in
		 * red color Date created/modified: 14/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		// Extract the integer part
		int integerPart = (int) value;

		// Extract the fractional part
		double fractionalPart = value - integerPart;

		// Round based on the fractional part
		if (fractionalPart < 0.50) {
			return integerPart; // Round down
		} else {
			return integerPart + 1; // Round up
		}
	}

	public static double roundDoubleDecimalPlace(double value, int place) {
		/*
		 *************************************************************************************
		 * FunctionName: roundDoubleDecimalPlace Input Parameters: double value, int
		 * decimal place Output Parameters: int value Description: This method will
		 * highlight a webelement in red color Date created/modified: 15/05/2024
		 * Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		// Create a BigDecimal from the double value
		BigDecimal bd = new BigDecimal(Double.toString(value));

		// Set the scale to 2 decimal places and round up
		bd = bd.setScale(place, RoundingMode.HALF_UP);

		// Convert back to double if needed
		double roundedValue = bd.doubleValue();
		return roundedValue;
	}

	public static int getDropdownSize(WebElement el) {
		/*
		 *************************************************************************************
		 * FunctionName: getDropdownSize Input Parameters: WebElement el Output
		 * Parameters: int value Description: This method will get the size of dropdown
		 * Date created/modified: 15/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		scrollIntoView(el);
		// Create a Select object
		Select dropdown = new Select(el);

		// Get the list of all options
		List<WebElement> options = dropdown.getOptions();

		// Get the count of options
		int count = options.size();
		return count;
	}

	public static String generateRandomFirstName() {
		/*
		 *************************************************************************************
		 * FunctionName: generateRandomFirstName Input Parameters: Output Parameters:
		 * String First Name Description: This method will generate random First Name
		 * Date created/modified: 15/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		Faker faker = new Faker();
		return faker.name().firstName();
	}

	public static String generateRandomEmailID() {
		Faker faker = new Faker();
		return faker.internet().emailAddress();
	}

	public static String generateRandomWebsite() {
		Faker faker = new Faker();
		return faker.internet().url();
	}

	public static String generateUsername() {
		Faker faker = new Faker();
		return faker.name().username();
	}

	public static String generatePassword() {
		Faker faker = new Faker();
		return faker.internet().password(8, 12, true, true, true);
	}

	public static String generateRandomLastName() {
		/*
		 *************************************************************************************
		 * FunctionName: generateRandomLastName Input Parameters: Output Parameters:
		 * String Last Name Description: This method will generate random Last Name Date
		 * created/modified: 15/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		Faker faker = new Faker();
		return faker.name().lastName();
	}

	public static String generateRandomAddress() {
		Faker faker = new Faker();
		return faker.address().fullAddress();
	}

	public static String generateRandomIndianCellPhoneNumber() {
		Faker faker = new Faker();
		String indianPhoneNumber = "+91 9" + faker.numerify("#########");
		return indianPhoneNumber;
	}

	public static String generateRandomUSCanMobilePhoneNumber() {
		Faker faker = new Faker();
		String USCanMobile = faker.numerify("##########");
		return USCanMobile;
	}

	public static String generateRandomNumber(int length) {
		Faker faker = new Faker();
		return faker.number().digits(length);
	}

	public static Date generateRandomDOB(int minAge, int maxAge) {
		Faker faker = new Faker();
		return faker.date().birthday(minAge, maxAge);
	}

	public static String generateRandomUSCanWorkPhoneNumber() {
		Faker faker = new Faker();
		String USCanWorkPhone = faker.phoneNumber().phoneNumber();
		return USCanWorkPhone;
	}

	public static String formatPhoneNumber(String number) {
		if (number.length() == 10) {
			return number.substring(0, 3) + "-" + number.substring(3, 6) + "-" + number.substring(6);
		} else if (number.length() == 13) {
			return number.substring(0, 3) + "-" + number.substring(3, 6) + "-" + number.substring(6, 10) + " ext "
					+ number.substring(10);
		} else {
			return number; // Return as is if not 10 or 13 digits
		}
	}

	public static String formatShortRole(String role) {
		switch (role.trim().toLowerCase()) {
		case "client":
			contShortRole = "CC";
			break;
		case "business partner":
			contShortRole = "BP";
			break;
		case "realtor":
			contShortRole = "RC";
			break;
		case "appraiser":
			contShortRole = "AC";
			break;
		case "solicitor":
			contShortRole = "SC";
			break;
		}
		return contShortRole;
	}

	public static boolean checkTextInArray(String[] arr, final String srchText) {
		boolean isPresent = Arrays.stream(arr).anyMatch(text -> text.equals(srchText));

		if (!isPresent) {
			return false;
		}
		return true;
	}

	public static void deleteFile(String filePath) {
		Path path = Paths.get(filePath);
		if (Files.exists(path)) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
		}
	}

	public static void cleanDirectory(Path path) {
		try {
			Files.walkFileTree(path, new SimpleFileVisitor<Path>() {
				@Override
				public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
					Files.delete(file);
					return FileVisitResult.CONTINUE;
				}

				@Override
				public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
					if (exc == null) {
						Files.delete(dir);
						return FileVisitResult.CONTINUE;
					} else {
						throw exc;
					}
				}
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void deleteChromeHistory() {
		// Get the current user's username
		String username = System.getProperty("user.name");

		// Construct the path to the Chrome History file
		String dbPath = "";

		// Determine the operating system
		String os = System.getProperty("os.name").toLowerCase();

		if (os.contains("win")) {
			// Windows
			dbPath = "C:\\Users\\" + username + "\\AppData\\Local\\Google\\Chrome\\User Data\\Default\\History";
		} else if (os.contains("mac")) {
			// macOS
			dbPath = "/Users/" + username + "/Library/Application Support/Google/Chrome/Default/History";
		} else if (os.contains("nix") || os.contains("nux")) {
			// Linux
			dbPath = "/home/" + username + "/.config/google-chrome/Default/History";
		}

		// Create a connection to the SQLite database
		Connection connection = null;
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
			if (connection != null) {
				// Create a statement
				Statement statement = connection.createStatement();

				// Execute the delete query
				String sql = "DELETE FROM urls";
				statement.executeUpdate(sql);

			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	public static void cleanup() {
		// delete chrome browser history/cache
		deleteChromeHistory();
	}

	public boolean isFileDownloaded(String downloadPath, String fileName, int timeoutInSeconds) {
		File dir = new File(downloadPath);
		File[] dirContents = dir.listFiles();
		Instant end = Instant.now().plus(Duration.ofSeconds(timeoutInSeconds));

		while (Instant.now().isBefore(end)) {
			for (File file : dirContents) {
				if (file.getName().equals(fileName)) {
					return true;
				}
			}
			// Refresh the file list in the directory
			dirContents = dir.listFiles();
		}
		return false;
	}

	public boolean movePageBack() {
		driver.navigate().back();
		return true;
	}

	public boolean movePageForward() {
		driver.navigate().forward();
		return true;
	}

	public boolean refreshPage() {
		driver.navigate().refresh();
		return true;
	}

	public boolean launchUrl(String navUrl) {
		driver.navigate().to(navUrl);
		return pageLoader();
	}

	public static String removeSpclChar(String text) {
		return text.replaceAll("[^a-zA-Z0-9]", "");
	}

	public static List<String> fetchDropdownValues(WebElement el, String type) {
		/*
		 *************************************************************************************
		 * FunctionName: fetchDropdownValues Input Parameters: WebElement,
		 * "enabled|disabled" Output Parameters: List<String> dropdown values String
		 * Last Name Description: This method will fetch Dropdown values Date
		 * created/modified: 15/05/2024 Modified by: Rahul Abhay Kamat
		 *************************************************************************************/
		WebElement element = waitForElement(el, "visibility");
//		scrollIntoView(el);
		String attrDesc = (el.toString().substring(el.toString().indexOf("-> ") + 3)).replace("]", "");
		Select dropdown = new Select(el);
		List<String> lstValues = new ArrayList<>();

		if (element != null) {
			// Get all options in the dropdown
			List<WebElement> options = dropdown.getOptions();

			for (WebElement option : options) {
				String optionText = option.getText();

				// Check if the option is enabled
				if (type.trim().equalsIgnoreCase("enabled")) {
					if (option.isEnabled()) {
						lstValues.add(optionText);
					}
				} else if (type.trim().equalsIgnoreCase("disabled")) {
					if (!option.isEnabled()) {
						lstValues.add(optionText);
					}
				}
			}
		} else {
			lstValues.add("");
		}
		return lstValues;
	}

	public static int countOccurrences(String input, String type) {
		/*
		 *************************************************************************************
		 * FunctionName: countOccurrences Input Parameters: input text, "int or
		 * alphabets or specialchar Output Parameters: String Last Name Description:
		 * This method will generate random Last Name Date created/modified: 15/05/2024
		 * Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		if (input == null || type == null) {
			throw new IllegalArgumentException("Input string and type cannot be null");
		}

		String regex;
		switch (type.trim().toLowerCase()) {
		case "int":
			regex = "\\d"; // Matches digits (0-9)
			break;
		case "alphabets":
			regex = "[a-zA-Z]"; // Matches alphabets (both lowercase and uppercase)
			break;
		case "specialchar":
			regex = "[^a-zA-Z0-9]"; // Matches special characters (excluding numbers and alphabets)
			break;
		default:
			regex = type;
		}

		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);
		int count = 0;

		while (matcher.find()) {
			count++;
		}

		return count;
	}

	public static String selectRandomValueFromDropdown(WebElement el, int indexToSkip) {
		/*
		 *************************************************************************************
		 * FunctionName: selectRandomValueFromDropdown Input Parameters: WebElement el,
		 * int indexToSkip Output Parameters: true|false Description: This method will
		 * select random value other than selected Date created/modified: 17/02/2025
		 * Modified by: Rahul Abhay Kamat
		 *************************************************************************************/

		Select dropdown = new Select(el);

		// Get all available options
		List<WebElement> options = dropdown.getOptions();

		// Get the currently selected option
		String selectedValue = dropdown.getFirstSelectedOption().getText();

		// Remove the currently selected value from the list
		options.removeIf(option -> option.getText().equals(selectedValue));

		if (indexToSkip >= 0 && indexToSkip < options.size()) {
			options.remove(indexToSkip);
		}

		// If there are still options left, select a random one
		if (!options.isEmpty()) {
			int randomIndex = new Random().nextInt(options.size());
			options.get(randomIndex).click(); // Select the random option
		}
		return dropdown.getFirstSelectedOption().getText();
	}

	public static String selectRandomValueFromDropdown(WebElement el, String... valuesToSkip) {

		Select dropdown = new Select(el);

		// Get all available options
		List<WebElement> options = new ArrayList<>(dropdown.getOptions());

		// Get the currently selected option
		String selectedValue = dropdown.getFirstSelectedOption().getText();

		// Remove the currently selected value and the specified values to skip
		options.removeIf(option -> option.getText().equals(selectedValue)
				|| Arrays.asList(valuesToSkip).contains(option.getText()));

		// If there are still options left, select a random one
		if (!options.isEmpty()) {
			int randomIndex = new Random().nextInt(options.size());
			dropdown.selectByVisibleText(options.get(randomIndex).getText()); // Select the random option
		}

		return dropdown.getFirstSelectedOption().getText();
	}

	public static String generateRandomValue(String inputType, int length) {
		String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
		String alphanumeric = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		String alphanumericSpcl = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()-_+=<>?";

		String charSet;

		switch (inputType.toLowerCase()) {
		case "alphabet":
			charSet = alphabet;
			break;
		case "alphanumeric":
			charSet = alphanumeric;
			break;
		case "alphanumericspcl":
			charSet = alphanumericSpcl;
			break;
		default:
			throw new IllegalArgumentException(
					"Invalid inputType. Allowed values: alphabet, alphanumeric, alphanumericspcl.");
		}

		SecureRandom random = new SecureRandom();
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < length; i++) {
			int index = random.nextInt(charSet.length());
			result.append(charSet.charAt(index));
		}

		return result.toString();
	}

	public static String dateGenerator(String format, int day) {
		// Replace lowercase format parts with DateTimeFormatter compatible parts
		Map<String, String> formatMap = new HashMap<>();
		formatMap.put("dd", "dd");
		formatMap.put("mm", "MM"); // 'mm' is for minutes, 'MM' is for months
		formatMap.put("yyyy", "yyyy");

		// Replace format placeholders
		String pattern = format.replace("mm", formatMap.get("mm")).replace("dd", formatMap.get("dd")).replace("yyyy",
				formatMap.get("yyyy"));

		// Get today's date and add/subtract days
		LocalDate adjustedDate = LocalDate.now().plusDays(day);

		// Create formatter
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

		return adjustedDate.format(formatter);
	}

	public static String longDateFormat(String shortDateFormat) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("MMMM dd, yyyy");

		// Parse the input string into a Date object
		Date date = inputFormat.parse(shortDateFormat);

		// Format the Date object into the desired string format
		String longDateFormat = outputFormat.format(date);

		return longDateFormat; // Output: January 03, 2001
	}

	public static String shortDateFormat(String shortDateFormat) throws ParseException {
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat outputFormat = new SimpleDateFormat("MMM dd, yyyy");

		// Parse the input string into a Date object
		Date date = inputFormat.parse(shortDateFormat);

		// Format the Date object into the desired string format
		String longDateFormat = outputFormat.format(date);

		return longDateFormat; // Output: January 03, 2001
	}

	public static String dateFormat(Date date) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		return localDate.format(formatter);
	}

	public static void shiftFocusFromElement(WebElement element) {
		element.sendKeys(Keys.TAB);
	}

	public static void pressEnterKey() {
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).perform();
	}

}