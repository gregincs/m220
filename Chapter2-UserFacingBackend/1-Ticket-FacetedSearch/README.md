
Chapter 2: User-Facing Backend

### Ticket: Faceted Search

Problem:

**User Story**

"As a user, I want to be able to filter cast search results by one facet, **metacritic** rating."

----------

**Task**

For this ticket, you'll be required to modify the **getMoviesCastFaceted** method in MovieDao.java, so the MFlix application can perform faceted searches. You will find a more detailed description of the task as a comment in the MovieDao.java file.

----------

**MFlix Functionality**

Once the change is implemented for this ticket, the user interface will reflect this change when you search for cast (e.g. "Tom Hanks"), then additional search parameters will be added as shown below:

> ![https://university-courses.s3.amazonaws.com/m220/facetedSearchScreenshot.png](https://university-courses.s3.amazonaws.com/m220/facetedSearchScreenshot.png)

_What is a Faceted Search?_

Faceted search is a way of narrowing down search results as search parameters are added. For example, let's say MFlix allows users to filter movies by a rating from 1 to 10, but Kate Winslet has only acted in movies that have a rating of 6 or higher.

If we didn't specify any other search parameters, MFlix would allow us to choose a rating between 1 and 10. But if we first search for Kate Winslet, MFlix would only let us choose a rating between 6 and 10, because none of the movie documents in the result set have a rating below 6.

If you're curious, you can read more about Faceted Search [here](https://en.wikipedia.org/wiki/Faceted_search).

_Faceted Search in MFlix_

Faceted searches on the MFlix site cannot be supported with the basic search method **getMovies**. For faceted searches, the application must use the Aggregation Framework.

The method **getMoviesCastFaceted** uses the Aggregation Framework, and the individual _stages_ in the pipeline have already been completed. Follow instructions in the MovieDao.java file to add the required stages to the pipeline object.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=FacetedSearchTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

or run the Application.java from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant tests, what is the validation code for **Faceted Search**?
