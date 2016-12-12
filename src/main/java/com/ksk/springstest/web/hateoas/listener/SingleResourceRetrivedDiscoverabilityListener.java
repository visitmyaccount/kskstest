/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.listener;

import com.google.common.base.Preconditions;
import com.google.common.net.HttpHeaders;
import com.ksk.springstest.web.hateoas.events.SingleResourceRetrivedEvent;
import com.ksk.springstest.web.util.LinkUtil;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author sivakrishna.k
 */
@Component
class SingleResourceRetrivedDiscoverabilityListener implements ApplicationListener<SingleResourceRetrivedEvent>{

    @Override
    public void onApplicationEvent(final SingleResourceRetrivedEvent event) {
        Preconditions.checkNotNull(event);
        
        addLinkHeaderOnSingleResourceRetrived(event.getResponse());
    }
    
    void addLinkHeaderOnSingleResourceRetrived(final HttpServletResponse response ){
        
        final String requestedUri = ServletUriComponentsBuilder.fromCurrentRequestUri().build().toUri().toASCIIString();
        final int positionOfLastSlash = requestedUri.lastIndexOf("/");
        final String uriForResourceCreation = requestedUri.substring(0, positionOfLastSlash);
        
        final String linkHeaderValue = LinkUtil.createLinkHeader(requestedUri, LinkUtil.REL_COLLECTION);
        
        response.addHeader(HttpHeaders.LINK, linkHeaderValue);
    }
    
}
