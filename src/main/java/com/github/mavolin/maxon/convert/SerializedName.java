package com.github.mavolin.maxon.convert;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * The interface {@code SerializedName} can be used to annotate {@link Enum Enums}, that are to be converted with the
 * {@link com.github.mavolin.maxon.converter.EnumConverter EnumConverter}. If an enum constant is annotated with this
 * annotation, the converter will use the provided name, instead of the exact name of the annotated field.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface SerializedName {


    /**
     * Gets the serialized name of the field.
     *
     * @return the name of the field after serialization
     */
    String value();


}
