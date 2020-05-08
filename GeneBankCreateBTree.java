import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Scanner;

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
		int degree = 1;//our default degree
		File gbkFile = null;
		int sequenceLength = 0;
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
		//At this point we should be all parsed up
		Scanner sc = null;
		try {
			sc = new Scanner(gbkFile);
		} catch (FileNotFoundException e) {
			System.out.println("File not found.");
			System.exit(1);
		}
		while(sc.hasNext() && sc.next().contentEquals("ORIGIN")) {
			sc.next();
		}
		//create a BTree to store information
		BTree geneBankBTree = new BTree(degree);
		long inLong=0, putLong = 0, mask = 0;
		int counter = 0;
		
		for(int i = 0; i < sequenceLength; i++) {
			mask <<= 2;
			mask |= 0b11; //creates a mask with enough bits
		}
		while(sc.hasNext() && sc.next() != "//") {
			Reader letterReader = new StringReader(sc.next());
			BufferedReader br = new BufferedReader(letterReader);
			try {
				int c = 0;
				while((c = br.read()) != -1) { //let's read each line char by char, shall we?
					if(c == 65) { //A 00
						inLong = 0b00;
					}
					if(c == 84) { //T 11
						inLong = 0b11;
					}
					if(c == 67) { //C  01
						inLong = 0b01;
					}
					if(c == 71) { //G 10
						inLong = 0b10;
					}
					if(c == 110) { //n
						counter = 0;
						inLong = 0;
						putLong = 0;
					}
					if(counter < sequenceLength-1 && c != 110) {
						putLong <<= 2;
						putLong |= inLong;
						counter++;
					}
					else if(c != 110) {
						putLong <<= 2;
						putLong |= inLong;
						putLong &= mask;
						counter++;
						geneBankBTree.add(new TreeObject(putLong)); 						
					}

				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}