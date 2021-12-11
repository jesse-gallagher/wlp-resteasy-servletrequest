package com.example;

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
  public HttpServletRequest getRequest() {
      return req;
  }

  @Produces
  @JaxRsContext
  @RequestScoped
  public HttpServletResponse getResponse() {
      return resp;
  }
  
  public void setReq(HttpServletRequest req) {
    this.req = req;
  }
  public void setResp(HttpServletResponse resp) {
    this.resp = resp;
  }

}
