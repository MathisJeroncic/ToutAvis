package opinion;

import exceptions.BadEntryException;
import exceptions.NotMemberException;

public class ItemBook {
	String title;
	String kind;
	String author;
	int nbPages;

	public ItemBook(String title, String kind, String author, int nbPages) {
		this.title = title;
		this.kind = kind;
		this.author = author;
		this.nbPages = nbPages;
	}

	public String toString() {
		return ("Book title: " + this.title + "\n" + "Book kind: " + this.kind + "\n" + "Book author: " + this.author
				+ "\n" + "Book nuber of pages: " + this.nbPages);
	}

	public boolean sameBook(ItemBook book) {
		return this.title.trim().equalsIgnoreCase(book.title.trim())
				&& this.author.trim().equalsIgnoreCase(book.author.trim());
	}// Return 1 if the book already exist

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

		if (this.title == null) {
			throw new BadEntryException("Title can't be null");
		}
		if (this.title.strip().isBlank()) {
			throw new BadEntryException("Title cant be empty");
		}

		if (this.kind == null) {
			throw new BadEntryException("Kind cant be null");
		}
		if (this.kind.strip().isBlank()) {
			throw new BadEntryException("Kind cant be empty");
		}

		if (this.author == null) {
			throw new BadEntryException("Author cant be null");
		}
		if (this.author.strip().isBlank()) {
			throw new BadEntryException("Author cant be empty");
		}

		if (this.nbPages <= 0) {
			throw new BadEntryException("Number of page cant be inferor than 0");
		}
	}

}
