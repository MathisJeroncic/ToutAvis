package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotItemException;


public class ReviewItemBookTest {
	
	private static int reviewItemBookTestBadEntryTest(ISocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage){
		int nbBooks = sn.nbBooks();
		
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}catch (BadEntryException e) {
			if (sn.nbBooks() != nbBooks) {
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
	
	private static int reviewItemBookOKTest(ISocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
			if (sn.nbBooks() != nbBooks + 1) {
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
	
	private static int reviewItemBookNotMemberTest(ISocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
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
	
	private static int reviewItemBookNotItemTest(ISocialNetwork sn, String login, 
			String password, String title, float mark,
			String comment, String testId, String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.reviewItemFilm(login, password, title, mark, comment);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		} catch (NotItemException e) {
			if (sn.nbBooks() != nbBooks) {
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
	
}
