package com.github.mavolin.maxon.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The {@code Converts} annotation is used to specify the {@link Class Classes} that can be converted by a {@link
 * JsonConverter JsonConverter}, that is annotated with this annotation. Note that this annotation is required in order
 * to register a {@link JsonConverter JsonConverter} at a {@link com.github.mavolin.maxon.Maxon Maxon}.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Converts {


    /**
     * The {@link Class Classes} that can be converted with {@link JsonConverter JsonConverter}.
     *
     * @return the {@link Class Classes} that can be converted with the {@link JsonConverter JsonConverter}
     */
    Class[] value();


}
