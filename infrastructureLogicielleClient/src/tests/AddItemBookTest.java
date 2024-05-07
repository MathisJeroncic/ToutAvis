/**
 * This class provides test cases for the addItemBook method in the ISocialNetwork interface.
 */
package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.NotTestReportException;
import exceptions.NotMemberException;


/**
 * This class contains test methods for the addItemBook method in the ISocialNetwork interface.
 */
public class AddItemBookTest {

    /**
     * Tests if addItemBook method throws BadEntryException for incorrect parameters.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the book to add.
     * @param kind         The category of the book to add.
     * @param author       The author of the book to add.
     * @param nbPages      The number of pages of the book to add.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 1 if the test fails, 0 otherwise.
     */
	private static int addItemBookBadEntryTest(ISocialNetwork sn,String login,String pwd,
			String title,String kind,String author, int nbPages,String testId, 
			String errorMessage) {
		int nbBooks = sn.nbBooks();
		
		try {
			sn.addItemBook(login,pwd,title,kind,author,nbPages);
			System.out.println("Err " + testId + " : " + errorMessage); 
			return 1;
		}catch (BadEntryException e) {
			if (sn.nbBooks() != nbBooks) { 
				System.out.println("Err "+ testId+ 
						" : BadEntry was thrown but the number of books was changed"); 
				return 1;
				} else
				return 0;
		}catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); 
			e.printStackTrace();
			return 1;
			}
		}
    /**
     * Tests if addItemBook method throws ItemBookAlreadyExistsException when adding an already existing book.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the book to add.
     * @param kind         The category of the book to add.
     * @param author       The author of the book to add.
     * @param nbPages      The number of pages of the book to add.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 1 if the test fails, 0 otherwise.
     */
	private static int addItemBookAlreadyExistsTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, int nbPages, String testId,
			String errorMessage) {
		int nbBooks = sn.nbBooks();
		
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}catch(ItemBookAlreadyExistsException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId+ 
						" : ItemBookAlreadyExistsException was thrown, but the number of Book was changed");
				return 1;
				}else 
				return 0;
		}catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception. "+ e); 
			e.printStackTrace();
			return 1;
			}					
		}
	

    /**
     * Tests if addItemBook method adds a book successfully.
     *
     * @param sn      The social network on which to perform the test.
     * @param login   The user's login.
     * @param pwd     The user's password.
     * @param title   The title of the book to add.
     * @param kind    The category of the book to add.
     * @param author  The author of the book to add.
     * @param nbPages The number of pages of the book to add.
     * @param testId  The identifier of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
	private static int addItemBookOKTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, int nbPages, String testId) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			if (sn.nbBooks() != nbBooks + 1) { 
				System.out.println("Err " + testId
						+ " : the number of members (" + nbBooks
						+ ") was not incremented");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out
					.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1; 
		}
	}
    /**
     * Tests if addItemBook method throws NotMemberException when the user is not a member.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the book to add.
     * @param kind         The category of the book to add.
     * @param author       The author of the book to add.
     * @param nbPages      The number of pages of the book to add.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 1 if the test fails, 0 otherwise.
     */	
	private static int addItemBookNotMemberTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, int nbPages, String testId,
			String errorMessage) {
		int nbBooks = sn.nbBooks();
		try {
			sn.addItemBook(login, pwd, title, kind, author, nbPages);
			System.out.println("Err " + testId + " : " + errorMessage);
			return 1;
		}catch (NotMemberException e) {
			if (sn.nbBooks() != nbBooks) {
				System.out.println("Err " + testId+ 
						" : NotMemberException was thrown, but the number of Book was changed");
				return 1;
				}else 
				return 0;
			}catch (Exception e) {
				System.out.println("Err " + testId + " : unexpected exception. "+ e); 
				e.printStackTrace();
				return 1;
				}
		}
	
    /**
     * Executes the test cases for the addItemBook method.
     *
     * @return TestReport containing the results of the tests.
     */
	public static TestReport test(){
		
		ISocialNetwork sn = new SocialNetwork();

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
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh",null,"fx","jean", 48,"1.1",
				"addMember() doesn't reject null title");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","","kind","author", 48,"1.2",
				"addMember() doesn't reject title that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test",null,"jean", 48,"1.3",
				"addMember() doesn't reject null kind");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test","","author", 48,"1.4",
				"addMember() doesn't reject kind that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test","fx",null, 48,"1.5",
				"addMember() doesn't reject null author");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test","kind","", 48,"1.6",
				"addMember() doesn't reject author that don't contain at least one character other than space");
//		nbTests++;
//		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test","kind","jean",null, "1.7",
//				"addMember() doesn't reject null nbpages");
		nbTests++;
		nbErrors += addItemBookBadEntryTest(sn, "test", "qsdfgh","test","kind","author",0, "1.8",
				"addMember() doesn't reject nbpages that don't contain at least one pages");

		

		// <=> test n°2

		// populate 'sn' with 3 members

		nbTests++;
		nbErrors += addItemBookOKTest(sn, "Paul", "paul", "One piece", "Manga", "ODA", 50,
				"2.1a");
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "Antoine", "antoine",
				"code en java pour les nulls", "Tuto", "pour les nulls", 1150, "2.1b");
		nbTests++;
		nbErrors += addItemBookOKTest(sn, "janne", "uoiu",
				"reussir l'imt en 2 pages", "tuto", "Fip", 2, "2.1c");

		// try to add already registered members

		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, new String("Paul"),
				"abcdefghij", "", "2.2",
				"The login of the first member was accepted as login for a new member");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(sn, new String("Alice"),
				"abcdefghij", "", "2.3",
				"The login of the last member was accepted as login for a new member");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(
				sn,
				new String("anToine"),
				"abcdefghij",
				"",
				"2.4",
				"An already registered login, but with different case, was accepted as login for a new member");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest(
				sn,
				new String(" Antoine "),
				"abcdefghij",
				"",
				"2.5",
				"An already registered login, surrounded by leading/trailing blanks, was accepted as login for a new member");
		nbTests++;
		nbErrors += addItemBookAlreadyExistsTest
				(
				sn,
				"An" + "toi" + "ne",
				"abcdefghij",
				"",
				"2.6",
				"A String concatenation building an already registered login was accepted as login for a new member");

		nbTests++;
		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out
					.println("Error : the number of films was unexepectedly changed by addMember()");
			nbErrors++;
		}
		nbTests++;
		if (nbBooks != sn.nbBooks()) {
			System.out
					.println("Error : the number of books was unexepectedly changed by addMember()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try{
			TestReport tr = new TestReport(nbTests, nbErrors);	
			System.out.println("AddMemberTest : " + tr);
			return tr;	
		}
		catch (NotTestReportException e){ //This shouldn't happen
			System.out.println("Unexpected error in AddMemberTest test code - Can't return valuable test results");
			return null;
			}
		}

    /**
     * Main method to execute the test cases.
     *
     * @param args Command-line arguments.
     */
	public static void main(String[] args) {
		test();
	}
}
