package edu.rit.swen253.page.AppNavigation;

import edu.rit.swen253.utils.DomElement;
import org.openqa.selenium.By;

public class NavigationColumn {

    private final DomElement viewContainer;

    public NavigationColumn(DomElement viewContainer) {
        this.viewContainer = viewContainer;
    }

    public void navigateToGPACalculator()
    {
        viewContainer.findChildrenBy(By.tagName("button")).get(6).click();
    }

    public void navigateToClassCompare()
    {
        viewContainer.findChildrenBy(By.tagName("button")).get(7).click();
    }
}
