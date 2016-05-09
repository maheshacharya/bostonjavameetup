package org.meetup.bostonjava.components;

import org.hippoecm.hst.component.support.bean.BaseHstComponent;
import org.hippoecm.hst.configuration.hosting.Mount;
import org.hippoecm.hst.content.beans.standard.HippoBean;
import org.hippoecm.hst.core.component.HstRequest;
import org.hippoecm.hst.core.component.HstResponse;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.parameters.ParametersInfo;


@ParametersInfo(type = CalendarComponentInfo.class)
public class CalendarComponent extends BaseHstComponent {

    @Override
    public void doBeforeRender(HstRequest request, HstResponse response) {
        super.doBeforeRender(request, response);

        CalendarComponentInfo info = getComponentParametersInfo(request);
        if (info != null) {
            request.setAttribute("info", info);
            request.setAttribute("now", System.currentTimeMillis());
        }
    }


}
