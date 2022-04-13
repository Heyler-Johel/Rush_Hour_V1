package AStar;

public class Vehicle implements IPrototype<Vehicle> {

    	public int x, y, size;
	boolean horizontal;
        public String color;
	
	public Vehicle(int x, int y, boolean horizontal, int size, String color) {
            super();
            this.x = x;
            this.y = y;
            this.size = size;
            this.horizontal = horizontal;
            this.color = color;
	}
	
	public void moveDown() {
		this.x ++;
	}

	public void moveUp() {
		this.x --;
	}

	public void moveRight() {
		this.y ++;
	}

	public void moveLeft() {
		this.y --;
	}

	public boolean isHorizontal() {
		return horizontal;
	}

	public boolean isVertical() {
		return !horizontal;
	}
	
    @Override
    public Vehicle clone(){

            return new Vehicle(this.x, this.y, this.horizontal, this.size, this.color);
    }

    @Override
    public Vehicle deepClone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
