﻿
Chapter 2: User-Facing Backend

### Ticket: Get Comments

Problem:

**User Story**

"As a user, I want to be able to view comments for a movie when I look at the movie detail page."

----------

**Task**

For this ticket, you'll be required to extend the **getMovie** method in MovieDao.java so that it also fetches the comments for a given movie.

The comments should be returned in order from most recent to least recent using the date key.

Movie comments are stored in the comments collection, so this task can be accomplished by performing a $lookup. Refer to the Aggregation [Quick Reference](https://docs.mongodb.com/manual/reference/operator/aggregation/lookup/?jmp=university#join-conditions-and-uncorrelated-sub-queries) for the specific syntax.

----------

**MFlix Functionality**

Once this ticket is completed, each movie's comments will be displayed on that movie's detail page.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=GetCommentsTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

Or run Application.java from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant tests, what is the validation code for **Get Comments**?

Hint: We need to sort the comments in the $lookup stage.
