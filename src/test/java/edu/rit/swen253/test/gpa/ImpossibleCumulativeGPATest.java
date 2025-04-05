package edu.rit.swen253.test.gpa;

import edu.rit.swen253.page.gpa.GPACalculatorPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ImpossibleCumulativeGPATest extends AbstractWebTest {
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
    @DisplayName("Navigate to the Tiger Center GPA Calculator page.")
    public void navigateToGPACalc(){
        homePage.selectGPACalculator();
        gpaPage = new GPACalculatorPage();

        assertEquals("https://tigercenter.rit.edu/tigerCenterApp/api/gpa-calc", gpaPage.getURL());
    }

    @Test
    @Order(3)
    @DisplayName("Add 2 courses to the term.")
    public void addCourses(){
        gpaPage.addCourse(2);
    }

    @Test
    @Order(4)
    @DisplayName("Fill in the course name, credits, grades, and past grade for the courses")
    public void populateCourses(){
        String[] names = {"Intro to Software Enginering", "Analysis of Alogrithms", "Software Testing"};
        String[] credits = {"3","3","3"};
        String[] grades = {"A","A","B+"};
        String[] pastGrades = {"F","F","F"};

        gpaPage.fillCourseInfo(names, credits, grades, pastGrades);
    }

    @Test
    @Order(5)
    @DisplayName("Enter the number of credits earned so far")
    public void populateEarnedCredits(){
        gpaPage.fillEarnedCredits("15");
    }

    @Test
    @Order(6)
    @DisplayName("Enter the current GPA of the student.")
    public void populateCurrentGPA(){
        gpaPage.fillCurrentGPA("3.5");
    }

    @Test
    @Order(7)
    @DisplayName("Calculate the impossible term and cumulative GPAs and ensure the notice pops up")
    public void calculateImpossibleGPA(){
        String[] gpas;
        gpas = gpaPage.calculateGPA();

        assertEquals("3.78", gpas[0]);
        assertEquals("4.92", gpas[1]);

        assertEquals("Notice: Having a GPA above 4.0 is not possible, please review your input to resolve any errors", gpaPage.getNotice());
    }
}
