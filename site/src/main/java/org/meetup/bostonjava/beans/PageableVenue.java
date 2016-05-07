package org.meetup.bostonjava.beans;


import org.onehippo.cms7.essentials.components.paging.DefaultPagination;

import java.util.List;

/**
 * Used for ApiDocumentation - Swagger has difficulty with generics
 */
public class PageableVenue extends DefaultPagination<Venue> {
    public PageableVenue(List<Venue> items) {
        super(items);
    }
}
