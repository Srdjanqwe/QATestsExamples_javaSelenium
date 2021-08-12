package tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.EtsyLoginPage;

import java.time.Duration;


public class EtsyLoginTest extends BaseTest
{
    @Test
        public void EtsyLoginPage() throws InterruptedException {
            String username = "srdjan.rados90@gmail.com";
            String password = "Qwertysha1@";
            String usernick = "Srdjan";

            EtsyLoginPage loginPage = new EtsyLoginPage(driver);
            loginPage.login(username,password);

            // Login validation
            wdWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.className("wt-flex-shrink-xs-0")));
            wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("wt-flex-shrink-xs-0")));
            WebElement userProfile = driver.findElement(By.xpath("/html/body/div[3]/header/div[4]/nav/ul/li[3]/div/button"));
            userProfile.click();

            WebElement user = driver.findElement(By.xpath("/html/body/div[3]/header/div[4]/nav/ul/li[3]/div/div/ul/li[1]/a/span/h4"));
            System.out.println("user:"+user.getText());
            WebElement signOut = driver.findElement(By.xpath("/html/body/div[3]/header/div[4]/nav/ul/li[3]/div/div/ul/li[6]/a/div[2]/p"));
            System.out.println("sign out:"+signOut.getText());

            Assert.assertEquals("Nije isti user",user.getText(),usernick);
            Assert.assertTrue("There is no button", signOut.getText().contains("Sign out"));

            // Visualisation accpetance
            Thread.sleep(3000);
        }

        @Test
        public void EtsyLoginPageWrong() throws InterruptedException
        {
            String username = "srdjan.rados@gmail.com";
            String password = "Qwertysha1@";
            EtsyLoginPage loginPage = new EtsyLoginPage(driver);
            loginPage.login(username,password);

            // Login validation
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            // WebElement usernotfound = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='aria-join_neu_email_field-error']")));
            // System.out.println("error login user name:"+usernotfound.getText());
            wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@id,'aria-join_neu_email_field-error') or contains(@id,'aria-join_neu_password_field-error')")));
            WebElement usernotfound = driver.findElement(By.xpath("//*[contains(@id,'field-error') and not(contains(@class,'--is-hidden'))]"));
            System.out.println("error message:"+usernotfound.getText());

            Assert.assertTrue("Term is not found", usernotfound.getText().contains("Email address is invalid."));

            // Visualisation accpetance
            Thread.sleep(3000);
        }
}