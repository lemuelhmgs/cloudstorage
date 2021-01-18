package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

  private CommonUtil util;
  private LoginPage loginPage;

  HomePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
    util = new CommonUtil(driver);
    loginPage = new LoginPage(driver);
  }

  @FindBy(id = "logOut")
  WebElement logoutButton;

  @FindBy(id = "nav-files-tab")
  WebElement fileTab;

  @FindBy(id = "nav-notes-tab")
  WebElement notesTab;

  @FindBy(id = "nav-credentials-tab")
  WebElement credentialsTab;

  @FindBy(id = "addNote")
  WebElement addNoteButton;

  @FindBy(id = "note-title")
  WebElement titleTextBox;

  @FindBy(id = "note-description")
  WebElement descriptionTextBox;

  @FindBy(id = "saveNote")
  WebElement saveNoteChanges;

  @FindBy(id = "resultMessage")
  WebElement message;

  @FindBy(id = "homeLink")
  WebElement redirectToHomePage;

  @FindBy(id = "editNote")
  WebElement editNote;

  @FindBy(id = "deleteNote")
  WebElement deleteNote;

  @FindBy(id = "noteTitleContent")
  WebElement noteTitle;

  @FindBy(id = "resultHome")
  WebElement returnToHomeFromResult;

  @FindBy(id = "addCredButton")
  WebElement addCredentialButton;

  @FindBy(id = "credential-url")
  WebElement urlInputText;

  @FindBy(id = "credential-username")
  WebElement credUsernameInputText;

  @FindBy(id = "credential-password")
  WebElement credPasswordInputText;

  @FindBy(id = "saveCred")
  WebElement saveCredential;

  @FindBy(id = "editCred")
  WebElement editCredential;

  @FindBy(id = "deleteCred")
  WebElement deleteCredential;

  @FindBy(id = "urlTitleText")
  WebElement credURLText;

  public void addNote(String title, String description) throws InterruptedException {
    loginPage.signIn("admin", "1234");
    Thread.sleep(1000);
    if (util.isClickable(notesTab)) {
      util.click(notesTab);

    }
    util.click(addNoteButton);
    util.enterText(titleTextBox, title);
    util.enterText(descriptionTextBox, description);
    util.click(saveNoteChanges);
    Thread.sleep(1000);

  }

  public void editNote(String title, String newTitle, String description)
      throws InterruptedException {
    addNote(title, description);
    navigateHomeTab(notesTab);
    util.click(editNote);
    util.enterText(titleTextBox, newTitle);
    util.click(saveNoteChanges);
    Thread.sleep(1000);
    navigateHomeTab(notesTab);
    Thread.sleep(1000);

  }

  public void deleteNote(String title, String description) throws InterruptedException {
    addNote(title, description);
    navigateHomeTab(notesTab);
    util.click(deleteNote);


  }

  private void navigateHomeTab(WebElement element) throws InterruptedException {
    util.click(returnToHomeFromResult);
    Thread.sleep(1000);
    if (util.isClickable(element)) {
      util.click(element);

    }
  }


  public void addCredential(String url, String userName, String password)
      throws InterruptedException {
    loginPage.signIn("admin", "1234");
    Thread.sleep(1000);
    if (util.isClickable(credentialsTab)) {
      util.click(credentialsTab);

    }
    util.click(addCredentialButton);
    util.enterText(urlInputText, url);
    util.enterText(credUsernameInputText, userName);
    util.enterText(credPasswordInputText, password);
    util.click(saveCredential);
    Thread.sleep(1000);
  }

  public void editCredential(String url, String newURL, String userName, String password)
      throws InterruptedException {
    addCredential(url, userName, password);

    navigateHomeTab(credentialsTab);
    util.click(editCredential);
    util.enterText(urlInputText, newURL);
    util.click(saveCredential);
    Thread.sleep(1000);
    navigateHomeTab(credentialsTab);
    Thread.sleep(1000);

  }

  public void deleteCredential(String url, String userName, String password)
      throws InterruptedException {
    addCredential(url, userName, password);
    navigateHomeTab(credentialsTab);

    util.click(deleteCredential);
  }

  public String getCredURLText() {
    return util.getText(credURLText);
  }

  public String getTitleText() {

    return util.getText(noteTitle);
  }

  public String getResultMessage() {
    return util.getText(message);
  }

  public void logout() throws InterruptedException {
    util.isClickable(logoutButton);
    Thread.sleep(1000);
    util.click(logoutButton);
  }


}
