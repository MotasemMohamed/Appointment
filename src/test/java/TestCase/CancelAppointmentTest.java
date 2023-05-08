package TestCase;

import Pages.AddAppointmentPage;
import Pages.CanselAppointmentPage;
import Pages.Dashboard;
import base.TestBase;
import io.qameta.allure.Feature;
import org.testng.annotations.Test;

import java.awt.*;
import java.sql.SQLException;

@Feature("Cancel_An_Appointment")
public class CancelAppointmentTest extends TestBase {

    AddAppointmentPage LoginObject;
    Dashboard InspectorQueueObject;
    @Test(description = "Making cancel An appointment")
    public void Dispatsh() throws InterruptedException, AWTException, SQLException, ClassNotFoundException {
        Dashboard DashboardObject = new Dashboard(driver);
        DashboardObject.OpenCancelappointment();
        CanselAppointmentPage CanselAppointmentObject = new CanselAppointmentPage(driver);
        CanselAppointmentObject.Filldata();
        //Assert.assertEquals(driver.findElement(By.className("inspector-name")).getText(),"مهندس الدعم");
        }
}