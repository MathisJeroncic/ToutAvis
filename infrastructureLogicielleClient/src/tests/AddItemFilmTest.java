/**
 * This class provides test cases for the addItemFilm method in the ISocialNetwork interface.
 */
package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotTestReportException;
import exceptions.NotMemberException;

/**
 * This class contains test methods for the addItemFilm method in the ISocialNetwork interface.
 */
public class AddItemFilmTest {

    /**
     * Tests if addItemFilm method throws BadEntryException for incorrect parameters.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the book to add.
     * @param kind         The category of the book to add.
     * @param director     The director of the film to add.
     * @param scriptwriter The scriptwriter of the film to add.
     * @param duration     The duration of the film to add.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addItemFilmBadEntryTest(ISocialNetwork sn, String login, String pwd, String title, String kind,
                                                String director, String scriptwriter, int duration, String testId,
                                                String errorMessage) {
        int nbFilms = sn.nbFilms();

        try {
            sn.addItemFilm(login, pwd, title, kind, director, scriptwriter, duration);
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;
        } catch (BadEntryException e) {
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Err " + testId + " : BadEntry was thrown but the number of films was changed");
                return 1;
            } else
                return 0;
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Tests if addItemFilm method throws ItemFilmAlreadyExistsException when adding an already existing film.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the film to add.
     * @param kind         The category of the film to add.
     * @param director     The director of the film to add.
     * @param scriptwriter The scriptwriter of the film to add.
     * @param duration     The duration of the film to add.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 1 if the test fails, 0 otherwise.
     */
    private static int addItemFilmAlreadyExistsTest(ISocialNetwork sn, String login, String pwd, String title,
                                                    String kind, String director, String scriptwriter, int duration,
                                                    String testId, String errorMessage) {
        int nbFilms = sn.nbFilms();

        try {
            sn.addItemFilm(login, pwd, title, kind, director, scriptwriter, duration);
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;
        } catch (ItemFilmAlreadyExistsException e) {
            if (sn.nbFilms() != nbFilms) {
                System.out.println("Err " + testId
                        + " : ItemFilmAlreadyExistsException was thrown, but the number of Film was changed");
                return 1;
            } else
                return 0;
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Tests if addItemFilm method adds a film successfully.
     *
     * @param sn           The social network on which to perform the test.
     * @param login        The user's login.
     * @param pwd          The user's password.
     * @param title        The title of the film to add.
     * @param kind         The category of the film to add.
     * @param director     The director of the film to add.
     * @param scriptwriter The scriptwriter of the film to add.
     * @param duration     The duration of the film to add.
     * @param testId       The identifier of the test.
     * @return 1 if the test fails, 0 otherwise.
     */
	private static int addItemFilmOKTest(ISocialNetwork sn, String login, String pwd, String title, String kind,
			String director,String scriptwriter, int duration, String testId) {
		int nbFilms = sn.nbFilms();
		try {
			sn.addItemFilm(login, pwd, title, kind, director, scriptwriter,duration);
			if (sn.nbFilms() != nbFilms + 1) {
				System.out.println("Err " + testId + " : the number of Film (" + nbFilms + ") was not incremented");
				return 1;
			} else
				return 0;
		} catch (Exception e) {
			System.out.println("Err " + testId + " : unexpected exception " + e);
			e.printStackTrace();
			return 1;
		}
	}

	/**
	 * Tests if addItemFilm method throws NotMemberException when the user is not a member.
	 *
	 * @param sn           The social network on which to perform the test.
	 * @param login        The user's login.
	 * @param pwd          The user's password.
	 * @param title        The title of the film to add.
	 * @param kind         The category of the film to add.
	 * @param director     The director of the film to add.
	 * @param scriptwriter The scriptwriter of the film to add.
	 * @param duration     The duration of the film to add.
	 * @param testId       The identifier of the test.
	 * @param errorMessage The error message to display in case of failure.
	 * @return 1 if the test fails, 0 otherwise.
	 */
	private static int addItemFilmNotMemberTest(ISocialNetwork sn, String login, String pwd, String title, String kind,
	                                            String director, String scriptwriter, int duration, String testId,
	                                            String errorMessage) {
	    int nbFilms = sn.nbFilms();
	    try {
	        sn.addItemFilm(login, pwd, title, kind, director, scriptwriter, duration);
	        System.out.println("Err " + testId + " : " + errorMessage);
	        return 1;
	    } catch (NotMemberException e) {
	        if (sn.nbFilms() != nbFilms) {
	            System.out.println(
	                    "Err " + testId + " : NotMemberException was thrown, but the number of Film was changed");
	            return 1;
	        } else
	            return 0;
	    } catch (Exception e) {
	        System.out.println("Err " + testId + " : unexpected exception. " + e);
	        e.printStackTrace();
	        return 1;
	    }
	}

	/**
	 * Executes the test cases for the addItemFilm method.
	 *
	 * @return TestReport containing the results of the tests.
	 * @throws MemberAlreadyExistsException
	 * @throws BadEntryException
	 */
	public static TestReport test() throws BadEntryException, MemberAlreadyExistsException {

		ISocialNetwork sn = new SocialNetwork();

		int nbBooks = sn.nbBooks(); // number of books in 'sn' (should be 0
									// here)
		int nbFilms = sn.nbFilms(); // number of films in 'sn' (should be 0
									// here)

		int nbTests = 0; // total number of performed tests
		int nbErrors = 0; // total number of failed tests

		System.out.println("Testing addItemFilm()");

		// <=> test n°1

		// check if incorrect parameters cause addMember() to throw BadEntry
		// exception

		sn.addMember("test", "qsdfgh", "test");
		
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", null, "fx", "jean", "scriptwriter",250 ,"1.1",
				"addItemFilm() doesn't reject null title");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "", "kind", "author", "scriptwriter",250, "1.2",
				"addItemFilm() doesn't reject title that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", null, "jean", "scriptwriter",250, "1.3",
				"addItemFilm() doesn't reject null kind");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "", "author", "scriptwriter",250, "1.4",
				"addItemFilm() doesn't reject kind that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "fx", null, "scriptwriter",250, "1.5",
				"addItemFilm() doesn't reject null director");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "kind", "", "scriptwriter",250, "1.6",
				"addItemFilm() doesn't reject director that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "fx", "author", null,250, "1.7",
				"addItemFilm() doesn't reject null scriptwriter");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "kind", "author", "",250, "1.8",
				"addItemFilm() doesn't reject scriptwriter that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "qsdfgh", "test", "kind", "author", "",-25, "1.9",
				"addItemFilm() doesn't reject a film duration who is not stricly positive ");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "", "qsdfgh","test","kind","jean","scriptwriter",45, "1.10",
				"addItemFilm() doesn't reject null login");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, null, "qsdfgh","test","kind","jean","scriptwriter",45, "1.11",
				"addItemFilm() doesn't reject login that don't contain at least one character other than space");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", "","test","kind","jean","scriptwriter",45, "1.12",
				"addItemFilm() doesn't reject null password");
		nbTests++;
		nbErrors += addItemFilmBadEntryTest(sn, "test", null,"test","kind","jean","scriptwriter",45, "1.13",
				"addItemFilm() doesn't reject password that don't contain at least one character other than space");

		// <=> test n°2

		// populate 'sn' with 3 members
		sn.addMember("Paul", "paul", "fan de manga");
		sn.addMember("Antoine", "antoine", "fan de tuto");
		sn.addMember("Janne", "uoiu", "ras");

		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Paul", "paul", "harry potter", "fx", "author","scriptwriter", 160, "2.1a");
		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Antoine", "antoine", "TENET", "fx", "author","scriptwriter",
				180, "2.1b");
		nbTests++;
		nbErrors += addItemFilmOKTest(sn, "Janne", "uoiu", "divergente", "fx","author","scriptwriter", 184, "2.1c");
		// try to add already registered book

		nbFilms = sn.nbFilms();
		
		nbTests++;
		nbErrors += addItemFilmAlreadyExistsTest(sn, "Paul", "paul", new String("harry potter"), "fx",
				"author", "scriptwriter", 160, "2.2",
				"The title of the first Film was accepted as title for a new Film");
		nbTests++;
		nbErrors += addItemFilmAlreadyExistsTest(sn, "Janne", "uoiu", new String("divergente"), "fx",
				"author", "scriptwriter", 160, "2.3",
				"The title of the last film was accepted as title for a new film");
		nbTests++;
		nbErrors += addItemFilmAlreadyExistsTest(sn, "Antoine", "antoine", new String("DivergentE"),
				"fx", "author", "scriptwriter", 160, "2.4",
				"An already registered film, but with different case, was accepted as title for a new film");
		nbTests++;
		nbErrors += addItemFilmAlreadyExistsTest(sn, "Antoine", "antoine", new String("Divergente "),
				"fx", "author", "scriptwriter", 160, "2.5",
				"An already registered login, surrounded by leading/trailing blanks, was accepted as login for a new film");
		nbTests++;
		nbErrors += addItemFilmAlreadyExistsTest(sn, "Antoine", "antoine", "harry" + " potter","fx", "author", "scriptwriter", 160, "2.6",
				"A String concatenation building an already registered film was accepted as title for a new film");

		nbTests++;

		// try to add book whit no registered member
		nbErrors += addItemFilmNotMemberTest(sn, "Victor", "STETER", "one piece red", "animation", "oda","oda", 1630,
				"3.1", "The book has been added, but the member does not exist");
		nbTests++;

		nbErrors += addItemFilmNotMemberTest(sn, "Paul", "STETER", "one piece red", "animation", "oda","oda", 1630, "3.2",
				"The book has been added, but the member pwr does not exist");
		nbTests++;

		// check that 'sn' was not modified
		if (nbFilms != sn.nbFilms()) {
			System.out.println("Error : the number of films was unexepectedly changed by addItemFilm()");
			nbErrors++;
		}
		nbTests++;

		if (nbBooks != sn.nbBooks()) {
			System.out.println("Error : the number of books was unexepectedly changed by addItemFilm()");
			nbErrors++;
		}

		// Display final state of 'sn'
		System.out.println("Final state of the social network : " + sn);

		// Print a summary of the tests and return test results
		try {
			TestReport tr = new TestReport(nbTests, nbErrors);
			System.out.println("addItemFilm : " + tr);
			return tr;
		} catch (NotTestReportException e) { // This shouldn't happen
			System.out.println("Unexpected error in addItemFilm test code - Can't return valuable test results");
			return null;
		}
	}

	/**
	 * Main method to execute the test cases.
	 *
	 * @param args Command-line arguments.
	 * @throws MemberAlreadyExistsException
	 * @throws BadEntryException
	 */
	public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException {
		test();
	}
}
