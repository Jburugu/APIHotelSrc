package com.APIHotels.pages.navigationalLinks.Airlines;

import java.io.IOException;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.APIHotels.framework.BasePage;

public class OperationsTab extends BasePage {

	public EventFiringWebDriver driver = null;

	@FindBy(xpath= "//td[text()='Operations']")
	public WebElement operationsLink;

	@FindBy(id= "iconreservations")
	public WebElement reservationsLink;

	@FindBy(id= "iconbiztravel")
	public WebElement businessTravelLink;
	
	//AeroMexico
	@FindBy(id= "icongtOpsSchedule")
	public WebElement gtOpsSchedule;

	// Envoy 
	@FindBy(css= "#iconbizTravelTraining")
	public WebElement traningReservations;

	// Air Transat and Mesa Airlines
	@FindBy(xpath= "//td[contains(text(),'Manual Booking')]")
	public WebElement manualBooking;

	//UPS
    @FindBy(id= "iconbiztravel")
    public WebElement dayRoomNonPairingRequestLink;
	
	@FindBy(xpath= "//td[text()='Find Reservation']")
	public WebElement findReservationLinkUnderBT;

	@FindBy(xpath= "//td[contains(text(),'Request Limo')]")
	public WebElement requestLimoLink;

	// Room Block Requests link under aces direct (Commut Air)/Air Transit
	@FindBy(xpath= "//td[text()='Room Block Requests']")
	public WebElement roomBlockRequestsLink;

	// Jazz Airlines
	@FindBy(xpath= "//td[text()='Room Block']")
	public WebElement roomBlockLink;

	@FindBy(xpath= ".//*[@id='iconadhocHotel']")
	public WebElement addAdhocHotelRoomsLink;

	@FindBy(id= "iconflightResvs")
	private WebElement acesDirectLink;

	@FindBy(xpath= "//td[contains(text(),'Request Room Block')]")
	public WebElement requestRoomBlockLink;

	
	 //UPS
    @FindBy(xpath= "//td[contains(text(),'Request Room block')]")
    public WebElement requestRoomblockTab;
    
    @FindBy(xpath= "//td[contains(text(),'Find/Edit Room Block Requests')]")
	public WebElement findOrEditRoomBlockRequestsLink;

	@FindBy(id= "iconfindPairings")
	public WebElement findPairingsLink;

	@FindBy(id= "iconreservationSearch")
	private WebElement findReservationLink;

	
	//UPS
    @FindBy(id= "iconreviewMatches")
    private WebElement CATTSApprovalLink;
    
    @FindBy(xpath= "//td[text()='Crew Sign-In Sheets']")
	public WebElement crewSignInSheetLink;

	@FindBy(xpath= ".//*[@id='iconcsi']/label")
	public WebElement generateSheetLink;

	@FindBy(xpath= ".//*[@id='iconfindCsi']")
	public WebElement findSheetLink;

	@FindBy(id= "iconactionStatus")
	public WebElement actionStatusLink;

	@FindBy(id= "iconcrewLayover")
	private WebElement hotelInfoLink;

	@FindBy(id= "bizTravelForm:conferenceTab_lbl")
	public WebElement requestConferenceRoomLink;

	@FindBy(xpath= "//td[contains(text(),'Request Reservation')]")
	public WebElement requestReservationLink;

	@FindBy(id= "iconvendorMismatch")
	public WebElement vendorMismatchLink;

	@FindBy(xpath= "//td[text()='Crew Trac Block']")
	public WebElement crewTracBlockLink;

	@FindBy(xpath= "//td[contains(text(),'Allowances')]")
	public WebElement allowancesLink;

	@FindBy(id= "iconforecast")
	public WebElement viewForecastLink;

	@FindBy(id= "iconsearchAllowanceInvoice")
	public WebElement viewAllowanceInvoicesLink;

	@FindBy(id= "iconsearchApprovedInvoice")
	public WebElement viewApprovedAllowanceInvoicesLink;

	@FindBy(id= "iconsearchAllowanceTransactions")
	public WebElement viewAllowanceTransactionsLink;

	@FindBy(id= "iconcreateAdhocAllowanceReq")
	public WebElement createAdhocAllowanceLink;

	@FindBy(id= "iconsearchAdhocAllowanceReq")
	public WebElement searchAdhocAllowanceLink;

	@FindBy(id= "iconpaymentExtractReport")
	public WebElement paymentExtractReportLink;

	@FindBy(id= "icondeaReport")
	public WebElement deaReportLink;

	@FindBy(id= "iconsupplierConfig")
	public WebElement configureSupplierFeatureLink;

	@FindBy(id= "iconairNelsonReport")
	public WebElement airnelsonReportLink;
	
	@FindBy(id= "iconsearchSubmittedInvoices")
	private WebElement viewSubmittedAllowanceInvoicesLink;

	public OperationsTab(EventFiringWebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	public void clickOnReservationLink() {
		waitForElementVisibility(operationsLink);
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(reservationsLink, "Operations Menu-> Reservations Link");;
	}

	public void clickOnBusinessTravelLink() {
		waitForElementVisibility(operationsLink);
		clickOn(operationsLink, "HomePage-> Operations Menu"); 
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
	}

	//AeroMexico
    public void ClickOnGTOPSSchedule(){
        clickOn(operationsLink, "HomePage-> Operations Menu");
        clickOn(gtOpsSchedule, "Operations Menu-> GTOpsSchedule Link");
    }
    
	// Air Transit and MesaAirlines
	public void clickOnManualBookingLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(manualBooking, "Operations Menu-> ManualBooking Link");
	}

	public void clickOnTrainingReservations() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(traningReservations, "Operations Menu-> TrainingReservations Link");
	}

	// Jazz Airlines
	public void clickOnAdhocHotelLimoRequest() {
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
	}

	public void clickOnFindReservationLinkUnderBT() {
		clickOn(findReservationLinkUnderBT,  "BusinessTravel Menu -> FindReservation Link");
	}

	//UPS
    public void clickOnrequestRoomblockTab()
    {
        clickOn(requestRoomblockTab);
    }
    
	public void clickOnRequestLimoUnderBT() {
		clickOn(requestLimoLink);
	}

	public void clickOnRequestRoomBlockUnderACESDirect() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(acesDirectLink);
		clickOn(requestRoomBlockLink);
	}

	public void clickOnRequestRoomBlockUnderBT() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
		clickOn(requestRoomBlockLink);
	}

	public void clickOnRequestRoomBlockUnderMB() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(manualBooking);
		clickOn(roomBlockRequestsLink);
	}

	public void clickOnRoomBlockRequests() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(acesDirectLink);
		clickOn(roomBlockRequestsLink);
	}

	public void clickOnRoomBlock() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
		clickOn(roomBlockLink);
	}

	public void clickOnAddAdhocHotelRoomsLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(addAdhocHotelRoomsLink, "Operations Menu -> Add Adhoc Hotel Rooms Link");
	}

	public void clickOnOperationsLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
	}

	public void clickOnFindOrEditRoomBlockRequestsLink() {
		clickOn(findOrEditRoomBlockRequestsLink);
		try {
			takeScreenshots();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void clickOnFindPairingLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(findPairingsLink);
	}

	public void clickOnFindReservationLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(findReservationLink);

	}

	public void clickOnAcesDirectLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(acesDirectLink);
	}

	public void clickOnGenerateSheetLink() {
		clickOnCrewSignInSheetLink();
		clickOn(generateSheetLink);
	}

	public void clickOnCrewSignInSheetLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(crewSignInSheetLink);

	}

	public void clickOnFindSheetLink() {
		clickOnCrewSignInSheetLink();
		clickOn(findSheetLink);

	}

	public void clickOnTrainingRoomsLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
	}

	public void clickOnActionStatus() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(actionStatusLink);
	}

	public void clickOnHotelInfoLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(hotelInfoLink);
	}

	public void clickOnRequestConferenceRoomLink() {
		clickOn(requestConferenceRoomLink);
	}

	public void clickOnAddAdhocHotelsLink() {
		waitForElementVisibility(operationsLink);
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(addAdhocHotelRoomsLink);
	}

	public void clickOnRequestRoomBlockUnderTrainingReservationsLink() {
		clickOnTrainingReservations();
		clickOn(requestRoomBlockLink);
	}

	public void clickOnRequestReservationLink() {
		clickOn(requestReservationLink);
	}

	public void clickOnRoomBlockRequestsLink() {
		clickOn(roomBlockRequestsLink);
	}

	public void clickOnRequestRoomBlockLink() {
		clickOn(requestRoomBlockLink);
	}

	public void clickOnVendorMismatchLink() {
		clickOn(vendorMismatchLink);
	}

	public void clickOnCrewTarcBlock() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
		clickOn(crewTracBlockLink);
	}

	public void clickOnAllowancesLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(allowancesLink, "Operations Menu-> Allowances Link");
	}

	public void clickOnViewForecastLink() {
		clickOn(viewForecastLink, "Allowances Link -> ViewForecastLink");
	}

	public void clickOnViewAllowanceInvoicesLink() {
		clickOn(viewAllowanceInvoicesLink, "Allowances Link -> ViewAllowanceInvoices Link");
	}

	public void clickOnViewApprovedAllowanceInvoicesLink() {
		clickOn(viewApprovedAllowanceInvoicesLink, "Allowances Link -> ViewApprovedAllowanceInvoices Link");
	}

	public void clickOnViewAllowanceTransactionsLink() {
		clickOn(viewAllowanceTransactionsLink, "Allowances Link -> ViewAllowanceTransactionsLink Link");
	}

	public void clickOnCreateAdhocAllowanceLink() {
		clickOn(createAdhocAllowanceLink, "Allowances Link -> Create Adhoc Allowances Link");
	}

	public void clickOnSearchAdhocAllowanceLink() {
		clickOn(searchAdhocAllowanceLink, "Allowances Link -> Search Adhoc Allownaces Link");
	}

	public void clickOnPaymentExtractReportLink() {
		clickOn(paymentExtractReportLink, "Allowances Link -> Payment Extract Report Link" );
	}

	public void clickOnDEAReportLink() {
		clickOn(deaReportLink, "Allowances Link -> DEAReport Link" );
	}

	public void clickOnConfigureSupplierFeatureLink() {
		clickOn(configureSupplierFeatureLink, "Allowances Link -> configureSupplierFeature Link" );
	}

	public void clickOnAirnelsonReportLink() {
		clickOn(airnelsonReportLink);
	}

	public void clickOnDayRoomNonPairingRequestLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(dayRoomNonPairingRequestLink);
	}

	public void clickOnCATTSApprovalLink() {
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(CATTSApprovalLink);
	}
	
	//JetBlue
	public void clickOnAdhocHotelRoomBlockRequests(){
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");//clicks on Adhoc Hotel/Limo Request
		clickOn(roomBlockRequestsLink);
	}
	
	public void clickOnAdhocHotelRequestReservationRequests(){
		clickOn(operationsLink, "HomePage-> Operations Menu");
		clickOn(businessTravelLink, "Operations Menu-> BusinessTravel Link");
		clickOn(requestReservationLink);
	}
	
	public void clickOnViewSubmittedAllowanceInvoicesLink() {
		clickOn(viewSubmittedAllowanceInvoicesLink);
	}
	
}
