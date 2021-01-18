package com.udacity.jwdnd.course1.cloudstorage;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CommonUtil {

  private WebDriver driver;
  private WebDriverWait wait;

  public CommonUtil(WebDriver driver) {
    this.driver = driver;

    wait =new WebDriverWait(driver, 30);
  }



  public void click (WebElement element){
    waitForElement(element);
    element.click();
  }

  public String getText(WebElement element){
    return element.getText();
  }
  public void enterText(WebElement element, String value){
    waitForElement(element);
    element.clear();
    element.sendKeys(value);
  }

  public void waitForElement(WebElement element){
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  public  boolean isClickable(WebElement element)
  {
    try
    {
      WebDriverWait wait = new WebDriverWait(driver, 25);
      wait.until(ExpectedConditions.elementToBeClickable(element));
      return true;
    }
    catch (Exception e)
    {
      return false;
    }
  }


}
