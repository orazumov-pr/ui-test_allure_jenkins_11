package tests;

import org.junit.jupiter.api.DisplayName;
import pages.RegistrationPage;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.components.ResultTableData;

import static tests.TestData.*;


public class RegistrationTest {
    RegistrationPage registrationPage = new RegistrationPage();
    ResultTableData resultTableData = new ResultTableData();


    @BeforeAll
    static void setUp() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com/";
        Configuration.timeout = 10000;
//        Configuration.browser = "chrome";
//        Configuration.browserVersion = "128.0";
//        Configuration.remote = "https://user1:1234@selenoid.autotests.cloud/wd/hub";
    }

    @Test
    @DisplayName("Заполнение формы данными и проверочная таблица")
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
}