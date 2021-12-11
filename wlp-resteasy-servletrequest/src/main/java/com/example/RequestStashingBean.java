package com.example;

import java.util.Objects;

import jakarta.enterprise.context.RequestScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RequestScoped
public class RequestStashingBean {

  private HttpServletRequest req;
  private HttpServletResponse resp;
  
  @Produces
  @JaxRsContext
  @RequestScoped
  public HttpServletRequest produceHttpServletRequest() {
      return Objects.requireNonNull(req, "Cannot produce HttpServletRequest");
  }

  @Produces
  @JaxRsContext
  @RequestScoped
  public HttpServletResponse produceHttpServletResponse() {
      return Objects.requireNonNull(resp, "Cannot produce HttpServletResponse");
  }
  
  public void setReq(HttpServletRequest req) {
    this.req = req;
  }
  public void setResp(HttpServletResponse resp) {
    this.resp = resp;
  }

}
