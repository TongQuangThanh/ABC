package z8.art.keywords;

import z8.art.object.KeywordInfo;

public class LoginPage extends ProviderPages {

	public static String URL = "http://10.88.96.125:8110/login";

	@KeywordInfo(Description = "Login for Tribo")
	public void loginTribo() {
		navigate(URL);
		typeText("LoginPage.Username_Text", "Username");
		typeText("LoginPage.Password_Text", "Password");
		click("LoginPage.Login_Button");
		checkExist("HomePage.Account_Button");
	}

}
