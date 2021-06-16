package tests;

import org.checkerframework.checker.units.qual.A;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import pages.DonesiPage;
import pages.Meal;
import pages.OrderPage;

import java.sql.SQLOutput;
import java.util.Arrays;
import java.util.List;
import java.util.stream.DoubleStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonesiTest extends BaseTest
{
    @Test
    public void DonesiOOP() throws InterruptedException
    {
        String adressForm = "Knez Mihailova 7, Beograd";
        DonesiPage homepage = new DonesiPage(driver);
        homepage.search(adressForm);

        int allResNum = Integer.parseInt(homepage.allResNum);
        int itaResNum = Integer.parseInt(homepage.itaResNum);
        int itaResNumCheck = homepage.itaResNumCheck;

        Assert.assertTrue("Nema vise restorana",allResNum>itaResNum);
        Assert.assertEquals("There is no 127 results", 124 , itaResNumCheck);

        // Visualisation accpetance
        Thread.sleep(3_000);
    }

    @Test
    public void OrderPageOOP() throws InterruptedException {

//        String adressForm = "Knez Mihailova 7, Beograd";
        OrderPage orderpage = new OrderPage(driver);
        orderpage.order();

        double racunTotal = orderpage.totalCartPrice;
        double minDeliveryPrice = orderpage.minDeliveryCost;
        double carryPrice = Double.parseDouble(String.valueOf(orderpage.carryCost));

//        System.out.println("Lista dodatih jela:"+""+ orderpage.addedMeals.size());
//        for(Meal meal: orderpage.addedMeals)
//        {
//            System.out.println(meal.name);
//        }
        wdWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.className("cart-product-list")));
        List<WebElement> cartItems = driver.findElements(By.className("cart-product-list-item"));

//        Loop through cartItem list
        double sumCartPrice = 0.0;
        for (int i = 0; i<orderpage.addedMeals.size(); i++)
        {
            WebElement cartItem = cartItems.get(i);
            String cartItemQty = cartItem.findElement(By.className("cart-product-list-item-quantity")).getText();
            String cartItemName = cartItem.findElement(By.className("cart-product-list-item-name")).getText();
            Double cartItemPrice = Double.valueOf(cartItem.findElement(By.className("cart-product-list-item-price")).getText().replace("RSD","").replace(".",",").replace(",","."));
            System.out.println("Cart list:"+" "+cartItemName);
            String addedProductName = orderpage.addedMeals.get(i).name;
            System.out.println("Added to cart:"+" "+addedProductName);
            System.out.println("------");
            System.out.println("Kolicine iz korpe:"+" "+cartItemQty);
            String addedProductQty = orderpage.addedMeals.get(i).qty;
            System.out.println("Narucene kolicine:"+ " "+addedProductQty);
            System.out.println("------");
            System.out.println("Cena jela:"+ " "+cartItemPrice);
            Double addedProductPrice = Double.parseDouble(orderpage.addedMeals.get(i).price.replace("RSD","").replace(".",",").replace(",","."));
            System.out.println("Racun narucene kolicine:"+ " "+addedProductPrice);
            System.out.println("--------");

            sumCartPrice += Double.parseDouble(orderpage.addedMeals.get(i).price.replace("RSD","").replace(".",",").replace(",","."));
//            System.out.println("Ukupno:"+" "+sumCartPrice);

            assertEquals("Imena narudzbina nisu ista", orderpage.addedMeals.get(i).name, cartItemName);
            assertEquals("Kolicine nisu iste",orderpage.addedMeals.get(i).qty, cartItemQty);
//            assertEquals("Cene nisu iste", orderpage.addedMeals.get(i).price, cartItemPrice);
            assertEquals("Cene nisu iste", addedProductPrice, cartItemPrice);
        }

        sumCartPrice = DoubleStream.of(sumCartPrice).sum()+(carryPrice);
        System.out.println("sumCartPrice:"+sumCartPrice);
        System.out.println("racunTotal:"+racunTotal);
        assertEquals("Racun nije isti", 0, sumCartPrice - racunTotal, 0.0);


//        double subtotal = Double.parseDouble(orderpage.totalCartPrice.replace("RSD","").replace(".",",").replace(",","."));
//        double minDeliveryCost = Double.parseDouble(orderpage.deliveryMinInfo.replace("RSD","").replace(".",",").replace(",","."));
//        double carryCost = Double.parseDouble(orderpage.carryDeliveryCost.replace("RSD","").replace(".",",").replace(",","."));

//        System.out.println("Racun:"+ " "+subtotal);
//        System.out.println("Minimalna porudzbina:"+ " "+minDeliveryPrice);
//        System.out.println("Naplata dostave:"+ " "+carryPrice);

//        Assert.assertTrue("Nije veci racun od minimalne dostave fali jos"+ " "+(minDeliveryCost-subtotal),subtotal>minDeliveryCost);
//        Assert.assertEquals();

        // Visualisation accpetance
        Thread.sleep(3_000);
    }


}