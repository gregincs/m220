/**
 * Adds a new Comment to the collection. The equivalent instruction in the mongo shell would be:
 *
 * <p>db.comments.insertOne({comment})
 *
 * <p>
 *
 * @param comment - Comment object.
 * @throw IncorrectDaoOperation if the insert fails, otherwise
 * returns the resulting Comment object.
 */
public Comment addComment(Comment comment) {

	// TODO> Ticket - Update User reviews: implement the functionality that enables adding a new
	// comment.
	// TODO> Ticket - Handling Errors: Implement a try catch block to
	// handle a potential write exception when given a wrong commentId.
	try {
		if( comment.getId() == null || comment.getId().isEmpty() ) {
			throw new IncorrectDaoOperation("Comment Id must not be null");
		}

		commentCollection.insertOne(comment);
		return getComment(comment.getId());
	} catch (IncorrectDaoOperation e){
		throw new IncorrectDaoOperation(e.getMessage());
	}
}

/**
 * Updates the comment text matching commentId and user email. This method would be equivalent to
 * running the following mongo shell command:
 *
 * <p>db.comments.update({_id: commentId}, {$set: { "text": text, date: ISODate() }})
 *
 * <p>
 *
 * @param commentId - comment id string value.
 * @param text      - comment text to be updated.
 * @param email     - user email.
 * @return true if successfully updates the comment text.
 */
public boolean updateComment(String commentId, String text, String email) {

	// TODO> Ticket - Update User reviews: implement the functionality that enables updating an
	// user own comments
	// TODO> Ticket - Handling Errors: Implement a try catch block to
	// handle a potential write exception when given a wrong commentId.
	try {
		if(commentId == null || commentId.isEmpty()) {
			throw new IncorrectDaoOperation("Comment Id must not be null");
		}

		if( text == null || text.isEmpty()){
			throw new IncorrectDaoOperation("Text must not be null");
		}
		Comment comment = getComment(commentId);

		if (!email.equals(comment.getEmail())) {
			return false;
		} else {
			comment.setText(text);

			UpdateOptions options = new UpdateOptions();
			options.upsert(true);

			Bson query = Filters.and(Filters.eq("_id", new ObjectId(commentId)), Filters.eq("email", email));

			UpdateResult result = commentCollection.replaceOne(query, comment);

			return result.wasAcknowledged();
		}
	} catch (IncorrectDaoOperation e) {
		throw  new IncorrectDaoOperation(e.getMessage());
	}
}