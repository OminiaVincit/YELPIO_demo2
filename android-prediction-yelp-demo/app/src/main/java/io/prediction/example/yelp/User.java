package io.prediction.example.yelp;

public class User {
    private String uid;
    private String name;
    private double average_stars;
    private double review_count;

    public User(String uid, String name, double average_stars,
                double review_count) {
        this.uid = uid;
        this.name = name;
        this.average_stars = average_stars;
        this.review_count = review_count;
    }

    public String getUid() {
        return this.uid;
    }

    public String getUserName() {
        return this.name;
    }

    public double getStars() {
        return this.average_stars;
    }

    public double getReviewCount() {
        return this.review_count;
    }

    @Override
    public String toString() {
        return "UserID: " + uid + ", UserName: " + name + ",average_stars: " + average_stars
                + ", review_count: " + review_count;
    }
}
