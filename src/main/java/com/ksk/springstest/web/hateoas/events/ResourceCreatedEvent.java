/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.events;

import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author sivakrishna.k
 */
public class ResourceCreatedEvent extends ApplicationEvent{
    
    private final HttpServletResponse response;
    private final long idOfNewResource;
    
    public ResourceCreatedEvent(final Object source, final HttpServletResponse response, final long idOfNewResource) {
        super(source);
        
        this.response = response;
        this.idOfNewResource = idOfNewResource;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public long getIdOfNewResource() {
        return idOfNewResource;
    }

}
