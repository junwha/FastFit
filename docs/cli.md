## Usage (Command Line Program)

### Preliminaries

- If you want to know which genres, occupations, etc. you can use as an input,
  please see [Available Inputs](available-inputs.md) page.

### Average Rating

```
$ java -cp target/cse364-project-1.0-SNAPSHOT.jar -Dloader.main=com.cse364.CommandLineMain org.springframework.boot.loader.PropertiesLauncher [genre1|genre2|...] [occupation]
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
$ java -cp target/cse364-project-1.0-SNAPSHOT.jar -Dloader.main=com.cse364.CommandLineMain org.springframework.boot.loader.PropertiesLauncher [gender] [age] [occupation] [genre1|genre2|...]
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
