import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class Solver {
	static ArrayList<Board> tray = new ArrayList<Board> ();
	static HashMap<String,Board> trayMap = new HashMap<String,Board>();
	static HashSet<Board> traySet = new HashSet<Board>(100,1);
	static ArrayList<block> Goal =new ArrayList<block>(); 
	static int globalCount;
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

		StringTokenizer tokens = new StringTokenizer (" ");
		
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
    
	public static int nextMove(Board brd){
		int result=0;
		brd.constructListofMovableBlocks();
		Board boardToAdd=(Board)brd.clone();
		for (Iterator<block> iter=brd.movableBlockUp.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveUp(b)){ 
			//System.out.println(b.getID()+" U");
				if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return -1;
				}
				boardToAdd=(Board)brd.clone();
				if (!tray.contains(boardToAdd)){
					tray.add(boardToAdd);
		    		result=1;
				}
				brd.MoveDown(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong Up");
			
		}
		for (Iterator<block> iter=brd.movableBlockDown.iterator();iter.hasNext(); ){
			block b=iter.next();
			if(brd.MoveDown(b)){
				if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return -1;
				}
			//System.out.println(b.getID()+" D");
				boardToAdd=(Board)brd.clone();
				brd.compareToGoal(Goal);
				if (!tray.contains(boardToAdd)){
					tray.add(boardToAdd);
					result=1;
				}
				brd.MoveUp(b);
			}
			else 
				System.out.println(b.getID()+ " Something Wrong Down");
		}
		for (Iterator<block> iter=brd.movableBlockLeft.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveLeft(b)){
				if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return -1;
				}
			//System.out.println(b.getID()+" L");
				boardToAdd=(Board)brd.clone();
				if (!tray.contains(boardToAdd)){
					tray.add(boardToAdd);
					result=1;
				}
				brd.MoveRight(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong L");
		}
		for (Iterator<block> iter=brd.movableBlockRight.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveRight(b)){
				//System.out.println(b.getID()+" R");
				if (brd.compareToGoal(Goal)){
				//	brd.displayBoard();
					return -1;
				}
				boardToAdd=(Board)brd.clone();
				if (!tray.contains(boardToAdd)){
					tray.add(boardToAdd);
					result=1;
				}
				brd.MoveLeft(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong R");
		}
		
		return result;
		
	}
	
	public static int nextMove3(Board brd){
		globalCount=0;
		int result=0;
		long startTime = System.nanoTime();
		brd.constructListofMovableBlocks();
	
		Board boardToAdd=(Board)brd.clone();
		for (Iterator<block> iter=brd.movableBlockUp.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveUp(b)){ 
			//System.out.println(b.getID()+" U");
				if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return -1;
				}
			boardToAdd=(Board)brd.clone();
			if (traySet.contains(boardToAdd)){
				
//			     System.out.println("no day ne "+tray.indexOf(boardToAdd)+" "+tray.size());
//			     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//			     System.out.println("*******************end*******************");
			}
			else{
		    traySet.add(boardToAdd);
		    globalCount=1;
		    tray.add(boardToAdd);
		    //System.out.println("This board will be add in!");
		    result=1;
		    //brd.displayBoard();
			}
			
			brd.MoveDown(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong Up");
			
		}
		for (Iterator<block> iter=brd.movableBlockDown.iterator();iter.hasNext(); ){
			block b=iter.next();
			if(brd.MoveDown(b)){
				//if (brd.compareToGoal(Goal)){
				//	brd.displayBoard();
				//	return -1;
			//	}
			//System.out.println(b.getID()+" D");
			boardToAdd=(Board)brd.clone();
			//brd.compareToGoal(Goal);
			if (traySet.contains(boardToAdd)){
//			     System.out.println("no day ne "+tray.indexOf(boardToAdd));
//			     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//			     System.out.println("*******************end*******************");
			}
			else{
		    traySet.add(boardToAdd);
		    globalCount=1;
		    tray.add(boardToAdd);
		    result=1;
		    //System.out.println("This board will be add in!");
		    //brd.displayBoard();
		    
			}
			
			brd.MoveUp(b);
			}
			else 
				System.out.println(b.getID()+ " Something Wrong Down");
		}
		for (Iterator<block> iter=brd.movableBlockLeft.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveLeft(b)){
//				if (brd.compareToGoal(Goal)){
//					brd.displayBoard();
//					return -1;
//				}
			//System.out.println(b.getID()+" L");
			boardToAdd=(Board)brd.clone();
			if (traySet.contains(boardToAdd)){
			     
			}
			else{
		    traySet.add(boardToAdd);
		    tray.add(boardToAdd);
		    globalCount=1;
		   // System.out.println("This board will be add in!");
		    //brd.displayBoard();
		    result=1;
			}
			
			brd.MoveRight(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong L");
		}
		for (Iterator<block> iter=brd.movableBlockRight.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveRight(b)){
				//System.out.println(b.getID()+" R");
				//if (brd.compareToGoal(Goal)){
					//brd.displayBoard();
					//return -1;
			//	}
				boardToAdd=(Board)brd.clone();
				if (traySet.contains(boardToAdd)){
//				     System.out.println("no day ne "+tray.indexOf(boardToAdd));
//				     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//				     System.out.println("*******************end*******************");
				}
				else{
			    traySet.add(boardToAdd);
			    tray.add(boardToAdd);
			    //System.out.println("This board will be add in!");
			    result=1;
			    globalCount=1;
			    //brd.displayBoard();
				}
				
				brd.MoveLeft(b);
			}
			
			else 
				System.out.println(b.getID()+" Something Wrong R");
		}
		long finishTime = System.nanoTime();
        System.out.println ("Time to Construct the Constructlist "
            + (finishTime-startTime));
		return result;
		
	}
	
	
	public static HashMap<String,Board> nextMove2(Board brd){
		int result=0;
		HashMap<String, Board> trayMap2= new HashMap<String,Board>();
		
		brd.constructListofMovableBlocks();
		Board boardToAdd=(Board)brd.clone();
		for (Iterator<block> iter=brd.movableBlockUp.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveUp(b)){ 
			//System.out.println(b.getID()+" U");
//				if (brd.compareToGoal(Goal)){
	//				brd.displayBoard();
//					return null;
//				}
			boardToAdd=(Board)brd.clone();
			if (trayMap.containsValue(boardToAdd)){
				
//			     System.out.println("no day ne "+tray.indexOf(boardToAdd)+" "+tray.size());
//			     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//			     System.out.println("*******************end*******************");
			}
			else{
		    trayMap2.put(boardToAdd.toString(), boardToAdd);
		    System.out.println("This board will be add in!");
		    result=1;
		    brd.displayBoard();
			}
			
			brd.MoveDown(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong Up");
			
		}
		for (Iterator<block> iter=brd.movableBlockDown.iterator();iter.hasNext(); ){
			block b=iter.next();
			if(brd.MoveDown(b)){
			if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return null;
				}
			//System.out.println(b.getID()+" D");
			boardToAdd=(Board)brd.clone();
		//	brd.compareToGoal(Goal);
			if (trayMap.containsValue(boardToAdd)){
//			     System.out.println("no day ne "+tray.indexOf(boardToAdd));
//			     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//			     System.out.println("*******************end*******************");
			}
			else{
				trayMap2.put(boardToAdd.toString(), boardToAdd);
		    result=1;
		    System.out.println("This board will be add in!");
		    brd.displayBoard();
		    
			}
			
			brd.MoveUp(b);
			}
			else 
				System.out.println(b.getID()+ " Something Wrong Down");
		}
		for (Iterator<block> iter=brd.movableBlockLeft.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveLeft(b)){
	if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return null;
				}
			//System.out.println(b.getID()+" L");
			boardToAdd=(Board)brd.clone();
			if (trayMap.containsValue(boardToAdd)){
			     
			}
			else{
				trayMap2.put(boardToAdd.toString(), boardToAdd);
		    System.out.println("This board will be add in!");
		    brd.displayBoard();
		    result=1;
			}
			
			brd.MoveRight(b);
			}
			else 
				System.out.println(b.getID()+" Something Wrong L");
		}
		for (Iterator<block> iter=brd.movableBlockRight.iterator();iter.hasNext(); ){
			block b=iter.next();
			if (brd.MoveRight(b)){
				//System.out.println(b.getID()+" R");
				if (brd.compareToGoal(Goal)){
					brd.displayBoard();
					return null;
				}
				boardToAdd=(Board)brd.clone();
				if (trayMap.containsValue(boardToAdd)){
//				     System.out.println("no day ne "+tray.indexOf(boardToAdd));
//				     tray.get(tray.indexOf(boardToAdd)).displayBoard();
//				     System.out.println("*******************end*******************");
				}
				else{
					trayMap2.put(boardToAdd.toString(), boardToAdd);
			   System.out.println("This board will be add in!");
			    result=1;
			    brd.displayBoard();
				}
				
				brd.MoveLeft(b);
			}
			
			else 
				System.out.println(b.getID()+" Something Wrong R");
		}
		
		return trayMap2;
		
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
		
        //Board my_board, finalboard;//board_final;
        //my_board = board_setup(inputConfigName);
        //finalboard = board_setup(finalConfigName);
//         if (play(my_board, finalboard))
//        	 System.out.print("No solution exists");
//         else
//        	 System.out.print("Solution found");
        

       
        Board my_board;//board_final;
        
        my_board = board_setup(inputConfigName);
        System.out.println();
        
Goal=getGoal(finalConfigName);
        
       System.out.println("Start to test NextMove-----------------");
      


//trayMap.put(my_board.toString(),my_board);
//tray.add(my_board);
       System.out.println(my_board.hashCode());
       nextMove3(my_board);
int sizeOfTray=tray.size();
int oldSizeOfTray=0;
int depth=0;

while(true){
    int a=0;
	
//	ArrayList<Board> closedList = new ArrayList<Board>();
//	HashMap<String, Board> closedMap=new HashMap<String,Board>();
//	for (int i=0;i<sizeOfTray;i++){
//		    Board blah=(Board)tray.get(i).clone();
//		    closedList.add(blah);
//		    closedMap.put(blah.toString(),blah);
//	}
//	tray=new ArrayList<Board>();
//	trayMap=new HashMap<String,Board>();
//	trayMap.putAll(closedMap);System.out.println("Size of tray "+tray.size() + " "+trayMap.size()+ " " +closedList.size());
  for (;oldSizeOfTray<sizeOfTray;oldSizeOfTray++){
   	    Board blah=(Board)tray.get(oldSizeOfTray).clone();
   	  //System.out.println("Yes "+oldSizeOfTray);
   	 if( nextMove3(blah)==-1) {System.out.println("Done!!" + depth+ " "+tray.size());return;}
   	a=a+globalCount; 
      }
  if (a==0) {System.out.println("No Solution!!");return;};
	  depth++;
  sizeOfTray=tray.size();
  System.out.println("Yes "+sizeOfTray + " "+depth + " "+a);
}
       
       
       
       
       
       
       
       
       
       
	//System.out.println(trayMap.size());
	
	
	
	
	
	
//       while(oldSizeOfTray!=sizeOfTray){
//    	   
//    	   for (Iterator iter = trayMap. ;oldSizeOfTray<sizeOfTray;oldSizeOfTray++){
//    	    	    Board blah=(Board)trayMap.
//    	    	
//    	    	  //System.out.println("Yes "+oldSizeOfTray);
//    	    	 if( nextMove(blah)==-1) {System.out.println("Done!!" + depth+ " "+tray.size());return;}
//    	       }
//    	 	  depth++;
//    	   sizeOfTray=tray.size();
//    	   System.out.println("Yes "+sizeOfTray + " "+depth);
//       }
       
       
       
//       int num=tray.size();
//              
//       for (int i=0;i<num;i++){
//    	  Board blah=(Board)tray.get(i).clone();
//    	  System.out.println("Yes "+i);
//    	  nextMove(blah);
//       }
//       int newnum=tray.size();
//       for (int i=num;i<newnum;i++){
//    	  Board blah=(Board)tray.get(i).clone();
//    	  System.out.println("Yes "+i);
//    	  nextMove(blah);
//       }
//        System.out.println(tray.size());
//        int newnum2=tray.size();
//        for (int i=newnum;i<newnum2;i++){
//     	  Board blah=(Board)tray.get(i).clone();
//     	  System.out.println("Yes "+i);
//     	  nextMove(blah);
//        }
//         System.out.println(tray.size());
//         int newnum3=tray.size();
//         for (int i=newnum2;i<newnum3;i++){
//      	  Board blah=(Board)tray.get(i).clone();
//      	  System.out.println("Yes "+i);
//      	  nextMove(blah);
//         }
//          System.out.println(tray.size());
//          System.out.println(tray.size());
//          int newnum4=tray.size();
//          for (int i=newnum3;i<newnum4;i++){
//       	  Board blah=(Board)tray.get(i).clone();
//       	  System.out.println("Yes "+i);
//       	  nextMove(blah);
//          }
           
           
//      block b=my_board.findBlockByID(5);
//      my_board.MoveUp(b);
//      System.out.println();
//      my_board.displayBoard();
//      Board newBoard=(Board)my_board.clone();
//      System.out.println(tray.contains(newBoard));
//      tray.add(newBoard);
//      my_board.MoveUp(b);
//      System.out.println();
//      my_board.displayBoard();
//      newBoard=(Board)my_board.clone();
//      System.out.println(tray.contains(newBoard));
//      tray.add(newBoard);
//      
//      System.out.println(tray.size());
      
	
	}
}

