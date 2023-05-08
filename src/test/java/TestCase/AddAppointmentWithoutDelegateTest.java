package TestCase;

import Pages.AddAppointmentPage;
import Pages.Dashboard;
import API.Get_Appointment_API;
import base.TestBase;
import io.qameta.allure.Description;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;

@Feature("Add_An_Appointment")
public class AddAppointmentWithoutDelegateTest extends TestBase {
    @Story("Add a suadi plated vehicle")
    @Description("User will make an appointment for a saudi vehicle in the support vtc")
    @Test(description = "Making add An appointment for the current day")
    public void AddAppointmentWithoutDelegate() throws  AWTException, SQLException, ClassNotFoundException {
        Dashboard DashboardObject = new Dashboard(driver);
        Get_Appointment_API AppointmentAPIObject = new Get_Appointment_API();
        AppointmentAPIObject.GetVehicleClasses();
        AppointmentAPIObject.GetServices();
        String startdate=AppointmentAPIObject.Get_Available_Appointment_Time_Slots();
        DashboardObject.OpenAddappointment();
        AddAppointmentPage AddAppointmentObject = new AddAppointmentPage(driver);
        AddAppointmentObject.FilldataWithoutDelegate(startdate);
        AddAppointmentObject.Send_The_OTP();
        Assert.assertEquals(driver.findElement(By.cssSelector("div.book-success")).getText(),"تم حجز موعدك بنجاح");
    }
}