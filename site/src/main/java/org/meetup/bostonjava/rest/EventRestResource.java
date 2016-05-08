package org.meetup.bostonjava.rest;



import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.ObjectBeanManagerException;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.hippoecm.repository.api.HippoWorkspace;
import org.hippoecm.repository.api.WorkflowException;
import org.hippoecm.repository.api.WorkflowManager;
import org.meetup.bostonjava.beans.EventDocument;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by macharya on 1/15/2016.
 */
@Path("/event/")
@Produces(MediaType.APPLICATION_JSON)
public class EventRestResource extends AbstractResource {
    private static Logger logger = LoggerFactory.getLogger(EventRestResource.class);


    /**
     * @param servletRequest
     * @param servletResponse
     * @param uriInfo
     * @param startDate       expected format MM-DD-YYYY
     * @param endDate         expected format MM-DD-YYYY
     * @return
     */
    @Path("/calendar")
    @GET
    public ResponseMessage calendar(@Context HttpServletRequest servletRequest,
                                    @Context HttpServletResponse servletResponse,
                                    @Context UriInfo uriInfo,
                                    @QueryParam("start") String startDate,
                                    @QueryParam("end") String endDate) throws ParseException, QueryException {

        SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
        Date sDate = format.parse(startDate);
        Date eDate = format.parse(endDate);

        HstRequestContext ctx = RequestContextProvider.get();
        HstQuery query = ctx.getQueryManager().createQuery(ctx.getSiteContentBaseBean(), EventDocument.class);
        //set max limit.
        query.setLimit(5000);

        Filter filter = query.createFilter();
        Calendar start = new GregorianCalendar();
        start.setTime(sDate);
        Calendar end = new GregorianCalendar();
        end.setTime(eDate);
        filter.addGreaterOrEqualThan("nejug:date", start);
        filter.addLessOrEqualThan("nejug:enddate", end);
        query.addOrderByDescending("nejug:date");
        query.setFilter(filter);
        HstQueryResult result = query.execute();
        HippoBeanIterator it = result.getHippoBeans();
        List<EventInfo> eventInfo = new ArrayList();
        while (it.hasNext()) {
            EventsDocument doc = (EventsDocument) it.nextHippoBean();
            HstLinkCreator linkCreator = ctx.getHstLinkCreator();
            HstLink link = linkCreator.create(doc, ctx);
            EventInfo info = new EventInfo(doc);
            String url = link.toUrlForm(ctx, false) + ".html";
            info.setLink(url.substring(url.indexOf("/events")));
            eventInfo.add(info);

        }


        ResponseMessage message = new ResponseMessage(HttpStatus.SC_OK, "Success");
        message.getData().put("eventInfo", eventInfo);
        return message;
    }




}
