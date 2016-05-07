package org.meetup.bostonjava.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

@XmlRootElement(name = "venue")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "bostonjavameetup:Venue")
@Node(jcrType = "bostonjavameetup:Venue")
public class Venue extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:name")
    public String getName() {
        return getProperty("bostonjavameetup:name");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:address")
    public String[] getAddress() {
        return getProperty("bostonjavameetup:address");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:city")
    public String getCity() {
        return getProperty("bostonjavameetup:city");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:state")
    public String getState() {
        return getProperty("bostonjavameetup:state");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:zip")
    public String getZip() {
        return getProperty("bostonjavameetup:zip");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:phoneNumber")
    public String getPhoneNumber() {
        return getProperty("bostonjavameetup:phoneNumber");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:contactPerson")
    public String getContactPerson() {
        return getProperty("bostonjavameetup:contactPerson");
    }
}
