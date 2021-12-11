package com.example;

import java.util.Objects;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
