## Usage (REST API Server)

## Running a Server

```
$ mvn spring-boot:run
```

## API Specification

### Preliminaries

- If you want to know which genres, occupations, etc. you can use as an input,
  please see [Available Inputs](available-inputs.md) page.
- You can send a GET request with JSON body.
- The response is returned as a JSON object.

### Movie Recommendation based on User Information (and Genres)

```
$ curl -X GET [http://address_to_server:8080/users/recommendations] -H 'Content-type:application/json' -d '{"gender": "[gender]", "age": "[age]", "occupation": "[occupation]", "genres": "[genre1|genre2|...]"}'
```

You can send a GET request with a JSON object including user informations(gender, age, occupation) and genre(s) as inputs, and server will send you top 10 movies rated by similar users of given information. If genre(s) are specified, all output movies will have at least one of the specified genre(s).

For multiple input of genres, you can separate the genres with vertical bars `|`.
If you do not want to specify an information, please include "info_name": "" within the JSON object.
Genre/occupation inputs are case-insensitive, and any special characters and whitespaces will be ignored.

### Movie Recommendation based on User Information (and Genres) Example

```
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "other", "genres": ""}'
[{"title":"Our Town (1940)","genre":"Drama","imdb":"http://www.imdb.com/title/tt0032881"},{"title":"Two Women (La Ciociara) (1961)","genre":"Drama|War","imdb":"http://www.imdb.com/title/tt0054749"},{"title":"Criminal Lovers (Les Amants Criminels) (1999)","genre":"Drama|Romance","imdb":"http://www.imdb.com/title/tt0205735"},{"title":"Lured (1947)","genre":"Crime","imdb":"http://www.imdb.com/title/tt0039589"},{"title":"Freedom for Us (� nous la libert� ) (1931)","genre":"Comedy","imdb":"http://www.imdb.com/title/tt0022599"},{"title":"Bells, The (1926)","genre":"Crime|Drama","imdb":"http://www.imdb.com/title/tt0016640"},{"title":"Vampyros Lesbos (Las Vampiras) (1970)","genre":"Horror","imdb":"http://www.imdb.com/title/tt0066380"},{"title":"Ulysses (Ulisse) (1954)","genre":"Adventure","imdb":"http://www.imdb.com/title/tt0047630"},{"title":"Room at the Top (1959)","genre":"Drama","imdb":"http://www.imdb.com/title/tt0053226"},{"title":"Three Ages, The (1923)","genre":"Comedy","imdb":"http://www.imdb.com/title/tt0014538"}]
```

### Movie Recommendation based on one Movie Title

```
$ curl -X GET [http://address_to_server:8080/movies/recommendations] -H 'Content-type:application/json' -d '{"title": "[title_of_the_movie(year_of_release)]"[,"limit": "max_number_of_list_of_movie"]}'
```

You can send a GET request with a JSON object including a movie title and optionally limit number as inputs, and server will send you the list of recommended movies(at max limit) based on the given movie. You should include the year of release for the movie as shown in the use reference above.

If limit is not specified, server will send you at max 10 movies.
Title input is case-insensitive, and any whitespace will be ignored.

### Movie Recommendation based on one Movie Title Example

```
$ curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "Toy story(1995)", "limit": "10"}'
[{"title":"Goofy Movie, A (1995)","genre":"Animation|Children's|Comedy|Romance","imdb":"http://www.imdb.com/title/tt0113198"},{"title":"Aladdin (1992)","genre":"Animation|Children's|Comedy|Musical","imdb":"http://www.imdb.com/title/tt0827990"},{"title":"Space Jam (1996)","genre":"Adventure|Animation|Children's|Comedy|Fantasy","imdb":"http://www.imdb.com/title/tt0117705"},{"title":"Aladdin and the King of Thieves (1996)","genre":"Animation|Children's|Comedy","imdb":"http://www.imdb.com/title/tt0115491"},{"title":"Hercules (1997)","genre":"Adventure|Animation|Children's|Comedy|Musical","imdb":"http://www.imdb.com/title/tt0119282"},{"title":"Jungle Book, The (1967)","genre":"Animation|Children's|Comedy|Musical","imdb":"http://www.imdb.com/title/tt0061852"},{"title":"Lady and the Tramp (1955)","genre":"Animation|Children's|Comedy|Musical|Romance","imdb":"http://www.imdb.com/title/tt0048280"},{"title":"Little Mermaid, The (1989)","genre":"Animation|Children's|Comedy|Musical|Romance","imdb":"http://www.imdb.com/title/tt0097757"},{"title":"Steamboat Willie (1940)","genre":"Animation|Children's|Comedy|Musical","imdb":"http://www.imdb.com/title/tt0019422"},{"title":"American Tail, An (1986)","genre":"Animation|Children's|Comedy","imdb":"http://www.imdb.com/title/tt0090633"}]
```
