/*
 * В данном файле содержится компонента для отображения
 * цветового индикатора.
 */

package view;

import model.GeoinformationDataUnit;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;

/**
 * Компонента для отображения цветового индикатора.
 *
 * @author Иван Шагурин
 */
public class ColorIndicatorComponent extends JComponent {
    /**
     * Шрифт для отображения надписей цветового индикатора.
     */
    private static final Font COLOR_INDICATOR_LABELS_FONT
            = new Font("Arial", Font.PLAIN, 12);

    public final MainFrame mainFrame;

    public ColorIndicatorComponent(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Раскрашиваем компоненту.
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;

        paintBackground(graphics2D);
        paintColorIndicator(graphics2D);
        adjustGraphics2D(graphics2D);
        paintColorIndicatorLabels(graphics2D);
    }

    /**
     * Настраиваем объект для рисования.
     *
     * @param graphics2D упомянутый объект для рисования.
     */
    private void adjustGraphics2D(Graphics2D graphics2D) {
        graphics2D.setRenderingHint(RenderingHints.KEY_ALPHA_INTERPOLATION, RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING, RenderingHints.VALUE_COLOR_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_DITHERING, RenderingHints.VALUE_DITHER_ENABLE);
        graphics2D.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_ON);
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_STROKE_CONTROL, RenderingHints.VALUE_STROKE_PURE);
    }

    /**
     * Раскрашиваем фон.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintBackground(Graphics2D graphics2D) {
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fillRect(0, 0, getWidth(), getHeight());
    }

    /**
     * Раскрашиваем цветовой индикатор.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintColorIndicator(Graphics2D graphics2D) {
        Rectangle outerRectangle = new Rectangle(
                (getWidth() - 30) / 2,
                (getHeight() - 500) / 2,
                30,
                500
        );

        int totalNumberOfInnerRectangles = 300;

        for (int i = 0; i < totalNumberOfInnerRectangles; ++i) {
            Rectangle innerRectangle = getInnerRectangle(
                    outerRectangle,
                    totalNumberOfInnerRectangles,
                    i
            );

            double ratio = (double) i / totalNumberOfInnerRectangles;
            double value = mainFrame.model.getOptions().colorIndicatorLimit * (1 - ratio);

            Color color = GeoinformationDataUnit.getColor(value, mainFrame.model.getOptions().colorIndicatorLimit);

            graphics2D.setPaint(color);
            graphics2D.fillRect(
                    (int) innerRectangle.getX(),
                    (int) innerRectangle.getY(),
                    (int) innerRectangle.getWidth(),
                    (int) innerRectangle.getHeight() * 2
            );
        }
    }

    /**
     * Получаем внутренний прямоугольник.
     *
     * @param outerRectangle внешний прямоугольник.
     * @param totalNumberOfRectangles общее число внутренний прямоугольников.
     * @param indexOfInnerRectangle индекс внутреннего прямогольника.
     * @return упомянутый прямоугольник.
     */
    private Rectangle getInnerRectangle(
            Rectangle outerRectangle,
            int totalNumberOfRectangles,
            int indexOfInnerRectangle
    ) {
        double innerRectangleHeight = outerRectangle.getHeight() / totalNumberOfRectangles;
        double innerRectangleWidth = outerRectangle.getWidth();
        double innerRectangleX = outerRectangle.getX();
        double innerRectangleY = outerRectangle.y + innerRectangleHeight * indexOfInnerRectangle;

        return new Rectangle(
                (int) innerRectangleX,
                (int) innerRectangleY,
                (int) innerRectangleWidth,
                (int) innerRectangleHeight
        );
    }

    /**
     * Раскрашиваем надписи цветового индикатора.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintColorIndicatorLabels(Graphics2D graphics2D) {
        Rectangle outerRectangle = new Rectangle(
                (getWidth() - 30) / 2,
                (getHeight() - 500) / 2,
                30,
                500
        );

        graphics2D.setPaint(Color.BLACK);
        graphics2D.setFont(COLOR_INDICATOR_LABELS_FONT);

        graphics2D.drawString(
                new DecimalFormat("#0.00").format(mainFrame.model.getOptions().colorIndicatorLimit),
                (float) (outerRectangle.x + outerRectangle.getWidth() * 1.3),
                (float) outerRectangle.y
        );

        graphics2D.drawString(
                new DecimalFormat("#0.00").format(mainFrame.model.getOptions().colorIndicatorLimit / 2),
                (float) (outerRectangle.x + outerRectangle.getWidth() * 1.3),
                outerRectangle.y + outerRectangle.height / 2f
        );

        graphics2D.drawString(
                new DecimalFormat("#0.00").format(0),
                (float) (outerRectangle.x + outerRectangle.getWidth() * 1.3),
                outerRectangle.y + outerRectangle.height
        );
    }
}
