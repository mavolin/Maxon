package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.Maxon;

import java.text.DateFormat;
import java.time.format.DateTimeFormatter;

/**
 * The {@code DateFormatConfiguration} is used by {@link Maxon Maxon} to convert date and time objects of the new {@code
 * java.time} package to and from JSON.
 */
public class DateFormatConfiguration {


    /**
     * The {@link DateTimeFormatter DateTimeFormatter used for {@link java.time.LocalTime LocalTime} objects.}
     */
    DateTimeFormatter localTimeFormatter;
    /**
     * The {@link DateTimeFormatter DateTimeFormatter used for {@link java.time.LocalDate LocalDate} objects.}
     */
    DateTimeFormatter localDateFormatter;
    /**
     * The {@link DateTimeFormatter DateTimeFormatter used for {@link java.time.LocalDateTime LocalDateTime} objects.}
     */
    DateTimeFormatter localDateTimeFormatter;
    /**
     * The {@link DateTimeFormatter DateTimeFormatter used for {@link java.time.OffsetDateTime OffsetDateTime}
     * objects.}
     */
    DateTimeFormatter offsetDateTimeFormatter;
    /**
     * The {@link DateTimeFormatter DateTimeFormatter used for {@link java.time.ZonedDateTime ZonedDateTime} objects.}
     */
    DateTimeFormatter zonedDateTimeFormatter;
    /**
     * The {@link DateFormat DateFormat} used for {@link java.util.Date Date}.
     */
    DateFormat dateFormat;

    /**
     * Instantiates a new Date format configuration.
     */
    public DateFormatConfiguration() {


    }

    /**
     * Sets the {@link DateTimeFormatter DateTimeFormatter} for {@link java.time.LocalTime LocalTime} to the passed one
     * . If the passed {@link DateTimeFormatter DateTimeFormatter} is null, the default date format will be used.
     *
     * @param localTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     */
    public void setLocalTimeFormatter(DateTimeFormatter localTimeFormatter) {

        this.localTimeFormatter = localTimeFormatter;
    }

    /**
     * Sets the {@link DateTimeFormatter DateTimeFormatter} for {@link java.time.LocalDate LocalDate} to the passed one
     * . If the passed {@link DateTimeFormatter DateTimeFormatter} is null, the default date format will be used.
     *
     * @param localDateFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     */
    public void setLocalDateFormatter(DateTimeFormatter localDateFormatter) {

        this.localDateFormatter = localDateFormatter;
    }

    /**
     * Sets the {@link DateTimeFormatter DateTimeFormatter} for {@link java.time.LocalDateTime LocalDateTime} to the
     * passed one. If the passed {@link DateTimeFormatter DateTimeFormatter} is null, the default date format will be
     * used.
     *
     * @param localDateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     */
    public void setLocalDateTimeFormatter(DateTimeFormatter localDateTimeFormatter) {

        this.localDateTimeFormatter = localDateTimeFormatter;
    }

    /**
     * Sets the {@link DateTimeFormatter DateTimeFormatter} for {@link java.time.OffsetDateTime OffsetDateTime} to the
     * passe* on . If the passed {@link DateTimeFormatter DateTimeFormatter} is null, the default date format will be
     * used.
     *
     * @param offsetDateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     */
    public void setOffsetDateTimeFormatter(DateTimeFormatter offsetDateTimeFormatter) {

        this.offsetDateTimeFormatter = offsetDateTimeFormatter;
    }

    /**
     * Sets the {@link DateTimeFormatter DateTimeFormatter} for {@link java.time.ZonedDateTime ZonedDateTime} to the
     * passed on . If the passed {@link DateTimeFormatter DateTimeFormatter} is null, the default date format will be
     * used.
     *
     * @param zonedDateTimeFormatter
     *         the {@link DateTimeFormatter DateTimeFormatter}
     */
    public void setZonedDateTimeFormatter(DateTimeFormatter zonedDateTimeFormatter) {

        this.zonedDateTimeFormatter = zonedDateTimeFormatter;
    }

    /**
     * Sets the {@link DateFormat DateFormat} for {@link java.util.Date Date} to the passed on . If the passed {@link
     * DateFormat DateFormat} is null, the default date format will be used.
     *
     * @param dateFormat
     *         the {@link DateFormat DateFormat}
     */
    public void setDateFormatter(DateFormat dateFormat) {

        this.dateFormat = dateFormat;
    }


}
