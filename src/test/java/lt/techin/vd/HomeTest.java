package lt.techin.vd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.BufferedReader;
import java.io.FileReader;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeTest extends BaseTest {
    @Test
    public void registrationSimpleTest(){
        HomePage homePage = new HomePage(driver);
        homePage.clickSignIn();
        homePage.clickRegisterNewUser();

        RegistrationPage registrationPage = new RegistrationPage(driver);
        registrationPage.selectSocialTitle();
        registrationPage.inputFirstName("Petras");
        registrationPage.inputLastName("Petraitis");
        registrationPage.inputEmail("aaaa@aaa.aa");
        registrationPage.inputPassword("slaptazodis123");
        registrationPage.inputBirthday();
        registrationPage.clickAgreeToTheTerms();
        registrationPage.clickAgreeToTheCustomerData();
        registrationPage.clickSaveButton();

        AccountPage accountPage = new AccountPage(driver);
        assertTrue(accountPage.checkSignOutButton(), "User is not logged in");
        accountPage.clickSignOutButton();
    }
    @Test
    public void loginSimpleTest(){
        HomePage homePage = new HomePage(driver);
        AccountPage accountPage = new AccountPage(driver);
        RegistrationPage registrationPage = new RegistrationPage(driver);
        homePage.clickSignIn();
        registrationPage.inputEmail("aaaa@aaa.aa");
        registrationPage.inputPassword("slaptazodis123");
        registrationPage.clickSubmit();
        assertTrue(accountPage.checkSignOutButton(), "User is not logged in");
    }
//    @Test
//    public void registrationTest(){
//
//        HomePage homePage = new HomePage(driver);
//        homePage.clickSignIn();
//        homePage.clickRegisterNewUser();
//
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        registrationPage.selectSocialTitle();
//        registrationPage.inputFirstName("Petras");
//        registrationPage.inputLastName("Petraitis");
//       // registrationPage.inputEmail();
//        registrationPage.inputEmail(registrationPage.getEmail());
//
//        registrationPage.inputPassword(registrationPage.getPassword());
//
//        registrationPage.inputBirthday();
//        registrationPage.clickAgreeToTheTerms();
//        registrationPage.clickAgreeToTheCustomerData();
//        registrationPage.clickSaveButton();
//
//        AccountPage accountPage = new AccountPage(driver);
//        assertTrue(accountPage.checkSignOutButton(), "User is not logged in");
//        accountPage.clickSignOutButton();
//    }
//
//    @Test
//    public void loginTest() {
//
//        HomePage homePage = new HomePage(driver);
//        AccountPage accountPage = new AccountPage(driver);
//        RegistrationPage registrationPage = new RegistrationPage(driver);
//        homePage.clickSignIn();
//
//        registrationPage.inputEmail(registrationPage.getEmail());
//        registrationPage.inputPassword(registrationPage.getPassword());
//        registrationPage.clickSubmit();
//        assertTrue(accountPage.checkSignOutButton(), "User is not logged in");
//
//
//
//    }

    @ParameterizedTest
    @CsvFileSource(resources = "/products.csv", numLinesToSkip = 1)
        ////,
    void readFromFile(String name, double price, double discount, String size) throws InterruptedException {
        System.out.println(name);

        HomePage homePage = new HomePage(driver);
        homePage.searchByName(name);

        driver.findElement(By.cssSelector("input[name='s']")).sendKeys(name);
        driver.findElement(By.cssSelector("input[name='s']")).sendKeys(Keys.ENTER);
        Assertions.assertEquals(name.toLowerCase(), driver.findElement(By.cssSelector(".product-description  a")).getText().toLowerCase(), "does not match");
        driver.findElement(By.cssSelector("img[src$='.jpg']")).click();
        driver.findElement(By.cssSelector(".wishlist-button-product .material-icons")).click();
        Assertions.assertEquals("You need to be logged in to save products in your wishlist.", driver.findElement(By.cssSelector(".modal-text")).getText(), "messages does not match");
        driver.findElement(By.cssSelector(".show .btn-secondary")).click();

        String cartAmount = driver.findElement(By.cssSelector(".header [aria-hidden]")).getText();

        WebElement dropdownElement = driver.findElement(By.cssSelector("select[name='group[1]']"));
        Select dropdown = new Select(dropdownElement);
        dropdown.selectByVisibleText(size);

        driver.findElement(By.cssSelector(".add-to-cart.btn.btn-primary")).click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement waits = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h4#myModalLabel")));
        String text = driver.findElement(By.cssSelector("h4#myModalLabel")).getText();
        String modiefiedText = text.substring(1);
        Assertions.assertEquals("Product successfully added to your shopping cart",modiefiedText,"messages are not equal" );

        driver.findElement(By.cssSelector(".modal-body .btn.btn-primary")).click();

        Assertions.assertFalse(driver.findElement(By.cssSelector(".cart-products-count")).equals(cartAmount), "cart amount did not change");
        Assertions.assertEquals(size, driver.findElement(By.cssSelector(".size .value")).getText(), "Sizes does not match");

        double priceWithDiscount = price-(price*discount/100);
        String formatedPrice = String.format("%.2f", priceWithDiscount).replace(",",".");
        WebElement priceElement =driver.findElement(By.cssSelector(".current-price .price"));
        String priceFromWebPage = priceElement.getText();
        String modified = priceFromWebPage.substring(1).trim();
        Assertions.assertEquals(formatedPrice,modified,"prices with discount are not the same");



    }
}
