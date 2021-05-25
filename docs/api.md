# Usage (REST API Server)

## Running a Server

```
$ mvn spring-boot:run
```

## API Specification

### Preliminaries

- If you want to know which genres, occupations, etc. you can use as an input,
  please see [Available Inputs](available-inputs.md) page.
- The request/response bodies are JSON objects. (`Content-type` is always `application/json`.)
- If unrelated fields are given in the request body, they will be ignored.
- If a request object does not follow the specification (e.g. a required field is not given in the request body, a field contains invalid value, etc.), the server will return a response with `400 BAD REQUEST` status code. The body will contain a field `message` which describes the error and a field `status` which describes status code.

### Movie Recommendation Based on User Information

- **Endpoint :** `/users/recommendations`

- **HTTP Method :** `GET`

#### Description

Returns top 10 movies rated by similar users of given information. You can optionally specify genres so that the returned movies have at least one of the specified genres.

If you don't want to specify some of gender/age/occupation information, please put empty strings (`""`) at those fields.

Gender and occupation are matched case-insensitively. Also, any special characters and whitespaces will be ignored.

#### Request

**Required parameters**

- `gender` (string) : User information - gender. Only `"F"` and `"M"` are available.
- `age` (string) : User information - age. It should be at least 0.
- `occupation` (string) : User information - occupation. The available inputs are listed in [Available Inputs](available-inputs.md) page.

**Optional Parameters**

- `genres` (string) : If this field is specified, the returned movies will have at least one of the specified genres. To specify multiple genres, you can separate the genres with vertical bars (`|`).

#### Response

Returns a list of movie information objects. Movie information objects has following fields:

- `title` (string) : The title of the movie.
- `genre` (string) : The genres of the movie.
- `imdb` (string) : IMDB link of the movie.

#### Example Request

```shell
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "other", "genres": ""}'
```

#### Example Response

```json
[
	{"title":"Our Town (1940)","genre":"Drama","imdb":"http://www.imdb.com/title/tt0032881"},
	{"title":"Two Women (La Ciociara) (1961)","genre":"Drama|War","imdb":"http://www.imdb.com/title/tt0054749"},
	{"title":"Criminal Lovers (Les Amants Criminels) (1999)","genre":"Drama|Romance","imdb":"http://www.imdb.com/title/tt0205735"},
	...
]
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
