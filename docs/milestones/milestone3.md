## Milestone 3

### What We've Finished
- Seperated command line program (Milestone1 & Milestone2) from API server
- Designed and implemented related movie recommendation algorithm
- Introduced Spring Boot and implemented REST API for recommendation services
- Achived high branch coverage of unit test
- Adopted Project Lombok for simplifying domain model objects

### About Recommendation by Movie Algorithm

#### Our algorithm is here
[`com.cse364.app.RecommendByMovieService`](/src/main/java/com/cse364/app/RecommendByMovieService.java)

#### Design Choice

If one person watched a movie, he/she has a relatively high possibility to have watched another movie similar to it and reviewed it.
Thus, in a large scale, filtering it by matching number of genres and average ratings from those users will give movies recommendable and similar to the given one.

#### Algorithm Description

1. Find the movie by given title.
2. Find all users who rated the movie.
3. Find all ratings from users of step 2.
4. Group all movies that had ratings in step 3, in decreasing order of number of matching genres.
5. Calculate average per movie from all ratings in step 3.
6. Return top `limit` movies, in more significant order the matching genres and in less significant order the average rating.

### Roles

한동규 @queuedq
- Adopted Project Lombok for simplifying domain model objects
- Refactored ranking service using user info similarity notion
- Modified validation for age and gender for ranking service
- Helped troubleshooting Spring Boot adoption
- Wrote API spec documentation

심재환 @jaehwan1912
- Recommendation by Movie algorithm design & implementation & test
- Helped troubleshooting REST input problem & test modification
- Wrote README

홍준화 @junwha0511
- Set up Spring Boot
- Implemented REST API (mapping to services)
- Wrote API server integration test using MockMVC
- Handled API server errors
