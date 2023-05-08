/*
 * Copyright (c) INTELLISC 2020.
 * All Rights Reserved.
 *
 * ver          Developer          Date        Comments
 * ----- ---------------------  ----------  ----------------------------------------
 * 1.00  Eng. Mohamed Abden 23/06/2020 - Script created.
 */
package base;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;


public class PageBase {
    public JavascriptExecutor jse;
    public static Actions actions;
    public static WebDriver driver;
    public static final long WAIT=45;
    //Super constructor
    public PageBase(WebDriver driver) {
        this.driver = driver;
    }

    public static void waitForVisibility(WebElement element)  {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static void waitelementToBeClickable(WebElement element)  {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    protected static void clickButton(WebElement element) {
        waitForVisibility(element);
        element.click();
    }
    protected static void clickButton2(WebElement element) {
        waitelementToBeClickable(element);
        element.click();
    }

    protected static void SendData(WebElement textElement, String value) {
        //waitForVisibility(textElement);
        //waitelementToBeClickable(textElement);
        textElement.sendKeys(value);
    }
    protected static void DropdownList(WebElement Element, String value) {
        waitForVisibility(Element);
        Actions actions = new Actions(driver);
        actions.moveToElement(Element).click().perform();
        waitForVisibility(Element);
        Select Color1option=  new Select(Element);
        Color1option.selectByVisibleText(value);
    }
    protected static void Listoption(List<WebElement> textElement, int value) {
        //waitForVisibility2(textElement);
        textElement.get(value).click();
    }
    public static void scrollDownPage(WebElement element){
    //  JavascriptExecutor js = (JavascriptExecutor) driver;
    //  js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        actions = new Actions(driver);
        actions.moveToElement(element);
        actions.perform();
    }

    protected static void clearField(WebElement button) {
        button.clear();
    }

    public void convertElementEditable(String elementName) {
        jse.executeScript("arguments[0].removeAttribute('readonly','readonly')", elementName);
    }
}