﻿
Chapter 2: User-Facing Backend

### Ticket: User Management

Problem:

**User Story**

"As a user, I should be able to register for an account, log in, and logout."

----------

**Task**

For this Ticket, you'll be required to implement all the methods in UserDao.java that are marked for the **User Management** ticket. Specifically, you'll implement:

-   **createUserSession**
-   **getUser**
-   **getUserSession**
-   **deleteUserSession**
-   **deleteUser**

Registering should create an account and log the user in, ensuring an entry is made in the **sessions** collection. There is a [unique index](https://docs.mongodb.com/manual/core/index-unique/?jmp=university) on the user_id field in **sessions**, so we can efficiently query on this field.

----------

**MFlix Functionality**

Once this ticket is completed, users will be able to register for a new account, log in, logout, and delete their account.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=UserTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

or run the Application.java from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant tests, what is the validation code for **User Management**?
