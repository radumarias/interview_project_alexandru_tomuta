# interview_project_alexandru_tomuta 

## Location Manager

We make use of Google's Autocomplete js framework on the client side to obtain coordinates
for a location. Those coordinates are sent to the back-end server which in turn queries Google places API
for detailed information regarding establishments close to that location.

DONE:
* GWT project
* DB Setup
* Guice integration
* Google API calls integration
* Google API autocomplete for location search
* Google API places response mapping - using Jackson Json mapper
* DB integration - add Hibernate support, entities, persistence layer
* Enable CRUD operation for locations
* Cross check location look-up with DB values and update accordingly
* ~~Do we need to make Google API calls from back-end? We can just move these API calls in client and
any CRUD operations - saved in back-end db.~~
* Display locations from DB on client-side
* Enable CRUD operations on Client
* Google response validation - api limit exceeded
* Client input validation

TODO:

* Implement unit tests - code coverage
* Setup docker container for GWT app
* Create module for Integration Tests - create google places api simulator (otherwise we'll reach the quota really fast)

### LocationManager-DB

Flyway project for versioning different updates to the database \
\
**Commands** \
`mvn flyway:migrate` migrates database to latest version \
`mvn flyway:clean` cleans database - drops everything 
***
**Paths** \
`src/main/resources/database/migration` migration scripts location \
`src/main/resources/stage/flywat-[ENV].properties` properties file for different environments 
 
 ### LocationManager-APP
 
 GWT project with client, server and shared packages 
 
 **Commands** \
 `mvn clean install gwt:run` builds the project and starts the application in an embedded jetty server
 ***
 **Paths** 
 ### Docker images
 
 ####  db-postgres
 
**Disclaimer**: Using PostgreSQL in a container for testing purpose. 
This shouldn't be used in production
   
**Commands**

`docker build docker/db-postgres -t pg-db-gui:latest` builds the docker image - adds the pgstudio 2.0 for db management over the web \
`docker run -p 8080:8080 -p 5432:5432 pg-db-gui:latest` starts up the container
***