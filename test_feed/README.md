## Introduction

* Author: Knoldus
* Swagger: [here](http://localhost:8080/swagger-ui/index.html)

## How Stuff Works

## Requirements

| Framework   | Version |
|-------------|---------|
| Maven       | 4.0.0   |
| Java        | 11      |
| Spring Boot | 2.6.7   |
| RabbitMQ    | 3.9.16  |
# **Steps to set up the feed service**
<details><summary></summary>
</details>

* **Clone the project from the given feed service repository**
    * **Command:** git clone https://github.com/knoldus/go1percent.git


* **Import the feed service into the ide**


## Project structure

    .
    ├── bin                         : Shell scripts for starting and creating local database.
    ├── docker                      : Docker-related files for testing, building and deploying the service.
    │   └── test                    : Test Docker files.
    ├── README.md                   : This file you are reading right now ;).
    ├── sql                         : General database scripts for starting a test database.
    └── src                         : Sources.
        ├── main                    : Main sources.
        └── test                    : Test sources.
* **Go to bin folder**
    * **Command:** cd /bin
* **Run the .sh file  :** ./start-test-db.sh
* **Build the Application**
    * **Command:** mvn clean install


* **Run the Application**  
