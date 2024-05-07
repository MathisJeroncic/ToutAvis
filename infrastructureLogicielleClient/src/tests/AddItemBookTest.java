package tests;

import opinion.ISocialNetwork;
import opinion.SocialNetwork;


import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.NotTestReportException;
import exceptions.NotMemberException;



public class AddItemBookTest {
	private static int addItemBookBadEntryTest(ISocialNetwork sn,String login,String pwd,
			String title,String kind,String author,String testId, int nbPages, 
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
	
	private static int addItemBookAlreadyExistsTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, String testId, int nbPages,
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
	private static int addItemBookOKTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, String testId, int nbPages) {
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
	
	private static int addItemBookNotMemberTest(ISocialNetwork sn, String login, String pwd,
			String title, String kind, String author, String testId, int nbPages,
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

	public static void main(String[] args) {
	}
}
