# Toolbox

Toolboxes (core libraries) that make Java development more efficient, more rapid and less bug.

## Adding Toolbox to your build

Maven:

```text
<dependency>
    <groupId>io.github.shniu</groupId>
    <artifactId>toolbox</artifactId>
    <version>${latestVersion}</version>
</dependency>
```

Gradle:

```text
implementation("io.github.shniu:toolbox:${latestVersion}")
```

Latest version is `1.0.1`.

## Introduction

Toolbox is a set of core Java libraries that includes collections, strings, io, distributed support(id generator, rate limiter etc.), 
bits, bytes, caching, utilities for concurrency, json, Emoji, Excel, Pdf, and more.

### Dependency

- [Guava](https://github.com/google/guava)
- Apache Commons
- Jackson2
- jjwt
- Mockito
- DBUnit
- RestAssured
- Lombok
- Feign
- itextpdf
- Tika

### Strings

[TODO]

### Rate Limit

[TODO]

## Resources

- [使用Gradle发布构件(Jar)到Maven中央仓库](https://segmentfault.com/a/1190000018026290)
