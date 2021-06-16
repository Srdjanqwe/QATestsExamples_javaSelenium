package tests;

import org.junit.Assert;
import org.junit.Test;
import pages.AirbnbPage;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class AirbnbDOMTest extends BaseTest
{
    @Test
    public void AirbnbPage() throws InterruptedException {
        String location = "Srebrno jezero, Serbia ";
        Integer setGuests = 2;

        AirbnbPage homepage = new AirbnbPage(driver);
        homepage.bookyourholiday(location,setGuests);

        String arrivalDate = homepage.adpicker;
        String departureDate = homepage.ddpicker;
        long noOfDaysBetween;

        SimpleDateFormat fromUser = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat myFormat = new SimpleDateFormat("d.M.yyyy.");

        try {
            Date adFromUser = fromUser.parse(arrivalDate);
            arrivalDate = myFormat.format(adFromUser);
            System.out.println("formated ad:"+arrivalDate);

            Date ddFromUser = fromUser.parse(departureDate);
            departureDate = myFormat.format(ddFromUser);
            System.out.println("formated dd:"+departureDate);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        //days beetween
        LocalDate dateBefore = LocalDate.parse(homepage.adpicker);
        LocalDate dateAfter = LocalDate.parse(homepage.ddpicker);

        noOfDaysBetween = ChronoUnit.DAYS.between(dateBefore, dateAfter);
//            System.out.println(noOfDaysBetween);
        int daysBetween = Integer.parseInt(String.valueOf(noOfDaysBetween));

        Assert.assertEquals("Nije isti datum",arrivalDate,homepage.resADCheck);
        Assert.assertEquals("Nije isti datum",departureDate,homepage.resDDCheck);
        Assert.assertEquals("Nije isti broj gostiju",homepage.resNGCheck, homepage.setGuestValue);
        Assert.assertEquals("Total nije isti",0,Integer.parseInt(homepage.resCTPrice)-(daysBetween*Integer.parseInt(homepage.pricePerNight)),1);

        // Visualisation accpetance
        Thread.sleep(8000);
    }
}