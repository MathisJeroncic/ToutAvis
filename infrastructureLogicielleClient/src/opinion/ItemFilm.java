package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;

/**
 * Represents a film item that can be reviewed.
 * Extends the Item class by adding director, scenarist, and duration.
 */
public class ItemFilm extends Item {
    
    /**
     * The director of the film.
     */
    private String director;
    
    /**
     * The scenarist of the film.
     */
    private String scenarist;
    
    /**
     * The duration of the film in minutes.
     */
    private int duration;

    /**
     * Constructs an ItemFilm with the specified title, kind, director, scenarist, and duration.
     *
     * @param title The title of the film.
     * @param kind The kind of the film.
     * @param director The director of the film.
     * @param scenarist The scenarist of the film.
     * @param duration The duration of the film in minutes.
     */
    public ItemFilm(String title, String kind, String director, String scenarist, int duration) {
        super(title, kind);
        this.director = director;
        this.scenarist = scenarist;
        this.duration = duration;
    }

    /**
     * Returns a string representation of the film.
     *
     * @return A string with the film's details.
     */
    public String toString() {
        return "Film title: " + this.title + "\n" + 
               "Film kind: " + this.kind + "\n" + 
               "Film director: " + this.director + "\n" + 
               "Film scenarist: " + this.scenarist + "\n" + 
               "Film duration: " + this.duration + " minutes";
    }

    /**
     * Checks if this film has the same title as another film.
     *
     * @param film The film to compare with.
     * @return true if the titles are the same, false otherwise.
     */
    public boolean sameFilm(ItemFilm film) {
        return this.title.trim().equalsIgnoreCase(film.title.trim());
    }

    /**
     * Checks if this film has the same title as the given title.
     *
     * @param title The title to compare with.
     * @return true if the titles are the same, false otherwise.
     */
    public boolean sameFilm(String title) {
        return this.title.trim().equalsIgnoreCase(title.trim());
    }

    /**
     * Returns the director of the film.
     *
     * @return The director of the film.
     */
    public String getDirector() {
        return this.director;
    }

    /**
     * Returns the scenarist of the film.
     *
     * @return The scenarist of the film.
     */
    public String getScenarist() {
        return this.scenarist;
    }

    /**
     * Returns the duration of the film.
     *
     * @return The duration of the film in minutes.
     */
    public int getDuration() {
        return this.duration;
    }

    /**
     * Checks the parameters of the film and throws an exception if any are invalid.
     *
     * @throws BadEntryException if any of the parameters are invalid.
     */
    public void checkParameters() throws BadEntryException {
        if (this.title == null || this.title.trim().isEmpty()) {
            throw new BadEntryException("Title can't be empty");
        }
        if (this.kind == null || this.kind.trim().isEmpty()) {
            throw new BadEntryException("Kind can't be empty");
        }
        if (this.director == null || this.director.trim().isEmpty()) {
            throw new BadEntryException("Director can't be empty");
        }
        if (this.scenarist == null || this.scenarist.trim().isEmpty()) {
            throw new BadEntryException("Scenarist can't be empty");
        }
        if (this.duration <= 0) {
            throw new BadEntryException("Duration can't be less than or equal to 0");
        }
    }
}
