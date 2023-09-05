import java.util.Objects;
import java.util.Scanner;

public class Circle implements Shape {
    private Point center;
    private final double radius;
    public Circle(){
        this.center = new Point(0,0);
        this.radius = 1;
    }
    public Circle(Point center,double radius){
        this.center = center.copy();
        this.radius = radius;
    }

    // Constructor that parses a string to create a circle.
    public Circle(String details){
        Scanner build = new Scanner(details);
        build.useDelimiter(",");
        this.center = new Point(build.nextDouble(),build.nextDouble());
        this.radius = build.nextDouble();
    }

    // Override the equals method to check if two circles are equal based on their center and radius.
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Circle circle = (Circle) o;
        return Double.compare(circle.radius, radius) == 0 && center.equals(circle.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius);
    }
    public String toString(){
        return "Circle [radius=" + HW3Utils.formatDouble(this.radius) + ", center=" + this.center + "]";
    }

    @Override
    public double area() {
        return (Math.PI*this.radius*this.radius);
    }
    @Override
    public double perimeter() {
        return (Math.PI*2*this.radius);
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
