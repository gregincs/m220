/**
 * Deletes comment that matches user email and commentId.
 *
 * @param commentId - commentId string value.
 * @param email     - user email value.
 * @return true if successful deletes the comment.
 */
public boolean deleteComment(String commentId, String email) {
	// TODO> Ticket Delete Comments - Implement the method that enables the deletion of a user
	// comment
	// TIP: make sure to match only users that own the given commentId
	// TODO> Ticket Handling Errors - Implement a try catch block to
	// handle a potential write exception when given a wrong commentId.
	try {
		if(commentId == null || commentId.isEmpty()){
			throw new IllegalArgumentException("Comment Id must not be null or empty");
		}

		if(email == null) {
			throw new IllegalArgumentException("Email must not be null or empty");
		}

		Bson query = Filters.and(Filters.eq("_id", new ObjectId(commentId)), Filters.eq("email", email));
		DeleteResult result = commentCollection.deleteOne(query);

		if(result.getDeletedCount() == 1) {
			return true;
		} else {
			return false;
		}

	} catch (IllegalArgumentException e) {
		throw new IllegalArgumentException(e.getMessage());
	}
}