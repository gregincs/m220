package mflix.api.daos;

import com.mongodb.ErrorCategory;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoWriteException;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.model.*;
import com.mongodb.client.result.DeleteResult;
import com.mongodb.client.result.UpdateResult;
import mflix.api.models.Session;
import mflix.api.models.User;
import org.bson.Document;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;
import org.bson.conversions.Bson;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.print.Doc;
import java.util.Map;

import static org.bson.codecs.configuration.CodecRegistries.fromProviders;
import static org.bson.codecs.configuration.CodecRegistries.fromRegistries;

@Configuration
public class UserDao extends AbstractMFlixDao {

    private final MongoCollection<User> usersCollection;
    //TODO> Ticket: User Management - do the necessary changes so that the sessions collection
    //returns a Session object
    private final MongoCollection<Session> sessionsCollection;

    private final Logger log;

    @Autowired
    public UserDao(
            MongoClient mongoClient, @Value("${spring.mongodb.database}") String databaseName) {
        super(mongoClient, databaseName);
        CodecRegistry pojoCodecRegistry =
                fromRegistries(
                        MongoClientSettings.getDefaultCodecRegistry(),
                        fromProviders(PojoCodecProvider.builder().automatic(true).build()));

        usersCollection = db.getCollection("users", User.class).withCodecRegistry(pojoCodecRegistry);
        log = LoggerFactory.getLogger(this.getClass());
        //TODO> Ticket: User Management - implement the necessary changes so that the sessions
        // collection returns a Session objects instead of Document objects.
        sessionsCollection = db.getCollection("sessions", Session.class).withCodecRegistry(pojoCodecRegistry);
    }

    /**
     * Inserts the `user` object in the `users` collection.
     *
     * @param user - User object to be added
     * @return True if successful, throw IncorrectDaoOperation otherwise
     */
    public boolean addUser(User user) {
            //TODO > Ticket: Durable Writes -  you might want to use a more durable write concern here!
            //TODO > Ticket: Handling Errors - make sure to only add new users
            // and not users that already exist.
        try {
            usersCollection.withWriteConcern(WriteConcern.W2).insertOne(user);
            return true;
        } catch (MongoWriteException e) {
            if (ErrorCategory.fromErrorCode( e.getCode() ) == ErrorCategory.DUPLICATE_KEY) {
                throw new IncorrectDaoOperation("The User is already in the database.");
            }
            return false;
        }
    }

    /**
     * Creates session using userId and jwt token.
     *
     * @param userId - user string identifier
     * @param jwt    - jwt string token
     * @return true if successful
     */
    public boolean createUserSession(String userId, String jwt) {
        //TODO> Ticket: User Management - implement the method that allows session information to be
        // stored in it's designated collection.
        //TODO > Ticket: Handling Errors - implement a safeguard against
        // creating a session with the same jwt token.

        try {
            UpdateOptions options = new UpdateOptions();
            options.upsert(true);

            Bson query = Filters.eq("user_id",userId);
            Bson update = Updates.set("jwt", jwt);

            sessionsCollection.updateOne(query, update, options);

            return  true;
        } catch (IncorrectDaoOperation e) {
            throw new IncorrectDaoOperation(e.getMessage());
        } catch (MongoWriteException e) {
            throw new MongoWriteException(e.getError(), e.getServerAddress());
        }
    }

    /**
     * Returns the User object matching the an email string value.
     *
     * @param email - email string to be matched.
     * @return User object or null.
     */
    public User getUser(String email) {
        User user = null;
        //TODO> Ticket: User Management - implement the query that returns the first User object.
        Document queryFilter = new Document("email", email);
        user = usersCollection.find(queryFilter).first();
        return user;
    }

    /**
     * Given the userId, returns a Session object.
     *
     * @param userId - user string identifier.
     * @return Session object or null.
     */
    public Session getUserSession(String userId) {
        //TODO> Ticket: User Management - implement the method that returns Sessions for a given
        // userId
        try {
            Document queryFilter = new Document("user_id", userId);
            Session session = sessionsCollection.find(queryFilter).first();
            return session;
        } catch (Exception e){
            return  null;
        }
    }

    public boolean deleteUserSessions(String userId) {
        //TODO> Ticket: User Management - implement the delete user sessions method
        try {
            Bson query = Filters.eq("user_id", userId);
            DeleteResult result = sessionsCollection.deleteOne(query);
            return result.wasAcknowledged();
        } catch (MongoWriteException e) {
            throw new MongoWriteException(e.getError(), e.getServerAddress());
        } catch (IncorrectDaoOperation e) {
            throw new IncorrectDaoOperation(e.getMessage());
        }
    }

    /**
     * Removes the user document that match the provided email.
     *
     * @param email - of the user to be deleted.
     * @return true if user successfully removed
     */
    public boolean deleteUser(String email) {
        // remove user sessions
        //TODO> Ticket: User Management - implement the delete user method
        //TODO > Ticket: Handling Errors - make this method more robust by
        // handling potential exceptions.
        try {
            if (deleteUserSessions(email)){
                Bson query = Filters.eq("email", email);
                DeleteResult result = usersCollection.deleteOne(query);
                return result.wasAcknowledged();
            } else {
                return  false;
            }

        } catch (MongoWriteException e) {
            throw new MongoWriteException(e.getError(), e.getServerAddress());
        } catch (IncorrectDaoOperation e) {
            throw new IncorrectDaoOperation(e.getMessage());
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Updates the preferences of an user identified by `email` parameter.
     *
     * @param email           - user to be updated email
     * @param userPreferences - set of preferences that should be stored and replace the existing
     *                        ones. Cannot be set to null value
     * @return User object that just been updated.
     */
    public boolean updateUserPreferences(String email, Map<String, ?> userPreferences) {
        //TODO> Ticket: User Preferences - implement the method that allows for user preferences to
        // be updated.
        //TODO > Ticket: Handling Errors - make this method more robust by
        // handling potential exceptions when updating an entry.
        try {
            UpdateOptions options = new UpdateOptions();
            options.upsert(true);
            if(userPreferences == null){
                throw new IncorrectDaoOperation("User preferences must not be null.");
            }

            Bson query = Filters.eq("email", email);
            Bson update = Updates.set("preferences", userPreferences);

            UpdateResult result = usersCollection.updateOne(query, update, options);

            return result.wasAcknowledged();
        } catch (IncorrectDaoOperation incorrectDaoOperation) {
            throw incorrectDaoOperation;
        } catch (MongoWriteException e) {
            throw new MongoWriteException(e.getError(), e.getServerAddress());
        } catch (Exception e) {
            return false;
        }
    }
}
