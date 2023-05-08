package Pages;


import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class TransactionDetailsPage extends PageBase {

    public TransactionDetailsPage(WebDriver Loginddriver)
    {
        super(Loginddriver);
    }
    WebDriverWait wait = new WebDriverWait(driver, WAIT);

    public void AddVehicleData() throws InterruptedException {

        WebElement VehicleCategory=driver.findElement(By.xpath("//div[@id='s2id_formId:vehicleClass:vehicleClassField']/a/span"));
        waitelementToBeClickable(VehicleCategory);

        clickButton(VehicleCategory);
        WebElement VehicleCategoryoption=driver.findElement(By.xpath("(//div[@class='select2-result-label'])[3]"));
        clickButton(VehicleCategoryoption);
        waitForVisibility(driver.findElement(By.xpath("//div[@class='ui-pnotify-text']/li[text()='لم يتم استرجاع بيانات فحص لتلك المركبة من نظام الترخيص']")));
        WebElement closebuttonofWarningmessage=driver.findElement(By.xpath("//span[@class='icon-remove']"));

        clickButton(closebuttonofWarningmessage);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//span[@class='icon-remove']")));
        WebElement  Make=  driver.findElement(By.id("formId:vehicleMake:vehicleMakeField"));
        SendData( Make,"أودى");
        List <WebElement> MakeList= driver.findElements(By.xpath("//ul[contains(@class, 'typeahead ')]"));
        //wait.until(ExpectedConditions.visibilityOfElementLocated(MakeList));
        MakeList.get(0).click();
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//span/div/div/div/ul/li[@data-value='أودى']")));
        // WebElement Model=driver.findElement(By.xpath("//div/input[@id='formId:vehicleModel:vehicleModelField']"));
        //waitelementToBeClickable(Model);
        Thread.sleep(3000);
        //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS) ;
        SendData(driver.findElement(By.id("formId:vehicleModel:vehicleModelField")),"اس 4");
        waitForVisibility(driver.findElement(By.cssSelector("ul[style='right: initial; top: 45px; left: 0px; display: block;']")));
        List <WebElement> ModelList= driver.findElements(By.xpath("(//ul[@class='typeahead dropdown-menu'])[2]"));
        ModelList.get(0).click();
        WebElement Year=  driver.findElement(By.id("formId:makeYear:makeYearField"));
        SendData( Year,"2022");

        WebElement sequencenumber=  driver.findElement(By.id("formId:vhlNumber:vhlNumberField"));
        SendData( sequencenumber,"123456");
        WebElement Color1=  driver.findElement(By.xpath("(//div[@id='formId:j_idt981:0:colorElement0:colorElement0Panel1']/select)[1]"));
        DropdownList(Color1,"احمر");
        waitForVisibility(driver.findElement(By.xpath("//select[@id='formId:j_idt981:0:colorElement0:colorElement0Color0Field']/option[@selected='selected'][text()='احمر']")));
        WebElement Color2=  driver.findElement(By.id("formId:j_idt981:0:colorElement0:colorElement0Color1Field"));
        DropdownList(Color2,"فضي المنيوم");

        waitForVisibility(driver.findElement(By.xpath("//select[@id='formId:j_idt981:0:colorElement0:colorElement0Color1Field']/option[@selected='selected'][text()='فضي المنيوم']")));
        scrollDownPage(driver.findElement(By.id("s2id_formId:plateDetails:plate-new-design-3:plate-new-design-3Field")));
        WebElement plate3=  driver.findElement(By.id("s2id_formId:plateDetails:plate-new-design-3:plate-new-design-3Field"));
        clickButton(plate3);

        waitForVisibility(driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]")));
        WebElement plate1list= driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]"));
        clickButton(plate1list);

       WebElement  plate2=  driver.findElement(By.id("s2id_formId:plateDetails:plate-new-design-2:plate-new-design-2Field"));
        clickButton(plate2);
        waitForVisibility(driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]")));
        WebElement plate2list= driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]"));
        clickButton(plate2list);

        WebElement  plate=  driver.findElement(By.id("s2id_formId:plateDetails:plate-new-design-1:plate-new-design-1Field"));
        clickButton(plate);

        waitForVisibility(driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]")));
        WebElement platelist= driver.findElement(By.xpath("(//li[@class='select2-results-dept-0 select2-result select2-result-selectable'])[1]"));
        clickButton(platelist);

        WebElement plateText=  driver.findElement(By.id("formId:plateDetails:plate-number-id"));
        SendData(plateText,"1234");
        WebElement continuebtn=  driver.findElement(By.id("formId:btn-continue"));
        clickButton(continuebtn);
    }
}
