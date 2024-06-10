package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import java.util.LinkedList;

import exceptions.*;

/**
 * Tools for testing a ISocialNetwork
 * @author cousin
 *
 */
public class Tools {

	private static java.util.Random randomGenerator;

	/*
	 * These counters allow specific members/books/films to be automatically generated, through String concatenate operations
	 * There are static, so as to guarantee that successive calls to populate() won't generate identical Strings
	 * They represent the number of members/books/films already generated via Tools 
	 * but depending on other actions made on the ISocialNetwork, they may differ from its actual 'numbers' of members/books/films
	 */
	private static int nextMember=1; // next value for members (cf logins/passwords)
	private static int nextBook=1;   // next value for books (cf title)
	private static int nextFilm=1;   // next value for films (cf title)

	/*
	 * These constants are String or prefix-String to be used when generating new members/books/films/reviews
	 */
	private static final String LOGIN ="member";
	private static final String PASSWORD = "passwd";
	private static final String PROFILE ="profile";

	private static final String TITLE = "title";
	private static final String KIND = "kind"; 
	private static final String AUTHOR = "author";
	private static final int NB_PAGES = 12;

	private static final String DIRECTOR = "director";
	private static final String SCENARIST = "scenarist";
	private static final int DURATION = 15;

	private static final String COMMENT = "comment";

	
 


	/**
	 * Adds members, books, films and reviews to the ISocialNetwork, according to the following process :<br>
	 * 1. Adds the asked number of members, giving them pseudo-random logins/passwords<br>
	 * 2. Make one of the added members to add the total asked number of books, giving them pseudo-random characteristics<br>
	 * 3. Make one of the added members to add the total asked number of films, giving them pseudo-random characteristics<br>
	 * 4. Add the asked number of reviews : <br> 
	 *    - reviewed items (book/film) and reviews'authors are randomly chosen among those that were added by Tools<br>
	 *    - depending on "chance", a member can add a review on an item that he already had reviewed, which will replace this previous review. It is therefore not guaranteed that the total number of reviews stored in the ISocialNetwork will have a nbReviews increase.<br>
	 * @param sn the ISocialNetwork to work with
	 * @param nbMembers the number of members to add to sn.
	 * @param nbBooks the number of books to add to sn
	 * @param nbFilms the number of films to add to sn
	 * @param nbReviews the number of reviews to add to sn
	 * @return meantime of each operation
	 * @throws java.lang.Exception whenever one of the calls to addMember(), addItemBook(), addItemFilm(), reviewItemBook() or reviewItemFilm() throws an exception, this exception is directly rethrown to the caller. That's why these methods should have been well tested before trying to use populate().
	 **/
	public static long populate (ISocialNetwork sn, int nbMembers, int nbBooks, int nbFilms, int nbReviews) 
			throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException,  NotItemException {
		
		long elapsedTime=0;
		long before, after; //timestamps
		int totalNbOfOperations = nbMembers + nbBooks +nbFilms + nbReviews;
		randomGenerator = new java.util.Random();

		//
		// Adds nbNumber members, giving them pseudo-random logins/passwords
		//
		for (int i=0; i<nbMembers;i++) {
			before = System.nanoTime();
			sn.addMember(LOGIN+nextMember, PASSWORD+nextMember, PROFILE);
			after = System.nanoTime();
			elapsedTime += after - before; 
			nextMember++;	
		}

		//
		//  Make the last added member to add the total asked number of books, giving them pseudo-random characteristics
		//
		if (nbBooks>0) { 
			//we don't care about WHO adds the books, so we only use last added member to do it
			if (nextMember==1) throw new BadEntryException ("populate() : must add at least a member before being able to add books");
			for (int i=0;i<nbBooks;i++) {
				int lastMemberNumber = nextMember-1;
				before = System.nanoTime();
				sn.addItemBook(LOGIN+lastMemberNumber, PASSWORD+lastMemberNumber, TITLE+nextBook, KIND, AUTHOR, NB_PAGES);
				after = System.nanoTime();
				elapsedTime += after - before; 
				nextBook++;			
			}
		}

		//
		// Make one of the added members to add the total asked number of films, giving them pseudo-random characteristics 
		//
		if (nbFilms>0) {
			// we don't care about WHO adds the films, so we only use last added member to do it
			if (nextMember==1) throw new BadEntryException ("populate(): must add at least 1 member before being able to add films");
			for (int i=0;i<nbFilms;i++) {
				int lastMemberNumber = nextMember-1;
				before = System.nanoTime();
				sn.addItemFilm(LOGIN+lastMemberNumber, PASSWORD+lastMemberNumber, TITLE+nextFilm, KIND, DIRECTOR, SCENARIST, DURATION);
				after = System.nanoTime();
				elapsedTime += after - before; 
				nextFilm++;			
			}
		}

		//
		//  Add the asked number of reviews : 
		//    - author of each review is randomly chosen among those that were added in step 1 ; 
		//    - reviewed items (book/film) and reviews'authors are randomly chosen among those that were added in steps 1, 2 and 3
		//    - depending on "chance", a member can add a review on an item that he already had reviewed, which will replace this previous review. 
		//  It is therefore not guaranteed that the total number of reviews stored in the ISocialNetwork will have a nbReviews increase.
		//
		if (nbReviews>0) {
			// try to fairly distribute reviews over members that were generated this time and previously
			// try to fairly distribute reviews among items

			if (nextMember==1) throw new BadEntryException ("populate() : must add at least a member before being able to add reviews");
			if ((nextBook==1)&&(nextFilm==1)) throw new BadEntryException ("populate() : must add at least an item before being able to add reviews");
			int currentMemberNum; // number of the member that do the review
			int itemNum; // number of the item that is reviewed

			// reviews will be splited between books reviews and film reviews
			int nbBookReviews, nbFilmReviews; // number of book reviews  and film reviews
			nbBookReviews = nextBook * (nbReviews / (nextBook + nextFilm)) ; // proportional distribution 
			nbFilmReviews = nbReviews - nbBookReviews;

			currentMemberNum = nextMember-1; // work downward so as to have new members contribute first


			for (int i = 0; i<nbBookReviews;i++) {// add a book review			
				itemNum = 1 + randomGenerator.nextInt(nextBook-2); // choose randomly a book to review - some tricks here, dealing with boundaries			
				before = System.nanoTime();
				sn.reviewItemBook(LOGIN+currentMemberNum, PASSWORD+currentMemberNum, TITLE+itemNum, 5*randomGenerator.nextFloat(), COMMENT);
				currentMemberNum = (currentMemberNum>1)  ? currentMemberNum-1 : nextMember-1; // decrementing modulo nextMember
				after = System.nanoTime();
				elapsedTime += after - before; 
				nbReviews--;
			}

			for (int i = 0; i<nbFilmReviews;i++) {// add a film review			
				itemNum = 1 + randomGenerator.nextInt(nextFilm-2); // choose randomly a film to review - some tricks here, dealing with boundaries		
				before = System.nanoTime();
				sn.reviewItemFilm(LOGIN+currentMemberNum, PASSWORD+currentMemberNum, TITLE+itemNum, 5*randomGenerator.nextFloat(), COMMENT);
				currentMemberNum = (currentMemberNum>1)  ? currentMemberNum-1 : nextMember-1; // decrementing modulo nextMember
				after = System.nanoTime();
				elapsedTime += after - before; 
				nbReviews--;
			}
		}




		return elapsedTime/totalNbOfOperations; 

	}
	public static void main(String[] args) throws Exception {
		
		SocialNetwork sn = new SocialNetwork();
		int nbMembers = 500;
		int nbBooks = 2500;
		int nbFilms = 2500;
		int nbReviews = 10000;
		
		
		
		long valuesBasics = populate(sn, nbMembers, nbBooks, nbFilms, nbReviews);
		System.out.println(valuesBasics);
		
		
		nbMembers = 50;
		nbBooks = 500;
		nbFilms = 500;
		nbReviews = 1000;
		int nbIterations = 50;
		
		LinkedList<Long> valuesMembers = new LinkedList<Long>();
		
		for(int i=1 ;i < nbIterations; i+=5) {
			long test = populate(sn, i*nbMembers, nbBooks, nbFilms, nbReviews);
			valuesMembers.add(test);
		}
		
		System.out.println(valuesMembers);
			
		
		nbMembers = 500;
		nbBooks = 50;
		nbFilms = 500;
		nbReviews = 1000;
		nbIterations = 50;
		
		LinkedList<Long> valuesBooks = new LinkedList<Long>();
		
		for(int i=1 ;i < nbIterations; i+=5) {
			long test = populate(sn, nbMembers, i*nbBooks, nbFilms, nbReviews);
			valuesBooks.add(test);
		}
		
		System.out.println(valuesBooks);
		
		
		
	}

}

