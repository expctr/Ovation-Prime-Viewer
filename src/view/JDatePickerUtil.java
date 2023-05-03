/*
 * В данном файле содержится класс для использования календаря.
 */

package view;

import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import org.jdatepicker.impl.SqlDateModel;

import javax.swing.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Properties;

/**
 * Класс для использования календаря.
 *
 * @author Иван Шагурин
 */
public class JDatePickerUtil {
    /**
     * Реализация календаря.
     */
    public final JDatePickerImpl datePicker;

    /**
     * Модель с данными о дате.
     */
    public final SqlDateModel sqlDateModel;

    /**
     * Конструктор.
     */
    public JDatePickerUtil() {
        sqlDateModel = new SqlDateModel();
        Properties p = new Properties();

        sqlDateModel.setSelected(true);

        p.put("text.day", "Day");
        p.put("text.month", "Month");
        p.put("text.year", "Year");

        JDatePanelImpl panel = new JDatePanelImpl(sqlDateModel, p);

        datePicker = new JDatePickerImpl(panel, new JFormattedTextField.AbstractFormatter() {
            @Override
            public String valueToString(Object value) throws ParseException {
                if (value != null) {
                    Calendar cal = (Calendar) value;
                    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                    String strDate = format.format(cal.getTime());

                    return strDate;
                }

                return "";
            }

            @Override
            public Object stringToValue(String text) throws ParseException {
                return "";
            }
        });
    }
}
