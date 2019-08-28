[![Javadocs](https://www.javadoc.io/badge/com.github.mavolin/maxon.svg)](https://www.javadoc.io/doc/com.github.mavolin/maxon)  [![Maven Central](https://img.shields.io/maven-central/v/com.github.mavolin/maxon)](https://search.maven.org/artifact/com.github.mavolin/maxon)

# maxon

Maxon is a lightweight JSON converter. It features a high customizability due to the various ways, custom converters can be written and is easy to use.

## Maven Central Repository

Maxon is uploaded to the central repository and can be added to your project by following the steps below.

### Maven

Add the following dependency to your ``pom.xml``

```xml
<dependency>
  <groupId>com.github.mavolin</groupId>
  <artifactId>maxon</artifactId>
  <version>1.1.2</version>
</dependency>
```

### Gradle

Add this to your ``build.gradle`` file:

```
implementation 'com.github.mavolin:maxon:1.1.2'
```

## Natively Supported Classes

- All Java primitives, and their wrappers
- ``BigInteger`` and ``BigDecimal``
- ``String``
- ``AtomicBoolean``, ``AtomicInteger``, ``AtomicIntegerArray``, ``AtomicLong`` and ``AtomicLongArray``
- ``Instant``, ``LocalTime``, ``LocalDate``, ``LocalDateTime``, ``OffsetDateTime``, ``ZonedDateTime`` and ``Date``
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
