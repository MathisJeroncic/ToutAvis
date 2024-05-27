package opinion;

import java.util.LinkedList;

public class Item {
	
	protected String title;
	protected String kind;
	protected float meanMark;
	protected LinkedList<Review> reviews;
	
	public Item(String title,String kind)
	{
		this.title=title;
		this.kind=kind;
	}
	
	public String getTitle() {
		return this.title;
	}
	
	public String getKind() {
		return this.kind;
	}
	
	public float getMean() {
		float mark = 0.0f;
		for (Review r : reviews) {
			mark += r.getMark();
		}
		this.meanMark=mark/reviews.size();
		return this.meanMark;
	}
	
	public LinkedList<Review> getReviews() {
		return this.reviews;
	}
	
	public int nbReviews()
	{
		return reviews.size();
	}
	
	public void addReviews(Review reviewToAdd) {
		reviews.add(reviewToAdd);
	}
	
	
	
	



}
