/*
 * В данном файле содержится реализация отрезка на плоскости.
 */

package model;

import view.PointF;

import java.util.ArrayList;

/**
 * Класс реализует отрезок на плоскости.
 */
public class Segment {
    /**
     * Первый конец отрезка.
     */
    private final PointF firstEnd;

    /**
     * Второй конец отрезка.
     */
    private final PointF secondEnd;

    /**
     * Первый соседний отрезок.
     */
    private Segment firstNeighbour;

    /**
     * Второй соседний отрезок.
     */
    private Segment secondNeighbour;

    /**
     * Ориентация отрезка.
     */
    private SegmentOrientation orientation;

    /**
     * Список соседей по первому концу.
     */
    public ArrayList<Segment> firstEndNeighbours = new ArrayList<>();

    /**
     * Список соседей по второму концу.
     */
    public ArrayList<Segment> secondEndNeighbours = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param firstEnd первый конец отрезка.
     * @param secondEnd второй конец отрезка.
     */
    public Segment(PointF firstEnd, PointF secondEnd) {
        this.firstEnd = firstEnd;
        this.secondEnd = secondEnd;
    }

    /**
     * Конструктор.
     *
     * @param firstEnd первый конец отрезка.
     * @param secondEnd второй конец отрезка.
     * @param orientation ориентация.
     */
    public Segment(PointF firstEnd, PointF secondEnd, SegmentOrientation orientation) {
        this(firstEnd, secondEnd);
        this.orientation = orientation;
    }

    /**
     * Получаем первый конец отрезка.
     *
     * @return упомянутый конец.
     */
    public PointF getFirstEnd() {
        return firstEnd;
    }

    /**
     * Получаем второй конец отрезка.
     *
     * @return упомянутый конец.
     */
    public PointF getSecondEnd() {
        return secondEnd;
    }

    /**
     * Получаем первый соседний отрезок.
     *
     * @return упомянутый отрезок.
     */
    public Segment getFirstNeighbour() {
        return firstNeighbour;
    }

    /**
     * Получаем второй соседний отрезок.
     *
     * @return упомянутый отрезок.
     */
    public Segment getSecondNeighbour() {
        return secondNeighbour;
    }

    /**
     * Проверяем, что указанный отрезок является новым соседом
     * текущего отрезка.
     *
     * @param segment указанный отрезок.
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean isNewNeighbour(Segment segment) {
        if ((firstNeighbour == segment) || (secondNeighbour == segment)) {
            return false;
        } else if ((segment.firstNeighbour == this) || (segment.secondNeighbour == this)) {
            return false;
        } else {
            return (firstEnd.approximatelyEquals(segment.firstEnd)
                    || (firstEnd.approximatelyEquals(segment.secondEnd)
                    || (secondEnd.approximatelyEquals(segment.firstEnd))
                    || (secondEnd.approximatelyEquals(segment.secondEnd))));
        }
    }

    /**
     * Проверяем, что указанный отрезок является соседом по первому концу.
     *
     * @param segment указанный отрезок.
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean isFirstNeighbour(Segment segment) {
        if (firstEnd.approximatelyEquals(segment.firstEnd)
                && secondEnd.approximatelyEquals(segment.secondEnd)) {
            return false;
        } else if (firstEnd.approximatelyEquals(segment.secondEnd)
                && secondEnd.approximatelyEquals(segment.firstEnd)) {
            return false;
        }

        return firstEnd.approximatelyEquals(segment.firstEnd)
                || firstEnd.approximatelyEquals(segment.secondEnd);
    }

    /**
     * Проверяем, что указанный отрезок является соседом по второму концу.
     *
     * @param segment указанный отрезок.
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean isSecondNeighbour(Segment segment) {
        if (firstEnd.approximatelyEquals(segment.firstEnd)
                && secondEnd.approximatelyEquals(segment.secondEnd)) {
            return false;
        } else if (firstEnd.approximatelyEquals(segment.secondEnd)
                && secondEnd.approximatelyEquals(segment.firstEnd)) {
            return false;
        }

        return secondEnd.approximatelyEquals(segment.firstEnd)
                || secondEnd.approximatelyEquals(segment.secondEnd);
    }

    /**
     * Связываем текущий отрезок и указанный в качестве соседей.
     *
     * @param segment указанный отрезок.
     */
    public void connectAsNeighbour(Segment segment) {
        if (firstNeighbour == null) {
            firstNeighbour = segment;
        } else if (secondNeighbour == null) {
            secondNeighbour = segment;
        }

        if (segment.firstNeighbour == null) {
            segment.firstNeighbour = this;
        } else if (segment.secondNeighbour == null) {
            segment.secondNeighbour = this;
        }
    }

    /**
     * Связываем отрезок с истинным соседом по певому концу.
     * Если по первому концу имеется ровно один сосед, то он
     * считается истинным соседом по первому концу. Если по первому
     * концу имеется более одного соседа, то истинным соседом считается
     * сосед с той же ориентацией.
     */
    public void connectWithFirstTrueNeighbour() {
        if (firstEndNeighbours.size() == 1) {
            firstNeighbour = firstEndNeighbours.get(0);
        } else {
            Segment firstSameOrientedNeighbour = getFirstSameOrientedNeighbour();

            if (firstSameOrientedNeighbour != null) {
                firstNeighbour = firstSameOrientedNeighbour;
            }
        }
    }

    /**
     * Связываем отрезок с истинным соседом по второму концу.
     * Если по второму концу имеется ровно один сосед, то он
     * считается истинным соседом по второму концу. Если по второму
     * концу имеется более одного соседа, то истинным соседом считается
     * сосед с той же ориентацией.
     */
    public void connectWithSecondTrueNeighbour() {
        if (secondEndNeighbours.size() == 1) {
            secondNeighbour = secondEndNeighbours.get(0);
        } else {
            Segment secondSameOrientedNeighbour = getSecondSameOrientedNeighbour();

            if (secondSameOrientedNeighbour != null) {
                secondNeighbour = secondSameOrientedNeighbour;
            }
        }
    }

    /**
     * Получаем соседа по первому концу с той же ориентацией.
     *
     * @return упомянтый отрезок.
     */
    private Segment getFirstSameOrientedNeighbour() {
        for (Segment firstEndNeighbour : firstEndNeighbours) {
            if (orientation == firstEndNeighbour.orientation) {
                return firstEndNeighbour;
            }
        }

        return null;
    }

    /**
     * Получаем соседа по второму концу с той же ориентацией.
     *
     * @return упомянутый отрезок.
     */
    private Segment getSecondSameOrientedNeighbour() {
        for (Segment secondEndNeighbour : secondEndNeighbours) {
            if (orientation == secondEndNeighbour.orientation) {
                return secondEndNeighbour;
            }
        }

        return null;
    }

    /**
     * Получаем середину отрезка.
     *
     * @return середина отрезка.
     */
    public PointF getMiddle() {
        return new PointF(
                (firstEnd.x + secondEnd.x) / 2,
                (firstEnd.y + secondEnd.y) / 2
        );
    }

    /**
     * Проверяем, что отрезок имеет обоих соседей.
     *
     * @return true, если упомянутое утверждение истинно. Иначе - false.
     */
    public boolean hasBothNeighbours() {
        return (firstNeighbour != null) && (secondNeighbour != null);
    }

    /**
     * Получаем точку, которая делит данный отрезок в указанном отношении.
     *
     * @param ratio отношение длины отрезка между первым концом исходного отрезка
     *              и упомянутой точкой и длины исходного отрезка.
     * @return упомянутая точка.
     */
    public PointF getRatioPoint(float ratio) {
        if (ratio == 0) {
            return firstEnd;
        } else if (ratio == 1) {
            return secondEnd;
        }

        float lambda = ratio / (1 - ratio);

        float x = (firstEnd.x + lambda * secondEnd.x) / (1 + lambda);
        float y = (firstEnd.y + lambda * secondEnd.y) / (1 + lambda);

        return new PointF(x, y);
    }

    /**
     * Получаем точку пересечения данного отрезка и его первого соседа.
     *
     * @return упомянутая точка.
     */
    public PointF getIntersectionWithFirstNeighbour() {
        if (firstNeighbour == null) {
            return null;
        } else if (firstEnd.approximatelyEquals(firstNeighbour.firstEnd)) {
            return firstEnd;
        } else if (firstEnd.approximatelyEquals(firstNeighbour.secondEnd)) {
            return firstEnd;
        } else if (secondEnd.approximatelyEquals(firstNeighbour.firstEnd)) {
            return secondEnd;
        } else if (secondEnd.approximatelyEquals(firstNeighbour.secondEnd)) {
            return secondEnd;
        } else {
            return null;
        }
    }

    /**
     * Возвращаем точку пересечения данного отрезка и его второго соседа.
     *
     * @return упомянутая точка.
     */
    public PointF getIntersectionWithSecondNeighbour() {
        if (secondNeighbour == null) {
            return null;
        } else if (firstEnd.approximatelyEquals(secondNeighbour.firstEnd)) {
            return firstEnd;
        } else if (firstEnd.approximatelyEquals(secondNeighbour.secondEnd)) {
            return firstEnd;
        } else if (secondEnd.approximatelyEquals(secondNeighbour.firstEnd)) {
            return secondEnd;
        } else if (secondEnd.approximatelyEquals(secondNeighbour.secondEnd)) {
            return secondEnd;
        } else {
            return  null;
        }
    }
}
