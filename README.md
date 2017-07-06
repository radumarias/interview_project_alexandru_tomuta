# interview_project_alexandru_tomuta 

## Location Manager

### LocationManager-DB

Flyway project for versioning different updates to the database \
\
**Commands** \
`mvn flyway:migrate` migrates database to latest version \
`mvn flyway:clean` cleans database - drops everything 

**Paths** \
`src/main/resources/database/migration` migration scripts location \
`src/main/resources/stage/flywat-[ENV].properties` properties file for different environments 
 
 ### LocationManager-APP
 
 GWT project with client, server and shared packages 
 
 **Commands**
 
 ### Docker images
 
 * **db-postgres** 
 
**Disclaimer**: Using PostgreSQL in a container for testing purpose. 
This shouldn't be used in production
   
**Commands**

`docker build . -t pg-db-gui:latest` builds the docker image - adds the pgstudio 2.0 for db management over the web \
`docker run -p 8080:8080 -p 5432:5432 pg-db-gui:latest` starts up the container
    