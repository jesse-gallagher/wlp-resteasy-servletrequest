package com.example;

import java.io.IOException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

@Path("test")
public class TestResource {
  @Inject
  RequestStashingBean bean;
  
  @Context
  HttpServletRequest req;
  
  @Context
  HttpServletResponse resp;
  
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Object get() throws ServletException, IOException {
    return new CustomClass();
  }

}
