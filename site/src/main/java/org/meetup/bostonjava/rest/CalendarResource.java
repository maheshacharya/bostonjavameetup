package org.meetup.bostonjava.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.http.HttpStatus;
import org.hippoecm.hst.container.RequestContextProvider;
import org.hippoecm.hst.content.beans.query.HstQuery;
import org.hippoecm.hst.content.beans.query.HstQueryResult;
import org.hippoecm.hst.content.beans.query.exceptions.QueryException;
import org.hippoecm.hst.content.beans.query.filter.Filter;
import org.hippoecm.hst.content.beans.standard.HippoBeanIterator;
import org.hippoecm.hst.core.linking.HstLink;
import org.hippoecm.hst.core.linking.HstLinkCreator;
import org.hippoecm.hst.core.request.HstRequestContext;
import org.hippoecm.hst.jaxrs.services.AbstractResource;
import org.meetup.bostonjava.beans.EventDocument;

import org.meetup.bostonjava.beans.PageablePerson;
import org.meetup.bostonjava.rest.model.EventInfo;
import org.meetup.bostonjava.rest.model.ResponseMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


@Path("/Calendar/")
@Produces(MediaType.APPLICATION_JSON)
@Api(value = "/Calendar", description = "Calendar REST Operations")
public class CalendarResource extends AbstractResource {
    private static Logger logger = LoggerFactory.getLogger(CalendarResource.class);
    private static int MAX_RESULT_LIMIT = 500;


    /**
     * @param servletRequest
     * @param servletResponse
     * @param uriInfo
     * @param startDate       expected format MM-DD-YYYY
     * @param endDate         expected format MM-DD-YYYY
     * @return
     */
    @Path("/events")
    @GET
    @ApiOperation(value = "Get event list for a given date/time period.", response = ResponseMessage.class)
    public ResponseMessage events(@Context HttpServletRequest servletRequest,
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
        query.setLimit(MAX_RESULT_LIMIT);

        Filter filter = query.createFilter();
        Calendar start = new GregorianCalendar();
        start.setTime(sDate);
        Calendar end = new GregorianCalendar();
        end.setTime(eDate);
        filter.addGreaterOrEqualThan("bostonjavameetup:startDate", start);
        filter.addLessOrEqualThan("bostonjavameetup:endDate", end);
        query.addOrderByDescending("bostonjavameetup:startDate");
        query.setFilter(filter);
        HstQueryResult result = query.execute();
        HippoBeanIterator it = result.getHippoBeans();
        List<EventInfo> eventInfo = new ArrayList();
        while (it.hasNext()) {
            EventDocument doc = (EventDocument) it.nextHippoBean();
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
