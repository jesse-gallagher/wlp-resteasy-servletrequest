package com.example;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("test")
public class TestResource {
  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Object get() throws ServletException, IOException {
    return new CustomClass();
  }

}
