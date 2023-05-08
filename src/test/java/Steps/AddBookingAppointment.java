package Steps;

import Pages.AddAppointmentPage;
import Pages.Dashboard;
import API.Get_Appointment_API;
import base.TestBase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;

import java.sql.SQLException;

public class AddBookingAppointment extends TestBase {
    Dashboard DashboardObject;
    AddAppointmentPage AddAppointmentObject;
    @Given("^the user in the appointment portal and click on the booking appointment$")
    public  void the_user_in_the_appointment_portal_and_click_on_the_booking_appointment()
    {  DashboardObject = new Dashboard(driver);
        DashboardObject.OpenAddappointment();
    }
    @When("^I add all required data$")
    public  void I_add_all_required_data() throws InterruptedException, SQLException, ClassNotFoundException {
        Get_Appointment_API AppointmentAPIObject = new Get_Appointment_API();
        AppointmentAPIObject.GetVehicleClasses();
        AppointmentAPIObject.GetServices();
        String startdate=AppointmentAPIObject.Get_Available_Appointment_Time_Slots();
         AddAppointmentObject = new AddAppointmentPage(driver);
        AddAppointmentObject.FilldataWithoutDelegate(startdate);
    }
    @When("^Adding OTP$")
    public  void Adding_OTP() throws SQLException, ClassNotFoundException {
        AddAppointmentObject.Send_The_OTP();
    }
    @Then("^The appointment will be created$")
    public  void The_appointment_will_be_created ()
    {
        Assert.assertEquals(driver.findElement(By.cssSelector("div.book-success")).getText(),"تم حجز موعدك بنجاح");
    }
}
