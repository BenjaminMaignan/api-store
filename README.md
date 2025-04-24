# Store API - Spring Boot Application

### How to use this project

First, make sure you have the following prerequisites installed:

* [Java 21](https://www.oracle.com/java/technologies/javase/jdk21-archive-downloads.html)
* [Apache Maven](https://maven.apache.org/download.cgi)
* [Docker](https://www.docker.com/get-started)

Then, clone the repository and navigate to the project directory:

```bash
    git clone git@github.com:BenjaminMaignan/api-store.git
```


### Guides

1) Open your docker terminal and run the following command to start the PostgreSQL database:

```bash
    docker compose up -d
```

2) Open your terminal and navigate to the project directory. Run the following command to build the project:

```bash
    mvn clean install
```

3) After the build is successful, run the following command to start the application:

```bash
    mvn spring-boot:run
```
