/**
 * This is the main method that uses TreeCollection and Tree classes to create a user 
 * friendly database of trees.
 * @author Julie Cestaro
 * @version 23 April 2017
 * 
 */


package Project4;

import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class NYCStreetTrees {
	
	/**
	 * This main method takes in a file name or absolute path to a CSV file as a 
	 * command line argument and creates a user-friendly database of the tree 
	 * objects created from the data in the file.
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception{
		if(args.length == 0){
			//there are no arguments
			System.err.println("The program expects a file name as an argument");
			System.exit(1);
		}
		//read in the file
		File file = new File(args[0]);
		if (!file.exists()){
			System.err.println("File does not exist");
			System.exit(1);
		}
		
		//create a new scanner to read the file
		Scanner input = null;
		//be sure that the file exists
		try{
			input = new Scanner(file);
		}catch(FileNotFoundException e){
			System.err.println("File not found exception thrown for file " + file.getName());
			System.exit(1);
		}
		
		//create treeCollection to contain future tree objects
		TreeCollection listOfTrees = new TreeCollection();
		
		//while loop to read and parse data
		while (input.hasNextLine()){
			//uses another method to parse the CSV into an ArrayList
			ArrayList<String> splitLine = splitCSVLine(input.nextLine());
			
			//A smaller ArrayList to actually create the tree objects
			ArrayList<String> treeInfo = new ArrayList<String>();	
			
			//validate that the line is not missing data
			if(splitLine.size() == 41){
				//tree_id: 0
				treeInfo.add(splitLine.get(0));
				//tree_dbh: 1
				treeInfo.add(splitLine.get(3));
				//status: 2
				treeInfo.add(splitLine.get(6));
				//health: 3
				treeInfo.add(splitLine.get(7));
				//spc_common: 4
				treeInfo.add(splitLine.get(9));
				//zipcode: 5
				treeInfo.add(splitLine.get(25));
				//boroname: 6
				treeInfo.add(splitLine.get(29));
				//x_sp: 7
				treeInfo.add(splitLine.get(39));
				//y_sp: 8
				treeInfo.add(splitLine.get(40));
			}else{
				continue;
			}
			
			//attempt to create a tree object
			try{
				Tree t = new Tree(Integer.parseInt(treeInfo.get(0)), Integer.parseInt(treeInfo.get(1)), treeInfo.get(2), treeInfo.get(3), 
						treeInfo.get(4), Integer.parseInt(treeInfo.get(5)), treeInfo.get(6), Double.parseDouble(treeInfo.get(7)), 
						Double.parseDouble(treeInfo.get(8)));
				//add successfully created tree objects to treeCollection
				listOfTrees.add(t);
				
			}catch(IllegalArgumentException e){
				
			}
			
		}
		
		input.close();
		
		Scanner userIn = new Scanner(System.in);
		//while loop to take user input and display output
		while(true){
			System.out.println("\nEnter the tree species to learn more about it (\"quit\" to stop): ");
			
			//new scanner to take in user input
			String user = userIn.nextLine();
			
			//case to end program
			if(user.equalsIgnoreCase("quit")){
				break;
			}
			
			//use the method in treeCollection to print the species matching the user input
			ArrayList<String> matching = new ArrayList<String>(listOfTrees.getMatchingSpecies(user));				
			if(matching.size() == 0){
				System.out.println("There is no record of " + user + " on the NYC streets.");
				continue;
			}
			System.out.println("All matching species:");
			for (int i = 0; i < matching.size(); ++i){
				System.out.println("\t" + matching.get(i));
			}
			
			//print out information about where trees are located
			System.out.println("\nPopularity in the city:\n");
//			String n = "NYC";
//			System.out.printf("%-13s:\t%5d (%d) %10.2f", 
//					n, listOfTrees.getCountByTreeSpecies(user), listOfTrees.getTotalNumberOfTrees(),
//					getPercentTrees(listOfTrees.getCountByTreeSpecies(user), listOfTrees.getTotalNumberOfTrees()));
			//use static method to print information about specific boroughs
			boroPrint("NYC", user, listOfTrees);
			boroPrint("Manhattan", user, listOfTrees);
			boroPrint("Bronx", user, listOfTrees);
			boroPrint("Brooklyn", user, listOfTrees);
			boroPrint("Queens", user, listOfTrees);
			boroPrint("Staten Island", user, listOfTrees);
			
		}
		
		userIn.close();

	}
	// -----------leftSide ---------------------|--spacesNeeded---|----percentages start here
	// SOME PLACE HERE         :   ????? (?????)|--spacesNeeded---|7.68                
	// SOME OTHER PLACE HERE   :   ???? (????)|----spacesNeeded---|1.24               
	// ANOTHER PLACE HERE      :   ??? (???)|------spacesNeeded---|6.44
	
	
	/**
	 * Takes in the borough name, the species substring entered by the user, and the treeCollection
	 * created in this main method and prints a formatted representation.
	 * @param boro
	 * @param in
	 * @param list
	 */
	public static void boroPrint(String boro, String in, TreeCollection list){
		int percentagesAtColumn = 40;
		String leftSide;
		Double percent;
		if(boro.equals("NYC")){
			leftSide = String.format("\t%-13s:\t%5d (%d)", boro, list.getCountByTreeSpecies(in), list.getTotalNumberOfTrees());
			percent = getPercentTrees(list.getCountByTreeSpecies(in), list.getTotalNumberOfTrees());
		}else{
			leftSide = String.format("\t%-13s:\t%5d (%d)", boro, list.getCountByTreeSpeciesBorough(in,boro), list.getCountByBorough(boro));
			percent = getPercentTrees(list.getCountByTreeSpeciesBorough(in,boro), list.getCountByBorough(boro));
		}
		int spacesNeeded = percentagesAtColumn-leftSide.length();
		char[] spaceArray = new char[spacesNeeded];
		Arrays.fill(spaceArray, ' ');
		String spaceAdded = new String(spaceArray);
		
		DecimalFormat formatter = new DecimalFormat("00.00");
		String percentString = formatter.format(percent);
		String line = leftSide+spaceAdded+percentString+"%%";
		System.out.println(line);
//		System.out.printf("\n%-13s:\t%5d (%d) %10.2f", 
//				boro, list.getCountByTreeSpeciesBorough(in,boro), list.getCountByBorough(boro),
//				getPercentTrees(list.getCountByTreeSpeciesBorough(in,boro), list.getCountByBorough(boro)));
	}
	
	/**
	 * Takes in one argument and divides it by another.
	 * Used as an easy way to find what percentage of a specific kind of tree is
	 * located in each borough.
	 * @param count
	 * @param total
	 * @return percent
	 */
	public static double getPercentTrees(int count, int total){
		double percent = 0.0;
		if (total != 0){
			percent = ((double)count/total)*100;
		}
		return percent;
	}
	
	/**
	 * Splits the given line of a CSV file according to commas and double quotes
	 * (double quotes are used to surround multi-word entries so that they may contain commas)
	 * @author Joanna Klukowska
	 * @param textLine	a line of text to be passed
	 * @return an Arraylist object containing all individual entries found on that line
	 */
	public static ArrayList<String> splitCSVLine(String textLine){
		
		ArrayList<String> entries = new ArrayList<String>(); 
		int lineLength = textLine.length(); 
		StringBuffer nextWord = new StringBuffer(); 
		char nextChar; 
		boolean insideQuotes = false; 
		boolean insideEntry= false;
		
		// iterate over all characters in the textLine
		for (int i = 0; i < lineLength; i++) {
			nextChar = textLine.charAt(i);
			
			// handle smart quotes as well as regular quotes
			if (nextChar == '"' || nextChar == '\u201C' || nextChar =='\u201D') {
					
				// change insideQuotes flag when nextChar is a quote
				if (insideQuotes) {
					insideQuotes = false; 
					insideEntry = false;
				}else {
					insideQuotes = true; 
					insideEntry = true;
				}
			} else if (Character.isWhitespace(nextChar)) {
				if ( insideQuotes || insideEntry ) {
				// add it to the current entry 
					nextWord.append( nextChar );
				}else { // skip all spaces between entries
					continue; 
				}
			} else if ( nextChar == ',') {
				if (insideQuotes){ // comma inside an entry
					nextWord.append(nextChar); 
				} else { // end of entry found
					insideEntry = false;
					entries.add(nextWord.toString());
					nextWord = new StringBuffer();
				}
			} else {
				// add all other characters to the nextWord
				nextWord.append(nextChar);
				insideEntry = true;
			} 
			
		}
		// add the last word ( assuming not empty ) 
		// trim the white space before adding to the list 
		if (!nextWord.toString().equals("")) {
			entries.add(nextWord.toString().trim());
		}

		return entries;
	}


}
