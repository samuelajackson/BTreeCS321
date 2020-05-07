import java.io.File;

/**
 * CS321: Bioinformatics Group Project
 * 
 * GeneBankCreateBTree parses information from given files
 * and creates BTrees with that information.
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class GeneBankCreateBTree {

	/**
	 * Main function
	 * @param args
	 */
	public static void main(String[] args) {
		if(args.length < 4) {
			//Not enough arguments
			System.exit(1);
		}
		else if(args.length > 6) {
			//too many arguments
			System.exit(1);
		}
		//At this point we probably have the correct number of args
		//Variables set by user
		boolean cache = false;
		int degree;
		File gbkFile;
		int sequenceLength;
		if(Integer.parseInt(args[0]) < 0 || Integer.parseInt(args[0]) > 1) {
			System.out.println("First argument must be either zero or one.");
			System.exit(1);
		}
		else {
			if(Integer.parseInt(args[0]) == 1) {
				cache = true; //turns cache on, off by default
			}
		}
		//checks and sets degree
		if(Integer.parseInt(args[1]) < 0) {
			System.out.println("Degree must be an integer zero or greater.");
			System.exit(1);
		}
		else {
			degree = Integer.parseInt(args[1]);
		}
		//check to make sure the file is valid and makes it
		try{
			gbkFile = new File(args[2]);
		}
		catch(Exception e) {
			System.out.println("Invalid file.");
			System.exit(1);
		}
		//checks and sets sequence length
		if(Integer.parseInt(args[3]) < 1 || Integer.parseInt(args[3]) > 31) {
			System.out.println("Sequence length must be an integer between one and thirty one inclusive.");
			System.exit(1);
		}
		else {
			sequenceLength = Integer.parseInt(args[3]);
		}
		//if cache is turned on, arg 4 is cache size; else, arg 4 is debug level
		int debugLevel = 0;
		//Extra optional bits
		if(args.length > 4) {
			if(cache) {
				if(Integer.parseInt(args[4]) < 0) {
					System.out.println("Cache size must be a positive integer.");
					System.exit(1);
				}
				else {
					int cacheSize = Integer.parseInt(args[4]);
				}
				//M A X I M U M args
				if(args.length == 6) {
					if(Integer.parseInt(args[4]) < 0 || Integer.parseInt(args[4]) > 1){
						System.out.println("Debug level must be one or zero.");
						System.exit(1);
					}
					else {
						debugLevel = Integer.parseInt(args[4]);
					}
				}
			}
			else {
				if(Integer.parseInt(args[4]) < 0 || Integer.parseInt(args[4]) > 1){
					System.out.println("Debug level must be one or zero.");
					System.exit(1);
				}
				else {
					debugLevel = Integer.parseInt(args[4]);
				}
			}
		}
	}
}
