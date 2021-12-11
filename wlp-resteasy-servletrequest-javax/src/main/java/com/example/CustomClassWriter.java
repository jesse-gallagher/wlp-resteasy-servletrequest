package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;

@Produces(MediaType.TEXT_PLAIN)
public class CustomClassWriter implements MessageBodyWriter<CustomClass> {


  @Context
  private HttpServletRequest req;

  @Context
  private HttpServletResponse resp;

  @Override
  public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
    return CustomClass.class.equals(type);
  }

  @Override
  public void writeTo(CustomClass t, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
      MultivaluedMap<String, Object> httpHeaders, OutputStream entityStream) throws IOException, WebApplicationException {
    ServletContext context = req.getServletContext();
    System.out.println("req: " + req.getClass());
    
    RequestDispatcher rd = context.getRequestDispatcher("/WEB-INF/hello.jsp");
    try {
      rd.forward(new HttpServletRequestWrapper(req), new HttpServletResponseWrapper(resp));
    } catch (ServletException e) {
      throw new WebApplicationException(e);
    }
  }

}
