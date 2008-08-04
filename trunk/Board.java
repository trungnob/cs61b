import java.util.*;


public class Board {

	public Board(int newRows, int newCols) {
		initGame(newRows, newCols);
	}
	
	// block_num keep track of which block we are dealing w/
	private int block_num = 0;
	
	private int board_map [][];
	
	private int block_map [][];
	
	public HashSet<block> block_set = new HashSet<block> ();
	
	private static final int CELL_EMPTY = 0;
	private static final int CELL_OCCUPIED = 1;
	
	public int getRows() {
		return board_map.length;
	}	
	
	public int getCols() {
		return board_map[0].length;
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
        	block_num++;
    }

    private void initGame(int newRows, int newCols) {
    	//allocate space for mines and tiles array
    	if ((newRows >= 1) && (newCols >= 1)) {
    	    board_map = new int[newRows][newCols];
    	    block_map = new int[newRows][newCols];
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
    	block b0 = new block(len, wid, row, col);
    	block_set.add(b0);
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
    
    public boolean equal(Board board_to_compare){
    	if ( (board_to_compare.getRows()!=this.getRows())
    			|| (board_to_compare.getCols()!=this.getCols()) )
    		return false;
    	Iterator<block> iter = board_to_compare.block_set.iterator();
    	while(iter.hasNext()){
    		block b0 = iter.next();
    		if (!this.block_set.contains(b0))
    			return false;
    	}
    	return true;
    }
 
}
