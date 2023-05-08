package Pages;

import Utilities.DBconnection;
import base.PageBase;
import com.github.javafaker.Faker;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

public class EditAppointmentPage extends PageBase {

    public EditAppointmentPage(WebDriver Loginddriver) throws SQLException, ClassNotFoundException {
        super(Loginddriver);
    }
    WebDriverWait wait = new WebDriverWait(driver, WAIT);
    DateFormat dateFormat = new SimpleDateFormat("dd");
    Date date = new Date();
    String date1= dateFormat.format(date);
    Faker fakerdata= new Faker();
    String PlateDetailsFaker = fakerdata.number().digits(4);
    DBconnection db= new DBconnection();
    Map<String,Object> map = db.getReferenceNumberForAppointment();

    public void Filldata() {
        WebElement Appointment_Reference=driver.findElement(By.id("edit-book-id"));
       SendData(Appointment_Reference, (String) map.get("AppointmentNumber"));
        String AppointmentNumber =map.get("PhoneNumber").toString();
        String AppointmentNumberwithoutcountrycode = (AppointmentNumber).substring(4);
        WebElement PhoneNumber= driver.findElement(By.id("edit-customer-mobile-no"));
        SendData(PhoneNumber,AppointmentNumberwithoutcountrycode);
        //new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));  //This line is used to select captch
        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();    //This line is used to select captch
        WebElement NextButton= driver.findElement(By.id("edit-submit"));
        clickButton(NextButton);
        //This section for OTP
        WebElement OTPCheck= driver.findElement(By.id("edit-otp"));
        wait.until(ExpectedConditions.visibilityOf(OTPCheck));
        clickButton(OTPCheck);
        SendData(OTPCheck,"000000");
        clickButton(driver.findElement(By.xpath("//button[@Class='button js-form-submit form-submit btn-primary btn']")));
    }

    public void EditAppointmentDetails() {
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.linkText("تعديل موعد"))));
        WebElement DateService= driver.findElement(By.id("edit-timeslot"));
        scrollDownPage(DateService);
        clickButton(DateService);
        Select SelectOptions= new Select(DateService);
        SelectOptions.selectByIndex(2);
        WebElement NextButton= driver.findElement(By.id("edit-submit"));
        clickButton(NextButton);
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.book-success"))));
        Assert.assertEquals(driver.findElement(By.cssSelector("div.book-success")).getText(),"تم تعديل موعدك بنجاح");
    }
}
