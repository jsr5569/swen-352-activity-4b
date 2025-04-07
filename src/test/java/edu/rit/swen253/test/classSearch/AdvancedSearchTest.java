package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AdvancedSearchTest extends AbstractWebTest {
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
    @DisplayName("Test Open Advanced Search Modal")
    void openAdvancedSearchTest() {
        classSearchPage.selectSemesterTerm();
        classSearchPage.openAdvancedSearch();
    }

    @Test
    @Order(3)
    @DisplayName("Test Enter Instructor Name and Save Options")
    void createAdvancedSearchTest() {
        classSearchPage.enterInstructorName("Bryan");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        classSearchPage.saveOptions();
    }

    @Test
    @Order(4)
    @DisplayName("Enter Search Term and Display Results")
    void enterAdvancedSearchTerm() {
        classSearchPage.searchForClass("SWEN");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        List<String> results = classSearchPage.getSearchResults();
        assertEquals(6, results.size());
        results.forEach(LOGGER::info);
    }

    @Test
    @Order(5)
    @DisplayName("Get the first returned class")
    void checkFirstResultTest() {
        String result = classSearchPage.getFirstResult();
        if (result != null) {
            assertEquals("Software Testing", result);
        } else {
            fail("No results found.");
        }
    }
}
