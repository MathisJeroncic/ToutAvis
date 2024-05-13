package opinion;

import exceptions.BadEntryException;
import exceptions.NotMemberException;

public class ItemBook {
	String title;
	String kind;
	String author;
	int nbPages;
	
	public ItemBook(String title, String kind, String author, int nbPages) {
        this.title = title;
        this.kind = kind;
        this.author = author;
        this.nbPages = nbPages;
	}
	
	public boolean sameBook(ItemBook book) {
		return this.title.equalsIgnoreCase(book.title) && this.author.equalsIgnoreCase(book.author);
	}//Return 1 if the book already exist
	
    public String getTitle() {
        return title;
    }

    public String getKind() {
        return kind;
    }

    public String getAuthor() {
        return author;
    }

    public int getNbPages() {
        return nbPages;
    }
    public void checkParameters(String title, String kind, String author, int nbPages) throws BadEntryException 
    {

        if (title == null || title.isEmpty()) {
            throw new BadEntryException("Title cant be empty");
        }
        if (kind == null || kind.isEmpty()) {
            throw new BadEntryException("Kind cant be empty");
        }
        if (author == null || author.isEmpty()) {
            throw new BadEntryException("Author cant be empty");
        }
        if (nbPages <= 0) {
            throw new BadEntryException("Number of page cant be inferor than 0");
        }
    }
    
    /*
      		if(areYou(login)==false) {throw new NotMemberException(login+" isn't a user.");}
		
		
		Member m;
		int i=0;
		while(members.get(i).getLogin()!=login)
		{
			i=i+1;
		}
		m=members.get(i);
		
		if(m.getPassword()!=password){throw new BadEntryException("The password is wrong");}
		*/
}
