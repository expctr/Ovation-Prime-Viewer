/*
 * В данном файле содержится фрейм для выбора типа файлов
 * для визуализации.
 */

package view;

import model.CastType;
import model.EnergyType;
import model.FileType;
import model.HorizonSideType;

import javax.swing.*;
import java.awt.*;

/**
 * Фрейм для выбора типа файлов для визуализации.
 */
public class FileTypeFrame extends JFrame {
    /**
     * Главный фрейм.
     */
    private final MainFrame mainFrame;

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
     * Кнопка "Применить".
     */
    private final JButton applyButton = new JButton("Применить");

    /**
     * Конструктор.
     *
     * @param mainFrame главный фрейм.
     */
    public FileTypeFrame(MainFrame mainFrame) {
        this.mainFrame = mainFrame;

        setTitle("Выберите тип отображаемых файлов");
        setSizeAndLocation();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        marshalContainer();
        setFileType(mainFrame.model.fileType);
        addActionListeners();
    }

    /**
     * Устанавливаем размер и позицию.
     */
    private void setSizeAndLocation() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();

        int screenHeight = screenSize.height - 40;
        int screenWidth = screenSize.width;

        setSize(400, 300);
        setLocation((screenWidth - 400) / 2, (screenHeight - 300) / 2);
    }

    /**
     * Устанавливаем тип файлов для визуализации.
     *
     * @param fileType упомянутый тип файлов.
     */
    private void setFileType(FileType fileType) {
        northCheckBox.setSelected(fileType.horizonSideType() == HorizonSideType.NORTH);
        southCheckBox.setSelected(fileType.horizonSideType() == HorizonSideType.SOUTH);

        forecastCheckBox.setSelected(fileType.castType() == CastType.FORECAST);
        nowcastCheckBox.setSelected(fileType.castType() == CastType.NOWCAST);

        diffuseCheckBox.setSelected(fileType.energyType() == EnergyType.DIFFUSE);
        ionsCheckBox.setSelected(fileType.energyType() == EnergyType.IONS);
        monoCheckBox.setSelected(fileType.energyType() == EnergyType.MONO);
        waveCheckBox.setSelected(fileType.energyType() == EnergyType.WAVE);
        totalCheckBox.setSelected(fileType.energyType() == EnergyType.TOTAL);
    }

    /**
     * Размещаем элементы GUI на контейре фрейма.
     */
    private void marshalContainer() {
        Container container = getContentPane();
        container.setLayout(null);

        northCheckBox.setBounds(150, 10, 60, 25);
        southCheckBox.setBounds(150, 30, 60, 25);

        forecastCheckBox.setBounds(150, 65, 100, 25);
        nowcastCheckBox.setBounds(150, 85, 100, 25);

        totalCheckBox.setBounds(150, 120, 100, 25);
        diffuseCheckBox.setBounds(150, 140, 100, 25);
        ionsCheckBox.setBounds(150, 160, 100, 25);
        monoCheckBox.setBounds(150, 180, 100, 25);
        waveCheckBox.setBounds(150, 200, 100, 25);

        applyButton.setBounds(150, 230, 100, 25);

        container.add(northCheckBox);
        container.add(southCheckBox);

        container.add(forecastCheckBox);
        container.add(nowcastCheckBox);

        container.add(totalCheckBox);
        container.add(diffuseCheckBox);
        container.add(ionsCheckBox);
        container.add(monoCheckBox);
        container.add(waveCheckBox);

        container.add(applyButton);
    }

    /**
     * Добавляем элементам GUI обработчики событий.
     */
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

        applyButton.addActionListener(e -> {
            FileType fileType = getFileType();

            if (fileType == null) {
                JOptionPane.showMessageDialog(null, "Ошибка. Один из параметров не выбран.");
                return;
            }

            mainFrame.model.setFileType(fileType);
            dispose();
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
}
