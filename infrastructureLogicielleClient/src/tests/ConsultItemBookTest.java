package tests;

import opinion.ISocialNetwork;
import opinion.ItemBook;
import opinion.SocialNetwork;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.NotMemberException;
import exceptions.NotItemException;

public class ConsultItemBookTest {

	private static int consultItemBookBadEntryTest(String title) {
		
		try {
		
			if (title == null || title.trim().isEmpty()) {
				throw new BadEntryException("Title cant be null");
			}
			if (title.strip().isBlank()) {
				throw new BadEntryException("Title cant be empty");
			}
			
			if (title.length()<1) {
				throw new BadEntryException("Title cant be smaller than 1 character");
			}
			
			
			
			return 0;
		} catch (BadEntryException e) {

		}
		return 0;
	}
}
