/**
 * Ticket: User Report - produce a list of users that comment the most in the website. Query the
 * `comments` collection and group the users by number of comments. The list is limited to up most
 * 20 commenter.
 *
 * @return List {@link Critic} objects.
 */
public List<Critic> mostActiveCommenters() {
	List<Critic> mostActive = new ArrayList<>();
	// // TODO> Ticket: User Report - execute a command that returns the
	// // list of 20 users, group by number of comments. Don't forget,
	// // this report is expected to be produced with an high durability
	// // guarantee for the returned documents. Once a commenter is in the
	// // top 20 of users, they become a Critic, so mostActive is composed of
	// // Critic objects.
	List<Bson> pipeline = new ArrayList<>();

	pipeline = Arrays.asList(group("$email", sum("count", 1L)), sort(descending("count")), limit(20));

	AggregateIterable<Critic> result = commentCollection.withReadConcern(ReadConcern.MAJORITY).aggregate(pipeline, Critic.class);

	for (Critic critic: result) {
		mostActive.add(critic);
	}

	return mostActive;
}