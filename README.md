# Global Beverage Corporation Exchange Stock Market Service

#Project Features
* Spring Boot, Java 8 is used.
* Application is running on port 9090, having the REST APIs
* Swagger is used to test the Rest APIs
* Test data from the Global Beverage Corporation Exchange is Added via the application.properties

#Building the artifacts:
This is a maven project, so you can run these 2 goals:
* mvn test -> to execute the unit tests.
* mvn package -> to generate the executable jar.

To run the program just run:
* java -jar gbce-stock-market-service-0.1.0.jar

To run the REST APIs
* After the Server is started by the above command. Please open http://localhost:9090/gbce-stock-market-service/swagger-ui.html on Browser
* Now we can run the APIs by filling appropriate values needed for the APIs


