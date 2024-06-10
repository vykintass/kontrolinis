package lt.techin.vd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountPage extends BasePage{
    public AccountPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css=".hidden-sm-down.logout")
    private WebElement signOutButton;
    public boolean checkSignOutButton(){
        return signOutButton.isDisplayed();
    }
    public void clickSignOutButton(){
        signOutButton.click();
    }
}
