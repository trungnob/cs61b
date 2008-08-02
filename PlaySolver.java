import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;


public class PlaySolver {
	
	
	public static void main (String [ ] args)  throws IOException{
		
		String inputfileName = "", outputfileName = "";
		
        if (args.length == 2) {
        	inputfileName = args[0];
        	outputfileName = args[1];
		} else if (args.length == 3){
			inputfileName = args[1];
			outputfileName = args[2];
		} else{
			System.out.println("Wrong number of arguments");
			System.exit (0);
		}
		
		BufferedReader reader = null;
		//BufferedReader writer = null;
		try {
			reader = new BufferedReader (new FileReader (inputfileName));
		} catch (Exception e) {
			System.err.println ("Couldn't access file!");
			System.exit (1);
		}
		String line = null;
		line = reader.readLine();
		StringTokenizer tokens = new StringTokenizer (line, " ");
		String [ ] token_array = new String [tokens.countTokens ( )];
		token_array[0] = (String) tokens.nextElement ( );
		token_array[1] = (String) tokens.nextElement ( );
		
		int row = Integer.parseInt(token_array[0]);
		int col = Integer.parseInt(token_array[1]);
		
		Solver s0= new Solver(row, col);
		
		while ((line=reader.readLine()) != null) {
			tokens = new StringTokenizer (line, " ");
			token_array = new String [tokens.countTokens ( )];
			int block_len = Integer.parseInt((String) tokens.nextElement ( ));
			int block_wid = Integer.parseInt((String) tokens.nextElement ( ));
			int block_row = Integer.parseInt((String) tokens.nextElement ( ));
			int block_col = Integer.parseInt((String) tokens.nextElement ( ));
			s0.adding_block(block_len, block_wid, block_row, block_col);
		}
		
		s0.displayBoard();
		
		reader.close();  
        //writer.close();
	}
}
