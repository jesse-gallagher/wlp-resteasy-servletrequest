package com.example;

import java.io.IOException;

import javax.annotation.Priority;
import javax.enterprise.inject.spi.CDI;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;

@PreMatching
@Priority(0) // very early
public class RequestStashingFilter implements ContainerRequestFilter {
  @Context
  HttpServletRequest req;
  
  @Context
  HttpServletResponse resp;

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    RequestStashingBean bean = CDI.current().select(RequestStashingBean.class).get();
    bean.setReq(req);
    bean.setResp(resp);
  }

}
