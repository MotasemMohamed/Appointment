package Pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Dashboard extends PageBase {
    public Dashboard(WebDriver Loginddriver)
    {
        super(Loginddriver);
    }
    public void OpenAddappointment() {
        WebElement AddAppointment = driver.findElement(By.id("main-menu-link-content0ab33c7e-4fbb-41c1-a220-1ea519e11de2"));
        clickButton(AddAppointment);
        }
    public void OpenEditappointment() {
        WebElement EditAppointment = driver.findElement(By.id("main-menu-link-content5d57090d-7dc2-4c6d-a906-082f5fc8b69d"));
        clickButton(EditAppointment);
    }
    public void OpenCancelappointment() {
        WebElement CancelAppointment = driver.findElement(By.id("main-menu-link-content911cf031-449e-44ec-a609-3f4c4640d4ad"));
        clickButton(CancelAppointment);
    }
}
