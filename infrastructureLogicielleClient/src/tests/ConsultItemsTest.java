package tests;

import opinion.ISocialNetwork;
import opinion.ItemBook;
import opinion.SocialNetwork;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;
import exceptions.NotItemException;

public class ConsultItemsTest {

	private static int consultItemsBadEntryTest(ISocialNetwork sn, String title, String testId, String errorMessage)throws BadEntryException {

		try {
			sn.consultItems(title);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}

		catch (BadEntryException e) {
			
			return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}

	}
	
	private static int consultItemsOkTest(ISocialNetwork sn, String title, String testId) {

		try {
			sn.consultItems(title);
//			System.out.println("Err " + testId + " : " + errorMessage);
			return 0;
		}

		catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. " + e);
			e.printStackTrace();
			return 1;
		}

	}

	
	

	public static TestReport test() throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, NotItemException, ItemFilmAlreadyExistsException {

		ISocialNetwork sn = new SocialNetwork();

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing consultItems()");
		
		sn.addMember("Paul", "paul", "Potterhead");
		sn.addMember("Jean", "jean", "lecteur passionner");
		sn.addItemBook("Paul", "paul", "Harry Potter", "Roman", "JK Rolling", 600);
		sn.reviewItemBook("Paul", "paul","Harry Potter",(float)  5.0, "Good Roman");
		sn.reviewItemBook("Jean", "jean","Harry Potter",(float)  4.5, ":)");
		sn.reviewItemBook("Paul", "paul","Harry Potter",(float)  3.2, "after a second read is not that good");
		sn.addItemFilm("Paul", "paul", "Star Wars", "FX", "director", "scriptwriter", 300);
		sn.reviewItemFilm("Paul", "paul","Star Wars",(float)  5.0, "Good film");
		sn.reviewItemFilm("Jean", "jean","Star Wars",(float)  4.5, ":)");
		sn.reviewItemFilm("Paul", "paul","Star Wars",(float)  3.2, "after a second watch is not that good");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn,null,"1.1","consultItems() doesn't reject null title");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn," ","1.2","consultItems() doesn't reject title that don't contain at least one character other than space");
		
		nbTests++;
		nbErrors += consultItemsOkTest(sn,"Harry Potter","2.1");
		
		nbTests++;
		nbErrors += consultItemsOkTest(sn,"Star Wars","2.2");
		

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("ConsultItemsTest : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in ConsultItemsTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, NotItemException, ItemFilmAlreadyExistsException {
		test();
	}

}
