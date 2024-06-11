package tests;

/** 
 * This class launches a test suite for the SocialNetwork.
 * It runs a series of tests and aggregates their results.
 * 
 * @author B. Prou, GO
 * @version V2.0 - April 2018
 */
public class SocialNetworkTest {

    /**
     * Main method to launch the test suite.
     * 
     * @param args not used
     */
    public static void main(String[] args) {
        try {
            TestReport testSuiteReport = new TestReport(0, 0);
            TestReport tr;
            
            // Run initial test
            tr = InitTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run AddMemberTest
            tr = AddMemberTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run AddItemBookTest
            tr = AddItemBookTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run AddItemFilmTest
            tr = AddItemFilmTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run ReviewItemBookTest
            tr = ReviewItemBookTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run ReviewItemFilmTest
            tr = ReviewItemFilmTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // Run ConsultItemsTest
            tr = ConsultItemsTest.test();
            testSuiteReport.add(tr);
            System.out.println("\n\n **********************************************************************************************\n");
            
            // End of the test suite: give some feedback to the tester
            System.out.println("Global tests results: \n" + testSuiteReport);
        } catch (Exception e) {
            System.out.println("ERROR: Some exception was thrown unexpectedly");
        }
    }
}
