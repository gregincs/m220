[{
    $group: {
        _id: "$email",
        count: {
            $sum: 1
        }
    }
}, {
    $sort: {
        count: -1
    }
}, {
    $limit: 20
}]