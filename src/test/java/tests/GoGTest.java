package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.GoGLoginPage;

import java.time.Duration;

public class GoGTest extends BaseTest
{
    @Test
    public void GoGLoginPage() throws InterruptedException
    {
        String username = "testgoglog@gmail.com";
        String password = "asdfgh456";
        String usernick = "Sergio901";
        GoGLoginPage loginPage = new GoGLoginPage(driver);
        loginPage.login(username,password);

        // Login validation
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/nav/div[1]/div[1]/div[7]")));
        WebElement nickUser = driver.findElement(By.className("menu-username"));

        Actions action = new Actions(driver);
        action.moveToElement(nickUser).perform();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/nav/div[1]/div[1]/div[7]/div/div[17]/a")));
        WebElement out = driver.findElement(By.xpath("/html/body/nav/div[1]/div[1]/div[7]/div/div[17]/a"));
        System.out.println("signout:"+element.getText());

        Assert.assertEquals("Nije isti user",nickUser.getText().toUpperCase(),usernick.toUpperCase());
        Assert.assertTrue("There is no button", out.getText().contains("Sign out"));

        // Visualisation accpetance
        Thread.sleep(6000);
    }

    @Test
    public void GoGLoginWithWrongCredentials () throws InterruptedException
    {
        String username = "testgoglog@gmail.com";
        String password = "asdfgh321";
        GoGLoginPage loginPage = new GoGLoginPage(driver);
        loginPage.login(username,password);

        wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[contains(@class,'form__field field field--error ')]")));
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class,'form__field field field--error ')]")));
        
        WebElement usernotfound = driver.findElement(By.xpath("//span[contains(@class,'field__msg') and not(contains(@class,'is-hidden'))]"));
        System.out.println("error mess:"+usernotfound.getText());

        Assert.assertTrue("User not found", usernotfound.getText().contains("USER NOT FOUND") ^ usernotfound.getText().contains("Incorrect password"));

        // Visualisation accpetance
        Thread.sleep(3000);

    }
}
