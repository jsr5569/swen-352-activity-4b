package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ClassSearchPage extends AbstractPage {
    private static final By SELECT_TERM = By.tagName("select");
    private static final By SEARCH_BAR = By.tagName("input");
    private static final By SEARCH_RESULTS = By.className("classSearchResultsDisplayName");
    private List<DomElement> searchResults;


    public ClassSearchPage() { super(); }

    public Select selectSemesterTerm() {
        DomElement selectTerm = findOnPage(SELECT_TERM);
        Select termDropdown = new Select(selectTerm.getWebElement());
        termDropdown.selectByIndex(3);
        return termDropdown;
    }

    public void searchForClass(String query) {
        DomElement input = findOnPage(SEARCH_BAR);
        input.sendKeys(query);
        input.sendKeys(Keys.ENTER);
    }

    public List<String> getSearchResults() {
        searchResults = findAllOnPage(SEARCH_RESULTS);
        List<String> list = new ArrayList<>();
        for (DomElement element : searchResults) {
            String className = element.getText();
            if (className != null) {
                list.add(className);
            }
        }
        return list;
    }

    public String getFirstResult() {
        List<String> list = getSearchResults();
        return list.getFirst();
    }
}
