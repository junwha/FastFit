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

The program takes genre(s) and an occupation as inputs, and shows the average rating score of all movies having given genres, rated by the people with given occupation.

For multiple input of genres, you can separate the genres with vertical bars `|`.
When you use special characters (including `|`) or whitespaces, please enclose each argument with double quotes.
Genre/occupation inputs are case-insensitive, and any special characters and whitespaces will be ignored.

### Example

```
$ java ... "adventure|children's" artist
Average rating of movies with genres [Adventure, Children's]
rated by people with occupation [Artist]
is [3.177143].
```

### List of Possible Genres

- Action
- Adventure
- Animation
- Children's
- Comedy
- Crime
- Documentary
- Drama
- Fantasy
- Film-Noir
- Horror
- Musical
- Mystery
- Romance
- Sci-Fi
- Thriller
- War
- Western

### List of Possible Occupations

- Other
- Academic / Educator
- Artist
- Clerical / Admin
- College student / Grad student
- Customer service
- Doctor / Health care
- Executive / Managerial
- Farmer
- Homemaker
- K-12 student
- Lawyer
- Programmer
- Retired
- Sales / Marketing
- Scientist
- Self-employed
- Technician / Engineer
- Tradesman / Craftsman
- Unemployed
- Writer



## What We've Finished

Create Dockerfile for environment setup

Create run.sh for cloning directory / building & executing program
 
Implement program that calculates the average rating score of the movies of specific genres given by specific occupation

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
- Documentation

홍준화 junwha0511
- Dockerfile draft
- Java project creation
- Data reading/parsing design/implementation
- Rating averaging help
- Rating / Occupation refactoring
