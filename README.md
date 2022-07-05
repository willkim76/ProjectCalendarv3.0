# Project Calendar v3.0

## Introduction

Project Calendar is a REST API that provides a simple Calendar service platform for clients. A Calendar is composed of 
just two entities. A User entity which is created by and managed by the client and Event entities which are associated 
with the User which are also created and managed by the client.

### Motivation

The motivation behind Project Calendar was to practice the concepts and methodologies of software development learned 
from Bloom Institute of Technologies Backend Program as well as an opportunity to learn more about the technologies in 
depth.

A secondary motivation was to develop a solution to a problem that has already been solved (such as a Google Calendar, 
albeit in much limited scope) from scratch to test problem-solving, design skills, and to promote creativity.

## Design

The Calendar API uses Spring Boot to drive the Java application and the respective endpoints. The overall configurations
of the Spring Boot application have been left in their default states such as the Tomcat Server. HttpOnly Cookies are 
used to authenticate and validate HTTP method calls to the API endpoints when altering a User or Event entities which
implements 

####User Stories:
* As a user I want to be able to create a User account
* As a user I want to be able to modify a User account
* As a user I want to be able to delete a User account
* As a user I want to be able to login and logout of a User account
* As a user I want to be able to create Events associated with a User account
* As a user I want to be able to modify Events associated with a User account
* As a user I want to be able to delete Events associated with a User account
* As a user I want to be able to query Events associated with a User account

### Technologies
* Docker to containerize and automate the deployment process
* Amazon DynamoDB is used to store the User entities and Event entities
* Amazon Fargate to host the containerized Spring Boot Application 
