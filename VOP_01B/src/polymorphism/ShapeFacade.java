package polymorphism;

public class ShapeFacade {

    private static ShapeFacade instance = null;

    public static ShapeFacade getInstance() {
        if (instance == null) {
            instance = new ShapeFacade();
        }
        return instance;
    }

    private ShapeFacade() {
    }
    
    public enum SHAPES {
        CIRCLE, ELLIPSE, RECTANGLE, SQUARE
    };

    public String getShapeInfo(SHAPES shapeType, double... parametre) {
        ShapeInterface shape = null;
        
        switch(shapeType) {
            case CIRCLE:
                shape = new Circle(parametre[0]);
                break;
            case ELLIPSE:
                shape = parametre.length > 1 ? new Ellipse(parametre[0], parametre[1]) : new Circle(parametre[0]);
                break;
            case RECTANGLE:
                shape = parametre.length > 1 ? new Rectangle(parametre[0], parametre[1]) : new Square(parametre[0]);
                break;
            case SQUARE:
                shape = new Square(parametre[0]);
                break;
        }
        return shape != null ? shape.toString() : "NULL";
    }
}
