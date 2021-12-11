# wlp-resteasy-servletrequest

This project demonstrates an exception that can occur when using Open Liberty 22.0.0.1-beta with Jakarta EE 9 features.

Specifically, the trouble occurs when:

* An HttpServletRequest object that is stored in a CDI @RequestScoped bean by a pre-matching a `ContainerRequestFilter`
* It is then injected into a `MessageBodyWriter` by way of a custom `@JaxRsContext` annotation that allows for `@Context HttpServletRequest req`
* That request is then passed in to a `RequestDispatcher` looked up by the writer

This manifests in a stack trace like:

```
org.jboss.resteasy.spi.UnhandledException: java.lang.RuntimeException: SRV.8.2: RequestWrapper objects must extend ServletRequestWrapper or HttpServletRequestWrapper
  at org.jboss.resteasy.core.ExceptionHandler.handleException(ExceptionHandler.java:381)
  at [internal classes]
Caused by: java.lang.RuntimeException: SRV.8.2: RequestWrapper objects must extend ServletRequestWrapper or HttpServletRequestWrapper
  at com.ibm.wsspi.webcontainer.util.ServletUtil.unwrapRequest(ServletUtil.java:89)
  at [internal classes]
  at com.example.CustomClassWriter.writeTo(CustomClassWriter.java:45)
  at com.example.CustomClassWriter.writeTo(CustomClassWriter.java:1)
  at org.jboss.resteasy.core.interception.jaxrs.ServerWriterInterceptorContext.lambda$writeTo$1(ServerWriterInterceptorContext.java:79)
  ... 1 more
````

The specific trouble is that the `HttpServletRequest` instance provided to the `MessageBodyWriter` in this case is a Proxy object created by RESTEasy, and is not an `HttpServletRequestWrapper`. WLP's `ServletUtil` then tries to unwrap it down specifically to an `IExtendedRequest` instance, but it can't.

### Reproduction

The problem can be reproduced in this project by:

* Go into the `wlp-resteasy-servletrequest` module
* Run `mvn liberty:run`
* Visit http://localhost:9080/app/test

As a counterpoint, doing the same process in `wlp-resteasy-servletrequest-javax` - which uses Jakarta EE 8 - will render the contents of the JSP loaded by the writer properly.