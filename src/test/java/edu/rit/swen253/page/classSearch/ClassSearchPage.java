package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ClassSearchPage extends AbstractAngularPage {
    private static final By SELECT_TERM = By.tagName("select");
    private static final By SEARCH_BAR = By.tagName("input");
    private static final By INSTRUCTOR_INPUT = By.className("advancedSearchInstructorInput");
    private static final By SEARCH_RESULTS = By.className("classSearchResultsDisplayName");
    private static final By SEARCH_BUTTON = By.className("classSearchSearchButton");
    private static final By ADVANCED_SEARCH = By.className("classSearchAdvancedSearchText");
    private static final By SAVE_OPTIONS = By.className("advancedSearchDoneButton");
    private List<DomElement> searchResults;


    public ClassSearchPage() { super("class-search"); }

    public Select selectSemesterTerm() {
        DomElement selectTerm = findOnPage(SELECT_TERM);
        Select termDropdown = new Select(selectTerm.getWebElement());
        termDropdown.selectByIndex(3);
        return termDropdown;
    }

    public void searchForClass(String query) {
        DomElement input = findOnPage(SEARCH_BAR);
        DomElement searchButton = findOnPage(SEARCH_BUTTON);
        input.sendKeys(query);
        searchButton.click();
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

    public void openAdvancedSearch() {
        DomElement advancedSearch = findOnPage(ADVANCED_SEARCH);
        advancedSearch.click();
    }

    public void enterInstructorName(String instructor) {
        DomElement input = findOnPage(INSTRUCTOR_INPUT);
        input.sendKeys(instructor);
    }

    public void saveOptions() {
        DomElement saveButton = findOnPage(SAVE_OPTIONS);
        saveButton.click();
    }
}
