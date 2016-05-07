package org.meetup.bostonjava.beans;

import org.hippoecm.hst.content.beans.Node;
import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "video")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "bostonjavameetup:video")
@Node(jcrType = "bostonjavameetup:video")
public class Video extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:title")
    public String getTitle() {
        return getProperty("bostonjavameetup:title");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:description")
    public String getDescription() {
        return getProperty("bostonjavameetup:description");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:link")
    public String getLink() {
        return getProperty("bostonjavameetup:link");
    }
}
