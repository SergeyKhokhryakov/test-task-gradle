package ru.ostrovcy.qademo.pages;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class ButtonsPage {

  @Step("8. Нажать на кнопку «Click me»")
  public ButtonsPage click() {
    $x("//button[text()='Click Me']").click();
    return this;
  }

  @Step("9. Проверить, что появился текст «You have done a dynamic click»")
  public ButtonsPage verifyClickResult(String value) {
    verifyResult("#dynamicClickMessage", value);
    return this;
  }

  @Step("10. Нажать на кнопку «Right Click me»")
  public ButtonsPage contextClick(){
    $("button#rightClickBtn").contextClick();
    return this;
  }

  @Step("11. Проверить, что появился текст «You have done a right click»")
  public ButtonsPage verifyContextClickResult(String value) {
    verifyResult("#rightClickMessage", value);
    return this;
  }

  @Step("12. Нажать на кнопку «Double Click me»")
  public ButtonsPage doubleClick(){
    $("button#doubleClickBtn").doubleClick();
    return this;
  }

  @Step("13. Проверить, что появился текст «You have done a double click»")
  public void verifyDoubleClickResult(String value) {
    verifyResult("#doubleClickMessage", value);
  }

  public void verifyResult(String locator, String textExpected) {
    $(locator).should(appear).shouldHave(text(textExpected));
  }

}
