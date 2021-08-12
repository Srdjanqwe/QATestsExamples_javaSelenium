package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.BookingPage;

public class BookingTest extends BaseTest
{
    @Test
    public void BookingPage() throws InterruptedException
    {
        String location = "Paris";
        Integer setChildernAge = 2;
        String hotelName ="Novotel Paris Les Halles";

        BookingPage homepage = new BookingPage(driver);
        homepage.bookholiday(location,setChildernAge);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@id='hotellist_inner']")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='hotellist_inner']")));

        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div/div/div/div/h3/a/span[contains(@data-et-click,'   ') and contains(.,'Novotel Paris Les Halles')]")));
        WebElement novotelHotel = driver.findElement(By.xpath("//div/div/div/div/h3/a/span[contains(@data-et-click,'   ') and contains(.,'Novotel Paris Les Halles')]"));
        js.executeScript("arguments[0].scrollIntoView();",novotelHotel);
        js.executeScript("arguments[0].click();",novotelHotel);
        System.out.println("hotel you choose:"+novotelHotel.getText());

        Assert.assertTrue("You didn't choose Novotel Paris Les Halles", novotelHotel.getText().trim().equals(hotelName));

        // Visualisation accpetance
        Thread.sleep(3000);
    }

}
