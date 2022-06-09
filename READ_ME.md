# SPRING BOOT TECHNICAL TEST by Charly Ducrocq

## Dependencies
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

## Clean the DB
The database is an H2 database store in the `./data` folder.
If you want to clean the database, please stop the app if running and delete the folder.