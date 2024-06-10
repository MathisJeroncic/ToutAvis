package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * Represents a book item that can be reviewed.
 * Extends the Item class by adding author and number of pages.
 */
public class ItemBook extends Item {
    
    /**
     * The author of the book.
     */
    private String author;
    
    /**
     * The number of pages in the book.
     */
    private int nbPages;

    /**
     * Constructs an ItemBook with the specified title, kind, author, and number of pages.
     *
     * @param title The title of the book.
     * @param kind The kind of the book.
     * @param author The author of the book.
     * @param nbPages The number of pages in the book.
     */
    public ItemBook(String title, String kind, String author, int nbPages) {
        super(title, kind);
        this.author = author;
        this.nbPages = nbPages;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return A string with the book's details.
     */
    public String toString() {
        return "Book title: " + this.title + "\n" + 
               "Book kind: " + this.kind + "\n" + 
               "Book author: " + this.author + "\n" + 
               "Book number of pages: " + this.nbPages;
    }

    /**
     * Checks if this book has the same title as another book.
     *
     * @param book The book to compare with.
     * @return true if the titles are the same, false otherwise.
     */
    public boolean sameBook(ItemBook book) {
        return this.title.trim().equalsIgnoreCase(book.title.trim());
    }

    /**
     * Checks if this book has the same title as the given title.
     *
     * @param title The title to compare with.
     * @return true if the titles are the same, false otherwise.
     */
    public boolean sameBook(String title) {
        return this.title.trim().equalsIgnoreCase(title.trim());
    }

    /**
     * Returns the author of the book.
     *
     * @return The author of the book.
     */
    public String getAuthor() {
        return this.author;
    }

    /**
     * Returns the number of pages in the book.
     *
     * @return The number of pages.
     */
    public int getNbPages() {
        return this.nbPages;
    }

    /**
     * Checks the parameters of the book and throws an exception if any are invalid.
     *
     * @throws BadEntryException if any of the parameters are invalid.
     */
    public void checkParameters() throws BadEntryException {
        if (this.title == null || this.title.trim().isEmpty()) {
            throw new BadEntryException("Title can't be empty");
        }
        if (this.kind == null || this.kind.trim().isEmpty()) {
            throw new BadEntryException("Kind can't be empty");
        }
        if (this.author == null || this.author.trim().isEmpty()) {
            throw new BadEntryException("Author can't be empty");
        }
        if (this.nbPages <= 0) {
            throw new BadEntryException("Number of pages can't be less than or equal to 0");
        }
    }
}
