package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;
import exceptions.MemberAlreadyExistsException;
import exceptions.NotMemberException;

/**
 * The {@code Member} class represents a member of a system where users have a login, password, and profile.
 * This class manages the basic information of a user.
 */
public class Member {
    
    /**
     * The login of the member.
     */
    private String login;
    
    /**
     * The password of the member.
     */
    private String password;
    
    /**
     * The profile of the member.
     */
    private String profile;

    /**
     * Constructor to create a new member with the specified login, password, and profile.
     *
     * @param login    The login of the new member. Must not be {@code null}.
     * @param password The password of the new member. Must not be {@code null}.
     * @param profile  The profile of the new member. Must not be {@code null}.
     */
    public Member(String login, String password, String profile) {
        this.login = login;
        this.password = password;
        this.profile = profile;
    }

    /**
     * Retrieves the login of the member.
     *
     * @return The login of the member.
     */
    public String getLogin() {
        return this.login;
    }

    /**
     * Retrieves the password of the member.
     *
     * @return The password of the member.
     */
    public String getPassword() {
        return this.password;
    }

    /**
     * Retrieves the profile of the member.
     *
     * @return The profile of the member.
     */
    public String getProfile() {
        return this.profile;
    }

    /**
     * Checks if the provided login matches the member's login.
     *
     * @param login The login to compare.
     * @return {@code true} if the logins match, {@code false} otherwise.
     */
    public boolean areYou(String login) {
        return login.trim().equalsIgnoreCase(this.login.trim());
    }

    /**
     * Identifies if a member with the specified login and password exists in the provided list.
     *
     * @param members  The list of members.
     * @param login    The login to identify.
     * @param password The password to identify.
     * @return {@code true} if a member with the matching login and password is found, {@code false} otherwise.
     */
    public boolean identifyMember(LinkedList<Member> members, String login, String password) {
        for (Member m : members) {
            if (m.areYou(login) && m.getPassword().trim().equalsIgnoreCase(password.trim())) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks the parameters of the member and throws an exception if any are invalid.
     *
     * @throws BadEntryException if any of the parameters are invalid.
     */
    public void checkParameters() throws BadEntryException {
        if (this.login == null) {
            throw new BadEntryException("The login is null");
        }
        if (this.login.strip().isBlank()) {
            throw new BadEntryException("The login is blank");
        }

        if (this.password == null) {
            throw new BadEntryException("The password is null");
        }
        if (this.password.strip().length() < 4) {
            throw new BadEntryException("The password is smaller than 4 characters");
        }

        if (this.profile == null) {
            throw new BadEntryException("The profile is null");
        }
    }
}
