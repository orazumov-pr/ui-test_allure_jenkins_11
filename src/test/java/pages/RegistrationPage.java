package pages;

import pages.components.CalendarComponent;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;

import java.time.Duration;


public class RegistrationPage {

    CalendarComponent calendar = new CalendarComponent();

    //Elements
    private final SelenideElement firstNameInput = $("#firstName");
    private final SelenideElement lastNameInput = $("#lastName");
    private final SelenideElement emailInput = $("#userEmail");
    private final SelenideElement genderContainer = $("#genterWrapper");
    private final SelenideElement numberInput = $("#userNumber");
    private final SelenideElement subjectContainer = $("#subjectsInput");
    private final SelenideElement hobbiesContainer = $("#hobbiesWrapper");
    private final SelenideElement pictureUpload = $("#uploadPicture");
    private final SelenideElement addressInput = $("#currentAddress");
    private final SelenideElement stateCityContainer = $("#stateCity-wrapper");
    private final SelenideElement setState = $("#state");
    private final SelenideElement setCity = $("#city");
    private final SelenideElement submitButton = $("#submit");


    //Actions
    public RegistrationPage openPage() {
        open("/");
        SelenideElement formsElement = $(byText("Forms"));
        formsElement.shouldBe(visible, Duration.ofSeconds(10));
        executeJavaScript("arguments[0].click();", formsElement);
        $$(".router-link").findBy(text("Practice Form")).click();

        return this;
    }

    public RegistrationPage typeFirstName(String value) {
        firstNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeLastName(String value) {
        lastNameInput.setValue(value);

        return this;
    }

    public RegistrationPage typeEmail(String value) {
        emailInput.setValue(value);

        return this;
    }

    public RegistrationPage setGender(String value) {
        genderContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage typeNumber(String value) {
        numberInput.setValue(value);

        return this;
    }

    public RegistrationPage setDateOfBirth(String day, String month, String year) {
        $("#dateOfBirthInput").click();

        calendar.setDate(day, month, year);

        return this;
    }

    public RegistrationPage setSubjects(String value) {
        subjectContainer.setValue(value).pressEnter();

        return this;
    }

    public RegistrationPage setHobbies(String value) {
        hobbiesContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage typeAddress(String value) {
        addressInput.setValue(value);

        return this;
    }

    public RegistrationPage loadPicture(String value) {
        pictureUpload.uploadFromClasspath(value);

        return this;
    }

    public RegistrationPage setState(String value) {
        setState.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setCity(String value) {
        setCity.click();
        stateCityContainer.$(byText(value)).click();

        return this;
    }

    public RegistrationPage setStateAndCity(String state, String city) {
        setState(state);
        setCity(city);

        return this;
    }

    public RegistrationPage submitForm() {
        submitButton.click();

        return this;
    }
}
