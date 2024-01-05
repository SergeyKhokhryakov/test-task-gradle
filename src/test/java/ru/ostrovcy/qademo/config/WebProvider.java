package ru.ostrovcy.qademo.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class WebProvider {
  protected static WebConfig webConfig = null;
  private final AuthConfig authConfig;

  private final static long DELAY = 60000;

  public WebProvider() {
    this.webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());
    this.authConfig = ConfigFactory.create(AuthConfig.class, System.getProperties());

    createWeb();
  }

  private void createWeb() {

    Configuration.baseUrl = webConfig.getBaseUrl();
    Configuration.browserSize = webConfig.getBrowserSize();
    Configuration.pageLoadTimeout = DELAY;
    if (webConfig.isRemote() == true) {
      Configuration.browser = webConfig.getBrowser().toString();
      Configuration.browserVersion = webConfig.getBrowserVersion();
      String s = "s",
              password = authConfig.getPassword(),
              userName = "";
      if (password == null || password.equals("")) {
        password = "";
        s = "";
      } else {
        userName = authConfig.getUserName() + ":";
        password += "@";
      }
      Configuration.remote = "http" + s + "://" + userName + password + webConfig.getRemoteUrl() + "/wd/hub";

      DesiredCapabilities capabilities = new DesiredCapabilities();
      capabilities.setCapability("selenoid:options", Map.<String, Object>of(
              // включить режим "окно-в-окне"
              "enableVNC", true,
              // включить режим "запись видео"
              "enableVideo", true
      ));
      Configuration.browserCapabilities = capabilities;
    }
  }
}
