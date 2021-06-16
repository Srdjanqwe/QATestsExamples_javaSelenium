package pages;

import helpers.BaseHelper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.openqa.selenium.By.*;

public class OrderPage extends BaseHelper
{
    // RandomRestaurant
    @FindBy (className = "address-form-component-search-input") WebElement searchField;
    @FindBy (className= "address-form-component-list") WebElement AdressFolder;
    @FindBy (xpath = "/html/body/div[2]/main/div/section[1]/div[2]/div/div/div/div/div[2]/form/div[2]/div[1]/div[2]/button") WebElement naruciteButton;
    @FindBy (xpath = "/html/body/div[7]/div/div/div/div[1]/div[1]/div/div[2]/form/button") WebElement naruciPotvrdaAdreseButton;
    @FindBy (className = "shops-listings-shops-list") WebElement ListFolder;

    // Restaurant menu list *all meals
    @FindBy (id = "menu-list-content") WebElement menuList;

    // Info delivery from modal
    @FindBy (className = "cc-modal-header__name") WebElement mealName;
//    @FindBy (className = "cc-modal-header__total") WebElement mealPrice;
//    // Unit price
//    @FindBy (className = "cc-tiers-tier-choices-radio-price") WebElement mealUnitPrice;
    @FindBy (className = "cc-controls-quantity-input") WebElement mealQty;
    @FindBy (className = "cart-total-price") WebElement racun;

    @FindBy (className = "cc-controls-quantity-increase") WebElement increaseQty;
//    @FindBy (xpath = "/html/body/div[2]/main/section[3]/div/div[2]/div/div[1]/div[3]/div/div[2]/div/div[2]/button") WebElement addToCart;
    @FindBy (className = "cc-modal-footer-controls-submit__button") WebElement addToCart;

    @FindBy (xpath ="/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]") WebElement minDelivery;
    @FindBy (xpath ="/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]/div[2]/div[3]") WebElement deliveryCost;

//    // using old variable method
//    public String orderedDish;
//    public String qtyCounter;
    public String totalPrice;
    public String deliveryMinInfo;
    public String carryDeliveryCost;

    public Double totalCartPrice;
    public Double minDeliveryCost;
    public Double carryCost;

    private List<WebElement> meals;

    public List<Meal> addedMeals;

    WebDriver driver;
    public OrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
//    // Get random restaurant
//    private void navigateToRadnomRestaurant()
//    {
//        driver.get("https://www.donesi.com");
//    }
//    private void enterAdressFormInSearchField(String adressForm)
//    {
////        searchField.click();
//        searchField.sendKeys(adressForm);
//    }
//    private void adressResults()
//    {
//        wdWait.until(ExpectedConditions.visibilityOf(AdressFolder));
//        List<WebElement> address = AdressFolder.findElements(By.tagName("li"));
//        address.get(0).click();
//    }
//    private void clickOnNaruciteButton() { naruciteButton.click(); }
//    private void potvrdaAdereseButton()
//    {
//        wdWait.until(ExpectedConditions.elementToBeClickable(naruciPotvrdaAdreseButton));
//        js.executeScript("arguments[0].click()",naruciPotvrdaAdreseButton);
//    }
//    private void clickOnRandomRestaurant()
//    {
//        wdWait.until(ExpectedConditions.visibilityOf(ListFolder));
////        List<WebElement> items = ListFolder.findElements(By.tagName("a"));
//        List<WebElement> items = ListFolder.findElements(By.className("shops-listings-shops-list-item"));
//        int newMaxLength = items.size();
////        System.out.println(newMaxLength);
//        Random nrnd = new Random();
//        int nrndBroj = nrnd.nextInt(newMaxLength);
//
//        WebElement rndRestaurant = items.get(nrndBroj);
////        js.executeScript("arguments[0].scrollIntoView();",items.get(nrndBroj));
//        js.executeScript("arguments[0].scrollIntoView();",rndRestaurant);
////        wdWait.until(ExpectedConditions.elementToBeClickable(items.get(nrndBroj)));
//        wdWait.until(ExpectedConditions.visibilityOf(rndRestaurant));
//
//        // click on random res
////        js.executeScript("arguments[0].click();",items.get(nrndBroj));
////        js.executeScript("arguments[0].click();",rndRestaurant);
//        rndRestaurant.click();
//    }
    private void navigateToOrderPage()
    {
        driver.get("https://www.donesi.com/shops?address=Kneza%20Mihaila%207%2C%2011000%20Beograd%2C%20Stari%20grad&city=Stari%20grad&county=Beograd&latitude=44.8158669&longitude=20.4590983&nomap=0&street=Kneza%20Mihaila&street_no=7&zip=11000&area=Stari%20grad&city_transliterated=&slug=%2F&deliverytype=0&scope=personal&t=1620983466859&lo=%2F");
//        driver.get("https://www.donesi.com/dostava/beograd/savski-venac/haos-food");
//        driver.get("https://www.donesi.com/dostava/beograd/bigpizza-centar");
        driver.get("https://www.donesi.com/dostava/beograd/savski-venac/haos-food");
//        driver.get("https://www.donesi.com/dostava/beograd/ushtshe/grande-wurst");
//        driver.get("https://www.donesi.com/dostava/beograd/zvezdara-uzhi-gradski-deo/mesara-kod-rodjaka");
    }

    private void getDeliveryCost()
    {
        wdWait.until(ExpectedConditions.visibilityOf(minDelivery));
        deliveryMinInfo = driver.findElement(By.xpath("/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]/div[2]/div[1]/span")).getText();
//        System.out.println(deliveryCost);
        wdWait.until(ExpectedConditions.visibilityOf(deliveryCost));
        carryDeliveryCost = driver.findElement(By.xpath("/html/body/div[2]/main/section[2]/div/div/div/div/div/div[1]/div[3]/div[2]/div[3]/span")).getText();
//        System.out.println(carryDeliveryCost);
        minDeliveryCost = Double.parseDouble(deliveryMinInfo.replace("RSD","").replace(".",",").replace(",","."));
        carryCost = Double.parseDouble(carryDeliveryCost.replace("RSD","").replace(".",",").replace(",","."));
        System.out.println("Minimalno narucivanje za dostavu:"+" "+minDeliveryCost+" "+"rsd");
        System.out.println("Cena dostave:"+" "+carryCost+" "+"rsd");
        System.out.println("--------------");

        addedMeals = new ArrayList<>();
    }

    // After refatoring
    private void orderDish(int i)
    {
        // Delivery info
        Meal addedMeal = new Meal();

        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.id("menu-list-content")));

        // dishes.size or declare list meals and use meals.size
//        List<WebElement> dishes = menuList.findElements(tagName("li"));
        meals = menuList.findElements(tagName("li"));
        int maxLength = meals.size();
//        System.out.println(maxLength);
        Random rnd = new Random();
        int rndBroj = rnd.nextInt(maxLength);

        WebElement rndDishes = meals.get(rndBroj);
//        js.executeScript("arguments[0].scrollIntoView();",meals.get(rndBroj));
//        wdWait.until(ExpectedConditions.visibilityOf(meals.get(rndBroj)));

        js.executeScript("arguments[0].scrollIntoView();",rndDishes);
        wdWait.until(ExpectedConditions.visibilityOf(rndDishes));

        // click on random meal
//        js.executeScript("arguments[0].click();",meals.get(rndBroj));
        js.executeScript("arguments[0].click();",rndDishes);

        // wait for modal IMPORTANT *not same for every dish
        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cc-modal")));

        WebElement mealPrice = driver.findElement(By.className("cc-modal-header__total"));
        addedMeal.price = mealPrice.getText();
//        addedMeal.price.replace("RSD","").replace(".",",").replace(",",".");
//        System.out.println("Jedinicna cena:"+" "+addedMeal.price+" "+"rsd");
        System.out.println("Jedinicna cena:"+" "+addedMeal.price);


        // Increase qty instead of writting function *condition for qty
        if (i==2)
        {
            increaseQty.click();
            wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cc-controls-quantity-input"),"1"));
            wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cc-modal-header__total"), addedMeal.price));
        }

//        // via variables
//        orderedDish = rndDishes.findElement(className("shop-profile-menu-list-item-name")).getText();
//        System.out.println("jelo:"+" "+orderedDish);
//        totalPrice = String.valueOf(rndDishes.findElement(tagName("span")).getText().replaceFirst("Od","").replace(" ",""));
//        System.out.println("cena:"+" "+totalPrice);
//        System.out.println("----------");

        // via Class meal
        addedMeal.name = mealName.getText();
        System.out.println("Naziv jela:"+" "+addedMeal.name);
        addedMeal.price = mealPrice.getText();
        double addedMealPrice = Double.parseDouble(addedMeal.price.replace("RSD","").replace(".",",").replace(",","."));
        System.out.println("Ukupna cena:"+" "+addedMealPrice+" "+"rsd");
//        System.out.println("Ukupna cena:" +addedMeal.price+" "+"RSD");
//        System.out.println("Ukupna cena:" +addedMeal.price);
        addedMeal.qty = mealQty.getText();
        System.out.println("Narucena kolicina:"+" "+addedMeal.qty);

//        System.out.println("----------");

        // Added object Meal to  list addedMeals
        addedMeals.add(addedMeal);

        // add Cart to choose meal function
        wdWait.until(ExpectedConditions.elementToBeClickable(addToCart));
        addToCart.click();
//        js.executeScript("arguments[0].click();",addToCart);

        WebElement racun = driver.findElement(By.className("cart-total-price"));
        totalPrice = racun.getText();
        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cart-total-price"),totalPrice));
        totalPrice = racun.getText();
//        System.out.println("totalprice:"+totalPrice);
//        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cart-total-price"),);
        totalCartPrice = Double.parseDouble(totalPrice.replace("RSD","").replace(".",",").replace(",","."));
//        System.out.println("cartPrice"+totalCartPrice);
        System.out.println("Racun:"+" "+totalCartPrice+" "+"rsd");
        System.out.println("-------------------------------");
    }

//    private void increaseQty()
//    {
//        wdWait.until(ExpectedConditions.elementToBeClickable(increaseQty));
//        increaseQty.click();
//        qtyCounter = driver.findElement(className("cc-controls-quantity-input")).getText();
//        System.out.println(qtyCounter);
//    }

//    private void addToCart()
//    {
//        wdWait.until(ExpectedConditions.elementToBeClickable(addToCart));
//        addToCart.click();
////        js.executeScript("arguments[0].click();",addToCart);
//    }

//    private void subTotalCheck()
//    {
//        wdWait.until(ExpectedConditions.presenceOfElementLocated(By.className("cart-total-price")));
//        totalCartPrice = driver.findElement(By.className("cart-total-price")).getText();
//        wdWait.until(ExpectedConditions.invisibilityOfElementWithText(By.className("cart-total-price"),totalCartPrice));
//        totalCartPrice = driver.findElement(By.className("cart-total-price")).getText();
//        System.out.println(totalCartPrice);
//        System.out.println("-----------");
//    }

    private void chooseMeal()
    {
        //  Loop through methods 2x
        for (int i=1; i<=2; i++)
        {
            orderDish(i);
//            increaseQty();
//            addToCart();
        }

//        subTotalCheck();
    }

    public void order()
    {
        navigateToOrderPage();

//        navigateToRadnomRestaurant();
//        enterAdressFormInSearchField(adressForm);
//        adressResults();
//        clickOnNaruciteButton();
//        potvrdaAdereseButton();
//        clickOnRandomRestaurant();

        getDeliveryCost();
        chooseMeal();
    }
}
