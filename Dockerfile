FROM openjdk:11-jdk
ADD beStronger.jar .
EXPOSE 8080
CMD java -jar beStronger.jar