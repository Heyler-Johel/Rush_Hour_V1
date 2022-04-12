package AStar;

public class Vehicle implements IPrototype<Vehicle> {

    	public int x, y, size;
	String orientation;
        public String color;
	
	public Vehicle(int x, int y, String orientation, int size, String color) {
		super();
		this.x = x;
		this.y = y;
		this.size = size;
		this.orientation = orientation;
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
		return orientation.equals("h");
	}

	public boolean isVertical() {
		return orientation.equals("v");
	}
	
    @Override
    public Vehicle clone(){

            return new Vehicle(this.x, this.y, this.orientation, this.size, this.color);
    }

    @Override
    public Vehicle deepClone() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
