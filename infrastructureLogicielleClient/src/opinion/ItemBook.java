package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public class ItemBook {
	private String title;
	private String kind;
	private String author;
	private int nbPages;
	private LinkedList<Review> reviews;
	private float meanMark;

	public ItemBook(String title, String kind, String author, int nbPages) {
		this.title = title;
		this.kind = kind;
		this.author = author;
		this.nbPages = nbPages;
		reviews=new LinkedList<Review>();
	}

	public String toString() {
		return ("Book title: " + this.title + "\n" + "Book kind: " + this.kind + "\n" + "Book author: " + this.author
				+ "\n" + "Book nuber of pages: " + this.nbPages);
	}

	public boolean sameBook(ItemBook book) {
		return this.title.trim().equalsIgnoreCase(book.title.trim());
	}// Return 1 if the book already exist

	public boolean sameBook(String title) {
		return this.title.trim().equalsIgnoreCase(title.trim());
	}
	
	public String getTitle() {
		return this.title;
	}

	public String getKind() {
		return this.kind;
	}

	public String getAuthor() {
		return this.author;
	}

	public int getNbPages() {
		return this.nbPages;
	}
	
	public LinkedList<Review> getReviews() {
		return this.reviews;
	}
	
	public int nbReviews()
	{
		return reviews.size();
	}
	
	public void addReviews(Review reviewToAdd) {
		reviews.add(reviewToAdd);
	}
	
	public float getMean() {
		float mark = 0.0f;
		for (Review r : reviews) {
			mark += r.getMark();
		}
		this.meanMark=mark/reviews.size();
		return this.meanMark;
	}


	public void checkParameters() throws BadEntryException {

		if (this.title == null || this.title.trim().isEmpty()) {
			throw new BadEntryException("Title cant be empty");
		}
		if (this.kind == null || this.kind.trim().isEmpty()) {
			throw new BadEntryException("Kind cant be empty");
		}
		if (this.author == null || this.author.trim().isEmpty()) {

			throw new BadEntryException("Author cant be empty");
		}
		if (this.nbPages <= 0) {
			throw new BadEntryException("Number of page cant be inferor than 0");
		}
	}

}
