package org.meetup.bostonjava.beans;

import org.onehippo.cms7.essentials.dashboard.annotations.HippoEssentialsGenerated;
import org.hippoecm.hst.content.beans.Node;
import org.hippoecm.hst.content.beans.standard.HippoDocument;
import org.hippoecm.hst.content.beans.standard.HippoHtml;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import org.onehippo.cms7.essentials.components.rest.adapters.HippoHtmlAdapter;

@XmlRootElement(name = "person")
@XmlAccessorType(XmlAccessType.NONE)
@HippoEssentialsGenerated(internalName = "bostonjavameetup:Person")
@Node(jcrType = "bostonjavameetup:Person")
public class Person extends BaseDocument {
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:name")
    public String getName() {
        return getProperty("bostonjavameetup:name");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:email")
    public String getEmail() {
        return getProperty("bostonjavameetup:email");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:title")
    public String getTitle() {
        return getProperty("bostonjavameetup:title");
    }

    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:company")
    public String getCompany() {
        return getProperty("bostonjavameetup:company");
    }

    @XmlJavaTypeAdapter(HippoHtmlAdapter.class)
    @XmlElement
    @HippoEssentialsGenerated(internalName = "bostonjavameetup:bio")
    public HippoHtml getBio() {
        return getHippoHtml("bostonjavameetup:bio");
    }
}
