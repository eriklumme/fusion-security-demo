package com.example.application;

import com.vaadin.flow.server.VaadinRequest;
import com.vaadin.flow.server.connect.Endpoint;

import javax.annotation.security.RolesAllowed;

@Endpoint
public class MyEndpoint {

    @RolesAllowed("USER")
    public String test() {
        return "Works";
    }
}
