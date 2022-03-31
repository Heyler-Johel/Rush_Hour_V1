package Business;

public class Position {
    private int x;
    private int y;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void forward(boolean horizontal){
        if(horizontal){
            x += 1;
        }else{
            y += 1;
        }
    }

    public void backward(boolean horizontal){
        if(horizontal){
            x -= 1;
        }else{
            y -= 1;
        }
    }
}
