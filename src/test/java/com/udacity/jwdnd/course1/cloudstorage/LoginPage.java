package com.udacity.jwdnd.course1.cloudstorage;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
  private CommonUtil util;

  LoginPage(WebDriver driver){

    PageFactory.initElements(driver, this);
     util = new CommonUtil(driver);
  }
  @FindBy(id = "inputUsername")
  private WebElement usernameInputField;

  @FindBy(id = "inputPassword")
  private WebElement passwordInputField;

  @FindBy(id ="submit")
  private WebElement submitButton;

  @FindBy(id = "error-msg")
  private WebElement errorMessageText;

  @FindBy(id ="signup-link")
  private WebElement signUpLink;



  public void signIn(String userName, String password){
    util.enterText(usernameInputField,userName);
    util.enterText(passwordInputField,password);
    util.click(submitButton);

  }

  public void signUp(){
    util.click(signUpLink);
  }


  public String getErrMessage(){

    return util.getText(errorMessageText);

  }

}
