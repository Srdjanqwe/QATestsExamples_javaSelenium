package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HeroPage extends BaseHelper
{
    @FindBy (id="username") WebElement userBox;
    @FindBy (id="password")  WebElement passBox;
    @FindBy (className="fa-sign-in")  WebElement loginButton;

    WebDriver driver;
    public HeroPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
    private void navigateToHeroPage()
    {
        driver.get("https://the-internet.herokuapp.com/login");
    }
    private void enterUserName(String username) { userBox.sendKeys(username); }
    private void enterPassName(String password)
    {
        passBox.sendKeys(password);
    }
    private void clickOnLoginButton()
    {
        loginButton.click();
    }

    public void login(String usernametom, String passwordtom)
    {
        navigateToHeroPage();
        enterUserName(usernametom);
        enterPassName(passwordtom);
        clickOnLoginButton();
    }
}
