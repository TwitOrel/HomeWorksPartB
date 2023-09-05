public abstract class PrismPyramid implements Comparable<PrismPyramid>{
    Shape baseShape;
    double baseZ;
    double z;
    /** @return the Height of the body */
    public double getHeight(){
        return Math.abs(this.z-this.baseZ);
    }
    /** @return the BaseShape of the body */
    public Shape getBaseShape(){
        return this.baseShape;
    }
    @Override
    public int compareTo(PrismPyramid that){
        return Double.compare(this.getHeight(), that.getHeight());
    }
    /** @return the volume of the body */
    public abstract double volume();
    /** @return the body */
    public abstract String toString();
    /** @return if 2 bodies are the same body*/
    public abstract boolean equals(Object o);
}


