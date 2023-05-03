/*
 * В данном файле содержится дисплей.
 */

package view;

import java.awt.*;

/**
 * Дисплей.
 *
 * @author Иван Шагурин
 */
public class Display {
    /**
     * Коэффициент сжатия изображения по умолчанию.
     */
    private final static float DEFAULT_COMPRESSION_RATIO = 0.85f;

    /**
     * Коэффициент, который задает позицию изображения по умолчанию.
     */
    private final static float DEFAULT_POSITION_RATIO = 0.5f;

    /**
     * Минимальная ширина.
     */
    private final static int MIN_WIDTH = 495;

    /**
     * Минимальная высота.
     */
    private final static int MIN_HEIGHT = 495;

    /**
     * Максимальная ширина.
     */
    private final static int MAX_WIDTH = 1729;

    /**
     * Максмальная высота.
     */
    private final static int MAX_HEIGHT = 1729;

    /**
     * Коэффициент увеличения размеров дисплея при
     * однократной прокрутке колесика мыши.
     */
    public final static float INCREASE_SIZE_RATIO = 1.05f;

    /**
     * Коэффициент уменьшения размеров дисплея при
     * однократной прокрутке колесика мыши.
     */
    public final static float DECREASE_SIZE_RATIO = 1 / 1.05f;

    /**
     * Минимальный сдвиг по оси X.
     */
    private final static int MIN_OFFSET_X = -890;

    /**
     * Максимальный сдвиг по оси X.
     */
    private final static int MAX_OFFSET_X = 1680;

    /**
     * Минимальный сдвиг по оси Y.
     */
    private final static int MIN_OFFSET_Y = -900;

    /**
     * Максимальный сдвиг по оси Y.
     */
    private final static int MAX_OFFSET_Y = 1650;

    /**
     * Сдвиг по оси X.
     */
    private int offsetX;

    /**
     * Сдвиг по оси Y.
     */
    private int offsetY;

    /**
     * Ширина.
     */
    private int width;

    /**
     * Высота.
     */
    private int height;

    /**
     * Конструктор.
     *
     * @param visualizationComponent компонента для визуализации.
     */
    public Display(VisualizationComponent visualizationComponent) {
        adjustOffsetAndSizes(visualizationComponent);
    }

    /**
     * Настраиваем сдвиг и размер.
     *
     * @param visualizationComponent компонента для визуализации.
     */
    public void adjustOffsetAndSizes(VisualizationComponent visualizationComponent) {
        offsetX = (int) (visualizationComponent.getWidth() * DEFAULT_POSITION_RATIO);
        offsetY = (int) (visualizationComponent.getHeight() * DEFAULT_POSITION_RATIO);

        width = (int) (Math.min(
                visualizationComponent.getWidth(),
                visualizationComponent.getHeight()
        ) * DEFAULT_COMPRESSION_RATIO);
        height = (int) (Math.min(
                visualizationComponent.getWidth(),
                visualizationComponent.getHeight()
        ) * DEFAULT_COMPRESSION_RATIO);
    }

    /**
     * Получаем сдвиг по оси X.
     *
     * @return упомянутый свдиг.
     */
    public int getOffsetX() {
        return offsetX;
    }

    /**
     * Получаем сдвиг по оси Y.
     *
     * @return упомянутый сдвиг.
     */
    public int getOffsetY() {
        return offsetY;
    }

    /**
     * Получаем ширину.
     *
     * @return упомянутая ширина.
     */
    public int getWidth() {
        return width;
    }

    /**
     * Получаем высоту.
     *
     * @return упомянутая высота.
     */
    public int getHeight() {
        return height;
    }

    /**
     * Меняем сдвиг на указанные расстояния по оси X и Y.
     *
     * @param deltaX сдвиг по оси X.
     * @param deltaY сдвиг по сои Y.
     */
    public void changeOffset(int deltaX, int deltaY) {
        int newOffsetX = offsetX + deltaX;
        int newOffsetY = offsetY + deltaY;

        if (newOffsetX < MIN_OFFSET_X) {
            offsetX = MIN_OFFSET_X;
        } else if (newOffsetX > MAX_OFFSET_Y) {
            offsetX = MAX_OFFSET_X;
        } else {
            offsetX = newOffsetX;
        }

        if (newOffsetY < MIN_OFFSET_Y) {
            offsetY = MIN_OFFSET_Y;
        } else if (newOffsetY > MAX_OFFSET_Y) {
            offsetY = MAX_OFFSET_Y;
        } else {
            offsetY = newOffsetY;
        }
    }

    /**
     * Проверяем, что можно увеличить размеры.
     *
     * @return true, если размеры можно увеличить. Иначе - false.
     */
    public boolean canIncreaseSizes() {
        return (width * 1.05 <= MAX_WIDTH) && (height * 1.05 <= MAX_HEIGHT);
    }

    /**
     * Проверяем, что можно уменьшить размеры.
     *
     * @return true, если размеры можно уменьшить. Иначе - false.
     */
    public boolean canDecreaseSizes() {
        return (width / 1.05 >= MIN_WIDTH) && (height / 1.05 >= MIN_HEIGHT);
    }

    /**
     * Увеличиваем размеры.
     */
    public void increaseSizes() {
        width *= 1.05;
        height *= 1.05;
    }

    /**
     * Уменьшаем размеры.
     */
    public void decreaseSizes() {
        width /= 1.05;
        height /= 1.05;
    }

    /**
     * Закрашиваем овал.
     *
     * @param x1 координата x левой верхней вершина прямоугольника, в который вписан овал.
     * @param y1 коордианата y левой верхней вершины прямоугольника, в который вписан овал.
     * @param x2 кордината x нижней правой вершины прямоугольника, в который вписан овал.
     * @param y2 координата y нижней правой вершины прямоугольника, в который вписан овал.
     * @param color цвет овала.
     * @param graphics2D объект для рисования.
     */
    public void fillOval(int x1, int y1,
                         int x2, int y2,
                         Color color, Graphics2D graphics2D) {
        graphics2D.setPaint(color);

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.secondCoordinateAdapter.setDisplay(this);

        CoordinateAdapter.firstCoordinateAdapter.set(x1, y1, CoordinateSystem.DISPLAY);
        CoordinateAdapter.secondCoordinateAdapter.set(x2, y2, CoordinateSystem.DISPLAY);

        int width
                = CoordinateAdapter.secondCoordinateAdapter.getX(CoordinateSystem.COMPONENT)
                - CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT);
        int height
                = CoordinateAdapter.secondCoordinateAdapter.getY(CoordinateSystem.COMPONENT)
                - CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT);

        graphics2D.fillOval(
                CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                width,
                height
        );
    }

    /**
     * Рисуем овал.
     *
     * @param x1 координата x левой верхней вершина прямоугольника, в который вписан овал.
     * @param y1 коордианата y левой верхней вершины прямоугольника, в который вписан овал.
     * @param x2 кордината x нижней правой вершины прямоугольника, в который вписан овал.
     * @param y2 координата y нижней правой вершины прямоугольника, в который вписан овал.
     * @param color цвет овала.
     * @param stroke кисть.
     * @param graphics2D объект для рисования.
     */
    public void drawOval(int x1, int y1,
                         int x2, int y2,
                         Color color,
                         Stroke stroke,
                         Graphics2D graphics2D) {
        graphics2D.setPaint(color);
        graphics2D.setStroke(stroke);

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.secondCoordinateAdapter.setDisplay(this);

        CoordinateAdapter.firstCoordinateAdapter.set(x1, y1, CoordinateSystem.DISPLAY);
        CoordinateAdapter.secondCoordinateAdapter.set(x2, y2, CoordinateSystem.DISPLAY);

        int width
                = CoordinateAdapter.secondCoordinateAdapter.getX(CoordinateSystem.COMPONENT)
                - CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT);
        int height
                = CoordinateAdapter.secondCoordinateAdapter.getY(CoordinateSystem.COMPONENT)
                - CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT);

        graphics2D.drawOval(
                CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                width,
                height
        );
    }

    /**
     * Рисуем прямую линию.
     *
     * @param x1 координата x начала линии.
     * @param y1 координата y начала линии.
     * @param x2 координата x конца линии.
     * @param y2 координата y конца линии.
     * @param color увет линии.
     * @param stroke кисть.
     * @param graphics2D объект для рисования.
     */
    public void drawLine(int x1, int y1,
                         int x2, int y2,
                         Color color,
                         Stroke stroke,
                         Graphics2D graphics2D) {
        graphics2D.setPaint(color);
        graphics2D.setStroke(stroke);

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.secondCoordinateAdapter.setDisplay(this);

        CoordinateAdapter.firstCoordinateAdapter.set(x1, y1, CoordinateSystem.DISPLAY);
        CoordinateAdapter.secondCoordinateAdapter.set(x2, y2, CoordinateSystem.DISPLAY);

        graphics2D.drawLine(
                CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                CoordinateAdapter.secondCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                CoordinateAdapter.secondCoordinateAdapter.getY(CoordinateSystem.COMPONENT)
        );
    }

    /**
     * Закрашиваем четырехугольник.
     *
     * @param x1 координата x первой вершины четырехугольника.
     * @param y1 координата y первый вершины четырехугольника.
     * @param x2 координата x второй вершины четырехугольника.
     * @param y2 координата y второй вершины четырехугольника.
     * @param x3 координата x третей вершины четырехугольника.
     * @param y3 координата y третей вершины четырехугольника.
     * @param x4 координата x четвертой вершины четырехугольника.
     * @param y4 координата y четвертой вершины четырехугольника.
     * @param color цвет четырехугольника.
     * @param graphics2D объект для рисования.
     */
    public void fillTetragon(
            int x1, int y1,
            int x2, int y2,
            int x3, int y3,
            int x4, int y4,
            Color color,
            Graphics2D graphics2D
    ) {
        graphics2D.setPaint(color);

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.secondCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.thirdCoordinateAdapter.setDisplay(this);
        CoordinateAdapter.fourthCoordinateAdapter.setDisplay(this);

        CoordinateAdapter.firstCoordinateAdapter.set(x1, y1, CoordinateSystem.DISPLAY);
        CoordinateAdapter.secondCoordinateAdapter.set(x2, y2, CoordinateSystem.DISPLAY);
        CoordinateAdapter.thirdCoordinateAdapter.set(x3, y3, CoordinateSystem.DISPLAY);
        CoordinateAdapter.fourthCoordinateAdapter.set(x4, y4, CoordinateSystem.DISPLAY);

        graphics2D.fillPolygon(new int[]{
                        CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.secondCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.thirdCoordinateAdapter.getX(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.fourthCoordinateAdapter.getX(CoordinateSystem.COMPONENT)},
                new int[]{
                        CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.secondCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.thirdCoordinateAdapter.getY(CoordinateSystem.COMPONENT),
                        CoordinateAdapter.fourthCoordinateAdapter.getY(CoordinateSystem.COMPONENT)},
                4);
    }

    /**
     * Пишем строку.
     *
     * @param str текст.
     * @param x координата x.
     * @param y коордианата y.
     * @param shiftX сдвиг по оси X.
     * @param shiftY сдвиг по оси Y.
     * @param color цвет строки.
     * @param font шрифт строки.
     * @param graphics2D объект для рисования.
     */
    public void drawString(String str, int x, int y,
                     int shiftX, int shiftY,
                     Color color, Font font,
                     Graphics2D graphics2D) {
        graphics2D.setPaint(color);
        graphics2D.setFont(font);

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(this);

        CoordinateAdapter.firstCoordinateAdapter.set(x, y, CoordinateSystem.DISPLAY);

        graphics2D.drawString(str,
                CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.COMPONENT) - shiftX,
                CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.COMPONENT) - shiftY);
    }

    /**
     * Закрашиваем надпись.
     *
     * @param text текст.
     * @param x координата x.
     * @param y координата y.
     * @param labelColor цвет фона.
     * @param textColor цвет текста.
     * @param font шрифт.
     * @param graphics2D объект для рисования.
     */
    public void fillLabel(String text, double x, double y,
                          Color labelColor, Color textColor,
                          Font font,
                          Graphics2D graphics2D) {
        graphics2D.setPaint(labelColor);
        graphics2D.fillRect((int) (x + 10), (int) (y),
                graphics2D.getFontMetrics().stringWidth(text),
                font.getSize());

        graphics2D.setPaint(textColor);
        graphics2D.setFont(font);
        graphics2D.drawString(text, (float) (x + 10), (float) (y + 13));
    }
}
