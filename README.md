# gestion-contact-entreprise
Simple enterprises and contacts manager WEB API

## Introduction

GCE stands for "Gestion des contacts d'entreprise".  It is a rest web service that manages the operations for CRUD contacts and CRUD companies. 
Furthermore, it allows to associate contacts to companies.

## Installation
1_ Download the source code and add it to your favorite IDE (for instance IntelliJ IDEA).
2_ Run the microservice throuhg main in GestionContactEntrepriseVimBeServer.class

3_ The Swagger-ui is available at URL: http://localhost:8116/gce/swagger-ui/index.html#/

5_ Spring security has been integrated so that Swagger can be accessed by logging in with the following credentials:
  * username: fenncim
  * password: 64007a3b-a096-4c64-92f1-c81c6a6ced74

6_ H2 database has been integrated and it be can be accessed by logging in with the following credentials:
  * username: sa
  * password: password.
  
  H2 url: http://localhost:8116/gce/h2-console

7_ You can also use the Postman collection, which you can find in the resources folder of the source code.
 genesis_gce_vim.postman_collection.json

## Note
This rest web service can be improved in error management, integration Test, Sonar for Quality tracking, Jacoco code coverage, Docker containerisation and others.
