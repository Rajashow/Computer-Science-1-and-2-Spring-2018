import java.util.Random;

/**
 * Book is an book object that has the title, author, # of ratings,
 *          Avg. rating, Price,Recommendation, and Hard cover
 * @author Rajashow Parajuli
 * @version : 9.A.6A.c.2        
 **/
public class Book {
	public int numbOfRatings;
	private double avgRating, price;
	private String title, author, hardcover;
	// recommendations="No Information Is Available For Recommendation"

	/**
	 * Constructor:Takes the information for the title, author and makes a book
	 * 
	 * @param _title
	 *            the title of the book
	 * @param _author
	 *            the author of the book
	 * 
	 **/
	public Book(String _title, String _author) {
		author = _author;
		title = _title;
		price = new Random().nextInt(10) + 1;
		avgRating = numbOfRatings = 0;
		hardcover = "no";// hardcover = (_hardcover)? "yes":"no";
	}

	/**
	 * Constructor:Takes the information for the title, author, price and whether
	 * the book has hardcover or not and creates a book instance with the given
	 * information. The number of ratings and total rating will be set to zero
	 * 
	 * @param _title
	 *            the title of the book
	 * @param _author
	 *            the author of the book
	 * @param _hardcover
	 *            whether the book has a hardcover
	 * @param _price
	 *            the price of the book
	 * @param _avgRate
	 *            the average rating for the book
	 * @param _numbRate
	 *            the number of ratings for the book
	 * 
	 * */
	public Book(String _title, String _author, boolean _hardcover, double _price, double _avgRate, int _numbRate) {
		author = _author;
		title = _title;
		price = _price;
		avgRating = _avgRate;
		numbOfRatings = _numbRate;
		hardcover = (_hardcover) ? "yes" : "no";
	}

	/**
	 * Returns the average rating for this book or 0 if there is no rating for the
	 * book.
	 * 
	 * @return avgRating
	 * */
	public double findAvgRating() {
		return avgRating;
	}

	/**
	 * Takes the rating for the book and adds it to the total rating as well as
	 * incrementing the number of ratings for this book.
	 * 
	 * @param _newRating
	 *            new rating for the book
	 */
	public void addRating(double _newRating) {
		if (_newRating != 0) {
			numbOfRatings++;
			avgRating = (avgRating + _newRating) / 2;
		}
	}

	/**
	 * Returns a string based on the average rating of the book.The book is
	 * “strongly recommended” for average rating between 3 and 4, “Recommended” for
	 * average rating between 2 and 3(exclusive) , “Not Recommended” for average
	 * rating between 1 and 2 (exclusive),and if there is no rating return “No
	 * Information Is Available For Recommendation”.
	 * 
	 * @return String recommendation for the book
	 */
	public String bookRecommendation() {
		if (avgRating < 1)
			return "No Information Is Available For Recommendation";
		else if (4 >= avgRating && 3 <= avgRating)
			return "Strongly Recommendated";
		else if (3 > avgRating && 2 <= avgRating)
			return "Recommendated";
		else if (2 > avgRating && 1 <= avgRating)
			return "Not Recommendated";
		else
			return null;
	}

	/**
	 * Returns the string representation of a Book object: title, author, number of
	 * ratings, average rating, price and book recommendation.
	 * 
	 * @return String with the information about the class
	 */
	public String toString() {
		return String.format(
				"Title : %s " + "\nAuthor : %s " + "\nTotal # of rating : %d " + "\nAverage ratings : %.2f "
						+ "\nPrice : %.2f " + "\nHardcover : %s " + "\nRecomendation based on ratings : %s",
				title, author, numbOfRatings, avgRating, price, hardcover, bookRecommendation());
	}

}
