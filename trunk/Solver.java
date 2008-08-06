import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Solver {

	
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
		}
		board.update_movable(true);
		
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
		}
		
		System.out.println("Goal has "+ result.size()+" blocks");
		
		initConfig.close();
		
		return result;
		
	}
	
	public static boolean isAtGoal(Board playBoard, Board goalBoard){
		
		return false;
	}

	
	public static HashSet<Board> Board_set = new HashSet<Board> ();
	
	public static void get_children_move(Board my_board, ArrayList<block> Goal){
		if (my_board == null) return;
		my_board.update_movable(false);
		//Board board_child = null;
		int i=0;
		for (Iterator<block> itr = my_board.block_Movable.iterator(); itr.hasNext();){
			block b0 = itr.next();
			System.out.println("debugging i = " + i++);
			if (my_board.EmptyNeighborLeft(b0)){
				Board board_child = my_board.MoveLeft(b0);
				if (!Board_set.contains(board_child)){
					Board_set.add(board_child);
					board_child.displayBoard();
					if (board_child.compareToGoal(Goal)) return;
					my_board.addleftmostchild(board_child);
					get_children_move(board_child, Goal);
					System.out.println("checking the origial my board");
					my_board.displayBoard();
				}
			}
			if (my_board.EmptyNeighborRight(b0)){
				Board board_child = my_board.MoveRight(b0);
				if (!Board_set.contains(board_child)){
					Board_set.add(board_child);
					board_child.displayBoard();
					if (board_child.compareToGoal(Goal)) return;
					my_board.addleftmostchild(board_child);
					get_children_move(board_child, Goal);
				}
			}
			if (my_board.EmptyNeighborUp(b0)){
				Board board_child = my_board.MoveUp(b0);
				if (!Board_set.contains(board_child)){
					Board_set.add(board_child);
					board_child.displayBoard();
					if (board_child.compareToGoal(Goal)) return;
					my_board.addleftmostchild(board_child);
					//get_children_move(board_child, Goal);
				}
			}
			if (my_board.EmptyNeighborDown(b0)){
				Board board_child = my_board.MoveDown(b0);
				if (!Board_set.contains(board_child)){
					Board_set.add(board_child);
					board_child.displayBoard();
					if (board_child.compareToGoal(Goal)) return;
					my_board.addleftmostchild(board_child);
					//get_children_move(board_child, Goal);
				}
			}
		}
	}
	
	
	public static void check_all(Board root,ArrayList<block> Goal){
		Queue<Board> board_que = null;
		
		if (root == null)
			return;
		
		board_que.offer(root);
		Board traverse;
		
		while (!board_que.isEmpty()){
			traverse = board_que.remove();
			if (traverse.compareToGoal(Goal)){
				System.out.println("I'm done here");
				traverse.displayBoard();
				return;
			}
			for (Iterator<Board> itr = traverse.Leftmostchild.iterator(); itr.hasNext();){
				Board tmp = itr.next();
				board_que.offer(tmp);
			}
		}
		
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
		        
        Board my_board;//board_final;
        my_board = board_setup(inputConfigName);
        System.out.println();
        ArrayList<block>theGoal= getGoal(finalConfigName);
        System.out.println(my_board.compareToGoal(theGoal));
        
        /*for (Iterator<block> itr = my_board.block_Movable.iterator(); itr.hasNext();){
			block b0 = itr.next();
			System.out.println(b0);
        }*/
        
        Board_set.add(my_board);
        get_children_move(my_board, theGoal);
        Board my_board2 = null;
        Board my_board3 = null;
        Board my_board4 = null;
        Board my_board5 = null;
        Board my_board6 = null;
        Board my_board7 = null;
        Board my_board8 = null;
        Board my_board9 = null;
        
        
        
        
       /* block b=my_board.findBlockByID(7);
        if (b==null) System.out.println("It is null!!\n");else
        my_board2 = my_board.MoveUp(b);
        my_board2.displayBoard();
        System.out.println();
        block b1=my_board2.findBlockByID(7);
        my_board3 = my_board2.MoveRight(b1);
        my_board3.displayBoard();
        System.out.println();
        block b2=my_board3.findBlockByID(6);
        if (my_board3.EmptyNeighborRight(b2))
        	my_board4 = my_board3.MoveRight(b2);
        else
        	my_board4 = my_board3;
        my_board4.displayBoard();
        System.out.println();
        block b3=my_board4.findBlockByID(6);
        my_board5 = my_board4.MoveUp(b3);
        my_board5.displayBoard();*/
        
        
        
        //check_all(my_board,theGoal);
        
        
        
       /* block b=my_board.findBlockByID(1);
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




///////////////////////// emin code ///////////////////////

/*	
	
    public Board generatemove(Board brd){
    	String inputConfigName = "";
    	Board board;
    	Iterator <block> iter = brd.getblock().iterator();
    	while (iter.hasNext()){
    		block b = iter.next();
    		if (brd.MoveRight(b)){
    			while (iter.hasNext()){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    				b = iter.next();
    			}
    		}else if (brd.MoveUp(b)){
    			while (iter.hasNext()){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    				b = iter.next();
    			}
    		}else if (brd.MoveLeft(b)){
    			while (iter.hasNext()){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    				b = iter.next();
    			}
    		}else if (brd.MoveDown(b)){
    			while (iter.hasNext()){
    				board= new Board(brd.getRows(), brd.getCols());
    				board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    				b = iter.next();
    			}
    		}else{}
    	}
        return board;
    }
	*/
/*	
    public Board generatemove(Board brd){
    	Board board;
    	Iterator <block> iter = brd.getblock().iterator();
    	block b = iter.next();
    	if (brd.MoveRight(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		return board;
    	}else if (brd.MoveUp(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		return board;
    	}else if (brd.MoveLeft(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		return board;
    	}else if (brd.MoveDown(b)){
    		board= new Board(brd.getRows(), brd.getCols());
    		board.adding_block(b.get_block_len(), b.get_block_wid(), b.get_block_row(), b.get_block_col());
    		return board;
    	}else{
    		return null;
    	}
    }
    
    public void play(Board brd){
    	Board board = null;
    	Iterator <Board> iter = brd.getsibblings().iterator();
    	if (generatemove(brd)!= null)
    		board =generatemove(brd);
    	while (generatemove(brd) != null){
    		board.addsibblings(generatemove(brd));
    	}
    	brd.addleftmostchild(board);
    	while(iter.hasNext()){
    		play(iter.next());
    	}
    }*/