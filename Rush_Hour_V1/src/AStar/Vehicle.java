package AStar;

public class Vehicle {
    private int id;
    private boolean horizontal;
    private String color;

    public Vehicle(int pId, boolean pHorizontal, String pColor){
        this.id = pId;
        this.horizontal = pHorizontal;
        this.color = pColor;
    }

    public int getId() {
        return id;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public String getColor() {
        return color;
    }
}
