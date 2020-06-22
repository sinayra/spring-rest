# Algaworks Spring Rest API for Beginners course project
A simple RESP API to create tickets for a service provided to a customer.

## About the course
This was one-week free course to programmers with low or none experience with Spring Framework and with low or none experience with REST architecture. 
The main focus of the course was to delivery an application capable of CRUD operations, using MYSQL as database, following SOLID principles.

### Routes
* **GET /**: Displays the message *hello world*

* **POST /clients**: Creates a new customer
* **GET /clients**: Displays all registeres customers
* **GET /clients/{id}**: Displays a customer with ID *{id}*
* **PUT /clients/{id}**: Updates a customer with ID *{id}*
* **DELETE /clients/{id}**: Deletes a customer with ID *{id}*

* **POST /service-order**: Creates a new ticket
* **GET /service-order**: List all tickets
* **GET /service-order/{id}**: Displays a ticket with ID *{id}*
* **PUT /service-order/{id}/finish**: Closes ticket with ID *{id}*

* **POST /service-order/{id}/comments**: Creates a new comment for the ticket with ID *{id}*
* **GET /service-order/{id}/comments**: Displays all comments for the ticket with ID *{id}*

## Challenging myself
Since I already have some experience in Java delevopment, and I changed some of technologies used in the course to better suit my own enviroment.

### Java version
I upgraded my Java version to 14, instead of Java 11 used in the course. Since the course was for beginners, this change did not cause any difference in the programming itself.

### IDE
Instead using Eclipse, I give a try to use the Sping Tools for Visual Studio Code. 

Compared to the Eclipse one, the spring  project start functionality was not transparent as the Eclipse one: I had to use the [spring initializr](https://start.spring.io/) (otherwise, it was not creating the same structure as the instructor). 
Unfortunately, Flyway was not working properly when used with Spring dev tools: every time I have created a new migration, I had to compile my maven once again for Flyway to work and execute the migrations.

I am sure it was just a configuration that I have missed.

### Database
I changed the database from MySQL to SQLite, to simplify the database connection and avoid other configurations that must be done if use MySQL, since this project it is very simple. I followed the [Baeldung](https://www.baeldung.com/spring-boot-sqlite) tutorial to proper create the SQLite Dialect for Hibernate. After that, it was very simple to use.

### Deploying in Heroku
I started studying for a few weeks and most my Dockerfiles was for node applications, and this was the first time I deployed a Java application and sucessful deployed in other enviroment. Before that, I had never deploy in any plataform (I have my tries in AWS, but they were not successful yet). 

Since I simplified the database to be just a SQLite file, that was no need to deploy another container with the database. So, all the API would be just a single Dockerfile.
Using the [Medium tutorial](https://medium.com/@urbanswati/deploying-spring-boot-restapi-using-docker-maven-heroku-and-accessing-it-using-your-custom-aa04798c0112), it was very simple to deploy it, althought I still messed up with the Heroku dynos I still need to study how to use workers to better encapsulated my application.

## Final thoughts
Overall, the course was very helpful. It was not the first time that I used Spring, but with this course I got a better understanding of its design patterns and what I should do and not do when creating a Spring Java application.
And about my own challenges, I am very proud that I could fully deployed my application on Heroku. Even though my app is not 100% secure and I still do not undestand all Heroku cycles for its dynos, I still consider this a very nice first try. Good job, me! 

![Obama meme](https://i.imgflip.com/2/1hhv9m.jpg)
