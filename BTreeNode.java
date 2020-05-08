import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.NoSuchElementException;

/**
 * CS321: Bioinformatics Group Project
 * 
 * BTreeNode class defining nodes for holding
 * TreeObjects in a BTree
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 *
 */
public class BTreeNode {
	// ============================================================
	// Instance Variables
	// ============================================================
	
	private int degreeT;			// Minimum degree of BTree
	private int orderM;				// Maximum order of BTree
	private int maxKeys;			// Max number of keys per node
	private TreeObject[] keys;		// Array of keys
	private BTreeNode[] children;	// Array of children
	private BTreeNode parent;		// Parent node in BTree
	
	// ============================================================
	// Constructors & getters/setters
	// ============================================================
	
	/**
	 * Default constructor
	 * @param t - the minimum degree of the BTree
	 */
	public BTreeNode(int t) {
		degreeT = t;
		orderM = degreeT * 2;
		maxKeys = orderM - 1;
		keys = new TreeObject[maxKeys + 1];
		children = new BTreeNode[orderM + 1];
		parent = null;
	}
	
	/**
	 * Returns the parent of this node
	 * @return - a reference to this node's parent variable
	 */
	public BTreeNode getParent() {
		return parent;
	}
	
	/**
	 * Sets the value of this node's parent variable
	 * @param parent - the new parent of this node
	 */
	public void setParent(BTreeNode parent) {
		this.parent = parent;
	}
	
	/**
	 * Returns specified key at given index
	 * @param index - the index at which to retrieve the key
	 * @return - a reference to the specified key
	 */
	public TreeObject getKey(int index) {
		if (index < 0 || index > maxKeys) {
			throw new IndexOutOfBoundsException();
		}
		return keys[index];
	}
	
	/**
	 * Sets the key at given index to the specified TreeObject
	 * @param object - the TreeObject being set
	 * @param index - the index at which to set the TreeObject
	 */
	public void setKey(TreeObject object, int index) {
		if (index < 0 || index > maxKeys) {
			throw new IndexOutOfBoundsException();
		}
		keys[index] = object;
	}
	
	/**
	 * Returns a specified child node given an index
	 * @param index - the index at which to retrieve the child node
	 * @return - a reference to a specified child node
	 */
	public BTreeNode getChild(int index) {
		if (index < 0 || index >= orderM) {
			throw new IndexOutOfBoundsException();
		}
		return children[index];
	}
	
	/**
	 * Sets the child link at index to the specified child
	 * @param child - the new child to be linked
	 * @param index - the index at which to link the child
	 */
	public void setChild(BTreeNode child, int index) {
		if (index < 0 || index > orderM) {
			throw new IndexOutOfBoundsException();
		}
		children[index] = child;
	}
	
	/**
	 * Returns true if this node is a leaf, false otherwise
	 * @return - the value of boolean leaf
	 */
	public boolean isLeaf() {
		return (this.lengthChildren() == 0);
	}
	
	/**
	 * Returns the length of the keys array
	 * @return - the number of non-null elements in keys
	 */
	public int lengthKeys() {
		int i = 0;
		while (i <= maxKeys && keys[i] != null) {
			i++;
		}
		return i;
	}
	
	/**
	 * Returns the length of the children array
	 * @return - the number of non-null elements in children
	 */
	public int lengthChildren() {
		int i = 0;
		while (i <= orderM && children[i] != null) {
			i++;
		}
		return i;
	}
	
	// ============================================================
	// Class Methods
	// ============================================================
	
	/**
	 * Searches nodes and inserts TreeObject at proper location in
	 * BTree
	 * @param object - the TreeObject to be inserted
	 * @return - the root of the BTree after the addition
	 */
	public BTreeNode add(TreeObject object) {
		BTreeNode current = this;
		boolean isLeaf = this.isLeaf();
		
		while (!isLeaf) {
			int counter = 0;
			while(counter != current.lengthKeys() && object.getData() > current.getKey(counter).getData()) {
				counter++;
			}
			
			if(current.getKey(counter) != null && object.getData() == current.getKey(counter).getData()) {
				current.getKey(counter).incrementFrequency();
				return this;
			}
			else {
				current = current.getChild(counter);
				isLeaf = current.isLeaf();
			}
		}
		
		if (current.lengthKeys() == 0) {
			current.setKey(object, 0);
		}
		else {
			int counter = 0;
			while(counter != current.lengthKeys() && object.getData() > current.getKey(counter).getData()) {
				counter++;
			}
			
			if(current.getKey(counter) != null && object.getData() == current.getKey(counter).getData()) {
				current.getKey(counter).incrementFrequency();
				return this;
			}
			else {
				current.shiftKeys(counter);
				current.setKey(object, counter);
			}
		}
		
		if (current.lengthKeys() > maxKeys) {
			current.split();
			
			if (parent == null) {
				return this;
			}
			else {
				return parent;
			}
		}
		
		return this;
	}
	
	/**
	 * Splits the current node into two new nodes by promoting
	 * the median element to the parent node and splitting the
	 * remaining keys among the two children
	 */
	private void split() {
		BTreeNode newChild = new BTreeNode(degreeT);
		TreeObject medianKey = keys[degreeT-1];
		
		if (parent == null) {
			BTreeNode newParent = new BTreeNode(degreeT);
			this.setParent(newParent);
			
			parent.setKey(medianKey, 0);
			parent.setChild(this, 0);
			parent.setChild(newChild, 1);
		}
		else {
			int indexFromParent = parent.getChildIndex(this);
			if (indexFromParent == -1) {
				throw new NoSuchElementException();
			}
			
			parent.shiftKeys(indexFromParent);
			parent.shiftChildren(indexFromParent+1);
			parent.setKey(medianKey, indexFromParent);
			parent.setChild(newChild, indexFromParent+1);
		}
		
		newChild.setParent(parent);
		int newChildIndex = 0;
		for (int i = degreeT; i <= maxKeys; i++) {
			newChild.setKey(keys[i], newChildIndex);
			newChildIndex++;
		}
		newChildIndex = 0;
		for (int i = degreeT; i <= orderM; i++) {
			newChild.setChild(children[i], newChildIndex);
			if (children[i] != null) {
				newChild.getChild(newChildIndex).setParent(newChild);
			}
			newChildIndex++;
		}
		
		for (int i = degreeT-1; i <= maxKeys; i++) {
			keys[i] = null;
		}
		for (int i = degreeT; i <= orderM; i++) {
			children[i] = null;
		}
		
		if (parent.lengthKeys() > maxKeys) {
			parent.split();
		}
	}
	
	/**
	 * Shifts keys to the right to make room for a new key
	 * @param index - the index the new key is to be inserted into
	 */
	private void shiftKeys(int index) {
		if (index < 0 || index > maxKeys) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = this.lengthKeys(); i > index; i--) {
			keys[i] = keys[i-1];
		}
	}
	
	/**
	 * Shifts children to the right to make room for a new child
	 * @param index - the index the new child is to be inserted into
	 */
	private void shiftChildren(int index ) {
		if (index < 0 || index > orderM) {
			throw new IndexOutOfBoundsException();
		}
		
		for (int i = this.lengthChildren(); i > index; i--) {
			children[i] = children[i-1];
		}
	}
	
	/**
	 * Returns the index of a specified child node
	 * @param child - the child node who's index is being found
	 * @return - the index of the specified child node
	 */
	private int getChildIndex(BTreeNode child) {
		int index = 0;
		boolean found = false;
		while (!found && index != this.lengthChildren()) {
			if (children[index] == child) {
				found = true;
			}
			else {
				index++;
			}
		}
		
		if (!found) {
			return -1;
		}
		else {
			return index;
		}
	}
	
	/**
	 * Traverses the BTree in order. Dumps information into file with given name.
	 * @param the name of the file to be created
	 */
	public void traverse(String fileName) {
		File dumpFile = new File(fileName);
			try {
				if(dumpFile.createNewFile()) {
					System.out.println("File created: " + dumpFile.getName());
				}
				FileWriter dumpFileWriter = null;
				dumpFileWriter = new FileWriter(fileName);
				int i = 0;
				for(i = 0; i < this.lengthKeys(); i++) {
					if(!this.isLeaf()) {
						dumpFileWriter.write(children[i].toString());
						children[i].traverse(fileName);
					}
				}
				if(!isLeaf()) {
					dumpFileWriter.write(children[i].toString());
					children[i].traverse(fileName);
				}
				dumpFileWriter.close();
			} catch (IOException e) {
				e.printStackTrace(); //oops
			}
	}
}
