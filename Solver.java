import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Solver {
	static HashSet<Board> tray = new HashSet<Board> ();
	
////////////////////get the initial configuration and print the board //////////////////
	public static Board board_setup(String filename) throws IOException{

		BufferedReader initConfig = null;
		try {
			initConfig = new BufferedReader (new FileReader (filename));
		} catch (Exception e) {
			System.err.println ("Couldn't access file!");
			System.exit (1);
		}
		String line = null;
		line = initConfig.readLine();
		StringTokenizer tokens = new StringTokenizer (line, " ");
		
		int row = Integer.parseInt((String) tokens.nextElement ( ));
		int col = Integer.parseInt((String) tokens.nextElement ( ));
		
		Board board= new Board(row, col);
		
		while ((line=initConfig.readLine()) != null) {
			tokens = new StringTokenizer (line, " ");
			int b_len = Integer.parseInt((String) tokens.nextElement ( ));
			int b_wid = Integer.parseInt((String) tokens.nextElement ( ));
			int b_row = Integer.parseInt((String) tokens.nextElement ( ));
			int b_col = Integer.parseInt((String) tokens.nextElement ( ));
			
			board.adding_block(b_len, b_wid, b_row, b_col);
			//board.block_set.add((new block(b_len, b_wid, b_row, b_col)));
			//block b0 = board.adding_block(b_len, b_wid, b_row, b_col);
			//board.block_set.add(b0);
			//board.block_set = board.adding_block(b_len, b_wid, b_row, b_col);
		}
		
		board.displayBoard();
		
		initConfig.close();
		
		return board;
		
	}
	
	
	public static ArrayList<block> getGoal(String filename) throws IOException{
         ArrayList<block> result= new ArrayList<block>();
		BufferedReader initConfig = null;
		try {
			initConfig = new BufferedReader (new FileReader (filename));
		} catch (Exception e) {
			System.err.println ("Couldn't access file!");
			System.exit (1);
		}
		String line = null;
//		line = initConfig.readLine();
		StringTokenizer tokens = new StringTokenizer (" ");
//		
		//int row = Integer.parseInt((String) tokens.nextElement ( ));
		//int col = Integer.parseInt((String) tokens.nextElement ( ));
		
		//Board board= new Board(row, col);
		
		while ((line=initConfig.readLine()) != null) {
			tokens = new StringTokenizer (line, " ");
			int b_len = Integer.parseInt((String) tokens.nextElement ( ));
			int b_wid = Integer.parseInt((String) tokens.nextElement ( ));
			int b_row = Integer.parseInt((String) tokens.nextElement ( ));
			int b_col = Integer.parseInt((String) tokens.nextElement ( ));
			
			block b= new block(b_len, b_wid, b_row, b_col);
			result.add(b);
			//board.block_set.add((new block(b_len, b_wid, b_row, b_col)));
			//block b0 = board.adding_block(b_len, b_wid, b_row, b_col);
			//board.block_set.add(b0);
			//board.block_set = board.adding_block(b_len, b_wid, b_row, b_col);
		}
		
		System.out.println("Goal has "+ result.size()+" blocks");
		
		initConfig.close();
		
		return result;
		
	}
	
//	public static boolean isAtGoal(Board playBoard, Board goalBoard){
//		
//		return false;
//	}


 /*   public Board generatemove(Board brd){
    	//String inputConfigName = "";
    	Board board = null;
    	Iterator <block> iter = brd.getblock().iterator();
    	while (iter.hasNext()){
    		block b = iter.next();
    		if (brd.MoveRight(b)){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		}else if (brd.MoveUp(b)){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		}else if (brd.MoveLeft(b)){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		}else if (brd.MoveDown(b)){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		}else{}
    	}
        return board;
    }
	*/
    
	public Board generaterightmove(Board brd, block b){
    	Board temp;
    	temp = brd.clone();
    	brd.MoveRight(b);
    	if (tray.contains(brd)){
    		return generateleftmove(temp, b);}
    	else
    		tray.add(brd);
    	return brd;
	}
	
	
	public Board generateleftmove(Board brd, block b){
    	Board temp;
    	temp = brd.clone();
    	brd.MoveLeft(b);
    	if (tray.contains(brd)){
    		return generateleftmove(temp, b);}
    	else
    		tray.add(brd);
    	return brd;
	}
	
	public Board generateupmove(Board brd, block b){
    	Board temp;
    	temp = brd.clone();
    	brd.MoveUp(b);
    	if (tray.contains(brd)){
    		return generateleftmove(temp, b);}
    	else
    		tray.add(brd);
    	return brd;
	}
	
	public Board generatedownmove(Board brd, block b){
    	brd.MoveDown(b);
    	if (tray.contains(brd)){
    		return null;}
    	else
    		tray.add(brd);
    	return brd;
	}
	
    	
    	public Board generatemove(Board brd, block b){
        	Board myboard;
        	Board temp;
        	block c;
        	temp = brd.clone();
        	Iterator <block> iter = brd.getblock().iterator();
        	c = iter.next();
        	myboard = generaterightmove(brd, c);
        	while (myboard == null && iter.hasNext()){
        		b = iter.next();
        		myboard = generatemove(temp, b);}
        	return myboard;      	
    	}	
    	
	/*
	public Board generatemove(Board brd){
    	Board board;
    	Board temp = brd;
    	Iterator <block> iter = brd.getblock().iterator();
    	block b = iter.next();
    	while ()
    	if (brd.MoveRight(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		if (tray.contains(board)){
    			brd = temp;
    			return generatemove(brd);}
    		else
        		tray.add(board);
    		return board;
    	}else if (brd.MoveUp(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		if (tray.contains(board)){
    			brd = temp;
    			return generatemove(brd);}
    		else
        		tray.add(board);
    		return board;
    	}else if (brd.MoveLeft(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		if (tray.contains(board)){
    			brd = temp;
    			return generatemove(brd);}
    		else
        		tray.add(board);
    		return board;
    	}else if (brd.MoveDown(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		if (tray.contains(board)){
    			brd = temp;
    			return generatemove(brd);}
    		else
        		tray.add(board);
    		return board;
    	}else{
    		return null;
    	}
    }*/
    
    public boolean play(Board brd, Board fnl){
    	Board board = null;
    	Board head= null;
    	Board ref = null;
    	boolean t = false;
    	if (brd == null)
    		return false;
    	if (brd.equals(fnl))
    		return true;
    	tray.add(brd);
    	ref = board = head = brd.clone();
    	head.myparent = brd;
    	brd.Leftmostchild = head;
    	while (ref != null){
    		generatemove(board, null);
    		board = brd.clone();
    		ref.mysibbling = board;
    		board.myparent = brd;
    		ref = board;
        	if (head == null)
        		return false;
    		while (!t){
    			t = play(head, fnl);
    			if (t == true)
    				return true;
    		}
    	}
    	return false;
    }
	
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static void main (String [ ] args)  throws IOException{
		
		String inputConfigName = "", finalConfigName = "";
		
        if (args.length == 2) {
        	inputConfigName = args[0];
        	finalConfigName = args[1];
		} else if (args.length == 3){
			inputConfigName = args[1];
			finalConfigName = args[2];
		} else{
			System.out.println("Wrong number of arguments");	
			System.exit (0);
		}
		
        Board my_board, finalboard;//board_final;
        my_board = board_setup(inputConfigName);
        finalboard = board_setup(finalConfigName);
         if (play(my_board, finalboard))
        	 System.out.print("No solution exists");
         else
        	 System.out.print("Solution found");
        

      /*  
        Board my_board;//board_final;
        
        my_board = board_setup(inputConfigName);
        System.out.println();
       ArrayList<block>theGoal= getGoal(finalConfigName);
        System.out.println(my_board.compareToGoal(theGoal));
        block b=my_board.findBlockByID(1);
        if (b==null) System.out.println("It is null!!\n");else 
        my_board.MoveRight(b);
        my_board.displayBoard();
        b=my_board.findBlockByID(3);
        my_board.MoveUp(b);
        System.out.println();
        my_board.displayBoard();
        b=my_board.findBlockByID(6);
        my_board.MoveUp(b);
        System.out.println();
        my_board.displayBoard();
        b=my_board.findBlockByID(2);
        my_board.MoveLeft(b);
        System.out.println();
        my_board.displayBoard();
        
        //if (my_board.equal(board_final) )
        //	System.out.println("the compare is good");
		*/
	}
}
