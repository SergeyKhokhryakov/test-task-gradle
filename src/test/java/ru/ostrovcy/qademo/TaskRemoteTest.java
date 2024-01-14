package ru.ostrovcy.qademo;

import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import ru.ostrovcy.qademo.base.TestBaseRemote;

/**
 * @author hso (Sergey Khokhryakov).
 */

@Layer("web")
@Owner("hso")
//@Feature("Issues")
@Feature("Фичи: \"Elements\", \"Alerts, Frame & Windows\"")
public class TaskRemoteTest extends TestBaseRemote {

  String clickResult = "You have done a dynamic click";
  String contextClickResult = "You have done a right click";
  String doubleClickResult = "You have done a double click";
  String textConfirm = "You selected \nOk";
  String promptText = "Test name";

  @Test
  @TM4J("AE-T3")
  @Story("Элементы на странице demoqa.com")
  @JiraIssues({@JiraIssue("AE-2")})
  @Description("Данный тест разработан в соотвествии с тестовым заданием")
  @Severity(SeverityLevel.CRITICAL)
  @Tag("remote")
//  @Tags({@Tag("web"), @Tag("critical")})
  @DisplayName("Позитивный тест: Шаги с аннотацией @Step")
  public void test() {
    startPage.open()
            .goToElementsCategory()
            .openTextBoxPage()
            .inputFields(data.getUserName(), data.getUserEmail(), data.getCurrentAddress(), data.getPermanentAddress())
            .sendForm()
            .verifyBlockResult(data.getUserName(), data.getUserEmail(), data.getCurrentAddress(), data.getPermanentAddress());

    mainPage.openButtonsPage()
            .click()
            .verifyClickResult(clickResult)
            .contextClick()
            .verifyContextClickResult(contextClickResult)
            .doubleClick()
            .verifyDoubleClickResult(doubleClickResult);

    mainPage.openAlertsFrameWindowsCategory()
            .openBrowserWindowsPage()
            .openNewTab()
            .closeNewTab();

    mainPage.onBrowserWindowsPage()
            .openNewWindow()
            .closeNewWindow();

    mainPage.openAlertPage()
            .showAlert()
            .closeModal();

    mainPage.onAlertsPage()
            .showAlertTimer()
            .closeModalTimer();

    mainPage.onAlertsPage()
            .showConfirmBox()
            .confirmModal()
            .verifyConfirmResult(textConfirm);

    mainPage.onAlertsPage()
            .showPromptBox()
            .input(promptText)
            .verifyPromptResult(promptText);
  }

}
