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
- Implemented new features of program which receive 3 and 4 arguments
- Designed and implemented Movie Ranking algorithm 
- Ranking algorithm make Top N Ranking with similar user and can specified with some genres

- Installed JaCoCo and Implemented Unit Test
- Refactored previous works and introduced Onion Architecture

### Roles

한동규 @queuedq
- JaCoCo installing for testing
- Introduced Onion Architecture
  - Repository Pattern
- Infrastructure Layer redesign
  - Unit-testable DataLoader class by using DTO
  - Logic seperation from Main class (Config: initializing Repositoies and Services, Controller: UI)
  - Validation Service seperation from Controller class

심재환 @jaehwan1912


홍준화 @junwha0511
- Data loading (link.dat)
- Unit test for existing class
- Ranking Algorithm Design & Implement
