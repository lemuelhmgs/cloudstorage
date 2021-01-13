package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

  LoginPage(WebDriver driver){
    PageFactory.initElements(driver, this);
  }
  @FindBy(id = "inputUsername")
  private WebElement usernameInputField;

  @FindBy(id = "inputPassword")
  private WebElement passwordInputField;

  @FindBy(id ="submit")
  private WebElement submitButton;

  @FindBy(id = "error-msg")
  private WebElement errorMessageText;

  @FindBy(id= "su")



  public void signIn(String userName, String password){
    usernameInputField.sendKeys(userName);
    passwordInputField.sendKeys(password);
    submitButton.click();
  }

  public void enterValue(WebElement element, String value){
    element.sendKeys(value);
  }

  public String getErrMessage(){
    return errorMessageText.getText();
  }

}
