package org.d13.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD) // Use the annotation on the method.
@Retention(RetentionPolicy.SOURCE) // It is available only during Source processing but unavailable at runtime.
public @interface BuildProperty2{
}