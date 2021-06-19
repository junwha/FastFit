# FastFit Web App

When you run a server locally, it uses the port 8080 by default. So you can access the web page by connecting to http://localhost:8080/.

## Pages

### Main page

- **Address :** `/`, `/index.html`

> TODO: describe the page structure, including the buttons and sections

On the top part, there are some introduction texts and buttons that goes to each custom recommendation page.

"Our recommendations" section below the top part shows the overall top 10 recommended movies. Then comes the "by-genre" sections. They show top 10 recommended movies by each genre.

The entry/number of Genres and Movies to be shown per each section is determined by entry of list in server program by Thymeleaf.
You can change it by modifying it.

### Recommendation by your information

- **Address :** `/users/recommendations.html`

On the top part, there are some introduction texts, field where one can input Gender, Age, Occupation and Genres, and a button to send the argument to server. If there is a wrong input, it will be notified with a red text.

"Our recommendations" section below the top part showes the top 10 recommended movies by our "recommendation by information" algorithm.

Some input fields only accepts a limited number of values. To check which values are allowed, please see the [Available Inputs](/docs/available-inputs.md) page.

The number of movies to be shown in "Our recommendations" section is determined by entry of list in server program by Thymeleaf.
You can change it by modifying it.

### Recommendation by your favorite movie

- **Address :** `/movies/recommendations.html`

On the top part, there are some introduction texts, field where one can input movie title with year, and a button to send the argument to server. If there is a wrong input, it will be notified with a red text.

"Our recommendations" section below the top part showes the top 10 recommended movies by our "recommendation by movie" algorithm.

Some input fields only accepts a limited number of values. To check which values are allowed, please see the [Available Inputs](/docs/available-inputs.md) page.

The number of movies to be shown in "Our recommendations" section is determined by entry of list in server program by Thymeleaf.
You can change it by modifying it.

