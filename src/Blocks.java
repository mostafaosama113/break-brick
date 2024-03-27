
public class Blocks {
	
	private int [] blockX , blockY ;
	private int l , counter;
	
	public Blocks(int l){
		this.l = l;
		counter = 11 * l;
		reset();
	}
	
	public int [] ReturnX() {
		return blockX;
	}
	public int [] ReturnY() {
		return blockY;
	}
	public int getL() {
		return blockX.length / 11; 
	}
	
	public void remove(int index) {
		blockX[index] = -100;
		if(counter > 0)
			counter--;
	}
	
	public void reset() {
		blockX = new int[11*l];
		blockY = new int[11*l];
		
		int tempX = 20;
		int tempY = 20;
		
		for(int i = 0 ; i < l * 11 ; i++) {
			
			
			blockX[i] = tempX;
			blockY[i] = tempY;
			
			tempX += 60;
			
			if(tempX > 620) {
				tempX = 20;
				tempY += 64;
			}
		}
		counter = 11 * l;
	}
	public int getCount() {
		return counter;
	}
	
}
