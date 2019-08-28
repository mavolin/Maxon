package com.github.mavolin.maxon;

import com.github.mavolin.maxon.convert.Converts;
import com.github.mavolin.maxon.convert.JsonConverter;
import com.github.mavolin.maxon.convert.ObjectConverter;
import com.github.mavolin.maxon.converter.*;
import com.github.mavolin.maxon.exceptions.MissingAnnotationException;
import com.github.mavolin.maxon.jsonvalues.*;
import com.github.mavolin.maxon.parsing.JsonValueConverter;
import com.github.mavolin.maxon.utils.JsonPrinter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * The {@code Maxon} class is the heart of this JSON converter. Using the {@code getAsJson} and {@code getFromJson}
 * methods, you can transform a Java {@link Object Object} to its JSON representation and vice versa.
 */
public class Maxon {


    /**
     * The {@link JsonValueConverter JsonValueConverter}.
     */
    private static final JsonValueConverter jsonValueConverter = new JsonValueConverter();
    /**
     * The {@link EnumConverter EnumConverter}.
     */
    private static final EnumConverter enumConverter = new EnumConverter();

    /**
     * The character used as whitespace when {@link PrintStyle#SINGLE_WHITESPACE PrintStyle.SINGLE_WHITESPACE} or {@link
     * PrintStyle#PRETTY_PRINTED PrintStyle.PRETTY_PRINTED} are selected.
     */
    private final char whitespaceChar;
    /**
     * The quantity of whitespace characters used in {@link PrintStyle#PRETTY_PRINTED PrintStyle.PrettyPrinted} mode, as
     * indent.
     */
    private final int whitespaceCharQty;
    /**
     * The style the JSON is being returned in.
     */
    private final PrintStyle printStyle;
    /**
     * Defines if {@code nulls} found in {@link com.github.mavolin.maxon.jsonvalues.JsonArray JsonArrays} and {@link
     * com.github.mavolin.maxon.jsonvalues.JsonObject JsonObjects} are to be serialized or not;
     */
    private final boolean ignoreNull;

    /**
     * Holds the different {@link ObjectConverter ObjectConverter} and is called when a conversion is needed by one of
     * those {@link ObjectConverter JsonParsers}.
     */
    private final ObjectConverterManager objectConverterManager = new ObjectConverterManager();

    /**
     * Stores the {@link JsonConverter JsonConverter} that belongs to a specific {@link Class Class}.
     */
    private final Map<Class<?>, JsonConverter> converter = new HashMap<>();


    /**
     * Instantiates a new {@code Maxon} converter with default settings.
     */
    public Maxon() {

        this(new MaxonConfigurator());
    }

    /**
     * Instantiates a new {@code Maxon} with the configuration of the pass {@link MaxonConfigurator MaxonConfigurator}.
     *
     * @param maxonConfigurator
     *         the {@link MaxonConfigurator MaxonConfigurator}
     */
    Maxon(MaxonConfigurator maxonConfigurator) {

        this.whitespaceChar = maxonConfigurator.whitespaceChar;
        this.whitespaceCharQty = maxonConfigurator.whitespaceCharQty;
        this.printStyle = maxonConfigurator.printStyle;
        this.ignoreNull = maxonConfigurator.ignoreNull;

        this.registerConverter(new PrimitivesConverter());
        this.registerConverter(new AtomicObjectConverter());
        this.registerConverter(new DateTimeConverter(maxonConfigurator.dateFormatConfiguration));
    }


    /**
     * Returns the JSON representation of the passed {@link Object Object}.
     *
     * @param source
     *         the {@link Object Object} that is to be converted.
     *
     * @return the {@link Object Object} in its JSON representation
     */
    public String getAsJson(Object source) {

        return this.getAsJson(this.getAsJsonValue(source));
    }

    /**
     * Converts the passed {@link JsonValue JsonValue} to its {@link String String} JSON representation.
     *
     * @param jsonValue
     *         the {@link JsonValue JsonValue} that is to be converted
     *
     * @return the {@link String String} JSON representation of the {@link JsonValue}
     */
    public String getAsJson(JsonValue jsonValue) {

        if (jsonValue instanceof JsonPrimitive) {
            return JsonPrinter.printJsonPrimitive((JsonPrimitive) jsonValue);
        } else if (jsonValue instanceof JsonArray) {
            return JsonPrinter.printJsonArray((JsonArray) jsonValue, this.whitespaceChar, this.whitespaceCharQty,
                                              this.ignoreNull, this.printStyle);
        } else if (jsonValue instanceof JsonObject) {
            return JsonPrinter.printJsonObject((JsonObject) jsonValue, this.whitespaceChar, this.whitespaceCharQty,
                                               this.ignoreNull, this.printStyle);
        } else {
            throw new IllegalArgumentException("The passed JsonValue is not supported");
        }
    }

    /**
     * Converts the passed {@link String String} to an {@link Object Object} of the specified {@link Class Class} and
     * returns it.
     *
     * @param <T>
     *         the type parameter
     * @param source
     *         the JSON {@link String String}
     * @param clazz
     *         the desired {@link Class Class} of the output {@link Object Object}
     *
     * @return the converted {@link Object Object}
     */
    public <T> T getFromJson(String source, Class<T> clazz) {

        JsonValue jsonValue = jsonValueConverter.getFromJson(source);

        if (new JsonElement(jsonValue).isNull()) {
            return null;
        }

        if (JsonValue.class.isAssignableFrom(clazz)) {
            return jsonValueConverter.getFromJson(source, clazz);
        } else if (this.converter.containsKey(clazz)) {
            return this.converter.get(clazz).getFromJson(jsonValue, clazz);
        } else if (Enum.class.isAssignableFrom(clazz)) {
            return enumConverter.getFromJson(jsonValue, clazz);
        } else {
            throw new UnsupportedOperationException("The provided Object cannot be converted by Maxon");
        }
    }

    /**
     * Registers a {@link ObjectConverter ObjectConverter} associated with the specified {@link Class Class}. If there
     * is already a {@link JsonConverter JsonConverter} or a {@link ObjectConverter ObjectConverter} registered the
     * specified {@link Class Class}, then the converter for that {@link Class Class} will be overwritten.
     *
     * @param <T>
     *         the type parameter
     * @param objectConverter
     *         the {@link ObjectConverter ObjectConverter}
     * @param clazz
     *         the {@link Class Class} the {@link ObjectConverter ObjectConverter} can convert to and from
     */
    public <T> void registerParser(ObjectConverter<T> objectConverter, Class<T> clazz) {

        this.objectConverterManager.registerParser(objectConverter, clazz);

        this.converter.put(clazz, this.objectConverterManager);
    }

    /**
     * Registers a {@link JsonConverter JsonConverter}. If there is already a {@link JsonConverter JsonConverter} or a
     * {@link ObjectConverter ObjectConverter} registered for one of the convertible {@link Class Classes}, then the
     * converter for that {@link Class Class} will be overwritten.
     *
     * @param converter
     *         the {@link JsonConverter JsonConverter}
     */
    public void registerConverter(JsonConverter converter) {

        this.converterCheck(converter);

        Class converterClass = converter.getClass();

        Converts converts = (Converts) converterClass.getAnnotation(Converts.class);

        for (Class convertibleClass : converts.value()) {
            this.converter.put(convertibleClass, converter);
        }
    }

    /**
     * Gets the specified {@link Object Object} as a {@link JsonValue JsonVaue}.
     *
     * @param source
     *         the source
     *
     * @return the converted {@link Object Object}
     */
    private JsonValue getAsJsonValue(Object source) {

        if (source == null) {
            return JsonPrimitive.NULL;
        }

        Class sourceClass = source.getClass();

        if (this.converter.containsKey(sourceClass)) {
            return this.converter.get(sourceClass).getAsJson(source);
        } else if (source instanceof Enum) {
            return enumConverter.getAsJson(source);
        } else {
            throw new UnsupportedOperationException("The provided Object cannot be converted by Maxon");
        }
    }

    /**
     * Converts the passed Java array to a {@link JsonArray JsonArray}.
     *
     * @param source
     *         the {@link Object Object} array
     *
     * @return the converted {@link JsonArray JsonArray}
     */
    private JsonArray getArrayAsJson(Object source) {

        JsonArray jsonArray = new JsonArray();

        if (source.getClass().isArray()) {
            Class<?> componentType = source.getClass().getComponentType();

            if (componentType.isPrimitive()) {
                if (boolean.class.isAssignableFrom(componentType)) {
                    boolean[] booleans = (boolean[]) source;

                    for (boolean bool : booleans) {
                        jsonArray.add(this.getArrayAsJson(bool));
                    }
                } else if (char.class.isAssignableFrom(componentType)) {
                    char[] chars = (char[]) source;

                    for (char character : chars) {
                        jsonArray.add(this.getArrayAsJson(character));
                    }
                } else if (byte.class.isAssignableFrom(componentType)) {
                    byte[] bytes = (byte[]) source;

                    for (byte num : bytes) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                } else if (short.class.isAssignableFrom(componentType)) {
                    short[] shorts = (short[]) source;

                    for (short num : shorts) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                } else if (int.class.isAssignableFrom(componentType)) {
                    int[] ints = (int[]) source;

                    for (int num : ints) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                } else if (long.class.isAssignableFrom(componentType)) {
                    long[] longs = (long[]) source;

                    for (long num : longs) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                } else if (float.class.isAssignableFrom(componentType)) {
                    float[] floats = (float[]) source;

                    for (float num : floats) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                } else if (double.class.isAssignableFrom(componentType)) {
                    double[] doubles = (double[]) source;

                    for (double num : doubles) {
                        jsonArray.add(this.getArrayAsJson(num));
                    }
                }
            } else {
                Object[] objects = (Object[]) source;

                for (Object object : objects) {
                    jsonArray.add(this.getArrayAsJson(object));
                }
            }

        } else {
            jsonArray.add(this.getAsJsonValue(source));
        }

        return null;
    }

    /**
     * The {@code #converterCheck(JsonConversionManager)} is a utility method used run some basic checks, e. g. if the
     * passed {@link JsonConverter JsonConverter} is null. If there is a problem found with the {@link JsonConverter
     * JsonConverter} an exception will be thrown.
     *
     * @param converter
     *         the converter
     */
    private void converterCheck(JsonConverter converter) {

        Objects.requireNonNull(converter, "The passed converter is null");

        Class<?> converterClass = converter.getClass();
        if (!converterClass.isAnnotationPresent(Converts.class)) {
            throw new MissingAnnotationException("The provided converter is not annotated with a Converts annotation.");
        }

    }


}
