package z8.art.keywords;

import z8.art.object.KeywordInfo;

public class HomePage extends ProviderPages{

	
/*	@KeywordInfo(Description = "go to [User] page")
	public void goToUserPage() {
		wait(2);
		clickHover("HomePage.Menu_Admin", 1, "HomePage.MenuItem_User");
		checkExist("HomePage.Account_Button");
	}*/
	
	@KeywordInfo(Description = "go to [Group] page")
	public void goToGroupPage() {
		wait(2);
		clickHover("HomePage.Menu_Admin", 1, "HomePage.MenuItem_Group");
		checkExist("GroupPage.Label_Group");
	}
	
	@KeywordInfo(Description = "go to [Role] page")
	public void goToRolePage() {
		wait(2);
		clickHover("HomePage.Menu_Admin", 1, "HomePage.MenuItem_Role");
		checkExist("RolePage.Title");
	}
}
