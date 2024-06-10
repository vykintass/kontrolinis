package lt.techin.vd;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.Random;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }
    @FindBy(id = "field-id_gender-1")
    private WebElement selectSocialTitle;
    @FindBy(id = "field-firstname")
    private WebElement inputFirstName;
    @FindBy(id = "field-lastname")
    private WebElement inputLastName;
    @FindBy(id = "field-email")
    private WebElement inputEmail;
    @FindBy(id = "field-password")
    private WebElement inputPassword;
    @FindBy(id="submit-login")
    private WebElement clickSubmit;
    @FindBy(id = "field-birthday")
    private WebElement inputBirthday;
    @FindBy(css = "input[name='psgdpr']")
    private WebElement clickAgreeToTheTerms;
    @FindBy(css = "input[name='customer_privacy']")
    private WebElement clickAgreeToTheCustomerData;
    @FindBy(css=".btn.btn-primary.float-xs-right.form-control-submit")
    private WebElement clickSaveButton;
    @FindBy(id="submit-login")
    private WebElement clickSignIn;

    public void selectSocialTitle() {
        selectSocialTitle.click();
    }

    public void inputFirstName(String name) {
        inputFirstName.sendKeys(name);
    }

    public void inputLastName(String lastname) {
        inputLastName.sendKeys(lastname);
    }

        private String email;
        private String password;

        public void generateCredentials() {
           email = generateRandomGmail();
           password = generateRandomPassword();
        }
        public String getEmail() {
            return email;
        }

        public String getPassword() {
            return password;
        }
    public static String generateRandomString(int length) {
        String characters = "abcdefghijklmnopqrstuvwxyz";
        StringBuilder sb = new StringBuilder(length);
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            sb.append(characters.charAt(random.nextInt(characters.length())));
        }
        return sb.toString();
    }

    public static String generateRandomGmail() {
        String username = generateRandomString(3);
        String domain = generateRandomString(2) + ".com";
        return   username + "@" + domain;
    }

    public static int generateRandomNumber() {
        Random random = new Random();
        int randomNumber = random.nextInt(10) + 1;
        return randomNumber;
    }
    public static String generateRandomPassword() {
        String password = generateRandomString(8);
        return password + generateRandomNumber();
    }

    public void inputPassword(String password) {
        // password = generateRandomPassword();
        this.password = password;
        inputPassword.sendKeys(this.password);
    }
    public void inputEmail(String email) {
        //userEmail = generateRandomGmail();
        this.email = email;
        inputEmail.sendKeys(this.email);


    }

    public void clickSubmit(){
        clickSubmit.click();
    }

    public static int generateRandomDay() {
        Random random = new Random();
        int randomNumber = random.nextInt(31) + 1;
        return randomNumber;
    }

    public static int generateRandomMonth() {
        Random random = new Random();
        int randomNumber = random.nextInt(12) + 1;
        return randomNumber;
    }

    public static int generateRandomYear() {
        Random random = new Random();
        int randomNumber = random.nextInt(65) + 1960;
        return randomNumber;
    }

    public void inputBirthday() {
//        int month = generateRandomNumber();
//        int day = generateRandomNumber();
//        int year = generateRandomNumber();

        inputBirthday.sendKeys(generateRandomMonth() + "/" + generateRandomDay() + "/" + generateRandomYear());
    }

    public void clickAgreeToTheTerms() {
        clickAgreeToTheTerms.click();
    }

    public void clickAgreeToTheCustomerData() {
        clickAgreeToTheCustomerData.click();
    }
    public void clickSaveButton(){
        clickSaveButton.click();
    }


}
