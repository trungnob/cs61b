
public class BlockShape {
	private int length;
	private int width;
	public BlockShape(){
		length=0;
		width=0;
	}
	public BlockShape(int l, int w){
		length=l;
		width=w;
	}
	public boolean equals(Object o){
		return (((BlockShape)o).length==this.length && ((BlockShape)o).width==this.width); 
		
	}
	public int getLength(){
	return length;	
	}
	public int getWidth(){
		return width;
		
	}
}
