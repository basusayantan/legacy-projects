Java library for Application Configuration Management. Built with Apache Ant.

## Features

Separation of locally set configs and externally sourced ones.

## Getting Started

### Prerequisites

Java 8+
Apache Ant 1.10.14

### Build Instructions

To build a jar, run the following:
```bash
ant clean
ant package
```

The packaged jar file will be placed in `bin` folder.

### Usage

Add the jar to use classpath, and use:
```java
Config config = Configfactory.create()```