import java.io.File;
import java.io.RandomAccessFile;
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
    
    private static int withCache, degree;
    private static String btreeFile, queryFile, qfName;
    private static String[] btfName;
    private static int cacheSize, debugLevel, btlength, qflength;
    private static BTree btree;
    private static File btf, qf;

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
    
        btf = new File(bTreeFile);
        qf = new File(queryFile);

        if(!btf.exists() || !btf.isFile()){
            System.err.println("BTreeFile does not exist.");
            System.exit(-1);
        }
        if(!qf.exists() || !qf.isFile()){
            System.err.println("BTreeFile does not exist.");
            System.exit(-1);
        }

        btfName = btf.getName().split("\\.");
        btlength = Integer.parseInt(btfName[4]);
        degree = Integer.parseInt(btfName[5]);

        qfName = qf.getName();
        qflength = Integer.parseInt(qfName.substring(qfName.lastIndexOf('y') +1));
        
        if(btlength != qflength){
            System.err.println("Query length does not equal BTree length.");
            System.exit(-1);
        }



    }
    
    public static String reverse(String s){
        return new StringBuffer(s).reverse().toString();
    }
	
}