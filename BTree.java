/**
 * CS321: Bioinformatics Group Project
 * 
 * BTree class, a simple implementation of the BTree data
 * structure.
 * 
 * @author Alex Guy, Sam Jackson, Terran Dykes
 */
public class BTree {
	// ============================================================
	// Instance variables
	// ============================================================
	
	private int degreeT;	// The minimum degree of the BTree
	private int orderM;		// The maximum order of the BTree
	private BTreeNode root;	// The root node of the BTree	
	
	// ============================================================
	// Constructor & getters/setters
	// ============================================================
	
	/**
	 * Initializes degreeT and orderM based on provided degree and 
	 * initializes root to an empty BTreeNode
	 * 
	 * @param degree - the minimum desired degree of the BTree
	 */
	public BTree(int degree) {
		degreeT = degree;
		orderM = degreeT * 2;
		root = new BTreeNode(orderM);
	}
	
	// ==================================================
	// Class methods
	// ==================================================
	
	
	
}
