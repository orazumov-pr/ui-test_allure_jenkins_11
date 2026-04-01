package tests;

import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import pages.RegistrationPage;
import com.codeborne.selenide.Configuration;
import pages.components.ResultTableData;
import static com.codeborne.selenide.Selenide.closeWebDriver;
import helpers.Attach;

import static tests.TestData.*;


public class RegistrationTest {
    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableData resultTableData = new ResultTableData();

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.timeout = 10000;
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "128.0";
       Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    @DisplayName("Filling out the form with parameters")
    void successfulRegistrationTest() {

        registrationPage.openPage()
                .typeFirstName(firstName)
                .typeLastName(lastName)
                .typeEmail(userEmail)
                .setGender(gender)
                .typeNumber(userNumber)
                .setDateOfBirth(daySelect, monthSelect, yearSelect)
                .setSubjects("En")
                .setHobbies(hobbieInput)
                .loadPicture(namePicture)
                .typeAddress(address)
                .setStateAndCity(state, city)
                .submitForm();

        resultTableData.checkModalContent()
                .checkModalHeader("Thanks for submitting the form")
                .checkField("Student Name", firstName + " " + lastName)
                .checkField("Student Email", userEmail)
                .checkField("Gender", gender)
                .checkField("Mobile", userNumber)
                .checkField("Date of Birth", daySelect + " " + monthSelect + "," + yearSelect)
                .checkField("Subjects", "English")
                .checkField("Hobbies", hobbieInput)
                .checkField("Picture", namePicture)
                .checkField("Address", address)
                .checkField("State and City", state + " " + city);
    }

    @AfterEach
    void addAttachments() {
        Attach.screenshotAs("Last screenshot");
        Attach.pageSource();
        Attach.browserConsoleLogs();
        Attach.addVideo();
//        Attach.attachAsText("Some file", "Some content");
        closeWebDriver();
    }
}