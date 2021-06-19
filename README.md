# FastFit

(This project is for UNIST CSE364 Software Engineering course.)

## What is FastFit?

FastFit is a movie recommendation system. We quickly(*fast*) find movies that *fits* your taste.

> TODO: add introductory paragraph (refer to the presentation): goals, features, a screenshot

## Getting Started

### Running the server

To use FastFit, you first need to run the server.

#### via Docker-Tomcat

- Build a docker .war build with maven.

```
$ mvn clean package -P war-build -P docker
```

- Move the .war file in target folder, Dockerfile and run.sh file in main folder to deploy folder.

- In the deploy folder, build a docker image, and start a container. (expose port to be able to connect from outside)

```
$ docker build -t [image_tag] ./
$ docker run -p [host_port]:[container_port] -it [image_tag]
```

- Enjoy!

#### via JAR directly

- Install mongoDB on the host machine, and start the service.

- Build a .jar build with maven.

```
$ mvn clean package
```

- Run the .jar file on the target folder. On first time run, you should run with the initialization profile for loading DB.

```
$ java -jar [jar_file] (-P init)
```

- Enjoy!

### Using the web application

We provide a simple web application that serves as an interface for end users.

#### Main Page

On the top, there are Service name, button to two custom recommendation page, and some introductions.
Lower, there are 10 overall recommended movies by our algorithm, and 10 recommended movies from chosen genres.

#### Recommendation by User Information

You can use our Recommendation by User Information Algorithm to search for movies that people of similarity rated the best.
On the top, you can either input some values, or leave them empty if you do not want to specify.
After filling it up, hit the button below, and the top 10 similar recommendable movies will show.

Some input fields only accepts a limited number of values. To check which values are allowed, please see the [Available Inputs](/docs/available-inputs.md) page.

#### Recommendation by Movie Title

You can use our Recommendation by Movie Title Algorithm to search for movies that who watched the movie rated the best.
On the top, you can input the movie title and the year the movie was released (ex) Toy Story (1995)).
After filling it up, hit the button below, and the top 10 similar recommendable movies will show.

Some input fields only accepts a limited number of values. To check which values are allowed, please see the [Available Inputs](/docs/available-inputs.md) page.

For more information, please see the [Web App](/docs/web.md) reference page.

### Using the REST API

For developers, there is a REST API to fetch data in the JSON format. This is a standard way to communicate the data programmatically and it allows you to handle the data in any way you desire.

```
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "other", "genres": ""}'
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "Toy story (1995)", "limit": "10"}'
```

(Upper example is done by curl, but any correct GET request will qualify)

For more information, please see the [REST API](/docs/api.md) reference page.

## Development Guide

### Build

```
$ mvn package [-P profile1 -P profile2 ...]
```

#### Maven profiles

We use Maven profiles to customize build configurations.

- `init` : Enables a Spring batch job to initialize the MongoDB and fill the data. The batch job doesn't run when the database is already filled. Use this option in most cases.
- `war-build` : Builds a WAR package instead of a JAR package.

### Test

To test the codebase, run:

```
$ mvn test
```

After running the test command, open `target/site/jacoco/index.html` file in the browser to check the code coverage.

## References

- [Web App](/docs/web.md)
- [REST API](/docs/api.md)
- [Command Line Program (Deprecated)](/docs/cli.md)

## Update History

- [Milestone 1](/docs/milestones/milestone1.md)
- [Milestone 2](/docs/milestones/milestone2.md)
- [Milestone 3](/docs/milestones/milestone3.md)
- [Milestone 4](/docs/milestones/milestone4.md)
