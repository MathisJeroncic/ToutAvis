package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

public class ItemBook extends Item {
	
	private String author;
	private int nbPages;


	public ItemBook(String title, String kind, String author, int nbPages) {
		
		super(title,kind);
		
		this.author = author;
		this.nbPages = nbPages;
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

	public String getAuthor() {
		return this.author;
	}

	public int getNbPages() {
		return this.nbPages;
	}
	
	public void checkParameters() throws BadEntryException 
	{

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
