/**
 * CS321: Bioinformatics Group Project
 * 
 * TreeObject class, a container class for objects stored
 * by BTreeNode Objects.
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class TreeObject {


    private int frequency;
    private long data;

    /**
     * Constructor - Takes in long type data, our DNA string,
     * and int type frequency for number of occurences.
     * 
     * @param data
     * @param frequency
     */
    public TreeObject(long data, int frequency){

        this.frequency = frequency;
        this.data = data;
    }

    /**
     * Constructor - Same as above, but frequency default value
     * is set at 1.
     * 
     * @param data
     */
    public TreeObject(long data){

        frequency = 1;
        this.data = data;
    }

    public TreeObject(){}

    /**
     * Gets frequency from given TreeObject.
     * 
     * @return
     */
    public int getFrequency(){

        return frequency;
    }

    /**
     * Increments frequency for given TreeObject.
     */
    public void incrementFrequency(){

        frequency++;
    }

    /**
     * Returns given TreeObject's data.
     * 
     * @return
     */
    public long getData(){

        return data;
    }

    /**
     * Sets given TreeObject's data.
     * 
     * @param data
     */
    public void setData(long data){

        this.data = data;
    }

    /**
     * Prints DNA string (data) and it's related frequency.
     */
    public String toString(){

        String result = data + ":  " + frequency;
        return result;
    }
}