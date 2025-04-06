package edu.rit.swen253.page.hoursAndLocations;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

import static edu.rit.swen253.utils.HtmlUtils.ANCHOR_FINDER;

public class LocationResultView {

    private final DomElement viewContainer;

    public LocationResultView(DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public DomElement getViewContainer() {
        return viewContainer;
    }

    public String getName()
    {
        return viewContainer.findChildBy(By.className("student-affairsTabEateryName")).getText();
    }

    public String getDescription()
    {
        return viewContainer.getParent().findChildBy(By.className("mat-tab-body-wrapper")).getText();
    }

    public void openLocationMap()
    {
        viewContainer.findChildBy(ANCHOR_FINDER).click();
    }
}
