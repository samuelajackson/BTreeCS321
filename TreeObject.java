import java.io.Serializable;

/**
 * CS321: Bioinformatics Group Project
 * 
 * TreeObject class, a container class for objects stored
 * by BTreeNode Objects.
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class TreeObject implements Serializable {


    private int frequency;
    private long data;
    private int sequenceLength;
    
    private static final long serialVersionUID = 1577L; // ID for serialization

    /**
     * Constructor - Takes in long type data, our DNA string,
     * and int type frequency for number of occurences.
     * 
     * @param data
     * @param frequency
     * @param sequenceLength
     */
    public TreeObject(long data, int frequency, int sequenceLength){

        this.frequency = frequency;
        this.data = data;
        this.sequenceLength = sequenceLength;
    }

    /**
     * Constructor - Same as above, but frequency default value
     * is set at 1.
     * 
     * @param data
     * @param sequenceLength
     */
    public TreeObject(long data, int sequenceLength){

        frequency = 1;
        this.data = data;
        this.sequenceLength = sequenceLength;
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