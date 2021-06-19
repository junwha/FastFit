# FastFit Web App

When you run a server locally, it uses the port 8080 by default. So you can access the web page by connecting to http://localhost:8080/.

## Pages

### Main page

- **Address :** `/`, `/index.html`

In FastFit, you can get movie recommendations by your information or your favorite movie. There are buttons on the main page that links to each recommendation page.

Or, you can check our own recommendations at the bottom. "All Movies" section shows the overall top 10 recommended movies. Next comes the genre sections. They show top 10 recommended movies by each genre.

### Recommendation by your information

- **Address :** `/users/recommendations.html`

Enter your gender, age, occupation, or a list of your favorite movie genres here. We'll suggest 10 movies you might enjoy according to the provided information. You can omit some fields if you want.

Some input fields only accepts a limited number of values. If you enter an invalid input, an error message will be displayed. Please see the [Available Inputs](/docs/available-inputs.md) page to check which values are allowed.

The recommendation system works as follows: We first find the users similar to you in the database. Then we gather the movies they enjoyed. We sort them by average rating and then provide you the top 10 movies as a recommendation.

### Recommendation by your favorite movie

- **Address :** `/movies/recommendations.html`

If you enjoyed a movie and are looking for similar ones, you've come to the right place. Enter your favorite movie title with its release year in the parentheses. (e.g. `Toy Story (1995)`) We'll recommend 10 similar movies for you.

If you miswrite the movie title or don't include the release year, an error message will be displayed.

The recommendation system works as follows: We first find users who enjoyed the movie in the database. Then we gather the movies they enjoyed. We sort them by average rating and then provide you the top 10 movies as a recommendation.
