# CSE-364-Software-Engineering

## Docker Build and Run

```
$ docker -t cse364 .
$ docker run -it cse364
root@[CONTAINER_ID]:~/project# . run.sh
```

## Build

```
$ mvn package
```

## Usage

```
$ java -cp target/cse364-project-1.0-SNAPSHOT.jar com.cse364.Main [genre1|genre2|...] [occupation]
```

Example:

```
$ java ... "Adventure|Children's" artist
Average rating of movies with genres [adventure, children's]
rated by people with occupation [artist]
is [3.177143].
```

## What We've Finished
Create Dockerfile
Create run.sh
Program that calculates the average rating score of the movies of specific genres given by specific occupation

## Roles

한동규 queuedq
- Suggestion for Git/Github utilization, coding convention, overall advice etc.
- Docker setting
- Refactoring
- Testing, Error handling

심재환 jaehwan1912
- Input reading/parsing and rating averaging design/implementation
- Exception handling
- Rating averaging refactoring

홍준화 junwha0511
- Dockerfile draft
- Java project creation
- Data reading/parsing design/implementation
- Rating averaging help
- Rating / Occupation refactoring
