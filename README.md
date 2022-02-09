# ATM Machine
This is an ATM Machine Project for Neueda Evaluation.

# It has been developed using:
- Java 17
- Spring Boot
- H2 Database
- Swagger (Springfox)
- Intellij IDEA

# Requirements
For building and running the application you need:

Maven 3.6.3

Running the application locally
There are several ways to run a Spring Boot application on your local machine. One way is to execute the main method in the com.neueda.atm.NeuedaAtmMachineApplication class from your IDE.

Alternatively you can use the Spring Boot Maven plugin like so:

mvn spring-boot:run

# Endpoints
Once the apring boot app is running we will be able to try endpoints going to following url: http://localhost:8080/atm/swagger-ui.html

Or using the following CURL examples:<br/>
### Getting account balance: <br/>
``curl -X GET "http://localhost:8080/atm/account" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"number\": 123456789, \"pin\": 1234}"``

### Withdrawing money:<br/>
``curl -X PUT "http://localhost:8080/atm/account/withdrawal" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"account\": { \"number\": 123456789, \"pin\": 1234 }, \"withdrawAmount\": 55}"``
