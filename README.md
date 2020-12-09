# English Number Translator

Web application for the translation of numbers to its english text representation. 

Ex: 123 -> One hundred and three

The application accepts numbers between the range of -9,999,999,999 and 9,999,999,999. Also the number can entered
in the UI without format (only numbers) or with english format Ex(9,999,999). The application also allows to check
the history of translations that the user has requested in the session.

The application was built with spring boot v2.4.0 and java 11 for the REST API. The front was built with react 16. For managing the
application dependencies and packaging we use maven for the whole project and yarn for the front end part.
An integration between maven and the react project was implemented so that the build can be executed from a single
maven command (more details in next sections). In the backend Lombok library is used to avoid code duplication.
Unit tests and integration test were implemented in the backend with Junit5. Also a set of end-to-end tests were
implemented with selenium and testcontainer library. For the unit tests in the front jest and enzyme were used.
The project store data in memory, so, no database setup is required.  Finally it is used swagger for the documentation 
of the endpoints and for easily testing them via an html interface. A maven javadoc plugin is also used for generating
the javadoc files.


## Project structure


### UI (React)
number-translator-js: base directory

    |
    |---src: Contains all of the application source code
    |
    |---src/components: Contains the react components used for building the UI
    |
    |---node_modules: dependencies binaries
    |
    |---**__snapshots__: store snapshots used in the unit tests
    |
    |---package.json: Project general configuration and dependencies


### REST API (Spring boot)
The application structure is composed of mainly two layers (REST Controllers and Services).
Next is a diagram of the package organization and their purpose:

    com.sampleapp.numbertranslator: base package
    |
    |---controllers: REST endpoints are defined
    |
    |---services: Business functions. This package contains the services that execute the translation and also the service that retrieves the history.
    |
    |---dtos: DTOs used for REST endpoint response
    |
    |---core: Contains the artifacts with the core logic that translate the number to text.
    |
    |---exceptions: Custom business exceptions


## Prerequisites for execution

### Front end
For running the project separately from the backend it is required to install yarn and node.

You’ll need to have Node >= 10 on your local development machine. Visit the next page for download:
https://nodejs.org/en/blog/release/v10.18.0/

For installing yarn check the next site:
https://classic.yarnpkg.com/en/docs/install/#mac-stable

### Backend

As a prerequisite you need to have installed JDK 11

https://www.oracle.com/java/technologies/javase-jdk11-downloads.html

To check that java is properly installed you can execute 

    'java --version'
    
It should return something like the following:

    'openjdk version "11.0.5" 2019-10-15 LTS
     OpenJDK Runtime Environment Corretto-11.0.5.10.1 (build 11.0.5+10-LTS)
     OpenJDK 64-Bit Server VM Corretto-11.0.5.10.1 (build 11.0.5+10-LTS, mixed mode)'

Due that for e2e testing we use testcontainer library we need to have installed Docker Desktop in order 
to run those tests. Check the following page for details of the installation: 
https://docs.docker.com/get-docker/

## Execution

1. In the terminal go to the base package of the project (.../number-translator/)
2. Execute: 

        './mvnw spring-boot:run'
        
Be aware that we are using the maven wrapper so you must select the appropiate wrapper depending on the OS.

By default the port is set to 8087. If for some reason the port is occupied it must be changed
in application.properties file (server.port property), in /src folders.

You can enter the application in the browser with the following url: 'http://localhost:8087/'

Another alternative to execute the application is to package it in a jar and execute the jar directly:

        '1. ./mvnw package
         2. java -jar target/number-translator-0.0.1-SNAPSHOT.jar'

## Endpoints

You can check the endpoints in the swagger url:
http://localhost:8087/swagger-ui/#/

Here is a summary of the services provided:
1. Translate number: POST http://localhost:8087/api/numbers/{number}
2. Get history of translations: GET http://localhost:8087/api/numbers

## Generating javadoc

With the following command the javadoc is generated in the target folder of the project:

    './mvnw javadoc:javadoc'

The docs can be accessed opening in the browser the file: './target/site/apidocs/index.html'

## Testing

### Functional tests

For the functional tests dummy data is automatically populated in the database when the application
starts in dev mode (default). Two accounts are created with account_number 1 and 2. For account 1
a list of operations are created that help testing all the endpoints.

#### Swagger

1. Access http://localhost:8089/swagger-ui/#/
2. Select try it out
3. Fill the required fields 
4. Execute

### Unit, integration and E2E Tests

#### Front end

For running the unit tests in the front end go the base folder of the front end project (src/js/number-translator-js) 
and execute 'yarn test --watchAll'

#### Backend
Unit tests and Integration tests are stored in /test folder in the package corresponding to the class
they are testing. Unit test class names end always in ...Test.java and integration tests in ...IT.java. E2E tests
are in e2e package inside the base application package (com.sampleapp.numbertranslator) and end in E2E.java.

For running them execute in the terminal either of the two following commands: 

        './mvnw test' //Unit test execution
        './mvnw verify' //Unit, Integration and E2E test execution

When E2E tests are executed a video with the test execution is generated in the target folder, you can see the name
in one of the final lines shown in the terminal when executing ./mvnw verify. Next is an example of that output:

    '2020-12-08 19:05:13.735  INFO 23807 --- [           main] o.t.c.BrowserWebDriverContainer          : Screen recordings for test %5Bengine%3Ajunit-jupiter%5D%2F%5Bclass%3Acom.sampleapp.numbertranslator.e2e.IndexControllerE2E%5D%2F%5Bmethod%3AshouldTranslateAndShowHistory%28%29%5D will be stored at: target/PASSED-%5Bengine%3Ajunit-jupiter%5D%2F%5Bclass%3Acom.sampleapp.numbertranslator.e2e.IndexControllerE2E%5D%2F%5Bmethod%3AshouldTranslateAndShowHistory%28%29%5D-20201208-190513.flv
     [INFO] Tests run: 1, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: 15.396 s - in com.sampleapp.numbertranslator.e2e.IndexControllerE2E'
