# How It Works

## Recommendation by your information

### Terminology

- **Similar user** : A user that matches all given user information (gender, age, occupation).
- **Secondary similar user**: A user that matches given user information only partially.
- **Match count** : Number of matching properties of secondary similar user.

### Algorithm Description

1. Find all similar users according to the given user information. If there's too many of them, sample up to 100 users.
2. Calculate average of all ratings for each movie rated by similar users.
3. If the number of movie rated by similar user is larger or equal than `N`, return top `N` movies.
4. Otherwise, repeat finding secondary similar users by decrementing match count.
5. Append the new rankings obtained from secondary similar users to the ranking list, until the list has `N` movies.

### Code

[`com.cse364.app.RankingService`](/src/main/java/com/cse364/app/RankingService.java)

## Recommendation by your favorite movie

### Design Choice

If one person watched a movie, he/she has a relatively high possibility to have watched another movie similar to it and reviewed it.
Thus, in a large scale, filtering it by matching number of genres and average ratings from those users will give movies recommendable and similar to the given one.

### Algorithm Description

1. Find the movie by given title.
2. Find all users who rated the movie. If there's too many of them, sample up to 100 users.
3. Find all ratings from users of step 2.
4. Group all movies that had ratings in step 3, in decreasing order of number of matching genres.
5. Calculate average per movie from all ratings in step 3.
6. Return top `limit` movies, in more significant order the matching genres and in less significant order the average rating.

### Code

[`com.cse364.app.RecommendByMovieService`](/src/main/java/com/cse364/app/RecommendByMovieService.java)
