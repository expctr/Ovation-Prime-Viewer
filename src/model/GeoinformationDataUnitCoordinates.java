/*
 * В данном файле содержится реализация класса,
 * который соответствует координатам единицы геоинформационных
 * данных.
 */

package model;

import java.util.Objects;

/**
 * Класс, который соответствует координатам единицы
 * геоинформационных данных.
 *
 * @param polarAngle полярный угол.
 * @param polarDistance полярное расстояние.
 */
public record GeoinformationDataUnitCoordinates(
        float polarAngle,
        float polarDistance
) {
    /**
     * Выполняем проверку на равенство данного класса и аргумента.
     *
     * @param obj   объект для сравнения.
     * @return true, если равенство истинно. Иначе возвращаем false.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj == null) {
            return false;
        } else if (getClass() != obj.getClass()) {
            return false;
        }

        GeoinformationDataUnitCoordinates other
                = (GeoinformationDataUnitCoordinates) obj;

        return (polarAngle == other.polarAngle)
                && (polarDistance == other.polarDistance);
    }

    /**
     * Получаем хэш-код.
     *
     * @return упомянутый хэш-код.
     */
    @Override
    public int hashCode() {
        return Objects.hash(polarAngle, polarDistance);
    }
}
