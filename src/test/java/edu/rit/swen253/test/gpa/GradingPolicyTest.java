package edu.rit.swen253.test.gpa;

import edu.rit.swen253.page.SimplePage;
import edu.rit.swen253.page.gpa.GPACalculatorPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class GradingPolicyTest extends AbstractWebTest {
    private TigerCenterHomePage homePage;
    private BrowserWindow<TigerCenterHomePage> homeWindow;
    private GPACalculatorPage gpaPage;

    @Test
    @Order(1)
    @DisplayName("Navigate to the Tiger Center home page.")
    public void navigateToHomePage(){
        homePage = navigateToPage("https://tigercenter.rit.edu", TigerCenterHomePage::new);
        assertNotNull(homePage);
        homeWindow = getCurrentWindow();

    }

    @Test
    @Order(2)
    @DisplayName("Navigate to the Tiger Center home page.")
    public void navigateToGPACalc(){
        homePage.selectGPACalculator();
        gpaPage = new GPACalculatorPage();

        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc", gpaPage.getURL());
    }

    @Test
    @Order(3)
    @DisplayName("Navigate to the Tiger Center home page.")
    public void navigateToGradingPolicy(){
        gpaPage.clickGradingPolicy();
        final SimplePage gradingPolicyPage = assertNewPage(SimplePage::new);

        assertEquals("https://www.rit.edu/policies/d050", gradingPolicyPage.getURL());
    }
}
