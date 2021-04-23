#package the project into jar-file
mvn clean
mvn package

#run jar-file
java -jar target/Elevator-1.0.jar