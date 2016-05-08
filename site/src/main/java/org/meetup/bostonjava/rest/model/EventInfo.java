package org.meetup.bostonjava.rest.model;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.meetup.bostonjava.beans.EventDocument;
import org.meetup.bostonjava.beans.Person;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class EventInfo implements Serializable {
    private SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
    private SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");

    private String title;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private List<String> speakers = new ArrayList();
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @param doc
     */
    public EventInfo(EventDocument doc) {
        title = doc.getTitle();
        startDate = dateFormat.format(doc.getStartDate().getTime());
        startTime = timeFormat.format(doc.getStartDate().getTime());
        endDate = dateFormat.format(doc.getEndDate().getTime());
        endTime = timeFormat.format(doc.getEndDate().getTime());
        for (HippoBean spkrs : doc.getSpeaker()) {
            speakers.add(((Person) spkrs).getName());
        }

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }


    public List<String> getSpeakers() {
        return speakers;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }



}
