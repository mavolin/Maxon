package com.github.mavolin.maxon;

import com.github.mavolin.maxon.parsing.JsonValueConverter;
import com.github.mavolin.maxon.datetime.DateFormatConfiguration;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.time.format.DateTimeFormatter;

/**
 * The {@code Maxon} class is the heart of this JSON converter. Using the {@code getAsJson} and {@code getFromJson}
 * methods, you can transform a Java {@link Object Object} to its JSON representation and vice versa.
 */
public class Maxon {


    /**
     * The default {@link JsonValueConverter JsonValueConverter}.
     */
    private static final JsonValueConverter jsonValueConverter = new JsonValueConverter();

    /**
     * The character used as whitespace when {@link PrintStyle#SINGLE_WHITESPACE PrintStyle.SINGLE_WHITESPACE} or
     * {@link PrintStyle#PRETTY_PRINTED PrintStyle.PRETTY_PRINTED} are selected.
     */
    private final char whitespaceChar;
    /**
     * The quantity of whitespace characters used in {@link PrintStyle#PRETTY_PRINTED PrintStyle.PrettyPrinted} mode,
     * as indent.
     */
    private final int whitespaceCharQty;
    /**
     * The style the JSON is being returned in.
     */
    private final PrintStyle printStyle;
    /**
     * Defines if {@code nulls} found in {@link com.github.mavolin.maxon.jsonvalues.JsonArray JsonArrays} and
     * {@link com.github.mavolin.maxon.jsonvalues.JsonObject JsonObjects} are to be serialized or not;
     */
    private final boolean ignoreNull;
    /**
     * Holds the {@link DateTimeFormatter DateTimeFormatter} for the various date and time object of
     * {@link java.time java.time}
     */
    private final DateFormatConfiguration dateFormatConfiguration;


    /**
     * Instantiates a new {@code Maxon} converter with default settings.
     */
    public Maxon() {

        this(new MaxonConfigurator());
    }

    Maxon(MaxonConfigurator maxonConfigurator) {

        this.whitespaceChar = maxonConfigurator.whitespaceChar;
        this.whitespaceCharQty = maxonConfigurator.whitespaceCharQty;
        this.printStyle = maxonConfigurator.printStyle;
        this.ignoreNull = maxonConfigurator.ignoreNull;
        this.dateFormatConfiguration = maxonConfigurator.dateFormatConfiguration;
    }


    public String getAsJson(Object source) {

        if (source == null)
            return "null";

        return null;
    }

    public String getAsJson(JsonValue jsonValue) {

        return null;
    }

    @SuppressWarnings("unchecked")
    public <T> T getFromJson(String source, Class<T> clazz) {

        if (clazz.isAssignableFrom(JsonValue.class)) {
            return jsonValueConverter.getFromJson(source, clazz);
        }
    }


}
