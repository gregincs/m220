
Chapter 2: User-Facing Backend

### Ticket: Paging

Problem:

**User Story**

"As a user, I'd like to get the next page of results for my query by scrolling down in the main window of the application."

----------

**Task**

For this ticket, you'll be required to modify the **getMoviesByGenre** method in MovieDao.java, to allow for paging.

----------

**MFlix Functionality**

The UI is already asking for infinite scroll! You may have noticed a message stating "paging not implemented" when scrolling to the bottom of the page.

Once this ticket is completed, this message will go away, and scrolling to the bottom of the page will result in a new page of movies.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=PagingTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

or run the Application.java from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant tests, what is the validation code for **Paging**?
