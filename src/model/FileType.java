/*
 * В данном файле содержится класс с информацией о выбранном
 * типе файлов для визуализации.
 */

package model;

/**
 * Класс с информацией о выбранном типе файлов для визуализации.
 *
 * @param horizonSideType перечисление с типом полусферы.
 * @param castType перечисление с типом прогноза данных Ovation Prime.
 * @param energyType перечисление с типом энергии, к которому относятся
 *                   данные Ovation Prime.
 *
 * @author Иван Шагурин
 */
public record FileType(HorizonSideType horizonSideType,
                       CastType castType,
                       EnergyType energyType) {
    /**
     * Получаем строковое представление класса.
     *
     * @return описанное строковое представление.
     */
    public String toString() {
        return getHorizonTypeComponent()
                + getCastTypeComponent()
                + getEnergyTypeComponent()
                + "energy flux";
    }

    /**
     * Получаем строковое представление типа полусферы.
     *
     * @return описанное строковое представление.
     */
    private String getHorizonTypeComponent() {
        if (horizonSideType == HorizonSideType.NORTH) {
            return "north ";
        } else if (horizonSideType == HorizonSideType.SOUTH) {
            return "south ";
        } else {
            return "";
        }
    }

    /**
     * Получаем строковое представление типа прогноза.
     *
     * @return описаное строковое представление.
     */
    private String getCastTypeComponent() {
        if (castType == CastType.FORECAST) {
            return "forecast ";
        } else if (castType == CastType.NOWCAST) {
            return "nowcast ";
        } else {
            return "";
        }
    }

    /**
     * Получаем строковое представление типа энергии.
     *
     * @return описанное строковое представление.
     */
    private String getEnergyTypeComponent() {
        if (energyType == EnergyType.DIFFUSE) {
            return "diffuse ";
        } else if (energyType == EnergyType.IONS) {
            return "ions ";
        } else if (energyType == EnergyType.MONO) {
            return "mono ";
        } else if (energyType == EnergyType.WAVE) {
            return "wave ";
        } else if (energyType == EnergyType.TOTAL) {
            return "";
        } else {
            return "";
        }
    }

    public boolean equals(FileType fileType) {
        return (horizonSideType == fileType.horizonSideType)
                && (castType == fileType.castType)
                && (energyType == fileType.energyType);
    }
}
