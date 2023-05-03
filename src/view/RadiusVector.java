/*
 * В данном файле содержится реализация радиус-вектора.
 */

package view;

/**
 * Радиус-вектор.
 *
 * @author Иван Шагурин
 */
public class RadiusVector {
    /**
     * Конец радиус-вектора.
     */
    private PointF arrowhead = new PointF(0, 0);

    /**
     * Получаем координату x конца радиус-вектора.
     *
     * @return упомянутая координата x.
     */
    public float getArrowheadX() {
        return arrowhead.x;
    }

    /**
     * Получаем координату y конца радиус-вектора.
     *
     * @return упомянутая координата y.
     */
    public float getArrowheadY() {
        return arrowhead.y;
    }

    /**
     * Получаем длину радиус-вектора.
     *
     * @return упомянутая длина.
     */
    public double getLength() {
        return Math.sqrt(
                arrowhead.x * arrowhead.x
                        + arrowhead.y * arrowhead.y
        );
    }

    /**
     * Конструктор.
     *
     * @param xFrom координата x начала.
     * @param yFrom координата y начала.
     * @param xTo координата x конца.
     * @param yTo координата y конца.
     */
    public RadiusVector(float xFrom, float yFrom, float xTo, float yTo) {
        arrowhead = new PointF(xTo - xFrom, yTo - yFrom);
    }

    /**
     * Применяем поворот к радиус-вектору.
     *
     * @param angle угол поворота.
     */
    public void applyRotation(double angle) {
        float xRotated
                = (float) (arrowhead.x * Math.cos(angle)
                - arrowhead.y * Math.sin(angle));
        float yRotated
                = (float) (arrowhead.x * Math.sin(angle)
                + arrowhead.y * Math.cos(angle));

        arrowhead.x = xRotated;
        arrowhead.y = yRotated;
    }

    /**
     * Применяем гомотетию к радиус-вектору.
     *
     * @param ratio коэффициент гомотетии.
     */
    public void applyHomothety(double ratio) {
        arrowhead.x = (float) (arrowhead.x * ratio);
        arrowhead.y = (float) (arrowhead.y * ratio);
    }
}
