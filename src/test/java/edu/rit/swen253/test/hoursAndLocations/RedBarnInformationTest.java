package edu.rit.swen253.test.hoursAndLocations;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.hoursAndLocations.HoursAndLocationsPage;
import edu.rit.swen253.page.hoursAndLocations.LocationResultView;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static edu.rit.swen253.utils.BrowserType.FIREFOX;
import static edu.rit.swen253.utils.BrowserType.onBrowser;
import static edu.rit.swen253.utils.TimingUtils.sleep;
import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class RedBarnInformationTest extends AbstractWebTest {

    private TigerCenterHomePage homePage;

    private HoursAndLocationsPage hoursAndLocationsPage;

    private LocationResultView redBarnInformation;

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
    @DisplayName("Third, open the student affairs section")
    void clickStudentAffairsTab() {
        hoursAndLocationsPage.clickStudentAffairsTab();
    }

    @Test
    @Order(4)
    @DisplayName("Fourth, open the dropdown for the red barn")
    void openAndValidateRedBarn() {
        String description = "RBC is a bouldering-only gym, where climbs are shorter and falls are protected " +
                "with padding. Climbing routes are set and maintained to accommodate all abilities and skill " +
                "levels, from beginner to expert.All non-RIT visitors must be 18 years of age or older.The Red Barn " +
                "is a 100 year old \"Wells\" style dairy barn that lacks many modern amenities such as heat and " +
                "plumbing. Please dress based on the outside temperature. There is a \"Port-A-John\" located " +
                "in the main parking lot that is available to all visitors.RBC is open when RIT semester classes " +
                "are in session and closed during exam weeks and breaks.";
        redBarnInformation = hoursAndLocationsPage.viewStudentAffairOffice("Red Barn Climbing Gym");
        sleep(3);
        assertAll("group assertions",
                () -> assertNotNull(redBarnInformation),
                () -> assertEquals(description, redBarnInformation.getDescription()));
    }

    @Test
    @Order(5)
    @DisplayName("Fifth, open the location on the rit map")
    void openAndValidateRedBarnMap() {
        redBarnInformation.openLocationMap();
        final SimplePage mapsPage = assertNewWindowAndSwitch(SimplePage::new);
        // there's a timing issue with Firefox (give it a second to render)
        if (onBrowser(FIREFOX)) {
            sleep(1);
        }
        String maps = "https://maps.rit.edu/";
        assertTrue(mapsPage.getURL().contains(maps));
    }
}
