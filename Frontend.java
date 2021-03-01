import java.util.List;
import java.util.Scanner;
/**
 * This class is the frontend of the Movie Mapper project. It allows users to move from the base mode
 * to the genre selection and ratings mode and view the available movies.
 * @author austincohen
 *
 */
public class Frontend {
	private Scanner sc = new Scanner(System.in);
	private Backend backend; //implementing backend code
	private String [] selectedGenres;
	private String [] selectedRatings;

	public Frontend() {
	}
	/**
	 * This method runs the base mode of the movie mapper frontend class
	 * @param selectedGenres: an array of strings that say either SELECTED or UNSELECTED for each genre
	 * @param selectedRatings: an array of strings that say either SELCETED or UNSELCTED for each rating
	 * @param backend: the backend which is being used for this frontend interface
	 */
	public void runBaseMode() {
		if (selectedGenres==null) {
			selectedGenres=  new String [backend.getAllGenres().size()];
			for (int i=0; i<backend.getAllGenres().size(); i++)
				selectedGenres[i]="UNSELECTED";
		}//beings selected & unselected genres 
		if (selectedRatings==null) {
			selectedRatings=  new String [11];
			for (int i=0; i<11; i++)
				selectedRatings[i]="UNSELECTED";
		}//beings selected & unselected rankings 
		String userInput="";
		//welcome message
		System.out.println("Welcome to MovieMapper Base Mode. You may type numbers to naviage through the "
				+ "given movies which are ranked in decending rating order. \"x\" to quit.");
		System.out.println("Enter \"g\" to enter genre mode and enter \"r\" to enter ratings mode.");
		//prints first three movies
		//big for loop which keeps user in program "x" leaves
		List<MovieInterface> currThree;
		int location=0;
		while (!userInput.equals("x")) {
			currThree=backend.getThreeMovies(location);
			for (int i=0; i<currThree.size();i++)
				System.out.println(currThree.get(i));
			if (backend.getNumberOfMovies()==0)
				System.out.println("There are no movies selected, \"x\" to exit, \"g\" to enter genre mode, or \"r\" to enter ratings mode.");
			else if (backend.getNumberOfMovies()==1)
				System.out.println("There is only one available movie, \"x\" to exit, \"g\" to enter genre mode, or \"r\" to enter ratings mode.");
			else 
				System.out.println("Enter a number between 1 and "+(backend.getNumberOfMovies())+" to scroll through movies, "
						+ "\"x\" to exit, \"g\" to enter genre mode, or \"r\" to enter ratings mode.");
			userInput=sc.next();
			if (userInput.equals("x"))
				break;//break loop if user enters "x"
			if (userInput.equals("g")) {//enter genre mode 
				runGenreMode(selectedGenres);
				continue;//return from genre mode
			}
			if (userInput.equals("r")) {//enters rating mode
				runRatingSelectionMode(selectedRatings);
				continue;//returns from ratings mode
			}
			int x=turnStringToInt(userInput);//turns input into a string
			if (x==-1||x<1||x>backend.getNumberOfMovies()) {//returns to beginning of loop with invalid input
				System.out.println("Invalid Input");
				continue;
			}
			location=x-1;//moves down list of movies
		}
	}
	/**
	 * This method runs the genre mode which allows users to select and unselect genres to filter their movies
	 * @param givenArr: an array of strings which says either "SELECTED" or "UNSELECTED" for each genre
	 * @return: an array of strings which is the changed version of the entered array of "SELECTED"s and "UNSELECTED"s
	 */
	public String [] runGenreMode(String []givenArr) {
		List<String>genreList=backend.getAllGenres();
		String [] arr=givenArr;
		String userInput="";
		//Welcome Instructions
		System.out.println("Welcome to MovieMapper Genre Mode. You may select or unselct genres "
				+ "then return to base mode by typing \"x\". ");
		System.out.println("If multiple genres are selected only movies that are in both genres will"
				+ "be shown."+
				"To select a genre type \"s\" followed by the number next to the name of the wanted genre."+
				"To unselect a genre type \"u\" followed by number next to the name of the wanted genre."+
				"For example if you wanted to select the first genre you would type \"s1\". The genres are listed below:");
		while (!userInput.equals("x")) {
			printGenres(arr, genreList);//prints current list of selected and unselected
			//loop instructions 
			System.out.println("Enter \"s\" or \"u\" followed by the number of genre you would like to select or unselect or \"x\" to exit.");
			userInput=sc.next();
			if (userInput.equals("x"))
				return arr;
			char firstLetter=userInput.charAt(0);//breaks into select or unselect
			int genre=turnStringToInt(userInput.substring(1));//breaks into what genre 
			if ((firstLetter!='s'&&firstLetter!='u')||genre<1||genre>genreList.size()) {//checks valid input
				System.out.println("Invalid Input");
				continue;
			}
			if(firstLetter=='s') {//selects genre
				arr[genre-1]="SELECTED";
				backend.addGenre(genreList.get(genre-1));
			}
			if(firstLetter=='u') {//unselects genre
				arr[genre-1]="UNSELECTED";
				backend.removeGenre(genreList.get(genre-1));
			}
		}
		return null;//should never get here
	}
	/**
	 * This method runs the rating mode which allows users to select and unselect ratings to filter their movies
	 * @param givenArr: an array of strings which says either "SELECTED" or "UNSELECTED" for each rating
	 * @return: an array of strings which is the changed version of the entered array of "SELECTED"s and "UNSELECTED"s
	 */
	public String[] runRatingSelectionMode(String []givenArr) {
		String [] arr=givenArr;
		String userInput="";
		//Welcome instructions
		System.out.println("Welcome to MovieMapper Ratings Mode. You may select or unselect ratings "
				+ "then return to base mode by typing \"x\". ");
		System.out.println("If multiple ratings are selected if a movie has any of the selected ratings it will be shown."+
				"To select a rating type \"s\" followed by the wanted rating."+
				"To unselect a rating type \"u\" followed by the wanted rating."+
				"For example if you wanted to select the movies of a rating between one and two you would enter \"s1\". "
				+ "The ratings selection is below:");
		while (!userInput.equals("x")) {
			printRatings(givenArr);
			System.out.println("Enter \"s\" or \"u\" followed by the rating you would like to select or unselect or \"x\" to exit.");
			userInput=sc.next();
			if (userInput.equals("x"))
				return arr;
			char firstLetter=userInput.charAt(0);//breaks into s or d 
			int rating=turnStringToInt(userInput.substring(1));//breaks into what specific rating
			if ((firstLetter!='s'&&firstLetter!='u')||rating<0||rating>10) {//check input validity
				System.out.println("Invalid Input");
				continue;
			}
			if(firstLetter=='s') {//selected option
				arr[rating]="SELECTED";
				backend.addAvgRating(userInput.substring(1));
			}
			if(firstLetter=='u') {//unselected option
				arr[rating]="UNSELECTED";
				backend.removeAvgRating(userInput.substring(1));
			}
		}
		return null;
	}
	/**
	 * This method adds the backend to the frontend
	 * @param backend: the specific backend to be added to the frontend
	 */
	public void run (Backend backend) {
		if (backend==null)
			return;
		this.backend=backend;
		runBaseMode();
		return;
	}

	/**
	 * Helper method for all three modes which turns string input into a number
	 * @param x: is the entered string
	 * @return int: the number version of the entered string. If the string is not a number return -1
	 */
	private int turnStringToInt(String x) {
		int num=0;
		for (int i=0;i<x.length(); i++) {
			if ((int)x.charAt(i)>47&&(int)x.charAt(i)<58) {
				num+=(int)(x.charAt(i)-48)* Math.pow(10, x.length()-i-1);
			}
			else {
				return -1;
			}
		}
		return num;
	}
	/**
	 * This method prints all genres and whether they are selected or not
	 * @param selected: an array of strings and whether a specific genre is selected or not
	 * @param genres: the list of all genres
	 */
	private void printGenres(String[]selected, List<String>genres ) {
		for (int i=0; i<genres.size();i++) {
			System.out.println((i+1)+". " +genres.get(i)+": "+selected[i]);
		}
	}
	/**
	 * This method prints all ratings and states whether or not it is selected or not
	 * @param selected: an array of whether or not a value is selected 
	 */
	private void printRatings(String []selected) {
		for (int i=0; i<11; i++) {
			System.out.println((i)+". "+selected[i]);
		}
	}
}



