# Usage (REST API Server)

## Running a Server

```
$ mvn spring-boot:run
```

or


```
$ java -jar target/cse364-project-1.0-SNAPSHOT.jar
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
- `age` (string) : User information - age. You should wrap the integer value with `""` so to make string type. Age should be at least 0.
- `occupation` (string) : User information - occupation. The available inputs are listed in [Available Inputs](available-inputs.md) page.

**Optional Parameters**

- `genres` (string) : If this field is specified, the returned movies will have at least one of the specified genres. To specify multiple genres, you can separate the genres with vertical bars (`|`).

#### Response

Returns a list of movie information objects. Movie information objects has following fields:

- `title` (string) : The title of the movie.
- `genres` (string) : The genres of the movie.
- `imdb` (string) : IMDB link of the movie.

#### Example Request

```shell
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender": "M", "age": "", "occupation": "other", "genres": ""}'
```

#### Example Response

```json
[
    {"title":"Our Town (1940)","genres":"Drama","imdb":"http://www.imdb.com/title/tt0032881"},
    {"title":"Two Women (La Ciociara) (1961)","genres":"Drama|War","imdb":"http://www.imdb.com/title/tt0054749"},
    {"title":"Criminal Lovers (Les Amants Criminels) (1999)","genres":"Drama|Romance","imdb":"http://www.imdb.com/title/tt0205735"},
    ...
]
```

### Related Movie Recommendation

- **Endpoint :** `/users/recommendations`

- **HTTP Method :** `GET`

#### Description

Returns movie recommendations based on the given movie title. The maximum number of movies returned can optionally be specified by the `limit` field.

You should include the year of release for the movie in the title field, because there are movies that have the same names but different years of release. The title is matched case-insensitively and any whitespaces will be ignored.

#### Request

**Required parameters**

- `title` (string) : The movie title. You should include the year of release for the movie. (See an example below.)

**Optional Parameters**

- `limit` (string) : If this field is specified, at most `limit` movies will be returned. You should wrap the integer value with `""` so to make string type. If this field is not specified, it defaults to 10.

#### Response

Returns a list of movie information objects. Movie information objects has following fields:

- `title` (string) : The title of the movie.
- `genres` (string) : The genres of the movie.
- `imdb` (string) : IMDB link of the movie.

#### Example Request

```shell
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title": "Toy story (1995)", "limit": "10"}'
```

#### Example Response

```json
[
    {"title":"Goofy Movie, A (1995)","genres":"Animation|Children's|Comedy|Romance","imdb":"http://www.imdb.com/title/tt0113198"},
    {"title":"Aladdin (1992)","genres":"Animation|Children's|Comedy|Musical","imdb":"http://www.imdb.com/title/tt0827990"},
    {"title":"Space Jam (1996)","genres":"Adventure|Animation|Children's|Comedy|Fantasy","imdb":"http://www.imdb.com/title/tt0117705"},
    ...
]
```
