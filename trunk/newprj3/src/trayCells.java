
public class trayCells {
int[][] trayMap;
int totalCols;
public trayCells(int rows, int cols){
	trayMap=new int[rows][cols/32+1];
	totalCols=cols;
}
public int generateBitMap(int j){
	return 1<<(31-(j%32));
}
public void set(int i, int j){
	
	trayMap[i][j/32]|=generateBitMap(j);
}
public void clear(int i, int j){
	trayMap[i][j/32]&=(~(generateBitMap(j)));
}

public boolean getCells(int i,int j){
	boolean result=false;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=false; else result=true;
	return result;
}
public int getCellsInt(int i,int j){
	int result=0;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=0; else result=1;
	return result;
}
public int getCols(){
	return totalCols;
}
public int getRows(){
	return trayMap.length;
}
public void print(){
	for (int i =0 ; i<trayMap.length;i++){
		for (int j=0;j<totalCols;j++){
			System.out.print(getCellsInt(i,j)+" ");
		}
		System.out.println();
	}
}
public Object clone(){
	trayCells my_clone=new trayCells(this.getRows(),this.getCols());
	for (int i =0 ; i<trayMap.length;i++)
	 for (int j=0;j<trayMap[0].length;j++) 
		 my_clone.trayMap[i][j]=this.trayMap[i][j];
		 
	return my_clone;
}
public static void main(String[] args){
trayCells boardMap= new trayCells(5,4);
boardMap.print();
boardMap.set(2, 3);
boardMap.set(4, 3);
boardMap.clear(2, 3);
System.out.println();
boardMap.print();

}
}