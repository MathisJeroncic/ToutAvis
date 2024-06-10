package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;
import tests.Tools;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetwork {

	private LinkedList<Member> members;
	private LinkedList<ItemBook> itemBooks;
	private LinkedList<ItemFilm> itemFilms;

	/**
	 * Constructor to create an instance of the social network.
	 */
	public SocialNetwork() {
		members = new LinkedList<Member>();
		itemBooks = new LinkedList<ItemBook>();
		itemFilms = new LinkedList<ItemFilm>();
	}

	/**
	 * Adds a new member to the social network.
	 * 
	 * @param login    The login of the member to add. Must not be {@code null} or empty.
	 * @param password The password of the member to add. Must not be {@code null} and must contain at least 4 characters.
	 * @param profile  The profile of the member to add. Must not be {@code null}.
	 * @throws BadEntryException            If the login is {@code null}, empty, the password is {@code null}, less than 4 characters, or the profile is {@code null}.
	 * @throws MemberAlreadyExistsException If a member with the same login already exists in the network.
	 */
	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {

		Member memberToAdd = new Member(login, password, profile);

		memberToAdd.checkParameters();

		for (Member m : members) {
			if (memberToAdd.areYou(m.getLogin())) {
				throw new MemberAlreadyExistsException();
			}
		}
		members.add(memberToAdd);
	}

	/**
	 * Returns the number of members in the social network.
	 * 
	 * @return The number of members.
	 */
	@Override
	public int nbMembers() {
		return members.size();
	}

	/**
	 * Returns the number of films in the social network.
	 * 
	 * @return The number of films.
	 */
	@Override
	public int nbFilms() {
		return itemFilms.size();
	}

	/**
	 * Returns the number of books in the social network.
	 * 
	 * @return The number of books.
	 */
	@Override
	public int nbBooks() {
		return itemBooks.size();
	}

	/**
	 * Returns the list of books in the social network.
	 * 
	 * @return The list of books.
	 */
	public LinkedList<ItemBook> getBooks() {
		return itemBooks;
	}

	/**
	 * Returns the list of films in the social network.
	 * 
	 * @return The list of films.
	 */
	public LinkedList<ItemFilm> getFilms() {
		return itemFilms;
	}

	/**
	 * Adds a new film to the social network.
	 * 
	 * @param login       The login of the member adding the film. Must not be {@code null} or empty.
	 * @param password    The password of the member adding the film. Must not be {@code null} and must contain at least 4 characters.
	 * @param title       The title of the film. Must not be {@code null} or empty.
	 * @param kind        The genre of the film. Must not be {@code null}.
	 * @param director    The director of the film. Must not be {@code null}.
	 * @param scriptwriter The scriptwriter of the film. Must not be {@code null}.
	 * @param duration    The duration of the film in minutes.
	 * @throws BadEntryException             If any of the parameters are invalid.
	 * @throws NotMemberException            If the member is not found or password is incorrect.
	 * @throws ItemFilmAlreadyExistsException If a film with the same title already exists in the network.
	 */
	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director,
			String scriptwriter, int duration)
			throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {

		if (login == null || login.trim().isEmpty()) {
			throw new BadEntryException("login cant be empty");
		}
		if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
			throw new BadEntryException("password cant be empty or < 4");
		}

		boolean identification = false;
		Member m = new Member(title, password, "test");

		if (m.identifyMember(members, login, password)) {
			identification = true;
		} else {
			throw new NotMemberException("Identification manquée");
		}

		ItemFilm itemFilmToAdd = new ItemFilm(title, kind, director, scriptwriter, duration);

		for (ItemFilm f : itemFilms) {
			if (itemFilmToAdd.sameFilm(f)) {
				throw new ItemFilmAlreadyExistsException();
			}
		}

		itemFilmToAdd.checkParameters();
		if (identification == true) {
			itemFilms.add(itemFilmToAdd);
		}
	}

	/**
	 * Adds a new book to the social network.
	 * 
	 * @param login    The login of the member adding the book. Must not be {@code null} or empty.
	 * @param password The password of the member adding the book. Must not be {@code null} and must contain at least 4 characters.
	 * @param title    The title of the book. Must not be {@code null} or empty.
	 * @param kind     The genre of the book. Must not be {@code null}.
	 * @param author   The author of the book. Must not be {@code null}.
	 * @param nbPages  The number of pages in the book.
	 * @throws BadEntryException             If any of the parameters are invalid.
	 * @throws NotMemberException            If the member is not found or password is incorrect.
	 * @throws ItemBookAlreadyExistsException If a book with the same title already exists in the network.
	 */
	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {

		if (login == null || login.trim().isEmpty()) {
			throw new BadEntryException("login cant be empty");
		}
		if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
			throw new BadEntryException("password cant be empty");
		}

		boolean identification = false;
		Member m = new Member(title, password, "test");

		if (m.identifyMember(members, login, password)) {
			identification = true;
		} else {
			throw new NotMemberException("Identification manquée");
		}

		ItemBook itemBookToAdd = new ItemBook(title, kind, author, nbPages);

		for (ItemBook b : itemBooks) {
			if (itemBookToAdd.sameBook(b)) {
				throw new ItemBookAlreadyExistsException();
			}
		}

		itemBookToAdd.checkParameters();
		if (identification == true) {
			itemBooks.add(itemBookToAdd);
		}
	}

	/**
	 * Adds a review for a film in the social network.
	 * 
	 * @param login    The login of the member adding the review. Must not be {@code null} or empty.
	 * @param password The password of the member adding the review. Must not be {@code null} and must contain at least 4 characters.
	 * @param title    The title of the film being reviewed. Must not be {@code null} or empty.
	 * @param mark     The mark given to the film. Must be between 0 and 5.
	 * @param comment  The comment of the review. Must not be {@code null}.
	 * @return The average mark of the film after adding the review.
	 * @throws BadEntryException If any of the parameters are invalid.
	 * @throws NotMemberException If the member is not found or password is incorrect.
	 * @throws NotItemException   If the film is not found in the network.
	 */
	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {

		if (login == null || login.trim().isEmpty()) {
			throw new BadEntryException("login cant be empty");
		}
		if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
			throw new BadEntryException("password cant be empty or < 4");
		}
		
		Member m = new Member(title, password, "test");

		if (m.identifyMember(members, login, password)==false) {
			throw new NotMemberException("Identification manquée");
		}
		Review reviewItemFilmAdded = new Review(m, title, mark, comment);
		reviewItemFilmAdded.checkParameters();
		for (ItemFilm f : getFilms()) {
			if (f.sameFilm(title)) {
				LinkedList<Review> reviewList = f.getReviews();
				for (Review r : reviewList) {
					if (r.sameLogin(login)) {
						r.replaceReview(mark, comment);
						return (f.getMean());
					}
				}
				f.addReviews(reviewItemFilmAdded);
				return (f.getMean());
			}
		}
		throw new NotItemException("the Book " + title + " do not existe in the data base please add it.");
	}

	/**
	 * Adds a review for a book in the social network.
	 * 
	 * @param login    The login of the member adding the review. Must not be {@code null} or empty.
	 * @param password The password of the member adding the review. Must not be {@code null} and must contain at least 4 characters.
	 * @param title    The title of the book being reviewed. Must not be {@code null} or empty.
	 * @param mark     The mark given to the book. Must be between 0 and 5.
	 * @param comment  The comment of the review. Must not be {@code null}.
	 * @return The average mark of the book after adding the review.
	 * @throws BadEntryException If any of the parameters are invalid.
	 * @throws NotMemberException If the member is not found or password is incorrect.
	 * @throws NotItemException   If the book is not found in the network.
	 */
	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {

		if (login == null || login.trim().isEmpty()) {
			throw new BadEntryException("login cant be empty");
		}
		if (password == null || password.trim().isEmpty() || password.trim().length() < 4) {
			throw new BadEntryException("password cant be empty or < 4");
		}

		Member m = new Member(login, password, "test");

		if (m.identifyMember(members, login, password) == false) {
			throw new NotMemberException("Identification manquée");
		}

		Review reviewItemBookAdded = new Review(m, title, mark, comment);
		reviewItemBookAdded.checkParameters();
		for (ItemBook b : getBooks()) {
			if (b.sameBook(title)) {
				LinkedList<Review> reviewList = b.getReviews();
				for (Review r : reviewList) {
					if (r.sameLogin(login)) {
						r.replaceReview(mark, comment);
						return (b.getMean());
					}
				}
				b.addReviews(reviewItemBookAdded);
				return (b.getMean());
			}
		}
		throw new NotItemException("the Book " + title + " do not existe in the data base please add it.");
	}

	/**
	 * Consults items (books or films) in the social network by title.
	 * 
	 * @param title The title of the item to consult. Must not be {@code null} or empty.
	 * @return A list of strings describing the items found.
	 * @throws BadEntryException If the title is {@code null} or empty.
	 */
	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {

		LinkedList<Review> reviews = new LinkedList<Review>();
		LinkedList<String> consultedItemReviews = new LinkedList<String>();

		float mark = 0.0f;

		if (title == null || title.trim().isEmpty()) {
			throw new BadEntryException("Title cant be null");
		}

		for (ItemBook b : itemBooks) {
			if (b.getTitle() == title) {
				reviews = b.getReviews();

				for (Review r : reviews) {
					mark += r.getMark();

				}
				mark = mark / b.nbReviews();

				consultedItemReviews.add(b.getTitle() + "est un livre écrit par " + b.getAuthor() + " dans le style "
						+ b.getKind() + " avec " + b.getNbPages() + " pages et qui a reçu pour note " + mark + "/5");
			}
		}

		mark = 0.0f;

		for (ItemFilm f : itemFilms) {
			if (f.getTitle() == title) {
				reviews = f.getReviews();

				for (Review r : reviews) {
					mark += r.getMark();

				}
				mark = mark / f.nbReviews();

				consultedItemReviews.add(f.getTitle() + "est un film produit par " + f.getDirector()
						+ " et scénarisé par " + f.getScenarist() + " dans le style " + f.getKind() + " avec "
						+ f.getDuration() + " pages et qui a reçu pour note " + mark + "/5");
			}
		}
		return consultedItemReviews;

	}

	/**
	 * Main method to test the social network.
	 * 
	 * @param args Command line arguments.
	 */
	public static void main(String[] args) {
		ISocialNetwork sn = new SocialNetwork();
		int nbMembers = 100;
		int nbBooks = 50;
		int nbFilms = 50;
		int nbReviews = 200;

		try {
			long meantime = Tools.populate(sn, nbMembers, nbBooks, nbFilms, nbReviews);
			System.out.println("Meantime per operation: " + meantime + " ns");
		} catch (BadEntryException | MemberAlreadyExistsException | NotMemberException | ItemBookAlreadyExistsException
				| ItemFilmAlreadyExistsException | NotItemException e) {
			e.printStackTrace();
		}

	}

}
