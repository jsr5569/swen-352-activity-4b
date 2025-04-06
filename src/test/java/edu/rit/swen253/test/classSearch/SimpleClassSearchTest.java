package edu.rit.swen253.test.classSearch;

import edu.rit.swen253.page.classSearch.ClassSearchPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.*;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.logging.Logger;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class SimpleClassSearchTest {

    public static Logger LOGGER = Logger.getLogger(SimpleClassSearchTest.class.getName());

    private TigerCenterHomePage homePage;

    private ClassSearchPage classSearchPage;

    @Test
    @Order(1)
    @DisplayName("Test Navigate to Search Page")
    void navigateToClassSearch() {
        classSearchPage = navigateToClassSearch();
    }
}
