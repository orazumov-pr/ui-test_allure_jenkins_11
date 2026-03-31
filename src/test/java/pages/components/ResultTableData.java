package pages.components;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class ResultTableData {

    private SelenideElement modalContent = $(".modal-content");
    private SelenideElement modalHeader = $(".modal-header");

    public ResultTableData checkModalContent() {
        modalContent.shouldBe(visible);
        return this;
    }

    public ResultTableData checkModalHeader(String expectedHeader) {
        modalHeader.shouldHave(text(expectedHeader));
        return this;
    }

    public ResultTableData checkField(String fieldName, String expectedValue) {
        $(".table-responsive")
                .$(byText(fieldName))
                .parent()
                .shouldHave(text(expectedValue));
        return this;
    }
}
