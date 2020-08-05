/**
 * Gets a movie object from the database.
 *
 * @param movieId - Movie identifier string.
 * @return Document object or null.
 */
@SuppressWarnings("UnnecessaryLocalVariable")
public Document getMovie(String movieId) {
	if (!validIdValue(movieId)) {
		return null;
	}

	List<Bson> pipeline = new ArrayList<>();
	// match stage to find movie
	Bson match = match(Filters.eq("_id", new ObjectId(movieId)));
	pipeline.add(match);
	// TODO> Ticket: Get Comments - implement the lookup stage that allows the comments to
	// retrieved with Movies.

	List<Bson> lookupPipeline = new ArrayList<>();

	List let = Arrays.asList(new Variable<>("id", "$_id"));
	Bson lookupMatch = Aggregates.match(Filters.expr(new Document("$eq", Arrays.asList("$movie_id", "$$id"))));
	lookupPipeline.add(lookupMatch);

	Bson sort = Aggregates.sort(Sorts.descending("date"));
	lookupPipeline.add(sort);

	Bson lookup = Aggregates.lookup("comments", let, lookupPipeline, "comments");
	pipeline.add(lookup);

	Document movie = moviesCollection.aggregate(pipeline).first();

	return movie;
}