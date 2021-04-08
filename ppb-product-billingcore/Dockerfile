FROM openjdk:8-jdk-alpine

#install system dependencies
RUN apk update && apk add vim curl bash

RUN mkdir ppb-product-billingcore

WORKDIR ppb-product-billingcore

#deploy ppb-product-billingcore project
COPY target/*.jar app.jar

#copy script for wait until the mysql database is created
COPY wait wait
RUN chmod +x wait