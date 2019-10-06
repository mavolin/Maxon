[![Javadocs](https://www.javadoc.io/badge/com.github.mavolin/maxon.svg)](https://www.javadoc.io/doc/com.github.mavolin/maxon)  [![Maven Central](https://img.shields.io/maven-central/v/com.github.mavolin/maxon)](https://search.maven.org/artifact/com.github.mavolin/maxon)

---

# maxon

Maxon is a lightweight JSON converter. It features universal ``Object`` conversion, whichs output can be customized by using a rich set of configuration annotations (see Using the Universal Converter).

## Maven Central Repository

Maxon is uploaded to the central repository and can be added to your project by following the steps below.

### Maven

Add the following dependency to your ``pom.xml``

```xml
<dependency>
  <groupId>com.github.mavolin</groupId>
  <artifactId>maxon</artifactId>
  <version>1.3.1</version>
</dependency>
```

### Gradle

Add this to your ``build.gradle`` file:

```
implementation 'com.github.mavolin:maxon:1.3.1'
```

## Natively Supported Classes

- All Java primitives, and their wrappers
- ``BigInteger`` and ``BigDecimal``
- ``String``
- ``AtomicBoolean``, ``AtomicInteger``, ``AtomicIntegerArray``, ``AtomicLong`` and ``AtomicLongArray``
- ``Instant``, ``LocalTime``, ``LocalDate``, ``LocalDateTime``, ``OffsetDateTime``, ``ZonedDateTime`` and ``Date``
- ``HashMap``, ``LinkedHashMap``, ``Hashtable``, ``IdentityHashMap``, ``TreeMap``, ``WeakHashMap``, ``ConcurrentHashMap``, ``ConcurrentSkipListMap`` and ``EnumMap``
- ``ArrayList``, ``LinkedList`` and ``Vector``
- all enums

## Usage

Conversions with maxon are easy and done by simply instantiating a ``Maxon`` object and calling the corresponding conversion method.
A conversion to JSON would look like this:

```java
Maxon maxon = new Maxon();
String json = maxon.getAsJson(12);
```

and a conversion from JSON would look like this:

```java
Maxon maxon = new Maxon();
Integer myInt = maxon.getFromJson(json, Integer.class);
```

note that we are using the wrapper classes, in case the JSON value would be ``null``.

To enable customized output, you can simply configure Maxon's output by creating and configuring a ``MaxonConfigurator`` like so:

```java
Maxon maxon = new MaxonConfigurator()
				.toggleTabWhitespace(true)
				.setWhitespaceQuantity(1)
				.setPrintStyle(PrintStyle.PRETTY_PRINTED)
				.setIgnoreNull(true)
				.setDateFormatConfiguration(someDateFormatConfiguration)
				.buildMaxon();
```

If you don't set a all config fields, the default configuration for those fields will be used.

## Custom Converter

If you wish to convert objects other than those natively supported, you can either register a ``JsonConverter`` with your ``Maxon`` instance, or simply implement a ``ObjectConverter``in your custom object and register it. In contrast to the ``ObjectConverter``, the ``JsonConverter`` is able to provide support for objects of multiple classes. The ``ObjectConverter`` is ideal to implement in custom objects. After creating one of the two custom converters, they have to be registered with the ``Maxon`` instance, by calling their respective register method.

## Using the Universal Converter

If a ``Class`` is not known to maxon, the universal object converter will be used. While annotating is not necessary, it features ways of customizing the (de-)serialization process. Here is an example with explanation for all different annotaions:


```java
/*
If set to true, maxon will abort serialization if one of the fields annotated 
with the @Serialize annotation is not contained in the JSON source. If no fields
are annotated with @Serialize, abortion will happen when one of the objects fields
is not present in the passed JSON. When aborting a JsonParsingException will be
thrown.

If no @AbortOnMissingField annotation is present, no abortion will happen.
 */
@AbortOnMissingField(true)
public class Showcase {


    /*
    If at least one field is annotated with @Serialize, only the fields annotated 
    with @Serialize will be serialize. If no field is annotated with @Serialize,
    all fields will be serialized.
     */
    @Serialize
    private String str;
    /*
    If a String parameter is passed to the @Serialize annotation, the passed String
    will be used as name of the field during serialization, instead of using the
    literal name of the field.
     */
    @Serialize("integer")
    private int anInt;
    /*
    If a field is annotated with an @AbortOnMissingField, it overwrites the "global"
    setting, meaning the setting set at class level. If the field is missing, a
    JsonParsingException will be thrown.
     */
    @AbortOnMissingField(false)
    private boolean bool;
    
    
    /*
    The @DeserializationConstructor annotation is required, if there is no no-arg
    constructor for the class. The @DeserializationConstructor, it takes an array
    of Strings, which represent the names of the values, as found in the JSON
    source.
    Note that if a value gets used as a parameter for the constructor, the value will
    not be used again during field deserialization.
     */
    @DeserializationConstructor("float")
    Showcase(float num) {
        this.anInt = (int) num;
    }


}
```
