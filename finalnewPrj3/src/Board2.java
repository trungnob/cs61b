
import java.util.*;


public class Board2 {

	public Board2(short newRows, short newCols) {
		initBoard(newRows, newCols);
	}
//	public Board(int newRows, int newCols,int totalBlocks){
//		initBoard(newRows, newCols,totalBlocks);
//	}
	
	// block_num keep track of which block we are dealing w/ . 
	// This is just for displaying purpose. --Trung add 
	private short block_num = 1;
	private Board2 prevBoard=null;
	private trayCells bm;
	//private String theMove="";
	private short theMoveBlockID;
	//public HashSet<block> blockArray = null;
	private HashMap<Coordinate,block> blockArray=null;
	
//	public PriorityQueue<MovableBlock> blockQueue=null;
//	public ArrayList<block> movableBlockLeft=null;
//	public ArrayList<block> movableBlockRight=null;
//	public ArrayList<block> movableBlockUp=null;
//	public ArrayList<block> movableBlockDown=null;

	public short getRows() {
		//return board_map.length;
		return bm.getRows();
	}	
	
	public short getCols() {
		return bm.getCols();
	}
	


	
//	public HashSet <block> getblock(){
//		return blockArray;
//	}
//	
	
	public int getTotalBlock(){
		if (blockArray==null) return -1;
		return blockArray.size();
	}
   public void pushMove(Move  m){
	 //  char prevDir=m.getDir();
	   theMoveBlockID=m.getBlock().getID();
	   //= ""+prevBlock+prevDir;
	   
   }
   public void pushBoard(Board2 boa){
	   prevBoard=boa;
   }
   public Board2 popBoard(){
	   return prevBoard;
   }
 
   public String popMove(){
	  
	 
		  
		  if (this.prevBoard==null) return "";
		  block thisMobileBlock=this.findBlockByID(theMoveBlockID);
		  block thePrevBlock=this.prevBoard.findBlockByID(theMoveBlockID);
		  return thePrevBlock.toStringCoordinate()+" "+thisMobileBlock.toStringCoordinate();
   }
   
  
   
    private void initBoard(short newRows,short newCols) {
    	//
    	if ((newRows >= 1) && (newCols >= 1)) {
  
    	    bm=new trayCells(newRows,newCols);
    	    blockArray=new HashMap<Coordinate,block>();
   
    	}
    }
    
    public int adding_block(short len, short wid, short row, short col){
    	int i, j;
    	// check if block is place within the board_map
    	if (row+len > bm.getRows())
    		return 1;
    	else if (col+wid > bm.getCols())
    		return 1;
    	for (i=row; i<row+len; i++){
    		for (j=col; j<col+wid; j++){
    			if (bm.getCells((short)i, (short)j))
    				return 1;
    			bm.set((short) i, (short)j);
    			
    		}
    	}
    	block b0 = new block(len, wid, row, col,block_num);//use  block_num as a identification number.
    	blockArray.put(b0.getCoor(), b0);
    	block_num++;
    	return 0;
    }
	public void setBoardMap(trayCells newBm){
		bm=newBm;
	}
    public int adding_block(short len, short wid, short row,short col,short ID){
    	
    	
    	block b0 = new block(len, wid, row, col,ID);//use  block_num as a identification number.
    	blockArray.put(b0.getCoor(), b0);
    	//this.constructListofMovableBlocks();
    	return 0;
    }
    public void displayBoard(){
    	int i,j;
    	int print_board[][] = new int [this.getRows()][this.getCols()];
    	for (i=0; i<getRows(); i++)
    		for (j=0; j<getCols(); j++)
    			print_board[i][j] = 0;
    	for (Iterator<block> iter = this.blockArray.values().iterator(); iter.hasNext();){
    		block b0 = iter.next();
    		int id = b0.getID(),
    			row = b0.get_block_row(),
    			col = b0.get_block_col(),
    			len = b0.get_block_len(),
    			wid = b0.get_block_wid();
    		for (i=row; i<row+len; i++)
        		for (j=col; j<col+wid; j++)
        			print_board[i][j] = id;
    	}
    	for (i=0; i<getRows(); i++){
    		for (j=0; j<getCols(); j++){
    			if (print_board[i][j]>99)
    				System.out.print(" "+print_board[i][j]);
    			else if (print_board[i][j]>9)
    				System.out.print("  "+print_board[i][j]);
    			else
    				System.out.print("   "+print_board[i][j]);
    		}
    		System.out.println();
    	}
    }
    
   /* public void displayBoard_db(){
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
    }*/ 
    

  public block findBlockByID(int ID){
	  block result=null;
	  for (Iterator<block> ite=blockArray.values().iterator();ite.hasNext();){
			 block temp=ite.next();
			 if (temp.getID()==ID) return temp;
		 }
	  return result;
  }

public boolean EmptyCell(Coordinate c){
	if ((c.getRow() > bm.getRows())|| (c.getCol()>bm.getCols()))
	return false;
	else 
	{short i=c.getRow(),
		 j=c.getCol();
	 return !(bm.getCells(i, j));
		
	}
		
}
//public   PriorityQueue<MovableBlock>  listOfMovableBlocks(){
//	PriorityQueue<MovableBlock> result=new PriorityQueue<MovableBlock>(); 
//  for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
//	 block b = iter.next();
//	 if ((!result.contains(b))&&(this.EmptyNeighborUp(b))) {
//		 Move m= new Move('U');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborDown(b))) {
//		 Move m= new Move('D');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborLeft(b))) {
//		 Move m= new Move('L');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborLeft(b))) {
//		 Move m= new Move('R');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 
//  }
//  return result;
//}
//public   Stack<MovableBlock>  listOfMovableBlocks(){
//	Stack<MovableBlock> result=new Stack<MovableBlock>(); 
//  for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
//	 block b = iter.next();
//	 if ((!result.contains(b))&&(this.EmptyNeighborUp(b))) {
//		 Move m= new Move('U');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborDown(b))) {
//		 Move m= new Move('D');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborLeft(b))) {
//		 Move m= new Move('L');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 if ((!result.contains(b))&&(this.EmptyNeighborLeft(b))) {
//		 Move m= new Move('R');
//		 MovableBlock mb=new MovableBlock(b,m);
//		 result.add(mb); 
//	 }
//	 
//  }
//  return result;
//}
public   TreeSet<Move>  listOfMovableBlocks(){
	
	TreeSet<Move> result=new TreeSet<Move>(); 
  for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
	 block b = iter.next();
	 if ((this.EmptyNeighborUp(b))) {
	      Move nm= new Move(b,'U');
	      if (result.contains(nm));else
		 result.add(nm); 
	 }
	 if ((this.EmptyNeighborDown(b))) {
		 Move nm= new Move(b,'D');  
		 
		 if (result.contains(nm));else
			 result.add(nm); 
		  
	 }
	 if ((this.EmptyNeighborLeft(b))) {
		 Move nm= new Move(b,'L');  
		 if (result.contains(nm));else
			 result.add(nm); 
	 }
	 if ((this.EmptyNeighborRight(b))) {
		 Move nm= new Move(b,'R');  
		 if (result.contains(nm));else
			 result.add(nm); 
	 }
	 
  }
  return result;
}

public boolean EmptyBlock(Coordinate c, BlockShape bs){ // check a block is empty or not start at its  up left conner
	short row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth(),
	 bm_r=bm.getRows(),
	 bm_c=bm.getCols();
	
	
	if (((row>bm_r)||(col>bm_c)) || ((row+len)>bm_r) || (col+wid>bm_c) || (row<0) || (col<0))
	return false;
	else 
	{
	for (short i = row; i<row+len;i++ )
		for (short j= col;j<col+wid;j++ )
			if (bm.getCells(i, j)) return false;
	 return true;
	}
}

public boolean EmptyNeighborLeft(block b){ 
BlockShape bs=new BlockShape(b.get_block_len(),(short)1);
Coordinate c= new Coordinate(b.get_block_row(),(short)(b.get_block_col()-1));
return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborRight(block b){ 
	BlockShape bs=new BlockShape(b.get_block_len(),(short)1);
	Coordinate c= new Coordinate(b.get_block_row(),(short)(b.get_block_col()+b.get_block_wid()));
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborUp(block b){ 
	BlockShape bs=new BlockShape((short)1,b.get_block_wid());
	Coordinate c= new Coordinate((short)(b.get_block_row()-1),b.get_block_col());
	return EmptyBlock(c,bs);	
}
public boolean EmptyNeighborDown(block b){ 
	BlockShape bs=new BlockShape((short)1,b.get_block_wid());
	Coordinate c= new Coordinate((short)(b.get_block_row()+b.get_block_len()),b.get_block_col());
	return EmptyBlock(c,bs);	
}

public void setMoveUp(Coordinate c, BlockShape bs){
	short row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth();
	for (short i = col;i<col+wid;i++){
		bm.set((short)(row-1), i);
		bm.clear((short)(row+len-1), i);
	}
}

public void setMoveDown(Coordinate c, BlockShape bs){
	short row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth();
	for (short i = col;i<col+wid;i++){
		bm.set((short)(row+len), i);
		bm.clear(row, i);
//		board_map[row+len][i]=CELL_OCCUPIED;
//		board_map[row][i]=CELL_EMPTY;
	}
}

public void setMoveLeft(Coordinate c, BlockShape bs){
	short row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth();
	for (short i = row;i<row+len;i++){
		bm.set(i, (short)(col-1));
		bm.clear(i,(short)(col+wid-1));
//		board_map[i][col-1]=CELL_OCCUPIED;
//		board_map[i][col+wid-1]=CELL_EMPTY;
	}
}

public void setMoveRight(Coordinate c, BlockShape bs){
	short row=c.getRow(),
	 col=c.getCol(),
	 len=bs.getLength(),
	 wid=bs.getWidth();
	for (short i = row;i<row+len;i++){
		bm.set(i, (short)(col+wid));
		bm.clear(i,col);
//		board_map[i][col+wid]=CELL_OCCUPIED;
//		board_map[i][col]=CELL_EMPTY;
	}
}

public boolean MoveLeft(block b){
	Move MoveLeft=new Move('L');
	block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
	block newB=blockArray.remove(coor);
	if (EmptyNeighborLeft(newB)){  
		
		newB.ApplyMove(MoveLeft);
		//newB.AddMove(MoveLeft);
		setMoveLeft(save.getCoor(),save.getShape());
		blockArray.put(newB.getCoor(),newB);
		return true;
	}
	else return false;
}

//public boolean MoveLeft(MovableBlock b){
//Move MoveLeft=new Move('L');
//block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
//Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
//MovableBlock newB=(MovableBlock)blockArray.remove(coor);
//if (EmptyNeighborLeft(newB)){  
//	;
//	newB.ApplyMove(MoveLeft);
//	//newB.AddMove(MoveLeft);
//	setMoveLeft(save.getCoor(),save.getShape());
//	blockArray.put(newB.getCoor(),newB);
//	return true;
//}
//else return false;
//}

public boolean MoveRight(block b){
	Move MoveRight=new Move('R');
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
	block newB=blockArray.remove(coor);
	if (EmptyNeighborRight(newB)){

		newB.ApplyMove(MoveRight);
		//newB.AddMove(MoveRight);
		setMoveRight(save.getCoor(),save.getShape());
		blockArray.put(newB.getCoor(),newB);
		return true;
	}
	else return false;
}
//public boolean MoveRight(MovableBlock b){
//	Move MoveRight=new Move('R');
//	block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
//	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
//	MovableBlock newB=(MovableBlock)blockArray.remove(coor);
//	if (EmptyNeighborLeft(newB)){  
//		;
//		newB.ApplyMove(MoveRight);
//		//newB.AddMove(MoveLeft);
//		setMoveLeft(save.getCoor(),save.getShape());
//		blockArray.put(newB.getCoor(),newB);
//		return true;
//	}
//	else return false;
//	}

public boolean MoveUp(block b){
	Move MoveUp=new Move('U');
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	//Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
	//block newB=blockArray.get(coor);
	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
	block newB=blockArray.remove(coor);
	if (EmptyNeighborUp(newB)){
		
		newB.ApplyMove(MoveUp);
		//newB.AddMove(MoveUp);
		setMoveUp(save.getCoor(),save.getShape());
		blockArray.put(newB.getCoor(),newB);
		return true;
	}
	else return false;
}
//public boolean MoveUp(MovableBlock b){
//	Move MoveUp=new Move('U');
//	block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
//	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
//	MovableBlock newB=(MovableBlock)blockArray.remove(coor);
//	if (EmptyNeighborLeft(newB)){  
//		;
//		newB.ApplyMove(MoveUp);
//		//newB.AddMove(MoveLeft);
//		setMoveLeft(save.getCoor(),save.getShape());
//		blockArray.put(newB.getCoor(),newB);
//		return true;
//	}
//	else return false;
//	}

public boolean MoveDown(block b){
	Move MoveDown=new Move('D');
	block save =new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
	block newB=blockArray.remove(coor);
	if (EmptyNeighborDown(newB)){
		
		newB.ApplyMove(MoveDown);
		//newB.AddMove(MoveDown);
		setMoveDown(save.getCoor(),save.getShape());
		blockArray.put(newB.getCoor(),newB);
		return true;
	}
	else return false;
}

//public boolean MoveDown(MovableBlock b){
//	Move MoveDown=new Move('D');
//	block save=new block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
//	Coordinate coor= new Coordinate(b.get_block_row(),b.get_block_col());
//	MovableBlock newB=(MovableBlock)blockArray.remove(coor);
//	if (EmptyNeighborLeft(newB)){  
//		;
//		newB.ApplyMove(MoveDown);
//		//newB.AddMove(MoveLeft);
//		setMoveLeft(save.getCoor(),save.getShape());
//		blockArray.put(newB.getCoor(),newB);
//		return true;
//	}
//	else return false;
//	}
	public int hashCode ( ) { 
//		Iterator<block> iter = blockArray.iterator();
//		block b;
//		int code = 0;
//		while(iter.hasNext()){
//			b = iter.next();
//			code += b.hashCode()*b.get_block_len()*b.get_block_len()*(b.get_block_col()+1)*(b.get_block_row()+1);
//		};
//		return code;
		int code=0;

		  for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
				 block b = iter.next();
				 code=code+b.hashCode()*(b.get_block_len()*b.get_block_len())*(b.get_block_row()+1)*(b.get_block_wid()+1);
		  }
		  

    	
    		return code;
//		return this.toString().hashCode();
//		return hashNum;
	}
	
	public boolean equals(Object obj){ 
		Board2 bt=(Board2) obj;
		if (!(bt.bm.equals(bt.bm))) return false;
		Iterator<block> itr0 = ((Board2) obj).blockArray.values().iterator();
		if ( this.blockArray.size() != ((Board2) obj).blockArray.size() )
			return false;
		while (itr0.hasNext()){
			block b0 = itr0.next();
			if ( !this.blockArray.containsValue(b0))
				return false;
		}	
		return true;	
	}


public boolean compareToGoal(ArrayList<block> Goal ){
  	Iterator<block> iter = Goal.iterator();
  	while(iter.hasNext()){
  		block b0 = iter.next();
  		if (!this.blockArray.containsValue(b0))
  			return false;
  	}
  	return true;
  }
public void doMove(Move move){
	if (move.getDir()=='U')MoveUp(move.getBlock());
	else
	if (move.getDir()=='L')MoveLeft(move.getBlock());
	else
	if (move.getDir()=='R')MoveRight(move.getBlock());
	else
		if (move.getDir()=='D')MoveDown(move.getBlock());
}
public String toString(){
	int i,j;
	 String temp="";
	int print_board[][] = new int [this.getRows()][this.getCols()];
	for (i=0; i<getRows(); i++)
		for (j=0; j<getCols(); j++)
			print_board[i][j] = 0;
	for (Iterator<block> iter = this.blockArray.values().iterator(); iter.hasNext();){
		block b0 = iter.next();
		int id = b0.getID(),
			row = b0.get_block_row(),
			col = b0.get_block_col(),
			len = b0.get_block_len(),
			wid = b0.get_block_wid();
		for (i=row; i<row+len; i++)
    		for (j=col; j<col+wid; j++)
    			print_board[i][j] = id;
	}
	for (i=0; i<getRows(); i++){
		for (j=0; j<getCols(); j++){
			if (print_board[i][j]>99)
				temp+=" "+print_board[i][j];
			else if (print_board[i][j]>9)
				temp+="  "+print_board[i][j];
			else
				temp+="   "+print_board[i][j];
		}
		temp+="\n";
	}
	return temp;
}
public Object clone(){
	Board2 result= new Board2(this.getRows(),this.getCols());
	trayCells newBM= (trayCells)this.bm.clone();
	result.setBoardMap(newBM);
	for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
		block b= iter.next();
		result.adding_block(b.get_block_len(),b.get_block_wid(),b.get_block_row(),b.get_block_col(),b.getID());
	
	}
	return result;
}
public boolean isOk(){
	    	int i, j;
	    	if (getRows()<0 || getRows()>255 || getCols()<0 || getCols()>255  ) return false;
	    	boolean[][] checkArray=new boolean[this.getRows()][this.getCols()];
	    	for (Iterator<block> iter = blockArray.values().iterator();iter.hasNext();){
	    		block b= iter.next();
	    		if (!b.isOk()) return false;
	    		short br=b.get_block_row();
	    		short bl=b.get_block_len();
	    		short bw=b.get_block_wid();
	    		short bc=b.get_block_col();
	    		if (br+bl > getRows())
		    		return false;
		    	else if (bc+bw > getCols())
		    		return false;
		    	for (i=br; i<br+bl; i++)
		    		for (j=bc; j<bc+bw; j++)
		    		{ if (checkArray[i][j]) return false;
		    			checkArray[i][j]=true;
		    		}
		    	}
		    return true;	
	    		
	    	
	    	// check if block is place within the board_map
	    	
}
public static void main (String [ ] args){
	  Board2 newBoard= new Board2((short)6,(short)5);
	  //block b1= new block(1,1,1,2);
	  //block b2=new block (2,2,3,3);
	 // block b3=new block (1,1,4,3);
	  newBoard.adding_block((short)1,(short)1,(short)1,(short)2);
	  newBoard.adding_block((short)2,(short)2,(short)4,(short)2);
	 // newBoard.addblocks(b3);
	  newBoard.displayBoard();
	  
	 TreeSet<Move>lmb=newBoard.listOfMovableBlocks();
	 System.out.println(lmb.size());
	Iterator<Move>  iter = lmb.iterator() ;
	Move mbm=iter.next();
	Board2  moveBoard =(Board2)newBoard.clone();
    moveBoard.doMove(mbm);
    moveBoard.pushBoard(newBoard);
    moveBoard.pushMove(mbm);
    
	 System.out.println();
	 newBoard.displayBoard();
	 System.out.println(moveBoard.popMove());
	 
	
	 
	  //System.out.println(newBoard.EmptyNeighborDown(b1));
	  /*System.out.println(newBoard.EmptyNeighborDown(b2));
	  System.out.println(newBoard.EmptyNeighborRight(b2));
	  System.out.println(newBoard.EmptyNeighborLeft(b2));
	  System.out.println(newBoard.EmptyNeighborUp(b2));
	  System.out.println(newBoard.EmptyNeighborUp(b2));
	  System.out.println(newBoard.emptyCells.size());*/
	
	 //newBoard.MoveUp();
//	 newBoard.displayBoard();
  }
}
