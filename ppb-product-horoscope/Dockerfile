FROM openjdk:8-jdk-alpine

#install system dependencies
RUN apk update && apk add vim curl

RUN mkdir ppb-product-horoscope

WORKDIR ppb-product-horoscope

#deploy ppb-product-horoscope project
COPY target/*.jar app.jar

ENTRYPOINT ["java","-jar","/ppb-product-horoscope/app.jar"]