package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class BookingPage extends BaseHelper
{
    WebDriver driver;
    public BookingPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    private void navigateToPage()
    {
        driver.get("https://www.booking.com/");
    }
    private void setDestination(String location)
    {
        WebElement setLocation = driver.findElement(By.xpath("//input[@type='search']"));
        setLocation.sendKeys(location);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//ul[@role='listbox']")));
        WebElement suggestedLocations = driver.findElement(By.xpath("//ul[@role='listbox']"));
        List<WebElement> setlocation = suggestedLocations.findElements(By.xpath("//li[contains(@role,'option')]"));
        WebElement setloc = setlocation.get(0);
        setloc.click();
    }

    private void setDates()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("bui-calendar")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("bui-calendar")));

        WebElement calendarNext = driver.findElement(By.xpath("//div[@data-bui-ref='calendar-next']"));
        calendarNext.click();

        WebElement calSelectedDisplay = driver.findElement(By.xpath("//div[@data-bui-ref='calendar-selected-display']"));
        String displayedSelected = calSelectedDisplay.getText();

        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@data-bui-ref='calendar-selected-display']"),displayedSelected));
        WebElement checkInDate = driver.findElement(By.xpath("//table/tbody/tr/td[contains(@data-date,'2021-10-19')]"));
        checkInDate.click();

        WebElement checkOutDate = driver.findElement(By.xpath("//table/tbody/tr/td[contains(@data-date,'2021-10-25')]"));
        checkOutDate.click();

        WebElement newCalSelectedDisplay = driver.findElement(By.xpath("//div[@data-bui-ref='calendar-selected-display']"));
        String newDisplayedSelected = newCalSelectedDisplay.getText();
        wdWait.until(ExpectedConditions.textToBePresentInElementLocated(By.xpath("//div[@data-bui-ref='calendar-selected-display']"),newDisplayedSelected));
    }

    private void setGuests(Integer setChildrenAge)
    {
        WebElement setGuest = driver.findElement(By.xpath("//label[@id='xp__guests__toggle']"));
        setGuest.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("xp__guests__inputs-container")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("xp__guests__inputs-container")));

        WebElement increaseNumOfChildren = driver.findElement(By.xpath("//button[@aria-label='Increase number of Children']"));
        increaseNumOfChildren.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='sb-group__children__field clearfix']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@class='sb-group__children__field clearfix']")));
        Select selectAges = new Select(driver.findElement(By.xpath("//select[@name='age']")));
        selectAges.selectByValue(String.valueOf(setChildrenAge));

        WebElement searchButton = driver.findElement(By.xpath("//button[@class='sb-searchbox__button ']"));
        searchButton.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("filterbox_options_content")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("filterbox_options_content")));

        WebElement filter = driver.findElement(By.xpath("//a[@data-name='chaincode' and @class='filterelement js-filter__element ']/label/div/span[contains(text(),'Novotel')]"));
        js.executeScript("arguments[0].scrollIntoView();",filter);
        js.executeScript("arguments[0].click();",filter);
    }


    public void bookholiday(String location,Integer setChildernAge)
    {
        navigateToPage();
        setDestination(location);
        setDates();
        setGuests(setChildernAge);
    }
}
