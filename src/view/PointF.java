/*
 * В данном файле содержится реализация точки, координаты которой
 * являются числами с плавающей запятой.
 */

package view;

/**
 * Точка, координаты которой являются числа с плавающей запятой.
 *
 * @author Иван Шагурин
 */
public class PointF {
    /**
     * Точность, которая определяет равенство точек.
     */
    public static final float EPS = 0.01f;

    /**
     * Координата x.
     */
    public float x = 0;

    /**
     * Координата y.
     */
    public float y = 0;

    /**
     * Конструктор.
     *
     * @param x координата x.
     * @param y координата y.
     */
    public PointF(float x, float y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Устанавливаем приблизительное равенство между
     * данной точкой и указанным объектом.
     *
     * @param o указанный объект.
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean approximatelyEquals(Object o) {
        if (this == o) {
            return true;
        } else if (getClass() != o.getClass()) {
            return false;
        } else {
            PointF other = (PointF) o;
            return (Math.abs(x - other.x) <= EPS) && (Math.abs(y -  other.y) <= EPS);
        }
    }
}

