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
    public void checkParameters() throws BadEntryException 
    {

        if (this.title == null || this.title.isEmpty()) {
            throw new BadEntryException("Title cant be empty");
        }
        if (this.kind == null || this.kind.isEmpty()) {
            throw new BadEntryException("Kind cant be empty");
        }
        if (this.author == null || this.author.isEmpty()) {
            throw new BadEntryException("Author cant be empty");
        }
        if (this.nbPages <= 0) {
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
