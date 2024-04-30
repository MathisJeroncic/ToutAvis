package opinion;

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
}
