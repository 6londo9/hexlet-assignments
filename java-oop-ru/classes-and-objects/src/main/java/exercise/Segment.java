package exercise;

// BEGIN
public class Segment {

    private Point point1;
    private Point point2;

    public Segment(Point point1, Point point2) {
        this.point1 = point1;
        this.point2 = point2;
    }

    public Point getBeginPoint() {
        return point1;
    }

    public Point getEndPoint() {
        return point2;
    }

    public Point getMidPoint() {
        Integer midPointX = (this.point1.getX() + this.point2.getX()) / 2;
        Integer midPointY = (this.point1.getY() + this.point2.getY()) / 2;
        return new Point(midPointX, midPointY);
    }
}
// END
