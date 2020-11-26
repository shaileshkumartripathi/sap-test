
FROM openjdk:14
EXPOSE 8085
ADD target/Assessment-0.0.1-SNAPSHOT.jar Assessment-0.0.1-SNAPSHOT.jar
# java -jar /opt/app/Assessment-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","Assessment-0.0.1-SNAPSHOT.jar"]
# sudo docker build -t spring-boot:1.0 .
# sudo docker run -d -p 8085:8085 -t football-images