
public class trayCells {
int[][] trayMap;
byte totalCols;
public trayCells(byte rows, byte cols){
	trayMap=new int[rows][cols/32+1];
	totalCols=cols;
}
public int generateBitMap(byte j){
	return 1<<(31-(j%32));
}
public void set(byte i, byte j){
	
	trayMap[i][j/32]|=generateBitMap(j);
}
public void clear(byte i, byte j){
	trayMap[i][j/32]&=(~(generateBitMap(j)));
}

public boolean getCells(byte i,byte j){
	boolean result=false;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=false; else result=true;
	return result;
}
public int getCellsInt(byte i,byte j){
	int result=0;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=0; else result=1;
	return result;
}
public byte getCols(){
	return totalCols;
}
public byte getRows(){
	
	return (byte)trayMap.length;
}
public void print(){
	for (byte i =0 ; i<trayMap.length;i++){
		for (byte j=0;j<totalCols;j++){
			System.out.print(getCellsInt(i,j)+" ");
		}
		System.out.println();
	}
}
public boolean equals(Object o){
	trayCells tc= (trayCells) o;
	for (byte i =0 ; i<trayMap.length;i++)
		 for (byte j=0;j<trayMap[0].length;j++) 
			 if (this.trayMap[i][j]!=tc.trayMap[i][j]) return false;
	return true;
}
public Object clone(){
	trayCells my_clone=new trayCells(this.getRows(),this.getCols());
	for (int i =0 ; i<trayMap.length;i++)
	 for (int j=0;j<trayMap[0].length;j++) 
		 my_clone.trayMap[i][j]=this.trayMap[i][j];
		 
	return my_clone;
}

}