/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.meetup.bostonjava.components;

import org.hippoecm.hst.core.parameters.JcrPath;
import org.hippoecm.hst.core.parameters.Parameter;

/**
 * @author achxis-macbook
 */

public interface CalendarComponentInfo {
    static final String DEFAULT_PICKER_PATH = "/content/documents";

    @Parameter(name = "title", defaultValue = "Calendar", displayName = "Title")
    String getTitle();

    @Parameter(name = "width", defaultValue = "320", displayName = "Width")
    int getWidth();

    @Parameter(name = "height", defaultValue = "340", displayName = "Height")
    int getHeight();

    @Parameter(name = "maxYears", defaultValue = "20", displayName = "Maximum Number of Years")
    int getMaxYears();

    @Parameter(name = "futureYears", defaultValue = "4", displayName = "Future Years")
    int getFutureYears();

    @Parameter(name = "backgroundColor", defaultValue = "rgba(255,255,255,0.96)", displayName = "Background Color")
    String getBackgroundColor();

    @Parameter(name = "color", defaultValue = "#000", displayName = "Color")
    String getColor();

    @Parameter(name = "calendarClassName", defaultValue = "event-calendar", displayName = "Calendar Class Name")
    String getCalendarClassName();


    @Parameter(name = "padding", defaultValue = "0", displayName = "Padding")
    String getPadding();


    @Parameter(name = "dataRestEndpoint", defaultValue = "0", displayName = "Data Rest Endpoint")
    String getRestEndpoint();

    @Parameter(name = "infoDocument", required = false, displayName = "Info Document (Pick a Simple Document)")
    @JcrPath(isRelative = true, pickerInitialPath = DEFAULT_PICKER_PATH)
    String getInfoDocument();

}
