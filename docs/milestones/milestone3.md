## Milestone 3

### What We've Finished
- Seperated Command Line Program (Milestone1 & Milestone2)
- Designed and Implemented recommendation by movie algorithm
- Introduced Spring boot and implemented REST API for recommendation services
- Achived high branch coverage of unit test
- Adopted Lombok for simplifying domain model objects

### About Recommendation by Movie Algorithm

#### Our algorithm is here
[`com.cse364.app.RecommendByMovieService`](/src/main/java/com/cse364/app/RecommendByMovieService)

#### Terminology

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

심재환 @jaehwan1912
- Recommendation by Movie algorithm design & implementation & test
- Helped troubleshooting REST input problem & test modification
- README

홍준화 @junwha0511
- Spring setting
- Implemented REST API (MVC, Mapping)
- Write Spring Test (MockMVC)
- Spring Error handling
