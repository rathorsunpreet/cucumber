# Cucumber

Cucumber is an Eclipse Java project to test the [Rest API](https://reqres.in/). The following libraries / frameworks / plugins / tools have been used:
1. Rest-Assured to ping the API endpoints.
2. Maven as the build tool.
3. JUnit5 and Cucumber for test-case creation.
4. Maven Wrapper to allow non-maven users the ability to build and run the tests. 
5. Blaze (by Fizzed) to remove the long commands and shorten them for ease of use.
6. maven-cucumber-reporting (by net.masterthought) for HTML Report generation upon execution of `testAllHTML`.

Demo / Explanation Video: https://www.youtube.com/watch?v=qw16KlvStXs

## Requirements
* Java 21+
* Eclipse (Optional)

## Installation

Download the package from [Github](https://github.com/rathorsunpreet/cucumber) and unzip it.

## Usage

```terminal
# To run tests on a single endpoint, use the feature file name as argument
java -jar blaze.jar users

# To test all features
java -jar blaze.jar testAll

# To generate HTML Report per feature
java -jar blaze.jar loginHTML

# To generate HTML Report upon execution of all feature files
java -jar blaze.jar testAllHTML

# To use maven test instead
# Us mvnw.cmd for Windows, mvnw for Linux and Mac
mvnwd.cmd test

# To generate HTML Report using Maven
# Us mvnw.cmd for Windows, mvnw for Linux and Mac
mvnw.cmd verify
```

## Notes
The Rest API has been split into 4 feature files, namely:
* login
* resources
* register
* users

Simply provide the feature name to `blaze.jar` to execute in terminal or append `HTML` to generate HTML report. The generated HTML report is named after the feature file being executed.

HTML Reports are generated at the following locations:
1. Upon individual feature file execution at `\target\cucumber-reports\`.
2. Upon execution of `testAllHTML` at `\target\cucumber-html-reports\`.

When running on Linux / Mac, add executable permissions to `mvnw` before running any of the commands in the **Usage** section.

## License

[MIT](https://choosealicense.com/licenses/mit/)