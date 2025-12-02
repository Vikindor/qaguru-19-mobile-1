package io.github.vikindor.tests;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;

public class WikipediaTests extends TestBase {

    @Disabled
    @Test
    @DisplayName("Successful empty search")
    void emptySearchTest() {

        step("Perform empty search", () -> {
            mainScreen.enterTextIntoSearchBar("");
        });

        step("Check that search results list is not empty", () -> {
            mainScreen.checkSearchResultIsNotEmpty();
        });
    }

    @Test
    @DisplayName("Successful search by the word \"Appium\"")
    void shouldSearchForAppiumSuccessfully() {

        step("Enter search query", () -> {
            mainScreen.enterTextIntoSearchBar("Appium");
        });

        step("Verify found content", () -> {
            mainScreen.checkSearchResultForTitle("Appium");
        });
    }
}
