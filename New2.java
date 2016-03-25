



 import java.io.IOException;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.Queue;



public class New2<theID extends Comparable<theID>, Integer> {
	
		private static final boolean RED = true;
		private static final boolean BLACK = false;
		public static  String[] newArray;

		private  Node root;
		
		private class Node{
			private theID theID;
			private int count;
			private Node left, right;
			private boolean color;
			private int N;
			
			
			public Node(theID theID, int count, boolean color, int N){
				this.theID = theID;
				this.count = count;
				this.color = color;
				this.N =N;
				
			}
		}
		
		public New2(){
		}
		
			private boolean isRed(Node x){                      //check if the color of the node is red or not
				if (x == null) return false;
				return x.color == RED;
				
			} 
			private int size(Node x){                          //returns the size of the node i.e. size of subtrees.
				if(x == null)return 0;
				return x.N;
			}
		

			public int size(){
				return size(root);
			}
			public boolean isEmpty(){
				return root == null;
			}
			
			// function to print the count of desired ID
			public int count(theID theID) {
			        if (theID == null) throw new NullPointerException("argument to get() is null");
			        return count(root, theID);
			}
			private int count(Node x, theID theID) {
			        while (x != null) {
			            int cp = theID.compareTo(x.theID);
			            if      (cp < 0) x = x.left;
			            else if (cp > 0) x = x.right;
			            else return x.count;
			          
			        }
			      
			        return 0;
			}
			 
			public boolean contains(theID theID) {
			        return count(theID) != 0;
			}
			
			// insert a node in the tree
			public void put(theID theID, int count) {
		        if (theID == null) throw new NullPointerException("the ID is null");
		        if (count == 0) {
		            delete(theID);
		            return;
		        }
		        root = put(root, theID, count);
		        root.color = BLACK;
		       
		        
		        
		    }
			
			
			//insertion of a new node in subtree  of root
			 private Node put(Node h, theID theID, int count) { 
			        if (h == null) return new Node(theID, count, RED, 1);

			        int cp = theID.compareTo(h.theID);
			        if      (cp < 0) h.left  = put(h.left,  theID, count); 
			        else if (cp > 0) h.right = put(h.right, theID, count); 
			        else              h.count   = count;

			        // fix-up any right-leaning links
			        if (isRed(h.right) && !isRed(h.left))      h = rotateLeft(h);
			        if (isRed(h.left)  &&  isRed(h.left.left)) h = rotateRight(h);
			        if (isRed(h.left)  &&  isRed(h.right))     changeColors(h);
			        h.N = size(h.left) + size(h.right) + 1;

			        return h;
			    }
			 
			 // deleting a node from the tree
			 public void delete(theID theID) { 
			        if (theID == null) throw new NullPointerException("argument to delete() is null");
			        if (!contains(theID)) return;

			        // if both children of root are black, set root to red
			        if (!isRed(root.left) && !isRed(root.right))
			            root.color = RED;

			        root = delete(root, theID);
			        if (!isEmpty()) root.color = BLACK;
			      
			    }
			 
			 private Node delete(Node h, theID theID) { 
			       

			        if (theID.compareTo(h.theID) < 0)  {
			            if (!isRed(h.left) && !isRed(h.left.left))
			                h = moveRedLeft(h);
			            h.left = delete(h.left, theID);
			        }
			        else {
			            if (isRed(h.left))
			                h = rotateRight(h);
			            if (theID.compareTo(h.theID) == 0 && (h.right == null))
			                return null;
			            if (!isRed(h.right) && !isRed(h.right.left))
			                h = moveRedRight(h);
			            if (theID.compareTo(h.theID) == 0) {
			                Node x = min(h.right);
			                h.theID = x.theID;
			                h.count = x.count;
			                h.right = deleteMin(h.right);
			            }
			            else h.right = delete(h.right, theID);
			        }
			        return balance(h);
			    }
			 
			 
			
			 
			
			    
			    //previous function
			    
			    public String previous(theID theID) {                                          //gives the largest id smaller to given id
			        if (theID == null) throw new NullPointerException("no id exists");
			        if (isEmpty()) throw new NoSuchElementException("empty");
			        Node x = previous(root, theID);
			        if (x == null) return "0 0";
			        else  return x.theID+" "+x.count+"";                                       //returns both the id and count
			    }    

			    // the largest theID in the subtree rooted at x less than or equal to the given theID
			    private Node previous(Node x, theID theID) {
			        if (x == null) return null;
			        int cmp = theID.compareTo(x.theID);
			       // if (cmp == 0) return x;
			        if (cmp <= 0)  return previous(x.left, theID);
			        Node t = previous(x.right, theID);
			        if (t != null) return t; 
			        else return x;
			    }
			    
			    // next function
			    
			    public String next(theID theID) {
			        if (theID == null) throw new NullPointerException("null value");
			        if (isEmpty()) throw new NoSuchElementException("empty");
			        Node x = next(root, theID);
			        
			        if (x == null) return "0 0" ;
                     
			        else return x.theID+" "+x.count+"";
			        
  
			    }

			    // the smallest theID in the subtree rooted at x greater than to the given theID
			    private Node next(Node x, theID theID) {  
			        if (x == null) return null;
			        int cmp = theID.compareTo(x.theID);
			        //if (cmp == 0) return x;
			        if (cmp >= 0)  return next(x.right, theID);
			        Node t = next(x.left, theID);
			        if (t != null) return t; 
			        else           return x;
			    }
			    
			    
                            
               public void increase(theID id, int m){                       // performs increase operation on the count of given id
                     if(count(id)==0){
                        put(id,m);                                         // if id not present insert it using put() function
            
                  }
                      else{
                       increase(root,id,m);                                // else call the increase function recursively
                     }
               }
               
			   private void increase(Node x, theID theID, int m) {             
				
				
			        while (x != null) {
			            int cp = theID.compareTo(x.theID);                  // compare the id using the compareTo method of comparable
			            if(cp < 0) x = x.left;                              // less then move to left subtree
			            else if (cp > 0) x = x.right;                       // greater then move to right subtree
			            else {
                          break;
			            	
			            	
			            	
			            }
			        }
                                x.count = x.count + m;                      //increment count with given value
                                System.out.println(x.count);
			}
			   
			   
                        
                 public void reduce(theID id, int m){                        // reduces the value of the given id by given amount
                      if(count(id)!=0){
                                reduce(root,id,m);                           // if value is not 0 then call the reduce function
                       }
                      else{
                                System.out.println();   
                       }
                 }
    
                 public int reduce(Node x, theID id, int m){
                	 int p=0;
                     String t;
                     while (x != null) {                                        //make comparisons and recursively traverse left and right subtrees 
                         int cmp = id.compareTo(x.theID);
                         if      (cmp < 0) x = x.left;
                         else if (cmp > 0) x = x.right;
                         else   {
                             break;    
                         }
                     }
                     x.count=x.count-m;                                         //decrement count
                     if(x.count<1){                                            //if count falls less than 1 delete that id from the tree
                         delete(id);
                         
                     }
                     else{
                         
                         return x.count;
                    
                    
                }
        
                     return 0;
                  }
	   
			    

			    
			    public Integer inRange(theID lo, theID hi) {                        //gives total count of the nodes between the given range
			        if (lo == null) throw new NullPointerException("null");
			        if (hi == null) throw new NullPointerException("null");

			        Queue<Integer> queue = new LinkedList<Integer>();               // queue to store the sum of the count
			        // if (isEmpty() || lo.compareTo(hi) > 0) return queue;
			        inRange(root, queue, lo, hi);
			        if(queue.isEmpty()){                                           // if given range doesn't exists return 0
			            
			            String t ="0";
			            return (Integer)t;
			        }
			        else{
			        return (Integer)queue.poll();                                 // sum is stored at queue front
			        }
			    } 

			    
			    private void inRange(Node x, Queue queue, theID lo, theID hi) { 
			    	int t=0;
			        if (x == null) return; 
			        int cmplo = lo.compareTo(x.theID); 
			        int cmphi = hi.compareTo(x.theID); 
			        if (cmplo < 0) inRange(x.left, queue, lo, hi); 
			        if (cmplo <= 0 && cmphi >= 0)
			        	{
			        	if(queue.peek()!=null){
			        	t= (int)queue.poll();}                                    // if front of queue is not null keep adding the new count to it
			        	
			        	queue.add(t+x.count); 
			        	
			        	}
			        if (cmphi > 0) inRange(x.right, queue, lo, hi); 
			    } 
			 
			    
			    public int size(theID lo, theID hi) {
			        if (lo == null) throw new NullPointerException("null");
			        if (hi == null) throw new NullPointerException("null");
			        int totalCount = 0;
			        
			        while(lo.compareTo(hi)<0)
			        {
			        	size(root,lo,hi);
			        	
			        totalCount = totalCount+ count(root.theID);
			        }
			       
			        
			        return 0;
			    }
			    
			    public void deleteMin() {                                  // deleting the node having minimum id 
			        if (isEmpty()) throw new NoSuchElementException("BST underflow");

			        
			        if (!isRed(root.left) && !isRed(root.right))        // if both children of root are black, set root to red
			            root.color = RED;

			        root = deleteMin(root);
			        if (!isEmpty()) root.color = BLACK;
			       
			    }

			    
			    private Node deleteMin(Node h) { 
			        if (h.left == null)
			            return null;

			        if (!isRed(h.left) && !isRed(h.left.left))
			            h = moveRedLeft(h);

			        h.left = deleteMin(h.left);
			        return balance(h);
			    }


			  
			
			    
			  // rotation functions
			    
			    private Node rotateRight(Node h) {
			       
			        Node x = h.left;
			        h.left = x.right;
			        x.right = h;
			        x.color = x.right.color;
			        x.right.color = RED;
			        x.N = h.N;
			        h.N = size(h.left) + size(h.right) + 1;
			        return x;
			    }

			    
			    private Node rotateLeft(Node h) {
			        
			        Node x = h.right;
			        h.right = x.left;
			        x.left = h;
			        x.color = x.left.color;
			        x.left.color = RED;
			        x.N = h.N;
			        h.N = size(h.left) + size(h.right) + 1;
			        return x;
			    }
			    
			    private void changeColors(Node n) {
			        
			        n.color = !n.color;                                   // the node must have opposite colors of its two children
			        n.left.color = !n.left.color;
			        n.right.color = !n.right.color;
			    }
			    
			    
			    private Node moveRedLeft(Node n) {
			       

			        changeColors(n);
			        if (isRed(n.right.left)) { 
			            n.right = rotateRight(n.right);
			            n = rotateLeft(n);
			            changeColors(n);
			        }
			        return n;
			    }
			    
			    
			    private Node moveRedRight(Node n) {
			       
			        changeColors(n);
			        if (isRed(n.left.left)) { 
			            n = rotateRight(n);
			            changeColors(n);
			        }
			        return n;
			    }
			    
			    
			    private Node balance(Node n) {
			     
			        if (isRed(n.right)) n = rotateLeft(n);
			        if (isRed(n.left) && isRed(n.left.left)) n = rotateRight(n);
			        if (isRed(n.left) && isRed(n.right))     changeColors(n);

			        n.N = size(n.left) + size(n.right) + 1;
			        return n;
			    }

			    
			    //return height of the BST 
			    
			    public int height() {
			        return height(root);
			    }
			    private int height(Node x) {
			        if (x == null) return -1;
			        return 1 + Math.max(height(x.left), height(x.right));
			    }
			    
			        private int size(Node x, theID lo, theID hi)
			        {
			        theID i = lo;
			        int totalCount = count(hi)+count(lo);
			        int cmp = lo.compareTo(x.theID); 
			        if      (cmp < 0) return size(x.right,lo,x.theID); 
			        else if (cmp > 0) return size(x.left,x.theID,hi); 
			        else              return count(x.theID); 
			             
		        }
          
			  

			    // the smallest theID in subtree rooted at x; null if no such theID
			    private Node min(Node x) { 
			        // assert x != null;
			        if (x.left == null) return x; 
			        else                return min(x.left); 
			    }

			    
			    
	}

