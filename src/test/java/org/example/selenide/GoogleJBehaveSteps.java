package org.example.selenide;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.jbehave.core.annotations.AfterScenario;
import org.jbehave.core.annotations.BeforeScenario;
import org.jbehave.core.annotations.BeforeStory;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Named;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.jbehave.core.steps.Steps;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by iurii.dziuban on 02/10/2017.
 */
public class GoogleJBehaveSteps extends Steps {

    private WebDriver driver;

    @BeforeStory
    public static void setupClass() {
        System.setProperty("selenide.browser", "chrome");
        ChromeDriverManager.getInstance().setup();
    }

    @BeforeScenario
    public void beforeScenario() {
        driver = new ChromeDriver();
    }

    @AfterScenario
    public void afterScenario() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("Open url '$url'")
    public void openUrl(@Named("url") String url) {
        Selenide.open(url);
    }

    @When("find '$string'")
    public void find(@Named("string") String string) {
        Selenide.$(By.name("q")).val(string).pressEnter();
    }

    @Then("check result size is $size")
    public void checkSize(@Named("size") int size) {
        Selenide.$$("#ires .g").shouldHave(CollectionCondition.size(size));
    }

    @Then("check text '$text' is present")
    public void checkTextIsPresent(@Named("text") String text) {
        Selenide.$("#ires .g").shouldBe(Condition.visible).shouldHave(Condition.text(text));
    }
}
