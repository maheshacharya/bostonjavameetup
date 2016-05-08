package org.meetup.bostonjava.rest.model;

import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.meetup.bostonjava.beans.EventDocument;
import org.meetup.bostonjava.beans.Person;


import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class EventInfo implements Serializable {
    private String title;
    private String startDate;
    private String endDate;
    private String uuid;
    private String startTime;
    private String endTime;
    private String type;
    private List<String> speakers = new ArrayList();
    private String link;

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public EventInfo(EventDocument doc) {
        this.title = doc.getTitle();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm a");
        try {
            this.startDate = dateFormat.format(new Date(doc.getStartDate().getTimeInMillis()));
            this.startTime = timeFormat.format(new Date(doc.getStartDate().getTimeInMillis()));
            this.endDate = dateFormat.format(new Date(doc.getEndDate().getTimeInMillis()));
            this.endTime = timeFormat.format(new Date(doc.getEndDate().getTimeInMillis()));
        } catch (Exception e) {
            //date values could be null, that is okay
        }
        type = doc.getTitle();
        uuid = doc.getIdentifier();
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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
