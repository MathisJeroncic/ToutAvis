package opinion;

import exceptions.BadEntryException;

public class Review {
	private Member reviewer;
	private String title;
	private float mark;
	private String comment;

	public Review(Member reviewer, String title, float mark, String comment) {
		this.reviewer = reviewer;
		this.title = title;
		this.mark = mark;
		this.comment = comment;
	}

	public Member getReviewer() {
		return this.reviewer;
	}

	public String getTitle() {
		return this.title;
	}

	public float getMark() {
		return this.mark;
	}

	public String getComment() {
		return this.comment;
	}

	public boolean sameLogin(String login) {
		return this.reviewer.getLogin().trim().equalsIgnoreCase(login.trim());
	}

	public void replaceReview(float mark, String comment) {
		this.mark = mark;
		this.comment = comment;
	}

	public void checkParameters() throws BadEntryException {

		if (this.title == null || this.title.trim().isEmpty()) {
			throw new BadEntryException("Title cant be empty");
		}
		if (this.comment == null || this.comment.trim().isEmpty()) {
			throw new BadEntryException("Comment cant be empty");
		}
		if (this.mark < (float) 0.0 || this.mark > 5.0) {
			throw new BadEntryException("Mark must be between 0.0 ans 5.0");
		}
	}
}