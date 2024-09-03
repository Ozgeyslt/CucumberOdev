package steps;

import com.codeborne.selenide.Condition;
import common.PageFactory;
import common.PageManager;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.assertj.core.api.Assertions;
import test.BaseTest;

import java.time.Duration;

public class ButtonsSteps extends BaseTest {

    PageManager pageManager = new PageManager();

    @Before
    public void before() {
        PageFactory.buildButtons();
    }


    @Given("Buttons page opens")
    public void openButtonsPage() {
        pageManager.buttons.button.click();
    }

    @When("Click the Click me button")
    public void clickClickMeButton() {
        pageManager.buttons.clickToClickMe();
    }

    @Then("The dynamic message is displayed")
    public void dynamicClickIsDisplayed() {
        pageManager.buttons.dynamicMessage.should(Condition.appear, Duration.ofSeconds(10));
        Assertions.assertThat(pageManager.buttons.dynamicMessage.getText()).isEqualTo("You have done a dynamic click");
    }
}
