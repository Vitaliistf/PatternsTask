# PatternsTask

> This repository contains the solution for the "PatternsTask". This is a simple movie rental system implemented in 
> Java. The system allows you to manage a catalog of movies, add customers, and track customer rentals.

## Table of Contents

- [Requirements](#requirements)
- [Selected design patterns](#selected-design-patterns)
- [Technologies Used](#technologies-used)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Usage](#usage)

## Requirements

Let's imagine that we need to add new features. Firstly, the client wants the output also in HTML. 
Secondly, the client wants to add other types of movies, such as dramas, comedies, and thrillers. 
Thirdly, we want to add new attributes to the movies (country of origin, short description, director, actors).

These changes pose a challenge to the current structure, so we will need to modify it. 
Create the ability to manage multiple clients, work with text (!) files for data storage. 
The program should provide the following functionalities:

- View the catalog of all movies,
- Add a movie,
- Search based on specific characteristics, etc.

Create a user-friendly menu for accessing these functions.
The application should contain design patterns.

## Selected design patterns

- `Builder` is used to create Movie instances.
- `Strategy` is used to chose different pricing strategies for movie types.
- `Template method` is used to create different rental views.
- `MVC` is used to interact with the system.
- `Facade` - also we can say that controllers are a facade for the whole system.
- `Singleton` is used to create only one instance of some utility classes.

## Technologies Used

The application utilizes the following technologies:

- Java 21
- Maven
- JUnit 5

## Project Structure

The project contains following classes:

- `org.vitaliistf` package contains the main class for the application.
- `org.vitaliistf.model` package includes model classes.
- `org.vitaliistf.strategy` package contains price strategy implementations.
- `org.vitaliistf.view` package includes different views for displaying rentals.
- `org.vitaliistf.serialization` package includes a class for model serialization or deserialization.
- `org.vitaliistf.controller` package includes controllers, which provide the main functionality facade.

For more information about classes, please generate documentation.

## Getting Started

1. Clone the repository.
2. Change data file names if needed in the Main class.
3. Build and run the application.

## Usage

Follow the on-screen menu to perform various operations:
- Display Movie Catalog 
- Add a Movie to Catalog
- Search Movies by Director
- Display Customer List
- Add a Customer
- Save Data to File
- Load Data from File
- Display Customer Rentals
- Generate Customer Rentals HTML
- etc.