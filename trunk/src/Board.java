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
	public ArrayList<Coordinate> emptyCells;
	private int block_map [][];
	public Board mysibbling = null;
	public Board Leftmostchild = null;
	public ArrayList<block> blockArray = null;
	public ArrayList<block> movableBlockLeft=null;
	public ArrayList<block> movableBlockRight=null;
	public ArrayList<block> movableBlockUp=null;
	public ArrayList<block> movableBlockDown=null;
	private static final int CELL_EMPTY = 0;
	private static final int CELL_OCCUPIED = 1;
	
	public int getRows() {
		return board_map.length;
	}	
	
	public int getCols() {
		return board_map[0].length;
	}
	

	public void addblocks(block myblocks1){
		
		this.adding_block(myblocks1.get_block_len(), myblocks1.get_block_wid(),myblocks1.get_block_row() ,myblocks1.get_block_col());
		
	}

	
	public ArrayList <block> getblock(){
		return blockArray;
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
        			emptyCells.add(new Coordinate(i,j));
        			movableBlockLeft=new ArrayList<block>();
        			movableBlockRight=new ArrayList<block>();
        			movableBlockUp=new ArrayList<block>();
        			movableBlockDown=new ArrayList<block>();
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
    	    emptyCells = new ArrayList<Coordinate>();
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
    			if (board_map[i][j] != CELL_EMPTY)
    				return 1;
    			board_map[i][j] = CELL_OCCUPIED;
    			int index=emptyCells.indexOf(new Coordinate(i,j));
    			if (index!=-1)
    			emptyCells.remove(index);
    			block_map[i][j] = block_num; // set board_map position to be occupied;
    		}
    	}
    	block b0 = new block(len, wid, row, col,block_num);//use  block_num as a identification number.
    	blockArray.add(b0);
    	block_num++;
    	this.constructListofMovableBlocks();
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
    public void displayBoard_db(){
    	int i,j;
    	for (i=0; i<getRows(); i++){
    		for (j=0; j<getCols(); j++){
    			if (block_map[i][j]>99)
    				System.out.print(" "+board_map[i][j]);
    			else if (block_map[i][j]>9)
    				System.out.print("  "+board_map[i][j]);
    			else
    				System.out.print("   "+board_map[i][j]);
    		}
    		System.out.println();
    	}
    } 
    

  public block findBlockByID(int ID){
	  block result=null;
	  for (Iterator<block> ite=blockArray.iterator();ite.hasNext();){
			 block temp=ite.next();
			 if (temp.getID()==ID) return temp;
		 }
	  return result;
  }
 public ArrayList<block>  findNumberOfBlockbyShape(block blockToCheck ) { //return all block that have the same shape as the block input; using linear search
	 ArrayList<block> result= new ArrayList<block>();
	 for (Iterator<block> ite=blockArray.iterator();ite.hasNext();){
		 block temp=ite.next();
		 if (temp.isSameShape(blockToCheck)) result.add(temp);
	 }
	return result;
 }
public boolean EmptyCell(Coordinate c){
	if ((c.getRow() > board_map.length)|| (c.getCol()>board_map[0].length))
	return false;
	else 
	{int i=c.getRow(),
		 j=c.getCol();
	 return board_map[i][j]==CELL_EMPTY;
		
	}
		
}
public void constructListofMovableBlocks(){
//	movableBlockUp.removeAll(movableBlockUp);
//	movableBlockDown.removeAll(movableBlockDown);
//	movableBlockRight.removeAll(movableBlockRight);
//	movableBlockLeft.removeAll(movableBlockLeft);
	movableBlockUp=new ArrayList<block>();
	movableBlockDown=new ArrayList<block>();
	movableBlockLeft=new ArrayList<block>();
	movableBlockRight=new ArrayList<block>();
  for (Iterator<block> iter = blockArray.iterator();iter.hasNext();){
	 block b = iter.next();
	 if ((!this.movableBlockUp.contains(b))&&(this.EmptyNeighborUp(b))) movableBlockUp.add(b); 
	 if ((!this.movableBlockDown.contains(b))&&(this.EmptyNeighborDown(b))) movableBlockDown.add(b);
	 if ((!this.movableBlockLeft.contains(b))&&(this.EmptyNeighborLeft(b))) movableBlockLeft.add(b);
	 if ((!this.movableBlockRight.contains(b))&&(this.EmptyNeighborRight(b))) movableBlockRight.add(b);
  }
}
public boolean EmptyBlock(Coordinate c, BlockShape bs){ // check a block is empty or not start at its  up left conner
	int row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=board_map.length,
	 bm_c=board_map[0].length;
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len)>bm_r) || (col+wid>bm_c) || (row<0) || (col<0))
	return false;
	else 
	{
	for (int i = row; i<row+len;i++ )
		for (int j= col;j<col+wid;j++ )
			if (board_map[i][j]==CELL_OCCUPIED) return false;
	 return true;
	}
}

public boolean EmptyNeighborLeft(block b){ 
BlockShape bs=new BlockShape(b.get_block_len(),1);
Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()-1);
return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborRight(block b){ 
	BlockShape bs=new BlockShape(b.get_block_len(),1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()+b.get_block_wid());
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborUp(block b){ 
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborDown(block b){ 
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()+b.get_block_len(),b.get_block_col());
	return EmptyBlock(c,bs);	
}
public void setEmpty(Coordinate c, BlockShape bs){
	int row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=board_map.length,
	 bm_c=board_map[0].length;
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len)>bm_r) || (col+wid>bm_c) || (row<0) || (col<0))
	return;
	else 
	{
	for (int i = row; i<row+len;i++ )
		for (int j= col;j<col+wid;j++ )
		{ board_map[i][j]=CELL_EMPTY;
		  block_map[i][j]=0;
		}
	}
}
public void setBlockNum(Coordinate c, BlockShape bs,int num){
	int row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=board_map.length,
	 bm_c=board_map[0].length;
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len)>bm_r) || (col+wid>bm_c) || (row<0) || (col<0))
	return;
	else 
	{
	for (int i = row; i<row+len;i++ )
		for (int j= col;j<col+wid;j++ )
		{ 
		  board_map[i][j]=CELL_OCCUPIED;	
		  block_map[i][j]=num;
		}
	}
}

public void setLeftEmpty(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()-1);
	setEmpty(c,bs);	
}
public void setBlockNumLeft(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),b.get_block_wid()+1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()-1);
	setBlockNum(c,bs,b.getID());	
}

public void setRightEmpty(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()+b.get_block_wid());
	setEmpty(c,bs);	
}
public void setBlockNumRight(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),b.get_block_wid()+1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}

public void setUpEmpty(block b){
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	setEmpty(c,bs);	
}
public void setBlockNumUp(block b){
	BlockShape bs=new BlockShape(b.get_block_len()+1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}
public void setDownEmpty(block b){
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()+b.get_block_len(),b.get_block_col());
	setEmpty(c,bs);	
}
public void setBlockNumDown(block b){
	BlockShape bs=new BlockShape(b.get_block_len()+1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}

public boolean MoveLeft(block b){
	block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
if (EmptyNeighborLeft(b)) 
	{  
		b.MoveLeft();
		setBlockNumLeft(save);
		setRightEmpty(b);
	return true;
	}
else return false;
}

public boolean MoveRight(block b){
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	if (EmptyNeighborRight(b)) 
		{b.MoveRight();
		setBlockNumRight(save);
		setLeftEmpty(b);
		return true;
		}
	else return false;
	}

public boolean MoveUp(block b){
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	if (EmptyNeighborUp(b)) 
		{b.MoveUp();
		setBlockNumUp(save);
		setDownEmpty(b);
		return true;
		}
	else return false;
	}
public boolean MoveDown(block b){
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	if (EmptyNeighborDown(b)) 
		{b.MoveDown();
		setBlockNumDown(save);
		setUpEmpty(b);
		return true;
		}
	else return false;
	}

	public int hashCode ( ) { 
		return board_map.toString().hashCode(); 
	}

	public boolean equals(Object obj){ 
		Iterator<block> itr0 = ((Board) obj).blockArray.iterator();
		if ( this.blockArray.size() != ((Board) obj).blockArray.size() )
			return false;
		while (itr0.hasNext()){
			block b0 = itr0.next();
			if ( !this.blockArray.contains(b0) )
				return false;
		}	
		return true;	
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
public Object clone(){
	Board result= new Board(this.getRows(),this.getCols());
	for (Iterator<block> iter = blockArray.iterator();iter.hasNext();)
	{
		block b= iter.next();
	result.addblocks(b);
	
	}
	return result;
}
public static void main (String [ ] args){
	  Board newBoard= new Board(6,5);
	  block b1= new block(1,1,1,2);
	  block b2=new block (2,2,3,3);
	 // block b3=new block (1,1,4,3);
	  newBoard.addblocks(b1);
	  newBoard.addblocks(b2);
	 // newBoard.addblocks(b3);
	  newBoard.displayBoard();
	  //System.out.println(newBoard.EmptyNeighborDown(b1));
	  System.out.println(newBoard.EmptyNeighborDown(b2));
	  System.out.println(newBoard.EmptyNeighborRight(b2));
	  System.out.println(newBoard.EmptyNeighborLeft(b2));
	  System.out.println(newBoard.EmptyNeighborUp(b2));
	  System.out.println(newBoard.EmptyNeighborUp(b2));
	  System.out.println(newBoard.emptyCells.size());
	 Object[] blah=newBoard.emptyCells.toArray();
	 System.out.println(newBoard.emptyCells.size());
  }
}
