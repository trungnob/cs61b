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
	
	public static boolean isAtGoal(Board playBoard, Board goalBoard){
		
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
		
	}
}
