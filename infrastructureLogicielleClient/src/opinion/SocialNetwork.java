package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetwork {

	private LinkedList<Member> members;
	private LinkedList<ItemBook> itemBooks;
	private LinkedList<ItemFilm> itemFilms;

	/**
	 * Constructeur pour créer une instance du réseau social.
	 */

	public SocialNetwork() {
		members = new LinkedList<Member>();
		itemBooks = new LinkedList<ItemBook>();
	}

	/**
	 * Ajoute un nouveau membre au réseau social.
	 * 
	 * @param login    Le login du membre à ajouter. Ne doit pas être {@code null}
	 *                 ou vide.
	 * @param password Le mot de passe du membre à ajouter. Ne doit pas être
	 *                 {@code null} et doit contenir au moins 4 caractères.
	 * @param profile  Le profil du membre à ajouter. Ne doit pas être {@code null}.
	 * @throws BadEntryException            Si le login est {@code null}, vide, le
	 *                                      mot de passe est {@code null}, a moins
	 *                                      de 4 caractères, ou le profil est
	 *                                      {@code null}.
	 * @throws MemberAlreadyExistsException Si un membre avec le même login existe
	 *                                      déjà dans le réseau.
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
	 * Vérifie si un membre avec le login spécifié existe dans le réseau social.
	 * 
	 * @param login Le login à vérifier.
	 * @return {@code true} si le membre existe, {@code false} sinon.
	 */

	@Override
	public int nbMembers() {

		return members.size();
	}

	@Override
	public int nbFilms() {
		return itemFilms.size();
	}

	@Override
	public int nbBooks() {
		return itemBooks.size();
	}

	public LinkedList<ItemBook> getBooks() {
		return itemBooks;
	}
	
	public LinkedList<ItemFilm> getFilms() {
		return itemFilms;
	}

	@Override
	public void addItemFilm(String login, String password, String title, String kind, String director,
			String scriptwriter, int duration)
			throws BadEntryException, NotMemberException, ItemFilmAlreadyExistsException {
		
		boolean identification = false;

		ItemFilm itemFilmToAdd = new ItemFilm(title, kind, director,scriptwriter,duration);

		for (ItemFilm f : itemFilms) {
			if (itemFilmToAdd.sameFilm(f)) {
				throw new ItemFilmAlreadyExistsException();
			}
		}
		for (Member m : members) {

			if (m.identifyMember(members, login, password)) {
				identification = true;

			} else {
				throw new NotMemberException("Identification manquée");
			}

		}

		itemFilmToAdd.checkParameters();
		if (identification == true) {
			itemFilms.add(itemFilmToAdd);
		}
	}

	@Override
	public void addItemBook(String login, String password, String title, String kind, String author, int nbPages)
			throws BadEntryException, NotMemberException, ItemBookAlreadyExistsException {

		boolean identification = false;

		ItemBook itemBookToAdd = new ItemBook(title, kind, author, nbPages);

		for (ItemBook b : itemBooks) {
			if (itemBookToAdd.sameBook(b)) {
				throw new ItemBookAlreadyExistsException();
			}
		}
		for (Member m : members) {

			if (m.identifyMember(members, login, password)) {
				identification = true;

			} else {
				throw new NotMemberException("Identification manquée");
			}

		}

		itemBookToAdd.checkParameters();
		if (identification == true) {
			itemBooks.add(itemBookToAdd);
		}
	}

	@Override
	public float reviewItemFilm(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {

		boolean identification = false;
		float Mean = 0.0f;

		for (Member m : members) {

			if (m.identifyMember(members, login, password)) {
				identification = true;

			} else {
				throw new NotMemberException("Identification manquée");
			}

		}
		Review reviewItemFilmAdded = new Review(login, title, mark, comment);
		reviewItemFilmAdded.checkParameters();
		for (ItemFilm f : getFilms()) {
			if (identification) {
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
					}else {
					throw new NotItemException("the Book " + title + " do not existe in the data base please add it.");
				}
			}
		}
		return Mean;
	}

	@Override
	public float reviewItemBook(String login, String password, String title, float mark, String comment)
			throws BadEntryException, NotMemberException, NotItemException {

		boolean identification = false;
		float Mean = 0.0f;

		for (Member m : members) {

			if (m.identifyMember(members, login, password)) {
				identification = true;

			} else {
				throw new NotMemberException("Identification manquée");
			}

		}

		Review reviewItemBookAdded = new Review(login, title, mark, comment);
		reviewItemBookAdded.checkParameters();
		for (ItemBook b : getBooks()) {
			if (identification) {
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
					}else {
					throw new NotItemException("the Book " + title + " do not existe in the data base please add it.");
				}
			}
		}
		return Mean;
	}

	@Override
	public LinkedList<String> consultItems(String title) throws BadEntryException {

		LinkedList<Review> reviews = new LinkedList<Review>();
		LinkedList<String> consultedItemReviews = new LinkedList<String>();

		float mark=0.0f;

		if (title == null || title.trim().isEmpty()) {
			throw new BadEntryException("Title cant be null");
		}


		for (ItemBook b : itemBooks) {
			if (b.getTitle() == title) {
				reviews = b.getReviews();

				for (Review r : reviews) {
					mark += r.getMark();
					
				}
				mark=mark/b.nbReviews();
				
				consultedItemReviews.add(b.getTitle()+"est un livre écrit par "+b.getAuthor()+" dans le style "+b.getKind()+" avec "+b.getNbPages()+" pages et qui a reçu pour note "+mark+"/5");
			}
		}
		return consultedItemReviews;

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
