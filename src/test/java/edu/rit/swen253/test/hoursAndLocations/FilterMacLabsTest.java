package edu.rit.swen253.test.hoursAndLocations;

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
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FilterMacLabsTest extends AbstractWebTest {

    private TigerCenterHomePage homePage;

    private HoursAndLocationsPage hoursAndLocationsPage;

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
        sleep(3);
        hoursAndLocationsPage = assertNewPage(HoursAndLocationsPage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
    }

    @Test
    @Order(3)
    @DisplayName("Third, open the computer lab section")
    void clickComputerLabTab() {
        hoursAndLocationsPage.clickComputerLabTab();
        sleep(3);
    }

    @Test
    @Order(4)
    @DisplayName("Fourth, filter computer labs by MAC machines.")
    void FilterAndValidateResults() {
       List<LocationResultView> results = hoursAndLocationsPage.clickMacFilter();
       sleep(3);
       // Assert the number of computer labs with MAC machines
       assertEquals(results.size(), 10);
    }

}
