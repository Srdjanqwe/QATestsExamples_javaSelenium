package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoGLoginPage extends BaseHelper
{
    @FindBy (xpath = "/html/body/nav/div[1]/div[1]/div[6]") WebElement signIn;
    @FindBy (id = "login_username") WebElement emailField;
    @FindBy (id = "login_password") WebElement passField;
    @FindBy (id = "login_login") WebElement loginButton;

    WebDriver driver;

    public GoGLoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToEtsyPage() { driver.get("https://www.gog.com/"); }
    private void signIn() { signIn.click(); }
    private void enterValidCredentials(String username, String password)
    {
        //<iframe id="GalaxyAccountsFrame" name="galaxyAccounts" src="https://auth.gog.com/auth?client_id=46755278331571209&amp;redirect_uri=https%3A%2F%2Fwww.gog.com%2Fon_login_success&amp;response_type=code&amp;layout=default&amp;brand=gog&amp;gog_lc=RS_EUR_en-US" frameborder="0"></iframe>
        driver.switchTo().frame("GalaxyAccountsFrame");
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_modal__content-item")));
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