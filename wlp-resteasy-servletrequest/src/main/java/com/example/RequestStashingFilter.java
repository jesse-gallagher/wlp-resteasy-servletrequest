package com.example;

import java.io.IOException;

import jakarta.annotation.Priority;
import jakarta.enterprise.inject.spi.CDI;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.container.PreMatching;
import jakarta.ws.rs.core.Context;

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
