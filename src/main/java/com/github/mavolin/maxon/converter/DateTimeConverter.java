package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import com.github.mavolin.maxon.jsonvalues.JsonValue;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Objects;

/**
 * The {@code DateTimeConverter} is used for converting the various date and time representing objects of Java 8's
 * {@link java.time java.time} package, namely {@link LocalTime LocalTime}, {@link LocalDate LocalDate}, {@link
 * java.time.LocalDateTime LocalDateTime}, {@link OffsetDateTime OffsetDateTime} and {@link ZonedDateTime
 * ZonedDateTime}, as well as {@link Date Date}.
 */
public class DateTimeConverter {


    /**
     * Converts the given {@link Object Object} to its JSON representation.
     *
     * @param source
     *         the {@link Object Object} that is to be converted
     * @param dateFormatConfiguration
     *         the {@link DateFormatConfiguration DateFormatConfiguration}
     *
     * @return the JSON representation
     */
    public JsonValue getAsJson(Object source, DateFormatConfiguration dateFormatConfiguration) {

        if (source instanceof Instant) {
            return this.getInstantAsJson((Instant) source);
        } else if (source instanceof LocalTime) {
            return this.getLocalTimeAsJson((LocalTime) source, dateFormatConfiguration.localTimeFormatter);
        } else if (source instanceof LocalDate) {
            return this.getLocalDateAsJson((LocalDate) source, dateFormatConfiguration.localDateFormatter);
        } else if (source instanceof LocalDateTime) {
            return this.getLocalDateTimeAsJson((LocalDateTime) source, dateFormatConfiguration.localDateTimeFormatter);
        } else if (source instanceof OffsetDateTime) {
            return this.getOffsetDateTimeAsJson((OffsetDateTime) source,
                                                dateFormatConfiguration.offsetDateTimeFormatter);
        } else if (source instanceof ZonedDateTime) {
            return this.getZonedDateTimeAsJson((ZonedDateTime) source, dateFormatConfiguration.zonedDateTimeFormatter);
        } else if (source instanceof Date) {
            return this.getDateAsJson((Date) source, dateFormatConfiguration.dateFormat);
        } else {
            throw new JsonParsingException(source.getClass().getName() + " is not convertible by this converter");
        }
    }

    /**
     * Parses the passed {@link JsonValue JsonValue} and returns an object of the type {@code T}. The JSON must be a
     * String holding the date/time's {@link String String} representation.
     *
     * @param <T>
     *         the type parameter
     * @param source
     *         the {@link JsonValue JsonValue}
     * @param clazz
     *         the {@link Class Class}
     * @param dateFormatConfiguration
     *         the {@link DateFormatConfiguration DateFormatConfiguration}
     *
     * @return the extracted {@link Object Object} of the type {@code T}.
     */
    @SuppressWarnings("unchecked")
    public <T> T getFromJson(JsonValue source, Class<T> clazz, DateFormatConfiguration dateFormatConfiguration) {

        if (!(source instanceof JsonPrimitive)) {
            throw new JsonParsingException("The provided JsonValue does not resemble a " + clazz.getName());
        }
        JsonPrimitive jsonPrimitive = (JsonPrimitive) source;

        if (jsonPrimitive.isNull()) {
            return null;
        }
        if (!jsonPrimitive.isString()) {
            throw new JsonParsingException("The provided JsonValue does not resemble a " + clazz.getName());
        }
        String date = jsonPrimitive.getAsString();


        if (clazz.isAssignableFrom(Instant.class)) {
            return (T) this.getInstantFromJson(date);
        } else if (clazz.isAssignableFrom(LocalTime.class)) {
            return (T) this.getLocalTimeFromJson(date, dateFormatConfiguration.localTimeFormatter);
        } else if (clazz.isAssignableFrom(LocalDate.class)) {
            return (T) this.getLocalDateFromJson(date, dateFormatConfiguration.localDateFormatter);
        } else if (clazz.isAssignableFrom(LocalDateTime.class)) {
            return (T) this.getLocalDateTimeFromJson(date, dateFormatConfiguration.localDateTimeFormatter);
        } else if (clazz.isAssignableFrom(OffsetDateTime.class)) {
            return (T) this.getOffsetDateTimeFromJson(date, dateFormatConfiguration.offsetDateTimeFormatter);
        } else if (clazz.isAssignableFrom(ZonedDateTime.class)) {
            return (T) this.getZonedDateTimeFromJson(date, dateFormatConfiguration.zonedDateTimeFormatter);
        } else if (clazz.isAssignableFrom(Date.class)) {
            return (T) this.getDateFromJson(date, dateFormatConfiguration.dateFormat);
        } else {
            throw new JsonParsingException(clazz.getName() + " is not convertible by this converter");
        }
    }


    /**
     * Converts the {@link Instant Instant} to its JSON representation and returns it.
     *
     * @param instant
     *         the {@link Instant Instant} that is to be converted
     *
     * @return the {@link Instant Instant} in its JSON form
     */
    private JsonPrimitive getInstantAsJson(Instant instant) {

        return new JsonPrimitive(instant.toString());
    }

    /**
     * Converts the {@link LocalTime LocalTime} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link LocalTime LocalTime} that is to be converted
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter} used create a {@link String String} representation of the
     *         {@link LocalTime LocalTime}
     *
     * @return the {@link LocalTime LocalTime} in its JSON form
     */
    private JsonPrimitive getLocalTimeAsJson(LocalTime date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return new JsonPrimitive(date.toString());
        } else {
            return new JsonPrimitive(dateTimeFormatter.format(date));
        }
    }

    /**
     * Converts the {@link LocalDate LocalDate} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link LocalDate LocalDate} that is to be converted
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter} used create a {@link String String} representation of the
     *         {@link LocalDate LocalDate}
     *
     * @return the {@link LocalDate LocalDate} in its JSON form
     */
    private JsonPrimitive getLocalDateAsJson(LocalDate date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return new JsonPrimitive(date.toString());
        } else {
            return new JsonPrimitive(dateTimeFormatter.format(date));
        }
    }

    /**
     * Converts the {@link LocalDateTime LocalDateTime} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link LocalDateTime LocalDateTime} that is to be converted
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter} used create a {@link String String} representation of the
     *         {@link LocalDateTime LocalDateTime}
     *
     * @return the {@link LocalDateTime LocalDateTime} in its JSON form
     */
    private JsonPrimitive getLocalDateTimeAsJson(LocalDateTime date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return new JsonPrimitive(date.toString());
        } else {
            return new JsonPrimitive(dateTimeFormatter.format(date));
        }
    }

    /**
     * Converts the {@link OffsetDateTime OffsetDateTime} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link OffsetDateTime OffsetDateTime} that is to be converted
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter} used create a {@link String String} representation of the
     *         {@link OffsetDateTime OffsetDateTime}
     *
     * @return the {@link OffsetDateTime OffsetDateTime} in its JSON form
     */
    private JsonPrimitive getOffsetDateTimeAsJson(OffsetDateTime date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return new JsonPrimitive(date.toString());
        } else {
            return new JsonPrimitive(dateTimeFormatter.format(date));
        }
    }

    /**
     * Converts the {@link ZonedDateTime ZonedDateTime} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link ZonedDateTime ZonedDateTime} that is to be converted
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter} used create a {@link String String} representation of the
     *         {@link ZonedDateTime ZonedDateTime}
     *
     * @return the {@link ZonedDateTime ZonedDateTime} in its JSON form
     */
    private JsonPrimitive getZonedDateTimeAsJson(ZonedDateTime date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return new JsonPrimitive(date.toString());
        } else {
            return new JsonPrimitive(dateTimeFormatter.format(date));
        }
    }

    /**
     * Converts the {@link Date Date} to its JSON representation and returns it.
     *
     * @param date
     *         the {@link Date Date} that is to be converted
     * @param dateFormat
     *         the {@link DateFormat DateFormat} used create a {@link String String} representation of the {@link Date
     *         Date}
     *
     * @return the {@link Date Date} in its JSON form
     */
    private JsonPrimitive getDateAsJson(Date date, DateFormat dateFormat) {

        return new JsonPrimitive(Objects.requireNonNullElseGet(dateFormat, SimpleDateFormat::new).format(date));
    }


    /**
     * Extracts a {@link Instant Instant} from the passed {@link String String}.
     *
     * @param date
     *         the date
     *
     * @return the extracted {@link Instant Instant}
     */
    private Instant getInstantFromJson(String date) {

        return Instant.parse(date);
    }

    /**
     * Extracts a {@link LocalTime LocalTime} from the passed {@link String String}. If the passed * {@link
     * DateTimeFormatter DateTimeFormatter} is {@code null}, the default formatter will be used.
     *
     * @param date
     *         the date
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     *
     * @return the extracted {@link LocalTime LocalTime}
     */
    private LocalTime getLocalTimeFromJson(String date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return LocalTime.parse(date);
        } else {
            return LocalTime.parse(date, dateTimeFormatter);
        }
    }

    /**
     * Extracts a {@link LocalDate LocalDate} from the passed {@link String String}. If the passed {@link
     * DateTimeFormatter DateTimeFormatter} is {@code null}, the default formatter will be used.
     *
     * @param date
     *         the date
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DatetimeFormatter}
     *
     * @return the extracted {@link LocalDate LocalDate}
     */
    private LocalDate getLocalDateFromJson(String date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return LocalDate.parse(date);
        } else {
            return LocalDate.parse(date, dateTimeFormatter);
        }
    }

    /**
     * Extracts a {@link LocalDateTime LocalDateTime} from the passed {@link String String}. If the passed {@link
     * DateTimeFormatter DateTimeFormatter} is {@code null}, the default formatter will be used.
     *
     * @param date
     *         the date
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DatetimeFormatter}
     *
     * @return the extracted {@link LocalDateTime LocalDateTime}
     */
    private LocalDateTime getLocalDateTimeFromJson(String date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return LocalDateTime.parse(date);
        } else {
            return LocalDateTime.parse(date, dateTimeFormatter);
        }
    }

    /**
     * Extracts a {@link OffsetDateTime OffsetDateTime} from the passed {@link String String}. If the passed {@link
     * DateTimeFormatter DateTimeFormatter} is {@code null}, the default formatter will be used.
     *
     * @param date
     *         the date
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DatetimeFormatter}
     *
     * @return the extracted {@link OffsetDateTime OffsetDateTime}
     */
    private OffsetDateTime getOffsetDateTimeFromJson(String date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return OffsetDateTime.parse(date);
        } else {
            return OffsetDateTime.parse(date, dateTimeFormatter);
        }
    }

    /**
     * Extracts a {@link ZonedDateTime ZonedDateTime} from the passed {@link String String}. If the passed {@link
     * DateTimeFormatter DateTimeFormatter} is {@code null}, the default formatter will be used.
     *
     * @param date
     *         the date
     * @param dateTimeFormatter
     *         the {@link DateTimeFormatter DatetimeFormatter}
     *
     * @return the extracted {@link ZonedDateTime ZonedDateTime}
     */
    private ZonedDateTime getZonedDateTimeFromJson(String date, DateTimeFormatter dateTimeFormatter) {

        if (dateTimeFormatter == null) {
            return ZonedDateTime.parse(date);
        } else {
            return ZonedDateTime.parse(date, dateTimeFormatter);
        }
    }

    /**
     * Extracts a {@link Date Date} from the passed {@link String String}. If the passed {@link DateFormat DateFormat }
     * is {@code null} the default format will be used
     *
     * @param date
     *         the date
     * @param dateFormat
     *         the {@link DateFormat DateFormat}
     *
     * @return the extracted {@link Date Date}
     */
    private Date getDateFromJson(String date, DateFormat dateFormat) {

        try {
            return Objects.requireNonNullElseGet(dateFormat, SimpleDateFormat::new).parse(date);
        } catch (ParseException e) {
            throw new JsonParsingException("Unable to parse date", e);
        }
    }


}
