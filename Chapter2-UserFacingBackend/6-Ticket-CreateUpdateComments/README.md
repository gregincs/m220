﻿
Chapter 2: User-Facing Backend

### Ticket: Create/Update Comments

Problem:

**User Story**

"As a user, I want to be able to post comments to a movie page as well as edit my own comments."

----------

**Task**

For this ticket, you'll be required to implement the **addComment** and **updateComment** methods in CommentDao.

Ensure that **updateComment** only allows users to update their own comments, and no one else's comments.

----------

**MFlix Functionality**

Once this ticket is completed, users will be able to post comments on their favorite (and least favorite) movies, and edit comments they've posted.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=UpdateCreateCommentTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

Our launch the Application from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant unit tests, what is the validation code for **Create/Update Comments**?
