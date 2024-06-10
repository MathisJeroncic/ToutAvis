/**
 * Provides the classes necessary to create and manage a social network.
 * 
 * <p>
 * The main class is {@link opinion.SocialNetwork}, which implements the
 * {@link opinion.ISocialNetwork} interface. This class provides methods to add
 * members, books, and films to the network, as well as methods to add reviews
 * and consult items.
 * </p>
 * 
 * <p>
 * The package also includes the following exceptions:
 * <ul>
 * <li>{@link exceptions.BadEntryException} - Thrown when a method receives an invalid parameter.</li>
 * <li>{@link exceptions.ItemBookAlreadyExistsException} - Thrown when trying to add a book that already exists in the network.</li>
 * <li>{@link exceptions.ItemFilmAlreadyExistsException} - Thrown when trying to add a film that already exists in the network.</li>
 * <li>{@link exceptions.MemberAlreadyExistsException} - Thrown when trying to add a member that already exists in the network.</li>
 * <li>{@link exceptions.NotItemException} - Thrown when trying to review or consult an item that does not exist in the network.</li>
 * <li>{@link exceptions.NotMemberException} - Thrown when a member cannot be found or identified.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * The package also contains the following helper classes:
 * <ul>
 * <li>{@link opinion.Member} - Represents a member of the social network.</li>
 * <li>{@link opinion.ItemBook} - Represents a book in the social network.</li>
 * <li>{@link opinion.ItemFilm} - Represents a film in the social network.</li>
 * <li>{@link opinion.Review} - Represents a review of an item in the social network.</li>
 * </ul>
 * </p>
 * 
 * <p>
 * Example usage:
 * <pre>
 * {@code
 * ISocialNetwork sn = new SocialNetwork();
 * sn.addMember("john_doe", "password123", "Profile description");
 * sn.addItemBook("john_doe", "password123", "The Great Gatsby", "Novel", "F. Scott Fitzgerald", 180);
 * sn.reviewItemBook("john_doe", "password123", "The Great Gatsby", 4.5f, "Amazing book!");
 * }
 * </pre>
 * </p>
 */
package opinion;


