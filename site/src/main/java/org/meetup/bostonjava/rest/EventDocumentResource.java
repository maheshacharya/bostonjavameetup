/*
 * Copyright 2014 Hippo B.V. (http://www.onehippo.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.meetup.bostonjava.rest;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.meetup.bostonjava.beans.PageableEventDocument;
import org.onehippo.cms7.essentials.components.paging.Pageable;
import org.onehippo.cms7.essentials.components.rest.BaseRestResource;
import org.onehippo.cms7.essentials.components.rest.ctx.DefaultRestContext;
import org.meetup.bostonjava.beans.EventDocument;

/**
 * @version "$Id$"
 */

@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_FORM_URLENCODED})
@Path("/EventDocument/")
@Api(value = "/EventDocument", description = "EventDocument REST Operations")
public class EventDocumentResource extends BaseRestResource {


    @GET
    @Path("/")
    @ApiOperation(value = "Get all EventDocument beans.", response = PageableEventDocument.class)
    public Pageable<EventDocument> index(@Context HttpServletRequest request) {
        return findBeans(new DefaultRestContext(this, request), EventDocument.class);
    }


    @GET
    @Path("/page/{page}")
    @ApiOperation(value = "Get page of EventDocument beans.", response = PageableEventDocument.class)
    public Pageable<EventDocument> page(@Context HttpServletRequest request, @PathParam("page") int page) {
        return findBeans(new DefaultRestContext(this, request, page, DefaultRestContext.PAGE_SIZE), EventDocument.class);
    }


    @GET
    @Path("/page/{page}/{pageSize}")
    @ApiOperation(value = "Get page of EventDocument beans with specified page size.", response = PageableEventDocument.class)
    public Pageable<EventDocument> pageForSize(@Context HttpServletRequest request, @PathParam("page") int page, @PathParam("pageSize") int pageSize) {
        return findBeans(new DefaultRestContext(this, request, page, pageSize), EventDocument.class);
    }


}
