package z8.art.keywords;

import static org.fest.assertions.api.Assertions.assertThat;
import static org.testng.Assert.assertEquals;

import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;




import z8.art.aut.BaseTest;
import z8.art.common.Global;
import z8.art.common.Message;
import z8.art.common.Report;
import z8.art.dal.ExcelData;

public class ProviderPages extends BaseTest {
  
  public static String Blank_Text = "Blank";
  public static String Yes = "Yes";
  
  public void selectMultiItem (String objPath, String objSearchpath, String listItem, String text) {
    String [] arr = listItem.split(", ");
    click(objPath);
    for (String string : arr) {
      type(objSearchpath, string);
      String resultItem = "//Label[text()='" + text + "']/..//span[text()='" + string + "']/..";
      clickElementExits(resultItem);
      clearText(objSearchpath);
    }
  }
  
  public void clickElementExits (String xpath) {
    String stepDes = Message.format("RPT_CLICK", xpath);
    WebElement eleResult = getElementByXpath(xpath);
    if(eleResult == null){
      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_NOT_EXIST", xpath));
    }else {
      eleResult.click();
      Report.event(stepDes);
    }
  }
  
  public WebElement getElementByXpath (String xpath) {
    WebElement ele = null;
    By byResult = By.xpath(xpath);
    try {
      ele = Global.webDriver.findElement(byResult);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ele;
  }
  
  public Boolean checkElementExist(String xpath) {
    boolean r = false;
    String stepDes = Message.format("RPT_VERIFY_EXIST", xpath);
    WebElement ele = getElementByXpath(xpath);
    if (ele != null){
      r = true;
      Report.event(stepDes);
    } else {
      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_NOT_EXIST", xpath));
    }
    return r;
  }
  
  public Boolean checkElementNotExist(String xpath) {
    boolean r = false;
    String stepDes = Message.format("RPT_VERIFY_NOT_EXIST", xpath);
    WebElement ele = getElementByXpath(xpath);
    if (ele == null){
      r = true;
      Report.event(stepDes);
    } else {
      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_EXIST", xpath));
    }
    return r;
  }
  
  public void loadFilterExits () {
    click("SearchOrders.Button_LoadFilter");
    wait(2);
    typeText("LoadFilter.Input_FilterName", "Filter Name");
  }
  
  public void clickCheckBox (String obj, String textData) {
    boolean checked = checkSelected(obj);
    if (textData.equals("Clickable")) {
      if(!checked)
        click(obj);
    } else {
      if(checked)
        click(obj);
    }
  }
  
  public void selectListItemExcel (String objXpath, String fieldName) {
	  String text = ExcelData.getCellValue(fieldName);
	    if(!text.equals("")){
	    	selectListItem(objXpath, fieldName);	      
	    }	  
  }

  public void typeTextExcel (String objXpath, String fieldName) {
    String text = ExcelData.getCellValue(fieldName);
    if(!text.equals("")){
      if(text.equals(Blank_Text)){
        clearText(objXpath);
      } else {
        clearText(objXpath);
        wait(2);
        typeText(objXpath, fieldName);
      }
    }
  }
  
  public void checkClickAndWait (String objtXpath, String fieldName, int... time) {
    int timeWait = (time.length == 1) ? time[0] : 0;
    if(ExcelData.getCellValue(fieldName).equals(Yes)) {
      click(objtXpath);
      wait(timeWait);
    }
  }
  
  public Boolean checkClickable(String objPath) {
	  
    Boolean r = false ;
    String stepDes = Message.format("RPT_VERIFY_CLICKABLE", objPath);
    WebDriverWait wait = new WebDriverWait(Global.webDriver, 10);
    WebElement ele = wait.until(ExpectedConditions.elementToBeClickable(getBy(objPath)));
    if (ele != null) 
      r = true;
    if (r == true)
      Report.event(stepDes);
    else
      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_ENABLED", objPath));

    return r;
  }


  public void compareTextContains(String actual, String expected) {
		String stepDes = Message.format("RPT_VERIFY_CONTAINS", expected, expected);
		try {
			assertThat(actual).contains(expected);
		} catch (AssertionError ae) {
			Report.event(stepDes, Report.R_FAILED, Message.format("RPT_ACTUAL_VALUE", actual));
			Assert.assertTrue(false);
		}

	  
	}
  
  public void compareText(String actual, String expected) {
    String stepDes = Message.format("RPT_VERIFY_EQUALS", expected, expected);
    try {
      assertThat(actual).contains(expected);
    } catch (AssertionError ae) {
      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_ACTUAL_VALUE", actual));
    }
  }
  
  public void compareTextFullReport(String actual, String expected) {
	    String stepDes = Message.format("RPT_VERIFY_EQUALS", expected, expected);
	    try {
	      assertThat(actual).contains(expected);
	      Report.event(stepDes);
	    } catch (AssertionError ae) {
	      Report.event(stepDes, Report.R_FAILED, Message.format("RPT_ACTUAL_VALUE", actual));
	    }
	  }
  
  public void verifyValueDefaultCombobox(String objPath, String fieldname) {
    String expected = ExcelData.getCellText(fieldname);
    if (!expected.equals("")) {
      if (expected.equals(Blank_Text)) 
        expected = "";
      
      String stepDes = Message.format("RPT_VERIFY_EQUALS", fieldname, expected);
      String actual = getListSelectedItem(objPath);
      try {
        assertThat(actual).contains(expected);
      } catch (AssertionError ae) {
        Report.event(stepDes, Report.R_FAILED, Message.format("RPT_ACTUAL_VALUE", actual));
      }
      
    }
  }
  
  public void waitBlockUI(int seconds)
  {
	  int i = 0;
	  while (isExisted("HeadMenu.BlockUI", 2)&&i<seconds)
	  {
		  wait(1);
		  i++;
	  }
  }
  
  public String getTextExcel(String excelFieldName)
  {
	  try {
		return getDataText(excelFieldName);
	} catch (Exception e) {
		System.out.println("field data is null");
		return "";
	}
  }
  
  
  public void verifyOptionsCombobox (String objPath) {
    //call API
    
    //get list text of combobox
    
    String options = getListItemTexts(objPath);
    ArrayList<String> listOptions = new ArrayList<>();
    if (options.contains("|")) {
      String [] list = options.split("|");
      for (String string : list) {
        listOptions.add(string);
      }
    } else {
      listOptions.add(options);
    }
    
    // compare text
    if (listOptions.size() > 0) {
//      compareTextContains(actual, expected);
    }
    
  }
  
  public void selectOptionByStringXpath(String xpathSelect, String fieldName) {
	  String expText = ExcelData.getCellValue(fieldName);
	  Select select = new Select(getElementByXpath(xpathSelect));
	  String allText = getListTextsOptionOfSelect(xpathSelect);
	  String stepDes = Message.format("RPT_SELECT_LIST", fieldName, expText);
	  if (allText.isEmpty()) 
			Report.event(stepDes, Report.R_FAILED, Message.format("RPT_NOT_EXIST_ITEM", xpathSelect));
	 
	  if (allText.contains(expText)) {
		  select.selectByVisibleText(expText);
		  Report.event(stepDes);
	  } else {
		  Report.event(stepDes, Report.R_FAILED, Message.format("RPT_NOT_IN_LIST", xpathSelect));
	  }
	  
  }

  protected String getListTextsOptionOfSelect(String xpathSelect) {
		String r = "";

		if (getElementByXpath(xpathSelect) == null)
			return r;
		else {
			Select se = new Select(getElementByXpath(xpathSelect));
			for (int i = 0; i < se.getOptions().size(); i++) {
				r += "|" + se.getOptions().get(i).getText();
			}

			if (se.getOptions().size() > 0)
				r += "|";
		}
		return r;
	}
  
  public void selectMultiValue (String objXPath,String fieldName)
  {   
    String listItem =getTextExcel(fieldName);   
    if (listItem.equals("")) return;
    objXPath = objXPath + ".XPath";
    String xPath = Global.propObjRepo.getProperty(objXPath);
    String [] arr = listItem.split(",");    
    Select select = new Select(getElementByXpath(xPath));
    for (String provider : arr) 
    { 
      select.selectByVisibleText(provider);           
    }   
  }
  
  public void checkContainsByXpath(String xPath, String fieldName) {
		String expected = ExcelData.getCellText(fieldName);
		String actual = Global.webDriver.findElement(By.xpath(xPath)).getText();
		String stepDes = Message.format("RPT_VERIFY_CONTAINS", xPath, expected);

		try {
			assertEquals(actual, expected);
			Report.event(stepDes);

		} catch (NullPointerException e) {
			Report.event(stepDes, Report.R_FAILED,
					Message.format("RPT_ACTUAL_VALUE", actual));
		} catch (Exception e) {
			Report.event(stepDes, Report.R_FAILED,
					Message.format("RPT_ACTUAL_VALUE", actual));
		}
	}
  
  public void checkStatusOfElement (String objPath, String expectStatus) {
    switch (expectStatus) {
    case "Not Displayed":
        checkNotDisplayed(objPath);
        break;
    case "Displayed":
        checkDisplayed(objPath);
        break;
    case "Disabled":
        checkDisable(objPath);
        break;
    case "Enabled":
        checkEnabled(objPath);
        break;
    case "Clickable":
      checkClickable(objPath);
        break;
    case "Selected":
        checkSelected(objPath);
        break;
    case "Not Selected":
      checkNotSelected(objPath);
        break;
    case "Checked":
        checkSelected(objPath);
        break;
    case "Unchecked":
        checkNotSelected(objPath);
        break;
    default:
        break;
    }
  }
  
}
