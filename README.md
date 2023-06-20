<img src="src/main/frontend/src/images/logo.png"  width="200" height="300" />


# MoviePocket

## Getting started

MoviePocket is movie management system that will help you to orginize your movie collection and share it with other
movie lovers.
The more percise description of the service you can find in Documentation/ section:)


Explore world of movies with MoviePocket and create your personal unforgettable experience collecting sharing movies between users.

## Short user manual

### Join MoviePocket by creating your account

![Register](https://github.com/prymakD/MoviePocket/assets/54550596/c7252056-e857-4bd0-b290-8ff4b25476fd)

### Explore the upcoming movies on the main page 

![main_screen](https://github.com/prymakD/MoviePocket/assets/54550596/45864288-ad11-49d9-9b66-5b60c6b427c0)

###  Leave a review and share what you think about particular movie with others

![Review](https://github.com/prymakD/MoviePocket/assets/54550596/3f77fa8f-8bc8-4503-b9bb-0c3812b08718)

### Mark movie as wathced or add it to your favourites list

![Like_and_watched](https://github.com/prymakD/MoviePocket/assets/54550596/0b591513-dd3c-4106-857c-865410e18a82)



## To start develop

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
