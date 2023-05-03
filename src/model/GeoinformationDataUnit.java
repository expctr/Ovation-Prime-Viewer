/**
 * В данном файле содержится реализация единицы геоинформационных данных.
 */

package model;

import view.PointF;
import view.RadiusVector;

import java.awt.*;
import java.util.Arrays;

/**
 * Единица геоинформационных данных.
 *
 * @author Иван Шагурин
 */
public class GeoinformationDataUnit {
    /**
     * Первый граничный цвет для цветового индикатора.
     */
    private final static Color FIRST_COLOR = new Color(255, 0, 0);

    /**
     * Второй граничный цвет для цветового индикатора.
     */
    private final static Color SECOND_COLOR = new Color(255, 165, 0);

    /**
     * Третий граничный цвет для цветового индикатора.
     */
    private final static Color THIRD_COLOR = new Color(255, 255, 0);

    /**
     * Четвертый граничный цвет для цветового индикатора.
     */
    private final static Color FOURTH_COLOR = new Color(0, 255, 0);

    /**
     * Пятый граничный цвет для цветового индикатора.
     */
    private final static Color FIFTH_COLOR = new Color(0, 0, 255);

    /**
     * Шестой граничный цвет для цветового индикатора.
     */
    private final static Color SIXTH_COLOR = new Color(100, 0, 100);

    /**
     * Седьмой граничный цвет для цветового индикатора.
     */
    private final static Color SEVENTH_COLOR = new Color(0, 0, 0);

    /**
     * Значение единицы геоинформационных данных, которое соответствует
     * первому граничному цвету для цветового индикатора.
     */
    public static float maxValue = 1.5f;

    /**
     * Полярный угол единицы геоинформационных данных.
     */
    private final float polarAngle;

    /**
     * Полярное расстояние единицы геоинформационных данных.
     */
    private final float polarDistance;

    /**
     * Значение единицы геоинформационных данных.
     */
    private final float value;

    /**
     * Конструктор.
     *
     * @param polarAngle полярный угол.
     * @param polarDistance полярное расстояние.
     * @param value значение.
     */
    public GeoinformationDataUnit(
            float polarAngle,
            float polarDistance,
            float value
    ) {
        this.polarAngle = polarAngle;
        this.polarDistance = polarDistance;
        this.value = value;
    }

    /**
     * Получаем координаты единицы геоинформационных данных.
     *
     * @return упомянутые координаты.
     */
    public GeoinformationDataUnitCoordinates getCoordinates() {
        return new GeoinformationDataUnitCoordinates(
                polarAngle,
                polarDistance
        );
    }

    /**
     * Получаем полярный угол.
     *
     * @return полярный угол.
     */
    public float getPolarAngle() {
        return polarAngle;
    }

    /**
     * Получаем полярный угол в радианах.
     *
     * @return полярный угол в радианах.
     */
    public float getStandardPolarAngle() {
        return (float)((polarAngle - 6) * Math.PI / 12);
    }

    /**
     * Получаем полярное расстояние.
     *
     * @return полярное расстояние.
     */
    public float getPolarDistance() {
        return polarDistance;
    }

    /**
     * Получаем стандартное полярное расстояние.
     *
     * @param displayWidth ширина экрана.
     * @return стандартное полярное расстояние.
     */
    public float getStandardPolarDistance(int displayWidth) {
        return (90 - polarDistance) / 80 * displayWidth;
    }

    /**
     * Получаем стандартную координату x.
     *
     * @param displayWidth ширина экрана.
     * @return стандартная координата x.
     */
    public float getStandardX(int displayWidth) {
        return (float)(getStandardPolarDistance(displayWidth)
                * Math.cos(getStandardPolarAngle()));
    }

    /**
     * Получаем стандартную координату y.
     *
     * @param displayWidth ширина экрана.
     * @return стандартная координата y.
     */
    public float getStandardY(int displayWidth) {
        return (float)(getStandardPolarDistance(displayWidth)
                * Math.sin(getStandardPolarAngle()));
    }

    /**
     * Получаем значение.
     *
     * @return значение.
     */
    public float getValue() {
        return value;
    }

    /**
     * Получаем цвет.
     *
     * @return цвет.
     */
    public Color getColor() {
        return getColor(value);
    }

    /**
     * Получаем первую вершину четырехугольника, который
     * приближает изображение единицы геоинформационных данных.
     *
     * @param displayWidth ширина дисплея.
     * @return упомянутая вершина.
     */
    public PointF getFirstPoint(int displayWidth) {
        float x1 = getStandardX(displayWidth);
        float y1 = getStandardY(displayWidth);

        return new PointF(x1, y1);
    }

    /**
     * Получаем вторую вершину четырехугольника, который
     * приближает изображение единицы геоинформационных данных.
     *
     * @param displayWidth ширина дисплея.
     * @return упомянутая вершина.
     */
    public PointF getSecondPoint(int displayWidth) {
        PointF firstPoint = getFirstPoint(displayWidth);
        RadiusVector radiusVector
                = new RadiusVector(0, 0, firstPoint.x, firstPoint.y);
        radiusVector.applyRotation(Math.PI / 48);

        float x2 = radiusVector.getArrowheadX();
        float y2 = radiusVector.getArrowheadY();

        return new PointF(x2, y2);
    }

    /**
     * Получаем третью вершину четырехугольника, который
     * приближает изображение единицы геоинформационных данных.
     *
     * @param displayWidth ширина дисплея.
     * @return упомянутая вершина.
     */
    public PointF getThirdPoint(int displayWidth) {
        PointF secondPoint = getSecondPoint(displayWidth);
        RadiusVector radiusVector
                = new RadiusVector(0, 0, secondPoint.x, secondPoint.y);

        double l0 = displayWidth / 160f;
        double ratio = (radiusVector.getLength() - l0) / radiusVector.getLength();

        radiusVector.applyHomothety(ratio);

        float x3 = radiusVector.getArrowheadX();
        float y3 = radiusVector.getArrowheadY();

        return new PointF(x3, y3);
    }

    /**
     * Получаем четвертую вершину четырехугольника, который
     * приближает изображение единицы геоинформационных данных.
     *
     * @param displayWidth ширина дисплея.
     * @return упомянутая вершина.
     */
    public PointF getFourthPoint(int displayWidth) {
        PointF thirdPoint = getThirdPoint(displayWidth);
        RadiusVector radiusVector
                = new RadiusVector(0, 0, thirdPoint.x, thirdPoint.y);

        radiusVector.applyRotation(-Math.PI / 48);

        float x4 = radiusVector.getArrowheadX();
        float y4 = radiusVector.getArrowheadY();

        return new PointF(x4, y4);
    }

    /**
     * Получаем цвет, который соответсвтует указанному значению.
     *
     * @param value указанное значение.
     * @return цвет, который соответсвтует указанному значению.
     */
    public static Color getColor(double value) {
        if (value > maxValue) {
            return FIRST_COLOR;
        } else if (value > maxValue / 3 * 2.5) {
            return getColorGradient(
                    maxValue / 3 * 2.5, SECOND_COLOR,
                    maxValue, FIRST_COLOR,
                    value
            );
        } else if (value > maxValue / 3 * 2) {
            return getColorGradient(
                    maxValue / 3 * 2, THIRD_COLOR,
                    maxValue / 3 * 2.5, SECOND_COLOR,
                    value
            );
        } else if (value > maxValue / 3 * 1.5) {
            return getColorGradient(
                    maxValue / 3 * 1.5, FOURTH_COLOR,
                    maxValue / 3 * 2, THIRD_COLOR,
                    value
            );
        } else if (value > maxValue / 3) {
            return getColorGradient(
                    maxValue / 3, FIFTH_COLOR,
                    maxValue / 3 * 1.5, FOURTH_COLOR,
                    value
            );
        } else if (value > maxValue / 3 * 0.05) {
            return getColorGradient(
                    maxValue / 3 * 0.05, SIXTH_COLOR,
                    maxValue / 3, FIFTH_COLOR,
                    value
            );
        } else if (value > 0) {
            return getColorGradient(
                    0, SEVENTH_COLOR,
                    maxValue / 3 * 0.05, SIXTH_COLOR,
                    value
            );
        } else {
            return SEVENTH_COLOR;
        }
    }

    /**
     * Имеется отрезок на оси действительных чисел. Его концам
     * соответствуют цвета. Также имеется точка. Мы возвращаем
     * градиентный цвет, который определяется отношением, в котором
     * указанная точка делит указанный отрезок.
     *
     * @param leftBound левая граница отрезка.
     * @param leftBoundColor цвет левой границы отрезка.
     * @param rightBound правая граница отрезка.
     * @param rightBoundColor цвет правой границы отрезка.
     * @param intermediateValue точка на отрезке.
     * @return градиентный цвет, который соответствует точке на отрезке.
     */
    private static Color getColorGradient(
            double leftBound, Color leftBoundColor,
            double rightBound, Color rightBoundColor,
            double intermediateValue
    ) {
        if (intermediateValue <= leftBound) {
            return leftBoundColor;
        } else if (intermediateValue >= rightBound) {
            return rightBoundColor;
        }

        double lambda = (intermediateValue - leftBound)
                / (rightBound - intermediateValue);

        double red = (leftBoundColor.getRed() + lambda * rightBoundColor.getRed())
                / (1 + lambda);
        double green = (leftBoundColor.getGreen() + lambda * rightBoundColor.getGreen())
                / (1 + lambda);
        double blue = (leftBoundColor.getBlue() + lambda * rightBoundColor.getBlue())
                / (1 + lambda);

        return new Color((int)red, (int)green, (int)blue);
    }

    /**
     * Получаем единицу геоинформационных данных по ее
     * строковому представлению.
     *
     * @param str строковое представление единицы геоинформационных данных.
     * @return полученная единица геоинформационных данных.
     */
    public static GeoinformationDataUnit parse(String str) {
        String[] splitResult = Arrays.stream(str.split(" ")).filter(str_ -> !str_.isEmpty()).toArray(String[]::new);

        if (splitResult.length < 3) {
            throw new IllegalArgumentException();
        }

        float polarAngle = Float.parseFloat(splitResult[0]);
        float polarDistance = Float.parseFloat(splitResult[1]);
        float value = Float.parseFloat(splitResult[2]);

        return new GeoinformationDataUnit(polarAngle, polarDistance, value);
    }
}
