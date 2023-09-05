import java.util.Scanner;

public class Prism extends PrismPyramid{
    public Prism(Shape baseShape, double baseZ,double z){
        this.baseShape = baseShape;
        this.baseZ = baseZ;
        this.z = z;
    }
    public Prism(String type, String params,double baseZ, double z){
        Scanner param = new Scanner(params);
        param.useDelimiter(",");
        if (type.equals("C")){
            this.baseShape = new Circle(new Point(param.nextDouble(),param.nextDouble()) , param.nextDouble());
        }
        else {
            this.baseShape = new Rectangle(new Point(param.nextDouble(), param.nextDouble()), param.nextDouble(), param.nextDouble());
        }
        this.baseZ = baseZ;
        this.z = z;
    }

    @Override
    public double volume() {
        return this.baseShape.area()*Math.abs(this.baseZ-this.z);
    }

    @Override
    public String toString() {
        return "Prism: Base shape="+this.baseShape +". z-values for bases=" + HW3Utils.formatDouble(Math.min(this.baseZ,this.z)) + ", "+HW3Utils.formatDouble(Math.max(this.baseZ,this.z));
    }
    @Override
    public int hashCode() {
        return (int) (31 * (this.baseShape.hashCode() * this.baseZ * this.z));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Prism that) {
            return this.baseShape.equals(that.baseShape) && HW3Utils.areEqual(this.baseZ, that.baseZ) && HW3Utils.areEqual(this.z, that.z);
        } else {
            return false;
        }
    }
}
