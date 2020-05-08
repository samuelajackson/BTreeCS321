import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * CS321: Bioinformatics Group Project
 * 
 * BTree class, a simple implementation of the BTree data
 * structure.
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class BTree implements Serializable {
	// ============================================================
	// Instance variables
	// ============================================================
	
	private int degreeT;		// The minimum degree of the BTree
	private int orderM;			// The maximum order of the BTree
	private int sequenceLength;	// Length of one DNA sequence
	private BTreeNode root;		// The root node of the BTree
	
	private static final long serialVersionUID = 1579L;	// ID of BTree for serialization
	
	// ============================================================
	// Constructor & getters/setters
	// ============================================================
	
	/**
	 * Initializes degreeT and orderM based on provided degree and 
	 * initializes root to an empty BTreeNode
	 * 
	 * @param degree - the minimum desired degree of the BTree
	 * @param length - the length of one DNA sequence
	 */
	public BTree(int degree, int length) {
		degreeT = degree;
		orderM = degreeT * 2;
		sequenceLength = length;
		root = new BTreeNode(degreeT);
	}
	
	/**
	 * Returns the minimum degree of the BTree
	 * @return - degreeT of this BTree
	 */
	public int getDegree() {
		return degreeT;
	}
	
	/**
	 * Returns the maximum order of the BTree
	 * @return - orderM of this BTree
	 */
	public int getOrder() {
		return orderM;
	}
	
	/**
	 * Returns the root node of the BTree
	 * @return - a reference to the root BTreeNode
	 */
	public BTreeNode getRoot() {
		return root;
	}
	
	/**
	 * Sets the root node of the BTree
	 * @param newRoot - the node to become the new root
	 */
	public void setRoot(BTreeNode newRoot) {
		root = newRoot;
	}
	
	/**
	 * Returns the length of one DNA sequence
	 * @return - the value of sequenceLength
	 */
	public int getSequenceLength() {
		return sequenceLength;
	}
	
	// ============================================================
	// Class methods
	// ============================================================
	
	/**
	 * Adds the specified object to the BTree
	 * @param object - the object to be added
	 */
	public void add(TreeObject object) {
		BTreeNode rootAfterAdd = root.add(object);
		this.setRoot(rootAfterAdd);
	}
	
	/**
	 * Searches the BTree for the specified object
	 * @param object - the TreeObject being searched for
	 * @return - the specified TreeObject if found, null otherwise
	 */
	public TreeObject search(long data) {
		BTreeNode current = root;
		boolean found = false;
		boolean end = false;
		TreeObject result = null;
		
		while (!found && !end) {
			int counter = 0;
			while(data > current.getKey(counter).getData() && counter != current.lengthKeys()) {
				counter++;
			}
			
			if (data == current.getKey(counter).getData()) {
				found = true;
				result = current.getKey(counter);
			}
			
			if (!current.isLeaf()) {
				current = current.getChild(counter);
			}
			else {
				end = true;
			}
		}
		
		return result;
	}
	
	
	public byte[] toStream() {
		byte[] stream = null;
		
		try (ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
				ObjectOutputStream output = new ObjectOutputStream(byteOutput);) {
			output.writeObject(this);
			stream = byteOutput.toByteArray();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		return stream;
	}
	
	
	
	
	
	
	
}
