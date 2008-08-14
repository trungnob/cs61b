import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Solver {
	static HashSet<Board2> traySet = new HashSet<Board2>();
	static Stack<Board2> myStack=new Stack<Board2>();
	static ArrayList<block> Goal =new ArrayList<block>(); 
	private static boolean debugModeDetail=false;
	private static boolean debugModeStandard=false;
	private static Board2 goalBoard;
////////////////////get the initial configuration and print the board //////////////////
	public static Board2 board_setup(String filename) throws IOException{

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
		
		short row = (short)Integer.parseInt((String) tokens.nextElement ( ));
		short col = (short)Integer.parseInt((String) tokens.nextElement ( ));
		
		Board2 board= new Board2((short)row, (short)col);
		goalBoard =  new Board2((short)row, (short)col);
		while ((line=initConfig.readLine()) != null) {
			tokens = new StringTokenizer (line, " ");
			short b_len = (short)Integer.parseInt((String) tokens.nextElement ( ));
			short b_wid = (short)Integer.parseInt((String) tokens.nextElement ( ));
			short b_row = (short)Integer.parseInt((String) tokens.nextElement ( ));
			short b_col = (short)Integer.parseInt((String) tokens.nextElement ( ));
			
			board.adding_block(b_len, b_wid, b_row, b_col);
		}
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

		StringTokenizer tokens = new StringTokenizer (" ");
		
		while ((line=initConfig.readLine()) != null) {
			tokens = new StringTokenizer (line, " ");
			int b_len = Integer.parseInt((String) tokens.nextElement ( ));
			int b_wid = Integer.parseInt((String) tokens.nextElement ( ));
			int b_row = Integer.parseInt((String) tokens.nextElement ( ));
			int b_col = Integer.parseInt((String) tokens.nextElement ( ));
			
			block b= new block((short)b_len, (short)b_wid, (short)b_row, (short)b_col);
			result.add(b);
			goalBoard.adding_block((short)b_len, (short)b_wid, (short)b_row, (short)b_col);
			
		}
		
		initConfig.close();
		
		return result;
		
	}
    

	public static Board2 Solve(Board2 brd){
		myStack.push(brd);
		if (brd.compareToGoal(Goal)){
			return brd;
		}else
	while (!myStack.isEmpty()){
		if (debugModeStandard) System.out.println("Size of Tray"+traySet.size()+ " Size of Stack"+myStack.size());
		Board2 moveBoard= (Board2)myStack.pop();
	if (traySet.contains(moveBoard)){}
		else{
			traySet.add(moveBoard);
			TreeSet<Move> lbm =moveBoard.listOfMovableBlocks();
			Board2 boardToAdd;
			Iterator<Move> iter=lbm.iterator();
			while (iter.hasNext()){
				boardToAdd=(Board2)moveBoard.clone();
				Move mobileBlock=iter.next();
				boardToAdd.doMove(mobileBlock);
				if (traySet.contains(boardToAdd)){}
				else{
					boardToAdd.pushBoard(moveBoard);
					boardToAdd.pushMove(mobileBlock);
					if (debugModeDetail){
						if (!boardToAdd.isOk())System.out.println(boardToAdd.toString() + "Is not Ok!!");
						System.out.println(mobileBlock.toString());
						System.out.println(boardToAdd.toString());
						try {
							System.in.read();
						} catch (IOException e) {
						
							e.printStackTrace();
						}
					}
				if (boardToAdd.compareToGoal(Goal)){
					return boardToAdd;
				}
				
				myStack.push(boardToAdd);
				}
				
		
			}
		}
	
	}
		
	return null;
	}
	
    
	public static void main (String [ ] args)  throws IOException{
		
		String inputConfigName = "", finalConfigName = "";
		String option="";
		
        if (args.length == 2) {
        	inputConfigName = args[0];
        	finalConfigName = args[1];
		} else if (args.length == 3){
			option=args[0];
			inputConfigName = args[1];
			finalConfigName = args[2];
		} else{
			System.out.println("Wrong number of arguments");	
			System.exit (0);
		}
		
        if (option.equals("-od")) debugModeStandard=true;
        if (option.equals("-oddetail")){
        	debugModeDetail=true;
        	debugModeStandard=true;
        }
        
        Board2 my_board;
        
        my_board = board_setup(inputConfigName);
        
Goal=getGoal(finalConfigName);
 if (debugModeDetail) System.out.println("****You are entering debug Mode Detail *******");
 else
 if (debugModeStandard){ System.out.println("****You are entering debug Mode Standard *******");
 }
      Board2 solvedBoard=Solve(my_board);
      myStack=new Stack<Board2>();
      if (debugModeStandard) {
    	  System.out.println("The board you will solved is :");
    	  my_board.displayBoard();
    	  System.out.println("The Goal board is :");
    	  goalBoard.displayBoard();
      }
     if (solvedBoard==null) System.exit(1);
     else while (solvedBoard!=null){
    	 myStack.push(solvedBoard);
    	 solvedBoard=solvedBoard.popBoard();
     }
     while (!myStack.empty()){
    	Board2 boa=myStack.pop();
    	if (boa.popMove()!="")
    	System.out.println(boa.popMove());
    	
     }
	}
}

	

