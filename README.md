# MoviePocket

## Getting started

MoviePocket is movie management system that will help you to orginize your movie collection and share it with other
movie lovers.

## To start

### You will need on your machine installed:

- [Java(at least 1.8)](https://www.oracle.com/java/technologies/downloads/), **Spring boot** version at least 2.7.10 and
  Spring 5.X bounded with Maven for backend
    - Cookie based authentication by [Spring Security](https://spring.io/projects/spring-security) 2.7
    - ORM: Hibernate v 5. + JPA
- [Node.js](https://nodejs.org/en) >= 18.15.0 and **npm** >= 9.5.0 for frontend
- [MySql](https://www.mysql.com/) for Database

_Note_: to connect to the current DB you need to have UAM ip address as the DB is from UAM mysql server for now. To do
that you need to use vpn(e.g. OpenVPN) and follow
the [tutorial](https://laboratoria.wmi.amu.edu.pl/en/services/vpn/windows/)

WITHOUT IT BACKEND WILL NOT RUN!

## Useful URLs

Frontend and the whole service is run on http://localhost:3000
http://localhost:8080 for backend

REST API documentation is Swagger2 generated and available on  http://localhost:8080/swagger-ui.html

Some example api request urls:

- GET http://localhost:8080/user/{username} to find all info about the registered user by username

**Project vision** and **project requirements** documents are available in documents/ section of source

## Extra

With help of spring-boot-maven-plugin you can generate by ```mvn compile``` a compatible JAR file which will take care
of installing the appropriate ver for front and back dependencies 