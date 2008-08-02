
public class Solver {

	public Solver(int newRows, int newCols) {
		initGame(newRows, newCols);
	}
	
	
	private int board [][];
	
	private int block [][];
	
	private static final int BOARD_EMPTY = 0;
	
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
        		for (j=0; j<col; j++)
        			board[i][j] = BOARD_EMPTY;
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
    			board[i][j] = 1; // set board position to be occupied;
    		}
    	}
    	return 0;
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
    	

}
