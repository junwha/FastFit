# CSE-364-Software-Engineering

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

## Usage (Server & Client)

## Serverside

```
$ mvn spring-boot:run
```

## Clientside

You can send a GET request and a correctly formed JSON object to get the results as a JSON object.
 
### Movie Recommendation based on User Information (and Genres)

```
$ curl -X GET [http://address_to_server:8080/users/recommendations] -H 'Content-type:application/json' -d '{"gender": "[gender]", "age": "[age]", "occupation": "[occupation]", "genres": "[genre1|genre2|...]"}'
```

You can send a GET request with a JSON object including user informations(gender, age, occupation) and genre(s) as inputs, and server will send you top 10 movies rated by similar users of given information. If genre(s) are specified, all output movies will have at least one of the specified genre(s).

For multiple input of genres, you can separate the genres with vertical bars `|`.
If you do not want to specify an information, please include "info_name": "" within the JSON object.
Genre/occupation inputs are case-insensitive, and any special characters and whitespaces will be ignored.

### Movie Recommendation based on one Movie Title

```
$ curl -X GET [http://address_to_server:8080/movies/recommendations] -H 'Content-type:application/json' -d '{"title": "[title_of_the_movie(year_of_release)]"[,"limit": "max_number_of_list_of_movie"]}'
```

You can send a GET request with a JSON object including a movie title and optionally limit number as inputs, and server will send you the list of recommended movies(at max limit) based on the given movie. You should include the year of release for the movie as shown in the use reference above.

If limit is not specified, server will send you at max 10 movies.
Title input is case-insensitive, and any whitespace will be ignored.

## Usage (Command Line)

### Average Rating

```
$ java -cp target/cse364-project-1.0-SNAPSHOT.jar com.cse364.CommandLineMain [genre1|genre2|...] [occupation]
```

The program takes genre(s) and an occupation as inputs, and shows the average rating score of all movies having given genres, rated by the people with given occupation.

For multiple input of genres, you can separate the genres with vertical bars `|`.
When you use special characters (including `|`) or whitespaces, please enclose each argument with double quotes.
Genre/occupation inputs are case-insensitive, and any special characters and whitespaces will be ignored.

### Average Rating Example

```
$ java ... "adventure|children's" artist
Average rating of movies with genres [Adventure, Children's]
rated by people with occupation [Artist]
is [3.177143].
```

### Movie Recommendation based on User Information (and Genres)

```
$ java -cp target/cse364-project-1.0-SNAPSHOT.jar com.cse364.CommandLineMain [gender] [age] [occupation] [genre1|genre2|...]
```

The program takes user informations(gender, age, occupation) and optionally genre(s) as inputs, and shows top 10 movies rated by similar users of given information. If genre(s) are specified, all output movies will have at least one of the specified genre(s).

If you do not want to specify a user information(gender, age or occupation), please write two double quotes at the place of that argument.

For multiple input of genres, you can separate the genres with vertical bars `|`.
When you use special characters (including `|`) or whitespaces, please enclose each argument with double quotes.
Genre/occupation inputs are case-insensitive, and any special characters and whitespaces will be ignored.

### Movie Recommendation Example

```
$ java ... "M" 25 other
The movie we recommend are:
Two Family House (2000) (http://www.imdb.com/title/tt0202641)
Our Town (1940) (http://www.imdb.com/title/tt0032881)
...
```

```
$ java ... F "" artist "Action|Comedy"
The movie we recommend are:
Love & Sex (2000) (http://www.imdb.com/title/tt0234137)
Kelly's Heroes (1970) (http://www.imdb.com/title/tt0065938)
...
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

### List of Possible Genders

Either `F` or `M`, where `F` means femail and `M` means male.

### Range of Possible Age

Input age must be greater or equal than 0.

## Milestone 1

### What We've Finished

- Created Dockerfile for environment setup.
- Created run.sh for cloning directory / building & executing program.

- Implemented program that calculates the average rating score of the movies with specified genres, rated by people with given occupation.

### Roles

한동규 @queuedq
- Suggestions for Git/Github utilization, coding conventions, overall advices, etc.
- Docker setup
- Overall refactoring
- Testing, Exception handling

심재환 @jaehwan1912
- Input reading/parsing and average rating method design & implementation
- Exception handling
- Average rating method refactoring
- Documentation

홍준화 @junwha0511
- Dockerfile draft
- Java project creation
- Data (.dat file) reading/parsing design & implementation
- Revising average rating method implementation 
- Rating/Occupation class refactoring

## Milestone 2

### What We've Finished

- Designed and implemented a movie ranking algorithm
- Introduced The Onion Architecture
- Achieved high branch coverage of unit tests (using JaCoCo)

### About Ranking Algorithm

#### Our algorithm is here
[`com.cse364.app.RankingService`](src/main/java/com/cse364/app/RankingService.java)
 
#### Terminology

- **Similar user** : A user that matches all given user information (gender, age, occupation).
- **Secondary similar user**: A user that matches given user information only partially.
- **Match count** : Number of matching properties of secondary similar user. 

#### Algorithm Description

1. Find all similar users according to the given user information. 
2. Calculate average of all ratings for each movie rated by similar users.
3. If the number of movie rated by similar user is larger or equal than `N`, return top `N` movies.
4. Otherwise, repeat finding secondary similar users by decrementing match count. 
5. Append the new rankings obtained from secondary similar users to the ranking list, until the list has `N` movies.

### Roles

한동규 @queuedq
- JaCoCo setup for checking branch coverage
- Introduced The Onion Architecture
  - Introduced repository pattern
- Infrastructure layer redesign
  - Refactored DataLoader using DTO so that it becomes unit-testable
  - Separated Config (initializing repositories and services), Controller (UI) from Main
  - Separated validation logic (as app service) from Controller

심재환 @jaehwan1912
- Input parsing, error handling and connecting to services in Controller
- Ranking algorithm improvement
  - Implemented ranking algorithm for secondary similar users
  - Refactoring
- Writing unit tests for higher branch coverage

홍준화 @junwha0511
- Data loading (link.dat)
- Unit test for existing classes from Milestone 1
- Ranking algorithm design & implementation
  - Investigated existing algorithms
  - Designed ranking algorithm considering both effectiveness and efficiency
  - Implemented ranking algorithm for (primary) similar users

## Milestone 3

### What We've Finished

### About Recommendation by Movie Algorithm

#### Our algorithm is here
[`com.cse364.app.RecommendByMovieService`](src/main/java/com/cse364/app/RecommendByMovieService)

#### Terminology

#### Algorithm Description

1. Find the movie by given title.
2. Find all users who rated the movie.
3. Find all ratings from users of step 2.
4. Group all movies that had ratings in step 3, in decreasing order of number of matching genres.
5. Calculate average per movie from all ratings in step 3.
6. Return top `limit` movies, in more significant order the matching genres and in less significant order the average rating.

### Roles

@queuedq


@jaehwan1912
- Modified API and Test to correctly accept JSON as input 
- Recommendation by Movie algorithm design & implementation
- README

@junwha0511
- Spring setting
- Implemented REST API (MVC, Mapping)
- Write Spring Test (MockMVC)
- Spring Error handling

