package org.meetup.bostonjava.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import java.util.Calendar;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import java.util.List;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.content.beans.standard.HippoGalleryImageSet;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;

@XmlRootElement(name = "eventdocument")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "bostonjavameetup:EventDocument")
@Node(jcrType = "bostonjavameetup:EventDocument")
public class EventDocument extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:starteDate")
    public Calendar getStarteDate() {
        return getProperty("bostonjavameetup:starteDate");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:endDate")
    public Calendar getEndDate() {
        return getProperty("bostonjavameetup:endDate");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:title")
    public String getTitle() {
        return getProperty("bostonjavameetup:title");
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:hippostd_html")
    public HippoHtml getHippostd_html() {
        return getHippoHtml("bostonjavameetup:hippostd_html");
    }

    @HippoEssentialsGenerated(internalName = "bostonjavameetup:speaker")
    public List<HippoBean> getSpeaker() {
        return getLinkedBeans("bostonjavameetup:speaker", HippoBean.class);
    }

    @HippoEssentialsGenerated(internalName = "bostonjavameetup:images")
    public List<HippoGalleryImageSet> getImages() {
        return getLinkedBeans("bostonjavameetup:images",
                HippoGalleryImageSet.class);
    }

    @HippoEssentialsGenerated(internalName = "bostonjavameetup:venue")
    public HippoBean getVenue() {
        return getLinkedBean("bostonjavameetup:venue", HippoBean.class);
    }
}
