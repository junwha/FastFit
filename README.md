# FastFit

(This project is for UNIST CSE364 Software Engineering course.)

## What is FastFit?

FastFit is a movie recommendation system. We quickly(*fast*) find movies that *fits* your taste.

> TODO: add introductory paragraph (refer to the presentation): goals, features, a screenshot

## Getting Started

### Running the server

To use FastFit, you first need to run the server.

> TODO: describe how to run in two ways: using Docker and building directly

### Using the web application

We provide a simple web application that serves as an interface for end users.

> TODO: describe each page (main, recommendation w/ user info, recommendation w/ favorite movie)

Some input fields only accepts a limited number of values. To check which values are allowed, please see the [Available Inputs](/docs/available-inputs.md) page.

For more information, please see the [Web App](/docs/web.md) reference page.

### Using the REST API

For developers, there is a REST API to fetch data in the JSON format. This is a standard way to communicate the data programmatically and it allows you to handle the data in any way you desire.

> TODO: put examples of API calls

For more information, please see the [REST API](/docs/api.md) reference page.

## Development Guide

### Build

```
$ mvn package
```

> TODO: describe about different maven profiles and how to build jar/war

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
