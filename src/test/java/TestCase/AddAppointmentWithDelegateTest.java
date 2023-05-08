package TestCase;

import Pages.*;
import base.TestBase;
import org.testng.annotations.Test;

import java.awt.*;

public class AddAppointmentWithDelegateTest extends TestBase {

    AddAppointmentPage LoginObject;
    Dashboard InspectorQueueObject;


    public void Dispatsh() throws InterruptedException, AWTException {
        Dashboard DashboardObject = new Dashboard(driver);
        DashboardObject.OpenAddappointment();
        AddAppointmentPage AddAppointmentObject = new AddAppointmentPage(driver);
        AddAppointmentObject.FilldataWithDelegate();
        //Assert.assertEquals(driver.findElement(By.className("inspector-name")).getText(),"مهندس الدعم");
        }
}