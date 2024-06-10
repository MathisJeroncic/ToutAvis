package opinion;

import exceptions.BadEntryException;

/**
 * The {@code Review} class represents a review given by a member.
 * It contains information about the reviewer, the title of the review, the mark given, and the comment.
 */
public class Review {
    
    /**
     * The member who wrote the review.
     */
    private Member reviewer;
    
    /**
     * The title of the review.
     */
    private String title;
    
    /**
     * The mark given in the review, between 0.0 and 5.0.
     */
    private float mark;
    
    /**
     * The comment of the review.
     */
    private String comment;

    /**
     * Constructs a Review with the specified reviewer, title, mark, and comment.
     *
     * @param reviewer The member who wrote the review.
     * @param title The title of the review.
     * @param mark The mark given in the review, between 0.0 and 5.0.
     * @param comment The comment of the review.
     */
    public Review(Member reviewer, String title, float mark, String comment) {
        this.reviewer = reviewer;
        this.title = title;
        this.mark = mark;
        this.comment = comment;
    }

    /**
     * Returns the member who wrote the review.
     *
     * @return The reviewer of the review.
     */
    public Member getReviewer() {
        return this.reviewer;
    }

    /**
     * Returns the title of the review.
     *
     * @return The title of the review.
     */
    public String getTitle() {
        return this.title;
    }

    /**
     * Returns the mark given in the review.
     *
     * @return The mark of the review.
     */
    public float getMark() {
        return this.mark;
    }

    /**
     * Returns the comment of the review.
     *
     * @return The comment of the review.
     */
    public String getComment() {
        return this.comment;
    }

    /**
     * Checks if the login matches the reviewer's login.
     *
     * @param login The login to check against the reviewer's login.
     * @return {@code true} if the logins match, {@code false} otherwise.
     */
    public boolean sameLogin(String login) {
        return this.reviewer.getLogin().trim().equalsIgnoreCase(login.trim());
    }

    /**
     * Replaces the current mark and comment with new ones.
     *
     * @param mark The new mark to set.
     * @param comment The new comment to set.
     */
    public void replaceReview(float mark, String comment) {
        this.mark = mark;
        this.comment = comment;
    }

    /**
     * Checks the parameters of the review and throws an exception if any are invalid.
     *
     * @throws BadEntryException if any of the parameters are invalid.
     */
    public void checkParameters() throws BadEntryException {
        if (this.title == null || this.title.trim().isEmpty()) {
            throw new BadEntryException("Title can't be empty");
        }
        if (this.comment == null || this.comment.trim().isEmpty()) {
            throw new BadEntryException("Comment can't be empty");
        }
        if (this.mark < 0.0 || this.mark > 5.0) {
            throw new BadEntryException("Mark must be between 0.0 and 5.0");
        }
    }
}
