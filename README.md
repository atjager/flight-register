# flight-register

A simple example project with Java, Postgres and Docker.
## Building from source

### Prerequisites

* mvn
* jdk 17
* docker

```shell
mvn clean install
```
---
### Run in docker
1. Copy the built artifact from target/flight-register-0.0.1.jar to vm/flight-register-0.0.1.jar.
2. Run ```docker compose up``` in the **vm** directory.
3. The app is now accessible on port 8081.

---
### Swagger API docs (if you run this on localhost)

[Swagger UI](http://localhost:8081/swagger-ui/index.html)

[API docs](http://localhost:8081/v3/api-docs)

---