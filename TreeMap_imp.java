package assignment2_f20;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;

//--------------------------------------------------------------

public class TreeMap_imp implements TreeMap { 
  TMCell root ;
  TMCell current;
  int size;
  
  
  boolean doesContain;
  Value oldV = null;
  int count = 0;
  boolean removed;
  TMCell minCell = null;
  TMCell maxCell = null;
  boolean isLeaf = false;
  String[] keys;
  ArrayList<String> keyList;



  TreeMap_imp () { 
    root = null; 
    current = root;
    size = 0;
    // for added fields you can add appropriate initialization code here
  }
  @Override
  public TMCell getRoot() { return this.root; }
  
  // ---------------------------------------------------------

  public int height() { 
	  /*"height" is a complete implementation of the public interface method height*/
    return height_r(this.getRoot());
  }
  
  // ---------------------------------------------------------
  
  private int height_r(TMCell c) { 
    if (c==null) return -1;
    int lht = height_r(c.getLeft());
    int rht = height_r(c.getRight());
    return Math.max(lht,rht) + 1;
  }
  
  // --------------------------------------------------------- 
  
  public String minKey() { 
	  
	  if(this.size() == 0 || root == null) {
	  return null; }
	 

	  if(root != null && current.getLeft() != null) {
		 current = current.getLeft();
		 minKey();
	  }
	  minCell = current;
	  return current.getKey();
	  } // end minKey
 
  // ---------------------------------------------------------
  
  public String maxKey() {
	  
	  if(this.size() == 0 || root == null) {
		  return null; }
		 

		  if(root != null && current.getRight() != null) {
			 current = current.getRight();
			 minKey();
		  }
		  maxCell = current;
		  return current.getKey();
  } // end maxKey
  
  
  // ---------------------------------------------------------
  
  // needs to be finished
  public String[] getKeys() {	  
	 
	  
	  return inOrderTraversal(root);
  }
	  
	  public String [] inOrderTraversal (TMCell t) {
		  if( t != null) {
			  keyList.add(t.getKey());
			  inOrderTraversal(t.getLeft());
			  inOrderTraversal(t.getRight());
		  }
		  return addToArray(keyList);
	  }
	  
	  public String [] addToArray(ArrayList <String> l) {
		  for(int i = 0 ; i < l.size(); i++) {
			  keys[i] = l.get(i);
		  }
		  return keys;
	  }
	  
	
	  
	  
	  
	  
	  

  // ----------------------------------------------------------------

   /** Map methods: put, get, remove, hasKey, size **/
  
  public Value put ( String k, Value v ) {
	  TMCell c = new TMCell_imp(k, v);
	  
	  /**  IF NOT IN TREE ALREADY**/
	  if(!hasKey(k)) {
		  
		  // case 1: tree is null, assign as root
		  if(getRoot() == null) {
			  this.root = c;
			  size++;
			  current = root;
			  return null;
		  } 
		  // case 2: size = 1, see if we can replace root
		  if(size() == 1) {
			  if(root.getKey().compareTo(k) < 0) {
				  root.setRight(c);
				  size++;
				  current = root;
				  return null;
			  } else if(root.getKey().compareTo(k) > 0) {
				  root.setLeft(c);
				  size++;
				  current = root;
				  return null;
			  }
		  }
		  // case 3: left branch is empty and k is less than root
		  if(root.getLeft() == null && k.compareTo(root.getKey() ) < 0) {
			  root.setLeft(c);
			  size++;
			  current = root;
			  return null;
		  }
		  
		  // case 4: right branch is empty and k is greater than root
		  if(root.getRight() == null && root.getKey().compareTo(k) < 0) {
			  root.setRight(c);
			  current = root;
			  size++;
			  return null;
		  }
		  
		  
		  // when comparison is 0 and left is not null, call on recursive 
		  
		  
		  
		  else {
			  
			
			  if(root.getKey().compareTo(k) > 0 && root.getLeft()!= null)
			  {
				
				put(root.getLeft().getKey(), root.getLeft().getValue());
				size++;
				return null;
			  }
			  if(root.getKey().compareTo(k) <  0  && root.getRight() != null)
			  {
				
				  put(root.getRight().getKey(), root.getRight().getValue());
				  size++;
				  return null;
			  }
			  
		  }
		  
		  
		  
		 
		  }
	  // if key is in structure
	  
	  if(hasKey(k)) {
		  if(root.getKey().compareTo(k) ==0) {
			  oldV = root.getValue();
			  root.setValue(v);
			  return oldV;
		  }
		  put(k, v);
		  

	  }
	  
	  return oldV;
	  } // end if not in tree already
	  /** IF IN THE TREE ALREADY **/
	  
	  
	 
  


  // needs to be finished


public void remove(String k) {
	
	if(root == null || k == null) {
		return;
	}
	if(root.getKey().compareTo(k) == 0) {
		if(root.getLeft() == null && root.getRight() == null) {
			root = null;
		} else if(root.getLeft() == null) {
			root = root.getRight();
		} else if(root.getRight() == null) {
			root = root.getLeft();
		} else {
			root = root.getRight();
		}
		size--;
	
	}


}

  
  public boolean hasKey(String k) {
	  /** 
	   * if key is in structure, return true
	   * if not, return false
	   * for both cases, no change to structure
	   */
	  
	  doesContain = false;
	  if(this.getRoot() == null) {
		  return doesContain;
	  } else if(this.getRoot().getKey().compareTo(k) == 0) {
		  doesContain = true;
		  return doesContain;
	  }
	  
	  
	  if(current.getKey().compareTo(k) < 0 && current.getRight() != null) {
		  current = current.getRight();
		  hasKey(k);
	  } else if(current.getKey().compareTo(k) < 0 && current.getRight() == null) {
		  current = root;
		  return doesContain;
	  } else if(current.getKey().compareTo(k) > 0 && current.getRight() != null) {
		  current = current.getLeft();
		  hasKey(k);
	  } else if(current.getKey().compareTo(k) > 0 && current.getLeft() == null) {
		  current = root;
		  return doesContain;
	  } else if(current.getKey().compareTo(k) == 0) {
		  doesContain = true;
		  return doesContain;
	  }
	  
	  
	  
	  return doesContain;
  } // end hasKey
  
  int sizeLST = 0;
  int sizeRST = 0;
  
  
public Value get (String k)    {
	  
	  if(!this.hasKey(k)) { return null; }
	  
	  if(this.hasKey(k)) {
		  if(current.getKey().compareTo(k) < 0 && current.getRight() != null) {
			  current = current.getRight();
			  hasKey(k);
		  } else if(current.getKey().compareTo(k) < 0 && current.getRight() == null) {
			  current = root;
			  return current.getValue();
		  } else if(current.getKey().compareTo(k) > 0 && current.getRight() != null) {
			  current = current.getLeft();
			  hasKey(k);	
		  } else if(current.getKey().compareTo(k) > 0 && current.getLeft() == null) {
			  current = root;
			  return current.getValue();
		  } else if(current.getKey().compareTo(k) == 0) { // if keys are equal
			  return current.getValue();
		  }
		  
	  }
	  return null;
  } 

public int size() {

	  return sizeLST + sizeRST + 1;
}
  
  

  
  
  
  
} // end main