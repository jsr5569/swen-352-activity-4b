package edu.rit.swen253.page.classSearch;

import edu.rit.swen253.page.AbstractPage;
import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.util.List;

public class ClassSearchPage extends AbstractPage {
    private static final By SELECT_TERM = By.className("bigTermPicker");
    private static final By SELECTED_TERM = By.tagName("option");

    public ClassSearchPage() { super(); }

    public void selectSemesterTerm() {
        DomElement selectTerm = findOnPage(SELECT_TERM);
        selectTerm.sendKeys(Keys.ENTER);

        List<DomElement> terms = findAllOnPage(SELECTED_TERM);
        if (!terms.isEmpty()) {
            DomElement fallSemester = terms.get(2);
            fallSemester.sendKeys(Keys.ENTER);
        }
    }
}
