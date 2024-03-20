import javafx.scene.shape.Polygon;
/**
 * A triangle that extends the polygon class.
 * @author gordo_000
 */
class Triangle extends Polygon {
    public Triangle(double p1x, double p1y, double p2x, double p2y, double p3x, double p3y) {
        super(new double[] {p1x, p1y, p2x, p2y, p3x, p3y});
    }
}
