package edu.rit.swen253.page.hoursAndLocations;

import edu.rit.swen253.page.AbstractAngularPage;
import edu.rit.swen253.page.AppNavigation.NavigationColumn;
import edu.rit.swen253.utils.BrowserType;
import edu.rit.swen253.utils.DomElement;
import edu.rit.swen253.utils.HtmlUtils;
import edu.rit.swen253.utils.SeleniumUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class HoursAndLocationsPage extends AbstractAngularPage {

    private static final By MAINPAGE = By.cssSelector("#content-sidenav-container > mat-sidenav-content > div.widthFix > hours-and-locations");
    private static final By DINING = By.cssSelector("#mat-tab-content-0-0 > div > dining");
    private static final By LABS = By.cssSelector("#mat-tab-content-0-1 > div > labs");
    private static final By STUDENTAFFAIRS = By.cssSelector("#mat-tab-content-0-2 > div > student-affairs");
    private DomElement mainPage;
    private DomElement dining;
    private DomElement labs;
    private DomElement studentaffairs;

    public HoursAndLocationsPage()
    {
        super("hours-and-locations");
        mainPage = findOnPage(MAINPAGE);
        dining = findOnPage(DINING);
    }

    private static final By SEARCH = By.cssSelector("#mat-input-0");

    public List<LocationResultView> diningSearch(String phrase)
    {
        DomElement searchBody = findOnPage(SEARCH);

        searchBody.sendKeys(phrase);

        return dining.findChildBy(By.cssSelector("#mat-tab-content-0-0 > div >" +
                " dining > div.container-fluid.greyBorder > div > div:nth-child(12) > " +
                "div.col-xs-12.col-sm-9.col-md-9.col-lg-9.diningTabVerticalLine.diningTabLeftMarginMobile"))
                .findChildrenBy(By.className("diningTabEateryName"))
                .stream().map(LocationResultView::new).toList();
    }

    public void clickComputerLabTab()
    {
        WebElement computerLabTab = mainPage.findChildBy(By.cssSelector("#mat-tab-label-0-1 > div"));
        computerLabTab.click();
        labs = findOnPage(LABS);
    }

    public void clickStudentAffairsTab()
    {
        WebElement studentAffairsTab = mainPage.findChildBy(By.cssSelector("#mat-tab-label-0-2 > div"));
        studentAffairsTab.click();
        studentaffairs = findOnPage(STUDENTAFFAIRS);
    }

    public List<LocationResultView> clickMacFilter()
    {
        labs.findChildBy(By.cssSelector("#mat-tab-content-0-1 > div > labs > " +
            "div > div > div.col-xs-12.col-sm-3.col-md-3.col-lg-3"))
            .findChildrenBy(By.tagName("mat-checkbox")).getFirst().findChildBy(By.className("mat-checkbox-inner-container"))
                .click(SeleniumUtils.getShortWait(), ExpectedConditions::elementToBeClickable);

        return labs.findChildBy(By.cssSelector(("#mat-tab-content-0-1 > div > labs > div > " +
                "div > div.col-xs-12.col-sm-9.col-md-9.col-lg-9.labsTabVerticalLine")))
            .findChildrenBy(By.className("clickHelper"))
            .stream().map(LocationResultView::new).toList();
    }

    public LocationResultView viewStudentAffairOffice(String location)
    {
        List<LocationResultView> locations = studentaffairs.findChildBy(By.cssSelector("#mat-tab-content-0-2 " +
                "> div > student-affairs > div > div > " +
                "div.col-xs-12.col-sm-8.col-md-9.col-lg-9.student-affairsTabVerticalLine"))
                .findChildrenBy(By.className("clickHelper"))
                .stream().map(LocationResultView::new).toList();

        LocationResultView redBarn = null;
        for (LocationResultView locationResult : locations)
        {
            String name = locationResult.getName();
            if(name.equals(location))
            {
                redBarn = locationResult;
                scrollIntoView(redBarn.getViewContainer());
                redBarn.getViewContainer().click();
                break;
            }
        }
        return redBarn;
    }

    private void scrollIntoView(DomElement viewContainer) {
        if (!scrolledAlready) {
            switch (BrowserType.discover()) {
                case CHROME, EDGE -> viewContainer.scrollIntoView();
                case FIREFOX, SAFARI -> SeleniumUtils.makeAction().scrollByAmount(0, 2000).perform();
            }
            scrolledAlready = true;
        }
    }
    private static boolean scrolledAlready = false;

    public NavigationColumn getSideNavColumn()
    {
        DomElement navCol = angularView.getParent().getParent()
                .findChildBy(By.cssSelector("#content-sidenav-container > mat-sidenav-content " +
                "> div.tc-side-nav > div > div:nth-child(4)"));
        return new NavigationColumn(navCol);
    }
}
