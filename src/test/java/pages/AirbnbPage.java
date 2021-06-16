package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.*;

public class AirbnbPage extends BaseHelper {
    @FindBy(className = "_1xq16jy") WebElement locationField;
    @FindBy(className = "_uh2dzp") WebElement dateField;
    @FindBy(className = "_1mzhry13") WebElement searchButton;
    @FindBy(className = "_ravnr26") WebElement checkBox;
    @FindBy (className = "_1h559tl") WebElement searchResultsInfo;
    @FindBy (id = "menuItemButton-price_range") WebElement filterPrice;
    @FindBy (id = "price_filter_max") WebElement priceValue;
    @FindBy (id = "filter-panel-save-button") WebElement saveFilterButton;

    public List<BookedBnb> addedInfo;
    public String itemName;
    public String adpicker;
    public String ddpicker;
    public String setGuestValue;
    public String pricePerNight;
    public String avgTotalPrice;

    public String resADCheck;
    public String resDDCheck;
    public String resNGCheck;
    public String resCTPrice;

    WebDriver driver;

    public AirbnbPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToHomePage() { driver.get("https://www.airbnb.rs/"); }
    private void setLocation(String location)
    {
        // close privacy cookies
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_b35z1j")));
        WebElement clickok = driver.findElement(By.className("_1qnlffd6"));
        clickok.click();

//        driver.findElement(By.xpath("//input[@class='_1xq16jy']")).click();
        locationField.sendKeys(location);

//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_xgnsuq")));
//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_rj7nz")));
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("_rj7nz"),"Istražite obližnja odredišta"));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("Koan-query__listbox")));
        WebElement suggestedLocations = driver.findElement(By.id("Koan-query__listbox"));
        List<WebElement> locations = suggestedLocations.findElements(By.xpath("//li[contains(@id,'Koan-query__option-')]"));

        WebElement getloc = locations.get(0);
        System.out.println("get los:"+getloc.getText());
        getloc.getText();

        addedInfo = new ArrayList<>(); }

    private void getBookInfo(Integer setGuests) {

        BookedBnb addInfo = new BookedBnb();

        dateField.click();
        // date panel
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_1foj6yps")));
        WebElement dateArrivalList = driver.findElement(By.className("_1foj6yps"));

        // only available date list
        List<WebElement> dates = dateArrivalList.findElements(By.className("_12fun97"));

        WebElement setArrivalDate = dates.get(2);
//        System.out.println(setArrivalDate.getAttribute("aria-label"));
        js.executeScript("arguments[0].click();",setArrivalDate);
//        setArrivalDate.click();
//        System.out.println("set arrival picker date:" + setArrivalDate.findElement(By.className("_1258d0t")).getAttribute("data-testid"));
//        addInfo.arrivalDate = setArrivalDate.findElement(By.className("_1258d0t")).getAttribute("data-testid").replace("datepicker-day-","");
        adpicker = setArrivalDate.findElement(By.className("_1258d0t")).getAttribute("data-testid").replace("datepicker-day-","");
        System.out.println("set arrival picker date:" +adpicker);
        addInfo.arrivalDate = adpicker;

        WebElement setDepartureDate = dates.get(4);
//        System.out.println(setDepartureDate.getAttribute("aria-label"));
        js.executeScript("arguments[0].click();",setDepartureDate);
//        setDepartureDate.click();
        ddpicker = setDepartureDate.findElement(By.className("_1258d0t")).getAttribute("data-testid").replace("datepicker-day-","");
        System.out.println("set departure picker date:" +ddpicker);
        addInfo.departureDate = ddpicker;
        System.out.println("--------");

        //guests
        WebElement guestField = driver.findElement(By.className("_uh2dzp"));
        guestField.click();
//        String defaultGetGuestField = guestField.getText();
//        System.out.println("default Guest:" +defaultGetGuestField);

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_1573fat")));
        WebElement increaseGuest = driver.findElement(By.cssSelector("._7hhhl3:enabled"));

        for (int i = 0; i < setGuests; i++) {
            increaseGuest.click();
        }

        setGuestValue = guestField.getText();
        System.out.println("set guests:" + setGuestValue);
        addInfo.guest = setGuestValue;
        System.out.println("-----------");

        searchButton.click();

        // unchecked check-box
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_fhph4u")));
        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("_fhph4u")));
        checkBox.click();

        WebElement searchResults = driver.findElement(By.className("_fhph4u"));
        List<WebElement> itemList = searchResults.findElements(By.className("_8ssblpx"));
        // Fetch max value price
        ArrayList<Integer> priceList = new ArrayList<>();

        for (int i = 0; i<itemList.size(); i++) {
//            System.out.println("lista:"+priceList);
            Integer pricePerNight = Integer.parseInt(itemList.get(i).findElement(By.className("_155sga30")).getText().replace("$", ""));
            //            System.out.println(pricePerNight);

            pricePerNight = new Integer(pricePerNight);
            priceList.add(pricePerNight);
//            System.out.println(priceList);

//            System.out.println("per night" + " " + "$" + " " +pricePerNight);
//            System.out.println("--------");
        }
        System.out.println("Price list from search results:"+priceList);
        System.out.println("size list res per page:"+priceList.size());
        Integer maxValue = Collections.max(priceList);
        System.out.println("Max value price:"+Collections.max(priceList));
        String oldSearchResult = searchResultsInfo.getText();
//        System.out.println("sr get:" +searchResultsInfo.getText());
//        System.out.println("search results:"+searchResultsInfo.getText().replace(" smeštaja","").replace("1 – 20 od ",""));
        System.out.println("search results:"+oldSearchResult.replace(" smeštaja","").replace("1 – 20 od ",""));
        System.out.println("--------");

        filterPrice.click();

        // wait modal
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_10c4snr0")));
        WebElement modal = driver.findElement(By.className("_10c4snr0"));
        js.executeScript("arguments[0].scrollIntoView();",modal);

//        wdWait.until(ExpectedConditions.visibilityOf(priceValue));
        wdWait.until(ExpectedConditions.visibilityOfElementLocated(By.id("price_filter_max")));
//
        driver.findElement(By.xpath("//input[@id='price_filter_max']")).click();
//        WebElement pricefiltermax = driver.findElement(By.xpath("//input[@id='price_filter_max']"));
//        js.executeScript("arguments[0].click();",pricefiltermax);
        priceValue.sendKeys(Keys.chord(Keys.CONTROL, "a"), Keys.DELETE);
        priceValue.sendKeys(String.valueOf(maxValue-1));
        System.out.println("nova max:" +priceValue.getAttribute("value"));

        saveFilterButton.click();
//        WebElement savefilterbutton = driver.findElement(By.id("filter-panel-save-button"));
//        js.executeScript("arguments[0].scrollIntoView();",savefilterbutton);
//        js.executeScript("arguments[0].click();", savefilterbutton);

        WebElement backToHeader = driver.findElement(By.className("_zdxht7"));
        js.executeScript("arguments[0].scrollIntoView();",backToHeader);
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("_36rlri"),"Cena"));
//        wdWait.until(ExpectedConditions.elementToBeClickable(By.className("_fhph4u")));

//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_1h559tl")));
//        WebElement newSearch = driver.findElement(By.className("_1h559tl"));
//        String oldFetchSearchRes = newSearch.getText();
//        System.out.println("oldFetch:"+oldFetchSearchRes);

//        js.executeScript("arguments[0].scrollIntoView();",newSearch);
//        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_1h559tl")));
//        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("_1h559tl"), oldFetchSearchRes));
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("_1h559tl"), oldSearchResult));
        searchResultsInfo.getText();

        String newSearchResult = searchResultsInfo.getText().replace(" smeštaja","").replace("1 – 20 od ","");
        System.out.println("Novi search result:" +newSearchResult);
        System.out.println("------");

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_fhph4u")));
        WebElement newFilteredSearch = driver.findElement(By.className("_fhph4u"));
        List<WebElement> newItemList = newFilteredSearch.findElements(By.className("_8ssblpx"));
//        int  counter=0;
//        for(WebElement _8ssblpx:newItemList)
//        {
//            counter ++;
//            System.out.println(counter);
//            System.out.println(_8ssblpx.getText());
//            System.out.println("----------------");
//
//        }
        WebElement selectApartment = newItemList.get(1);
        js.executeScript("arguments[0].scrollIntoView();",selectApartment);
        System.out.println("Get info:"+selectApartment.getText());
//        getNewUrl = selectApartment.findElement(By.className("_8s3ctt")).findElement(By.tagName("a")).getAttribute("href");
//

        System.out.println("-----------");
//        System.out.println("new url ->:"+getNewUrl);
//        System.out.println("-----------");
        pricePerNight = selectApartment.findElement(By.className("_155sga30")).getText().replace("$","");
        System.out.println("Price per night:"+" "+"$"+" "+pricePerNight);
        addInfo.pricePerNight = pricePerNight;
        avgTotalPrice = selectApartment.findElement(By.className("_ebe4pze")).getText().replace("Pogledajte cenu po stavkama","").replace("Ukupno ","").replace("$","");
        System.out.println("Total:"+" "+"$"+" "+avgTotalPrice);
        addInfo.avgTotalPrice = avgTotalPrice;
        System.out.println("-------");
//

        // current window handle
        String chandle = driver.getWindowHandle();
//        System.out.println("Current window handle:"+chandle);
        selectApartment.click();
//        System.out.println("After click window handle:" +driver.getWindowHandle());
//        System.out.println("-----");
        // new window
        Set<String> handles = driver.getWindowHandles();
        Iterator it = handles.iterator();
        String newHandle = null;
        String handle= null;
        while (it.hasNext()) {
            while (it.hasNext()) {
                handle = it.next().toString();
//                System.out.println(handle);
//                System.out.println("-------");
                if (chandle.contentEquals(handle)) {

                } else {
                    newHandle = handle;
                }
            }
        }
        driver.switchTo().window(newHandle);

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_fecoyn4")));
        WebElement getName = driver.findElement(By.className("_fecoyn4"));
        itemName = getName.getText();
        System.out.println("h1 on selected name:"+itemName);

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_ud8a1c")));

        WebElement resCheckArrivalDate = driver.findElement(By.className("_1acx77b"));
        resADCheck = resCheckArrivalDate.getText().replace("DOLAZAK","").trim();
        System.out.println("arrival date check:" +resADCheck);

        WebElement resCheckDepartureDate = driver.findElement(By.className("_14tl4ml5"));
        resDDCheck = resCheckDepartureDate.getText().replace("ODLAZAK","").trim();
        System.out.println("departure date check:" +resDDCheck);

        WebElement resCheckNumGuest = driver.findElement(By.className("_1ir6ymk"));
        resNGCheck = resCheckNumGuest.getText();
        System.out.println("guest num check:" +resNGCheck);

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("_vcv8ong")));
        WebElement resCheckTotal = driver.findElement(By.className("_vcv8ong"));
        js.executeScript("arguments[0].scrollIntoView();",resCheckTotal);
        resCTPrice =resCheckTotal.getText().replace("Ukupno","").replace("$","").trim();
        System.out.println("total price check:"+resCTPrice+" "+"$");

        addedInfo.add(addInfo);
    }

    public void bookyourholiday(String location, Integer setGuests)
    {
        navigateToHomePage();
        setLocation(location);
        getBookInfo(setGuests);
    }
}