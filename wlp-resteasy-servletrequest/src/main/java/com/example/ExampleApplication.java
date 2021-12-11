package com.example;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("app")
public class ExampleApplication extends Application {
  @Override
  public Set<Class<?>> getClasses() {
    return new HashSet<>(Arrays.asList(
        TestResource.class,
        RequestStashingFilter.class,
        CustomClassWriter.class
    ));
  }
}
