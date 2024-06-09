package opinion;

import java.util.LinkedList;

import exceptions.BadEntryException;

public class ItemFilm extends Item{

	private String director;
	private String scenarist;
	private int duration;
	
public ItemFilm(String title, String kind, String director, String scenarist, int duration) {
		
		super(title,kind);
		
		this.director = director;
		this.scenarist = scenarist;
		this.duration = duration;
		
	}

	public String toString() {
		return ("Film title: " + this.title + "\n" + "Film kind: " + this.kind + "\n" + "Film director: " + this.director
				+ "Film scenarist: " + this.scenarist+ "\n" + "Film duration: " + this.duration);
	}

	public boolean sameFilm(ItemFilm film) {
		return this.title.trim().equalsIgnoreCase(film.title.trim());
	}// Return 1 if the book already exist

	public boolean sameFilm(String title) {
		return this.title.trim().equalsIgnoreCase(title.trim());
	}

	public String getDirector() {
		return this.director;
	}
	
	public String getScenarist() {
		return this.scenarist;
	}

	public int getDuration() {
		return this.duration;
	}
	
	public void checkParameters() throws BadEntryException 
	{

		if (this.title == null || this.title.trim().isEmpty()) {
			throw new BadEntryException("Title cant be empty");
		}
		if (this.kind == null || this.kind.trim().isEmpty()) {
			throw new BadEntryException("Kind cant be empty");
		}
		if (this.director == null || this.director.trim().isEmpty()) {

			throw new BadEntryException("Director cant be empty");
		}
		if (this.scenarist == null || this.scenarist.trim().isEmpty()) {

			throw new BadEntryException("Scenarist cant be empty");
		}
		if (this.duration <= 0) {
			throw new BadEntryException("Duration cant be inferor than 0");
		}
	}
	
	
}
