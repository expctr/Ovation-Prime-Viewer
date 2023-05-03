/*
 * В данном файле содержится реализация компоненты для визуализации.
 */

package view;

import model.GeoinformationDataUnit;
import model.GeoinformationDataUnitCoordinates;
import model.Segment;

import javax.swing.*;
import java.awt.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Компонента для визуализации.
 */
public class VisualizationComponent extends JComponent {
    /**
     * Цвет толстых линий координатной решетки.
     */
    private static final Color THICK_GRID_LINES_COLOR = new Color(193, 207, 215);

    /**
     * Цвет тонких линий координатной решетки.
     */
    private static final Color THIN_GRID_LINES_COLOR = new Color(120, 120, 120);

    /**
     * Тонкая кисть.
     */
    private static final Stroke THIN_STROKE = new BasicStroke(0.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    /**
     * Толстая кисть.
     */
    private static final Stroke THICK_STROKE = new BasicStroke(1f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    /**
     * Цвет надписей для толстых линий координатной решетки.
     */
    private static final Color THICK_GRID_LINES_LABELS_COLOR = new Color(0, 0, 0);

    /**
     * Цвет границы.
     */
    public static final Color MARGIN_COLOR = new Color(255, 255, 255);

    /**
     * Кисть для рисования границы.
     */
    public static final Stroke MARGIN_STROKE
            = new BasicStroke(2.5f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER);

    /**
     * Шрифт надписей для толстых линий координатной решетки.
     */
    private static final Font THICK_GRID_LINES_LABELS_FONT = new Font("Arial", Font.PLAIN, 16);

    /**
     * Шрифт надписей для единиц геоинформационных данных.
     */
    private static final Font GEOINFORMATION_DATA_UNITS_LABELS_FONT = new Font("Arial", Font.PLAIN, 16);

    /**
     * Дисплей.
     */
    public Display display;

    /**
     * Главный фрейм.
     */
    private final MainFrame mainFrame;

    /**
     * Конструктор.
     *
     * @param mainFrame главный фрейм.
     */
    public VisualizationComponent(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    /**
     * Инициализиурем дисплей.
     */
    public void initDisplay() {
        display = new Display(this);
    }

    /**
     * Раскрашиваем компоненту.
     *
     * @param graphics the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics graphics) {
        Graphics2D graphics2D = (Graphics2D) graphics;
        adjustGraphics2D(graphics2D);

        paintBackground(graphics2D);

        if (mainFrame.model.showHeatmapFlag) {
            paintGeoinformationDataUnits(graphics2D);
        }

        paintThinGridCircles(graphics2D);
        paintThickGridLineCircles(graphics2D);
        paintThinGridLines(graphics2D);
        paintThickGridLines(graphics2D);

        if (mainFrame.model.showMarginFlag) {
            if (mainFrame.model.smoothMarginFlag) {
                mainFrame.model.prepareToDrawSmoothMargin();
                drawSmoothMargin(graphics2D);
            } else {
                paintMargin(graphics2D);
            }
        }

        paintThickGridLinesLabels(graphics2D);
        paintThickGridCirclesLabels(graphics2D);
        paintLabel(graphics2D);
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
     * Красим фон.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintBackground(Graphics2D graphics2D) {
        graphics2D.setPaint(Color.WHITE);
        graphics2D.fill(new Rectangle(0, 0, getWidth(), getHeight()));

        display.fillOval(
                -display.getWidth() / 2,
                display.getHeight() / 2,
                display.getWidth() / 2,
                - display.getHeight() / 2,
                Color.BLACK,
                graphics2D
        );
    }

    /**
     * Красим тонкие окружности координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintThinGridCircles(Graphics2D graphics2D) {
        float stepX = display.getWidth() / 160f;
        float stepY = display.getHeight() / 160f;

        for (int i = 0; i < 80; ++i) {
            display.drawOval(
                    (int) (-display.getWidth() / 2 + stepX * i),
                    (int) (display.getHeight() / 2 - stepY * i),
                    (int) (display.getWidth() / 2 - stepX * i),
                    (int) (-display.getHeight() / 2 + stepY * i),
                    THIN_GRID_LINES_COLOR,
                    THIN_STROKE,
                    graphics2D
            );
        }
    }

    /**
     * Красим толстые окружности координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintThickGridLineCircles(Graphics2D graphics2D) {
        float stepX = display.getWidth() / 16f;
        float stepY = display.getHeight() / 16f;

        for (int i = 0; i < 8; ++i) {
            display.drawOval(
                    (int) (-display.getWidth() / 2 + stepX * i),
                    (int) (display.getHeight() / 2 - stepY * i),
                    (int) (display.getWidth() / 2 - stepX * i),
                    (int) (-display.getHeight() / 2 + stepY * i),
                    THICK_GRID_LINES_COLOR,
                    THICK_STROKE,
                    graphics2D
            );
        }
    }

    /**
     * Красим тонкие прямые линии координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    public void paintThinGridLines(Graphics2D graphics2D) {
        RadiusVector radiusVector = new RadiusVector(
                0, 0, 0, display.getHeight() / 2f
        );
        double stepAngle = Math.PI / 48;

        for (int i = 0; i < 96; ++i) {
            display.drawLine(
                    0,
                    0,
                    (int) radiusVector.getArrowheadX(),
                    (int) radiusVector.getArrowheadY(),
                    THIN_GRID_LINES_COLOR,
                    THIN_STROKE,
                    graphics2D
            );

            radiusVector.applyRotation(stepAngle);
        }
    }

    /**
     * Красим толстые прямые линии координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    public void paintThickGridLines(Graphics2D graphics2D) {
        RadiusVector radiusVector = new RadiusVector(
                0, 0, 0, display.getHeight() / 2f
        );
        double stepAngle = Math.PI / 12;

        for (int i = 0; i < 24; ++i) {
            display.drawLine(
                    0,
                    0,
                    (int) radiusVector.getArrowheadX(),
                    (int) radiusVector.getArrowheadY(),
                    THICK_GRID_LINES_COLOR,
                    THICK_STROKE,
                    graphics2D
            );

            radiusVector.applyRotation(stepAngle);
        }
    }

    /**
     * Красим единицы геоинформационных данных.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintGeoinformationDataUnits(Graphics2D graphics2D) {
        try {
            for (GeoinformationDataUnit value : mainFrame.model.geoinformationDataUnits.values()) {
                paintGeoinformationDataUnit(
                        value,
                        graphics2D
                );
            }
        } catch (Exception ignored) {
        }
    }

    /**
     * Красим единицу геоинформационных данных.
     *
     * @param geoinformationDataUnit упомянутая единица геоинформационных данных.
     * @param graphics2D объект для рисования.
     */
    private void paintGeoinformationDataUnit(GeoinformationDataUnit geoinformationDataUnit,
                                        Graphics2D graphics2D) {
        PointF firstPoint = geoinformationDataUnit.getFirstPoint(display.getWidth());
        PointF secondPoint = geoinformationDataUnit.getSecondPoint(display.getWidth());
        PointF thirdPoint = geoinformationDataUnit.getThirdPoint(display.getWidth());
        PointF fourthPoint = geoinformationDataUnit.getFourthPoint(display.getWidth());

        float x1 = firstPoint.x;
        float y1 = firstPoint.y;
        float x2 = secondPoint.x;
        float y2 = secondPoint.y;
        float x3 = thirdPoint.x;
        float y3 = thirdPoint.y;
        float x4 = fourthPoint.x;
        float y4 = fourthPoint.y;

        display.fillTetragon((int)x1, (int)y1, (int)x2, (int)y2, (int)x3, (int)y3, (int)x4, (int)y4,
                geoinformationDataUnit.getColor(), graphics2D);
    }

    /**
     * Красим надписи для толстых прямых линий координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintThickGridLinesLabels(Graphics2D graphics2D) {
        RadiusVector radiusVector = new RadiusVector(
                0, 0, 0, (float) (-display.getHeight() / 2 * 1.05)
        );
        double stepAngle = Math.PI / 4;
        int label = 0;

        for (int i = 0; i < 8; ++i) {
            display.drawString(
                    String.valueOf(label),
                    (int) (radiusVector.getArrowheadX()),
                    (int) (radiusVector.getArrowheadY()),
                    8, -4,
                    THICK_GRID_LINES_LABELS_COLOR,
                    THICK_GRID_LINES_LABELS_FONT,
                    graphics2D
            );

            radiusVector.applyRotation(stepAngle);
            label += 3;
        }
    }

    /**
     * Красим надписи для толстых окружностей координатной решетки.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintThickGridCirclesLabels(Graphics2D graphics2D) {
        RadiusVector radiusVector = new RadiusVector(
                0, 0, 0, -display.getHeight() / 2f
        );
        radiusVector.applyRotation(Math.PI / 12);
        int label = 50;
        double l0 = (new RadiusVector(
                0, 0, display.getWidth() / 23f,
                display.getHeight() / 23f
        )).getLength();

        for (int i = 0; i < 7; ++i) {
            display.drawString(
                    String.valueOf(label),
                    (int) radiusVector.getArrowheadX(),
                    (int) radiusVector.getArrowheadY(),
                    0,
                    0,
                    THICK_GRID_LINES_COLOR,
                    THICK_GRID_LINES_LABELS_FONT,
                    graphics2D
            );

            double ratio = (radiusVector.getLength() - l0) / radiusVector.getLength();
            radiusVector.applyHomothety(ratio);

            label += 5;
        }
    }

    /**
     * Красим надпись.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintLabel(Graphics2D graphics2D) {
        if (mainFrame.model.geoinformationDataUnits.size() == 0) {
            return;
        } else if (!mainFrame.model.showInfoFlag) {
            return;
        }

        CoordinateAdapter.firstCoordinateAdapter.setDisplay(display);

        CoordinateAdapter.firstCoordinateAdapter
                .set(mainFrame.model.mouseAdapterX,
                        mainFrame.model.mouseAdapterY,
                        CoordinateSystem.COMPONENT);

        int xDisplay = CoordinateAdapter.firstCoordinateAdapter.getX(CoordinateSystem.DISPLAY);
        int yDisplay = CoordinateAdapter.firstCoordinateAdapter.getY(CoordinateSystem.DISPLAY);

        double standardPolarDistance
                = Math.sqrt(xDisplay * xDisplay + yDisplay * yDisplay);
        double standardPolarAngle = Math.atan2(yDisplay, xDisplay);

        double modifiedPolarDistance
                = 90 - standardPolarDistance / display.getWidth() * 80;
        double modifiedPolarAngle = standardPolarAngle * 12 / Math.PI + 6;

        if (modifiedPolarAngle < 0) {
            modifiedPolarAngle += 24;
        }

        modifiedPolarDistance = roundModifiedPolarDistance(modifiedPolarDistance);
        modifiedPolarAngle = roundModifiedPolarAngle(modifiedPolarAngle);

        if (modifiedPolarDistance < 50 || modifiedPolarDistance > 89.5) {
            return;
        }

        double geoinformationDataUnitValue
                = mainFrame.model.getGeoinformationDataUnitValue(
                (float) modifiedPolarDistance, (float) modifiedPolarAngle);

        DecimalFormat decimalFormat = new DecimalFormat("#0.00");

        display.fillLabel(
                modifiedPolarAngle + ", "
                + modifiedPolarDistance + ", "
                + decimalFormat.format(geoinformationDataUnitValue),
                mainFrame.model.mouseAdapterX,
                mainFrame.model.mouseAdapterY,
                Color.GRAY,
                Color.WHITE,
                GEOINFORMATION_DATA_UNITS_LABELS_FONT,
                graphics2D
        );
    }

    /**
     * Рисуем границу.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintMargin(Graphics2D graphics2D) {
        paintRadialMarginLines(graphics2D);
        paintCircleMarginLines(graphics2D);
    }

    /**
     * Рисуем сглаженную границу.
     *
     * @param graphics2D объект для рисования.
     */
    private void drawSmoothMargin(Graphics2D graphics2D) {
        for (Segment middleSegment : mainFrame.model.middleMarginSegments) {
            if (middleSegment.getFirstNeighbour() != null) {
                drawBezierCurve(middleSegment.getFirstNeighbour().getMiddle(),
                        middleSegment.getIntersectionWithFirstNeighbour(),
                        middleSegment.getMiddle(),
                        MARGIN_COLOR,
                        MARGIN_STROKE,
                        graphics2D);
            }

            if (middleSegment.getSecondNeighbour() != null) {
                drawBezierCurve(middleSegment.getMiddle(),
                        middleSegment.getIntersectionWithSecondNeighbour(),
                        middleSegment.getSecondNeighbour().getMiddle(),
                        MARGIN_COLOR,
                        MARGIN_STROKE,
                        graphics2D);
            }
        }
    }

    /**
     * Рисуем радиальные линии границы.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintRadialMarginLines(Graphics2D graphics2D) {
        if (mainFrame.model.geoinformationDataUnits.size() == 0) {
            return;
        }

        for (float polarAngle = 0; polarAngle <= 23.75; polarAngle += 0.25) {
            for (float polarDistance = 50; polarDistance <= 89.5; polarDistance += 0.5) {
                GeoinformationDataUnit first
                        = mainFrame.model.geoinformationDataUnits.get(
                        new GeoinformationDataUnitCoordinates(polarAngle, polarDistance));

                if (first == null) {
                    continue;
                }

                float nextPolarAngle = polarAngle - 0.25f;

                if (nextPolarAngle < 0) {
                    nextPolarAngle = 23.75f;
                }

                GeoinformationDataUnit second
                        = mainFrame.model.geoinformationDataUnits.get(
                        new GeoinformationDataUnitCoordinates(nextPolarAngle, polarDistance));

                if (second == null) {
                    continue;
                }

                if (marginIsLocated(first.getValue(), second.getValue())) {
                    PointF firstPoint = first.getFirstPoint(display.getWidth());
                    PointF fourthPoint = first.getFourthPoint(display.getWidth());

                    display.drawLine(
                            (int) firstPoint.x,
                            (int) firstPoint.y,
                            (int) fourthPoint.x,
                            (int) fourthPoint.y,
                            MARGIN_COLOR,
                            MARGIN_STROKE,
                            graphics2D
                    );
                }
            }
        }
    }

    /**
     * Рисуем линии границы, которые приближенно являются круговыми.
     *
     * @param graphics2D объект для рисования.
     */
    private void paintCircleMarginLines(Graphics2D graphics2D) {
        if (mainFrame.model.geoinformationDataUnits.size() == 0) {
            return;
        }

        for (float polarAngle = 0; polarAngle <= 23.75; polarAngle += 0.25) {
            for (float polarDistance = 50.5f; polarDistance <= 89.5; polarDistance += 0.5) {
                GeoinformationDataUnit first
                        = mainFrame.model.geoinformationDataUnits.get(
                        new GeoinformationDataUnitCoordinates(polarAngle, polarDistance));

                if (first == null) {
                    continue;
                }

                float nextPolarDistance = polarDistance - 0.5f;

                GeoinformationDataUnit second
                        = mainFrame.model.geoinformationDataUnits.get(
                        new GeoinformationDataUnitCoordinates(polarAngle, nextPolarDistance));

                if (second == null) {
                    continue;
                }

                if (marginIsLocated(first.getValue(), second.getValue())) {
                    PointF firstPoint = first.getFirstPoint(display.getWidth());
                    PointF secondPoint = first.getSecondPoint(display.getWidth());

                    display.drawLine(
                            (int) firstPoint.x,
                            (int) firstPoint.y,
                            (int) secondPoint.x,
                            (int) secondPoint.y,
                            MARGIN_COLOR,
                            MARGIN_STROKE,
                            graphics2D
                    );
                }
            }
        }
    }

    /**
     * Округляем модифицированное полярное расстояние.
     *
     * @param modifiedPolarDistance упомянутое модифицированное полярное расстояние.
     * @return округленное модицифицированное полярное расстояние.
     */
    private static double roundModifiedPolarDistance(double modifiedPolarDistance) {
        double delta = modifiedPolarDistance - Math.floor(modifiedPolarDistance);
        modifiedPolarDistance = Math.floor(modifiedPolarDistance);

        if (delta < 0.5) {
            delta = 0;
        } else {
            delta = 0.5;
        }

        return modifiedPolarDistance + delta;
    }

    /**
     * Округляем модифицированный полярный угол.
     *
     * @param modifiedPolarAngle упомянутый модифицированный полярный угол.
     * @return округлыенный модифицированный полярный угол.
     */
    private static double roundModifiedPolarAngle(double modifiedPolarAngle) {
        double delta = modifiedPolarAngle - Math.floor(modifiedPolarAngle);
        modifiedPolarAngle = Math.floor(modifiedPolarAngle);

        if (delta < 0.25) {
            delta = 0;
        } else if (delta < 0.5) {
            delta = 0.25;
        } else if (delta < 0.75) {
            delta = 0.5;
        } else {
            delta = 0.75;
        }

        return modifiedPolarAngle + delta;
    }

    /**
     * Проверяем, что между единицами геоинформационных данных со значениями
     * <code>firstValue</code> и <code>secondValue</code> пролегает ребро границы.
     *
     * @param firstValue значение первой единицы геоинформационных данных.
     * @param secondValue значение второй единицы геоинформационных данных.
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean marginIsLocated(double firstValue, double secondValue) {
        return (
                (firstValue >= mainFrame.model.marginLevel) && (secondValue < mainFrame.model.marginLevel)
                ) || (
                (firstValue < mainFrame.model.marginLevel) && (secondValue >= mainFrame.model.marginLevel)
                );
    }

    /**
     * Рисуем кривую Безье по трем точкам.
     *
     * @param firstPoint первая точка для рисования кривой Безье.
     * @param secondPoint вторая точка для рисования кривой Безье.
     * @param thirdPoint третья точка для рисования кривой Безье.
     * @param color цвет кривой Безье.
     * @param stroke кисть для рисования кривой Безье.
     * @param graphics2D объект для рисования.
     */
    public void drawBezierCurve(PointF firstPoint, PointF secondPoint, PointF thirdPoint,
                                Color color, Stroke stroke, Graphics2D graphics2D) {
        Segment firstSegment = new Segment(firstPoint, secondPoint);
        Segment secondSegment = new Segment(secondPoint, thirdPoint);

        PointF previousPoint = null;

        for (float t = 0; t <= 1; t += 0.01) {
            Segment intermediateSegment = new Segment(firstSegment.getRatioPoint(t), secondSegment.getRatioPoint(t));
            PointF currentPoint = intermediateSegment.getRatioPoint(t);

            if (previousPoint != null) {
                display.drawLine(
                        (int) previousPoint.x,
                        (int) previousPoint.y,
                        (int) currentPoint.x,
                        (int) currentPoint.y,
                        color,
                        stroke,
                        graphics2D
                );
            }

            previousPoint = currentPoint;
        }
    }
}
