package com.github.mavolin.maxon.converter;

import com.github.mavolin.maxon.exceptions.JsonParsingException;
import com.github.mavolin.maxon.jsonvalues.JsonArray;
import com.github.mavolin.maxon.jsonvalues.JsonPrimitive;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.chrono.IsoChronology;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.ResolverStyle;

import static org.junit.jupiter.api.Assertions.*;

class DateTimeConverterTest {


    @Test
    void getAsJsonDefaultConfigTest() {

        DateTimeConverter dateTimeConverter = new DateTimeConverter(new DateFormatConfiguration());

        Instant instant = Instant.parse("2019-08-20T19:32:52.09Z");
        LocalTime localTime = LocalTime.of(19, 32, 52, 90000000);
        LocalDate localDate = LocalDate.of(2019, 8, 20);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 20, 19, 32, 52, 90000000);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(2019, 8, 20, 19, 32, 52, 90000000, ZoneOffset.of("Z"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2019, 8, 20, 19, 32, 52, 90000000, ZoneId.of("Z"));
        Date date = Date.valueOf(localDate);

        JsonPrimitive instantJson = new JsonPrimitive(instant.toString());
        JsonPrimitive localTimeJson = new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_TIME.format(localTime));
        JsonPrimitive localDateJson = new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE.format(localDate));
        JsonPrimitive localDateTimeJson =
                new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime));
        JsonPrimitive offsetDateTimeJson = new JsonPrimitive(
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));
        JsonPrimitive zonedDateTimeJson = new JsonPrimitive(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime));

        assertEquals(instantJson, dateTimeConverter.getAsJson(instant));
        assertEquals(localTimeJson, dateTimeConverter.getAsJson(localTime));
        assertEquals(localDateJson, dateTimeConverter.getAsJson(localDate));
        assertEquals(localDateTimeJson, dateTimeConverter.getAsJson(localDateTime));
        assertEquals(offsetDateTimeJson, dateTimeConverter.getAsJson(offsetDateTime));
        assertEquals(zonedDateTimeJson, dateTimeConverter.getAsJson(zonedDateTime));
        assertThrows(JsonParsingException.class, () ->
                dateTimeConverter.getAsJson(""));
    }

    @Test
    void getAsJsonDifferentConfigTest() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH mm ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH mm ss, dd MM yy");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ss, dd MM yy");

        DateFormatConfiguration dateFormatConfiguration = new DateFormatConfiguration();

        dateFormatConfiguration.setDateFormatter(simpleDateFormat);
        dateFormatConfiguration.setLocalTimeFormatter(timeFormatter);
        dateFormatConfiguration.setLocalDateFormatter(dateFormatter);
        dateFormatConfiguration.setLocalDateTimeFormatter(dateTimeFormatter);
        dateFormatConfiguration.setOffsetDateTimeFormatter(dateTimeFormatter);
        dateFormatConfiguration.setZonedDateTimeFormatter(dateTimeFormatter);

        DateTimeConverter dateTimeConverter = new DateTimeConverter(dateFormatConfiguration);

        LocalTime localTime = LocalTime.of(19, 32, 52);
        LocalDate localDate = LocalDate.of(2019, 8, 20);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 20, 19, 32, 52);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.of("Z"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Z"));
        Date date = Date.valueOf(localDate);

        JsonPrimitive localTimeJson = new JsonPrimitive(timeFormatter.format(localTime));
        JsonPrimitive localDateJson = new JsonPrimitive(dateFormatter.format(localDate));
        JsonPrimitive localDateTimeJson =
                new JsonPrimitive(dateTimeFormatter.format(localDateTime));
        JsonPrimitive offsetDateTimeJson = new JsonPrimitive(
                dateTimeFormatter.format(offsetDateTime));
        JsonPrimitive zonedDateTimeJson = new JsonPrimitive(
                dateTimeFormatter.format(zonedDateTime));
        JsonPrimitive dateJson = new JsonPrimitive(new SimpleDateFormat().format(date));

        assertEquals(localTimeJson, dateTimeConverter.getAsJson(localTime));
        assertEquals(localDateJson, dateTimeConverter.getAsJson(localDate));
        assertEquals(localDateTimeJson, dateTimeConverter.getAsJson(localDateTime));
        assertEquals(offsetDateTimeJson, dateTimeConverter.getAsJson(offsetDateTime));
        assertEquals(zonedDateTimeJson, dateTimeConverter.getAsJson(zonedDateTime));
        assertThrows(JsonParsingException.class, () ->
                dateTimeConverter.getAsJson(""));
    }

    @Test
    void getFromJsonDefaultConfigTest() {

        DateTimeConverter dateTimeConverter = new DateTimeConverter(new DateFormatConfiguration());

        Instant instant = Instant.parse("2019-08-20T19:32:52.09Z");
        LocalTime localTime = LocalTime.of(19, 32, 52, 90000000);
        LocalDate localDate = LocalDate.of(2019, 8, 20);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 20, 19, 32, 52, 90000000);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(2019, 8, 20, 19, 32, 52, 90000000, ZoneOffset.of("Z"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(2019, 8, 20, 19, 32, 52, 90000000, ZoneId.of("Z"));
        Date date = Date.valueOf(localDate);

        JsonPrimitive instantJson = new JsonPrimitive(instant.toString());
        JsonPrimitive localTimeJson = new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_TIME.format(localTime));
        JsonPrimitive localDateJson = new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE.format(localDate));
        JsonPrimitive localDateTimeJson =
                new JsonPrimitive(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(localDateTime));
        JsonPrimitive offsetDateTimeJson = new JsonPrimitive(
                DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(offsetDateTime));
        JsonPrimitive zonedDateTimeJson = new JsonPrimitive(
                DateTimeFormatter.ISO_ZONED_DATE_TIME.format(zonedDateTime));

        assertEquals(instant, dateTimeConverter.getFromJson(instantJson, Instant.class));
        assertEquals(localTime, dateTimeConverter.getFromJson(localTimeJson, LocalTime.class));
        assertEquals(localDate, dateTimeConverter.getFromJson(localDateJson, LocalDate.class));
        assertEquals(localDateTime, dateTimeConverter.getFromJson(localDateTimeJson, LocalDateTime.class));
        assertEquals(offsetDateTime, dateTimeConverter.getFromJson(offsetDateTimeJson, OffsetDateTime.class));
        assertEquals(zonedDateTime, dateTimeConverter.getFromJson(zonedDateTimeJson, ZonedDateTime.class));
        assertNull(dateTimeConverter.getFromJson(JsonPrimitive.NULL, LocalTime.class));
        assertThrows(JsonParsingException.class, () ->
                dateTimeConverter.getFromJson(new JsonArray(), LocalTime.class));
        assertThrows(JsonParsingException.class, () ->
                dateTimeConverter.getFromJson(new JsonPrimitive(123), LocalTime.class));
        assertThrows(JsonParsingException.class, () ->
                dateTimeConverter.getFromJson(new JsonPrimitive("dsa"), String.class));
    }

    @Test
    void getFromJsonDifferentConfigTest() {

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH mm ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MM yy");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH mm ss, dd MM yy");
        DateTimeFormatter dateTimeZoneFormatter = DateTimeFormatter.ofPattern("HH mm ss, dd MM yy X");
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH mm ss, dd MM yy");

        DateFormatConfiguration dateFormatConfiguration = new DateFormatConfiguration();

        dateFormatConfiguration.setDateFormatter(simpleDateFormat);
        dateFormatConfiguration.setLocalTimeFormatter(timeFormatter);
        dateFormatConfiguration.setLocalDateFormatter(dateFormatter);
        dateFormatConfiguration.setLocalDateTimeFormatter(dateTimeFormatter);
        dateFormatConfiguration.setOffsetDateTimeFormatter(dateTimeZoneFormatter);
        dateFormatConfiguration.setZonedDateTimeFormatter(dateTimeZoneFormatter);

        DateTimeConverter dateTimeConverter = new DateTimeConverter(dateFormatConfiguration);

        LocalTime localTime = LocalTime.of(19, 32, 52);
        LocalDate localDate = LocalDate.of(2019, 8, 20);
        LocalDateTime localDateTime = LocalDateTime.of(2019, 8, 20, 19, 32, 52);
        OffsetDateTime offsetDateTime = OffsetDateTime.of(localDateTime, ZoneOffset.of("Z"));
        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, ZoneId.of("Z"));
        Date date = Date.valueOf(localDate);

        JsonPrimitive localTimeJson = new JsonPrimitive(timeFormatter.format(localTime));
        JsonPrimitive localDateJson = new JsonPrimitive(dateFormatter.format(localDate));
        JsonPrimitive localDateTimeJson =
                new JsonPrimitive(dateTimeFormatter.format(localDateTime));
        JsonPrimitive offsetDateTimeJson = new JsonPrimitive(
                dateTimeZoneFormatter.format(offsetDateTime));
        JsonPrimitive zonedDateTimeJson = new JsonPrimitive(
                dateTimeZoneFormatter.format(zonedDateTime));
        JsonPrimitive dateJson = new JsonPrimitive(new SimpleDateFormat().format(date));

        assertEquals(localTime, dateTimeConverter.getFromJson(localTimeJson, LocalTime.class));
        assertEquals(localDate, dateTimeConverter.getFromJson(localDateJson, LocalDate.class));
        assertEquals(localDateTime, dateTimeConverter.getFromJson(localDateTimeJson, LocalDateTime.class));
        assertEquals(offsetDateTime, dateTimeConverter.getFromJson(offsetDateTimeJson, OffsetDateTime.class));
        assertEquals(zonedDateTime, dateTimeConverter.getFromJson(zonedDateTimeJson, ZonedDateTime.class));
    }


}