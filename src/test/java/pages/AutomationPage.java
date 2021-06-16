package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class AutomationPage extends BaseHelper
{
    @FindBy (className = "blockbestsellers") WebElement bestSellers;
    @FindBy (name = "Submit") WebElement addToCart;
    @FindBy (xpath = "/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]") WebElement proceedToCheckout;
    @FindBy (name = "email") WebElement inputEmailField;
    @FindBy (name = "passwd") WebElement inputPassField;
    @FindBy (id = "SubmitLogin") WebElement submitLogin;
    @FindBy (name = "processAddress") WebElement submitButton;
    @FindBy (className = "checker") WebElement checker;
    @FindBy (name = "processCarrier") WebElement proceedCarrier;
    @FindBy (className = "bankwire") WebElement  payingByBankWire;
    @FindBy (xpath = "/html/body/div/div[2]/div/div[3]/div/form/p/button") WebElement confirmOrder;

    public String setQty;
    public String unitPrice;
    public String total;
    public String cityAdress;

    public Double setQtyDouble;
    public Double articUnitPriceDouble;
    public Double totalDouble;

    WebDriver driver;
    public AutomationPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    private void navigateToPage() { driver.get("http://automationpractice.com/index.php"); }
    private void clickOnBestSellers() { bestSellers.click(); }

    private void selectProduct()
    {
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("blockbestsellers")));
        WebElement bestSellersArticles = driver.findElement(By.id("blockbestsellers"));
        List<WebElement> articles = bestSellersArticles.findElements(By.tagName("li"));

        WebElement thirdArticle = articles.get(2);
        thirdArticle.findElement(By.className("product-container")).click();
    }

    private void chooseSizeAndColour()
    {
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("group_1")));

        Select selectSize = new Select(driver.findElement(By.id("group_1")));
        selectSize.selectByVisibleText("L");

        WebElement selectColour = driver.findElement(By.id("color_to_pick_list"));
        List<WebElement> colourList = selectColour.findElements(By.tagName("li"));

        WebElement selectWhite = colourList.get(0);
        selectWhite.findElement(By.tagName("a")).click();
    }

    private void addToCart() { addToCart.click(); }

    private void validationThrowCheckOut()
    {
        //wait Modal
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("icon-ok")));
        WebElement proceedToCheckOut = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a"));
        js.executeScript("arguments[0].click();",proceedToCheckOut);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("cart_summary")));
        WebElement getQty = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/input[2]"));
        setQty = getQty.getAttribute("value");
        System.out.println("qty:"+setQty);

        WebElement price = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[6]/span"));
        unitPrice = price.getText();
        System.out.println("Unit price:"+unitPrice);
        getQty.clear();

        getQty.sendKeys("4");
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[6]/span"), unitPrice));
        setQty = getQty.getAttribute("value");
        System.out.println("set Qty:" +setQty);
        total = price.getText();
        System.out.println("total:"+total);

        articUnitPriceDouble = Double.parseDouble(unitPrice.replace("$",""));
        totalDouble = Double.parseDouble(total.replace("$",""));
        setQtyDouble = Double.parseDouble(setQty);

    }

    private void proceedToCheckout() { proceedToCheckout.click(); }
    private void signInEmail(String email)
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        inputEmailField.sendKeys(email);
    }
    private void signInPass(String password) { inputPassField.sendKeys(password); }
    private void submit() { submitLogin.click(); }

    private void checkAdress()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        WebElement adressItemBox = driver.findElement(By.id("address_delivery"));
        List<WebElement> adressInfo = adressItemBox.findElements(By.tagName("li"));
         WebElement adressContainsCity = adressInfo.get(2);
        cityAdress = adressContainsCity.getText();
    }

    private void  submitButton() { submitButton.click(); }

    private void checker()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("carrier_area")));
        checker.click();
    }

    private void procedCarrier() { proceedCarrier.click(); }

    private  void payingByBankWire()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        payingByBankWire.click();
    }

    private void confirmOrder()
    {
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        confirmOrder.click();
    }

    public void check(String email, String password)
    {
        navigateToPage();
        clickOnBestSellers();
        selectProduct();
        chooseSizeAndColour();
        addToCart();
        validationThrowCheckOut();
        proceedToCheckout();
        signInEmail(email);
        signInPass(password);
        submit();
        checkAdress();
        submitButton();
        checker();
        procedCarrier();
        payingByBankWire();
        confirmOrder();
    }
}
