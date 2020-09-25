# Plugin Abstract Library for Bukkit

A library used by my plugins that contains common code.

## Usage
Inside `build.gradle`:

```groovy
repositories {
    maven {
        url "https://repo.rosewooddev.io/repository/public/"
    }
}

dependencies {
	compileOnly 'dev.ethp.pal:bukkit:0.0.1-SNAPSHOT'
}
```

## Documentation
Unfortunately, generating Javadocs from Kotlin using Dokka 1.4.0 is currently "alpha",
and it doesn't properly generate docs for companion objects or for fields getters.

If you need Javadocs, the best thing you can do is use the sources jar and hope your IDE supports Java completion of Kotlin code.


## Compiling
Gradle is used to compile and package the artifacts.
Run the following command to build the project:

```bash
./gradlew build
```

The final files will be inside `build/libs`.
