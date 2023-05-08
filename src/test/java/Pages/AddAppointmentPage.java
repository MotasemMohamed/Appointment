package Pages;

import Utilities.DBconnection;
import base.PageBase;
import com.github.javafaker.Faker;
import io.qameta.allure.Step;
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
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

public class AddAppointmentPage extends PageBase {

    public AddAppointmentPage(WebDriver Loginddriver)
    {
        super(Loginddriver);
    }
    WebDriverWait wait = new WebDriverWait(driver, WAIT);
   DateFormat dateFormat = new SimpleDateFormat("dd");
    Date date = new Date();
    Calendar cal = Calendar.getInstance();
    String date1= dateFormat.format(date);
    Faker fakerdata= new Faker();
    String PlateDetailsFaker = fakerdata.number().digits(4);
    DBconnection db = new DBconnection();
    @Step
    public void FilldataWithDelegate() {
        WebElement Name=driver.findElement(By.id("edit-customer-name"));
        SendData(Name,"holamagolus");
        WebElement PhoneNumber= driver.findElement(By.id("edit-customer-mobile-no"));
        SendData(PhoneNumber,"551234567");
        WebElement Email= driver.findElement(By.id("edit-customer-email"));
        SendData(Email,"hamada@gmail.com");
//Delegate Section
        WebElement Checkbox= driver.findElement(By.cssSelector ("label[class='control-label option']"));
        Checkbox.click();
        scrollDownPage(driver.findElement(By.xpath("//div/label/input[@id='edit-delegate-type-resident']")));
        waitForVisibility(driver.findElement(By.cssSelector("span[class='fieldset-legend']")));
        WebElement DelegateName=driver.findElement(By.name("delegate_name"));
        SendData(DelegateName,"ابو نواف");
        WebElement DelegateNumber= driver.findElement(By.id("edit-delegate-mobile-no"));
        SendData(DelegateNumber,"551234567");
        WebElement ResidentNumber= driver.findElement(By.id("edit-delegate-residency"));
        SendData(ResidentNumber,"22222222222");
        WebElement DelegateBirthdate= driver.findElement(By.cssSelector("input[class='form-date form-control form-control input']"));
        clickButton(DelegateBirthdate);
        clickButton(driver.findElement(By.xpath("(//span [@aria-label='فبراير "+date1+", 2008'])[1]")));
        scrollDownPage(driver.findElement(By.id ("edit-delegate-terms")));
        WebElement Checkbox2= driver.findElement(By.id ("edit-delegate-terms"));
        Checkbox2.click();
        //plate Vehicle section
        WebElement optionList1= driver.findElement(By.id("edit-plate-1"));
        Select selectOption1 = new Select(optionList1);
        selectOption1.selectByVisibleText("أ - A");
        WebElement optionList2= driver.findElement(By.id("edit-plate-2"));
        Select selectOption2 = new Select(optionList2);
        selectOption2.selectByVisibleText("أ - A");
        WebElement optionList3= driver.findElement(By.id("edit-plate-3"));
        Select selectOption3 = new Select(optionList3);
        selectOption3.selectByVisibleText("أ - A");
        SendData(driver.findElement(By.id("edit-plate-4")),PlateDetailsFaker);
        clickButton(driver.findElement(By.id("select2-edit-service-id-container")));
        clickButton(driver.findElement(By.xpath("(//li[text()='خدمة الفحص الدوري'])[1]")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("(//span [text()='اختر مركز الفحص'])[1]"))));
        scrollDownPage(driver.findElement(By.xpath("(//input[@class='form-date form-control form-control input'])[2]")));
        clickButton(driver.findElement(By.xpath("(//input[@class='form-date form-control form-control input'])[2]")));
        clickButton(driver.findElement(By.xpath("//span [@aria-label='يناير "+date1+", 2023']")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//select/option[text()='موعد الخدمة']"))));
        scrollDownPage(driver.findElement(By.id("edit-submit")));

        new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));
        new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();
        WebElement NextButton= driver.findElement(By.id("edit-submit"));
        clickButton(NextButton);
    }
    @Step
     public void FilldataWithoutDelegate(String startdate)  {
         String Day=startdate.substring(0,2);
         String Day2=Day;
         Day2 = Day.replaceFirst("^0+(?!$)", ""); // remove left zero
         String Month=startdate.substring(3,5);
         String Year=startdate.substring(6,10);
         String[] months = new String[] {"يناير", "فبراير", "مارس", "أبريل", "مايو", "يونيو", "يوليو", "اغسطس","سبتمبر", "اكتوبر", "نوفمبر", "ديسمبر" };
         int monthInt= Integer.parseInt(Month);
         String Arabic_month= months [monthInt-1];
        WebElement Name=driver.findElement(By.id("edit-customer-name"));
        SendData(Name,"holamagolus");
        WebElement PhoneNumber= driver.findElement(By.id("edit-customer-mobile-no"));
        SendData(PhoneNumber,"551234567");
        WebElement Email= driver.findElement(By.id("edit-customer-email"));
        SendData(Email,"hamada@gmail.com");

        //plate Vehicle section
        WebElement optionList1= driver.findElement(By.id("edit-plate-1"));
        Select selectOption1 = new Select(optionList1);
        selectOption1.selectByVisibleText("أ - A");
        WebElement optionList2= driver.findElement(By.id("edit-plate-2"));
        Select selectOption2 = new Select(optionList2);
        selectOption2.selectByVisibleText("أ - A");
        WebElement optionList3= driver.findElement(By.id("edit-plate-3"));
        Select selectOption3 = new Select(optionList3);
        selectOption3.selectByVisibleText("أ - A");
        SendData(driver.findElement(By.id("edit-plate-4")),PlateDetailsFaker);
        clickButton(driver.findElement(By.id("select2-edit-service-id-container")));
        clickButton(driver.findElement(By.xpath("//li[text()='خدمه الفحص الدوري']")));
        //wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//span [text()='اختر مركز الفحص']"))));
        scrollDownPage(driver.findElement(By.xpath("//span[text()='اختر مركز الفحص']")));
        clickButton(driver.findElement(By.xpath("//span[text()='اختر مركز الفحص']")));
         clickButton(driver.findElement(By.xpath("(//ul[@id='select2-edit-center-id-results']/li)[2]")));
         scrollDownPage(driver.findElement(By.xpath("(//input[@class='form-date form-control form-control input'])[3]")));
         clickButton(driver.findElement(By.xpath("(//input[@class='form-date form-control form-control input'])[3]")));
         clickButton(driver.findElement(By.xpath( "//span[@aria-label='"+Arabic_month+" "+Day2+", "+Year+"']")));
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.xpath("//select/option[text()='موعد الخدمة']"))));
        //new WebDriverWait(driver, 10).until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//iframe[starts-with(@name, 'a-') and starts-with(@src, 'https://www.google.com/recaptcha')]")));   //This line is used to select captch
        //new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.recaptcha-checkbox-border"))).click();        //This line is used to select captch
        clickButton(driver.findElement(By.id("select2-edit-timeslot-container")));

        WebElement Slottime= driver.findElement(By.xpath("(//*[@id='select2-edit-timeslot-results']/li)[2]"));
        wait.until(ExpectedConditions.visibilityOf(Slottime));
        clickButton(Slottime);
        WebElement NextButton= driver.findElement(By.id("edit-submit"));
        scrollDownPage(driver.findElement(By.id("edit-submit")));
        clickButton(NextButton);
    }
    @Step
    public void Send_The_OTP() throws SQLException, ClassNotFoundException {

        WebElement OTPCheck= driver.findElement(By.id("edit-otp"));
        wait.until(ExpectedConditions.visibilityOf(OTPCheck));
        clickButton(OTPCheck);
        SendData(OTPCheck,"000000");
        clickButton(driver.findElement(By.xpath("//button[@Class='button js-form-submit form-submit btn-primary btn']")));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div.book-success"))));
        String Transactionnumber=driver.findElement(By.xpath("//div[@class='reference-details']/span[@class='h1']")).getText();
        Map<String,Object> map = db.AssertThetransactionexistinDB(Transactionnumber);
        Assert.assertEquals("exist",map.get("AppointmentExistance"));
    }
}