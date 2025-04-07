package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SimpleClassSearchTest extends AbstractWebTest {

    public static Logger LOGGER = Logger.getLogger(SimpleClassSearchTest.class.getName());

    private TigerCenterHomePage homePage ;

    private ClassSearchPage classSearchPage;

    @Test
    @Order(1)
    @DisplayName("Test Navigate to Search Page")
    void navigateToClassSearchTest() {
        homePage = navigateToPage("https://tigercenter.rit.edu/tigerCenterApp/landing", TigerCenterHomePage::new);
        homePage.selectClassSearch();
        classSearchPage = new ClassSearchPage();
    }

    @Test
    @Order(2)
    @DisplayName("Test Select Semester Term")
    void selectSemesterTermTest() {
        Select selectTerm = classSearchPage.selectSemesterTerm();
        assertEquals("2025-26 Fall (2251)", selectTerm.getFirstSelectedOption().getText());
    }

    @Test
    @Order(3)
    @DisplayName("Test Enter Search Term")
    void enterSearchTerm() {
        classSearchPage.searchForClass("SWEN");
        List<String> results = classSearchPage.getSearchResults();
        assertNotEquals(0, results.size());
        results.forEach(LOGGER::info);
    }

    @Test
    @Order(4)
    @DisplayName("Test Correct First Result")
    void checkFirstResultTest() {
        String result = classSearchPage.getFirstResult();
        assertEquals("Independent Study", result);
    }
}
