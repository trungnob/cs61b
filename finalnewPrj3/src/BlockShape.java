
public class BlockShape {
	private short length;
	private short width;
	public BlockShape(){
		length=0;
		width=0;
	}
	public BlockShape(short l, short w){
		length=l;
		width=w;
	}
	public boolean equals(Object o){
		return (((BlockShape)o).length==this.length && ((BlockShape)o).width==this.width); 
		
	}
	public short getLength(){
	return length;	
	}
	public short getWidth(){
		return width;
		
	}
	public boolean isOk(){
		if (length<256 && length >0 && width<256 && width>0) return true;
		else return false;
	}
}
