
public class Solver {

	public Solver(int newRows, int newCols) {
		initGame(newRows, newCols);
	}
	
	// block_num keep track of which block we are dealing w/
	private static int block_num = 0;
	
	private int board [][];
	
	private int block [][];
	
	private static final int CELL_EMPTY = 0;
	private static final int CELL_OCCUPIED = 0;
	
	 public int getRows() {
			return board.length;
	 }
	
	 public int getCols() {
			return board[0].length;
	 }
	 
    private void resetBoard() {
        	int row, col, i, j;
        	row = getRows();
        	col = getCols();
        	for (i=0; i<row; i++)
        		for (j=0; j<col; j++){
        			board[i][j] = CELL_EMPTY;
        			block[i][j] = 0;
        		}
        	block_num++;
    }

    private void initGame(int newRows, int newCols) {
    	
    	//allocate space for mines and tiles array
    	if ((newRows >= 1) && (newCols >= 1)) {
    	    board = new int[newRows][newCols];
    	    block = new int[newRows][newCols];
    	    
    	    //init tiles array
    	    resetBoard();
 
    	    //update clues
    	    //moves();
    	}
    }
    
    public int adding_block(int length, int width, int row, int col){
    	int i, j;
    	// check if block is place within the board
    	if (row+length > board.length)
    		return 1;
    	else if (col+width > board[0].length)
    		return 1;
    	for (i=row; i<row+length; i++){
    		for (j=col; j<col+width; j++){
    			if (board[i][j] != 0)
    				return 1;
    			board[i][j] = CELL_OCCUPIED;
    			block[i][j] = block_num; // set board position to be occupied;
    		}
    	}
    	block_num++;
    	return 0;
    }
	
    public void displayBoard(){
    	int i,j;
    	for (i=0; i<getRows(); i++){
    		for (j=0; j<getCols(); j++){
    			if (block[i][j]>99)
    				System.out.print(" "+block[i][j]);
    			else if (block[i][j]>9)
    				System.out.print("  "+block[i][j]);
    			else
    				System.out.print("   "+block[i][j]);
    		}
    		System.out.println();
    	}
    }
    
    	

}
