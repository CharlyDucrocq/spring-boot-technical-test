# SPRING BOOT TECHNICAL TEST by Charly Ducrocq
This project is a simple REST app that allow to create and get users.
You can see all the test statement [here](./statement.pdf).

## Requirement
- java 17
- maven

## How to run the test
```sh
./mvnw.cmd clean package
```

## How to run app
```sh
./mvnw.cmd spring-boot:run
```
The application is now running on localhost:8080. You could find the REST documentation [here](http://localhost:8080/swagger-ui).

## Postman
You could find a simple postman collection ine the `./postman` folder.


## Clean the DB
The database is an H2 database store in the `./data` folder.
If you want to clean the database, please stop the app if running and delete the folder.