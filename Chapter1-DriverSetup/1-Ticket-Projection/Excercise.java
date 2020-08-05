    /**
     * For a given a country, return all the movies that match that country.
     *
     * @param country - Country string value to be matched.
     * @return List of matching Document objects.
     */
    public List<Document> getMoviesByCountry(String... country) {

        Bson queryFilter = new Document("countries", new Document("$in", Arrays.asList(country)));
        Bson projection = new Document("title", 1);
        //TODO> Ticket: Projection - implement the query and projection required by the unit test
        List<Document> movies = new ArrayList<>();
        moviesCollection.find(queryFilter)
                .projection(projection)
                .into(movies);

        return movies;
    }