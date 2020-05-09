import java.io.File;
import java.util.Scanner;

/**
 * CS321: Bioinformatics Group Project
 * 
 * 
 * java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache
 * size>][<debug level>]
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class GeneBankSearch {
    
    private static int withCache;
    private static String btreeFile, queryFile, degree, sequence;
    private static int cacheSize, debugLevel, degreeLevel;
    private static BTree btree;

    public static void main(String[] args) {

        if (args.length >= 3 && args.length <= 5) {

            withCache = Integer.parseInt(args[0]);
            btreeFile = args[1];
            queryFile = args[2];


            // Check query length matches btree length here.
            // if(){}

            if(withCache == 0){

                
                if(args.length == 3){ //No options.

                    System.out.println("No options");
                    GeneBankSearch(0,btreeFile,queryFile,0,0);

                }
                else if(args.length == 4){ //With debug.

                    debugLevel = Integer.parseInt(args[3]);

                    if(debugLevel != 0){
                        System.err.println("Debug level: " + debugLevel + " invalid or unimplemented.");
                    }

                    System.out.println("Debug: " + debugLevel);
                }
                else{

                    System.err.println("Usage: java GeneBankSearch 0 <btree file> <query file> [<debug level>]");
                    System.exit(-1);
                }

            }
            else if(withCache == 1){

                cacheSize = Integer.parseInt(args[3]);

                if(args.length == 4){ //No debug.
                    System.out.println("No debug, cachesize: " + cacheSize);

                }
                else if(args.length == 5){ //All the fixin's.
                   
                    debugLevel = Integer.parseInt(args[4]);

                    if(debugLevel != 0){
                        System.err.println("Debug level: " + debugLevel + " invalid or unimplemented.");
                    }



                    System.out.println("Debug: " + debugLevel + ", cachesize: " + cacheSize);
                }
                else{

                    System.err.println("Usage: java GeneBankSearch 1 <btree file> <query file> [<cache size>][<debug level>]");
                    System.exit(-1);
                }
            }

        }
        else{
            System.err.println("Usage: java GeneBankSearch <0/1(no/with Cache)> <btree file> <query file> [<cache size>][<debug level>]");
            System.exit(-1);
        }
    }

    private static void GeneBankSearch(int cache, String bTreeFile, String queryFile, int cacheSize, int debugLevel) {
    
        for(int i = (bTreeFile.length() - 1); i >= 0; i--){
            if(bTreeFile.charAt(i) != '.'){
                degree += btreeFile.charAt(i);
            }
            else{
                break;
            }
        }


        for(int j = (bTreeFile.length() - 1) - (degree.length()-1); j>=0; j--){
            if(bTreeFile.charAt(j) != '.'){
                sequence += bTreeFile.charAt(j);
            }
            else{
                break;
            }
        }

        degree = reverse(degree);
        sequence = reverse(sequence);

        degreeLevel = Integer.parseInt(degree);

		try{
            btree = new BTree(degreeLevel, sequence.length());
            File qFile = new File(queryFile);
            Scanner s = new Scanner(qFile);

            while(s.hasNext()){
                String query = s.nextLine();

                long qLong = Long.parseLong(query);

                TreeObject found = btree.search(qLong);

                System.out.println(found.getData() + " : " + found.getFrequency());

            }
            s.close();
		}
		catch(Exception e){
			System.err.println("Provided bTree or query file invalid.");
			System.exit(-1);
		}
		
		
		
    }
    
    public static String reverse(String s){
        return new StringBuffer(s).reverse().toString();
    }
	
}