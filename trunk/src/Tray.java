import java.util.ArrayList;

public class Tray {
TrayNode root;

	public Tray(){
		root.block = null;
		root.board = null;
		root.next = null;
		root.mytrays = null;
	}

	public Tray(int[][] block1, int[][] board1){
		root.block = block1;
		root.board = board1;
		root.next = null;
		root.mytrays = null;
	}
	

	private class TrayNode{
		int[][] board;
		int[][] block;
		ArrayList <Tray> mytrays;
		TrayNode next;
		
		public TrayNode(int[][]block1, int[][]board1){
			board = board1;
			block = block1;
			mytrays = null;
			next = null;
		}
		
		public TrayNode(){
			board = null;
			block = null;
			mytrays = null;
			next = null;
		}
		
	}

}
