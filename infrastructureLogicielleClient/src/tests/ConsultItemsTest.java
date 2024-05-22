package tests;

import opinion.ISocialNetwork;
import opinion.ItemBook;
import opinion.SocialNetwork;

import java.util.LinkedList;

import exceptions.BadEntryException;
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
	
	private static int consultItemsOkTest(ISocialNetwork sn, String title, String testId, String errorMessage) {

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
	

	public static TestReport test() throws BadEntryException {

		ISocialNetwork sn = new SocialNetwork();

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing consultItems()");

		nbTests++;
		nbErrors += consultItemsOkTest(sn,"Harry Potter","1.1","Ne peut pas consulter un item");

		nbTests++;
		nbErrors += consultItemsOkTest(sn,"Harry Potter","1.2","Ne peut pas consulter deux fois le même item");

		nbTests++;
		nbErrors += consultItemsOkTest(sn,"Hp","1.3","Ne peut pas consulter un item de 2 charactères");

		nbTests++;
		nbErrors += consultItemsOkTest(sn,"H","1.4","Ne peut pas consulter un item de 1 charactère");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn,"","1.5","Ne peut pas consulter un item dont le titre est null");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn," ","1.6","Ne peut pas consulter un item dont le titre est un espace");

		nbTests++;
		nbErrors += consultItemsBadEntryTest(sn,"                       ","1.7","Ne peut pas consulter un item dont le titre est constitué uniquement d'espaces");

		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("ConsultItemsTest : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in ConsultItemsTest test code - Can't return valuable test results");
			return null;
		}
	}

	public static void main(String[] args) throws BadEntryException {
		test();
	}

}
