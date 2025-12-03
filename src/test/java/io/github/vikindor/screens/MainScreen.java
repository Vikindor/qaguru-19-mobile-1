package io.github.vikindor.screens;

import io.github.vikindor.screens.components.SearchComponent;
import io.qameta.allure.Step;

import static com.codeborne.selenide.CollectionCondition.sizeGreaterThan;
import static com.codeborne.selenide.Condition.text;

public class MainScreen {

    SearchComponent searchComponent = new SearchComponent();

    public MainScreen enterTextIntoSearchBar(String text) {
        searchComponent.searchContainer().click();
        searchComponent.searchSrcText().sendKeys(text);
        return this;
    }

    public MainScreen checkSearchResultIsNotEmpty() {
        searchComponent.pageListItemTitles().shouldHave(sizeGreaterThan(0));
        return this;
    }

    public MainScreen checkSearchResultForTitle(String title) {
        searchComponent.pageListItemTitle().shouldHave(text(title));
        return this;
    }
}
