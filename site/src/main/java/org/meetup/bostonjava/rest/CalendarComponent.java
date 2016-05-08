package org.meetup.bostonjava.rest;

import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.parameters.ParametersInfo;

/**
 * Created by macharya on 2/20/2016.
 */
@ParametersInfo(type = CalendarInfo.class)
public class CalendarComponent extends BaseComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);
        CalendarInfo info = getComponentParametersInfo(request);
        request.setAttribute("info", info);
        String path = info.getInfoDocument();
        HippoBean bean = request.getRequestContext().getSiteContentBaseBean().getBean(path);
        request.setAttribute("infoDoc", bean);
        HstLinkCreator linkCreator = request.getRequestContext().getHstLinkCreator();
        Mount mt = request.getRequestContext().getResolvedMount().getMount();
        HstLink link = linkCreator.create(info.getRestEndpoint(), mt , false);
        String url = link.toUrlForm(request.getRequestContext(), true);
        request.setAttribute("dataRestEndpoint", url);
        request.setAttribute("now", new java.util.Date().getTime());
    }


}
