package edu.rit.swen253.test.hoursAndLocations;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.hoursAndLocations.HoursAndLocationsPage;
import edu.rit.swen253.page.hoursAndLocations.LocationResultView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import java.util.List;

import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CoffeeLocationsTest extends AbstractWebTest {

    private TigerCenterHomePage homePage;

    private HoursAndLocationsPage hoursAndLocationsPage;

    private List<LocationResultView> results;

    @Test
    @Order(1)
    @DisplayName("First, navigate to the Tiger Center Home page.")
    void navigateToHomePage() {
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
    }

    @Test
    @Order(2)
    @DisplayName("Second, click on the Hours and Location button and validate navigation.")
    void navigateToHoursAndLocations() {
        homePage.selectHoursAndLocations();
        hoursAndLocationsPage = assertNewPage(HoursAndLocationsPage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Third, search for coffee locations.")
    void enterSearchAndSubmit() {
        results = hoursAndLocationsPage.diningSearch("coffee");
        // Assert the number of search results
        assertEquals(4, results.size());
    }

    @Test
    @Order(4)
    @DisplayName("Fourth, validate the search results.")
    void validateSearchResults() {
        // Assert the number of search results
        assertEquals(4, results.size());
    }
}
