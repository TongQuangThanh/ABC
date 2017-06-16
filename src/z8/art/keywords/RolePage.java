package z8.art.keywords;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
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
		String nameCell = ExcelData.getCellValue("Input role name");
		String descCell = ExcelData.getCellValue("Input role description");
		if (nameCell.equals("Not required"))
			clearText("RolePage.AddEditRole.Name");
		if (descCell.equals("Not required"))
			clearText("RolePage.AddEditRole.Description");
/*type text*/
		if (!nameCell.equals("Not required"))
			typeText("RolePage.AddEditRole.Name", "Input role name");
		
		if (!descCell.equals("Not required"))
			typeText("RolePage.AddEditRole.Description", "Input role description");
/*click save*/
		if (ExcelData.getCellValue("Click on save button").equals(Yes))
			click("RolePage.AddEditRole.Button_Save");
/*click cancel*/
		if (ExcelData.getCellValue("Click on cancel button").equals(Yes))
			click("RolePage.EditRole.Button_Cancel");
/*verify action*/
		if (ExcelData.getCellValue("Verify popup title").equals("Displayed"))
			checkDisplayed("RolePage.AddRole.Popup.Title");

		if (ExcelData.getCellValue("Verify notify message success").equals(Yes))
			checkDisplayed("RolePage.AddEditRole.SuccessMessage");

		if (ExcelData.getCellValue("Verify notify message fail").equals(Yes))
			checkDisplayed("RolePage.AddEditRole.FailMessage");

		if (ExcelData.getCellValue("Verify error title").equals("Displayed"))
			checkDisplayed("RolePage.AddEditRole.ErrorTitle");
/*Check navigation after add/edit */
		if(ExcelData.getCellValue("Click on close popup").equals(Yes)){
			click("RolePage.AddRole.Popup.Close");
			checkNotDisplayed("RolePage.AddRole.Popup.Title");
		}
		if(ExcelData.getCellValue("Click on add other role").equals(Yes)){
			click("RolePage.AddRole.Popup.AddMore");
			checkNotDisplayed("RolePage.AddRole.Popup.Title");
		}
		if(ExcelData.getCellValue("Click on view role list").equals(Yes)){
			click("RolePage.AddRole.Popup.RoleList");
			checkDisplayed("RolePage.Title");
		}
	}

	/*@KeywordInfo(Description = "Check action after Add/Edit Role successfully")
	public void navigateAfterAddEdit() {
		if(ExcelData.getCellValue("Click on close popup").equals(Yes)){
			click("RolePage.AddRole.Popup.Close");
			checkNotDisplayed("RolePage.AddRole.Popup.Close");
		}
		if(ExcelData.getCellValue("Click on add other role").equals(Yes)){
			click("RolePage.AddRole.Popup.AddMore");
			checkNotDisplayed("RolePage.AddRole.Popup.AddMore");
		}
		if(ExcelData.getCellValue("Click on view role list").equals(Yes)){
			click("RolePage.AddRole.Popup.RoleList");
			checkDisplayed("RolePage.Title");
		}
	}*/
	
	@KeywordInfo(Description = "Check Security")
	public void checkSecurity() {
		String url = Global.webDriver.getCurrentUrl();
		WebDriver newDriver = new ChromeDriver();
		newDriver.navigate().to(url);
		String newWindow = newDriver.getWindowHandle();
		newDriver.switchTo().window(newWindow);
		//switchToWindow(2);
		checkDisplayed("LoginPage.Username_Text");
	}

	@KeywordInfo(Description = "Check Pagination")
	public void checkPagination() {
		
		
	}

	@KeywordInfo(Description = "Check Sorting")
	public void checkSorting() {
		
	}
}