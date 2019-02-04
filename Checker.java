public class Checker {

    private int color;
    public Coords position;

    public Checker(int x, int y)
    {
        position = new Coords(x, y);
        color = Color.Empty;
    }

    public Checker(int color, int x, int y) {
        this.color = color;
        position = new Coords(x, y);
    }

    public Checker(int color, Coords position)
    {
        this.color = color;
        this.position = position;
    }

    public void swap()
    {
        color = color == Color.White ? Color.Black : Color.White;
    }

    public char getItem()
    {
        switch (color)
        {
            case Color.Empty:
                return  '_';
            case Color.Black:
                return 'B';
            case Color.White:
                return 'W';
        }
        return 'E';
    }

    public int getColor()
    {
        return color;
    }

    public void setColor(int color)
    {
        this.color = color;
    }

    public int getX()
    {
        return position.x;
    }

    public int getY()
    {
        return position.y;
    }
}
