package opinion;
//2eme Test git
import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.ItemBookAlreadyExistsException;
import exceptions.ItemFilmAlreadyExistsException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotItemException;
import exceptions.NotMemberException;

/**
 * Skeleton for the SocialNetwork
 * 
 */
public class SocialNetwork implements ISocialNetwork {

	private LinkedList<Member> members;
	private LinkedList<ItemBook> itemBooks;
	
	
    /**
     * Constructeur pour créer une instance du réseau social.
     */
	
	public SocialNetwork()
	{
		 members = new LinkedList<Member>();
		 itemBooks = new LinkedList<ItemBook>();
	}
	
	
	/**
     * Ajoute un nouveau membre au réseau social.
     * 
     * @param login Le login du membre à ajouter. Ne doit pas être {@code null} ou vide.
     * @param password Le mot de passe du membre à ajouter. Ne doit pas être {@code null} et doit contenir au moins 4 caractères.
     * @param profile Le profil du membre à ajouter. Ne doit pas être {@code null}.
     * @throws BadEntryException Si le login est {@code null}, vide, le mot de passe est {@code null}, a moins de 4 caractères, ou le profil est {@code null}.
     * @throws MemberAlreadyExistsException Si un membre avec le même login existe déjà dans le réseau.
     */
	@Override
	public void addMember(String login, String password, String profile)
			throws BadEntryException, MemberAlreadyExistsException {
		
		
		Member memberToAdd=new Member(login,password,profile);
		
		memberToAdd.checkParameters();
		
		for (Member m : members)
		{
			if(memberToAdd.areYou(m.getLogin())) {throw new MemberAlreadyExistsException();}
		}
		members.add(memberToAdd);
	}
	
	
	
    /**
     * Vérifie si un membre avec le login spécifié existe dans le réseau social.
     * 
     * @param login Le login à vérifier.
     * @return {@code true} si le membre existe, {@code false} sinon.
     */
	
	
	@Override
	public int nbMembers() {
		
		return members.size();
	}

	@Override
	public int nbFilms() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int nbBooks() {
		return itemBooks.size();
	}



	@Override
	public void addItemFilm(String login, String password, String title,
			String kind, String director, String scriptwriter, int duration)
			throws BadEntryException, NotMemberException,
			ItemFilmAlreadyExistsException {
		// TODO Auto-generated method stub

	}

	@Override
	public void addItemBook(String login, String password, String title,
			String kind, String author, int nbPages) throws BadEntryException,
			NotMemberException, ItemBookAlreadyExistsException {
		
		boolean identification=false;
		
		ItemBook itemBookToAdd=new ItemBook(title,kind,author,nbPages);
		
		for (ItemBook b : itemBooks)
		{
			if(itemBookToAdd.sameBook(b)) {throw new ItemBookAlreadyExistsException();}
		}
		for (Member m : members)
		{
			if(m.areYou(login))
			{
				if(m.identifyMember(members,login, password))
					{
					identification=true;

					}
				else
				{
					throw new NotMemberException("Identification manquée");	
				}
				
			}
		}
		
		itemBookToAdd.checkParameters();
		
		if(identification==true)
		{
		itemBooks.add(itemBookToAdd);
		}
	}

	@Override
	public float reviewItemFilm(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public float reviewItemBook(String login, String password, String title,
			float mark, String comment) throws BadEntryException,
			NotMemberException, NotItemException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public LinkedList<String> consultItems(String title)
			throws BadEntryException {
		return new LinkedList<String>();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
