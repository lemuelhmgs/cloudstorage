package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class SignupPage {
  private CommonUtil util;


  SignupPage(WebDriver driver){
    PageFactory.initElements(driver, this);
    util = new CommonUtil(driver);

  }

  @FindBy(id="inputFirstName")
  private WebElement inputFirstName;

  @FindBy(id="inputLastName")
  private WebElement inputlastName;

  @FindBy(id="inputUsername")
  private WebElement inputUserName;

  @FindBy(id="inputPassword")
  private WebElement inputPassword;

  @FindBy(id="signUp")
  private WebElement signUp;



  @FindBy(id="success-msg")
  private  WebElement signUpMessage;

  public void signUp(String firstName, String lastName, String userName, String password){

    util.waitForElement(inputFirstName);
    util.enterText(inputFirstName,firstName);
    util.enterText(inputlastName,lastName);
    util.enterText(inputUserName, userName);
    util.enterText(inputPassword,password);

    util.click(signUp);

    util.waitForElement(signUpMessage);

  }

  public String Message(){
    return util.getText(signUpMessage);
  }

}
