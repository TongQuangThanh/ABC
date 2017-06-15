package z8.art.keywords;

import static org.testng.Assert.assertEquals;

import java.io.File;
import java.util.ArrayList;

import org.openqa.selenium.By;

import z8.art.common.Global;
import z8.art.common.Message;
import z8.art.common.Report;
import z8.art.dal.ExcelData;
import z8.art.object.KeywordInfo;

public class GroupPage extends ProviderPages{
	
	public static String url;
	
	@KeywordInfo(Description = "Check status Elements on Group page")
	public void checkUIOnGroupPage () {
		if (!ExcelData.getCellValue("Verify Group Label").equals("")) 
			checkStatusOfElement("GroupPage.Label_Group", ExcelData.getCellValue("Verify Group Label"));
		
		if (!ExcelData.getCellValue("Verify Search Textfield").equals("")) 
			checkStatusOfElement("GroupPage.Input_Search", ExcelData.getCellValue("Verify Search Textfield"));
		
		if (!ExcelData.getCellValue("Verify Search Button").equals("")) 
			checkStatusOfElement("GroupPage.Button_Search", ExcelData.getCellValue("Verify Search Button"));
		
		if (!ExcelData.getCellValue("Verify Create Button").equals("")) 
			checkStatusOfElement("GroupPage.Button_Create", ExcelData.getCellValue("Verify Create Button"));
		
		if (!ExcelData.getCellValue("Verify Export Button").equals("")) 
			checkStatusOfElement("GroupPage.Button_ExportGroup", ExcelData.getCellValue("Verify Export Button"));
		
		if (!ExcelData.getCellValue("Verify Edit Gear Button").equals("")) 
			checkStatusOfElement("GroupPage.GearButton_Edit", ExcelData.getCellValue("Verify Edit Gear Button"));
		
		if (!ExcelData.getCellValue("Verify Pagination Left Arrow").equals("")) 
			checkStatusOfElement("GroupPage.Icon_PrevArrow", ExcelData.getCellValue("Verify Pagination Left Arrow"));
		
		if (!ExcelData.getCellValue("Verify Pagination Right Arrow").equals("")) 
			checkStatusOfElement("GroupPage.Icon_NextArrow", ExcelData.getCellValue("Verify Pagination Right Arrow"));
		
		if (!ExcelData.getCellValue("Column Name").equals("")) 
			checkStatusOfElement("GroupPage.ColumnName_Label", ExcelData.getCellValue("Column Name"));
		
		if(!ExcelData.getCellValue("Verify Maximum Record").equals("")) 
			checkMaximumRecord("Verify Maximum Record");
		//Check pagination
//		if(!ExcelData.getCellValue("Action").equals("")) 
//			checkPagination("Action");
			
		
	}
	
	@KeywordInfo(Description = "Check status Elements on Create/Edit Group page")
	public void checkUIOnCreateEditGroup () {
		
		if(ExcelData.getCellValue("Action To Test").equals("View")) {
			if (!ExcelData.getCellValue("Verify Group Label").equals("")) 
			checkStatusOfElement("GroupConfig.Title_GrConfig", ExcelData.getCellValue("Verify Group Label"));
		}
		else {
			if (!ExcelData.getCellValue("Verify Group Label").equals("")) 
				checkStatusOfElement("CreateGroup.Label_CreateNew", ExcelData.getCellValue("Verify Group Label"));
		}
		
		
		if (!ExcelData.getCellValue("Verify Group Name").equals("")) 
			checkStatusOfElement("GroupConfig.Input_GrName", ExcelData.getCellValue("Verify Group Name"));
		
		if (!ExcelData.getCellValue("Group Description").equals("")) 
			checkStatusOfElement("GroupConfig.Input_GrDescription", ExcelData.getCellValue("Group Description"));

		if (!ExcelData.getCellValue("Group Visible To Customer").equals("")) 
			checkStatusOfElement("GroupConfig.Checkbox_VisibleToCus", ExcelData.getCellValue("Group Visible To Customer"));
		
		if (!ExcelData.getCellValue("Verify Assign Role").equals("")) 
			checkStatusOfElement("GroupConfig.Dropdown_AssignRole", ExcelData.getCellValue("Verify Assign Role"));
		
		if (!ExcelData.getCellValue("Verify Role List").equals("")) 
			checkStatusOfElement("GroupConfig.List_Role", ExcelData.getCellValue("Verify Role List"));
		
		if (!ExcelData.getCellValue("Verify Save Button").equals("")) 
			checkStatusOfElement("GroupConfig.Button_Save", ExcelData.getCellValue("Verify Save Button"));
		
		if (!ExcelData.getCellValue("Verify Cancel Button").equals("")) 
			checkStatusOfElement("GroupConfig.Button_Cancel", ExcelData.getCellValue("Verify Cancel Button"));
		
	}
	
	
	@KeywordInfo(Description = "action Search on Group")
	public void searchOnGroup () {
		String num = ExcelData.getCellValue("Number Of Record");
		String matchGroupName = ExcelData.getCellValue("Input To Search");
		
		String xPathRecord = ".//div[@class='ui-grid-canvas' and count(div) = "+num+"]";
		String xPathGroupName = ".//p[contains(text(),'"+matchGroupName+"')]";
		typeTextExcel("GroupPage.Input_Search", "Input To Search");
		
		checkClickAndWait("GroupPage.Button_Search", "Click Search Button",2);
		
		//verify no result after search
		if(!ExcelData.getCellValue("Check Search Group").equals(Yes)) {
			checkElementExist(xPathRecord);
		}
		//verify have results
		if(ExcelData.getCellValue("Check Search Group").equals(Yes)) {
			checkElementExist(xPathGroupName);
			checkElementExist(xPathRecord);
		}
		
	}
	
	
	@KeywordInfo(Description = "action create or edit on Group")
	public void createOrEditGroup () {
		checkClickAndWait("GroupPage.Button_Create", "Click Create Button");
		
		String groupEdit = ExcelData.getCellValue("Group Name");
		String xpath = ".//div[@class='ng-isolate-scope' and .//p[text()='"+groupEdit+"']]//a";
		String xPathDes = ".//div[@class='ng-isolate-scope' and .//p[text()='"+groupEdit+"']]//div[2]";
		String description = Global.webDriver.findElement(By.xpath(xPathDes)).getText();
		//Check if user click edit another group name or not
		if(!groupEdit.equals(""))
			clickElementExits(xpath);
			wait(1);
		String groupName = ExcelData.getCellValue("Input Group Name");
		if(!ExcelData.getCellValue("Action To Test").equals("View")) {
			//Input to Group Name
			typeTextExcel("GroupConfig.Input_GrName", "Input Group Name");
			//Input to Group Description
			typeTextExcel("GroupConfig.Input_GrDescription", "Group Description");
		}
		else {
			checkContains("GroupConfig.Input_GrName", "Group Name");
			compareTextContains(getText("GroupConfig.Input_GrDescription"), description);
		}
		if(ExcelData.getCellValue("Group Visible To Customer").equals(Yes)) {
			if(checkNotSelected("GroupConfig.Checkbox_VisibleToCus"))
				click("GroupConfig.Checkbox_VisibleToCus");
		}
		else {
			if(ExcelData.getCellValue("Group Visible To Customer").equals("No")) {
				if(checkSelected("GroupConfig.Checkbox_VisibleToCus"))
					click("GroupConfig.Checkbox_VisibleToCus");
			}
		}
			
		
		addRoleforGroup(ExcelData.getCellValue("Assign Role"),"Assign Role");
		
		checkClickAndWait("GroupConfig.Button_Save", "Click Save Button");
		
		checkClickAndWait("GroupConfig.Button_Cancel", "Click Cancel Button");
		
		//verify message success
		if (ExcelData.getCellValue("Message Success").equals(Yes))
			checkExist("xpath message success");
		
		//verify message unsuccess
		if (ExcelData.getCellValue("Message Unsuccess").equals(Yes))
			checkExist("xpath message unsuccess");
			
		//verify adding success using this method for create
		String xpathGroupAdd = "//div[@text()='" + groupName + "']" ;
		if(!ExcelData.getCellValue("Action To Test").equals("View")) {
			if (ExcelData.getCellValue("Check Adding success").equals(Yes))
				checkExist(xpathGroupAdd);
			
			if (ExcelData.getCellValue("Check Adding Unsuccess").equals(Yes))
				checkNotExist(xpathGroupAdd);
		}
		
	}
	
	@KeywordInfo(Description = "ation Export file on Group")
	public void exportFile() {
		if(ExcelData.getCellValue("Click Export Data").equals(Yes)) {
			if(!ExcelData.getCellValue("Click Cancel Button").equals(Yes)) {
				//Check before download
				File folder = new File("C:\\Users\\sonnh32\\Downloads");	//Get folder to save file
				File listFile[] = folder.listFiles();	//Get all file
				for (File file : listFile) {
					if(file.getName().endsWith("."+ExcelData.getCellValue("File Type").toLowerCase())) {
						file.delete();	//Delete all file pdf
					}
				}
				//Export Data
				wait(2);
				click("GroupPage.Button_ExportGroup");
				wait(1);
				checkDisplayed("ExportGroup.Image_Xls");
				checkDisplayed("ExportGroup.Image_Pdf");
				checkDisplayed("ExportGroup.Button_Cancel");
				click("ExportGroup.Image_"+ExcelData.getCellValue("File Type"));
				wait(2);
				//Check after download
				File newFolder = new File("C:\\Users\\sonnh32\\Downloads"); //Get folder to save file
				//Get all file
				File files[] = newFolder.listFiles();
				//Find all file pdf or xls
				ArrayList<String> list = new ArrayList<String>();
				for (File file : files) {
					if(file.getName().endsWith("."+ExcelData.getCellValue("File Type").toLowerCase())) {
						list.add(file.getName());
					}
				}
				String stepDes = Message.format("RPT_VERIFY_EXIST", list.toString());
				if(list.isEmpty())
					Report.event(stepDes, Report.R_FAILED, Message.format("RPT_NOT_EXIST", list.toString()));
				else
					Report.event(stepDes);
				wait(2);
				
			}
			
			if(ExcelData.getCellValue("Click Cancel Button").equals(Yes)) {
				//Export file unsuccesfully
				wait(2);
				click("GroupPage.Button_ExportGroup");
				wait(1);
				checkDisplayed("ExportGroup.Image_Xls");
				checkDisplayed("ExportGroup.Image_Pdf");
				checkDisplayed("ExportGroup.Button_Cancel");
				click("ExportGroup.Button_Cancel");
				wait(2);
				//Check after download
				File folder = new File("C:\\Users\\sonnh32\\Downloads"); //Get folder to save file
				File listFile[] = folder.listFiles();	//Get all file
				ArrayList<String> list = new ArrayList<String>();	//Find all file pdf or xls
				for (File file : listFile) {
					if(file.getName().endsWith("."+ExcelData.getCellValue("File Type").toLowerCase())) {
						list.add(file.getName());
					}
				}
				String stepDes = Message.format("RPT_WS_ERR", list.toString());
				if(list.isEmpty())
					Report.event(stepDes);
				else
					Report.event(stepDes, Report.R_FAILED, Message.format("RPT_WS_ERR", list.toString()));
				wait(2);
			}
		}
	}
	
	@KeywordInfo(Description = "action Check Security")
	public void createGroupFirst() {
		wait(2);
		click("UserGroup.Create_Button");
		wait(2);
		url = Global.webDriver.getCurrentUrl();
		System.err.println(url);
		
	}
	
	@KeywordInfo(Description = "action Check Security")
	public void gotoCreateGroupSecond() {
		navigate(url);
		String last = Global.webDriver.getCurrentUrl();
		System.err.println(last);
		assertEquals(last, ExcelData.getCellValue("URL Page"));
	}
	
	
	/**
	 * Add role from dropdown list
	 * @param status : check if user only view or edit
	 * @param fieldName : value of role
	 */
	public void addRoleforGroup (String status,String fieldName) {
		if(status.equals("Only View")) {
			//nothing action
		}
		else {
			String roles = ExcelData.getCellValue(fieldName);
			if (!roles.equals("")) {
				if (roles.contains(",")) {
					String [] arr = roles.split(",");
					for (String string : arr) {
						String xpathOption = ".//a[@class='ng-binding ng-scope' and text()='"+string+"']";
						click("GroupConfig.Dropdown_AssignRole");
						clickElementExits(xpathOption);
						
						//verify add success
						String xPathAddSuccess = ".//div[@class='n1']//p[text()=' "+string+" ']";
						checkElementExist(xPathAddSuccess);
					}
				} else {
					String xpathOption = ".//a[@class='ng-binding ng-scope' and text()='"+roles+"']";
					click("GroupConfig.Dropdown_AssignRole");
					clickElementExits(xpathOption);
					
					//verify add success
					String xPathAddSuccess = ".//div[@class='n1']//p[text()=' "+roles+" ']";
					checkElementExist(xPathAddSuccess);
				}
				
			}
		}
	}
	
	public void checkMaximumRecord(String fieldName) {
		String number = ExcelData.getCellValue(fieldName);
		String xPathRecord = ".//div[@class='ui-grid-canvas' and count(div) = "+number+"]";
		checkElementExist(xPathRecord);
	}
	
	public void checkPagination(String fieldName) {
		if(ExcelData.getCellValue(fieldName).equals("Click Arrow")) {
			String groupName_1 = ExcelData.getCellValue("First Page");
			String groupName_2 = ExcelData.getCellValue("Next Page");
			
			String xPathGrName_1 = ".//div[@class='ui-grid-row ng-scope']//p[contains(text(),'"+groupName_1+"')]";
			String xPathGrName_2 = ".//div[@class='ui-grid-row ng-scope']//p[contains(text(),'"+groupName_2+"')]";
			
			wait(2);
			checkClickable("UserGroup.NextArrow_Icon");
			click("UserGroup.NextArrow_Icon");
			checkElementExist(xPathGrName_2);
			wait(1);
			checkClickable("UserGroup.PrevArrow_Icon");
			click("UserGroup.PrevArrow_Icon");
			checkElementExist(xPathGrName_1);
			wait(1);
		}
	}
	
	@KeywordInfo(Description = "Close driver")
	public void closeWebDriver() {
		wait(2);
		closeDriver();
	}
}
