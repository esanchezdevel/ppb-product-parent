FROM openjdk:8-jdk-alpine

#install system dependencies
RUN apk update && apk add vim curl bash

RUN mkdir ppb-product-user-management

WORKDIR ppb-product-user-management

#deploy ppb-product-user-management project
COPY target/*.jar app.jar

#copy script for wait until the mysql database is created
COPY wait wait
RUN chmod +x wait