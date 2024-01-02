package ru.ostrovcy.qademo.pages;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$;

public class BrowserWindowsPage {

  @Step("16. Нажать на кнопку «New Tab»")
  public BrowserWindowsPage openNewTab(){
    $("#tabButton").click();
    return this;
  }

  @Step("17. Закрыть новую вкладку")
  public void closeNewTab(){
    close();
  }

  @Step("18. Нажать на кнопку «New window»")
  public BrowserWindowsPage openNewWindow(){
    $("#windowButton").click();
    return this;
  }

  @Step("19. Закрыть новое окно")
  public void closeNewWindow(){
    close();
  }

  private void close(){
    Selenide.switchTo().window(1);
    Selenide.closeWindow();
    Selenide.switchTo().window(0);
  }

}
