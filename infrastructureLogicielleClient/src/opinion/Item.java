package opinion;

import java.util.LinkedList;

/**
 * Represents an item that can be reviewed.
 * Each item has a title, kind, and a list of reviews.
 */
public class Item {
    
    /**
     * The title of the item.
     */
    protected String title;
    
    /**
     * The kind of the item (e.g., book, movie).
     */
    protected String kind;
    
    /**
     * The mean mark of all reviews for this item.
     */
    protected float meanMark;
    
    /**
     * The list of reviews for this item.
     */
    protected LinkedList<Review> reviews;

    /**
     * Constructs an Item with the specified title and kind.
     *
     * @param title The title of the item.
     * @param kind The kind of the item.
     */
    public Item(String title, String kind) {
        this.title = title;
        this.kind = kind;
        this.reviews = new LinkedList<Review>();
    }

    /**
     * Returns the title of the item.
     *
     * @return The title of the item.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the kind of the item.
     *
     * @return The kind of the item.
     */
    public String getKind() {
        return this.kind;
    }

    /**
     * Calculates and returns the mean mark of all reviews for this item.
     *
     * @return The mean mark of all reviews.
     */
    public float getMean() {
        float mark = 0.0f;
        for (Review r : reviews) {
            mark += r.getMark();
        }
        this.meanMark = mark / reviews.size();
        return this.meanMark;
    }

    /**
     * Returns the list of reviews for this item.
     *
     * @return A LinkedList of reviews.
     */
    public LinkedList<Review> getReviews() {
        return this.reviews;
    }

    /**
     * Returns the number of reviews for this item.
     *
     * @return The number of reviews.
     */
    public int nbReviews() {
        return reviews.size();
    }

    /**
     * Adds a review to the list of reviews for this item.
     *
     * @param reviewToAdd The review to add.
     */
    public void addReviews(Review reviewToAdd) {
        reviews.add(reviewToAdd);
    }
}
