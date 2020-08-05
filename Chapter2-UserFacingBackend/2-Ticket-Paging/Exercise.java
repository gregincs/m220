/**
 * Finds all movies that match the provide `genres`, sorted descending by the `sortKey` field.
 *
 * @param sortKey - sorting key string.
 * @param limit   - number of documents to be returned.
 * @param skip    - number of documents to be skipped
 * @param genres  - genres matching string vargs.
 * @return List of matching Document objects.
 */
public List<Document> getMoviesByGenre(String sortKey, int limit, int skip, String... genres) {
	// query filter
	Bson castFilter = Filters.in("genres", genres);
	// sort key
	Bson sort = Sorts.descending(sortKey);
	Bson sortId = Sorts.ascending("_id");
	List<Document> movies = new ArrayList<>();
	// TODO > Ticket: Paging - implement the necessary cursor methods to support simple
	// pagination like skip and limit in the code below
	/* the commented approach above, works only for unit tests if it fails with the message:
	 * [ERROR]   PagingTest.testPagingByGenre:105 Expected `title` field does match: 
	 * Please check your getMoviesByGenre() movies sort order. expected:<[Only the Dead]> but was:<[Wolf Hall]>
	 * this happens due to the worng collection provided by the course
	 */
//        if(skip > 0){
//            moviesCollection.find(castFilter)
//                    .sort(sort)
//                    .sort(sortId)
//                    .limit(limit)
//                    .skip(skip)
//                    .iterator()
//                        .forEachRemaining(movies::add);
//        } else {
		moviesCollection.find(castFilter)
				.sort(sort)
				.limit(limit)
				.skip(skip)
				.iterator()
					.forEachRemaining(movies::add);
	//}
	return movies;
}