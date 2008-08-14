
public class trayCells {
int[][] trayMap;
short totalCols;
public trayCells(short rows, short cols){
	trayMap=new int[rows][cols/32+1];
	totalCols=cols;
}
public int generateBitMap(short j){
	return 1<<(31-(j%32));
}
public void set(short i, short j){
	
	trayMap[i][j/32]|=generateBitMap(j);
}
public void clear(short i, short j){
	trayMap[i][j/32]&=(~(generateBitMap(j)));
}

public boolean getCells(short i,short j){
	boolean result=false;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=false; else result=true;
	return result;
}
public int getCellsInt(short i,short j){
	int result=0;
	int bitMap=generateBitMap(j);
	if ((trayMap[i][j/32]&bitMap)==0) result=0; else result=1;
	return result;
}
public short getCols(){
	return totalCols;
}
public short getRows(){
	
	return (short)trayMap.length;
}
public void print(){
	for (short i =0 ; i<trayMap.length;i++){
		for (short j=0;j<totalCols;j++){
			System.out.print(getCellsInt(i,j)+" ");
		}
		System.out.println();
	}
}
public boolean equals(Object o){
	trayCells tc= (trayCells) o;
	for (short i =0 ; i<trayMap.length;i++)
		 for (short j=0;j<trayMap[0].length;j++) 
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
public boolean isOk(){
	if ((totalCols<256) && (totalCols>0) && (trayMap.length>0) && (trayMap.length<256) ) return true;
	else return false;
}

}