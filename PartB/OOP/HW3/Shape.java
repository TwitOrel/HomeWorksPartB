public interface Shape {
    /** @return the area of the shape */
    public double area();
    /** @return the perimeter of the shape */
    public double perimeter();
    /** @return the center point of the shape */
    public Point getCenter();
    /** Set the center point of the shape */
    public void setCenter(Point p);
}
