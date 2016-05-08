package org.meetup.bostonjava.rest;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
    private static SimpleDateFormat format = new SimpleDateFormat("MM-dd-yyyy");
    private static String START_DATE = "bostonjavameetup:startDate";
    private static String END_DATE = "bostonjavameetup:endDate";
    private static String SUCCESS_MESSAGE = "Success";
    private static String EVENT_INFO = "eventInfo";
    private static String EVENT_SITEMAP_SUFFIX = ".html";


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
                                  @DefaultValue("01-01-2016") @ApiParam(value = "expected format MM-dd-yyyy") @QueryParam("start") String startDate,
                                  @DefaultValue("06-01-2016") @ApiParam(value = "expected format MM-dd-yyyy") @QueryParam("end") String endDate) throws ParseException, QueryException {


        HstRequestContext ctx = RequestContextProvider.get();

        //Create HST Query
        HstQuery query = ctx.getQueryManager().createQuery(ctx.getSiteContentBaseBean(), EventDocument.class);

        //set max limit.
        query.setLimit(MAX_RESULT_LIMIT);

        //Query Filer
        Filter filter = query.createFilter();

        //add date/time filters
        filter.addGreaterOrEqualThan(START_DATE, getCalendar(startDate));
        filter.addLessOrEqualThan(END_DATE, getCalendar(endDate));

        //sort order
        query.addOrderByDescending(START_DATE);

        //set filter
        query.setFilter(filter);

        //execute the query
        HstQueryResult result = query.execute();

        //get EventInfo list
        List<EventInfo> eventInfo = getEventInfoList(result, ctx);

        ResponseMessage message = new ResponseMessage(HttpStatus.SC_OK, SUCCESS_MESSAGE);
        message.getData().put(EVENT_INFO, eventInfo);
        return message;
    }


    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static Calendar getCalendar(String date) throws ParseException {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(format.parse(date));
        return calendar;
    }

    /**
     * @param result
     * @param ctx
     * @return
     */
    public List<EventInfo> getEventInfoList(HstQueryResult result, HstRequestContext ctx) {

        List<EventInfo> eventInfo = new ArrayList();
        HippoBeanIterator it = result.getHippoBeans();
        while (it.hasNext()) {
            EventDocument doc = (EventDocument) it.nextHippoBean();
            HstLinkCreator linkCreator = ctx.getHstLinkCreator();
            HstLink link = linkCreator.create(doc, ctx);
            EventInfo info = new EventInfo(doc);
            info.setLink(link.getPath() + EVENT_SITEMAP_SUFFIX);
            eventInfo.add(info);

        }
        return eventInfo;

    }


}
