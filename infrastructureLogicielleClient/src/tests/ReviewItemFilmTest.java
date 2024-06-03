package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;
import opinion.ItemBook;
import opinion.ItemFilm;
import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.NotItemException;

public class ReviewItemFilmTest {

	// Test method to check for BadEntryException when reviewing a book
	private static int reviewItemFilmTestBadEntryTest(SocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {

		int nbReviews = 0;
		ItemFilm filmToMark	 = null;

		// Find the book by title and get the number of reviews
		for (ItemFilm f : sn.getFilms()) {
			if (f.sameFilm(title)) {
				nbReviews = f.nbReviews();
				filmToMark = f;
			}
		}
		try {
			// Attempt to review the book
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (BadEntryException e) {
			// Check if the number of reviews has changed
			if (filmToMark == null) {
				return 0;
			} else if (filmToMark.nbReviews() != nbReviews) {
				System.out.println("Err " + testId + " : BadEntry was thrown but the number of reviews was changed");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	// Test method to check if reviewing a book works correctly
	private static int reviewItemFilmOKTest(SocialNetwork sn, String login, String password, String title, float mark,
			String comment, String testId) {
		int nbReviews = 0;
		ItemFilm filmToMark = null;

		// Find the book by title and get the number of reviews
		for (ItemFilm f : sn.getFilms()) {
			if (f.sameFilm(title)) {
				nbReviews = f.nbReviews();
				filmToMark = f;
			}
		}
		try {
			// Attempt to review the book
			sn.reviewItemFilm(login, password, title, mark, comment);
			// Check if the number of reviews has been incremented
			if (filmToMark.nbReviews() != nbReviews + 1) {
				System.out.println("Err " + testId + " : the number of reviews was not incremented");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}

	// Test method to check for NotMemberException when reviewing a book
	private static int reviewItemFilmNotMemberTest(SocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbReviews = 0;
		ItemFilm filmToMark = null;

		// Find the book by title and get the number of reviews
		for (ItemFilm f : sn.getFilms()) {
			if (f.sameFilm(title)) {
				nbReviews = f.nbReviews();
				filmToMark = f;
			}
		}
		try {
			// Attempt to review the book
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			// Check if the number of reviews has changed
			if (filmToMark == null) {
				return 0;
			} else if (filmToMark.nbReviews() != nbReviews) {
				System.out.println(
						"Err " + testId + " : NotMemberException was thrown, but the number of reviews was changed");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	// Test method to check for NotItemException when reviewing a book
	private static int reviewItemFilmNotItemTest(SocialNetwork sn, String login, String password, String title,
			float mark, String comment, String testId, String errorMessage) {
		int nbReviews = 0;
		ItemFilm filmToMark = null;

		// Find the book by title and get the number of reviews
		for (ItemFilm f : sn.getFilms()) {
			if (f.sameFilm(title)) {
				nbReviews = f.nbReviews();
				filmToMark = f;
			}
		}
		try {
			// Attempt to review the book
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotItemException e) {
			// Check if the number of reviews has changed
			if (filmToMark == null) {
				return 0;
			} else if (filmToMark.nbReviews() != nbReviews) {
				System.out.println(
						"Err " + testId + " : NotItemException was thrown, but the number of reviews was changed");
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}
	}

	// Main test method to run all test cases
	public static TestReport test()
			throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException {

		SocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0 here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0 here)

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing ReviewItemBook()");

		// <=> test n°1
		// Check if incorrect parameters cause addMember() to throw BadEntry exception
		sn.addMember("test", "qsdfgh", "test");
		
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "qsdfgh", null, 2, "commentaire", "1.1",
				"Review() doesn't reject null title");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "qsdfgh", "", 2, "commentaire", "1.2",
				"Review() doesn't reject title that doesn't contain at least one character other than space");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "qsdfgh", "titre", (float) 6.0, "commentaire", "1.3",
				"Review() doesn't reject mark greater than 5.0");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "qsdfgh", "test", 4, "", "1.4",
				"Review() doesn't reject empty comment");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "qsdfgh", "test", 3, null, "1.5",
				"Review() doesn't reject null comment");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "", "qsdfgh","test",2.1f,"commentaire", "1.6",
				"addItemFilm() doesn't reject null login");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, null, "qsdfgh","test",2.1f,"commentaire", "1.7",
				"addItemFilm() doesn't reject login that don't contain at least one character other than space");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", "","test",2.1f,"commentaire", "1.8",
				"addItemFilm() doesn't reject null password");
		nbTests++;
		nbErrors += reviewItemFilmTestBadEntryTest(sn, "test", null,"test",2.1f,"commentaire", "1.9",
				"addItemFilm() doesn't reject password that don't contain at least one character other than space");

		// <=> test n°2
		// Populate 'sn' with 3 members and a book
		sn.addMember("Paul", "paul", "fan de manga");
		sn.addMember("Antoine", "antoine", "fan de tuto");
		sn.addMember("Janne", "uoiu", "ras");
		sn.addItemFilm("Paul", "paul", "One piece", "Manga", "ODA", "shuesha", 50);

		// Test valid reviews
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Paul", "paul", "One piece", (float) 5.0, "Cool Manga", "2.1a");
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Antoine", "antoine", "One piece", (float) 0.0, "Very long", "2.1b");
		nbTests++;
		nbErrors += reviewItemFilmOKTest(sn, "Janne", "uoiu", "One piece", (float) 3.2, "Middle", "2.1c");

		// Test invalid reviews
		nbFilms = sn.nbFilms();
		int nbReviews = 0;
		ItemFilm filmToMark = null;

		// Find the book by title and get the number of reviews
		for (ItemFilm f : sn.getFilms()) {
			if (f.sameFilm("One piece")) {
				nbReviews = f.nbReviews();
				filmToMark = f;
			}
		}

		nbTests++;
		nbErrors += reviewItemFilmNotMemberTest(sn, "Fabrice", "STETER", "One piece", 5, "Cool Manga", "2.2",
				"The review has been added, but the member does not exist");
		nbTests++;
		nbErrors += reviewItemFilmNotMemberTest(sn, "Paul", "STETER", "One piece", 5, "Cool Manga", "2.3",
				"The review has been added, but the member password is incorrect");
		nbTests++;
		nbErrors += reviewItemFilmNotItemTest(sn, "Paul", "paul", "Miserable", 1, "so long", "2.4",
				"The review has been added, but the book does not exist");
		nbTests++;

		// Check that 'sn' was not modified unexpectedly
		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error: the number of books was unexpectedly changed by Review()");
			nbErrors++;
		}
		nbTests++;

		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error: the number of films was unexpectedly changed by Review()");
			nbErrors++;
		}
		nbTests++;

		if (nbReviews != filmToMark.nbReviews()) {
			System.out.println("Error: the number of reviews was unexpectedly changed by Review()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network: " + sn);

		// Print a summary of the tests and return test results
		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("ReviewItemFilm : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in ReviewItemFilm test code - Can't return valid test results");
			return null;
		}
	}

	// Main method to run the test
	public static void main(String[] args)
			throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException {
		test();
	}

}
