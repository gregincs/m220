/**
 * This method is the java implementation of the following mongo shell aggregation pipeline
 * pipeline.aggregate([ {$match: {cast: {$in: ... }}}, {$sort: {tomatoes.viewer.numReviews: -1}},
 * {$skip: ... }, {$limit: ... }, {$facet:{ runtime: {$bucket: ...}, rating: {$bucket: ...},
 * movies: {$addFields: ...}, }} ])
 */
public List<Document> getMoviesCastFaceted(int limit, int skip, String... cast) {
	List<Document> movies = new ArrayList<>();
	String sortKey = "tomatoes.viewer.numReviews";
	Bson skipStage = Aggregates.skip(skip);
	Bson matchStage = Aggregates.match(Filters.in("cast", cast));
	Bson sortStage = Aggregates.sort(Sorts.descending(sortKey));
	Bson limitStage = Aggregates.limit(limit);
	Bson facetStage = buildFacetStage();
	// Using a LinkedList to ensure insertion order
	List<Bson> pipeline = new LinkedList<>();

	// TODO > Ticket: Faceted Search - build the aggregation pipeline by adding all stages in the
	// correct order
	// Your job is to order the stages correctly in the pipeline.
	// Starting with the `matchStage` add the remaining stages.
	pipeline.add(matchStage);
	pipeline.add(sortStage);
	pipeline.add(skipStage);
	pipeline.add(limitStage);
	pipeline.add(facetStage);

	moviesCollection.aggregate(pipeline).iterator().forEachRemaining(movies::add);
	return movies;
}