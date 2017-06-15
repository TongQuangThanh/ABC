package z8.art.keywords;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import z8.art.common.Global;
import z8.art.dal.ExcelData;
import z8.art.object.KeywordInfo;

public class RolePage extends ProviderPages {

	@KeywordInfo(Description = "Go to Sub Page of Role Module")
	public void goToSubRolePage() {
		if (ExcelData.getCellValue("Go to add role page").equals("Yes")) {
			click("RolePage.Button_CreateRole");
			checkExist("RolePage.AddRole.Title");
		}
		if (!ExcelData.getCellValue("Go to edit role page").equals("")) {
			WebElement elexPath = Global.webDriver
					.findElement(By.xpath("//p[text()='"+ ExcelData.getCellValue("Go to edit role page") + "']/../../..//a"));
			Actions actions = new Actions(Global.webDriver);
			actions.click(elexPath).build().perform();
		}
	}

	@KeywordInfo(Description = "Exit")
	public void exit() {
		closeDriver();
	}

	@KeywordInfo(Description = "Check UI of this page")
	public void checkUIRolePage() {
/* Check UI of add new role page */
		if (ExcelData.getCellValue("Go to add role page").equals("Yes")) {
			if (!ExcelData.getCellValue("Input role name").equals(""))
				checkStatusOfElement("RolePage.AddRole.Name", ExcelData.getCellValue("Input role name"));

			if (!ExcelData.getCellValue("Input role description").equals(""))
				checkStatusOfElement("RolePage.AddRole.Description", ExcelData.getCellValue("Input role description"));

			if (!ExcelData.getCellValue("Button add new role").equals(""))
				checkStatusOfElement("RolePage.AddRole.Button_Add", ExcelData.getCellValue("Button add new role"));
/* Check UI of edit role page */
		} else if (!ExcelData.getCellValue("Go to edit role page").equals("")) {
			if (!ExcelData.getCellValue("Input role name").equals(""))
				checkStatusOfElement("RolePage.EditRole.Name", ExcelData.getCellValue("Input role name"));

			if (!ExcelData.getCellValue("Input role description").equals(""))
				checkStatusOfElement("RolePage.EditRole.Description", ExcelData.getCellValue("Input role description"));

			if (!ExcelData.getCellValue("Button save role").equals(""))
				checkStatusOfElement("RolePage.EditRole.Button_Save", ExcelData.getCellValue("Button save role"));

			if (!ExcelData.getCellValue("Button cancel").equals(""))
				checkStatusOfElement("RolePage.EditRole.Button_Cancel", ExcelData.getCellValue("Button cancel"));

		} else {
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
		}
	}
	
	@KeywordInfo(Description = "Add or edit role")
	public void checkAddEditRolePage() {
		if (!ExcelData.getCellValue("Go to edit role page").equals("")) {
			clearText("RolePage.AddEditRole.Name");
			clearText("RolePage.AddEditRole.Description");
		}
		
		if (!ExcelData.getCellValue("Input role name").equals(""))
			typeText("RolePage.AddEditRole.Name", ExcelData.getCellValue("Input role name"));

		if (!ExcelData.getCellValue("Input role description").equals(""))
			typeText("RolePage.AddEditRole.Description", ExcelData.getCellValue("Input role description"));

		if (!ExcelData.getCellValue("Click on save button").equals(""))
		click("RolePage.AddEditRole.Button_Save");

		if (!ExcelData.getCellValue("Click on cancel button").equals(""))
			click("RolePage.AddEditRole.Button_Cancel");
	}

}