package TestCase;

import Pages.Dashboard;
import Pages.EditAppointmentPage;
import base.TestBase;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;
@Feature("Update_An_Appointment")
public class EditAppointmentTest extends TestBase {


    @Test(description = "Making edit appointment")
    public void Dispatsh() throws InterruptedException, AWTException, SQLException, ClassNotFoundException {
        Dashboard DashboardObject = new Dashboard(driver);
        DashboardObject.OpenEditappointment();
        EditAppointmentPage EditAppointmentObject = new EditAppointmentPage(driver);
        EditAppointmentObject.Filldata();
        EditAppointmentObject.EditAppointmentDetails();
        //Assert.assertEquals(driver.findElement(By.className("inspector-name")).getText(),"مهندس الدعم");
        }
}