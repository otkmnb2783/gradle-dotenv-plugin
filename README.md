# Gradle dotenv plugin

This plugin loads the dotenv file so that it can be referenced from build.gradle.

## How to use

### Minimal supported versions

This plugin was written using the new API available for gradle script kotlin builds.
This API is available in new versions of gradle.

Minimal supported [Gradle](www.gradle.org) version: `4.10`


### dotenv plugin

#### dotenv

```dotenv
MYSQL_USER=
MYSQL_PASSWORD=
MYSQL_DATABASE=
```

#### Simple setup

Build script snippet for use in all Gradle versions:

```groovy
buildscript {
  repositories {
    maven {
      url "https://plugins.gradle.org/m2/"
    }
  }
  dependencies {
    classpath "com.github.otkmnb2783.dotenv:gradle-dotenv-plugin:<current_version>"
  }
}

apply plugin: "com.github.otkmnb2783.dotenv"

flyway {	
    url = "jdbc:mysql://localhost:3306/${env.MYSQL_DATABASE}"	
    user = "${env.MYSQL_USER}"	
    password = "${env.MYSQL_PASSWORD}"	
}	

```


#### Using new plugin API

Build script snippet for new, incubating, plugin mechanism introduced in Gradle 2.1:
```groovy
plugins {
  id "com.github.otkmnb2783.dotenv" version "<current_version>"
}
```
