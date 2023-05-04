package view;

import model.*;

import javax.swing.*;
import java.awt.*;

public class OptionsFrame extends JFrame {
    // region Поля

    private final String TITLE = "Настройки";

    /**
     * Основной контейнер.
     */
    public final Container container = getContentPane();

    public final JLabel absoluteDefaultFolderPathLabel
            = new JLabel("Абсолютный адрес папки по умолчанию:");

    public final JTextField absoluteDefaultFolderPathTextField
            = new JTextField();

    public final JLabel fileTypeLabel = new JLabel("Тип отображаемых файлов");

    /**
     * Флажок для выбора северной полусферы.
     */
    private final JCheckBox northCheckBox = new JCheckBox("north");

    /**
     * Флажок для выбора южной полусферы.
     */
    private final JCheckBox southCheckBox = new JCheckBox("south");

    /**
     * Флажок для выбора прогноза.
     */
    private final JCheckBox forecastCheckBox = new JCheckBox("forecast");

    /**
     * Флажок для выбора наблюдаемых данных.
     */
    private final JCheckBox nowcastCheckBox = new JCheckBox("nowcast");

    /**
     * Флажок для выбора вклада рассеянного сияния.
     */
    private final JCheckBox diffuseCheckBox = new JCheckBox("diffuse");

    /**
     * Флажок для выбора вклада ионов.
     */
    private final JCheckBox ionsCheckBox = new JCheckBox("ions");

    /**
     * Флажок для выбора вклада моноэнергетических пиков.
     */
    private final JCheckBox monoCheckBox = new JCheckBox("mono");

    /**
     * Флажок для выбора вклада "broadband" ускорения.
     */
    private final JCheckBox waveCheckBox = new JCheckBox("wave");

    /**
     * Флажок для выбора данных об общем вкладе авроральных компонент.
     */
    private final JCheckBox totalCheckBox = new JCheckBox("total");

    /**
     * Надпись "Предельное значение цветовой шкалы:".
     */
    public final JLabel colorIndicatorLimitLabel = new JLabel("Предельное значение цветовой шкалы:");

    /**
     * Текстовое поле для отображения предельного значения цветовой шкалы.
     */
    public final JTextField colorIndicatorLimitTextField = new JTextField();

    /**
     * Флажок "Отображать тепловую карту".
     */
    public final JCheckBox paintHeatmapCheckBox = new JCheckBox("Отображать тепловую карту");

    /**
     * Флажок "Отображать границу".
     */
    public final JCheckBox paintMarginCheckBox = new JCheckBox("Отображать границу");

    /**
     * Флажок "Гладкая граница".
     */
    public final JCheckBox smoothMarginCheckBox = new JCheckBox("Гладкая граница");

    /**
     * Надпись "Уровень границы:".
     */
    public final JLabel marginLevelLabel = new JLabel("Уровень границы:");

    /**
     * Текстовое поле для отображения уровня границы.
     */
    public final JTextField marginLevelTextField = new JTextField();

    /**
     * Флажок "Автозагрузка данных".
     */
    public final JCheckBox automaticDataDownloadCheckBox = new JCheckBox("Автозагрузка данных");

    /**
     * Надпись "Время ожидания в секундах:".
     */
    public final JLabel waitingTimeLabel = new JLabel("Время ожидания в секундах:");

    /**
     * Текстовое поле для отображения времени ожидания между
     * сеансами автозагрузки данных.
     */
    public final JTextField waitingTimeTextField = new JTextField();

    public final JButton cancelChangesButton = new JButton("Отменить изменения");

    public final JButton defaultOptionsButton = new JButton("Настройки по умолчанию");

    public final JButton saveOptionsButton = new JButton("Сохранить настройки");

    public final MainFrame mainFrame;

    // endregion

    // region Методы

    public OptionsFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setTitle(TITLE);
        setSizeAndLocation();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        marshalContainer();

        showOptions(mainFrame.model.getOptions(), true);
        addActionListeners();
    }

    private void setSizeAndLocation() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        setSize(Math.min(300, screenWidth), Math.min(555, screenHeight - 40));
        setLocation((screenWidth - getWidth()) / 2, (screenHeight - getHeight()) / 2);
    }

    private void marshalContainer() {
        container.setLayout(null);

        absoluteDefaultFolderPathLabel.setBounds(10, 10, 300, 25);
        absoluteDefaultFolderPathTextField.setBounds(10, 40, 265, 25);
        fileTypeLabel.setBounds(10, 70, 300, 25);
        northCheckBox.setBounds(10, 100, 50, 25);
        southCheckBox.setBounds(65, 100, 50, 25);
        forecastCheckBox.setBounds(10, 130, 70, 25);
        nowcastCheckBox.setBounds(85, 130, 70, 25);
        diffuseCheckBox.setBounds(10, 160, 70, 25);
        ionsCheckBox.setBounds(85, 160, 50, 25);
        monoCheckBox.setBounds(140, 160, 70, 25);
        waveCheckBox.setBounds(215, 160, 50, 25);
        totalCheckBox.setBounds(10, 190, 50, 25);
        colorIndicatorLimitLabel.setBounds(10, 215, 200, 25);
        colorIndicatorLimitTextField.setBounds(205, 215, 60, 25);
        paintHeatmapCheckBox.setBounds(10, 245, 200, 25);
        paintMarginCheckBox.setBounds(10, 275, 200, 25);
        smoothMarginCheckBox.setBounds(10, 305, 200, 25);
        marginLevelLabel.setBounds(10, 335, 150, 25);
        marginLevelTextField.setBounds(100, 335, 70, 25);
        automaticDataDownloadCheckBox.setBounds(10, 365, 150, 25);
        waitingTimeLabel.setBounds(10, 395, 150, 25);
        waitingTimeTextField.setBounds(155, 395, 70, 25);
        cancelChangesButton.setBounds(10, 425, 265, 25);
        defaultOptionsButton.setBounds(10, 455, 265, 25);
        saveOptionsButton.setBounds(10, 485, 265, 25);

        container.add(absoluteDefaultFolderPathLabel);
        container.add(absoluteDefaultFolderPathTextField);
        container.add(fileTypeLabel);
        container.add(northCheckBox);
        container.add(southCheckBox);
        container.add(forecastCheckBox);
        container.add(nowcastCheckBox);
        container.add(diffuseCheckBox);
        container.add(ionsCheckBox);
        container.add(monoCheckBox);
        container.add(waveCheckBox);
        container.add(totalCheckBox);
        container.add(colorIndicatorLimitLabel);
        container.add(colorIndicatorLimitTextField);
        container.add(paintHeatmapCheckBox);
        container.add(paintMarginCheckBox);
        container.add(smoothMarginCheckBox);
        container.add(marginLevelLabel);
        container.add(marginLevelTextField);
        container.add(automaticDataDownloadCheckBox);
        container.add(waitingTimeLabel);
        container.add(waitingTimeTextField);
        container.add(cancelChangesButton);
        container.add(defaultOptionsButton);
        container.add(saveOptionsButton);
    }

    private void showOptions(Options options, boolean changeAbsoluteDefaultFolderPathFlag) {
        if (changeAbsoluteDefaultFolderPathFlag) {
            absoluteDefaultFolderPathTextField.setText(options.defaultFolderAbsolutePath);
        }

        switch (options.fileType.horizonSideType()) {
            case NORTH -> northCheckBox.setSelected(true);
            case SOUTH -> southCheckBox.setSelected(true);
        }

        switch (options.fileType.castType()) {
            case FORECAST -> forecastCheckBox.setSelected(true);
            case NOWCAST -> nowcastCheckBox.setSelected(true);
        }

        switch (options.fileType.energyType()) {
            case DIFFUSE -> diffuseCheckBox.setSelected(true);
            case IONS -> ionsCheckBox.setSelected(true);
            case MONO -> monoCheckBox.setSelected(true);
            case WAVE -> waveCheckBox.setSelected(true);
            case TOTAL -> totalCheckBox.setSelected(true);
        }

        colorIndicatorLimitTextField.setText(Double.toString(options.colorIndicatorLimit));
        paintHeatmapCheckBox.setSelected(options.paintHeatmapFlag);
        paintMarginCheckBox.setSelected(options.paintMarginFlag);
        smoothMarginCheckBox.setSelected(options.smoothMarginFlag);
        marginLevelTextField.setText(Double.toString(options.marginLevel));
        automaticDataDownloadCheckBox.setSelected(options.automaticDataDownloadFlag);
        waitingTimeTextField.setText(Integer.toString(options.waitingTimeInSeconds));
    }

    private void addActionListeners() {
        northCheckBox.addActionListener(e -> {
            if (northCheckBox.isSelected()) {
                southCheckBox.setSelected(false);
            }
        });

        southCheckBox.addActionListener(e -> {
            if (southCheckBox.isSelected()) {
                northCheckBox.setSelected(false);
            }
        });

        forecastCheckBox.addActionListener(e -> {
            if (forecastCheckBox.isSelected()) {
                nowcastCheckBox.setSelected(false);
            }
        });

        nowcastCheckBox.addActionListener(e -> {
            if (nowcastCheckBox.isSelected()) {
                forecastCheckBox.setSelected(false);
            }
        });

        totalCheckBox.addActionListener(e -> {
            if (totalCheckBox.isSelected()) {
                diffuseCheckBox.setSelected(false);
                ionsCheckBox.setSelected(false);
                monoCheckBox.setSelected(false);
                waveCheckBox.setSelected(false);
            }
        });

        diffuseCheckBox.addActionListener(e -> {
            if (diffuseCheckBox.isSelected()) {
                totalCheckBox.setSelected(false);
                ionsCheckBox.setSelected(false);
                monoCheckBox.setSelected(false);
                waveCheckBox.setSelected(false);
            }
        });

        ionsCheckBox.addActionListener(e -> {
            totalCheckBox.setSelected(false);
            diffuseCheckBox.setSelected(false);
            monoCheckBox.setSelected(false);
            waveCheckBox.setSelected(false);
        });

        monoCheckBox.addActionListener(e -> {
            totalCheckBox.setSelected(false);
            diffuseCheckBox.setSelected(false);
            ionsCheckBox.setSelected(false);
            waveCheckBox.setSelected(false);
        });

        waveCheckBox.addActionListener(e -> {
            totalCheckBox.setSelected(false);
            diffuseCheckBox.setSelected(false);
            ionsCheckBox.setSelected(false);
            monoCheckBox.setSelected(false);
        });

        saveOptionsButton.addActionListener(e -> {
            Options options = getOptionsFromInterface();

            if (options == null) {
                return;
            }

            mainFrame.model.setOptions(options);
        });

        cancelChangesButton.addActionListener(e -> {
            showOptions(mainFrame.model.getOptions(), false);
        });

        defaultOptionsButton.addActionListener(e -> {
            Options defaultOptions = new Options();
            showOptions(defaultOptions, false);
        });
    }

    /**
     * Получаем тип файлов для визуализации.
     *
     * @return упомянутый тип файлов.
     */
    private FileType getFileType() {
        FileType fileType
                = new FileType(getHorizonSideType(),
                getCastType(),
                getEnergyType());

        if ((fileType.horizonSideType() == null)
                || (fileType.castType() == null)
                || (fileType.energyType() == null)) {
            return null;
        } else {
            return fileType;
        }
    }

    /**
     * Получаем тип полусферы.
     *
     * @return упомянутый тип полусферы.
     */
    private HorizonSideType getHorizonSideType() {
        if (northCheckBox.isSelected()) {
            return HorizonSideType.NORTH;
        } else if (southCheckBox.isSelected()) {
            return HorizonSideType.SOUTH;
        } else {
            return null;
        }
    }

    /**
     * Получаем тип прогноза.
     *
     * @return упомянутый тип прогноза.
     */
    private CastType getCastType() {
        if (forecastCheckBox.isSelected()) {
            return CastType.FORECAST;
        } else if (nowcastCheckBox.isSelected()) {
            return CastType.NOWCAST;
        } else {
            return null;
        }
    }

    /**
     * Выбираем тип энергии.
     *
     * @return упомянутый тип энергии.
     */
    private EnergyType getEnergyType() {
        if (totalCheckBox.isSelected()) {
            return EnergyType.TOTAL;
        } else if (diffuseCheckBox.isSelected()) {
            return EnergyType.DIFFUSE;
        } else if (ionsCheckBox.isSelected()) {
            return EnergyType.IONS;
        } else if (monoCheckBox.isSelected()) {
            return EnergyType.MONO;
        } else if (waveCheckBox.isSelected()) {
            return EnergyType.WAVE;
        } else {
            return null;
        }
    }

    private Options getOptionsFromInterface() {
        Options options = new Options();

        options.defaultFolderAbsolutePath = absoluteDefaultFolderPathTextField.getText();

        FileType fileType = getFileType();

        if (fileType == null) {
            JOptionPane.showMessageDialog(null,
                    "Тип отображаемых файлов выбран некорректно.");
            return null;
        }

        options.fileType = fileType;

        try {
            options.colorIndicatorLimit = Double.parseDouble(colorIndicatorLimitTextField.getText());

            if (options.colorIndicatorLimit <= 0) {
                JOptionPane.showMessageDialog(null,
                        "Предельное значение цветовой шкалы должно быть положительным.");
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null,
                    "Предельное значение цветовой шкалы введено некорректно");
            return null;
        }

        options.paintHeatmapFlag = paintHeatmapCheckBox.isSelected();
        options.paintMarginFlag = paintMarginCheckBox.isSelected();
        options.smoothMarginFlag = smoothMarginCheckBox.isSelected();

        try {
            options.marginLevel = Double.parseDouble(marginLevelTextField.getText());
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null, "Уровень границы введен некорректно.");
            return null;
        }

        options.automaticDataDownloadFlag = automaticDataDownloadCheckBox.isSelected();

        try {
            options.waitingTimeInSeconds = Integer.parseInt(waitingTimeTextField.getText());

            if (options.waitingTimeInSeconds < 0) {
                JOptionPane.showMessageDialog(null,
                        "Время ожидания между сеансами автозагрузки данных не может быть отрицательным.");
                return null;
            }
        } catch (NumberFormatException numberFormatException) {
            JOptionPane.showMessageDialog(null,
                    "Время ожидания между автозагрузкой данных в секундах введено некорректно.");
            return null;
        }

        return options;
    }

    // endregion
}
