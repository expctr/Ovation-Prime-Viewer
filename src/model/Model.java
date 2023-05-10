/*
 * В данном файле содержится реализация модели главного фрейма
 * для паттерна MVC.
 */

package model;

import time.DateAndTimeUtil;
import view.MainFrame;
import view.PointF;

import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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

    /**
     * Индекс текущего файла.
     */
    private int currentFileIndex = 0;

    /**
     * Главный фрейм.
     */
    public final MainFrame mainFrame;

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, прогнозу, общему вкладу
//     * авроральных компонент.
//     */
//    private final ArrayList<File> northForecastEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northForecastEnergyFluxFileArray
            = new FileArray("north_forecast_total.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, наблюдаемым данным, общему вкладу
//     * авроральных компонент.
//     */
//    private final ArrayList<File> northNowcastEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northNowcastEnergyFluxFileArray
            = new FileArray("north_nowcast_total.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, прогнозу, общему вкладу
//     * авроральных компонент.
//     */
//    private final ArrayList<File> southForecastEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southForecastEnergyFluxFileArray
            = new FileArray("south_forecast_total.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, наблюдаемым данным, общему вкладу
//     * авроральных компонент.
//     */
//    private final ArrayList<File> southNowcastEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southNowcastEnergyFluxFileArray
            = new FileArray("south_nowcast_total.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, прогнозу, вкладу рассеянного сияния.
//     */
//    private final ArrayList<File> northForecastDiffuseEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northForecastDiffuseEnergyFluxFileArray
            = new FileArray("north_forecast_diffuse.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, прогнозу, вкладу ионов.
//     */
//    private final ArrayList<File> northForecastIonsEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northForecastIonsEnergyFluxFileArray
            = new FileArray("north_forecast_ions.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, прогнозу, вкладу моноэнергетических пиков.
//     */
//    private final ArrayList<File> northForecastMonoEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northForecastMonoEnergyFluxFileArray
            = new FileArray("north_forecast_mono.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, прогнозу, вкладу "broadband" ускорения.
//     */
//    private final ArrayList<File> northForecastWaveEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northForecastWaveEnergyFluxFileArray
            = new FileArray("north_forecast_wave.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, наблюдаемым данным, вкладу рассеянного сияния.
//     */
//    private final ArrayList<File> northNowcastDiffuseEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northNowcastDiffuseEnergyFluxFileArray
            = new FileArray("north_nowcast_diffuse.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, наблюдаемым данным, вкладу ионов.
//     */
//    private final ArrayList<File> northNowcastIonsEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northNowcastIonsEnergyFluxFileArray
            = new FileArray("north_nowcast_ions.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, наблюдаемым данным, вкладу моноэнергетических пиков.
//     */
//    private final ArrayList<File> northNowcastMonoEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northNowcastMonoEnergyFluxFileArray
            = new FileArray("north_nowcast_mono.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к северной полусфере, наблюдаемым данным, вкладу "broadband" ускорения.
//     */
//    private final ArrayList<File> northNowcastWaveEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray northNowcastWaveEnergyFluxFileArray
            = new FileArray("north_nowcast_wave.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, прогнозу, вкладу рассеянного сияния.
//     */
//    private final ArrayList<File> southForecastDiffuseEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southForecastDiffuseEnergyFluxFileArray
            = new FileArray("south_forecast_diffuse.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, прогнозу, вкладу ионов.
//     */
//    private final ArrayList<File> southForecastIonsEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southForecastIonsEnergyFluxFileArray
            = new FileArray("south_forecast_ions.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, прогнозу, вкладу моноэнергетических пиков.
//     */
//    private final ArrayList<File> southForecastMonoEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southForecastMonoEnergyFluxFileArray
            = new FileArray("south_forecast_mono.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, прогнозу, вкладу "broadband" ускорения.
//     */
//    private final ArrayList<File> southForecastWaveEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southForecastWaveEnergyFluxFileArray
            = new FileArray("south_forecast_wave.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, наблюдаемым данным, вкладу рассеянного сияния.
//     */
//    private final ArrayList<File> southNowcastDiffuseEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southNowcastDiffuseEnergyFluxFileArray
            = new FileArray("south_nowcast_diffuse.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, наблюдаемым данным, вкладу ионов.
//     */
//    private final ArrayList<File> southNowcastIonsEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southNowcastIonsEnergyFluxFileArray
            = new FileArray("south_nowcast_ions.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, наблюдаемым данным, вкладу моноэнергетических пиков.
//     */
//    private final ArrayList<File> southNowcastMonoEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southNowcastMonoEnergyFluxFileArray
            = new FileArray("south_nowcast_mono.txt");

//    /**
//     * Список имен файлов с данными Ovation Prime, которые относятся
//     * к южной полусфере, наблюдаемым данным, вкладу "broadband" ускорения.
//     */
//    private final ArrayList<File> southNowcastWaveEnergyFluxFiles
//            = new ArrayList<>();

    private final FileArray southNowcastWaveEnergyFluxFileArray
            = new FileArray("south_nowcast_wave.txt");

//    /**
//     * Выбранный список имен файлов.
//     */
//    private ArrayList<File> files = new ArrayList<>();

    private FileArray fileArray;

    /**
     * Флажок, который поднят, если на компоненте для рисования
     * нужно отображать информацию о выбранной единице геоинформационных данных.
     */
    public boolean showInfoFlag = false;

    private Options options;

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
    public Model(MainFrame mainFrame) throws IOException {
        this.mainFrame = mainFrame;
    }

    public void loadOptions() throws IOException {
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

    public void setOptions(Options options) throws IOException {
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
    public void setFileType(FileType fileType) throws IOException {
        options.fileType = fileType;
        selectFiles();
    }

    /**
     * Получаем размер списка файлов для визуализации.
     *
     * @return описанный размер списка.
     */
    public int getFilesSize() {
        return fileArray.size();
    }

    /**
     * Получаем имя текущего файла для визуализации.
     *
     * @return описанное имя файла.
     */
    public String getCurrentFileName() throws IOException {
        String path = fileArray.get(currentFileIndex);
        File file = new File(path);

        return file.getName();
    }

    /**
     * Переходим к файлу с указанным индексом.
     *
     * @param fileIndex упомянутый индекс файла.
     */
    public void gotoCertainFile(int fileIndex) throws IOException {
        if ((fileIndex < 0) || (fileIndex > fileArray.size() - 1)) {
            return;
        }

        currentFileIndex = fileIndex;
        load();

        mainFrame.currentFileNumberTextField.setText(String.valueOf(currentFileIndex + 1));
        mainFrame.fileNumberSlider.setValue(currentFileIndex);
        File file = new File(fileArray.get(currentFileIndex));
        mainFrame.fileNameTextField.setText(file.getName());
        mainFrame.visualize();
    }

    /**
     * Переходим к следующему файлу.
     */
    public void gotoNextFile() throws IOException {
        gotoCertainFile(currentFileIndex + 1);
    }

    /**
     * Переходим к предыдущему файлу.
     */
    public void gotoPreviousFile() throws IOException {
        gotoCertainFile(currentFileIndex - 1);
    }

    /**
     * Очищаем все списки файлов.
     */
    public void clear() throws IOException {
        northForecastEnergyFluxFileArray.clear();
        northNowcastEnergyFluxFileArray.clear();
        southForecastEnergyFluxFileArray.clear();
        southNowcastEnergyFluxFileArray.clear();
        northForecastDiffuseEnergyFluxFileArray.clear();
        northForecastIonsEnergyFluxFileArray.clear();
        northForecastMonoEnergyFluxFileArray.clear();
        northForecastWaveEnergyFluxFileArray.clear();
        northNowcastDiffuseEnergyFluxFileArray.clear();
        northNowcastIonsEnergyFluxFileArray.clear();
        northNowcastMonoEnergyFluxFileArray.clear();
        northNowcastWaveEnergyFluxFileArray.clear();
        southForecastDiffuseEnergyFluxFileArray.clear();
        southForecastIonsEnergyFluxFileArray.clear();
        southForecastMonoEnergyFluxFileArray.clear();
        southForecastWaveEnergyFluxFileArray.clear();
        southNowcastDiffuseEnergyFluxFileArray.clear();
        southNowcastIonsEnergyFluxFileArray.clear();
        southNowcastMonoEnergyFluxFileArray.clear();
        southNowcastWaveEnergyFluxFileArray.clear();
    }

    /**
     * Распределяем файл в соответствующий список.
     *
     * @param file упомянутый файл.
     */
    public void distributeFile(File file) throws IOException {
        if (file.getName().contains("north")) {
            distributeNorthFile(file);
        } else if (file.getName().contains("south")) {
            distributeSouthFile(file);
        }
    }

    /**
     * Выбираем список файлов для визуализации.
     */
    public void selectFiles() throws IOException {
        if (options.fileType.horizonSideType() == HorizonSideType.NORTH) {
            selectNorthFiles();
        } else if (options.fileType.horizonSideType() == HorizonSideType.SOUTH) {
            selectSouthFiles();
        }

        mainFrame.totalFileNumberTextField.setText(String.valueOf(fileArray.size()));
        mainFrame.fileNumberSlider.setMinimum(0);
        mainFrame.fileNumberSlider.setMaximum(fileArray.size() - 1);
        mainFrame.fileNumberSlider.setValue(0);
        mainFrame.model.gotoCertainFile(0);
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * северной полусфере.
     *
     * @param file упомянутый файл.
     */
    private void distributeNorthFile(File file) throws IOException {
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
    private void distributeSouthFile(File file) throws IOException {
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
    private void distributeNorthForecastFile(File file) throws IOException {
        if (file.getName().contains("diffuse")) {
            northForecastDiffuseEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("ions")) {
            northForecastIonsEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("mono")) {
            northForecastMonoEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("wave")) {
            northForecastWaveEnergyFluxFileArray.add(file.getAbsolutePath());
        } else {
            northForecastEnergyFluxFileArray.add(file.getAbsolutePath());
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * северной полусфере, прогнозу.
     */
    private void selectNorthForecastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            fileArray = northForecastDiffuseEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            fileArray = northForecastIonsEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            fileArray = northForecastMonoEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            fileArray = northForecastWaveEnergyFluxFileArray;
        } else {
            fileArray = northForecastEnergyFluxFileArray;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * северной полусфере, наблюдаемым данным.
     *
     * @param file упомянутый файл.
     */
    private void distributeNorthNowcastFile(File file) throws IOException {
        if (file.getName().contains("diffuse")) {
            northNowcastDiffuseEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("ions")) {
            northNowcastIonsEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("mono")) {
            northNowcastMonoEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("wave")) {
            northNowcastWaveEnergyFluxFileArray.add(file.getAbsolutePath());
        } else {
            northNowcastEnergyFluxFileArray.add(file.getAbsolutePath());
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * северной полусфере, наблюдаемым данным.
     */
    private void selectNorthNowcastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            fileArray = northNowcastDiffuseEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            fileArray = northNowcastIonsEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            fileArray = northNowcastMonoEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            fileArray = northNowcastWaveEnergyFluxFileArray;
        } else {
            fileArray = northNowcastEnergyFluxFileArray;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * южной полусфере, прогнозу.
     *
     * @param file упомянутый файл.
     */
    private void distributeSouthForecastFile(File file) throws IOException {
        if (file.getName().contains("diffuse")) {
            southForecastDiffuseEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("ions")) {
            southForecastIonsEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("mono")) {
            southForecastMonoEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("wave")) {
            southForecastWaveEnergyFluxFileArray.add(file.getAbsolutePath());
        } else {
            southForecastEnergyFluxFileArray.add(file.getAbsolutePath());
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * южной полусфере, прогнозу.
     */
    private void selectSouthForecastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            fileArray = southForecastDiffuseEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            fileArray = southForecastIonsEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            fileArray = southForecastMonoEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            fileArray = southForecastWaveEnergyFluxFileArray;
        } else {
            fileArray = southForecastEnergyFluxFileArray;
        }
    }

    /**
     * Распределяем файл в один из список, соответствующий
     * южной полусфере, наблюдаемым данным.
     *
     * @param file упомянутый файл.
     */
    private void distributeSouthNowcastFile(File file) throws IOException {
        if (file.getName().contains("diffuse")) {
            southNowcastDiffuseEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("ions")) {
            southNowcastIonsEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("mono")) {
            southNowcastMonoEnergyFluxFileArray.add(file.getAbsolutePath());
        } else if (file.getName().contains("wave")) {
            southNowcastWaveEnergyFluxFileArray.add(file.getAbsolutePath());
        } else {
            southNowcastEnergyFluxFileArray.add(file.getAbsolutePath());
        }
    }

    /**
     * Выбираем один из списков файлов, соответствующий
     * южной полусфере, наблюдаемым данным.
     */
    private void selectSouthNowcastFiles() {
        if (options.fileType.energyType() == EnergyType.DIFFUSE) {
            fileArray = southNowcastDiffuseEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.IONS) {
            fileArray = southNowcastIonsEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.MONO) {
            fileArray = southNowcastMonoEnergyFluxFileArray;
        } else if (options.fileType.energyType() == EnergyType.WAVE) {
            fileArray = southNowcastWaveEnergyFluxFileArray;
        } else {
            fileArray = southNowcastEnergyFluxFileArray;
        }
    }

    /**
     * Загружаем список единиц геонформационных данных из файла.
     */
    private void load() throws IOException {
        geoinformationDataUnits.clear();

        BufferedReader bufferedReader = new BufferedReader (new FileReader(fileArray.get(currentFileIndex)));
        List<String> lines = new ArrayList<>();

        try {
            String line;
            while((line = bufferedReader.readLine())!=null){
                lines.add(line);
            }

            bufferedReader.close();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,
                    "Ошибка при чтении файла " + fileArray.get(currentFileIndex));
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
    public void gotoFileWithSelectedDateAndTime(Calendar calendar) throws IOException {
        int indexOfSelectedFile = 0;
        File file = new File(fileArray.get(0));
        long elapsed = Math.abs(calendar.getTime().getTime()
                - getCalendarFromFileName(file.getName()).getTime().getTime());

        for (int i = 1; i < fileArray.size(); ++i) {
            File anotherFile = new File(fileArray.get(i));
            long currentElapsed = Math.abs(calendar.getTime().getTime()
                    - getCalendarFromFileName(anotherFile.getName()).getTime().getTime());

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
