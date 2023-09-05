package lab01;

import java.awt.*;

public class ArrayPointList implements PointList{
    private final Point[] points;
    private int cursor;
    private int size;


    public ArrayPointList(int size){
        this.points = new Point[size];
        this.cursor = -1;
        this.size = 0;
    }
    public ArrayPointList(){
        this(MAX_SIZE);
    }

    // Method to add a new Point to the end of the list
    @Override
    public void append(Point newPoint){
        if(isFull()){
            throw new IllegalStateException("list is full");
        }
        points[size] = newPoint;
        cursor = size;
        size++;
    }

    // Method to clear the list (remove all elements)
    @Override
    public void clear() {
        size = 0;
        cursor = -1;
    }

    // Method to check if the list is empty
    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    // Method to check if the list is full
    @Override
    public boolean isFull() {
        return (size == points.length);
    }

    // Method to move the cursor to the beginning of the list
    @Override
    public boolean goToBeginning() {
        if (isEmpty())return false;
        else {
            cursor = 0;
            return true;
        }
    }

    // Method to move the cursor to the end of the list
    @Override
    public boolean goToEnd() {
        if (isEmpty())return false;
        else {
            cursor = size-1;
            return true;
        }
    }

    // Method to move the cursor to the next element in the list
    @Override
    public boolean goToNext() {
        if (cursor == size-1)return false;
        cursor++;
        return true;
    }

    // Method to move the cursor to the previous element in the list
    @Override
    public boolean goToPrior() {
        if (cursor == 0)return false;
        cursor--;
        return true;
    }

    // Method to get the Point object at the cursor position
    @Override
    public Point getCursor() {
        if (isEmpty())return null;
        return (Point) points[cursor].clone();
    }

    // Method to represent the Point at the cursor position as a string
    public String stringCursor(){
        return "(" + (int)points[cursor].getX()+" ,"+(int)points[cursor].getY()+")";
    }
    @Override

    // Method to generate a string representation of the entire list
    public String toString(){
        if (isEmpty())return "Empty list";
        String out = "[";
        for (Point p: points){
            out += p;
        }
        out += "]";
        return out;
    }
}
