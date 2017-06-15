package z8.art.keywords;

import z8.art.dal.ExcelData;
import z8.art.object.KeywordInfo;

public class UserPage extends ProviderPages {
	
	@KeywordInfo(Description = "Check UI for User Page")
	public void checkUIOnUserPage() {
		if (!ExcelData.getCellValue("Verify UI Input Search").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Search").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Create").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Export").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Header Table").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
	}
	
	@KeywordInfo(Description = "Check UI for User Detail Page")
	public void checkUIOnUserDetailPage() {
		if (!ExcelData.getCellValue("Verify UI Input Search").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Search").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Create").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Button Export").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
		
		if (!ExcelData.getCellValue("Verify UI Header Table").equals("")) 
			checkStatusOfElement("UserPage.Input_Search", "fieldName");
	}
	
	
	@KeywordInfo(Description = "action Search on User")
	public void searchOnGroup () {
		String textSearch = ExcelData.getCellValue("Input To Search");
		typeTextExcel("UserPage.Input_Search", "Input To Search");
		
		checkClickAndWait("GroupPage.Search_Button", "Click Search Button");
		
		//verify no result after search
		if(ExcelData.getCellValue("Check No Result Search").equals(Yes));
			checkExist("UserPage.Label_resultPage");
			
		//verify have results
		String xpathResult = "//div[contains(text(),'" + textSearch + "')]"; 
		if(ExcelData.getCellValue("Check Exist Result Search").equals(Yes));
			checkElementExist(xpathResult);
		
	}			 	 	
	
	@KeywordInfo(Description = "action create or edit on User")
	public void createOrEditUser () {
		checkClickAndWait("Button Create New", "Field excel");
		
		String groupEdit = ExcelData.getCellValue("Group Name Edit");
		String xpath = "//span[" + groupEdit + "]/../../a";
		if (!groupEdit.equals(""))
			clickElementExits(xpath);
		
		String groupName = ExcelData.getCellValue("file input group name excel");
		typeTextExcel("xpath group name", "file input group name excel");
		
		typeTextExcel("xpath group des", "file input group des excel");
		
		if ( ExcelData.getCellValue("Visible to customer").equals(Yes))
			click("xpath checkbox");
		
		
		typeTextExcel("xpath group name", "file input group name excel");

		
		checkClickAndWait("Button Ada", "Field excel");
		
		checkClickAndWait("Button Cancel", "Field excel");
		
		//verify message success
		if (ExcelData.getCellValue("Check Message success").equals(Yes))
			checkExist("xpath message success");
		
		//verify message unsuccess
		if (ExcelData.getCellValue("Check Message Unsuccess").equals(Yes))
			checkExist("xpath message unsuccess");
			
		//verify adding success
		String xpathGroupAdd = "//div[@text()='" + groupName + "']" ;
		
		if (ExcelData.getCellValue("Check Adding success").equals(Yes))
			checkExist(xpathGroupAdd);
		
		if (ExcelData.getCellValue("Check Adding Unsuccess").equals(Yes))
			checkNotExist(xpathGroupAdd);
		
		
	}

}
