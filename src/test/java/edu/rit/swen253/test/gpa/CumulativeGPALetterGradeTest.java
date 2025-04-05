package edu.rit.swen253.test.gpa;

import edu.rit.swen253.page.gpa.GPACalculatorPage;
import edu.rit.swen253.page.tiger.TigerCenterHomePage;
import edu.rit.swen253.test.AbstractWebTest;
import edu.rit.swen253.utils.BrowserWindow;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CumulativeGPALetterGradeTest extends AbstractWebTest {
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
    @DisplayName("Add 3 courses to the term.")
    public void addCourses(){
        gpaPage.addCourse(3);
    }

    @Test
    @Order(4)
    @DisplayName("Fill in the course name, credits, grades, and past grade for the courses")
    public void populateCourses(){
        String[] names = {"Intro to Software Enginering", "Analysis of Alogrithms", "Trends in Software Engineering", "Software Testing"};
        String[] credits = {"3","4","3","2"};
        String[] grades = {"A","A","B","A-"};
        String[] pastGrades = {"","","",""};

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
    @DisplayName("Calculate the term and cumulative GPAs")
    public void calculateGPA(){
        String[] gpas;
        gpas = gpaPage.calculateGPA();

        assertEquals("3.70", gpas[0]);
        assertEquals("3.59", gpas[1]);
    }

}
