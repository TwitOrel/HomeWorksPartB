import java.util.Objects;
import java.util.Scanner;

public class Rectangle implements Shape {
    private Point center;
    private final double lengthVertical;
    private final double lengthHorizontal;

    public Rectangle(){
        this(new Point(0,0),1,1);
    }
    public Rectangle(Point center,double lengthVertical, double lengthHorizontal){
        this.center = center.copy();
        this.lengthVertical = lengthVertical;
        this.lengthHorizontal = lengthHorizontal;
    }

    // Constructor that parses a string to create a rectangle.
    public Rectangle(String details){
        Scanner use = new Scanner(details);
        use.useDelimiter("[,]");
        this.center = new Point(use.nextDouble(), use.nextDouble());
        this.lengthVertical = use.nextDouble();
        this.lengthHorizontal = use.nextDouble();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return Double.compare(rectangle.lengthVertical, lengthVertical) == 0 && Double.compare(rectangle.lengthHorizontal, lengthHorizontal) == 0 && center.equals(rectangle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, lengthVertical, lengthHorizontal);
    }

    @Override
    public String toString() {
        return "Rectangle [" + "length vertical edge=" + HW3Utils.formatDouble(this.lengthVertical) +", "+
                "length horizontal edge=" + HW3Utils.formatDouble(this.lengthHorizontal) +", "+
                "center="+ this.center + "]";
    }
    @Override
    public double area() {
        return (this.lengthHorizontal*this.lengthVertical);
    }
    @Override
    public double perimeter() {
        return (2*this.lengthVertical + 2*this.lengthHorizontal);
    }
    @Override
    public Point getCenter() {
        return this.center.copy();
    }
    @Override
    public void setCenter(Point p) {
        this.center = new Point(p.getX(),p.getY());
    }
}
