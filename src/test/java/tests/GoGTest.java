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
        String username = "srdjan.rados90@gmail.com";
        String password = "Qwertysha1@";
        String usernick = "SRLE90";
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

        Assert.assertEquals("Nije isti user",nickUser.getText(),usernick);
        Assert.assertTrue("There is no button", out.getText().contains("Sign out"));

        // Visualisation accpetance
        Thread.sleep(6000);
    }

    @Test
    public void GoGLoginOfUnregisteredUser () throws InterruptedException
    {
        String username = "srdjan.rados@com";
        String password = "asdfgh";
        GoGLoginPage loginPage = new GoGLoginPage(driver);
        loginPage.login(username,password);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        WebElement usernotfound = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[2]/div/div/form/ol[1]/li[1]/span[1]")));
        System.out.println("error login user name:"+usernotfound.getText());

        Assert.assertTrue("User not found", usernotfound.getText().contains("USER NOT FOUND"));

        // Visualisation accpetance
        Thread.sleep(3000);

    }
}
