package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.HeroPage;

public class HerokuapLoginTest extends BaseTest
{
    @Test
    public void LoginTestWithValidCredentials() throws InterruptedException {

        // Navigate to Herokuapp login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Input suggested "terms" on page in fields username and passworde
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("tomsmith");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("SuperSecretPassword!");

        // Click LoginButton
        // WebElement loginButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        WebElement loginButton = driver.findElement(By.className("radius"));
        loginButton.click();

        // Login Validation
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flash")));
        WebElement searchResults = driver.findElement(By.id("flash"));
        WebElement logoutButton = driver.findElement(By.className("icon-signout"));
        Assert.assertTrue("Term is not found", searchResults.getText().contains("You logged into a secure area!"));
        Assert.assertTrue("There is no button", logoutButton.getText().contains("Logout"));


        // Visualisation accpetance
        Thread.sleep(3_000);
    }

    @Test
    public void LoginTestWithWrongCredentials() throws InterruptedException
    {
        // Navigate to Herokuapp login page
        driver.get("https://the-internet.herokuapp.com/login");

        // Input any than suggested "terms" on page in fields username and passworde
        WebElement usernameInputField = driver.findElement(By.id("username"));
        usernameInputField.sendKeys("tomsaasdsmith");
        WebElement passwordInputField = driver.findElement(By.id("password"));
        passwordInputField.sendKeys("SuperSecretPassword!");

        // Click LoginButton
        WebElement loginButton = driver.findElement(By.xpath("/html/body/div[2]/div/div/form/button"));
        loginButton.click();

        // Validation with wrong credentials
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flash")));
        WebElement searchResults = driver.findElement(By.id("flash"));
        Assert.assertTrue("Term is not found", searchResults.getText().contains("Your username is invalid!"));

        // Visualisation accpetance
        Thread.sleep(3_000);
    }

    @Test
    public void HerokuapOop() throws InterruptedException {

        String username = "tomsmith";
        String password = "SuperSecretPassword!";
        HeroPage homepage = new HeroPage(driver);
        homepage.login(username, password);

        // Login Validation
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flash")));
        WebElement searchResults = driver.findElement(By.id("flash"));
        WebElement logoutButton = driver.findElement(By.className("icon-signout"));
        Assert.assertTrue("Term is not found", searchResults.getText().contains("You logged into a secure area!"));
        Assert.assertTrue("There is no button", logoutButton.getText().contains("Logout"));

        // Visualisation accpetance
        Thread.sleep(3_000);
    }

    @Test
    public void HerokuapOopWrongCredentials() throws InterruptedException {

        String username = "tommith";
        String password = "SuperSecretPassword!";
        HeroPage homepage = new HeroPage(driver);
        homepage.login(username, password);

        // Validation with wrong credentials
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.id("flash")));
        WebElement searchResults = driver.findElement(By.id("flash"));
        Assert.assertTrue("Term is not found", searchResults.getText().contains("Your username is invalid!"));

        // Visualisation accpetance
        Thread.sleep(3_000);
    }

}
