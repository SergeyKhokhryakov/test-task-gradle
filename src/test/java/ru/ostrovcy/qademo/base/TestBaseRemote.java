package ru.ostrovcy.qademo.base;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.remote.DesiredCapabilities;
import ru.ostrovcy.qademo.helpers.Attach;
import ru.ostrovcy.qademo.navigation.MainPage;
import ru.ostrovcy.qademo.navigation.StartPage;

import java.util.Map;

public class TestBaseRemote {
  protected StartPage startPage = new StartPage();
  protected MainPage mainPage = MainPage.getInstance();
  protected TestData data = new TestData();
  private final static String BASE_URL = "https://demoqa.com";

  @BeforeAll
  static void init() {
    Configuration.browserSize = "1920x1080";
    Configuration.baseUrl = BASE_URL;
    // для параметра Configuration.pageLoadTimeout не хватает значения по умолчанию (30 сек.),
    // https://demoqa.com имеет проблемы в загрузке контента страницы (Stalled, CAUTION: request isn't finished yet!) продолжительностью более 26 сек.
    Configuration.pageLoadTimeout = 60000;
    String selenoidAddress = System.getProperty("selenoid", "selenoid.autotests.cloud/wd/hub");
//    String selenoidAddress = System.getProperty("selenoid");
    Configuration.remote = "https://user1:1234@" + selenoidAddress;
    String browser = System.getProperty("browser");
    Configuration.browser = browser;
//    Configuration.browserVersion = "100.0";

    DesiredCapabilities capabilities = new DesiredCapabilities();
    capabilities.setCapability("selenoid:options", Map.<String, Object>of(
            // включить режим "окно-в-окне"
            "enableVNC", true,
            // включить режим "запись видео"
            "enableVideo", true
    ));
    Configuration.browserCapabilities = capabilities;
  }

  @BeforeEach
  void addListener() {
    SelenideLogger.addListener(" Allure", new AllureSelenide());
  }

  @AfterEach
  void addAttachments() {
    Attach.screenshotAs("Last screenshot");
    Attach.pageSource();
    Attach.browserConsoleLogs();
    Attach.addVideo();
  }
}
