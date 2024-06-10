package lt.techin.vd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;


public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }
    @FindBy(css = ".user-info .material-icons")
    private WebElement clickSignIn;
    @FindBy(linkText = "No account? Create one here")
    private WebElement clickRegisterNewUser;
    @FindBy(css="input[name='s']")
    private WebElement searchByName;
    public void clickSignIn() {
        clickSignIn.click();
    }

    public void clickRegisterNewUser() {
        clickRegisterNewUser.click();
    }
    public void searchByName(String name){
        searchByName.sendKeys(name.toLowerCase());
    }


}
