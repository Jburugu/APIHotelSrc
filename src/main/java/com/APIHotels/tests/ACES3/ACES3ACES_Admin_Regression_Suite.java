package com.APIHotels.tests.ACES3;

import org.testng.annotations.Test;

import com.APIHotels.framework.LocalDriverManager;
import com.APIHotels.pages.generic.PgWrapper;

public class ACES3ACES_Admin_Regression_Suite extends LocalDriverManager {

	public PgWrapper pgWrapper;

	@Test(description = "JIRA# ATA-*** - Verify that the new user is allowed to create", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 0)
	public void verifyCreateUser(String firstName, String lastName, String emailId, String roles,String createByIndicator, String tempUserName, String tempPassword)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAdminLink();
		pgWrapper.aces3acesHomePage.clickOnCreateUserLink();
		pgWrapper.aces3acesCreateUser.createUser(firstName, lastName, emailId, roles, createByIndicator,  tempUserName,  tempPassword);
		pgWrapper.aces3acesCreateUser.verifyUserCreated();
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
	}

	@Test(description = "JIRA# ATA-94 - Verify that the Manage user lists all the users of the application and user is allowed to perform Edit", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 1)
	public void verifyEditUser(String tenantName, String city, String supplierName, String emailId, String newEmailId,
			String newFirstName, String newLastName, String newRoles) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3acesLoginPage.loginToACESIII(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3acesHomePage.clickOnAdminLink();
		//pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3acesHomePage.clickOnManageUserLink();
		pgWrapper.aces3acesManageUsersPage.findUser(emailId);
		pgWrapper.aces3acesManageUsersPage.performActions("Edit");
		pgWrapper.aces3acesEditUserAccountPage.editUserAccount(newEmailId, newFirstName, newLastName, newRoles);
		pgWrapper.aces3acesEditUserAccountPage.verifyUserUpdated();
		pgWrapper.aces3acesHomePage.clickOnLogoutLink();
	}

	private void createRoles(String roleType, String roleName, String moduleName) {
		if (roleType.equals("PrivateRole"))
			pgWrapper.aces3SupplierManageRolesPage.addPrivateRole();
		else if (roleType.equals("SharedRole"))
			pgWrapper.aces3SupplierManageRolesPage.addSharedRole();
		pgWrapper.aces3SupplierCreateRolePage.createRole(roleName, moduleName);
		pgWrapper.aces3SupplierManageRolesPage.verifyCreatedRole(roleName);

	}

	
	@Test(description = "JIRA# ATA-98, ATA-99 - Verify that the user is allowed to change the password using Account management and verify that the user is allowed to edit the email address", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 4)
	public void verifyEmailAndPwdChangeByUser(String username, String password, String newPassword, String newEmailId)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(username, password);
		pgWrapper.aces3SupplierHomePage.clickOnAccountManagementLink();
		pgWrapper.aces3SupplierAccountManagementPage.changePassword(password, newPassword);
		pgWrapper.aces3SupplierAccountManagementPage.changeEmail(newEmailId);
		pgWrapper.aces3SupplierHomePage.clickOnLogoutLink();
	}

	@Test(description = "JIRA# ATA-94 - Verify that the Manage user lists all the users of the application and user is allowed to perform Suspend", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 5)
	public void verifySuspendUser(String tenantName, String city, String supplierName, String emailId)
			throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnManageUsersLink();
		pgWrapper.aces3SupplierManageUsersPage.findUser(emailId);
		pgWrapper.aces3SupplierManageUsersPage.performActions("Suspend");// Suspend
		pgWrapper.aces3SupplierManageUsersPage.accountSuspendConfirmation();
		// pgWrapper.aces3SupplierManageUsersPage.findUser(emailId);
	}

	@Test(description = "JIRA# ATA-94 - Verify that the Manage user lists all the users of the application and user is allowed to perform Delete", groups = {
			"Regression" }, dataProvider = "TestDataFile", priority = 6)
	public void verifyDeleteUser(String tenantName, String city, String supplierName, String emailId) throws Exception {
		pgWrapper = LocalDriverManager.getPageWrapper();
		pgWrapper.aces3SupplierLoginPage.loginToACES3(readPropValue("aces3SupplierUserName"),
				readPropValue("aces3SupplierPassword"));
		pgWrapper.aces3SupplierHomePage.selectSupplier(tenantName, city, supplierName);
		pgWrapper.aces3SupplierHomePage.clickOnManageUsersLink();
		pgWrapper.aces3SupplierManageUsersPage.findUser(emailId);
		pgWrapper.aces3SupplierManageUsersPage.performActions("Delete");
		pgWrapper.aces3SupplierManageUsersPage.accountDeleteConfirmation();
		pgWrapper.aces3SupplierManageUsersPage.findUser(emailId);
	}

	

}
