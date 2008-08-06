import java.util.ArrayList;
import java.util.Iterator;

public class Board_tree {
        
        private Board_node myRoot = null;
        
        // A constructor that starts an Board_node family with an Board_node
        // with the given name.
        public Board_tree (Board my_board) {
                myRoot = new Board_node(my_board, null);
        }
        
       public void addChild (Board parent_board, Board child_board) {
        	if (myRoot.myBoard.equals(parent_board))
      		  	myRoot.addChild(child_board);
    	    else{
    	    	addChild_helper (myRoot, parent_board, child_board);
    	    }
        }
        
        public void addChild_helper (Board_node node, Board parent_board, Board child_board) {
        	Iterator<Board_node> iter = node.myChildren.iterator();
        	while(iter.hasNext()){
        		Board_node cur = iter.next();
        		if  (cur.myBoard.equals(parent_board) ){
        			cur.addChild(child_board);
        			return;
        		}
        		else
        			addChild_helper (cur, parent_board, child_board);
        	}
        }
        
        
        /* debuggin printing technics
        public void print ( ) {
                // You supply this code.
         	print_helper(myRoot, 0);
        }
        
        public void print_helper (Board_node node, int level){
        	int i;
        	for (i=0; i<level; i++)
        		System.out.print("    ");
        	System.out.println(node.toString());
    		Iterator<Board_node> iter = node.myChildren.iterator();
        	while(iter.hasNext()){
        		Board_node cur = iter.next();
        		print_helper (cur, level+1);
        	}
        }*/
    
        public int size (){
        	return size_helper(myRoot)+1;
        }
        
        public int size_helper (Board_node node){
        	int i=0;
        	Iterator<Board_node> iter = node.myChildren.iterator();
        	while(iter.hasNext()){
        		Board_node cur = iter.next();
        		i++;
        		i +=size_helper (cur);
        	}
        	return i;
        }
        
        public int height_helper(){
        	return height(myRoot);
        }
        
        private static int height (Board_node x) {
            if (x.myChildren.isEmpty ( )) {
                return 1;
            } else {
                int bestSoFar = 1;
                Iterator<Board_node> iter = x.myChildren.iterator ( );
                while (iter.hasNext ( )) {
                	Board_node child = iter.next ( );
                    bestSoFar = Math.max (1+height(child), bestSoFar);
                }
                return bestSoFar;
            }
        }
        
        private static class Board_node {

        	    public Board myBoard;
                public Board_node myParent;     
                public ArrayList<Board_node> myChildren;
                
                public Board_node (Board current, Board_node parent) {
                	    myBoard = current;
                        myParent = parent;
                        myChildren = new ArrayList<Board_node> ( );
                }
                
                public Board_node parent ( ) {
                        return myParent;
                }
                
                public void addChild (Board child_Board) {
                	Board_node child = new Board_node (child_Board, this);
                        myChildren.add (child);
                }
        }
}       
