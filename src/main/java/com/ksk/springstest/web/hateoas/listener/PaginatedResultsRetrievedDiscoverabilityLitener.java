/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.listener;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.ksk.springstest.web.hateoas.events.PaginatedResultsRetrievedEvent;
import com.ksk.springstest.web.util.LinkUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

/**
 *
 * @author sivakrishna.k
 */

@Component
public class PaginatedResultsRetrievedDiscoverabilityLitener implements ApplicationListener<PaginatedResultsRetrievedEvent>{

    private final String PAGE = "page";
    private final String SIZE = "size";
    public PaginatedResultsRetrievedDiscoverabilityLitener() {
        super();
    }

    
    @Override
    public void onApplicationEvent(final PaginatedResultsRetrievedEvent event) {
        Preconditions.checkNotNull(event);
        
        addLinkHeaderOnPagedResourceReterival(event.getBuilder(), event.getResponse(), event.getPage(), event.getTotalPages(), event.getPageSize(), event.getClazz());
    }
    
    private void addLinkHeaderOnPagedResourceReterival(final UriComponentsBuilder uriBuilder, final HttpServletResponse response, final int page, final int totalPages, final int pageSize, final Class clazz){
        plural(uriBuilder, clazz);
        

        final StringBuilder linkHeader = new StringBuilder();
        if (hasNextPage(page, totalPages)) {
            final String nextPageUri = constructNextPageUri(uriBuilder, page, pageSize);
            linkHeader.append(LinkUtil.createLinkHeader(nextPageUri, LinkUtil.REL_NEXT));
        }
        
        if (hasPreviousPage(page)) {
            final String previousPageUri = constructPreviousPageUri(uriBuilder, page, pageSize);
            linkHeader.append(LinkUtil.createLinkHeader(previousPageUri, LinkUtil.REL_PREV));
        }
        
        if (hasFirstPage(page)) {
            final String firstPageUri = constructFirstPageUri(uriBuilder, pageSize);
            linkHeader.append(LinkUtil.createLinkHeader(firstPageUri, LinkUtil.REL_FIRST));
        }
        
        if (hasLasPage(page, totalPages)) {
            final String lastPageUri = constructLasPageUri(uriBuilder, totalPages, pageSize);
            linkHeader.append(LinkUtil.createLinkHeader(lastPageUri, LinkUtil.REL_LAST));
        }
        
        if (linkHeader.length() > 0) {
            response.addHeader(HttpHeaders.LINK, linkHeader.toString());
        }
        
    }
    
    final String constructNextPageUri(final UriComponentsBuilder uriBuilder, final int page, final int pageSize){
        return uriBuilder.replaceQueryParam(PAGE, page + 1).replaceQueryParam(SIZE, pageSize).build().encode().toUriString();
    }
    
    final String constructPreviousPageUri(final UriComponentsBuilder uriBuilder, final int page, final int pageSize){
        return uriBuilder.replaceQueryParam(PAGE, page -1).replaceQueryParam(SIZE, pageSize).build().encode().toUriString();
    }
    
    final String constructFirstPageUri(final UriComponentsBuilder uriBuilder, final int pageSize){
        return uriBuilder.replaceQueryParam(PAGE, 0).replaceQueryParam(SIZE, pageSize).build().encode().toUriString();
    }
    
    final String constructLasPageUri(final UriComponentsBuilder uriBuilder, final int totalPages, final int pageSize){
        return uriBuilder.replaceQueryParam(PAGE, totalPages).replaceQueryParam(SIZE, pageSize).build().encode().toUriString();
    }
    
    final boolean hasNextPage(final int page, final int totalPages){
        return page < totalPages - 1;
    }
    
    final boolean hasPreviousPage(final int page){
        return page > 0;
    }
    
    final boolean hasFirstPage(final int page){
        return hasPreviousPage(page);
    }
    
    final boolean hasLasPage(final int page, final int totalPages){
        return totalPages > 1 && hasNextPage(page, totalPages);
    }
    
    protected void plural(final UriComponentsBuilder builder, final Class clazz){
        final String resourceName = clazz.getSimpleName().toLowerCase() + "s";
        builder.path("/" + resourceName);
    }
}
