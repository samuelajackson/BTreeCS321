import java.lang.reflect.Array;

/**
 * CS321: Bioinformatics Group Project
 * 
 * BTreeNode class; nodes for holding TreeObjects
 * in the BTree
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class BTreeNode {
	
	private int maxKeys, t; //Max keys
	private TreeObject[] keys; //Array of keys
	private BTreeNode[] children; //array of children
	private BTreeNode parent;
	private boolean leaf; //is this a leaf?
	
	/**Constructor
	 *
	 * @param k, number of keys, object, object inserted
	 */
	public BTreeNode(int t) {
		this.maxKeys = t*2 - 1;
		this.t = t;
		keys = new TreeObject[maxKeys + 1]; //Supposed to hold m - 1 keys, increased for temporary holding
		children = new BTreeNode[t*2]; //Each node holds m links
		this.leaf = true; //they all start as leaves
		this.parent = null;
	}
	
	public boolean full() {
		return (keys[maxKeys] != null);
	}
	
	public BTreeNode getChild(int index) {
		return children[index];
	}
	
	public void linkChild(int index, BTreeNode node) {
		children[index] = node;
	}
	
	public void addKey(TreeObject addedObject) {
		if(!leaf) {
			int counter = 0;
			while(addedObject.getData() > keys[counter].getData() && counter != keys.length) {
				counter++;
			}
			if(addedObject.getData() == keys[counter].getData()) {
				keys[counter].incrementFrequency();
				return;
			}
			else {
				children[counter].addKey(addedObject); //could cause problems eyeball emoji
			}
		}
		else {
			if (keys.length == 0) {
				keys[0] = addedObject;
			}
			else {
				for(int i = 0; i < maxKeys; i++) {
					if(addedObject.getData() >= keys[i].getData()) {
						if(addedObject.getData() == keys[i].getData()) {
							keys[i].incrementFrequency();
							return;
						}
					}
					else {
						for(int j = keys.length; j > i; j--) {
							keys[j] = keys[j-1];
						}	
						keys[i] = addedObject;//insert
						if(this.full()) {
							if(parent == null) {
								parent = new BTreeNode(t);
							}
							parent.addKey(keys[t]); //recursion B)
							keys[t] = null;
							BTreeNode newChild = new BTreeNode(t);
							for(int k = t + 1; k < keys.length; k++) {
								newChild.addKey(keys[k]);
								keys[k] = null;
							}
						}
					}
				}
			}
		}
	}	
}
