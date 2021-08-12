package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Iterator;
import java.util.Set;

public class EtsyLoginPage extends BaseHelper
{
    @FindBy (className ="select-signin") WebElement signIn;
    @FindBy (name ="email") WebElement emailField;
    @FindBy (name ="password") WebElement passField;
    @FindBy (name ="submit_attempt") WebElement loginButton;

    WebDriver driver;

    public EtsyLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToEtsyPage() { driver.get("https://www.etsy.com/"); }
    private void signIn() { signIn.click(); }
    private void enterValidCredentials(String username, String password)
    {
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("join-neu-form")));
        emailField.sendKeys(username);
        passField.sendKeys(password);
    }

    private void presslogin() { loginButton.click(); }

    public void login(String username,String password)
    {
        navigateToEtsyPage();
        signIn();
        enterValidCredentials(username,password);
        presslogin();
    }
}
