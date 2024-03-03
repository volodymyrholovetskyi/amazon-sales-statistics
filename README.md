# Amazon Sales Statistics Application

* [General info](#general-info)
* [Api-docs](#api-docs)
* [Technologies](#technologies)
* [Set-up](#set-up)

## General Info

Output of sales statistics for the following attributes: <br>
- on a specific date
- on the specified ANSI
- for all dates
- for all ANSI

## Api-docs

> - Swagger UI: http://localhost:8080/swagger-ui/index.html

## Technologies
- Spring Boot 3.2.3
- Spring Data MongoDB
- Spring Devtools
- Spring Boot Caching (ehcache)
- Spring Security (JWT)
- Java 17
- Lombok
- Swagger
- Docker
- Maven

## Set-up
### Launch the program in three steps:
1. `git clone https://github.com/volodymyrholovetskyi/amazon-sales-statistics.git` <br>
2. Run Docker Engine on your PC <br>
3. Run the following command in a terminal window (in the complete directory):<br>
`./mvnw spring-boot:run`