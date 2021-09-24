# user-application-springboot

# Read Me First

Follow the below given instructions to build and run this project using Java Spring-boot and Docker.

# Getting Started

## To run the project using maven / Spring-boot:

* To install **Maven** dependencies run **./mvnw clean install** from the root directory.
* Move in to **src/main/resources/** and change the reqired data in **application.properties**
* Run migration scripts to initialise users in database
> ```mvn flyway:migrate -Dflyway.configFiles=myFlywayConfig.properties -Dflyway.ignoreMissingMigrations=true -Dflyway.outOfOrder=true```
* To check if migration scripts are executed successfully
> ```mvn flyway:info -Dflyway.configFiles=myFlywayConfig.properties```
* If not used migration to initialize the users in the database then initially five users are saved while building the project.

* Run **./mvnw clean spring-boot:run** to run the project.

> Pre-loaded users
> ```
> - liam@mailsac.com
> - olivia@mmailsac.com
> - oliver@mailsac.com
> - emma@mailsac.com
> - noah@mailsac.com

### Entities used in the project:

* Email
* First name
* Last name
* Date of birth
* Marital status

### Postman dump

* API examples are given in this [Postman Dump](https://www.getpostman.com/collections/a90a1b38e61f0f98eceb).

### Registration APIs

The following APIs are not authorized:

* [Login](http://localhost:8080/login)
    * http://localhost:8080/login
  > `{
  "username": "test@mail.com"
  }`
  > in request body.
* [Register](http://localhost:8080/register)
    * http://localhost:8080/register
  > ```
  > {
  > "username": "test@mail.com", 
  > "firstName": "firstName", 
  > "lastName": "lastname",
  > "dateOfBirth": "11-11-1111",
  >  "maritalStatus": "single/married"
  >  }
  > ``` 
  > in request body.
* [Authenticate](http://localhost:8080/authenticate)
    * http://localhost:8080/authenticate
  > `{
  "username": "test@mail.com",
  "password": "your_otp"
  }`
  > in request body.

### User CRUD APIs

The following APIs are authorized so after getting the access token from **/authenticate** use the access token in headers
as Bearer token to access the below given APIs:

* [Get All Users - GET Method](http://localhost:8080/users)
    * http://localhost:8080/users
* [Get One User - GET Method](http://localhost:8080/users/{id})
    * http://localhost:8080/users/{id}
  > `"id" = "user_id"`
  > in path param.
* [Save User - POST Method](http://localhost:8080/users)
    * http://localhost:8080/users
  > ```
  > {
  > "username": "test@mail.com", 
  > "firstName": "firstName", 
  > "lastName": "lastname",
  > "dateOfBirth": "11-11-1111",
  >  "maritalStatus": "single/married"
  >  }
  > ``` 
  > in request body.
* [Save User - PUT Method](http://localhost:8080/users/{id})
    * http://localhost:8080/users/{id}
  > `"id" = "user_id"`
  > in path param.
  > ```
  > {
  > "username": "test@mail.com", 
  > "firstName": "firstName", 
  > "lastName": "lastname",
  > "dateOfBirth": "11-11-1111",
  >  "maritalStatus": "single/married"
  >  }
  > ``` 
  > in request body.
* [Delete User - Delete Method](http://localhost:8080/users/{id})
    * http://localhost:8080/users/{id}
  > `"id" = "user_id"`
  > in path param.

## To run the project using Docker:

* Install Docker in your system using the [Official Installation Documentation](https://docs.docker.com/engine/install/).
* Install Docker-compose in your system using the [Official Installation Documentation](https://docs.docker.com/compose/install/).
* Have a look at the docker CLI commands before deploying the container image [Docker CLI Commands](https://docs.docker.com/engine/reference/commandline/docker/)
* Run all **docker** commands from the root directory of this project.
* Run **./mvnw clean package** from the root directory of this project.
* Use this command to extract the .jar file and build dependencies
  > ``mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)``
* Change the required data in **docker-compose.yml**
* To build docker image:
  > **docker build -t repo_name/image_name:tag .**
* To deploy the application with mariadb database.
  > **docker-compose up**
