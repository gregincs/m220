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
	}
	catch (Exception e) {
		return false;
	}
}
