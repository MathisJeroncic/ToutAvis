package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;
import exceptions.NotTestReportException;

/**
 * The ConsultItemsTest class contains methods to test the consultItems() method in the ISocialNetwork interface implementation.
 */
public class ConsultItemsTest {

    /**
     * Tests if the consultItems() method throws BadEntryException for incorrect parameters.
     *
     * @param sn           The social network on which to perform the test.
     * @param title        The title of the item to consult.
     * @param testId       The identifier of the test.
     * @param errorMessage The error message to display in case of failure.
     * @return 0 if the test passes, 1 otherwise.
     */
    private static int consultItemsBadEntryTest(ISocialNetwork sn, String title, String testId, String errorMessage) {
        try {
            sn.consultItems(title);
            System.out.println("Err " + testId + " : " + errorMessage);
            return 1;
        } catch (BadEntryException e) {
            return 0;
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Tests if the consultItems() method successfully consults an item.
     *
     * @param sn     The social network on which to perform the test.
     * @param title  The title of the item to consult.
     * @param testId The identifier of the test.
     * @return 0 if the test passes, 1 otherwise.
     */
    private static int consultItemsOkTest(ISocialNetwork sn, String title, String testId) {
        try {
            sn.consultItems(title);
            return 0;
        } catch (Exception e) {
            System.out.println("Err " + testId + " : unexpected exception. " + e);
            e.printStackTrace();
            return 1;
        }
    }

    /**
     * Executes the test cases for the consultItems() method.
     *
     * @return TestReport containing the results of the tests.
     * @throws MemberAlreadyExistsException if a member with the given login already exists.
     * @throws BadEntryException           if one of the entries is invalid.
     * @throws ItemBookAlreadyExistsException 
     * @throws NotMemberException 
     * @throws ItemFilmAlreadyExistsException 
     */
    public static TestReport test() throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException {
        ISocialNetwork sn = new SocialNetwork();

        int nbTests = 0; // total number of performed tests
        int nbErrors = 0; // total number of failed tests

        System.out.println("Testing consultItems()");

        // Populate the social network with members and items
        sn.addMember("Paul", "paul", "Potterhead");
        sn.addMember("Jean", "jean", "lecteur passionner");
        sn.addItemBook("Paul", "paul", "Harry Potter", "Roman", "JK Rowling", 600);
        sn.addItemFilm("Paul", "paul", "Star Wars", "FX", "director", "scriptwriter", 300);

        // Test consultItems() with invalid parameters
        nbTests++;
        nbErrors += consultItemsBadEntryTest(sn, null, "1.1", "consultItems() doesn't reject null title");

        nbTests++;
        nbErrors += consultItemsBadEntryTest(sn, " ", "1.2", "consultItems() doesn't reject title that don't contain at least one character other than space");

        // Test consultItems() with valid parameters
        nbTests++;
        nbErrors += consultItemsOkTest(sn, "Harry Potter", "2.1");

        nbTests++;
        nbErrors += consultItemsOkTest(sn, "Star Wars", "2.2");

        // Print a summary of the tests and return test results
        try {
            TestReport tr = new TestReport(nbTests, nbErrors);
            System.out.println("ConsultItemsTest : " + tr);
            return tr;
        } catch (NotTestReportException e) { // This shouldn't happen
            System.out.println("Unexpected error in ConsultItemsTest test code - Can't return valuable test results");
            return null;
        }
    }

    /**
     * Main method to execute the test cases.
     *
     * @param args Command-line arguments.
     * @throws MemberAlreadyExistsException if a member with the given login already exists.
     * @throws BadEntryException           if one of the entries is invalid.
     * @throws ItemFilmAlreadyExistsException 
     * @throws ItemBookAlreadyExistsException 
     * @throws NotMemberException 
     */
    public static void main(String[] args) throws BadEntryException, MemberAlreadyExistsException, NotMemberException, ItemBookAlreadyExistsException, ItemFilmAlreadyExistsException {
        test();
    }
}
