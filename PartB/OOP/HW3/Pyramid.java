import java.util.Scanner;

public class Pyramid extends PrismPyramid{
    private final Point quardZ;
    public Pyramid(Shape baseShape,double baseZ, double x,double y,double z){
        this.baseShape = baseShape;
        this.baseZ = baseZ;
        this.quardZ = new Point(x,y);
        this.z = z;
    }
    public Pyramid(String type,String params,double baseZ, double x,double y,double z){
        Scanner param = new Scanner(params);
        param.useDelimiter(",");
        if(type.equals("C")){
            this.baseShape = new Circle(new Point(param.nextDouble(),param.nextDouble()),param.nextDouble());
        }
        else {
            this.baseShape = new Rectangle(new Point(param.nextDouble(),param.nextDouble()),param.nextDouble(),param.nextDouble());
        }
        this.baseZ = baseZ;
        this.quardZ = new Point(x,y);
        this.z = z;
    }
    public double volume(){
        return this.getBaseShape().area()*this.getHeight()/3;
    }

    @Override
    public String toString() {
        return "Pyramid: Base shape="+this.baseShape+". z-base shape ="+HW3Utils.formatDouble(this.baseZ)
                +". Apex=("+HW3Utils.formatDouble(this.quardZ.getX())
                +","+HW3Utils.formatDouble(this.quardZ.getY())
                +","+HW3Utils.formatDouble(this.z)+")";
    }
    //
    @Override
    public int hashCode() {
        return (int) (31 * (this.baseShape.hashCode() * this.baseZ * this.z));
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Pyramid that) {
            return this.baseShape.equals(that.baseShape) && HW3Utils.areEqual(this.baseZ, that.baseZ)
                    && HW3Utils.areEqual(this.z, that.z) && this.quardZ.equals(that.quardZ);
        } else {
            return false;
        }
    }

}
