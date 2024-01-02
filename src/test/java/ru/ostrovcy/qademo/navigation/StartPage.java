package ru.ostrovcy.qademo.navigation;

import com.codeborne.selenide.Selenide;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.$x;

/**
 * Стартовая страница сайта https://demoqa.com/,
 * основу которой составляет перечень категорий:
 * 1. Elements
 * 2. Forms
 * 3. Alerts, Frame & Windows
 * 4. Widgets
 * 5. Interactions
 * 6. Book Store Application
 *
 * Методы:
 *   - open()
 *   - goToElementsCategory()
 *   - goToAlertsFrameWindowsCategory()
 *   - по остальным категориям методы дополняются по мере реализации проекта
 */
public class StartPage {
  private String locatorElements = "//h5[text()='Elements']";
  private String locatorAlertsFrameWindows = "//h5[text()='Alerts, Frame & Windows']";

  @Step("1. Перейти на https://demoqa.com/")
  public StartPage open() {
    Selenide.open("/");
    return this;
  }

  @Step("2.	Нажать на «Elements»")
  public MainPage goToElementsCategory() {
    goToCategory(locatorElements);
    return MainPage.getInstance();
  }

  public MainPage goToAlertsFrameWindowsCategory() {
    goToCategory(locatorAlertsFrameWindows);
    return MainPage.getInstance();
  }

  private void goToCategory(String locator) {
    $x(locator).click();
  }
}
