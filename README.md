# CSE-364-Software-Engineering

## What we've done in each milestones

- [Milestone 1](/docs/milestones/milestone1.md)
- [Milestone 2](/docs/milestones/milestone2.md)
- [Milestone 3](/docs/milestones/milestone3.md)
- [Milestone 4](/docs/milestones/milestone4.md)

---

## Docker Build and Run

```
$ docker build -t cse364 .
$ docker run -it cse364
root@[CONTAINER_ID]:~/project# . run.sh
```

## Build

```
$ mvn package
```

## Test

```
$ mvn test
```

How to check coverage: After running the test command, open `target/site/jacoco/index.html` file in the browser.

## Usage

- [Command Line Program](/docs/cli.md)
- [REST API Server](/docs/api.md)
- [FastFit Webapp](/docs/web.md)
