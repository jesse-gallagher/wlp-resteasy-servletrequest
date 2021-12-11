package com.example;

import java.io.IOException;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;

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
