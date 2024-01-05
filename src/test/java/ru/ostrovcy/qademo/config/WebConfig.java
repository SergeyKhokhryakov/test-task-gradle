package ru.ostrovcy.qademo.config;


import org.aeonbits.owner.Config;


//@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
//        "system:properties",
        "classpath:${env}.properties"
//        "file:~/${env}.properties",
//        "file:./${env}.properties"
})

public interface WebConfig extends Config {

  @Key("baseUrl")
  @DefaultValue("https://demoqa.com")
  String getBaseUrl();

  @Key("browser")
  @DefaultValue("CHROME")
  Browser getBrowser();

  @Key("browserVersion")
  @DefaultValue("100.0")
  String getBrowserVersion();

  @Key("browserSize")
  @DefaultValue("1920x1080")
  String getBrowserSize();

  @Key("isRemote")
  @DefaultValue("false")
  boolean isRemote();

  // зачитываем данные из командной строки
  @Key("remoteUrl")
  // обрабатывает дефолтное значение
  @DefaultValue("localhost:4444")
  // конвертируем в возращаемый тип
  String getRemoteUrl();
}
