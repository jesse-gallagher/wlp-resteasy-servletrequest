package com.example;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletResponseWrapper;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.ext.MessageBodyWriter;

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
