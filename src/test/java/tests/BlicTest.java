package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BlicPage;

import java.sql.SQLOutput;
import java.util.List;

public class BlicTest extends BaseTest
{
    @Test
    public void blicSearchTest() throws InterruptedException {

        String searchTerm = "Comtrade";

        // Navigate to blic home page
        driver.get("https://www.blic.rs");

        // CLick on magnifier glass icon
        WebElement magnifierGlassIcon = driver.findElement(By.className("fa-search"));
        magnifierGlassIcon.click();

        // Type Comtrade in search field
        WebElement searchField = driver.findElement(By.id("search-field-head"));
        searchField.sendKeys(searchTerm);

        // CLick on TraziButton
        // WebElement searchButton = driver.findElement(By.className("fa-angle-double-right"));
        WebElement searchButton = driver.findElement(By.xpath("/html/body/header/div[1]/div/div[3]/form/button"));
        searchButton.click();

        // Check if there are 24 search results

        //zbog promene url kad god promeni url
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("search__results")));
        WebElement searchResults = driver.findElement(By.className("search__results"));
        List<WebElement> articles = searchResults.findElements(By.tagName("article"));

        // prolazak kroz listu
//        int counter = 0;
//        for (WebElement article:articles)
//        {
////            counter ++;
////            System.out.println(counter);
////            System.out.println("----------------");
//            System.out.println(article.getText());
//        }

        // Get exact element from list
        WebElement secondArticle = articles.get(1);
        secondArticle.findElement(By.tagName("a")).click();

        // Test validation
        Assert.assertEquals("There is no 24 results", 24 , articles.size());
        System.out.println("Broj artikala:"+" "+articles.size());

        // Visualisation accpetance
        Thread.sleep(3000);
    }

    @Test
    public void blicOOP() throws InterruptedException
    {
        String term = "Comtrade";
        BlicPage homepage = new BlicPage(driver);
        homepage.blicSearch(term);

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("search__results")));
        WebElement searchResults = driver.findElement(By.className("search__results"));
        List<WebElement> articles = searchResults.findElements(By.tagName("article"));

        // Get exact element from list
        WebElement secondArticle = articles.get(1);
        secondArticle.findElement(By.tagName("a")).click();

        Assert.assertEquals("Nema 24 rezultata", 24, articles.size());
        System.out.println("Broj artikala:"+" "+articles.size());

        // Visualisation accpetance
        Thread.sleep(3000);
    }

}
