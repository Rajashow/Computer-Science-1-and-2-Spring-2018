import javax.swing.JOptionPane;

/**
 *  The driver class for Book that contains a main method
 * @author Rajashow Parajuli
 * @version : 9.A.6A.c.2
 *
 *         
 **/

public class Amazon {
/**
 * Runs the program
 * */
	public static void main(String[] args) {
	
		String more = "";
		do {
			JOptionPane.showConfirmDialog(null, getInput() + "\n", "Enter Carefully!",
					JOptionPane.OK_CANCEL_OPTION);
			more =JOptionPane.showInputDialog(null,
					"Enter 'y' to add another book"
							+ "\n or enter any other letter or leave it blank if you do not want to",
					"Enter Carefully!", JOptionPane.INFORMATION_MESSAGE);
			if (more == null) {
				cancel();
			}
		} while (more.equals("y"));
		JOptionPane.showMessageDialog(null, "Goodbye");

	}

	/**
	 * That returns a Book object based on user input. This method allows the user to enter book
	 * information, uses the information to construct a Book object and returns it.
	 * 
	 * @return Book that the user defined
	 */
	public static Book getInput() {
		int value = JOptionPane.showConfirmDialog(null,
				"Hello Welcome to Amazon books entry application."
						+ "\n If Amazon was not tech company and used fax for the primary method to share file."
						+ "\n lets get started press Yes to continue to enter book. No to quit."
						+ "\n MADE BY RAJAHSOW PARAJULI",
				"Please Confirm", JOptionPane.YES_NO_OPTION);
		if (value == JOptionPane.YES_OPTION) {

			String author, title;
			title = JOptionPane.showInputDialog(null, "Enter the title of the book.", "Enter Carefully!",
					JOptionPane.INFORMATION_MESSAGE);
			if (title == null) {
				cancel();
			}
			author = JOptionPane.showInputDialog(null, "Enter the author of " + title, "Enter Carefully!",
					JOptionPane.INFORMATION_MESSAGE);
			if (author == null) {
				cancel();
			}
			String k = JOptionPane.showInputDialog(null,
					"Enter 'y' to enter more infomation like price, # of ratings..."
							+ "\n or enter any other letter or leave it blank if you do not want to",
					"Enter Carefully!", JOptionPane.INFORMATION_MESSAGE);
			if (author == null) {
				cancel();
			}
			if (k.equals("y")) {
				double avgRating, price;
				int numbOfRatings = Integer.parseInt(JOptionPane.showInputDialog(null,
						"Enter number of ratings for " + title, "Enter Carefully!", JOptionPane.INFORMATION_MESSAGE));
				do {
					avgRating = Double.parseDouble(JOptionPane.showInputDialog(null,
							"Enter the average rating for " + title
									+ "\n it must be between 1-4 and you can put 0 if you do  not know it",
							"Enter Carefully!", JOptionPane.INFORMATION_MESSAGE));
				} while (!(avgRating <= 4 && avgRating >= 0));
				;
				price = Double.parseDouble(JOptionPane.showInputDialog(null, "Enter the price for " + title,
						"Enter Carefully!", JOptionPane.INFORMATION_MESSAGE));
				boolean hardcover = ("y".equals(
						JOptionPane.showInputDialog(null, "Enter 'y' if it has a hardcover else enter any other letter",
								"Enter Carefully!", JOptionPane.INFORMATION_MESSAGE))) ? true : false;

				return new Book(title, author, hardcover, price, avgRating, numbOfRatings);

			} else {
				return new Book(title, author);

			}
		} else if (value == JOptionPane.NO_OPTION) {
			cancel();
		}

		return null;
	}
/**
 * Closes the program
 * */
	public static void cancel() {
		JOptionPane.showMessageDialog(null, "Canceled so closing the program");
		System.exit(1);
	}
}
