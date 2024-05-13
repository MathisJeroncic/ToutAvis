package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;

/**
 * La classe {@code Member} représente un membre d'un système où les utilisateurs possèdent un login, un mot de passe, et un profil.
 * Cette classe permet de gérer les informations de base d'un utilisateur.
 */
public class Member {
    /**
     * Le login du membre.
     */
    private String login;
    
    /**
     * Le mot de passe du membre.
     */
    private String password;
    
    /**
     * Le profil du membre.
     */
    private String profile;
    
    /**
     * Constructeur pour créer un nouveau membre avec un login, un mot de passe et un profil spécifiés.
     * 
     * @param login Le login du nouveau membre. Ne doit pas être {@code null}.
     * @param password Le mot de passe du nouveau membre. Ne doit pas être {@code null}.
     * @param profile Le profil du nouveau membre. Ne doit pas être {@code null}.
     */
    public Member(String login, String password, String profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }
    
    /**
     * Récupère le login du membre.
     * 
     * @return Le login du membre.
     */
    public String getLogin() {
        return this.login;
    }
    
    /**
     * Récupère le mot de passe du membre.
     * 
     * @return Le mot de passe du membre.
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Récupère le profil du membre.
     * 
     * @return Le profil du membre.
     */
    public String getProfile() {
        return this.profile;
    }
    
    
    
	public boolean areYou(String login) 
	{
	return (login.trim().equalsIgnoreCase(this.login.trim()));			
	}
	
    public boolean identifyMember(LinkedList<Member> members,String login,String password)
    {
    	for(Member m : members)
    	{
    		if(m.areYou(login)&& m.getPassword().trim().equalsIgnoreCase(password))
    		{
    			return true;
    		}
    	}
    	return false;
    }
    
    public void checkParameters()throws BadEntryException
    {
    	
    	if(this.login==null) {throw new BadEntryException("The login is null");}
		if(this.login.strip().isBlank()) {throw new BadEntryException("The login is blank");}
		
		if(this.password==null) {throw new BadEntryException("The password is null");}
		if(this.password.strip().length()<4) {throw new BadEntryException("The password is smaller than 4");}
		
		if(this.profile==null) {throw new BadEntryException("The profile is null");}	

    }
}
