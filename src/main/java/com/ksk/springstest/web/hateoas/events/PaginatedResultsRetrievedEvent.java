/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.events;

import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author sivakrishna.k
 */
public class PaginatedResultsRetrievedEvent<T extends Serializable> extends ApplicationEvent {

    private final UriComponentsBuilder builder;
    private final HttpServletResponse response;
    private final int page;
    private final int totalPages;
    private final int pageSize;

    public PaginatedResultsRetrievedEvent(final Class<T> clazz, final UriComponentsBuilder builder, final HttpServletResponse response, final int page, final int totalPages, final int pageSize) {
        super(clazz);

        this.builder = builder;
        this.response = response;
        this.page = page;
        this.totalPages = totalPages;
        this.pageSize = pageSize;
    }

    public UriComponentsBuilder getBuilder() {
        return builder;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public int getPage() {
        return page;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public int getPageSize() {
        return pageSize;
    }

    /**
     * The object on which the Event initially occurred.
     *
     * @return The object on which the Event initially occurred.
     */
    @SuppressWarnings("unchecked")
    public final Class<T> getClazz() {
        return (Class<T>) getSource();
    }

}
