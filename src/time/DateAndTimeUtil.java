/*
 * В данном файле содержится класс с методами
 * для работы с именами файлов и временем.
 */

package time;

/**
 * Класс для работы с именами файлов и временем.
 *
 * @author Иван Шагурин
 */
public class DateAndTimeUtil {
    /**
     * Получаем год по имени файла.
     *
     * @param fileName упомянутое имя файла.
     * @return упомянутый год.
     */
    public static int getYearFromFileName(String fileName) {
        return Integer.parseInt(fileName.substring(0, 4));
    }

    /**
     * Получаем месяц по имени файла.
     *
     * @param fileName упомянутое имя файла.
     * @return упомянутый месяц.
     */
    public static int getMonthFromFileName(String fileName) {
        return Integer.parseInt(fileName.substring(4, 6)) - 1;
    }

    /**
     * Получаем день по имени файла.
     *
     * @param fileName упомянутре имя файла.
     * @return упомянутый день.
     */
    public static int getDayFromFileName(String fileName) {
        return Integer.parseInt(fileName.substring(6, 8));
    }

    /**
     * Получаем час по имени файла.
     *
     * @param fileName упомянутое имя файла.
     * @return упомянутый час.
     */
    public static int getHourFromFileName(String fileName) {
        return Integer.parseInt(fileName.substring(9, 11));
    }

    /**
     * Получаем минуту по имени файла.
     *
     * @param fileName упомянутое имя файла.
     * @return упомянутая минута.
     */
    public static int getMinuteFromFileName(String fileName) {
        return Integer.parseInt(fileName.substring(11, 13));
    }
}
