import java.util.*;

//
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
	
	public ArrayList <Board> mysibbling = null;
	public ArrayList <Board> Leftmostchild = null;
	
	//public HashSet<block> block_set = new HashSet<block> ();
	private ArrayList<block> blockArray = null;
	
	private static final int CELL_EMPTY = 0;
	private static final int CELL_OCCUPIED = 1;
	
	public ArrayList<block> block_Movable = null;
	
	
	
	
	
	public int getRows() {
		return board_map.length;
	}	
	
	public int getCols() {
		return board_map[0].length;
	}
	
	public ArrayList <Board> getsibblings(){
		return mysibbling;
	}
	
	
	public void addblocks(block myblocks1){
		blockArray.add(myblocks1);
	}

	public void addsibblings(Board sibbling){
		mysibbling.add(sibbling);
	}
	
	public ArrayList <block> getblock(){
		return blockArray;
	}
	
	public void addleftmostchild(Board  child){
		Leftmostchild.add(child);
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
    	    block_Movable =new ArrayList<block>();
    	    mysibbling = new ArrayList<Board>();
    		Leftmostchild = new ArrayList<Board>();
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
    
    public void update_movable(boolean setup){
    	if (!setup)
    		this.block_Movable.clear();
    	for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
    		block b0 = itr.next();
    		if ( EmptyNeighborLeft(b0) || EmptyNeighborRight(b0) || EmptyNeighborUp(b0) || EmptyNeighborDown(b0) )
    			this.block_Movable.add(b0);
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
	if ((c.getRow() > board_map.length-1)|| (c.getCol()>board_map[0].length-1)) //clark
	return false;
	else 
	{int i=c.getRow(),
		 j=c.getCol();
	 return board_map[i][j]==CELL_EMPTY;
		
	}	
}

public boolean EmptyBlock(Coordinate c, BlockShape bs){ // check a block is empty or not start at its  up left conner
	int row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=board_map.length -1,
	 bm_c=board_map[0].length -1;
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len-1)>bm_r) || ((col+wid-1)>bm_c) || (row<0) || (col<0))//clark change row +len to row +len-1
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
	//Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()+1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()+b.get_block_wid()); //clark
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborUp(block b){ 
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborDown(block b){ 
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	//Coordinate c= new Coordinate(b.get_block_row()+1,b.get_block_col());
	Coordinate c= new Coordinate(b.get_block_row()+b.get_block_len(),b.get_block_col());//clark 
	return EmptyBlock(c,bs);	
}
public void setEmpty(Coordinate c, BlockShape bs){
	int row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=board_map.length -1, //clark change row +len to row +len-1
	 bm_c=board_map[0].length -1; //clark change row +len to row +len-1
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len-1)>bm_r) || ((col+wid-1)>bm_c) || (row<0) || (col<0))//clark change row +len to row +len-1
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
	 bm_r=board_map.length -1, //clark change row +len to row +len-1
	 bm_c=board_map[0].length -1; //clark change row +len to row +len-1
	
	
	if ( (row>bm_r) || (col>bm_c) || ((row+len-1)>bm_r) || ((col+wid-1)>bm_c) || (row<0) || (col<0)) //clark change row +len to row +len-1
	return;
	else 
	{
	for (int i = row; i<row+len;i++ )
		for (int j= col;j<col+wid;j++ )
		{ 
		  block_map[i][j]=num;
		}
	}
}


public void setBlockNumLeft(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),b.get_block_wid()+1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()-1);
	setBlockNum(c,bs,b.getID());	
}
public void setBlockNumRight(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),b.get_block_wid()+1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}
public void setBlockNumUp(block b){
	BlockShape bs=new BlockShape(b.get_block_len()+1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}
public void setBlockNumDown(block b){
	BlockShape bs=new BlockShape(b.get_block_len()+1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col());
	setBlockNum(c,bs,b.getID());	
}


public void setLeftEmpty(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()-1);
	setEmpty(c,bs);	
}
public void setRightEmpty(block b){
	BlockShape bs=new BlockShape(b.get_block_len(),1);
	Coordinate c= new Coordinate(b.get_block_row(),b.get_block_col()+b.get_block_wid()); 
	setEmpty(c,bs);	
}
public void setUpEmpty(block b){
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()-1,b.get_block_col());
	setEmpty(c,bs);	
}

public void setDownEmpty(block b){
	BlockShape bs=new BlockShape(1,b.get_block_wid());
	Coordinate c= new Coordinate(b.get_block_row()+b.get_block_len(),b.get_block_col()); 
	setEmpty(c,bs);	
}
public Board MoveLeft(block b){
	Board new_board = new Board(this.getRows(),this.getCols());
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	block to_copy = null;
	for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		new_board.adding_block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col());
	}  
	for (Iterator<block> itr = new_board.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		if (b.equals(tmp))
			to_copy = tmp;
	}
	new_board.setBlockNumLeft(to_copy);
	to_copy.MoveLeft();
	new_board.setRightEmpty(to_copy);
	new_board.update_movable(true);  //setup the movable
	return new_board;
}

public Board MoveRight(block b){
	Board new_board = new Board(this.getRows(),this.getCols());
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	block to_copy= null;
	for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		new_board.adding_block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col());
	}  
	for (Iterator<block> itr = new_board.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		if (b.equals(tmp))
			to_copy = tmp;
	}
	new_board.setBlockNumRight(to_copy);
	to_copy.MoveRight();
	new_board.setLeftEmpty(to_copy);
	new_board.update_movable(true);  //setup the movable
	return new_board;
}

public Board MoveUp(block b){
	Board new_board = new Board(this.getRows(),this.getCols());
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	block to_copy= null;
	for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		new_board.adding_block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col());
	}  
	for (Iterator<block> itr = new_board.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		if (b.equals(tmp))
			to_copy = tmp;
	}
	new_board.setBlockNumUp(to_copy);
	to_copy.MoveUp();
	new_board.setDownEmpty(to_copy);
	new_board.update_movable(true);  //setup the movable
	return new_board;
}

/*for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
block tmp = itr.next();
block new_block =new block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col(),tmp.getID());
new_board.adding_block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col());
if (b.equals(new_block))
	to_copy = new_block;
}  */

public Board MoveDown(block b){
	Board new_board = new Board(this.getRows(),this.getCols());
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	block to_copy= null;
	for (Iterator<block> itr = this.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		new_board.adding_block(tmp.get_block_len(),tmp.get_block_wid(),tmp.get_block_row(),tmp.get_block_col());
	}  
	for (Iterator<block> itr = new_board.blockArray.iterator(); itr.hasNext();){
		block tmp = itr.next();
		if (b.equals(tmp))
			to_copy = tmp;
	}
	new_board.setBlockNumDown(to_copy);
	to_copy.MoveDown();
	new_board.setUpEmpty(to_copy);
	new_board.update_movable(true);  //setup the movable
	return new_board;
}

public int hashCode ( ) {
	int i,j;
	String result = "";
	for (i=0; i<getRows(); i++){
		for (j=0; j<getCols(); j++){
			if (block_map[i][j]>99)
				result = result + " "+block_map[i][j];
			else if (block_map[i][j]>9)
				result = result + "  "+block_map[i][j];
			else
				result = result + "   "+block_map[i][j];
		}
	}
	return result.hashCode(); 
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

/*public String toString(){
	return board_map.toString();
}*/



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



/*
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
*/
