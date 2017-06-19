## Continuous Integration
[![Build Status](https://travis-ci.org/tutabeier/bus_route.svg?branch=master)](https://travis-ci.org/tutabeier/bus_route)

Build history in [Travis CI](https://travis-ci.org/tutabeier/bus_route/).

## Requirements:
- Java 8
- Git

## Running the application
```sh
$ git clone https://github.com/tutabeier/bus_route.git bus_route
$ cd bus_route
$ ./gradlew build && java -jar build/libs/bus-route-challenge-0.1.0.jar
```

## Running the tests
### Unit tests
```sh
$ ./gradlew unitTests
```

### Integration tests
```sh
$ ./gradlew integrationTests
```

### End to Endtests
```sh
$ ./gradlew endToEndTests
```

