package ru.ostrovcy.qademo.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class TextBoxPage {

  SelenideElement response = $("#output");

  @Step("4. Заполнить поля: Full Name, Email, Current Address, Permanent Address")
  public TextBoxPage inputFields(String name, String email, String currentAddress, String permanentAddress){
    setUserName(name);
    setUserEmail(email);
    setCurrentAddress(currentAddress);
    setPermanentAddress(permanentAddress);
    return this;
  }

  public TextBoxPage setUserName(String value) {
    $("input#userName").setValue(value);
    return this;
  }

  public TextBoxPage setUserEmail(String value) {
    $("input#userEmail").setValue(value);
    return this;
  }

  public TextBoxPage setCurrentAddress(String value) {
    $("textarea#currentAddress").setValue(value);
    return this;
  }

  public TextBoxPage setPermanentAddress(String value) {
    $("textarea#permanentAddress").setValue(value);
    return this;
  }

  @Step("5. Нажать на кнопку «Submit»")
  public TextBoxPage sendForm(){
    $("#submit").click();
    return this;
  }

  @Step("6. Проверить, что данные в блоке сохранены корректно")
  public void verifyBlockResult(String name, String email, String currentAddress, String permanentAddress){
    verifyModalAppears();
    verifyResult("name", name);
    verifyResult("email", email);
    verifyResult("currentAddress", currentAddress);
    verifyResult("permanentAddress", permanentAddress);
  }

  private void verifyModalAppears() {
    response.should(appear);
  }

  private void verifyResult(String key, String value) {
    response.$("#" + key).shouldHave(text(value));
  }

}
