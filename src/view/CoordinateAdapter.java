/*
 * В данном файле содержится класс для преобразования координат.
 */

package view;

/**
 * Класс для преобразования координат.
 *
 * @author Иван Шагурин
 */
public class CoordinateAdapter {
    /**
     * Дисплей.
     */
    private Display display;

    /**
     * Координата x в системе отсчета компоненты для рисования.
     */
    private int xInComponent = 0;

    /**
     * Координата y в системе отсчета компоненты для рисования.
     */
    private int yInComponent = 0;

    /**
     * Первый адаптер координат.
     */
    public static CoordinateAdapter firstCoordinateAdapter = new CoordinateAdapter();

    /**
     * Второй адаптер координат.
     */
    public static CoordinateAdapter secondCoordinateAdapter = new CoordinateAdapter();

    /**
     * Третий адаптер координат.
     */
    public static CoordinateAdapter thirdCoordinateAdapter = new CoordinateAdapter();

    /**
     * Четвертый адаптер координат.
     */
    public static CoordinateAdapter fourthCoordinateAdapter = new CoordinateAdapter();

    /**
     * Конструктор.
     */
    private CoordinateAdapter() {

    }

    /**
     * Устанавливаем дисплей.
     *
     * @param display упомянутый дисплей.
     */
    public void setDisplay(Display display) {
        this.display = display;
    }

    /**
     * Устанавливаем коордианты.
     *
     * @param x координата x.
     * @param y координата y.
     * @param coordinateSystem система отсчета, в которой заданы упомянутые координаты.
     */
    public void set(int x, int y, CoordinateSystem coordinateSystem) {
        if (coordinateSystem == CoordinateSystem.COMPONENT) {
            xInComponent = x;
            yInComponent = y;
        } else if (coordinateSystem == CoordinateSystem.DISPLAY) {
            xInComponent = x + display.getOffsetX();
            yInComponent = -y + display.getOffsetY();
        } else {
            xInComponent = 0;
            yInComponent = 0;
        }
    }

    /**
     * Получаем координату x в заданной системе отсчета.
     *
     * @param coordinateSystem упомянутая система отсчета.
     * @return упомянутая координата x.
     */
    public int getX(CoordinateSystem coordinateSystem) {
        if (coordinateSystem == CoordinateSystem.COMPONENT) {
            return xInComponent;
        } else if (coordinateSystem == CoordinateSystem.DISPLAY) {
            return xInComponent - display.getOffsetX();
        } else {
            return 0;
        }
    }

    /**
     * Получаем координату y в заданной системе отсчета.
     *
     * @param coordinateSystem упомянутая система отсчета.
     * @return упомянутая координата y.
     */
    public int getY(CoordinateSystem coordinateSystem) {
        if (coordinateSystem == CoordinateSystem.COMPONENT) {
            return yInComponent;
        } else if (coordinateSystem == CoordinateSystem.DISPLAY) {
            return -yInComponent + display.getOffsetY();
        } else {
            return 0;
        }
    }
}
