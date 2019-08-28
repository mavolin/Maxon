package com.github.mavolin.maxon.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <p>
 *     The {@code DeserializationConstructor} annotation is used to specify which constructor shall be used, while
 *     deserializing a
 *     JSON
 *     object. If the {@link Object Object's} constructor is not annotated with a {@code DeserializationConstructor}
 *     annotation, maxon will attempt to use the no-arg constructor, to construct the {@link Object Object}.
 * </p>
 * <p>
 *     To perform deserialization on a non-no-arg class, you have to specify the names
 *     corresponding to the values that are to be passed to the annotated constructor.
 * </p>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.CONSTRUCTOR)
public @interface DeserializationConstructor {


    /**
     * The names associated with the values that are to be passed to the annotated constructor.
     *
     * @return names associated with the values that are to be passed to the annotated constructor
     */
    String[] value();


}
