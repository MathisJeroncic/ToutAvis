package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import opinion.ItemBook;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.NotItemException;


public class ReviewItemBookTest {
	
	private static int reviewItemBookTestBadEntryTest(SocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage){
		
		int nbReviews = 0;
		ItemBook bookToMark = null ;
		
		for(ItemBook b: sn.getBooks()) {
			if (b.sameBook(title)) {
				nbReviews = b.nbReviews();
				bookToMark = b;
			}
		}
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}catch (BadEntryException e) {
			if (bookToMark.nbReviews() != nbReviews) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of review was changed");
				return 1;
			} else
				return 0;
		}catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}		
	}
	
	private static int reviewItemBookOKTest(SocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId) {
		int nbReviews = 0;
		ItemBook bookToMark = null ;
		
		for(ItemBook b: sn.getBooks()) {
			if (b.sameBook(title)) {
				nbReviews = b.nbReviews();
				bookToMark = b;
			}
		}
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			if (bookToMark.nbReviews() != nbReviews) {
				System.out.println("Err " + testId + " : the number of review was not incremented");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	private static int reviewItemBookNotMemberTest(SocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage) {
		int nbReviews = 0;
		ItemBook bookToMark = null ;
		
		for(ItemBook b: sn.getBooks()) {
			if (b.sameBook(title)) {
				nbReviews = b.nbReviews();
				bookToMark = b;
			}
		}
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if (bookToMark.nbReviews() != nbReviews) {
				System.out.println(
						"Err " + testId + " : NotMemberException was thrown, but the number of review"
								+ ""
								+ " was changed");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	
	private static int reviewItemBookNotItemTest(SocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage) {
		int nbReviews = 0;
		ItemBook bookToMark = null ;
		
		for(ItemBook b: sn.getBooks()) {
			if (b.sameBook(title)) {
				nbReviews = b.nbReviews();
				bookToMark = b;
			}
		}
		try {
			sn.reviewItemBook(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotItemException e) {
			if (bookToMark.nbReviews() != nbReviews) {
				System.out.println(
						"Err " + testId + " : NotItemException was thrown, but the number of review"
								+ ""
								+ " was changed");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}
	public static TestReport test() throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException {

		SocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
									// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
									// here)

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing addItemBook()");

		// <=> test n°1

		// check if incorrect parameters cause addMember() to throw BadEntry
		// exception

		nbTests++;
		nbErrors += reviewItemBookTestBadEntryTest(sn, "test", 
				"qsdfgh", null, 2,"commentaire", "1.1", 
				"Review() doesn't reject null title");
		nbTests++;
		nbErrors += reviewItemBookTestBadEntryTest(sn, "test", "qsdfgh", "", 2,"commentaire", "1.2",
				"Review() doesn't reject title that don't contain at least one character other than space");
		nbTests++;
		nbErrors += reviewItemBookTestBadEntryTest(sn, "test", "qsdfgh", "titre", 6, "commentaire", "1.3",
				"Review() doesn't reject mark superore than 5.0");
		nbTests++;
		nbErrors += reviewItemBookTestBadEntryTest(sn, "test", "qsdfgh", "test", 4, "", "1.4",
				"Review() doesn't reject null comment");
		nbTests++;
		nbErrors += reviewItemBookTestBadEntryTest(sn, "test", "qsdfgh", "test", 3, null, "1.5",
				"Review() doesn't reject comment that don't contain at least one character other than space");
		
		// <=> test n°2

		// populate 'sn' with 3 members
		sn.addMember("Paul", "paul", "fan de manga");
		sn.addMember("Antoine", "antoine", "fan de tuto");
		sn.addMember("Janne", "uoiu", "ras");
		sn.addItemBook("Paul", "paul", "One piece", "Manga", "ODA", 50);

		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Paul", "paul", "One piece", 5, "Cool Manga", "2.1a");
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Antoine", "antoine", "One piece", 2, "Verry long", "2.1b");
		nbTests++;
		nbErrors += reviewItemBookOKTest(sn, "Janne", "uoiu", "One piece", 3, "middle", "2.1c");
		// try to add already registered book
		
		nbBooks = sn.nbBooks();
		
		int nbReviews = 0;
		ItemBook bookToMark = null ;
		
		for(ItemBook b: sn.getBooks()) {
			if (b.sameBook("One piece")) {
				nbReviews = b.nbReviews();
				bookToMark = b;
			}
		}

		
		nbTests++;
		nbErrors += reviewItemBookNotMemberTest(sn, "Fabrice", "STETER", "One piece", 5, "Cool Manga",
				"3.1", "The Review has been added, but the member does not exist");
		nbTests++;

		nbErrors += reviewItemBookNotMemberTest(sn, "Paul", "STETER", "One piece", 5, "Cool Manga", "3.2",
				"The Review has been added, but the member pwr does not exist");
		nbTests++;
		
		nbErrors += reviewItemBookNotItemTest(sn, "Paul", "paul", "Miserable", 1, "so lonnng", "3.3",
				"The Review has been added, but the book does not exist");
		nbTests++;

		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by Review()");
			nbErrors++;
		}
		nbTests++;

		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of books was unexepectedly changed by Review()");
			nbErrors++;
		}
		
		nbTests++;

		if (nbReviews != bookToMark.nbReviews()) {
			System.out.println("Error : the number of Reviews was unexepectedly changed by Review()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("AddMemberTest : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException {
		test();
	}
}
