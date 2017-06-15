package z8.art.keywords;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import z8.art.common.Global;
import z8.art.dal.ExcelData;
import z8.art.object.KeywordInfo;

public class RolePage extends ProviderPages {
	
	@KeywordInfo(Description = "Go to Sub Page of Role Module")
	public void goToRoleInfoPage() {
		if (ExcelData.getCellValue("Go to add role page").equals(Yes)) {
			click("RolePage.Button_CreateRole");
			checkExist("RolePage.AddRole.Title");
		}
		if (!ExcelData.getCellValue("Go to edit role page").equals("")) {
			clickElementExits("//p[text()='"+ ExcelData.getCellValue("Go to edit role page") + "']/../../..//a");
		}
	}

	@KeywordInfo(Description = "Exit")
	public void exit() {
		closeDriver();
	}
	
	@KeywordInfo(Description = "Check UI of Role Info page")
	public void checkUIRoleInfo() {
		if (!ExcelData.getCellValue("Input role name").equals(""))
			checkStatusOfElement("RolePage.AddEditRole.Name", ExcelData.getCellValue("Input role name"));

		if (!ExcelData.getCellValue("Input role description").equals(""))
			checkStatusOfElement("RolePage.AddEditRole.Description", ExcelData.getCellValue("Input role description"));

		if (!ExcelData.getCellValue("Button add new role").equals(""))
			checkStatusOfElement("RolePage.AddEditRole.Button_Save", ExcelData.getCellValue("Button add new role"));

		if (!ExcelData.getCellValue("Button cancel").equals(""))
			checkStatusOfElement("RolePage.EditRole.Button_Cancel", ExcelData.getCellValue("Button cancel"));
	}
	
	@KeywordInfo(Description = "Check UI of Role page")
	public void checkUIRolePage() {
		if (!ExcelData.getCellValue("Textfield search role").equals(""))
			checkStatusOfElement("RolePage.Input_SearchRole", ExcelData.getCellValue("Textfield search role"));

		if (!ExcelData.getCellValue("Button search role").equals(""))
			checkStatusOfElement("RolePage.Button_SearchRole", ExcelData.getCellValue("Button search role"));

		if (!ExcelData.getCellValue("Button create role").equals(""))
			checkStatusOfElement("RolePage.Button_CreateRole", ExcelData.getCellValue("Button create role"));

		if (!ExcelData.getCellValue("Button export role").equals(""))
			checkStatusOfElement("RolePage.Button_Export", ExcelData.getCellValue("Button export role"));

		if (!ExcelData.getCellValue("Button previous page").equals(""))
			checkStatusOfElement("RolePage.Button_Previous", ExcelData.getCellValue("Button previous page"));

		if (!ExcelData.getCellValue("Button next page").equals(""))
			checkStatusOfElement("RolePage.Button_Next", ExcelData.getCellValue("Button next page"));
//		}
	}
	
	@KeywordInfo(Description = "Add or edit role")
	public void checkAddEditRolePage() {
/*Check add page or edit page to clear text*/
		if (!ExcelData.getCellValue("Go to edit role page").equals("")) {
			clearText("RolePage.AddEditRole.Name");
			clearText("RolePage.AddEditRole.Description");
		}
/*type text*/
		typeText("RolePage.AddEditRole.Name", ExcelData.getCellValue("Input role name"));
		typeText("RolePage.AddEditRole.Description", ExcelData.getCellValue("Input role description"));
/*click save*/
		if (!ExcelData.getCellValue("Click on save button").equals(""))
		click("RolePage.AddEditRole.Button_Save");
/*click cancel*/
		if (!ExcelData.getCellValue("Click on cancel button").equals(""))
			click("RolePage.AddEditRole.Button_Cancel");
/*verify action*/
		if (ExcelData.getCellValue("Verify message").equals("Success")){
			checkDisplayed("RolePage.AddEditRole.Message");
		}else if (ExcelData.getCellValue("Verify message").equals("Fail")){
			
		}
	}
	
	@KeywordInfo(Description = "Check Security")
	public void checkSecurity() {
		String url = Global.webDriver.getCurrentUrl();
		System.out.println(url);
		WebDriver newDriver = null;
		newDriver.get(url);
		checkDisplayed("LoginPage.Username_Text");
	}

	@KeywordInfo(Description = "Check Pagination")
	public void checkPagination() {
		String url = Global.webDriver.getCurrentUrl();
		System.out.println(url);
		Actions actions = new Actions(Global.webDriver);
		actions.sendKeys(Keys.CONTROL + "n").build().perform();
		navigate(url);
		checkDisplayed("LoginPage.Username_Text");
	}

	@KeywordInfo(Description = "Check Sorting")
	public void checkSorting() {
		
	}
}