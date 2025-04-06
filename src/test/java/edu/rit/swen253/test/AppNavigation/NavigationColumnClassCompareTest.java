package edu.rit.swen253.test.AppNavigation;

import edu.rit.swen253.page.AppNavigation.NavigationColumn;
import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.hoursAndLocations.HoursAndLocationsPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import org.junit.jupiter.api.*;

import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class NavigationColumnClassCompareTest extends AbstractWebTest {

    private TigerCenterHomePage homePage;
    private HoursAndLocationsPage hoursAndLocationsPage;

    private NavigationColumn navCol;

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
    @DisplayName("Third, click on the Class Compare Navigation Column Button.")
    void navigateToGPACalculator() {
        navCol = hoursAndLocationsPage.getSideNavColumn();
        navCol.navigateToClassCompare();
        sleep(3);
    }

    @Test
    @Order(4)
    @DisplayName("Fourth, validate navigation.")
    void validateNavigation() {
        final SimplePage gpaCalc = assertNewPage(SimplePage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/class-compare", gpaCalc.getURL());
    }
}
