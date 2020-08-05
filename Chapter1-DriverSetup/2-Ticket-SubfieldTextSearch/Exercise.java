    /**
     * Finds all movies that contain any of the `casts` members, sorted in descending by the `sortKey`
     * field.
     *
     * @param sortKey - sort key.
     * @param limit   - number of documents to be returned.
     * @param skip    - number of documents to be skipped.
     * @param cast    - cast selector.
     * @return List of documents sorted by sortKey that match the cast selector.
     */
    public List<Document> getMoviesByCast(String sortKey, int limit, int skip, String... cast) {
        Bson castFilter = new Document("cast", new Document("$in", Arrays.asList(cast)));
        Bson sort = Sorts.descending(sortKey);
        //TODO> Ticket: Subfield Text Search - implement the expected cast
        // filter and sort
        List<Document> movies = new ArrayList<>();
        moviesCollection
                .find(castFilter)
                .sort(sort)
                .limit(limit)
                .skip(skip)
                .iterator()
                .forEachRemaining(movies::add);
        return movies;
    }