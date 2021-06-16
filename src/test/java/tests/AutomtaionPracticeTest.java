package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import pages.AutomationPage;

import java.util.List;

public class AutomtaionPracticeTest extends BaseTest
{
    @Test
    public void Automation() throws InterruptedException {

        driver.get("http://automationpractice.com/index.php");

        WebElement bestSellerCat = driver.findElement(By.className("blockbestsellers"));
        bestSellerCat.click();

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("blockbestsellers")));
        WebElement bestSellersArticles = driver.findElement(By.id("blockbestsellers"));
        List<WebElement> articles = bestSellersArticles.findElements(By.tagName("li"));
//        System.out.println(articles.size());

        WebElement thirdArticle = articles.get(2);
        thirdArticle.findElement(By.className("product-container")).click();

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("group_1")));

        Select selectSize = new Select(driver.findElement(By.id("group_1")));
        selectSize.selectByVisibleText("L");

        WebElement selectColour = driver.findElement(By.id("color_to_pick_list"));
        List<WebElement> colourList = selectColour.findElements(By.tagName("li"));

        WebElement selectWhite = colourList.get(0);
        selectWhite.findElement(By.tagName("a")).click();

        WebElement addToCartButton = driver.findElement(By.name("Submit"));
        addToCartButton.click();

        //wait Modal
        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("icon-ok")));
        WebElement proceedToCheckOut = driver.findElement(By.xpath("/html/body/div/div[1]/header/div[3]/div/div/div[4]/div[1]/div[2]/div[4]/a"));
        js.executeScript("arguments[0].click();",proceedToCheckOut);

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("cart_summary")));
        WebElement getQty = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[5]/input[2]"));


        WebElement price = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[6]/span"));

        String unitPriceDouble = price.getText();
//        System.out.println("Unit price:"+unitPriceDouble);
        getQty.clear();

        getQty.sendKeys("4");
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.xpath("/html/body/div/div[2]/div/div[3]/div/div[2]/table/tbody/tr/td[6]/span"), unitPriceDouble));

        String totalDouble = price.getText();
//        System.out.println("total:"+totalDouble);

        double articleunitPrice = Double.parseDouble(unitPriceDouble.replace("$",""));
        double totalSummaryDouble = Double.parseDouble(totalDouble.replace("$",""));
        double Qty = 4.00;

        Assert.assertEquals("nisu isti", totalSummaryDouble,articleunitPrice*Qty, 0.0);

        WebElement proceedToCheckout = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/p[2]/a[1]"));
        proceedToCheckout.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        WebElement inputEmailField = driver.findElement(By.name("email"));
        inputEmailField.sendKeys("bovor33296@rphinfo.com");

        WebElement inputPassField = driver.findElement(By.name("passwd"));
        inputPassField.sendKeys("qwerty1");

        WebElement submitLogin = driver.findElement(By.id("SubmitLogin"));
        submitLogin.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        WebElement adressItemBox = driver.findElement(By.id("address_delivery"));
        List<WebElement> adressInfo = adressItemBox.findElements(By.tagName("li"));
        int counter = 0;
        for(WebElement li:adressInfo)
        {
            counter++;
            System.out.println(counter);
            System.out.println("------");
            System.out.println(li.getText());
        }
        WebElement adressContainsCity = adressInfo.get(2);
//        System.out.println(adressContainsCity.getText());

        Assert.assertTrue(adressContainsCity.getText().contains("Beograd"));

        WebElement submitButton = driver.findElement(By.name("processAddress"));
        submitButton.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.name("carrier_area")));
        WebElement checker = driver.findElement(By.className("checker"));
        checker.click();

        WebElement proceedCarrier = driver.findElement(By.name("processCarrier"));
        proceedCarrier.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        WebElement payingByBankWire = driver.findElement(By.className("bankwire"));
        payingByBankWire.click();

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("center_column")));
        WebElement confirmOrder = driver.findElement(By.xpath("/html/body/div/div[2]/div/div[3]/div/form/p/button"));
        confirmOrder.click();

        // Visualisation accpetance
        Thread.sleep(3_000);
    }

    @Test
    public void AutomationOOP() throws InterruptedException
    {
        String email = "bovor33296@rphinfo.com";
        String password = "qwerty1";
        AutomationPage homepage = new AutomationPage(driver);
        homepage.check(email,password);

        double totalSummaryDouble = homepage.totalDouble;
        double articleunitPrice = homepage.articUnitPriceDouble;
        double QtyDouble = homepage.setQtyDouble;

        Assert.assertEquals("nisu isti", totalSummaryDouble,articleunitPrice*QtyDouble, 0.0);
        Assert.assertTrue(homepage.cityAdress.contains("Beograd"));


        // Visualisation accpetance
        Thread.sleep(3000);
    }
}
