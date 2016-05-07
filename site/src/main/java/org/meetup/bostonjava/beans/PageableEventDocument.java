package org.meetup.bostonjava.beans;


import io.swagger.annotations.ApiModelProperty;
import org.onehippo.cms7.essentials.components.paging.DefaultPagination;

import java.util.List;

/**
 * Used for ApiDocumentation - Swagger has difficulty with generics
 */
public class PageableEventDocument extends DefaultPagination<EventDocument> {
    public PageableEventDocument(List<EventDocument> items) {
        super(items);
    }
}
