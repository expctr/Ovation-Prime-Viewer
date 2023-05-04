/*
 * В данном файле содержится реализация главного фрейма.
 */

package view;

import controller.Controller;
import model.GeoinformationDataUnit;
import model.Model;
import time.DateAndTimeUtil;

import javax.swing.*;
import java.awt.*;
import java.util.Objects;

/**
 * Главный фрейм.
 *
 * @author Иван Шагурин
 */
public class MainFrame extends JFrame {
    /**
     * Заголовок.
     */
    public static final String TITLE = "Ovation Prime Визуализатор v1.7";

    /**
     * Основной контейнер.
     */
    public final Container container = getContentPane();

    /**
     * Полоса загрузки.
     */
    public final JProgressBar progressBar = new JProgressBar();

    /**
     * Кнопка "Выбрать папку".
     */
    public final JButton selectFolderButton = new JButton("Выбрать папку");

    /**
     * Кнопка "Папка по умолчанию".
     */
    public final JButton selectDefaultFolderButton = new JButton("Папка по умолчанию");

    /**
     * Надпись "Выбранная папка:".
     */
    public final JLabel selectedFolderLabel = new JLabel("Выбранная папка:");

    /**
     * Текстовое поле для отображения выбранной папки.
     */
    public final JTextField selectedFolderTextField = new JTextField();

    /**
     * Надпись "Текущий файл:".
     */
    public final JLabel fileNameLabel = new JLabel("Текущий файл:");

    /**
     * Текстовое поле с имененем текущего файла.
     */
    public final JTextField fileNameTextField = new JTextField(40);

    /**
     * Надпись "Дата:".
     */
    public final JLabel dateLabel = new JLabel("Дата:");

    /**
     * Класс для использования календаря.
     */
    public final JDatePickerUtil datePickerUtil = new JDatePickerUtil();

    /**
     * Надпись "Время:".
     */
    public final JLabel timeLabel = new JLabel("Время:");

    /**
     * Текстовое поле для отображения времени.
     */
    public final JTextField timeTextField = new JTextField(15);

    /**
     * Кнопка "Выбрать дату и время".
     */
    public final JButton selectDateAndTimeButton = new JButton("Выбрать дату и время");

//    /**
//     * Кнопка "Выбрать тип отображаемых файлов".
//     */
//    public final JButton selectShownFilesTypeButton = new JButton("Выбрать тип отображаемых файлов");

//    /**
//     * Текствое поле для отображения выбранного типа файлов.
//     */
//    public final JTextField shownFileTypeTextField = new JTextField(40);

//    /**
//     * Надпись "Предельное значение цветовой шкалы:".
//     */
//    public final JLabel colorIndicatorLimitLabel = new JLabel("Предельное значение цветовой шкалы:");

//    /**
//     * Текстовое поле для отображения предельного значения цветовой шкалы.
//     */
//    public final JTextField colorIndicatorLimitTextField = new JTextField();

//    /**
//     * Кнопка "Изменить" для смены предельного значения цветовой шкалы.
//     */
//    public final JButton changeColorIndicatorLimitButton
//            = new JButton("Изменить предельное значение цветовой шкалы");

    /**
     * Кнопка "Предыдущий файл".
     */
    public final JButton gotoPreviousFileButton = new JButton("Предыдущий файл");

    /**
     * Кнопка "Следующий файл".
     */
    public final JButton gotoNextFileButton = new JButton("Следующий файл");

    /**
     * Надпись "Номер файла:".
     */
    public final JLabel currentFileNumberLabel = new JLabel("Номер файла:");

    /**
     * Текстовое поле для отображения номера текущего файла.
     */
    public final JTextField currentFileNumberTextField = new JTextField();

    /**
     * Надпись "из".
     */
    public final JLabel fromLabel = new JLabel("из");

    /**
     * Текствое поле для отображения общего числа файлов выбранного типа.
     */
    public final JTextField totalFileNumberTextField = new JTextField();

    /**
     * Кнопка "Перейти к файлу".
     */
    public final JButton gotoFileButton = new JButton("Перейти к файлу");

    /**
     * Текстовое поле для отображения номера текущего файла.
     */
    public final JTextField fileNumberTextField = new JTextField();

    /**
     * Ползунок для выбора номера файла.
     */
    public final JSlider fileNumberSlider = new JSlider();

    /**
     * Кнопка "Сдвиг и масштаб по умолчанию".
     */
    public final JButton defaultOffsetAndScaleButton = new JButton("Сдвиг и масштаб по умолчанию");

//    /**
//     * Флажок "Отображать тепловую карту".
//     */
//    public final JCheckBox paintHeatmapCheckBox = new JCheckBox("Отображать тепловую карту");

//    /**
//     * Флажок "Отображать границу".
//     */
//    public final JCheckBox paintMarginCheckBox = new JCheckBox("Отображать границу");

//    /**
//     * Флажок "Гладкая граница".
//     */
//    public final JCheckBox smoothMarginCheckBox = new JCheckBox("Гладкая граница");

//    /**
//     * Надпись "Уровень границы:".
//     */
//    public final JLabel marginLevelLabel = new JLabel("Уровень границы:");

//    /**
//     * Текстовое поле для отображения уровня границы.
//     */
//    public final JTextField marginLevelTextField = new JTextField();

//    /**
//     * Кнопка "Задать".
//     */
//    public final JButton specifyMarginLevelButton = new JButton("Задать");

//    /**
//     * Флажок "Автозагрузка данных".
//     */
//    public final JCheckBox automaticDataDownloadCheckBox = new JCheckBox("Автозагрузка данных");

//    /**
//     * Надпись "Время ожидания в секундах:".
//     */
//    public final JLabel waitingTimeLabel = new JLabel("Время ожидания в секундах:");

//    /**
//     * Текстовое поле для отображения времени ожидания между
//     * сеансами автозагрузки данных.
//     */
//    public final JTextField waitingTimeTextField = new JTextField();

//    /**
//     * Кнопка "Изменить на".
//     */
//    public final JButton changeWaitingTimeButton = new JButton("Изменить на");

//    /**
//     * Текстовое поле для изменения времени ожидания между
//     * сеансами автозагрузки данных.
//     */
//    public final JTextField changeWaitingTimeTextField = new JTextField();

    public final JButton optionsButton = new JButton("Настройки");

    /**
     * Компонента для визуализации.
     */
    public final VisualizationComponent visualizationComponent = new VisualizationComponent(this);

    /**
     * Компонента для отображения цветового индикатора.
     */
    public final ColorIndicatorComponent colorIndicatorComponent = new ColorIndicatorComponent(this);

    /**
     * Модель для паттерна MVC.
     */
    public Model model;

    public Controller controller;

    /**
     * Конструктор.
     */
    public MainFrame() {
        setTitle(TITLE);
        setSizeAndLocation();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        marshalContainer();
        trySetLookAndFeel();
        visualizationComponent.initDisplay();

        model = new Model(this);

        controller = new Controller(this, model);
        controller.addEventListeners();

//        showFileType();
        model.loadOptions();
        visualize();

        // automaticDataDownloadCheckBox.setSelected(true);
//        controller.automaticDataDownloadCheckBoxListener();
    }

    /**
     * Выполняем попытку выбрать внешний вид элементов GUI.
     */
    private void trySetLookAndFeel() {
        try {
            UIManager.LookAndFeelInfo[] infos = UIManager.getInstalledLookAndFeels();

            for(var info : infos) {
                if(Objects.equals(info.getName(), "Windows")) {
                    UIManager.setLookAndFeel(info.getClassName());
                    SwingUtilities.updateComponentTreeUI(this);
                }
            }
        } catch (Exception ignored) {
            System.out.println("exception");
        }
    }

    /**
     * Устанавливаем размер и позицию главного фрейма.
     */
    private void setSizeAndLocation() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(Math.min(955, screenWidth), Math.min(770, screenHeight - 40));
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);
    }

    /**
     * Заполняем элеменами GUI главный фрейм.
     */
    private void marshalContainer() {
        container.setLayout(null);

        selectFolderButton.setBounds(10, 10, 120, 25);
        selectDefaultFolderButton.setBounds(135, 10, 150, 25);
        selectedFolderLabel.setBounds(10, 40, 200, 25);
        selectedFolderTextField.setBounds(10, 70, 280, 25);
        fileNameLabel.setBounds(10, 100, 200, 25);
        fileNameTextField.setBounds(10, 130, 280, 25);
        fileNumberSlider.setMinimum(0);
        fileNumberSlider.setMaximum(0);
        fileNumberSlider.setValue(0);
        fileNumberSlider.setBounds(10, 160, 280, 25);
        dateLabel.setBounds(10, 280, 30, 25);
        datePickerUtil.datePicker.setBounds(45, 280, 110, 25);
        timeLabel.setBounds(160, 280, 40, 25);
        timeTextField.setBounds(198, 280, 92, 25);
        selectDateAndTimeButton.setBounds(10, 310, 280, 25);
//        shownFileTypeTextField.setBounds(10, 340, 280, 25);
//        selectShownFilesTypeButton.setBounds(10, 370, 280, 25);
//        colorIndicatorLimitLabel.setBounds(10, 400, 200, 25);
//        colorIndicatorLimitTextField.setBounds(205, 400, 85, 25);
//        changeColorIndicatorLimitButton.setBounds(10, 430, 280, 25);
        gotoPreviousFileButton.setBounds(10, 190, 137, 25);
        gotoNextFileButton.setBounds(152, 190, 138, 25);
        currentFileNumberLabel.setBounds(10, 220, 70, 25);
        currentFileNumberTextField.setBounds(85, 220, 92, 25);
        fromLabel.setBounds(182, 220, 30, 25);
        totalFileNumberTextField.setBounds(198, 220, 92, 25);
        gotoFileButton.setBounds(10, 250, 182, 25);
        fileNumberTextField.setBounds(198, 250, 92, 25);
        defaultOffsetAndScaleButton.setBounds(10, 340, 280, 25);
        optionsButton.setBounds(10, 370, 280, 25);
        progressBar.setBounds(10, 400, 280, 10);
//        paintHeatmapCheckBox.setBounds(10, 490, 280, 25);
//        paintMarginCheckBox.setBounds(10, 520, 280, 25);
//        smoothMarginCheckBox.setBounds(10, 550, 280, 25);
//        marginLevelLabel.setBounds(10, 580, 100, 25);
//        marginLevelTextField.setBounds(115, 580, 70, 25);
//        specifyMarginLevelButton.setBounds(190, 580, 100, 25);
//        automaticDataDownloadCheckBox.setBounds(10, 610, 280, 25);
//        waitingTimeLabel.setBounds(10, 640, 150, 25);
//        waitingTimeTextField.setBounds(155, 640, 135, 25);
//        changeWaitingTimeButton.setBounds(10, 670, 140, 25);
//        changeWaitingTimeTextField.setBounds(155, 670, 135, 25);

        colorIndicatorComponent.setBounds(300, 0, 115, getHeight());
        visualizationComponent.setBounds(415, 0, getWidth() - 415, getHeight());

//        shownFileTypeTextField.setEditable(false);
        currentFileNumberTextField.setEditable(false);
        totalFileNumberTextField.setEditable(false);
//        waitingTimeTextField.setEditable(false);

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBounds(0, 0, 300, getHeight());
        panel.setBackground(Color.LIGHT_GRAY);

        panel.add(selectFolderButton);
        panel.add(selectDefaultFolderButton);
        panel.add(selectedFolderLabel);
        panel.add(selectedFolderTextField);
        panel.add(fileNameLabel);
        panel.add(fileNameTextField);
        panel.add(fileNumberSlider);
        panel.add(dateLabel);
        panel.add(datePickerUtil.datePicker);
        panel.add(timeLabel);
        panel.add(timeTextField);
        panel.add(selectDateAndTimeButton);
//        panel.add(shownFileTypeTextField);
//        panel.add(selectShownFilesTypeButton);
//        panel.add(colorIndicatorLimitLabel);
//        panel.add(colorIndicatorLimitTextField);
//        panel.add(changeColorIndicatorLimitButton);
        panel.add(gotoPreviousFileButton);
        panel.add(gotoNextFileButton);
        panel.add(currentFileNumberLabel);
        panel.add(currentFileNumberTextField);
        panel.add(fromLabel);
        panel.add(totalFileNumberTextField);
        panel.add(gotoFileButton);
        panel.add(fileNumberTextField);
        panel.add(defaultOffsetAndScaleButton);
        panel.add(optionsButton);
        panel.add(progressBar);
//        panel.add(paintHeatmapCheckBox);
//        panel.add(paintMarginCheckBox);
//        panel.add(smoothMarginCheckBox);
//        panel.add(marginLevelLabel);
//        panel.add(marginLevelTextField);
//        panel.add(specifyMarginLevelButton);
//        panel.add(automaticDataDownloadCheckBox);
//        panel.add(waitingTimeLabel);
//        panel.add(waitingTimeTextField);
//        panel.add(changeWaitingTimeButton);
//        panel.add(changeWaitingTimeTextField);

        container.add(panel);
        container.add(colorIndicatorComponent);
        container.add(visualizationComponent);

        progressBar.setVisible(false);
    }

//    /**
//     * Отображаем выбранный тип файлов.
//     */
//    private void showFileType() {
//        shownFileTypeTextField.setText(model.fileType.toString());
//    }

    /**
     * Производим визуализацию.
     */
    public void visualize() {
//        colorIndicatorLimitTextField
//                .setText(Float.toString(GeoinformationDataUnit.maxValue));

        if (model.geoinformationDataUnits.size() != 0) {
            int year = DateAndTimeUtil.getYearFromFileName(model.getCurrentFileName());
            int month = DateAndTimeUtil.getMonthFromFileName(model.getCurrentFileName());
            int day = DateAndTimeUtil.getDayFromFileName(model.getCurrentFileName());
            int hour = DateAndTimeUtil.getHourFromFileName(model.getCurrentFileName());
            int minute = DateAndTimeUtil.getMinuteFromFileName(model.getCurrentFileName());

            datePickerUtil.sqlDateModel.setDate(year, month, day);
            timeTextField.setText(String.format("%02d", hour) + ":" + String.format("%02d", minute));
        }

//        paintMarginCheckBox.setSelected(model.showMarginFlag);
//        paintHeatmapCheckBox.setSelected(model.showHeatmapFlag);
//        smoothMarginCheckBox.setSelected(model.smoothMarginFlag);
//        marginLevelTextField.setText(Float.toString(model.marginLevel));
//        waitingTimeTextField.setText(Integer.toString(model.waitingTimeInSeconds));

        visualizationComponent.repaint();
        colorIndicatorComponent.repaint();
    }
}
