# Introduction 
Simple java application using Spring Boot 3

# Getting Started
- Requirements:
  - Java 17
  - Maven 3
  - Docker

1.	Installation process
        - install IDE (Idea IntelliJ)
        - install Java (OpenJDK 17)
        - install Maven 3
2.	Software dependencies
        - Spring Boot 3
        - Spring Data JPA
        - Springdoc
        - MapStruct
        - OpenAPI generator
        - Apache Commons
        - etc.
3.	Latest releases
        - none
4.	API references
        - Application is based on API-first pattern, source API yaml file is based in `src/main/resources/api` folder.

# H2 database
- Application use H2 in-memory database to store data using spring JPA
  - [H2 console](http://localhost:8090/h2-ui)

# API generator
- Application generates interfaces for defined openAPI definition (defined in pom.xml)

# Swagger
- [Swagger UI](http://localhost:8090/swagger-ui/index.html#/)
- [OpenAPI yaml](http://localhost:8090/v3/api-docs.yaml)

# Build and Test
- checkout project from Git, run `mvn clean install`
- for skip tests, use `-DskipTests` arg

# Liquibase DB migrations
- Liquibase is enabled
- Main ChangeLog file `resources/db/migrations/db.changelog.yaml`
- Folder `resources/db/migrations/` contains versioned db migrations yaml files

# Logging
- Logging is provided into console
- Log pattern is defined in `application.yml` file as `%clr(%d{yy-MM-dd HH:mm:ss.SSS}){blue} %clr(%-5p) %clr(${PID}){faint} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{0}){blue} %clr(:){blue} %clr(%m){faint}%n`

# Checkstyle plugin
- usage: `mvn checkstyle:checkstyle`

# Docker image
- building application using maven wrapper
- base image does not require maven full installation

# Docker compose
- support ENV properties
- build and start docker image: `docker compose up`

# Vulnerabilities
- No critical vulnerabilities
- Dependecy management (exclusions)

# TODO
- oauth2 (Spring security)
- connection to artifactory (nexus2)
- create docker compose with another database (postgre) ?
- release notes publishing ? xxxx