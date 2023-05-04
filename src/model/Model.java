/*
 * В данном файле содержится реализация модели главного фрейма
 * для паттерна MVC.
 */

package model;

import time.DateAndTimeUtil;
import view.MainFrame;
import view.PointF;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

/**
 * Модель главного фрейма для паттерна MVC.
 */
public class Model {
    /**
     * Индекс строки, с которой начинается чтение данных о
     * единицах геоинформационных данных.
     */
    private static final int START_INDEX = 5;

    /**
     * Индекс строки, на которой заканчивается (не включительно)
     * чтение данных о единицах геоинформационных данных.
     */
    private static final int END_INDEX = 7684;

//    /**
//     * Класс с информацией о выбранном типе файлов для визуализации.
//     */
//    public FileType fileType = new FileType(HorizonSideType.NORTH, CastType.NOWCAST, EnergyType.TOTAL);

    /**
     * Индекс текущего файла.
     */
    private int currentFileIndex = 0;

    /**
     * Главный фрейм.
     */
    public final MainFrame mainFrame;

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, прогнозу, общему вкладу
     * авроральных компонент.
     */
    private final ArrayList<File> northForecastEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, наблюдаемым данным, общему вкладу
     * авроральных компонент.
     */
    private final ArrayList<File> northNowcastEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, прогнозу, общему вкладу
     * авроральных компонент.
     */
    private final ArrayList<File> southForecastEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, наблюдаемым данным, общему вкладу
     * авроральных компонент.
     */
    private final ArrayList<File> southNowcastEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, прогнозу, вкладу рассеянного сияния.
     */
    private final ArrayList<File> northForecastDiffuseEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, прогнозу, вкладу ионов.
     */
    private final ArrayList<File> northForecastIonsEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, прогнозу, вкладу моноэнергетических пиков.
     */
    private final ArrayList<File> northForecastMonoEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, прогнозу, вкладу "broadband" ускорения.
     */
    private final ArrayList<File> northForecastWaveEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, наблюдаемым данным, вкладу рассеянного сияния.
     */
    private final ArrayList<File> northNowcastDiffuseEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, наблюдаемым данным, вкладу ионов.
     */
    private final ArrayList<File> northNowcastIonsEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, наблюдаемым данным, вкладу моноэнергетических пиков.
     */
    private final ArrayList<File> northNowcastMonoEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к северной полусфере, наблюдаемым данным, вкладу "broadband" ускорения.
     */
    private final ArrayList<File> northNowcastWaveEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, прогнозу, вкладу рассеянного сияния.
     */
    private final ArrayList<File> southForecastDiffuseEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, прогнозу, вкладу ионов.
     */
    private final ArrayList<File> southForecastIonsEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, прогнозу, вкладу моноэнергетических пиков.
     */
    private final ArrayList<File> southForecastMonoEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, прогнозу, вкладу "broadband" ускорения.
     */
    private final ArrayList<File> southForecastWaveEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, наблюдаемым данным, вкладу рассеянного сияния.
     */
    private final ArrayList<File> southNowcastDiffuseEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, наблюдаемым данным, вкладу ионов.
     */
    private final ArrayList<File> southNowcastIonsEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, наблюдаемым данным, вкладу моноэнергетических пиков.
     */
    private final ArrayList<File> southNowcastMonoEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Список имен файлов с данными Ovation Prime, которые относятся
     * к южной полусфере, наблюдаемым данным, вкладу "broadband" ускорения.
     */
    private final ArrayList<File> southNowcastWaveEnergyFluxFiles
            = new ArrayList<>();

    /**
     * Выбранный список имен файлов.
     */
    private ArrayList<File> files = new ArrayList<>();

    /**
     * Флажок, который поднят, если на компоненте для рисования
     * нужно отображать информацию о выбранной единице геоинформационных данных.
     */
    public boolean showInfoFlag = false;

    private Options options;

//    /**
//     * Флажок, который поднят, если на компоненте для рисования
//     * нужно отображать границу.
//     */
//    public boolean showMarginFlag = true;

//    /**
//     * Флажок, который поднят, если на компоненте для рисования
//     * нужно отображать тепловую карту.
//     */
//    public boolean showHeatmapFlag = true;

//    /**
//     * Флажок, который поднят, если на компоненте для рисования
//     * нужно отображать сглаженную границу.
//     */
//    public boolean smoothMarginFlag = true;

    /**
     * Время ожидания в секундах между сеансами автозаргузки данных.
     */
    public int previousWaitingTimeInSeconds = -1;

    public FileType previousFileType = null;

    /**
     * Выбранная папка с данными для визуализации.
     */
    public File selectedFolder;

    /**
     * Координата x адаптера мыши.
     */
    public int mouseAdapterX;

//    /**
//     * Уровень границы.
//     */
//    public float marginLevel = 0.6f;

    /**
     * Координата y адаптера мыши.
     */
    public int mouseAdapterY;

    /**
     * Словарь, ключами которого являются координаты единиц
     * геоинформационных данных, а значениями - соответствующие
     * единицы геоинформационных данных.
     */
    public final HashMap<GeoinformationDataUnitCoordinates,
            GeoinformationDataUnit> geoinformationDataUnits
            = new HashMap<>();

    /**
     * Список ребер границы.
     */
    public final ArrayList<Segment> marginSegments = new ArrayList<>();

    /**
     * Список отрезков, которые соединяют середины соседних отрезков границы.
     */
    public final ArrayList<Segment> middleMarginSegments = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param mainFrame главный фрейм.
     */
    public Model(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
    }

    public void loadOptions() {
        try {
            setOptions(Options.read());
        } catch (Exception exception) {
            setOptions(new Options());

            try {
                options.write();
            } catch (Exception innerException){
                innerException.printStackTrace();
            }
        }
    }

    public void setOptions(Options options) {
        this.options = options;

        try {
            this.options.write();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

        if ((previousFileType == null) || (!previousFileType.equals(options.fileType))) {
            setFileType(options.fileType);
            previousFileType = options.fileType;
        }

        mainFrame.controller.automaticDataDownloadCheckBoxListener();
        previousWaitingTimeInSeconds = options.waitingTimeInSeconds;
        mainFrame.visualize();
    }

    public Options getOptions() {
        return options;
    }

    /**
     * Устанавливаем тип фалов для визуализации.
     *
     * @param fileType описанный тип файлов.
     */
    public void setFileType(FileType fileType) {
        options.fileType = fileType;
        selectFiles();
    }

    /**
     * Получаем размер списка файлов для визуализации.
     *
     * @return описанный размер списка.
     */
    public int getFilesSize() {
        return files.size();
    }

    /**
     * Получаем имя текущего файла для визуализации.
     *
     * @return описанное имя файла.
     */
    public String getCurrentFileName() {
        return files.get(currentFileIndex).getName();
    }

    /**
     * Переходим к файлу с указанным индексом.
     *
     * @param fileIndex упомянутый индекс файла.
     */
    public void gotoCertainFile(int fileIndex) {
        if ((fileIndex < 0) || (fileIndex > files.size() - 1)) {
            return;
        }

        currentFileIndex = fileIndex;
        load();

        mainFrame.currentFileNumberTextField.setText(String.valueOf(currentFileIndex + 1));
        mainFrame.fileNumberSlider.setValue(currentFileIndex);
        mainFrame.fileNameTextField.setText(files.get(currentFileIndex).getName());
        mainFrame.visualize();
    }

    /**
     * Переходим к следующему файлу.
     */
    public void gotoNextFile() {
        gotoCertainFile(currentFileIndex + 1);
    }

    /**
     * Переходим к предыдущему файлу.
     */
    public void gotoPreviousFile() {
        gotoCertainFile(currentFileIndex - 1);
    }

    /**
     * Очищаем все списки файлов.
     */
    public void clear() {
        northForecastEnergyFluxFiles.clear();
        northNowcastEnergyFluxFiles.clear();
        southForecastEnergyFluxFiles.clear();
        southNowcastEnergyFluxFiles.clear();
        northForecastDiffuseEnergyFluxFiles.clear();
        northForecastIonsEnergyFluxFiles.clear();
        northForecastMonoEnergyFluxFiles.clear();
        northForecastWaveEnergyFluxFiles.clear();
        northNowcastDiffuseEnergyFluxFiles.clear();
        northNowcastIonsEnergyFluxFiles.clear();
        northNowcastMonoEnergyFluxFiles.clear();
        northNowcastWaveEnergyFluxFiles.clear();
        southForecastDiffuseEnergyFluxFiles.clear();
        southForecastIonsEnergyFluxFiles.clear();
        southForecastMonoEnergyFluxFiles.clear();
        southForecastWaveEnergyFluxFiles.clear();
        southNowcastDiffuseEnergyFluxFiles.clear();
        southNowcastIonsEnergyFluxFiles.clear();
        southNowcastMonoEnergyFluxFiles.clear();
        southNowcastWaveEnergyFluxFiles.clear();
    }

    /**
     * Распределяем файл в соответствующий список.
     *
     * @param file упомянутый файл.
     */
    public void distributeFile(File file) {
        if (file.getName().contains("north")) {
            distributeNorthFile(file);
        } else if (file.getName().contains("south")) {
            distributeSouthFile(file);
        }
    }

    /**
     * Выбираем список файлов для визуализации.
     */
    public void selectFiles() {
        if (options.fileType.horizonSideType() == HorizonSideType.NORTH) {
            selectNorthFiles();
        } else if (options.fileType.horizonSideType() == HorizonSideType.SOUTH) {
            selectSouthFiles();
        }

        mainFrame.totalFileNumberTextField.setText(String.valueOf(files.size()));
        mainFrame.fileNumberSlider.setMinimum(0);
        mainFrame.fileNumberSlider.setMaximum(files.size() - 1);
        mainFrame.fileNumberSlider.setValue(0);
        mainFrame.model.gotoCertainFile(0);
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * северной полусфере.
     *
     * @param file упомянутый файл.
     */
    private void distributeNorthFile(File file) {
        if (file.getName().contains("forecast")) {
            distributeNorthForecastFile(file);
        } else if (file.getName().contains("nowcast")) {
            distributeNorthNowcastFile(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * северной полусфере.
     */
    private void selectNorthFiles() {
        if (options.fileType.castType() == CastType.FORECAST) {
            selectNorthForecastFiles();
        } else if (options.fileType.castType() == CastType.NOWCAST) {
            selectNorthNowcastFiles();
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * южной полусфере.
     *
     * @param file упомянутый файл.
     */
    private void distributeSouthFile(File file) {
        if (file.getName().contains("forecast")) {
            distributeSouthForecastFile(file);
        } else if (file.getName().contains("nowcast")) {
            distributeSouthNowcastFile(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * южной полусфере.
     */
    private void selectSouthFiles() {
        if (options.fileType.castType() == CastType.FORECAST) {
            selectSouthForecastFiles();
        } else if (options.fileType.castType() == CastType.NOWCAST) {
            selectSouthNowcastFiles();
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * северной полусфере, прогнозу.
     *
     * @param file упомянутый файл.
     */
    private void distributeNorthForecastFile(File file) {
        if (file.getName().contains("diffuse")) {
            northForecastDiffuseEnergyFluxFiles.add(file);
        } else if (file.getName().contains("ions")) {
            northForecastIonsEnergyFluxFiles.add(file);
        } else if (file.getName().contains("mono")) {
            northForecastMonoEnergyFluxFiles.add(file);
        } else if (file.getName().contains("wave")) {
            northForecastWaveEnergyFluxFiles.add(file);
        } else {
            northForecastEnergyFluxFiles.add(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * северной полусфере, прогнозу.
     */
    private void selectNorthForecastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            files = northForecastDiffuseEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            files = northForecastIonsEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            files = northForecastMonoEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            files = northForecastWaveEnergyFluxFiles;
        } else {
            files = northForecastEnergyFluxFiles;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * северной полусфере, наблюдаемым данным.
     *
     * @param file упомянутый файл.
     */
    private void distributeNorthNowcastFile(File file) {
        if (file.getName().contains("diffuse")) {
            northNowcastDiffuseEnergyFluxFiles.add(file);
        } else if (file.getName().contains("ions")) {
            northNowcastIonsEnergyFluxFiles.add(file);
        } else if (file.getName().contains("mono")) {
            northNowcastMonoEnergyFluxFiles.add(file);
        } else if (file.getName().contains("wave")) {
            northNowcastWaveEnergyFluxFiles.add(file);
        } else {
            northNowcastEnergyFluxFiles.add(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * северной полусфере, наблюдаемым данным.
     */
    private void selectNorthNowcastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            files = northNowcastDiffuseEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            files = northNowcastIonsEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            files = northNowcastMonoEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            files = northNowcastWaveEnergyFluxFiles;
        } else {
            files = northNowcastEnergyFluxFiles;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * южной полусфере, прогнозу.
     *
     * @param file упомянутый файл.
     */
    private void distributeSouthForecastFile(File file) {
        if (file.getName().contains("diffuse")) {
            southForecastDiffuseEnergyFluxFiles.add(file);
        } else if (file.getName().contains("ions")) {
            southForecastIonsEnergyFluxFiles.add(file);
        } else if (file.getName().contains("mono")) {
            southForecastMonoEnergyFluxFiles.add(file);
        } else if (file.getName().contains("wave")) {
            southForecastWaveEnergyFluxFiles.add(file);
        } else {
            southForecastEnergyFluxFiles.add(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * южной полусфере, прогнозу.
     */
    private void selectSouthForecastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            files = southForecastDiffuseEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            files = southForecastIonsEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            files = southForecastMonoEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            files = southForecastWaveEnergyFluxFiles;
        } else {
            files = southForecastEnergyFluxFiles;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * южной полусфере, наблюдаемым данным.
     *
     * @param file упомянутый файл.
     */
    private void distributeSouthNowcastFile(File file) {
        if (file.getName().contains("diffuse")) {
            southNowcastDiffuseEnergyFluxFiles.add(file);
        } else if (file.getName().contains("ions")) {
            southNowcastIonsEnergyFluxFiles.add(file);
        } else if (file.getName().contains("mono")) {
            southNowcastMonoEnergyFluxFiles.add(file);
        } else if (file.getName().contains("wave")) {
            southNowcastWaveEnergyFluxFiles.add(file);
        } else {
            southNowcastEnergyFluxFiles.add(file);
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * южной полусфере, наблюдаемым данным.
     */
    private void selectSouthNowcastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            files = southNowcastDiffuseEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            files = southNowcastIonsEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            files = southNowcastMonoEnergyFluxFiles;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            files = southNowcastWaveEnergyFluxFiles;
        } else {
            files = southNowcastEnergyFluxFiles;
        }
    }

    /**
     * Загружаем список единиц геонформационных данных из файла.
     */
    private void load() {
        geoinformationDataUnits.clear();

        Path filePath
                = Paths.get(files.get(currentFileIndex).getAbsolutePath());
        Charset charset = StandardCharsets.UTF_8;
        List<String> lines;

        try {
            lines = Files.readAllLines(filePath, charset);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Ошибка при чтении файла " + filePath);
            return;
        }

        for (int i = START_INDEX; (i < END_INDEX) && (i < lines.size()); ++i) {
            GeoinformationDataUnit geoinformationDataUnit = GeoinformationDataUnit.parse(lines.get(i));
            geoinformationDataUnits.put(
                    geoinformationDataUnit.getCoordinates(),
                    geoinformationDataUnit
            );
        }
    }

    /**
     * Получаем значение единицы геоинформационных данных по
     * ее координатам.
     *
     * @param polarDistance полярное расстояние упомянутой
     *                      единицы геоинформационных данных.
     * @param polarAngle полярный угол упомянутой
     *                   единицы геоинформационных данных.
     * @return значенеи упомянутой единицы геоинформационных данных.
     */
    public double getGeoinformationDataUnitValue(float polarDistance, float polarAngle) {
        if (geoinformationDataUnits.containsKey(
                new GeoinformationDataUnitCoordinates(polarAngle, polarDistance))
        ) {
            return geoinformationDataUnits.get(
                    new GeoinformationDataUnitCoordinates(polarAngle, polarDistance)
            ).getValue();
        }

        return -1;
    }

    /**
     * Переходим к файлу, соответствующему указанным дате и времени.
     *
     * @param calendar календарь с ифнмормацией об упомянутых дате и времени.
     */
    public void gotoFileWithSelectedDateAndTime(Calendar calendar) {
        int indexOfSelectedFile = 0;
        long elapsed = Math.abs(calendar.getTime().getTime()
                - getCalendarFromFileName(files.get(0).getName()).getTime().getTime());

        for (int i = 1; i < files.size(); ++i) {
            long currentElapsed = Math.abs(calendar.getTime().getTime()
                    - getCalendarFromFileName(files.get(i).getName()).getTime().getTime());

            if (currentElapsed < elapsed) {
                indexOfSelectedFile = i;
                elapsed = currentElapsed;
            }
        }

        gotoCertainFile(indexOfSelectedFile);
    }

    /**
     * Получаем календарь с информацией о дате и времени,
     * соответствующих файлу с указанным именем.
     *
     * @param fileName упомянутое имя файла.
     * @return упомянутый календарь.
     */
    private Calendar getCalendarFromFileName(String fileName) {
        int year = DateAndTimeUtil.getYearFromFileName(fileName);
        int month = DateAndTimeUtil.getMonthFromFileName(fileName);
        int day = DateAndTimeUtil.getDayFromFileName(fileName);
        int hour = DateAndTimeUtil.getHourFromFileName(fileName);
        int minute = DateAndTimeUtil.getMinuteFromFileName(fileName);

        Calendar calendar = Calendar.getInstance();

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, day);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);

        return calendar;
    }

    /**
     * Находим ребра границы.
     */
    public void findMarginSegments() {
        marginSegments.clear();

        findRadialMarginSegments();
        findRoundMarginSegments();
    }

    /**
     * Находим ребра границы, которые лежат на радиальных линиях
     * координатной решетки.
     */
    public void findRadialMarginSegments() {
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

                if (mainFrame.visualizationComponent.marginIsLocated(first.getValue(), second.getValue())) {
                    PointF firstPoint = first.getFirstPoint(mainFrame.visualizationComponent.display.getWidth());
                    PointF fourthPoint = first.getFourthPoint(mainFrame.visualizationComponent.display.getWidth());

                    marginSegments.add(new Segment(
                            firstPoint,
                            fourthPoint,
                            SegmentOrientation.RADIAL
                    ));
                }
            }
        }
    }

    /**
     * Находим ребра границы, которые приближенно лежат на круговых
     * линиях границы.
     */
    public void findRoundMarginSegments() {
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

                if (mainFrame.visualizationComponent.marginIsLocated(first.getValue(), second.getValue())) {
                    PointF firstPoint = first.getFirstPoint(mainFrame.visualizationComponent.display.getWidth());
                    PointF secondPoint = first.getSecondPoint(mainFrame.visualizationComponent.display.getWidth());

                    marginSegments.add(new Segment(
                            firstPoint,
                            secondPoint,
                            SegmentOrientation.ROUND
                    ));
                }
            }
        }
    }

    /**
     * Связываем соседние отрезки.
     *
     * @param segments список упомянутых отрезков.
     */
    private void connectSegments(ArrayList<Segment> segments) {
        for (int i = 0; i < segments.size(); ++i) {
            for (int j = 0; j < segments.size(); ++j) {
                if (i == j) {
                    continue;
                }

                if (segments.get(i).isNewNeighbour(segments.get(j))) {
                    segments.get(i).connectAsNeighbour(segments.get(j));
                }

                if (segments.get(i).hasBothNeighbours()) {
                    break;
                }
            }
        }
    }

    /**
     * Связываем ребра границы.
     */
    public void connectMarginSegments() {
        for (int i = 0; i < marginSegments.size(); ++i) {
            for (int j = 0; j < marginSegments.size(); ++j) {
                if (i == j) {
                    continue;
                }

                if (marginSegments.get(i).isFirstNeighbour(marginSegments.get(j))) {
                    marginSegments.get(i).firstEndNeighbours.add(marginSegments.get(j));
                }

                if (marginSegments.get(i).isSecondNeighbour(marginSegments.get(j))) {
                    marginSegments.get(i).secondEndNeighbours.add(marginSegments.get(j));
                }
            }
        }

        for (Segment marginSegment : marginSegments) {
            marginSegment.connectWithFirstTrueNeighbour();
            marginSegment.connectWithSecondTrueNeighbour();
        }
    }

    /**
     * Находим отрезки, которые соединяют середины соседних
     * ребер границы.
     */
    public void findMiddleMarginSegments() {
        middleMarginSegments.clear();

        for (Segment marginSegment : marginSegments) {
            if (marginSegment.getFirstNeighbour() != null) {
                middleMarginSegments.add(new Segment(
                        marginSegment.getMiddle(),
                        marginSegment.getFirstNeighbour().getMiddle()
                ));
            }

            if (marginSegment.getSecondNeighbour() != null) {
                middleMarginSegments.add(new Segment(
                        marginSegment.getMiddle(),
                        marginSegment.getSecondNeighbour().getMiddle()
                ));
            }
        }
    }

    /**
     * Связываем соседние отрезки, которые соединяют середины
     * соседних ребер границы.
     */
    public void connectMiddleMarginSegments() {
        connectSegments(middleMarginSegments);
    }

    /**
     * Выполняем подготовку к рисованию сглаженной границы.
     */
    public void prepareToDrawSmoothMargin() {
        findMarginSegments();
        connectMarginSegments();
        findMiddleMarginSegments();
        connectMiddleMarginSegments();
    }
}
