package com.github.mavolin.maxon.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to specify whether or not an exception should be thrown, in case a field is not present in
 * a JSON, that is being deserialized. If a class is annotated with this annotation, the setting will be used
 * globally and can only be overwritten by an annotated field. If the class itself is not annotated, the global
 * setting will be set to {@code false} ignoring all missing field and using there default values.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.FIELD})
public @interface AbortOnMissingField {


    /**
     * Defines whether or not to abort in case there is no mapping for a field.
     *
     * @return {@code true} if to abort; {@code false} otherwise
     */
    boolean value();


}
