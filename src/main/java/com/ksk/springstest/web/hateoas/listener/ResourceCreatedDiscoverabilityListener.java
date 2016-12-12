/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.listener;

import com.google.common.base.Preconditions;
import com.ksk.springstest.web.hateoas.events.ResourceCreatedEvent;
import java.net.URI;
import javax.servlet.http.HttpServletResponse;
import org.apache.http.HttpHeaders;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 *
 * @author sivakrishna.k
 * 
 */
@Component
class ResourceCreatedDiscoverabilityListener implements ApplicationListener<ResourceCreatedEvent>{

    @Override
    public void onApplicationEvent(ResourceCreatedEvent event) {
        Preconditions.checkNotNull(event);
        
        addLinkHeaderOnResourceCreation(event.getResponse(), event.getIdOfNewResource());
    }
    
    void addLinkHeaderOnResourceCreation(final HttpServletResponse response, final long idOfNewResource){
        final URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{idOfNewResource}").buildAndExpand(idOfNewResource).toUri();
        response.setHeader(HttpHeaders.LOCATION, uri.toASCIIString());
    } 
    
}
