package edu.rit.swen253.page.gpa;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import java.util.List;

/**
 * The GPA Calculator page of the TigerCenter Angular web application.
 *
 * @author - Jarred Reepmeyer
 */
public class GPACalculatorPage extends AbstractAngularPage {

    private static final By ADD_BUTTON = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[5]/div[3]/button");
    private static final By CALC_GPA_BUTTON = By.className("primaryButton");
    private static final By GPA_RESULTS = By.className("results");
    private static final By GRADING_POLICY = By.cssSelector("div.disclaimerMessage > a");
    private static final By COURSES = By.className("courseRow");
    private static final By EARNED_CREDITS = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[1]/div/input");
    private static final By CURRENT_GPA = By.xpath("/html/body/div[1]/app-root/div[2]/mat-sidenav-container[2]/mat-sidenav-content/div[2]/gpa-calc/div/div/div/div[2]/div[2]/div/input");
    private static final By NOTICE = By.className("alert");

    public GPACalculatorPage() {
        super("gpa-calc");
    }

    /**
     * Action for pushing the "Add" button which adds more course fields to the page
     *
     * @param numCourses - The number of courses to add (this translates to how many times to push the "Add" button
     */
    public void addCourse(int numCourses){
        DomElement addButton = findOnPage(ADD_BUTTON);

        for(int x = 0; x < numCourses; x++){
            addButton.click();
        }
    }

    /**
     * Action for pushing the "Calculate GPA" Button and retrieving the values calculated
     *
     * @return - The values for the term and cumulative GPAs
     */
    public String[] calculateGPA(){
        String [] gpas = new String[2];

        DomElement calcGPAButton = findOnPage(CALC_GPA_BUTTON);
        calcGPAButton.click();

        List<DomElement> gpa_results = findAllOnPage(GPA_RESULTS);

        for(int x = 0; x < 2; x++){
            gpas[x] = gpa_results.get(x).getText();
        }

        return gpas;
    }

    /**
     * Action for clicking the grading policy link
     *
     */
    public void clickGradingPolicy(){
        DomElement grading_policy = findOnPage(GRADING_POLICY);
        grading_policy.click();
    }

    /**
     * Fills in each course field with its respective values
     *
     * @param names - the names of the courses
     * @param credits - the number of credits each course has
     * @param grades - the grades received in each course
     * @param pastGrades - the grades received the first time taking each course (Null if first time)
     */
    public void fillCourseInfo(String[] names, String[] credits, String[] grades, String[] pastGrades){
        List<DomElement> courses = findAllOnPage(COURSES);


        for(int x=0; x < names.length; x++){
            DomElement courseName = courses.get(x).findChildBy(By.cssSelector("input:not([type])"));
            DomElement courseCredits = courses.get(x).findChildBy(By.cssSelector("input[type='number']"));
            List<DomElement> courseGrades = courses.get(x).findChildBy(By.cssSelector("select.gradeDropDown:not([title])")).findChildrenBy(By.tagName("option"));
            List<DomElement> coursePastGrades = courses.get(x).findChildBy(By.cssSelector("select.gradeDropDown[title='Not applicable for Graduate Students']")).findChildrenBy(By.tagName("option"));

            courseName.click();
            courseName.sendKeys(names[x]);

            courseCredits.click();
            courseCredits.sendKeys(Keys.BACK_SPACE);
            courseCredits.sendKeys(credits[x]);


            for(DomElement option : courseGrades) {
                if (option.getText().equals(grades[x])) {
                    option.click();
                    break;
                }
            }

            for(DomElement option : coursePastGrades) {
                if(pastGrades[x] == ""){
                    coursePastGrades.get(0).click();
                    break;
                }
                if (option.getText().equals(pastGrades[x])) {
                    option.click();
                    break;
                }
            }
        }
    }

    /**
     * Fill in the field for the number of credits the student has earned so far
     *
     * @param credits - The number of credits the student has earned
     */
    public void fillEarnedCredits(String credits){
        DomElement earnedCredits = findOnPage(EARNED_CREDITS);
        earnedCredits.click();
        earnedCredits.sendKeys(Keys.BACK_SPACE);
        earnedCredits.sendKeys(credits);
    }

    /**
     * Fill in the field for the student's current GPA
     *
     * @param currentGPA - the student's current GPA
     */
    public void fillCurrentGPA(String currentGPA){
        DomElement currentGPAField = findOnPage(CURRENT_GPA);
        currentGPAField.click();
        currentGPAField.sendKeys(Keys.BACK_SPACE);
        currentGPAField.sendKeys(currentGPA);
    }

    /**
     * Grab the notice that pops up on the page when an impossible GPA is calculated
     *
     * @return the text inside the notice
     */
    public String getNotice(){
        DomElement notice = findOnPage(NOTICE);
        return notice.getText();
    }

    /**
     * Get the URL of the current page
     *
     * @return - the URL of the current page
     */
    public String getURL(){
        return SeleniumUtils.getDriver().getCurrentUrl();
    }
}
