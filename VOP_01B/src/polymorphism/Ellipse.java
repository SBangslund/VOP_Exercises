package polymorphism;

public class Ellipse extends AbstractShape {

    private double r1;
    private double r2;
    
    public Ellipse(double r1, double r2) {
        this.r1 = r1;
        this.r2 = r2;
    }
    
    @Override
    public double getArea() {
        return Math.PI * r1 * r2;
    }

    @Override
    public double getCircumference() {
        return 2 * Math.PI * Math.sqrt(0.5f * (r1 * r1 + r2 * r2));
    }

    public double getR1() {
        return r1;
    }

    public double getR2() {
        return r2;
    }

    public void setR1(double r1) {
        this.r1 = r1;
    }

    public void setR2(double r2) {
        this.r2 = r2;
    }
}
