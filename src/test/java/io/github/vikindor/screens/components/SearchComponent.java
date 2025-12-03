package io.github.vikindor.screens.components;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static io.appium.java_client.AppiumBy.accessibilityId;
import static io.appium.java_client.AppiumBy.id;

public class SearchComponent {
    private final SelenideElement searchContainer = $(accessibilityId("Search Wikipedia"));
    private final SelenideElement searchSrcText = $(id("org.wikipedia.alpha:id/search_src_text"));
    private final SelenideElement pageListItemTitle = $(id("org.wikipedia.alpha:id/page_list_item_title"));
    private final ElementsCollection pageListItemTitles = $$(id("org.wikipedia.alpha:id/page_list_item_title"));

    public SelenideElement searchContainer() {
        return searchContainer;
    }

    public SelenideElement searchSrcText() {
        return searchSrcText;
    }

    public SelenideElement pageListItemTitle() {
        return pageListItemTitle;
    }

    public ElementsCollection pageListItemTitles() { return pageListItemTitles; }
}
