package com.github.mavolin.maxon;

import com.github.mavolin.maxon.converter.DateFormatConfiguration;
import com.github.mavolin.maxon.utils.PrintStyle;

import java.time.format.DateTimeFormatter;

public class MaxonConfigurator {


    /**
     * The character used as whitespace when {@link PrintStyle#SINGLE_WHITESPACE PrintStyle.SINGLE_WHITESPACE} or
     * {@link PrintStyle#PRETTY_PRINTED PrintStyle.PRETTY_PRINTED} are selected.
     */
    char whitespaceChar;
    /**
     * The quantity of whitespace characters used in {@link PrintStyle#PRETTY_PRINTED PrintStyle.PrettyPrinted} mode,
     * as indent.
     */
    int whitespaceCharQty;
    /**
     * The style the JSON is being returned.
     */
    PrintStyle printStyle;
    /**
     * Defines if {@code nulls} found in {@link com.github.mavolin.maxon.jsonvalues.JsonArray JsonArrays} and
     * {@link com.github.mavolin.maxon.jsonvalues.JsonObject JsonObjects} are to be serialized or not;
     */
    boolean ignoreNull;
    /**
     * Holds the {@link DateTimeFormatter DateTimeFormatter} for the various date and time object of
     * {@link java.time java.time}
     */
    DateFormatConfiguration dateFormatConfiguration;


    /**
     * Instantiates a new {@code MaxonConfigurator}.
     */
    public MaxonConfigurator() {

        this.whitespaceChar = ' ';
        this.whitespaceCharQty = 4;
        this.printStyle = PrintStyle.SINGLE_WHITESPACE;
        this.ignoreNull = false;
        this.dateFormatConfiguration = new DateFormatConfiguration();
    }

    /**
     * <p>
     *     If set to true, the default whitespace character will be a tabulator instead of a space.
     * </p>
     * <p>
     *     The default setting is {@code false}
     * </p>
     *
     * @param bool
     *         {@code true} to use tabulator as whitespace character; {@code false} to use a space
     */
    public void toggleTabWhitespace(boolean bool) {

        if (bool) {
            this.whitespaceChar = '\t';
        } else {
            this.whitespaceChar = ' ';
        }
    }

    /**
     * <p>
     *     Sets the quantity of whitespace characters used for one indent when in {@link PrintStyle#PRETTY_PRINTED
     *     PrintStyle.PRETTY_PRINTED} mode.
     * </p>
     * <p>
     *     The default setting is {@code 4}.
     * </p>
     *
     * @param qty
     *         the quantity of whitespace characters in one indent
     */
    public void setWhitespaceQuantity(int qty) {

        this.whitespaceCharQty = qty;
    }

    /**
     * <p>
     *     Sets the {@link PrintStyle PrintStyle} used to generate JSON {@link String Strings} to the passed
     *     {@link PrintStyle
     *     PrintStyle}.
     * </p>
     * <p>
     *     The default setting is {@link PrintStyle#SINGLE_WHITESPACE PrintStyle.SINGLE_WHITESPACE}.
     * </p>
     *
     * @param printStyle
     *         the {@link PrintStyle PrintStyle}
     */
    public void setPrintStyle(PrintStyle printStyle) {

        this.printStyle = printStyle;
    }

    /**
     * <p>
     *     Choose whether or not {@code nulls} found in {@link com.github.mavolin.maxon.jsonvalues.JsonArray
     *     JsonArrays} and {@link com.github.mavolin.maxon.jsonvalues.JsonObject JsonObjects} are to be ignored or not
     * </p>
     * <p>
     *     The default setting is {@code false}.
     * </p>
     *
     * @param ignoreNull
     *         {@code true} if {@code nulls} should be ignored; {@code false} otherwise
     */
    public void setIgnoreNull(boolean ignoreNull) {

        this.ignoreNull = ignoreNull;
    }

    /**
     * <p>
     *     Sets the {@link DateFormatConfiguration DateFormatConfiguration} which defines how date and time objects
     *     shall be parsed.
     * </p>
     * <p>
     *     The default value is {@code new DateFormatConfiguration}.
     * </p>
     *
     * @param dateFormatConfiguration
     *         the date format configuration
     */
    public void setDateFormatConfiguration(DateFormatConfiguration dateFormatConfiguration) {

        this.dateFormatConfiguration = dateFormatConfiguration;
    }

    public Maxon buildMaxon() {

        return new Maxon(this);
    }


}
