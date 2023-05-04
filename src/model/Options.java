package model;

import java.io.*;
import java.util.InvalidPropertiesFormatException;

public class Options {
    private static final String PATH = "options.txt";

    public String defaultFolderAbsolutePath = "";

    public FileType fileType
            = new FileType(HorizonSideType.NORTH, CastType.NOWCAST, EnergyType.TOTAL);

    public double colorIndicatorLimit = 1.5;

    public boolean paintHeatmapFlag = true;

    public boolean paintMarginFlag = true;

    public boolean smoothMarginFlag = true;

    public double marginLevel = 0.6;

    public boolean automaticDataDownloadFlag = false;

    public int waitingTimeInSeconds = 3600;

    public static Options read() throws Exception {
        BufferedReader bufferedReader;
        Options options = new Options();

        bufferedReader = new BufferedReader(new FileReader(PATH));

        options.defaultFolderAbsolutePath = bufferedReader.readLine().trim();

        String horizonSideTypeString = bufferedReader.readLine().trim();
        HorizonSideType horizonSideType = switch (horizonSideTypeString) {
            case "north" -> HorizonSideType.NORTH;
            case "south" -> HorizonSideType.SOUTH;
            default -> throw new Exception("Unknown horizon side type " + horizonSideTypeString);
        };

        String castTypeString = bufferedReader.readLine().trim();
        CastType castType = switch (castTypeString) {
            case "forecast" -> CastType.FORECAST;
            case "nowcast" -> CastType.NOWCAST;
            default -> throw new Exception("Unknown cast type " + castTypeString);
        };

        String energyTypeString = bufferedReader.readLine().trim();
        EnergyType energyType = switch (energyTypeString) {
            case "diffuse" -> EnergyType.DIFFUSE;
            case "ions" -> EnergyType.IONS;
            case "mono" -> EnergyType.MONO;
            case "wave" -> EnergyType.WAVE;
            case "total" -> EnergyType.TOTAL;
            default -> throw new Exception("Unknown energy type " + energyTypeString);
        };

        options.fileType = new FileType(horizonSideType, castType, energyType);
        options.colorIndicatorLimit = Double.parseDouble(bufferedReader.readLine());

        String paintHeatmapFlagString = bufferedReader.readLine().trim();
        options.paintHeatmapFlag = switch (paintHeatmapFlagString) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new Exception("Unknown paint heatmap flag " + paintHeatmapFlagString);
        };

        String paintMarginFlagString = bufferedReader.readLine().trim();
        options.paintMarginFlag = switch (paintMarginFlagString) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new Exception("Unknown paint margin flag " + paintMarginFlagString);
        };

        String smoothMarginFlagString = bufferedReader.readLine().trim();
        options.smoothMarginFlag = switch (smoothMarginFlagString) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new Exception("Unknown smooth margin flag " + smoothMarginFlagString);
        };

        options.marginLevel = Double.parseDouble(bufferedReader.readLine());

        String automaticDataDownloadFlagString = bufferedReader.readLine().trim();
        options.automaticDataDownloadFlag = switch (automaticDataDownloadFlagString) {
            case "true" -> true;
            case "false" -> false;
            default -> throw new Exception("Unknown automatic data download flag "
                    + automaticDataDownloadFlagString);
        };

        options.waitingTimeInSeconds = Integer.parseInt(bufferedReader.readLine());

        bufferedReader.close();
        return options;
    }

    public void write() throws IOException {
        File file = new File(PATH);
        BufferedWriter bufferedWriter;

        file.createNewFile();
        bufferedWriter = new BufferedWriter(new FileWriter(PATH));

        bufferedWriter.write(defaultFolderAbsolutePath + "\n");

        if (fileType.horizonSideType() == HorizonSideType.NORTH) {
            bufferedWriter.write("north" + "\n");
        } else if (fileType.horizonSideType() == HorizonSideType.SOUTH) {
            bufferedWriter.write("south" + "\n");
        }

        if (fileType.castType() == CastType.FORECAST) {
            bufferedWriter.write("forecast" + "\n");
        } else if (fileType.castType() == CastType.NOWCAST) {
            bufferedWriter.write("nowcast" + "\n");
        }

        if (fileType.energyType() == EnergyType.DIFFUSE) {
            bufferedWriter.write("diffuse" + "\n");
        } else if (fileType.energyType() == EnergyType.IONS) {
            bufferedWriter.write("ions" + "\n");
        } else if (fileType.energyType() == EnergyType.MONO) {
            bufferedWriter.write("mono" + "\n");
        } else if (fileType.energyType() == EnergyType.WAVE) {
            bufferedWriter.write("wave" + "\n");
        } else if (fileType.energyType() == EnergyType.TOTAL) {
            bufferedWriter.write("total" + "\n");
        }

        bufferedWriter.write(Double.toString(colorIndicatorLimit) + "\n");

        if (paintHeatmapFlag) {
            bufferedWriter.write("true" + "\n");
        } else {
            bufferedWriter.write("false" + "\n");
        }

        if (paintMarginFlag) {
            bufferedWriter.write("true" + "\n");
        } else {
            bufferedWriter.write("false" + "\n");
        }

        if (smoothMarginFlag) {
            bufferedWriter.write("true" + "\n");
        } else {
            bufferedWriter.write("false" + "\n");
        }

        bufferedWriter.write(Double.toString(marginLevel) + "\n");

        if (automaticDataDownloadFlag) {
            bufferedWriter.write("true" + "\n");
        } else {
            bufferedWriter.write("false" + "\n");
        }

        bufferedWriter.write(Integer.toString(waitingTimeInSeconds));

        bufferedWriter.close();
    }
}
