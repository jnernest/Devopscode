package org.example.selenide;

/**
 * Created by iurii.dziuban on 02/10/2017.
 */

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleTest {

    private WebDriver driver;

    @BeforeClass
    public static void setupClass() {
        System.setProperty("selenide.browser", "chrome");
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        driver = new ChromeDriver();
    }

    @After
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void test() {
        Selenide.open("http://google.com/ncr");
        Selenide.$(By.name("q")).val("selenide").pressEnter();
        Selenide.$$("#ires .g").shouldHave(CollectionCondition.size(10));
        Selenide.$("#ires .g").shouldBe(Condition.visible).shouldHave(
                Condition.text("Selenide: concise UI tests in Java"),
                Condition.text("selenide.org"));
    }
}
