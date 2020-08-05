﻿
Chapter 4: Resiliency

### Ticket: Connection Pooling

Problem:

**Task**

For this ticket, you'll be required to modify the configuration of option that defines our maxPoolSize in the **application.properties** file, and set the maximum size of the connection pool to **50** connections.

By changing the properties file, the MongoClient should be configured to use no more than 50 connections. Revise the [ConnectionString](http://mongodb.github.io/mongo-java-driver/3.6/javadoc/com/mongodb/ConnectionString.html) java class api.

----------

**Testing and Running the Application**

If the application is already running, **stop the application** and run the unit tests for this ticket by executing the following command:

mvn test -Dtest=ConnectionPoolingTest

Once the unit tests are passing, run the application with:

mvn spring-boot:run

Or deploy the application from your IDE.

Now proceed to the [status page](http://localhost:5000/status) to run the full suite of integration tests and get your validation code.

To have the application use the changes that you implemented for this ticket, make sure to **restart the application** after you completed those changes. Also, only refresh the status page to see the new results of the tests, after the application has been restarted.

After passing the relevant tests, what is the validation code for **Connection Pooling**?
