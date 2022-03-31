package Business;

public class Vehicle {
    private int id;
    private Position initialPosition;
    private Position finalPosition;
    private boolean horizontal;
    private String color;

    public Vehicle(int pId, Position init, Position fin, boolean pHorizontal, String pColor){
        this.id = pId;
        this.initialPosition = init;
        this.finalPosition = fin;
        this.horizontal = pHorizontal;
        this.color = pColor;
    }

    public int getId() {
        return id;
    }

    public Position getInitialPosition() {
        return initialPosition;
    }

    public void setInitialPosition(Position initialPosition) {
        this.initialPosition = initialPosition;
    }

    public Position getFinalPosition() {
        return finalPosition;
    }

    public void setFinalPosition(Position finalPosition) {
        this.finalPosition = finalPosition;
    }

    public boolean isHorizontal() {
        return horizontal;
    }

    public String getColor() {
        return color;
    }

    public void forward(boolean horizontal){
        initialPosition.forward(this.horizontal);
        finalPosition.forward(this.horizontal);
    }

    public void backward(boolean horizontal){
        initialPosition.backward(this.horizontal);
        finalPosition.backward(this.horizontal);
    }
}
