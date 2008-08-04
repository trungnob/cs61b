import java.util.*;


public class Board {

	public Board(int newRows, int newCols) {
		initBoard(newRows, newCols,0);
	}
//	public Board(int newRows, int newCols,int totalBlocks){
//		initBoard(newRows, newCols,totalBlocks);
//	}
	
	// block_num keep track of which block we are dealing w/ . 
	// This is just for displaying purpose. --Trung add 
	private int block_num = 0;
	
	private int board_map [][];
	
	private int block_map [][];
	
	//public HashSet<block> block_set = new HashSet<block> ();
	  private ArrayList<block> blockArray;
	private static final int CELL_EMPTY = 0;
	private static final int CELL_OCCUPIED = 0;
	
	public int getRows() {
		return board_map.length;
	}	
	
	public int getCols() {
		return board_map[0].length;
	}
	
	
	public int getTotalBlock(){
		if (blockArray==null) return -1;
		return blockArray.size();
	}
    private void resetboard_map() {
        	int row, col, i, j;
        	row = getRows();
        	col = getCols();
        	for (i=0; i<row; i++)
        		for (j=0; j<col; j++){
        			board_map[i][j] = CELL_EMPTY;
        			block_map[i][j] = 0;
        		}
        	block_num=1; 
    }

    private void initBoard(int newRows, int newCols,int numberOfBlock) {
    	//
    	if ((newRows >= 1) && (newCols >= 1)) {
    	    board_map = new int[newRows][newCols];
    	    block_map = new int[newRows][newCols];
    	    //blockArray= new block[numberOfBlock];
    	    blockArray=new ArrayList<block>();
    	    resetboard_map();
    	}
    }
    
    public int adding_block(int len, int wid, int row, int col){
    	int i, j;
    	// check if block is place within the board_map
    	if (row+len > board_map.length)
    		return 1;
    	else if (col+wid > board_map[0].length)
    		return 1;
    	for (i=row; i<row+len; i++){
    		for (j=col; j<col+wid; j++){
    			if (board_map[i][j] != 0)
    				return 1;
    			board_map[i][j] = CELL_OCCUPIED;
    			block_map[i][j] = block_num; // set board_map position to be occupied;
    		}
    	}
    	block b0 = new block(len, wid, row, col,block_num);//use  block_num as a identification number.
    	blockArray.add(b0);
    //	block_set.add(b0);
    	block_num++;
    	return 0;
    }
	
    public void displayBoard(){
    	int i,j;
    	for (i=0; i<getRows(); i++){
    		for (j=0; j<getCols(); j++){
    			if (block_map[i][j]>99)
    				System.out.print(" "+block_map[i][j]);
    			else if (block_map[i][j]>9)
    				System.out.print("  "+block_map[i][j]);
    			else
    				System.out.print("   "+block_map[i][j]);
    		}
    		System.out.println();
    	}
    }
    
//    public boolean equals(Board board_to_compare){ // this seems  like just check whether or not the board contain the block within the other board
//    	if ( (board_to_compare.getRows()!=this.getRows())
//    			|| (board_to_compare.getCols()!=this.getCols()) )
//    		return false;
//    	Iterator<block> iter = board_to_compare.block_set.iterator();
//    	while(iter.hasNext()){
//    		block b0 = iter.next();
//    		if (!this.block_set.contains(b0))
//    			return false;
//    	}
//    	return true;
//    }
  public block findBlockByID(int ID){
	  return null;
  }
 public ArrayList<block>  findNumberOfBlockbyShape(block blockToCheck ) { //return all block that have the same shape as the block input; using linear search
	 ArrayList<block> result= new ArrayList<block>();
	 for (Iterator<block> ite=blockArray.iterator();ite.hasNext();){
		 block temp=ite.next();
		 if (temp.isSameShape(blockToCheck)) result.add(temp);
	 }
	return result;
 }
 
 
  public boolean compareToGoal(ArrayList<block> Goal ){
	 
	  
  	Iterator<block> iter = Goal.iterator();
  	while(iter.hasNext()){
  		block b0 = iter.next();
  		if (!this.blockArray.contains(b0))
  			return false;
  	}
  	return true;
  }
  
}
