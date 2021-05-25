## Milestone 2

### What We've Finished

- Designed and implemented a movie ranking algorithm
- Introduced The Onion Architecture
- Achieved high branch coverage of unit tests (using JaCoCo)

### About Ranking Algorithm

#### Our algorithm is here
[`com.cse364.app.RankingService`](/src/main/java/com/cse364/app/RankingService.java)

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
