/*
 * В данном файле содержится реализация контроллера
 * главного фрейма для паттерна MVC.
 */

package controller;

import model.GeoinformationDataUnit;
import model.Model;
import view.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Timer;

/**
 * Контроллер главного фрейма для паттерна MVC.
 *
 * @author Иван Шагурин
 */
public class Controller {
    /**
     * Главный фрейм.
     */
    private final MainFrame mainFrame;

    /**
     * Модель для паттерна MVC.
     */
    private final Model model;

    /**
     * Флажок, который поднят, если идет процесс обхода директории,
     * которая содержит файлы с данными для визуализации.
     */
    private static boolean processingFlag = false;

    /**
     * Таймер.
     */
    private Timer timer = new Timer();

    /**
     * Конструктор.
     *
     * @param mainFrame главный фрейм.
     * @param model модель главного фрейма для паттерна MVC.
     */
    public Controller(MainFrame mainFrame, Model model) {
        this.mainFrame = mainFrame;
        this.model = model;
    }

    /**
     * Добавляем элементам GUI обработчики событий.
     */
    public void addEventListeners() {
        VisualizationMouseAdapter visualizationMouseAdapter
                = new VisualizationMouseAdapter(this);

        mainFrame.visualizationComponent
                .addMouseListener(visualizationMouseAdapter);
        mainFrame.visualizationComponent
                .addMouseMotionListener(visualizationMouseAdapter);
        mainFrame.visualizationComponent
                .addMouseWheelListener(visualizationMouseAdapter);

        mainFrame.selectShownFilesTypeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                FileTypeFrame fileTypeFrame = new FileTypeFrame(mainFrame);
                fileTypeFrame.setVisible(true);
            }
        });

        mainFrame.selectFolderButton
                .addMouseListener(
                        new SelectFolderButtonMouseAdapter(mainFrame, model));

        mainFrame.selectDefaultFolderButton.addMouseListener(
                new SelectDefaultFolderButtonMouseAdapter(mainFrame, model));

        mainFrame.gotoPreviousFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.geoinformationDataUnits.size() == 0) {
                    return;
                }

                model.gotoPreviousFile();
            }
        });

        mainFrame.gotoNextFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.geoinformationDataUnits.size() == 0) {
                    return;
                }

                model.gotoNextFile();
            }
        });

        mainFrame.defaultOffsetAndScaleButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                mainFrame.visualizationComponent.display.adjustOffsetAndSizes(mainFrame.visualizationComponent);
                mainFrame.visualizationComponent.repaint();
            }
        });

        mainFrame.fileNumberSlider
                .addChangeListener(e -> model.gotoCertainFile(mainFrame.fileNumberSlider.getValue()));

        mainFrame.gotoFileButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.geoinformationDataUnits.size() == 0) {
                    return;
                }

                int fileNumber;

                try {
                    fileNumber = Integer.parseInt(mainFrame.fileNumberTextField.getText());
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null,
                            "Номер файла введен некорректно.");
                    return;
                }

                if ((fileNumber < 1) || (fileNumber > model.getFilesSize())) {
                    JOptionPane.showMessageDialog(null,
                            "Номер файла должен быть в диапазоне от 1 до "
                                    + model.getFilesSize()
                                    + " включительно.");
                    return;
                }

                model.gotoCertainFile(fileNumber - 1);
                mainFrame.fileNumberTextField.setText("");
            }
        });

        mainFrame.changeColorIndicatorLimitButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    float newColorIndicatorLimit
                            = Float.parseFloat(mainFrame.colorIndicatorLimitTextField.getText());

                    if (newColorIndicatorLimit <= 0) {
                        JOptionPane.showMessageDialog(
                                null,
                                "Предельное значение цветового индикатора должно быть положительным.");
                        return;
                    }

                    mainFrame.colorIndicatorLimitTextField.setText("");
                    GeoinformationDataUnit.maxValue = newColorIndicatorLimit;
                    mainFrame.visualize();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(
                            null,
                            "Новое предельное значение цветового индикатора введено некорректно.");
                }
            }
        });

        mainFrame.selectDateAndTimeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (model.geoinformationDataUnits.size() == 0) {
                    return;
                }

                String[] splitResult = mainFrame.timeTextField.getText().split(":");

                if (splitResult.length != 2) {
                    JOptionPane.showMessageDialog(null, "Время указано некорректно.");
                    return;
                }

                int hour;
                int minute;

                try {
                    hour = Integer.parseInt(splitResult[0]);
                    minute = Integer.parseInt(splitResult[1]);
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Время указано некорректно.");
                    return;
                }

                if ((hour < 0) || (hour > 23) || (minute < 0) || (minute > 59)) {
                    JOptionPane.showMessageDialog(null, "Время указано некорректно.");
                    return;
                }

                int year = mainFrame.datePickerUtil.sqlDateModel.getYear();
                int month = mainFrame.datePickerUtil.sqlDateModel.getMonth();
                int day = mainFrame.datePickerUtil.sqlDateModel.getDay();

                Calendar calendar = Calendar.getInstance();

                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);

                model.gotoFileWithSelectedDateAndTime(calendar);
            }
        });

        mainFrame.paintMarginCheckBox.addActionListener(e -> {
            model.showMarginFlag = mainFrame.paintMarginCheckBox.isSelected();
            mainFrame.visualize();
        });

        mainFrame.paintHeatmapCheckBox.addActionListener(e -> {
            model.showHeatmapFlag = mainFrame.paintHeatmapCheckBox.isSelected();
            mainFrame.visualize();
        });

        mainFrame.smoothMarginCheckBox.addActionListener(e -> {
            model.smoothMarginFlag = mainFrame.smoothMarginCheckBox.isSelected();
            mainFrame.visualize();
        });

        mainFrame.specifyMarginLevelButton.addActionListener(e -> {
            try {

                mainFrame.model.marginLevel
                        = Float.parseFloat(mainFrame.marginLevelTextField.getText());
                mainFrame.visualize();
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(
                        null,
                        "Новый уровень границы указан некорректно.");
            }
        });

        mainFrame.changeWaitingTimeButton.addActionListener(e -> {
            try {
                int waitingTimeInSeconds
                        = Integer.parseInt(mainFrame.changeWaitingTimeTextField.getText());

                if (waitingTimeInSeconds < 0) {
                    JOptionPane.showMessageDialog(null,
                            "Время ожидания не может быть отрицательным.");
                    return;
                }

                mainFrame.model.waitingTimeInSeconds = waitingTimeInSeconds;

                boolean automaticDataDownloadFlag = mainFrame.automaticDataDownloadCheckBox.isSelected();
                timer.cancel();
                timer = new Timer();
                mainFrame.changeWaitingTimeTextField.setText("");
                mainFrame.visualize();
                if (automaticDataDownloadFlag) {
                    if (mainFrame.automaticDataDownloadCheckBox.isSelected()) {
                        timer.schedule(new TimerTask() {
                            @Override
                            public void run() {
                                if (model.selectedFolder == null) {
                                    JOptionPane.showMessageDialog(null,
                                            "Папка для загрузки данных не выбрана.");
                                    return;
                                }

                                doFilesProcessing(model.selectedFolder, mainFrame, model);
                            }
                        }, model.waitingTimeInSeconds * 1000L, model.waitingTimeInSeconds * 1000L);
                    } else {
                        timer.cancel();
                        timer = new Timer();
                    }
                }
            } catch (NumberFormatException exception) {
                JOptionPane.showMessageDialog(null,
                        "Новое время ожидания указано некорректно.");
            }
        });

        mainFrame.automaticDataDownloadCheckBox.addActionListener(e -> {
            automaticDataDownloadCheckBoxListener();
        });
    }

    /**
     * Адаптер обработки событий мыши для компоненты для визуализации.
     */
    static class VisualizationMouseAdapter extends MouseAdapter {
        /**
         * Флажок, который поднят, если активен режим перемещения
         * изображения компоненты для визуализации.
         */
        private boolean inMove = false;

        /**
         * Контроллер главного фрейма для паттерна MVC.
         */
        private final Controller controller;

        /**
         * Координата x нажатия мыши на компоненту для визуализации.
         */
        int x;

        /**
         * Координата y нажатия мыши на компоненту для визуализации.
         */
        int y;

        /**
         * Конструктор.
         *
         * @param controller контроллер главного фрейма для паттерна MVC.
         */
        public VisualizationMouseAdapter(Controller controller) {
            this.controller = controller;
        }

        /**
         * Обработчик события: "мышь зажата".
         *
         * @param e the event to be processed
         */
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON3) {
                inMove = true;
                x = e.getX();
                y = e.getY();
            } else if (e.getButton() == MouseEvent.BUTTON1) {
                controller.model.showInfoFlag = true;
                controller.model.mouseAdapterX = e.getX();
                controller.model.mouseAdapterY = e.getY();

                controller.mainFrame.visualizationComponent.repaint();
            }
        }

        /**
         * Обработчик события перетаскимвания зажатой мыши.
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseDragged(MouseEvent e) {
            if (inMove) {
                int deltaX = e.getX() - x;
                int deltaY = e.getY() - y;

                x = e.getX();
                y = e.getY();

                controller.mainFrame.visualizationComponent
                        .display.changeOffset(deltaX, deltaY);
                controller.mainFrame.visualizationComponent.repaint();
            } else if (controller.model.showInfoFlag) {
                controller.model.mouseAdapterX = e.getX();
                controller.model.mouseAdapterY = e.getY();

                controller.mainFrame.visualizationComponent.repaint();
            }
        }

        /**
         * Обработчик события: "мышь отжата".
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseReleased(MouseEvent e) {
             inMove = false;
             controller.model.showInfoFlag = false;

             controller.mainFrame.visualizationComponent.repaint();
        }

        /**
         * Обработчик события: "колесико мыши прокрутилось".
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseWheelMoved(MouseWheelEvent e) {
            Display display = controller.mainFrame.visualizationComponent.display;
            int a1 = e.getX() - display.getOffsetX();
            int c1 = e.getY() - display.getOffsetY();
            float alpha;

            if (e.getWheelRotation() < 0) {
                if (!display.canIncreaseSizes()) {
                    return;
                }

                display.increaseSizes();
                alpha = Display.INCREASE_SIZE_RATIO;
            } else {
                if (!display.canDecreaseSizes()) {
                    return;
                }

                display.decreaseSizes();
                alpha = Display.DECREASE_SIZE_RATIO;
            }

            display.changeOffset((int) ((1 - alpha) * a1), (int) ((1 - alpha) * c1));

            controller.mainFrame.visualizationComponent.repaint();
        }
    }

    /**
     * Адаптер обработки событий мыши для кнопки выбора папки.
     */
    static class FolderButtonMouseAdapter extends MouseAdapter {
        /**
         * Главный фрейм.
         */
        protected MainFrame mainFrame;

        /**
         * Модель главного фрейма.
         */
        protected Model model;

        /**
         * Конструктор.
         *
         * @param mainFrame главный фрейм.
         * @param model модель главного фрейма.
         */
        public FolderButtonMouseAdapter(MainFrame mainFrame, Model model) {
            this.mainFrame = mainFrame;
            this.model = model;
        }
    }

    /**
     * Адаптер обработки событий для кнопки выбора папки вручную.
     */
    static class SelectFolderButtonMouseAdapter extends FolderButtonMouseAdapter {
        /**
         * Конструктор.
         *
         * @param mainFrame главный фрейм.
         * @param model модель главного фрейма.
         */
        public SelectFolderButtonMouseAdapter(MainFrame mainFrame, Model model) {
            super(mainFrame, model);
        }

        /**
         * Обработчик события: "мышь щелкнута".
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            if (processingFlag) {
                return;
            }

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int result = fileChooser.showOpenDialog(mainFrame);

            if (result == JFileChooser.APPROVE_OPTION) {
                mainFrame.model.selectedFolder = fileChooser.getSelectedFile();
                mainFrame.selectedFolderTextField.setText(fileChooser.getSelectedFile().getAbsolutePath());

                new Thread(() -> {
                    doFilesProcessing(fileChooser.getSelectedFile(), mainFrame, model);
                }).start();
            }
        }
    }

    /**
     * Адаптер обработки событий для кнопки выбора папки по умолчанию.
     */
    static class SelectDefaultFolderButtonMouseAdapter extends FolderButtonMouseAdapter {
        /**
         * Конструктор.
         *
         * @param mainFrame главный фрейм.
         * @param model модель главного фрейма.
         */
        public SelectDefaultFolderButtonMouseAdapter(MainFrame mainFrame, Model model) {
            super(mainFrame, model);
        }

        /**
         * Обработчик события: "мышь щелкнута".
         *
         * @param e the event to be processed
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            BufferedReader reader;

            try {
                reader = new BufferedReader(new FileReader("default.txt", StandardCharsets.UTF_8));
                String line = reader.readLine();
                File folder;

                try {
                    folder = new File(line);
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null,
                            "Ошибка при попытке открыть папку по умолчанию.");
                    return;
                }

                try {
                    mainFrame.model.selectedFolder = folder;
                    mainFrame.selectedFolderTextField.setText(folder.getAbsolutePath());

                    new Thread(() -> {
                        doFilesProcessing(folder, mainFrame, model);
                    }).start();
                } catch (Exception exception) {
                    JOptionPane.showMessageDialog(null,
                            "Ошибка при попытке открыть папку по умолчанию.");
                }

                reader.close();
            } catch (FileNotFoundException exception) {
                JOptionPane.showMessageDialog(null,
                        "Папка по умолчанию не обнаружена.");
                File file = new File("default.txt");
                try {
                    file.createNewFile();
                } catch (IOException ignored) {
                }
            } catch (IOException exception) {
                JOptionPane.showMessageDialog(null,
                        "Ошибка при попытке открыть папку по умолчанию.");
            }
        }
    }

    /**
     * Обход дерева папок с целью формирования коллекции .txt файлов,
     * включая подготовительные и заключительные действия.
     *
     * @param file корень дерева папок.
     * @param mainFrame главный фрейм.
     * @param model модель.
     */
    private static synchronized void doFilesProcessing(File file, MainFrame mainFrame, Model model) {
        if (file == null) {
            return;
        }

        mainFrame.selectFolderButton.setEnabled(false);
        mainFrame.selectDefaultFolderButton.setEnabled(false);
        processingFlag = true;
        mainFrame.progressBar.setVisible(true);
        mainFrame.container.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        model.clear();
        int numberOfTXTFiles = 500000;
        mainFrame.progressBar.setMinimum(0);
        mainFrame.progressBar.setMaximum(numberOfTXTFiles);
        processFilesFromFolder(file, mainFrame, model);
        model.selectFiles();
        mainFrame.container.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        mainFrame.progressBar.setValue(0);
        mainFrame.progressBar.setVisible(false);
        processingFlag = false;
        mainFrame.selectFolderButton.setEnabled(true);
        mainFrame.selectDefaultFolderButton.setEnabled(true);
        model.gotoCertainFile(model.getFilesSize() - 1);
    }

    /**
     * Формируем коллекцию .txt файлов через обход дерева папок.
     *
     * @param folder корень коллекции.
     * @param mainFrame главный фрейм.
     * @param model модель.
     */
    private static void processFilesFromFolder(File folder, MainFrame mainFrame, Model model)
    {
        File[] folderEntries = folder.listFiles();

        if (folderEntries == null) {
            return;
        }

        Arrays.sort(folderEntries);

        for (File entry : folderEntries)
        {
            if (entry.isDirectory())
            {
                processFilesFromFolder(entry, mainFrame, model);
                continue;
            }

            Optional<String> fileExtension = getExtensionByStringHandling(entry.getName());

            if (fileExtension.isPresent() && fileExtension.get().equals("txt")) {
                model.distributeFile(entry);
                mainFrame.progressBar.setValue(mainFrame.progressBar.getValue() + 1);
            }
        }
    }

    /**
     * Получаем расширение файла по его имени.
     *
     * @param filename имя файла.
     * @return расширение файла.
     */
    public static Optional<String> getExtensionByStringHandling(String filename) {
        return Optional.ofNullable(filename)
                .filter(f -> f.contains("."))
                .map(f -> f.substring(filename.lastIndexOf(".") + 1));
    }

    public void automaticDataDownloadCheckBoxListener() {
        if (mainFrame.automaticDataDownloadCheckBox.isSelected()) {
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if (model.selectedFolder == null) {
                        JOptionPane.showMessageDialog(null,
                                "Папка для загрузки данных не выбрана.");
                        return;
                    }

                    doFilesProcessing(model.selectedFolder, mainFrame, model);
                }
            }, model.waitingTimeInSeconds * 1000L, model.waitingTimeInSeconds * 1000L);
        } else {
            timer.cancel();
            timer = new Timer();
        }
    }
}
