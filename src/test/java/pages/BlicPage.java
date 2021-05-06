package pages;

import helpers.BaseHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;


public class BlicPage extends BaseHelper
{
    @FindBy (className = "fa-search") WebElement magnifierGlassIcon;
    @FindBy (id = "search-field-head") WebElement searchField;
    @FindBy (xpath = "/html/body/header/div[1]/div/div[3]/form/button") WebElement searchButton;

    WebDriver driver;
    public BlicPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToHomePage()
    {
        driver.get("https://www.blic.rs");
    }

    private void clickOnMagnifierGlassIcon()
    {
        magnifierGlassIcon.click();
    }

    private void typeSearchTermInSearchField(String searchTerm)
    {
        wdWait.until(ExpectedConditions.visibilityOf(searchField));
        searchField.sendKeys(searchTerm);
    }

    private void clickOnSearchButton()
    {
        searchButton.click();
    }

    public void blicSearch(String term)
    {
        navigateToHomePage();
        clickOnMagnifierGlassIcon();
        typeSearchTermInSearchField(term);
        clickOnSearchButton();
    }

}
