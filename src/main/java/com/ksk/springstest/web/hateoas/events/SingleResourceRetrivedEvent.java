/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ksk.springstest.web.hateoas.events;

import java.io.Serializable;
import javax.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationEvent;

/**
 *
 * @author sivakrishna.k
 */
public class SingleResourceRetrivedEvent extends ApplicationEvent {

    private final HttpServletResponse response;

    public SingleResourceRetrivedEvent(final Object source, final HttpServletResponse response) {
        super(source);

        this.response = response;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

}
